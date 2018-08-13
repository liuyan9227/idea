package com.kaishengit.web;

import com.kaishengit.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liuyan
 * @date 2018/8/11
 */
public class UserController {

    public static void main(String[] args) {

        System.out.println("==========");

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-bbb.xml");
         UserService userService = (UserService) context.getBean("UserService");

        /*  String name = userService.findBuId(110);
        System.out.println(name);*/

    }

}
