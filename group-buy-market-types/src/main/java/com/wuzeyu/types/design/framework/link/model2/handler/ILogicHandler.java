package com.wuzeyu.types.design.framework.link.model2.handler;

/**
 * @author wuzeyu
 * @description 逻辑处理器
 * @github github.com/kkkkendei
 */
public interface ILogicHandler<T, D, R> {

    default R next(T requestParameter, D dynamicContext) {
        return null;
    }

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
