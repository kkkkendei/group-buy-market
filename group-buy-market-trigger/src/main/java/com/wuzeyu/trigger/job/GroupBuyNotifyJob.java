package com.wuzeyu.trigger.job;

import com.alibaba.fastjson.JSON;
import com.wuzeyu.domain.trade.service.ITradeSettlementOrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzeyu
 * @description 拼团完结回调通知任务；拼团回调任务表，实际公司场景会定时清理数据结转，不会有太多数据挤压
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class GroupBuyNotifyJob {

    @Resource
    private ITradeSettlementOrderService tradeSettlementOrderService;

    @Resource
    private RedissonClient redissonClient;

    @Scheduled(cron = "0 0 0 * * ?")
    public void exec() {
        // 为什么加锁？分布式应用N台机器部署互备（一个应用实例挂了，还有另外可用的），任务调度会有N个同时执行，那么这里需要增加抢占机制，谁抢占到谁就执行。完毕后，下一轮继续抢占。
        RLock lock = redissonClient.getLock("group-buy-notify-job");
        try {
            boolean isLock = lock.tryLock(3, 0, TimeUnit.SECONDS);
            if (! isLock) {
                return;
            }
            Map<String, Integer> res = tradeSettlementOrderService.execSettlementNotifyJob();
            log.info("定时任务，回调通知完结任务 result: {}", JSON.toJSONString(res));
        } catch (Exception e) {
            log.error("定时任务，回调通知拼团完结任务失败", e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

}
