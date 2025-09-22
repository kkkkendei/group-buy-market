package com.wuzeyu.types.design.framework.tree;

/**
 * @author wuzeyu
 * @description 策略映射器
 * @github github.com/kkkkendei
 */
public interface StrategyMapper<T, D, R> {

    StrategyHandler<T, D, R> get(T requestParam, D dynamicContext) throws Exception;

}
