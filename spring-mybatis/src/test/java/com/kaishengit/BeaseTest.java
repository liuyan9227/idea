package com.kaishengit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author liuyan
 * @date 2018/7/19
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring.xml")
@ContextConfiguration(classes = Application.class)
public class BeaseTest {
//    @RunWith(SpringJUnit4ClassRunner.class)   // 容器启动时,spring也跟随启动
//    @ContextConfiguration(locations = "classpath:spring.xml")     // 从xml中获取
//    @ContextConfiguration(classes = Application.class)  // 从指定class中获取
}
