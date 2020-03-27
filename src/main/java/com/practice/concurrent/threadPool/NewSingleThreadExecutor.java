package com.practice.concurrent.threadPool;

import java.util.concurrent.*;

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
        ExecutorService singleThreadExecutor =  new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        // ExecutorService singleThreadExecutor =  new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
        /**
         * 最好在外面包一层 FinalizableDelegatedExecutorService包装，这一层有什么用呢？
         *  FixedThreadPool可以向下转型为ThreadPoolExecutor，并对其线程池进行配置，而SingleThreadExecutor被包装后，无法成功向下转型。
         *  因此，SingleThreadExecutor被定以后，无法修改，做到了真正的Single。
         */

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
