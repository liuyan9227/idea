package com.myserver.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 */
public class ReentrantLockTest implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                // 必须在最后释放锁, 可能会出现死锁现象
                lock.unlock();
            }
        }
    }

    /**
     * 从上可以看出，使用重入锁进行加锁是一种显式操作，
     * 通过何时加锁与释放锁使重入锁对逻辑控制的灵活性远远大于synchronized关键字。
     * 同时，需要注意，有加锁就必须有释放锁，而且加锁与释放锁的分数要相同，这里就引出了“重”字的概念
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        // ReentrantLockTest是通过实现Runnable接口的, 所以讲对象通过thread类来启动线程
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();

        t1.sleep(100);
        t2.sleep(100); // 和join线程可任选其一

        System.out.println("----");

        /*t1.join();
        t2.join()*/; // main线程会等待t1和t2都运行完再执行以后的流程

        System.err.println(i);
    }
}
