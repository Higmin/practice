package com.exercise.lock;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/13 11:19
 * @Description : 死锁
 * 预期结果：产生死锁，互相等待
 */
public class Test01_DeadLock {

    private static Object lockA = new Object();

    private static Object lockB = new Object();

    public static void main(String[] args) {
        new Test01_DeadLock().deadLock();
    }

    private void deadLock() {

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                synchronized (lockA){
                    try {
                        System.out.println(Thread.currentThread().getName() + " 获取A锁 ing！");
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName() + " 睡眠500ms");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 需要B锁！！！");
                    synchronized (lockB){
                        System.out.println(Thread.currentThread().getName() + " B锁获取成功");
                    }
                }
            }
        },"Thread1");

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                synchronized (lockB){
                    try {
                        System.out.println(Thread.currentThread().getName() + "获取B锁 ing！");
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName() + "睡眠500ms");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "需要A锁！！！");
                    synchronized (lockA){
                        System.out.println(Thread.currentThread().getName() + "A锁获取成功");
                    }
                }
            }
        },"Thread2");

        thread1.start();
        thread2.start();

    }

}
