package com.wuzeyu.types.design.framework.link.model3;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
public interface ILogicChainArmory1<T, D, R> {

    ILogicLink1<T, D, R> next();

    ILogicLink1<T, D, R> appendNext(ILogicLink1<T, D, R> next);

}
