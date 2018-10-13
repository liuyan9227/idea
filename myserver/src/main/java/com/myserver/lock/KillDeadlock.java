package com.myserver.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 中断响应
 *
 * https://blog.csdn.net/Somhu/article/details/78874634
 */
public class KillDeadlock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public KillDeadlock(int lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();  // 以可以响应中断的方式加锁
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();  // 以可以响应中断的方式加锁
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) lock1.unlock();  // 注意判断方式
            if (lock2.isHeldByCurrentThread()) lock2.unlock();
            System.err.println(Thread.currentThread().getId() + "退出！");
        }
    }

    /**
     * t1、t2线程开始运行时，会分别持有lock1和lock2而请求lock2和lock1，这样就发生了死锁。
     * 但是，在③处给t2线程状态标记为中断后，持有重入锁lock2的线程t2会响应中断，
     * 并不再继续等待lock1，同时释放了其原本持有的lock2，这样t1获取到了lock2，正常执行完成。
     * t2也会退出，但只是释放了资源并没有完成工作。
     */
    public static void main(String[] args) throws InterruptedException {
        KillDeadlock deadLock1 = new KillDeadlock(1);
        KillDeadlock deadLock2 = new KillDeadlock(2);
        Thread t1 = new Thread(deadLock1);
        Thread t2 = new Thread(deadLock2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt(); // ③
    }
}
