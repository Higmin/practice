package com.practice.algorithm.limiting;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 漏桶算法
 *
 * 漏桶算法思路很简单，请求先进入到漏桶里，漏桶以固定的速度出水，也就是处理请求，
 * 当水加的过快，则会直接溢出，也就是拒绝请求，可以看出漏桶算法能强行限制数据的传输速率。
 *
 * @author Jimmy
 * @version 1.0, 2021/03/17
 * @since practice 1.0.0
 */
public class LeakyBucket {

    // 1.桶的容量。
    private final int capacity = 10;
    // 2.漏水速度。
    private final int rate = 4;
    // 3.当前的水量：用于溢出时的拒绝策略。
    private volatile long water = 0L;
    // 4.单位时间开始时间。
    private long begin = System.currentTimeMillis();

    public boolean acquire() {
        long now = System.currentTimeMillis();

        water = Math.max(0, water - (now - begin) / 1000 * rate);
        if (water >= capacity) {
            System.out.println("流量超限，执行拒绝策略~" + Thread.currentThread().getName());
            return false;
        }
        water++;
        System.out.println(Thread.currentThread().getName() + " 当前流量数为" + water);
        begin = now;
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        LeakyBucket leakyBucket = new LeakyBucket();
        while (true) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    leakyBucket.acquire();
                }
            });
            Thread.sleep(10 * random.nextInt(100));
        }
    }
}