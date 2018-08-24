package com.kaishengit.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyan
 * @date 2018/8/23
 */
// 1.RedissonConfig配置类作为redisson的配置文件,代替xml;
@Configuration
public class RedissonConfig {

    @Autowired
    private RedissonConfigProperties redissonConfigProperties;
    // 2.创建bean节点
    @Bean
    // <bean id="redissonConfig" class="RedissonConfig">
    public RedissonClient redissonClient(){
        // 3.设置方法里面的参数
        Config config = new Config();
        // 使用单节点部署方式, 设置服务器地址, 设置延迟时间
        config.useSingleServer().setAddress(redissonConfigProperties.getHost()).setTimeout(redissonConfigProperties.getTimeout());
        // 创建redisson客户端对象, 并返回
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }


}
