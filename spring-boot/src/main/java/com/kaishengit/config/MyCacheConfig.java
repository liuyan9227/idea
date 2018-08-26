package com.kaishengit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author liuyan
 * @date 2018/8/24
 */
@Configuration
@EnableCaching
public class MyCacheConfig {

    @Autowired
    private RedisCacheProperties redisCacheProperties;

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setUsePrefix(true);
        redisCacheManager.setExpires(redisCacheProperties.getExpires());
        return redisCacheManager;
    }



    /*@Bean
    public CacheManager cacheManager(){
        return new EhCacheCacheManager();
    }*/

}
