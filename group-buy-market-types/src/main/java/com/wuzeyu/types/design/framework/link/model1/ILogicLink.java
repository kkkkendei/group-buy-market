package com.wuzeyu.types.design.framework.link.model1;

/**
 * @author wuzeyu
 * @description 责任链接口
 * @github github.com/kkkkendei
 */
public interface ILogicLink<T, D, R> extends ILogicChainArmory<T, D, R> {

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
