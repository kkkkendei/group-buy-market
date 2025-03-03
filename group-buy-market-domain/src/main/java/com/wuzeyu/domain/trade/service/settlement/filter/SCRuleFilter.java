package com.wuzeyu.domain.trade.service.settlement.filter;

import com.wuzeyu.domain.trade.adapter.repository.ITradeRepository;
import com.wuzeyu.domain.trade.model.entity.TradeSettlementRuleCommandEntity;
import com.wuzeyu.domain.trade.model.entity.TradeSettlementRuleFilterBackEntity;
import com.wuzeyu.domain.trade.service.settlement.factory.TradeSettlementRuleFilterFactory;
import com.wuzeyu.types.design.framework.link.model2.handler.ILogicHandler;
import com.wuzeyu.types.enums.ResponseCode;
import com.wuzeyu.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description SC 渠道来源过滤 - 当某个签约渠道下架后，则不会记账
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class SCRuleFilter implements ILogicHandler<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeSettlementRuleFilterBackEntity apply(TradeSettlementRuleCommandEntity requestParameter, TradeSettlementRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("结算规则过滤-渠道黑名单校验{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());

        // sc 渠道黑名单拦截
        boolean intercept = repository.isSCBlackIntercept(requestParameter.getSource(), requestParameter.getChannel());
        if (intercept) {
            log.error("{}{} 渠道黑名单拦截", requestParameter.getSource(), requestParameter.getChannel());
            throw new AppException(ResponseCode.E0105);
        }

        return next(requestParameter, dynamicContext);
    }

}
