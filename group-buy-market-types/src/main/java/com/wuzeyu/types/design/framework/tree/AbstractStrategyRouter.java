package com.wuzeyu.types.design.framework.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wuzeyu
 * @description 策略路由抽象类
 * @github github.com/kkkkendei
 */
public abstract class AbstractStrategyRouter<T, D, R> implements StrategyMapper, StrategyHandler {

    @Getter
    @Setter
    protected StrategyHandler<T, D, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

    public R router(T requestParam, D dynamicContext) throws Exception {
        StrategyHandler<T, D, R> strategyHandler = get(requestParam, dynamicContext);
        if (strategyHandler != null) {
            return strategyHandler.apply(requestParam, dynamicContext);
        }

        return defaultStrategyHandler.apply(requestParam, dynamicContext);
    }

}
