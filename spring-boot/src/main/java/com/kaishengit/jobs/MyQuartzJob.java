package com.kaishengit.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author liuyan
 * @date 2018/8/25
 */
@Component
public class MyQuartzJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("quartz -> " + System.currentTimeMillis());
    }
}
