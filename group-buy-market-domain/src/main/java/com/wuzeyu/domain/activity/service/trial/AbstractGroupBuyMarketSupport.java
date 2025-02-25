package com.wuzeyu.domain.activity.service.trial;

import com.wuzeyu.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.wuzeyu.types.design.framework.tree.AbstractMultiThreadStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author wuzeyu
 * @description 抽象的拼团营销支撑类
 * @github github.com/kkkkendei
 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<com.wuzeyu.domain.activity.model.entity.MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, com.wuzeyu.domain.activity.model.entity.TrialBalanceEntity> {

    protected long timeout = 500;


    @Override
    protected void multiThread(com.wuzeyu.domain.activity.model.entity.MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 缺省的方法
    }

}
