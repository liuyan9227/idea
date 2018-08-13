package com.kaishengit.web;


import com.kaishengit.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author liuyan
 * @date 2018/8/11
 */
public class App1 {


    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        // 读取配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-bbb.xml");
        // 获得配置文件的bean节点的对象
        UserService userService = (UserService) context.getBean("UserService");
        System.out.println("请输入");
        // 适用对象调用方法
        Integer num = input.nextInt();

        String name = userService.findById(num);

        System.out.println("name:" + name);
        // 容器始终处于运行状态
        System.in.read();
    }
}
