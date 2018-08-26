package com.kaishengit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @author liuyan
 * @date 2018/8/25
 */
@Configuration
public class QuartzConfig {

    /*@Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setJobClass(com.kaishengit.jobs.MyQuartzJob.class);
        return jobDetailFactoryBean;
    }*/

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, DataSourceTransactionManager dataSourceTransactionManager){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setTransactionManager(dataSourceTransactionManager);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");

        return schedulerFactoryBean;
    }
}
