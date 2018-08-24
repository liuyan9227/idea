package com.kaishengit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuyan
 * @date 2018/8/23
 */
// 把实体类放入到spring容器中
@Component
// 使用注解说明这是一个properties类,参数传入与配置文件相同的前缀,需要在application文件中配置相应的值
@ConfigurationProperties(prefix = "redisson")
public class RedissonConfigProperties {

    private String host;
    private Integer timeout;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
