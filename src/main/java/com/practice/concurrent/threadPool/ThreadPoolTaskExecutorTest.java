package com.practice.concurrent.threadPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Higmin
 * @date 2019/8/9 15:46
 *----------------------------------------- 1. ThreadPoolTaskExecutor介绍-----------------------------------------------
 *
 * ThreadPoolTaskExecutor是一个spring的线程池技术，其实，它的实现方式完全是使用ThreadPoolExecutor进行实现
 * （有点类似于装饰者模式。当然Spring提供的功能更加强大些，因为还有定时调度功能）。
 *
 * --------------------------------------------- 2. 默认配置參數如下-----------------------------------------------------
 *
 * corePoolSize=1
 * queueCapacity=Integer.MAX_VALUE
 * maxPoolSize=Integer.MAX_VALUE
 * keepAliveTime=60s
 * allowCoreThreadTimeout=false
 * rejectedExecutionHandler=AbortPolicy()
 *
 *--------------------------------------------- 3. 自己配置参数前需要了解的几个概念----------------------------------------
 *
 * 设置参数需要根据以下几个值来决定
 * tasks ：每秒的任务数，假设为500~1000
 * taskcost：每个任务花费时间，假设为0.1s
 * responsetime：系统允许容忍的最大响应时间，假设为1s
 * 来做几个计算 哈哈 ：corePoolSize = 每秒需要多少个线程处理？
 *
 *------------------------------------------- 4. 这里是具体的大小计算-----------------------------------------------------
 *
 * threadcount = tasks/(1/taskcost) =tasks*taskcout = (500~1000)*0.1 = 50~100 个线程。corePoolSize设置应该大于50
 * 根据8020原则，如果80%的每秒任务数小于800，那么corePoolSize设置为80即可
 * queueCapacity = (coreSizePool/taskcost)*responsetime
 * 计算可得 queueCapacity = 80/1 = 80。意思是队列里的线程可以等待1s，超过了的需要新开线程来执行
 * 切记不能设置为Integer.MAX_VALUE，这样队列会很大，线程数只会保持在corePoolSize大小，当任务陡增时，不能新开线程来执行，响应时间会随之陡增。
 * maxPoolSize = (max(tasks)- queueCapacity)/(1/taskcost)
 * 计算可得 maxPoolSize = (1000-80)/10 = 92
 * （最大任务数-队列容量）/每个线程每秒处理能力 = 最大线程数
 *
 * 还有一条：rejectedExecutionHandler：根据具体情况来决定，任务不重要可丢弃，任务重要则要利用一些缓冲机制来处理
 *
 *------------------------------------------- 5. 知道怎么算之后，再浇一盆冷水----------------------------------------------
 *
 * 以上都是理想值，实际情况下要根据机器性能来决定。如果在未达到最大线程数的情况机器cpu load已经满了，则需要通过升级硬件（呵呵）和优化代码，降低taskcost来处理。
 *
 * 如何合理配置线程池大小，一般需要根据任务的类型来配置线程池大小：
 * 　　1、如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1（比如是4核心 就配置为5）
 * 　　2、如果是IO密集型任务，参考值可以设置为2*NCPU
 * 　　当然，这只是一个参考值，具体的设置还需要根据实际情况进行调整，比如可以先将线程池大小设置为参考值，再观察任务运行情况和系统负载、资源利用率来进行适当调整。
 *
 *-------------------------------------------------- 6. 使用场景 --------------------------------------------------------
 *
 * 1、当你的任务是非必要的时候。比如记录操作日志、通知第三方服务非必要信息等，可以使用线程池处理非阻塞任务
 * 2、当你的任务非常耗时时候，可以采用线程池技术
 * 3、当请求并发很高时，可以采用线程池技术优化处理
 *
 * 可以通过Executors静态工厂构建线程池，但一般不建议这样使用。
 *
 * 提醒：能够用线程池的时候，不要自己的去new线程start，在高并发环境下，系统资源是宝贵的，需要节约资源才能提高可用性。
 *
 **/
public class ThreadPoolTaskExecutorTest {

    // todo 温馨提示：这里注入需要依赖于 Spring 容器，所以此测试类不能直接运行，只是将写法列在这里以供参考,具体用法这里也列了一种，感兴趣大家可以再研究

    @Autowired
    private static ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public static void main(String[] args) {

        // // 这里是java 8 的 lambda 表达式
        // threadPoolTaskExecutor.execute(() -> {}) 等价于 threadPoolTaskExecutor.execute(new Runnable() {})
        threadPoolTaskExecutor.execute(() -> {
            System.out.println("在这里进行任务的执行");
            // 模拟任务执行需要 2s
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行完毕！");
        });
    }
}
