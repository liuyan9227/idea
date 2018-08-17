package com.kaishengit.task;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;

/**
 * @author liuyan
 * @date 2018/8/16
 */
public class MyTimeTaskTest {

    @Test
    public void testRun() throws IOException {

        Timer timer = new Timer();
        // 获取任务的对象,以0秒开始,间隔5秒执行
        // timer.schedule(new MyTimeTask(), 0, 5000);

        // timer.schedule(new MyTimeTask(), new Date(System.currentTimeMillis() + 3000));

        // timer.schedule(new MyTimeTask(), new Date(System.currentTimeMillis() + 3000), 1000);

        // fixRate是相对于上一次的任务的开始时间计时时间间隔，如果第二次任务开始执行时第一次任务还未完成，等待第一次任务完成之后在执行第二次任务
        timer.scheduleAtFixedRate(new MyTimeTask(), new Date(System.currentTimeMillis() + 2000L), 1500);

        // 循环读取,必须加不然不会执行sout
        System.in.read();
    }

}
