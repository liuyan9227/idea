package com.ly.entity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liuyan
 * @date 2018/7/15
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(target, args);
        System.out.println("after");
        return result;
    }

}
