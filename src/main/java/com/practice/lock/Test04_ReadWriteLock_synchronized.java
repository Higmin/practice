package com.practice.lock;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/13 15:40
 * @Description : synchronized 同步锁，读写时，Thread0读操作完了，Thread1才能进行读操作。明显这样性能很慢。
 * 预期结果：Thread-0 进行 读 操作
 *          等待 Thread-0 执行完毕之后   Thread-1 执行 读 操作
 */
public class Test04_ReadWriteLock_synchronized {

    public static void main(String[] args) {
        final Test04_ReadWriteLock_synchronized test = new Test04_ReadWriteLock_synchronized();

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

    public synchronized void get(Thread thread) {

        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1){
            System.out.println(thread.getName() + " 正在读操作");
        }
        System.out.println(thread.getName() + " 读操作完成");

    }

}
