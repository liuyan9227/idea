package com.ly;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liuyan
 * @date 2018/7/17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
//@ContextConfiguration(locations = "classpath:jdbc.xml")

public class BaseTest {

//    @RunWith(SpringJUnit4ClassRunner.class)   junit启动时一起启动Spring容器
//    @ContextConfiguration(classes = Application.class)    从指定java类的详情对像中获得
//    @ContextConfiguration(locations = "classpath:jdbc.xml")   从指定的xml文件中获得


}
