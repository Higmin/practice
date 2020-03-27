package com.practice.concurrent.threadPool;

import java.util.concurrent.*;

/**
 * @author Higmin
 * @date 2019/8/9 14:14
 *
 * 无界线程池，可以进行自动线程回收
 *
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 *
 * 这种类型的线程池特点是：
 * 工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。
 * 如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。
 * 终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
 * 在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪
 *
 **/
public class NewCachedThreadPool {

    /**
     * 参数介绍：
     * corePoolSize = 0，maximumPoolSize = Integer.MAX_VALUE，即线程数量几乎无限制；
     * keepAliveTime = 60s，线程空闲60s后自动结束。
     * workQueue 为 SynchronousQueue 同步队列，这个队列类似于一个接力棒，入队出队必须同时传递，因为CachedThreadPool线程创建无限制，不会有队列等待，所以使用SynchronousQueue；
     *
     * 适用场景：快速处理大量耗时较短的任务，如Netty的NIO接受请求时，可使用CachedThreadPool。
     */
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 这里是java 8 的 lambda 表达式
            // cachedThreadPool.execute(() -> {}) 等价于  cachedThreadPool.execute(new Runnable() {})
            // 预期结果 ：一个线程在轮询执行，如果当前线程还未终止就向线程池提交了新的任务，线程池就开启新的线程进行处理
            cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " index : " + index));
            cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " 我是为了在 输出index的线程 还未结束时，就向线程池提交了新的任务，以开启新的线程处理 "));
        }
    }
}
