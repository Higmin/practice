package com.practice.lock;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/13 11:21
 * @Description : synchronized避免死锁,固定秩序获取锁
 * 预期结果：解决Test01中的死锁问题
 */
public class Test02_AvoidDeadLock {
    private static Object lockA = new Object();

    private static Object lockB = new Object();

    public static void main(String[] args) {
        new Test02_AvoidDeadLock().deadLock();
    }

    private void deadLock() {

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                getLock();
            }
        },"Thread1");

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                getLock();
            }
        },"Thread2");

        thread1.start();
        thread2.start();

    }

    public void getLock() {
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

}
