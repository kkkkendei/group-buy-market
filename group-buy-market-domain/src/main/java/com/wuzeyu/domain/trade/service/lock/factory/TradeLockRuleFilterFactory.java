package com.wuzeyu.domain.trade.service.lock.factory;

import com.wuzeyu.domain.trade.model.entity.GroupBuyActivityEntity;
import com.wuzeyu.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.wuzeyu.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.wuzeyu.domain.trade.service.lock.filter.ActivityUsabilityRuleFilter;
import com.wuzeyu.domain.trade.service.lock.filter.UserTakeLimitRuleFilter;
import com.wuzeyu.types.design.framework.link.model2.LinkArmory;
import com.wuzeyu.types.design.framework.link.model2.chain.BusinessLinkedList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author wuzeyu
 * @description 交易规则过滤工厂
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class TradeLockRuleFilterFactory {

    @Bean("tradeRuleFilter")
    public BusinessLinkedList<TradeLockRuleCommandEntity, DynamicContext, TradeLockRuleFilterBackEntity> tradeRuleFilter(ActivityUsabilityRuleFilter activityUsabilityRuleFilter, UserTakeLimitRuleFilter userTakeLimitRuleFilter) {
        // 组装链
        LinkArmory<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> linkArmory =
                new LinkArmory<>("交易规则过滤链", activityUsabilityRuleFilter, userTakeLimitRuleFilter);

        // 链对象
        return linkArmory.getLogicLink();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {

        private GroupBuyActivityEntity groupBuyActivity;

    }

}
