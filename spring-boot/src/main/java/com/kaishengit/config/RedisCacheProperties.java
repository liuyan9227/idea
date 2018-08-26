package com.kaishengit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author liuyan
 * @date 2018/8/25
 */
@Component
@ConfigurationProperties(prefix = "car")
public class RedisCacheProperties {

    private Map<String, Long> expires;

    public Map<String, Long> getExpires() {
        return expires;
    }

    public void setExpires(Map<String, Long> expires) {
        this.expires = expires;
    }
}
