package com.myserver.arithmetic.recursion;

import org.junit.Test;

/**
 * 测试递归算法
 */
public class MyRecursionTest {

    MyRecursion recursion = new MyRecursion();

    @Test
    public void testRecursionSum(){
        Integer sum = recursion.recursionSum(10);
        System.out.println("得到的递归的和是 : " + sum);
    }

    @Test
    public void testRecursionMulity(){
        Integer mulity = recursion.recursionMulity(10);
        System.out.println("得到的递归的阶乘的得数是 : " + mulity);
    }

    @Test
    public void testFun(){
        String[] string = new String[]{"水电费水电费水电费"};
        boolean fun = recursion.fun(3, string);
        System.out.println(fun);
    }
}
