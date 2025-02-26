package com.wuzeyu.domain.activity.service;

import com.wuzeyu.domain.activity.model.entity.MarketProductEntity;
import com.wuzeyu.domain.activity.model.entity.TrialBalanceEntity;
import com.wuzeyu.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.wuzeyu.types.design.framework.tree.StrategyHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Service
public class IIndexGroupBuyMarketServiceImpl implements IIndexGroupBuyMarketService {

    @Resource
    private DefaultActivityStrategyFactory activityStrategyFactory;

    @Override
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception {
        StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandler = activityStrategyFactory.strategyHandler();
        TrialBalanceEntity trialBalanceEntity = strategyHandler.apply(marketProductEntity, new DefaultActivityStrategyFactory.DynamicContext());
        return trialBalanceEntity;
    }

}
