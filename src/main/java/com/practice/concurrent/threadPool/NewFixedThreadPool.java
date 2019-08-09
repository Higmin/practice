package com.practice.concurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Higmin
 * @date 2019/8/9 14:13
 *
 * 固定大小线程池
 *
 * 创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。
 *
 * FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。
 * 但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
 *
 **/
public class NewFixedThreadPool {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            // 这里是java 8 的 lambda 表达式
            // fixedThreadPool.execute(() -> {}) 等价于  fixedThreadPool.execute(new Runnable() {})
            fixedThreadPool.execute(() -> {
                try {
                    // 预期结果 ： 只有三个线程在轮询
                    System.out.println(Thread.currentThread().getName() + " index : " + index);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
