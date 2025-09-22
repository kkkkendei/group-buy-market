package com.wuzeyu.types.design.framework.link.model3;

import com.wuzeyu.types.design.framework.link.model3.chain.BusinessLinkedList;
import com.wuzeyu.types.design.framework.link.model3.handler.ILogicHandler;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
public class LinkArmory<T, D, R> {

    private final BusinessLinkedList<T, D, R> logicLink;

    @SafeVarargs
    public LinkArmory(String linkName, ILogicHandler<T, D, R>... logicHandlers) {
        logicLink = new BusinessLinkedList<>(linkName);
        for (ILogicHandler<T, D, R> logicHandler : logicHandlers) {
            logicLink.add(logicHandler);
        }
    }

    public BusinessLinkedList<T, D, R> getLogicLink() {
        return logicLink;
    }

}
