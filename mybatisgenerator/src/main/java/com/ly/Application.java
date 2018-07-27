package com.ly;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author liuyan
 * @date 2018/7/17
 */

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@PropertySource("classpath:jdbc.properties")
public class Application {
//    @Configuration    将此类标记为Spring的java配置类
//    @ComponentScan    开启自动扫描(默认从当前包和其子文件开始扫描)也可以使用(basePackages = "com.ly")来设置指定路径
//    @EnableAspectJAutoProxy   开启基于注解的AOP
//    @PropertySource("classpath:jdbc.properties")  获取properties配置文件的配置

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

}
