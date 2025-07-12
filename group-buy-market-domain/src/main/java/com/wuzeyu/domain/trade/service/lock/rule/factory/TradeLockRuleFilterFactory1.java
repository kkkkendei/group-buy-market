package com.wuzeyu.domain.trade.service.lock.rule.factory;

import com.wuzeyu.domain.trade.model.entity.GroupBuyActivityEntity;
import com.wuzeyu.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.wuzeyu.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.wuzeyu.domain.trade.service.lock.rule.filter.ActivityUsabilityRuleFilter1;
import com.wuzeyu.domain.trade.service.lock.rule.filter.UserTakeLimitRuleFilter1;
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
 * @description
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class TradeLockRuleFilterFactory1 {

    @Bean("tradeLockRuleFilter1")
    public BusinessLinkedList<TradeLockRuleCommandEntity, DynamicContext, TradeLockRuleFilterBackEntity> openLogic (UserTakeLimitRuleFilter1 userTakeLimitRuleFilter1, ActivityUsabilityRuleFilter1 activityUsabilityRuleFilter1) {
        LinkArmory<TradeLockRuleCommandEntity, DynamicContext, TradeLockRuleFilterBackEntity> linkArmory = new LinkArmory<>("logic", userTakeLimitRuleFilter1, activityUsabilityRuleFilter1);
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
