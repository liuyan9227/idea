package com.ly.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author liuyan
 * @date 2018/7/9
 */
public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        // 读取主配置文件mybatis
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession(boolean aotuCommit){
        return getSqlSessionFactory().openSession(aotuCommit);
    }

    public static SqlSession getSqlSession(){
        return getSqlSessionFactory().openSession();
    }
}
