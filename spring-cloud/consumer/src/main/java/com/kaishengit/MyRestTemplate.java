package com.kaishengit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuyan
 * @date 2018/9/3
 */
@Configuration
public class MyRestTemplate {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
