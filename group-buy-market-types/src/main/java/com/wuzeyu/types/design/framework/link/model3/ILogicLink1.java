package com.wuzeyu.types.design.framework.link.model3;

public interface ILogicLink1<T, D, R> extends ILogicChainArmory1<T, D, R> {

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
