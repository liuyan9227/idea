package com.kaishengit.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.kaishengit.realm.ShiroRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author liuyan
 * @date 2018/8/25
 */
@Configuration
@ControllerAdvice   // 异常处理类
public class ShiroConfig {

    /**
     * 依赖realm类的信息认证和授权(核心文件)
     */
    @Bean
    public Realm realm(){
        return new ShiroRealm();
    }

    /**
     * 配置指定的权限的映射关系
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        // 默认shiro过滤器链的定义
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // (1.请求路径, 2.指定规则) ... /favicon.ico : 图标
        definition.addPathDefinition("/favicon.ico", "anon");
        definition.addPathDefinition("/static/**", "anon");
        definition.addPathDefinition("/logout", "logout");
        definition.addPathDefinition("/**", "user");
        return definition;
    }

    /**
     * 用于与html页面整合(主要文件)
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
