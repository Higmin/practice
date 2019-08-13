package com.practice.concurrent.keyWord_volatile;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Higmin
 * @date 2019/8/13 13:50
 *
 * 本例为：使用Lock 加锁,将 num++ 操作变为原子性操作
 **/
public class Test_volatile_solvedByLock {
    private int num = 0;

    Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        try {
            num++;
        } finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Test_volatile_solvedByLock test = new Test_volatile_solvedByLock();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++){
                        test.increase();
                    }
                };
            }.start();
        }
        // 睡眠 3s 保证 10个线程的自增操作全部执行完毕
        Thread.sleep(3000);
        System.out.println("num 的值为: " + test.num);
    }
}
