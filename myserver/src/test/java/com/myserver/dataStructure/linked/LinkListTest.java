package com.myserver.dataStructure.linked;

import org.junit.Test;

/**
 * 测试手写的LinkList的链表
 */
public class LinkListTest {

    // 测试链表
    @Test
    public void testLinkList(){
        LinkList linkList = new LinkList();

        linkList.add(1);
        linkList.add(2);
        linkList.add(3);
        linkList.add(4);

        int size = linkList.getSize();
        System.out.println("该链表的长度为" + size);
        System.out.println("该链表的数据为" + linkList);
    }
}
