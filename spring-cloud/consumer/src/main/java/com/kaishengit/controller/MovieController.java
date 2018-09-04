package com.kaishengit.controller;

import com.kaishengit.MyRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuyan
 * @date 2018/9/3
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * restTemplate方式
     */
    @GetMapping("/movie/{id:\\d+}")
    public String movie(@PathVariable Integer id){
        // 访问服务的Http请求的路径
        String url = "http://127.0.0.1:8001/movie/" + id;
        String moviename = restTemplate.getForObject(url, String.class);
        return moviename;
    }
}
