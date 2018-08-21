package com.kaishengit.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author liuyan
 * @date 2018/8/21
 */
public class UserTest {

    @Test
    public void String(){
        // 连接jedis (参数: 1.连接的虚拟机ip地址 2.端口号)
        Jedis jedis = new Jedis("192.168.1.40",6379);
        jedis.set("name","max");
        jedis.set("mini","25");
        jedis.set("tom","99");
        jedis.close();
    }

    @Test
    public void testPool(){
        // 连接池
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(5);
        JedisPool jedisPool = new JedisPool(config,"192.168.1.40",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.close();
        jedisPool.destroy();

    }
}
