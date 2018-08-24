package com.kaishengit.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuyan
 * @date 2018/8/23
 */
@ResponseBody
@Controller
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 1.使用注入的redisson客户端对象,获取
     */
    @GetMapping("/redisson")
    public String redisson(){
        // 查看是否存在Key值
        RBucket<String> userName = redissonClient.getBucket("userName");
            userName.set("max");
        if(userName.isExists()){
            return userName.get();
        }
        return "nothing";
    }
}
