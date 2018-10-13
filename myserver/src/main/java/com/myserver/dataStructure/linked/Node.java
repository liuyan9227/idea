package com.myserver.dataStructure.linked;

/**
 * 自定义Node类
 *
 * 此类型专为实现链表结构需要的两个属性[volue, next]
 */
public class Node {
    // 存放值
    private Object value;
    // 存放下一个值
    private Node next;

    public Node(){}

    // 构造方法重载
    public Node(Object value){
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
