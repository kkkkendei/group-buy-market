package com.wuzeyu.trigger.job;

import com.alibaba.fastjson.JSON;
import com.wuzeyu.domain.trade.service.ITradeSettlementOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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

    @Scheduled(cron = "0 0 0 * * ?")
    public void exec() {
        try {
            Map<String, Integer> res = tradeSettlementOrderService.execSettlementNotifyJob();
            log.info("定时任务，回调通知完结任务 result: {}", JSON.toJSONString(res));
        } catch (Exception e) {
            log.error("定时任务，回调通知拼团完结任务失败", e);
        }
    }

}
