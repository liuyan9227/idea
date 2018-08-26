package com.kaishengit.jobs;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author liuyan
 * @date 2018/8/25
 */
@Component
public class MyScheduled {

    // @Scheduled(cron = "0/2 * * * * ?")
    @Async
    public void cronJob(){
        System.out.println("hello,job -> " + System.currentTimeMillis());
    }

}
