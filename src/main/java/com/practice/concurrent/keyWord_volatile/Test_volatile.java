package com.practice.concurrent.keyWord_volatile;

/**
 * @author Higmin
 * @date 2019/8/13 11:22
 *
 * 本示例为 volatile关键字 可以保证可见性和有序性，不能保证原子性
 **/
public class Test_volatile {

    private volatile int num = 0;

    public void increase() {
        num++;
    }

    public static void main(String[] args) throws InterruptedException {
        final Test_volatile test = new Test_volatile();
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
