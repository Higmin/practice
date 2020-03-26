package com.practice.designPatterns.ProducerAndConsumer.waitAndNotify;

import java.util.LinkedList;

/**
 * @author Higmin
 * @date 2020/3/26 13:39
 *
 * 测试运行
 *
 **/
public class Test {
	public static void main(String[] args) {
		LinkedList<Object> msgQueue = new LinkedList<>();

		Consumer consumer = new Consumer(msgQueue);
		Thread consumerThread = new Thread(consumer, "consumer");
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
		Thread producerThread = new Thread(producer, "producer");
		producerThread.start();
	}
}
