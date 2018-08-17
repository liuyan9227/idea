package com.kaishengit.task;

import java.util.TimerTask;

/**
 * @author liuyan
 * @date 2018/8/16
 */
public class MyTimeTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("task:任务");
    }
}
