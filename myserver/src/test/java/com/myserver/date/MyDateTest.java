package com.myserver.date;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 使用时间戳
 */
public class MyDateTest {

    @Test
    public void nowDate(){
        // 获得当前时间戳
        System.out.println(new Date());
    }

    @Test
    public void yesterdayDate(){
        // 获得昨天的当前时间戳
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        System.out.println(calendar.getTime()); // Sat Oct 06 20:52:47 CST 2018
        // java8中,另一种实现的功能
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(today); // 2018-10-07T20:52:47.558
        System.out.println(yesterday); // 2018-10-06T20:52:47.558
    }

}
