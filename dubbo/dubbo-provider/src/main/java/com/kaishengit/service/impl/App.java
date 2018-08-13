package com.kaishengit.service.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author liuyan
 * @date 2018/8/11
 */
public class App {

    public static void main(String[] args) throws IOException {

        // 读取配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aaa.xml");
        // 启动容器
        context.start();

        System.out.println("容器启动了");
        // 容器始终处于运行状态
        System.in.read();
    }
}
