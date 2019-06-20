package com.exercise.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/13 15:15
 * @Description : 显式锁Lock,固定秩序获取锁
 * 预期结果：使用 Lock 显式锁 ，也可以避免死锁问题。
 * 需要注意的是：lock显示锁 需要手动释放锁 。通常Lock显式锁 结合 try - catch - finally 来使用，在finally里释放锁
 */
public class Test03_ReentrantLock {
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Test03_ReentrantLock().deadLock();
    }

    private void deadLock() {

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 获取A锁 ing！");
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " 睡眠500ms");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " 需要B锁！！！");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " B锁获取成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "Thread1");

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 获取B锁 ing！");
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " 睡眠500ms");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " 需要A锁！！！");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " A锁获取成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "Thread2");

        thread1.start();
        thread2.start();
    }

}
