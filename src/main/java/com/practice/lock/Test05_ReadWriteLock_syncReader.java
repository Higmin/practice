package com.practice.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/13 15:51
 * @Description : ReentrantReadWriteLock 读-写同步锁, 读（readLock）操作的时候，线程间是不用排队来读操作的。这样效率明显很高。
 * 预期结果：Thread-0 和  Thread-1 可以 同时进行 读 操作
 */
public class Test05_ReadWriteLock_syncReader {
    private ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final Test05_ReadWriteLock_syncReader test = new Test05_ReadWriteLock_syncReader();

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
            rw1.readLock().lock();
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1){
                System.out.println(thread.getName() + " 正在读操作");
            }
            System.out.println(thread.getName() + " 读操作完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rw1.readLock().unlock();
        }

    }

}
