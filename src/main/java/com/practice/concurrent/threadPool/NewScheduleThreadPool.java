package com.practice.concurrent.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Higmin
 * @date 2019/8/9 14:13
 *
 * 定时、延时线程池
 *
 * 创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。
 *
 *  为了便于理解 ，测试 1 、2 、3  建议分开执行，结合最后的说明，相信会很容易理解
 *
 **/
public class NewScheduleThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        // 这里是java 8 的 lambda 表达式
        // scheduledThreadPool.execute(() -> {}, delay, unit) 等价于  scheduledThreadPool.schedule(new Runnable() {}, delay, unit)
        // 测试 1 ：预期结果 ：任务延时 3 秒执行
        scheduledThreadPool.schedule(() -> System.out.println(Thread.currentThread().getName() + "延迟 2 秒 后执行"), 2, TimeUnit.SECONDS);

        // 这里是java 8 的 lambda 表达式
        // scheduledThreadPool.execute(() -> {}, initialDelay, period,unit) 等价于  scheduledThreadPool.schedule(new Runnable() {}, initialDelay, period,unit)
        // 测试 2 ：预期结果 ：任务延时 6 秒执行,并且每 2 秒执行一次。 最多有五条线程在轮询执行，因为创建线程池大小为 5
        scheduledThreadPool.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread().getName() + "延迟 6 秒后启动，并且每 2 秒执行一次");
                    try {
                        // 模拟执行过程需要 1s 的时间
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                0,
                2,
                TimeUnit.SECONDS);

        // scheduledThreadPool.scheduleWithFixedDelay(() -> {},initialDelay, delay, unit) 等价于  scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {}, initialDelay, delay, unit)
        // 测试 3 ：预期结果 ：任务延时 6 秒执行,并且后面每次执行都是在执行完成后再延迟 2 秒执行一次。 最多有五条线程在轮询执行，因为创建线程池大小为 5
        scheduledThreadPool.scheduleWithFixedDelay(()->{
            System.out.println(Thread.currentThread().getName() + "启动后第一次延迟 6 秒执行，后面每次延时 2 秒执行");
                    try {
                        // 模拟执行过程需要 1s 的时间
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                6,
                2,
                TimeUnit.SECONDS);


        // todo 这里的 scheduleAtFixedRate() 和 scheduleWithFixedDelay() 都是以固定的频率去执行，看结果相似，但是源码中的区别是 （举例：第一次执行是 5s 后，假如执行耗时 1s，执行频率是 2s）：
        // todo scheduleAtFixedRate():  第三个参数为 period ,指的是两次成功执行之间的时间  (第一次执行是 5s 后，第二次执行是 7s 后，第三次执行是 9s 后)
        // todo scheduleWithFixedDelay(): 第三个参数为 delay ,指的是一次执行终止 和 下一次执行开始的延迟时间 (第一次执行是 5s 后，执行完是 6s 后，第二次执行是 8s 后，执行完是 9s 后，第三次执行是 11s 后)
    }
}
