package com.wuzeyu.types.design.framework.link.model2.chain;

import com.wuzeyu.types.design.framework.link.model2.handler.ILogicHandler;

/**
 * @author wuzeyu
 * @description 业务链路
 * @github github.com/kkkkendei
 */
public class BusinessLinkedList<T, D, R> extends LinkedList<ILogicHandler<T, D, R>> implements ILogicHandler<T, D, R> {

    public BusinessLinkedList(String name) {
        super(name);
    }

    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        Node<ILogicHandler<T, D, R>> current = this.first;
        do {
            ILogicHandler<T, D, R> item = current.item;
            R apply = item.apply(requestParameter, dynamicContext);
            if (apply != null) return apply;

            current = current.next;
        } while (current != null);

        return null;
    }
}
