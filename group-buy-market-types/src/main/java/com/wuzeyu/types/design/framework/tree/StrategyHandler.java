package com.wuzeyu.types.design.framework.tree;

/**
 * @author wuzeyu
 * @description 策略处理器
 * @github github.com/kkkkendei
 */
public interface StrategyHandler<T, D, R> {

    StrategyHandler DEFAULT = (T, D) -> null;

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
