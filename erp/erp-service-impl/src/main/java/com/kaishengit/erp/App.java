package com.kaishengit.erp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author liuyan
 * @date 2018/8/13
 */
public class App {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");

        context.start();

        System.out.println("容器启动成功");

        System.in.read();

    }
}
