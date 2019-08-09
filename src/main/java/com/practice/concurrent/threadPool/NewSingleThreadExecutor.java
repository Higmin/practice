package com.practice.concurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Higmin
 * @date 2019/8/9 14:11
 *
 * 单个后台线程
 *
 * 创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * 如果这个线程异常结束，会有另一个取代它，保证顺序执行。单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。
 *
 **/
public class NewSingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            // 这里是java 8 的 lambda 表达式
            // singleThreadExecutor.execute(() -> {}) 等价于  singleThreadExecutor.execute(new Runnable() {})
            singleThreadExecutor.execute(() -> {
                try {
                    // 预期结果 ： 10个线程按顺序依此执行
                    System.out.println(Thread.currentThread().getName() + " index : " + index);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
