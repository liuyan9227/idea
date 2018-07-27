package com.ly.proxy;

import com.ly.BaseTest;
import com.ly.entity.Sale;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liuyan
 * @date 2018/7/16
 */
public class MessageTest extends BaseTest {

    @Autowired
    private Sale sale;

    @Test
    public void testDell(){
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        // 获得bean节点的详情对像, 用接口指向实现类对象来接收
        // Sale sale = (Sale) applicationContext.getBean("dell");

        // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);


        sale.salePC("max");
        sale.save();
    }
}
