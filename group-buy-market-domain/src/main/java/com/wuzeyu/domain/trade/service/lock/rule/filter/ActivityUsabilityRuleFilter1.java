package com.wuzeyu.domain.trade.service.lock.rule.filter;

import com.wuzeyu.domain.trade.adapter.repository.ITradeRepository;
import com.wuzeyu.domain.trade.model.entity.GroupBuyActivityEntity;
import com.wuzeyu.domain.trade.model.entity.TradeLockRuleCommandEntity;
import com.wuzeyu.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import com.wuzeyu.domain.trade.service.lock.rule.factory.TradeLockRuleFilterFactory1;
import com.wuzeyu.types.design.framework.link.model2.handler.ILogicHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description 活动可用性 规则过滤[状态、有效期]
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class ActivityUsabilityRuleFilter1 implements ILogicHandler<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory1.DynamicContext, TradeLockRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeLockRuleFilterBackEntity apply(TradeLockRuleCommandEntity requestParameter, TradeLockRuleFilterFactory1.DynamicContext dynamicContext) throws Exception {
        GroupBuyActivityEntity groupBuyActivity = repository.queryGroupBuyActivityEntityByActivityId(requestParameter.getActivityId());

        return null;
    }

}
