package com.wuzeyu.types.design.framework.link.model3;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
public abstract class AbstractLogicLink1<T, D, R> implements ILogicLink1<T, D, R> {

    @Resource
    private ILogicLink1<T, D, R> next;

    @Override
    public ILogicLink1<T, D, R> next() {
        return next;
    }

    @Override
    public ILogicLink1<T, D, R> appendNext(ILogicLink1<T, D, R> next) {
        this.next = next;
        return next;
    }

    protected R next(T requestParameter, D dynamicContext) throws Exception {
        return next.apply(requestParameter, dynamicContext);
    }

}
