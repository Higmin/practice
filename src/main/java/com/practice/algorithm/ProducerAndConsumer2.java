package com.practice.algorithm;

/**
 * 生产者消费者手写 -- 使用阻塞队列实现：阻塞队列有 put/take 方法。
 *
 * @author Jimmy
 * @version 1.0, 2021/03/22
 * @since practice 1.0.0
 */

import org.apache.commons.collections4.CollectionUtils;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者消费者手写 -- 使用阻塞队列实现：阻塞队列有 put/take 方法。
 * <p>
 * add/offer/put 对应的是 remove,poll,take
 * 分别是：add/offer/put分别为：队列满抛异常/队列满返回false/队列满等待空间释放（加锁）
 *
 * @author Jimmy
 * @version 1.0, 2021/02/04
 * @since practice 1.0.0
 */
public class ProducerAndConsumer2 {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        Producer producer = new Producer(queue, 10);
        Consumer consumer = new Consumer(queue);
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            producer.sendMsg("this is a msg " + i);
            Thread.sleep(random.nextInt(1000));
        }
    }

    static class Producer {

        private BlockingQueue<String> queue;
        private int size;

        public Producer(BlockingQueue<String> queue, int size) {
            this.queue = queue;
            this.size = size;
        }

        public Producer() {
        }

        public void sendMsg(String msg) throws InterruptedException {

            if (queue.size() == size) {
                System.out.println("队列已满，等待消费...");
            }
            queue.put(msg);
            System.out.println("生产消息 " + msg);
        }

        public Queue<String> getQueue() {
            return queue;
        }

        public void setQueue(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    static class Consumer implements Runnable {

        private BlockingQueue<String> queue;

        public void getMsg() throws InterruptedException {
            try {
                while (true) {
                    if (CollectionUtils.isEmpty(queue)) {
                        System.out.println("队列为空，等待消息生产...");
                    }
                    String msg = queue.take();
                    System.out.println(Thread.currentThread().getName() + " ==> 消费消息 " + msg);
                    Random random = new Random();
                    Thread.sleep(random.nextInt(500));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        public Consumer() {
        }

        @Override
        public void run() {
            try {
                getMsg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
