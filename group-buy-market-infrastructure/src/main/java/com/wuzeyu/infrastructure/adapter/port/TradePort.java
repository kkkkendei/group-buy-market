package com.wuzeyu.infrastructure.adapter.port;

import com.wuzeyu.domain.trade.adapter.port.ITradePort;
import com.wuzeyu.domain.trade.model.entity.NotifyTaskEntity;
import com.wuzeyu.infrastructure.gateway.GroupBuyNotifyService;
import com.wuzeyu.infrastructure.redis.IRedisService;
import com.wuzeyu.types.enums.NotifyTaskHTTPEnumVO;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Service
public class TradePort implements ITradePort {

    @Resource
    private GroupBuyNotifyService groupBuyNotifyService;

    @Resource
    private IRedisService redisService;

    @Override
    public String groupBuyNotify(NotifyTaskEntity notifyTask) {
        RLock lock = redisService.getLock(notifyTask.lockKey());
        try {
            // group-buy-market 拼团服务端会被部署到多台应用服务器, 那么就会有很多任务一起执行。这个时候要进行抢占，避免被多次执行
            if (lock.tryLock(3, 0, TimeUnit.SECONDS)) {
                try {
                    if (StringUtils.isBlank(notifyTask.getNotifyUrl()) || notifyTask.getNotifyUrl().equals("暂无")) {
                        return NotifyTaskHTTPEnumVO.SUCCESS.getCode();
                    }
                    return groupBuyNotifyService.groupBuyNotify(notifyTask.getNotifyUrl(), notifyTask.getParameterJson());
                } finally {
                        if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                            lock.unlock();
                        }
                    }
            }
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        }
    }

}