package com.kaishengit;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author liuyan
 * @date 2018/7/19
 */


@Configuration
@ComponentScan
// 读取配置文件
@PropertySource("classpath:config.properties")
// 开启基于注解的事物
@EnableTransactionManagement
// 自动扫描mapper接口并自动创建实现类对象加入spring容器
@MapperScan(basePackages = "com.kaishengit.mapper")

public class Application {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("jdbc.driver");
        basicDataSource.setUrl("jdbc.url");
        basicDataSource.setUsername("jdbc.username");
        basicDataSource.setPassword("jdbc.password");
        return basicDataSource;
    }

    // 配置jdbc事物管理器
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager (DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    // 配置SqlSessionFactoryBean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据库连接池
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 配置别名所在包,别名默认为类名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.kaishengit.entity");
        // 配置mapper/*.xml所在的位置
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath:mapper/*xml");
        sqlSessionFactoryBean.setMapperLocations(resources);

        // mybatis的其他配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean;
    }
}
