package com.wuzeyu.types.design.framework.link.model3.chain;

import com.wuzeyu.types.design.framework.link.model3.handler.ILogicHandler;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
public class BusinessLinkedList<T, D, R> extends LinkedList<ILogicHandler<T, D, R>> implements ILogicHandler<T, D, R> {

    public BusinessLinkedList(String linkName) {
        super(linkName);
    }

    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        Node<ILogicHandler<T, D, R>> currentNode = this.first;
        do {
            R result = currentNode.item.apply(requestParameter, dynamicContext);
            if (result != null) {
                return result;
            }
            currentNode = currentNode.next;
        } while (currentNode != null);

        return null;
    }

}
