package com.kaishengit.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liuyan
 * @date 2018/8/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redisson.xml")
public class RedissionTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void setValue(){
        // 设置key值并添加value
        RBucket<Object> myName = redissonClient.getBucket("myName");
        myName.set("max");
    }

    @Test
    public void getValue(){
        RBucket<Object> address = redissonClient.getBucket("address");
        address.set("jiaozuio");
    }
}
