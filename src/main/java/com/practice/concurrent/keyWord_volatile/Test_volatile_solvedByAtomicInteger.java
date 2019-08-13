package com.practice.concurrent.keyWord_volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Higmin
 * @date 2019/8/13 13:54
 *
 * 本例为：采用JUC中的原子数据包,将 num++ 操作变为原子性操作
 **/
public class Test_volatile_solvedByAtomicInteger {
    private AtomicInteger num = new AtomicInteger();

    public void increase() {
        num.getAndIncrement(); // 相当于num++
    }

    public static void main(String[] args) throws InterruptedException {
        final Test_volatile_solvedByAtomicInteger test = new Test_volatile_solvedByAtomicInteger();
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
