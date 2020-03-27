package com.practice.concurrent.threadPool;

import java.util.concurrent.*;

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

    /**
     * 参数介绍：
     * corePoolSize与maximumPoolSize相等，即其线程全为核心线程，是一个固定大小的线程池，是其优势；
     * keepAliveTime = 0 该参数默认对核心线程无效，而FixedThreadPool全部为核心线程；
     * workQueue 为LinkedBlockingQueue（无界阻塞队列），队列最大值为Integer.MAX_VALUE。如果任务提交速度持续大余任务处理速度，会造成队列大量阻塞。因为队列很大，很有可能在拒绝策略前，内存溢出。是其劣势；
     * FixedThreadPool的任务执行是无序的.
     *
     * 适用场景：可用于Web服务瞬时削峰，但需注意长时间持续高峰情况造成的队列阻塞。
     */
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
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
