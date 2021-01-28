package com.practice.juc_lock;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Higmin
 * @date 2019/8/29 7:52
 *
 * 趣味练习：synchronized + 线程的挂起与唤醒，猜猜看输出什么？
 *
 *  ---- 模拟请求争抢访问，只有第一次访问的才能访问成功。
 *
 **/
public class Test09_practice {
    public static void main(String[] args) {
        final Thread main = Thread.currentThread();
        final ConcurrentHashMap<Object, Object> resultHolder = new ConcurrentHashMap<>();
        for (int i = 0; i < 3; i++){
            int finalI = i;
            new Thread(()->{
                try {
                    // 每个线程睡眠随机一段时间
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resultHolder){
                    // putIfAbsent   如果传入key对应的value已经存在，就返回存在的value，不进行替换。如果不存在，就添加key和value，返回null
                    Object value = resultHolder.putIfAbsent("result", Thread.currentThread().getName() + "的结果");
                    System.out.println("我是线程" + finalI + " ,我获得了锁，来往resultHolder中put值，只有第一次put才能成功");
                    if (value == null){
                        LockSupport.unpark(main);
                        System.out.println("我是线程" + finalI + " ，我是第一个来put的，resultHolder里存放我的值，所以由我来唤醒主线程!");
                    }
                }
            },"thread" + i).start();
        }
        LockSupport.park();
        System.out.println("resultHolder的值为：" + resultHolder.get("result"));
    }
}
