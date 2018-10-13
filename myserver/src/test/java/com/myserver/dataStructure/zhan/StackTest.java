package com.myserver.dataStructure.zhan;

import org.junit.Test;

public class StackTest {

    @Test
    public void testStack(){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
