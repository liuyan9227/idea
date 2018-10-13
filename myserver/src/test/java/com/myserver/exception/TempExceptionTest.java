package com.myserver.exception;

import org.junit.Test;

/**
 * Java - 类ExampleA继承Exception，类ExampleB继承ExampleA。
 * 有如下代码片断：请问执行此段代码的输出是什么？
 *
 * 输出：ExampleA。（根据里氏代换原则[能使用父类型的地方一定能使用子类型]，
 * 抓取ExampleA类型异常的catch块能够抓住try块中抛出的ExampleB类型的异常）
 */
public class TempExceptionTest {
    @Test
    public void tempException(){
        try {
            throw new ExceptionB("b");
        } catch(ExceptionA e){
            System.out.println("ExampleA");
        } catch(Exception e){
            System.out.println("Exception");
        }
    }
}
