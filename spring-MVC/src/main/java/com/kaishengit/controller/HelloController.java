package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuyan
 * @date 2018/7/20
 */

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello,springMVC");
        return "hello";
    }

}
