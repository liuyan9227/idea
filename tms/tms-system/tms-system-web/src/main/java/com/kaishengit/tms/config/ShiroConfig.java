package com.kaishengit.tms.config;

import com.kaishengit.tms.shiro.CustomerFilterChainDefinition;
import com.kaishengit.tms.shiro.MyRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author liuyan
 * @date 2018/8/31
 */
@Configuration
public class ShiroConfig {

    /**
     * 将自定义MyRealm类放入spring容器
     */
    @Bean
    public Realm realm(){
        return new MyRealm();
    }

    /**
     * 将自定义权限filter类放入spring容器
     * 容器初始化时调用
     */
    @Bean
    public CustomerFilterChainDefinition customerFilterChainDefinition(ShiroFilterFactoryBean shiroFilterFactoryBean) throws Exception {
        // 1.创建自定义的shiro过滤器对象
        CustomerFilterChainDefinition customerFilterChainDefinition = new CustomerFilterChainDefinition();
        // 2.将properties中的规则设置在shiro规则中
        customerFilterChainDefinition.setShiroFilter((AbstractShiroFilter) shiroFilterFactoryBean.getObject());
        // 使用linkedHashMap(有序map,因为/**,user这个规则需要在最后拦截所以要有序map),将静态资源写死在map中
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("/favicon.ico","anon");
        map.put("/bootstrap/**","anon");
        map.put("/dist/**","anon");
        map.put("/plugins/**","anon");
        map.put("/js/**","anon");
        map.put("/logout","logout");
        // 3.将有序map设置在对象中,对象以具有静态资源的放行规则
        customerFilterChainDefinition.setFilterChainDefinitions(map);
        // 4.将对象放入spring容器中
        return customerFilterChainDefinition;
    }



}
