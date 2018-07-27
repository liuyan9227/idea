package com.ly.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liuyan
 * @date 2018/7/16
 */
public class WxrProxy implements MethodInterceptor {

    /**
     * 代理类的模版
     * @param target 目标对象
     * @param method 没逼用
     * @param objects 参数
     * @param methodProxy 方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置通知");
        // 只能使用父类接收父类中定义的方法
        Object result = methodProxy.invokeSuper(target, objects);
        System.out.println("后置通知");
        System.out.println(result);
        return result;
    }
}
