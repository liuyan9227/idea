package com.ly.test;

import com.ly.entity.Dell;
import com.ly.entity.MyInvocationHandler;
import com.ly.entity.Sale;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author liuyan
 * @date 2018/7/15
 */
public class MyTestCase {

    @Test
    public void testIncocatonHandler(){
        Dell dell = new Dell();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(dell);
        Sale sale = (Sale) Proxy.newProxyInstance(
                Dell.class.getClassLoader(),
                Dell.class.getInterfaces(),
                myInvocationHandler
        );
        sale.salePC("max");
    }
}
