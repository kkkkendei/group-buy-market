package com.wuzeyu.types.design.framework.link.model1;

/**
 * @author wuzeyu
 * @description 责任链装配
 * @github github.com/kkkkendei
 */
public interface ILogicChainArmory<T, D, R> {

    ILogicLink<T, D, R> next();

    ILogicLink<T, D, R> appendNext(ILogicLink<T, D, R> next);

}
