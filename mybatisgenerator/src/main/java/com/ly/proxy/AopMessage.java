package com.ly.proxy;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author liuyan
 * @date 2018/7/16
 */
public class AopMessage {

    public void before(){
        System.out.println("前置通知");
    }

    public void after(){
        System.out.println("后置通知");
    }

    public void exception(){
        System.out.println("异常通知");
    }

    public void finaly(){
        System.out.println("最终通知");
    }

    public Object around(ProceedingJoinPoint proceedingJoinPoint){

        Object result = null;
        try {
            System.out.println("前置");
            result = proceedingJoinPoint.proceed();
            System.out.println("后置");
        } catch (Throwable throwable) {
            System.out.println("异常");
        } finally {
            System.out.println("最终");
        }
        return result;
    }
}
