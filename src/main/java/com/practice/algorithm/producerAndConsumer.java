package com.practice.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 生产者消费者手写 -- 使用wait/notify实现。
 *
 * @author Jimmy
 * @version 1.0, 2021/02/04
 * @since practice 1.0.0
 */
public class producerAndConsumer {

    public static void main(String[] args) throws InterruptedException {
        LinkedList<String> queue = new LinkedList<>();
        Producer producer = new Producer(queue, 2);
        Consumer consumer = new Consumer(queue);

        Random random = new Random();

        new Thread(consumer, "consumer1").start();
        new Thread(consumer, "consumer2").start();
        new Thread(consumer, "consumer3").start();

        for (int i = 0; i < 10; i++) {
            producer.produce("sss" + i);
            Thread.sleep(random.nextInt(1000));
        }
    }

    static class Producer implements Runnable {

        private final Queue<String> queue;
        private String msg;
        private Integer size;

        public Producer(Queue<String> queue, Integer size) {
            this.queue = queue;
            this.size = size;
        }

        public void produce(String msg) {
            this.msg = msg;
            run();
        }

        @Override
        public void run() {
            synchronized (queue) {
                if (queue.size() == this.size) {
                    try {
                        System.out.println("队列已满，等待消费...");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.offer(msg);
                System.out.println("生产消息： " + msg);
                queue.notifyAll();
            }
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    static class Consumer implements Runnable {

        private final Queue<String> queue;
        private String msg;

        public Consumer(Queue<String> queue) {
            this.queue = queue;
        }

        public void consume() {
            run();
        }

        @Override
        public void run() {
            synchronized (queue) {
                while (true) {
                    if (queue.size() == 0) {
                        try {
                            System.out.println("队列为空，等待消息生产...");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Object message = queue.poll();
                    if (message != null) {
                        this.msg = message.toString();
                        System.out.println(Thread.currentThread().getName() + " 消费消息： " + this.msg);
                        Random random = new Random();
                        try {
                            Thread.sleep(random.nextInt(500));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        queue.notifyAll();
                    }
                }
            }
        }
    }
}