package com.kaishengit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者提供数据向eureka提供服务
 * @author liuyan
 * @date 2018/9/3
 */
@RestController
public class EurekaController {

    @GetMapping("/movie/{id:\\d+}")
    public String movie(@PathVariable Integer id){

        return "理发师陶德";
    }
}
