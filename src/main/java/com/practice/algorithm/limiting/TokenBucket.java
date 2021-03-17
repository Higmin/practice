package com.practice.algorithm.limiting;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 令牌桶算法
 * 令牌桶算法的原理是系统会以一个恒定的速度往桶里放入令牌，而如果请求需要被处理，则需要先从桶里获取一个令牌，当桶里没有令牌可取时，则拒绝服务。
 *
 * @author Jimmy
 * @version 1.0, 2021/03/17
 * @since practice 1.0.0
 */
public class TokenBucket {

    // 1.桶的容量。
    private final int capacity = 10;
    // 2.生成令牌的速度。
    private final int rate = 4;
    // 3.当前令牌数量：用于令牌不够时的拒绝策略。
    private volatile long tokens = 0L;
    // 4.单位时间开始时间。
    private long begin = System.currentTimeMillis();

    public boolean acquire() {
        long now = System.currentTimeMillis();
        tokens = Math.min(capacity, tokens + (now - begin) / 1000 * rate);

        if (tokens < 1) {
            return false;
        }
        tokens--;
        begin = now;
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        TokenBucket tokenBucket = new TokenBucket();
        while (true) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    if (tokenBucket.acquire()) {
                        System.out.println(Thread.currentThread().getName() + " 当前流量数为" + tokenBucket.tokens);
                    } else {
                        System.out.println("流量超限，执行拒绝策略~" + Thread.currentThread().getName());
                    }
                }
            });
            Thread.sleep(random.nextInt(1000));
        }
    }
}
