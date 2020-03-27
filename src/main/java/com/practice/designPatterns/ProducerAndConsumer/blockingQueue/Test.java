package com.practice.designPatterns.ProducerAndConsumer.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Higmin
 * @date 2020/3/26 9:10
 *
 * 测试运行
 * BlockingQueue是一个阻塞队列，它的存取可以保证只有一个线程在进行，
 * 所以根据逻辑，生产者在内存满的时候进行等待，并且唤醒消费者队列，反过来消费者在饥饿状态下等待并唤醒生产者进行生产。
 **/
public class Test {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Object> msgQueue = new LinkedBlockingDeque<>(); // 这里使用 LinkedBlockingDeque 需要注意的是：LinkedBlockingDeque是线程安全的

		Thread consumer_1 = new Thread(new Consumer(msgQueue), "consumer_1");
		Thread consumer_2 = new Thread(new Consumer(msgQueue), "consumer_2");
		Thread consumer_3 = new Thread(new Consumer(msgQueue), "consumer_3");
		Thread consumer_4 = new Thread(new Consumer(msgQueue), "consumer_4");
		Thread consumer_5 = new Thread(new Consumer(msgQueue), "consumer_5");
		consumer_1.start(); // 监听队列，消费消息
		consumer_2.start(); // 监听队列，消费消息
		consumer_3.start(); // 监听队列，消费消息
		consumer_4.start(); // 监听队列，消费消息
		consumer_5.start(); // 监听队列，消费消息


		Producer producer = new Producer(msgQueue);
		Thread producerThread = new Thread(producer, "Producer");
		producerThread.start();


	}
}
