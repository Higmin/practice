package com.practice.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Higmin
 * @date 2019/8/21 8:13
 *
 * 利用ReentrantLock 和 Condition 等待/唤醒机制，实现以下功能：
 *
 * 条件 1 ：线程一 打印 0-100 的奇数
 * 条件 2 ：线程二 打印 0-100 的偶数
 * 条件 3 ：两个线程交替执行
 **/
public class Test08_Constans {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static int count = 0;

    public static void main(String[] args) {
        final Thread main = Thread.currentThread();
        //输出偶数的线程
        new Thread(()->{
            for (;;){
                if (count == 100){
                    break;
                }
                lock.lock();
                try {
                    if (count % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + " 输出 " + count++);
                        condition.signal();
                    }else condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            LockSupport.unpark(main);
        },"偶数线程").start();

        //输出奇数的线程
        new Thread(()->{
            for (;;){
                if (count == 100){
                    break;
                }
                lock.lock();
                try {
                    if (count % 2 != 0){
                        System.out.println(Thread.currentThread().getName() + " 输出 " + count++);
                        condition.signal();
                    }else condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            LockSupport.unpark(main);
        },"奇数线程").start();
        LockSupport.park();
        System.out.println("结束");
    }
}

