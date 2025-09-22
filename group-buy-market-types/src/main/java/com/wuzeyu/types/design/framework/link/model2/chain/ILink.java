package com.wuzeyu.types.design.framework.link.model2.chain;

/**
 * @author wuzeyu
 * @description 链接口
 * @github github.com/kkkkendei
 */
public interface ILink<E> {

    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean remove(Object o);

    E get(int index);

    void printLinkList();

}
