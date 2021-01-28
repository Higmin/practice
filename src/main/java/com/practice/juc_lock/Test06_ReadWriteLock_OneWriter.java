package com.practice.juc_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/13 16:04
 * @Description : ReentrantReadWriteLock 读-写同步锁, 写（writeLock）操作的时候，只允许一个写者，获得锁writeLock之后才能进行写操作。
 * 预期结果：Thread-0 先进行 写操作
 *          Thread-0 执行完毕 ===> Thread-1 进行 写 操作
 */
public class Test06_ReadWriteLock_OneWriter {
    private ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final Test06_ReadWriteLock_OneWriter test = new Test06_ReadWriteLock_OneWriter();

        new Thread(){
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();

    }

    public void get(Thread thread) {
        try {
            rw1.writeLock().lock();
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1){
                System.out.println(thread.getName() + " 正在写操作");
            }
            System.out.println(thread.getName() + " 写操作完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rw1.writeLock().unlock();
        }

    }

}
