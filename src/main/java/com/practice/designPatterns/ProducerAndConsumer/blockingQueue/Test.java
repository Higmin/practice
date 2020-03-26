package com.practice.designPatterns.ProducerAndConsumer.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Higmin
 * @date 2020/3/26 9:10
 *
 * 测试运行
 *
 **/
public class Test {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Object> msgQueue = new LinkedBlockingDeque<>();
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


		for (int i = 0; i < 100; i++){
			Producer producer = new Producer(msgQueue);
			producer.produceMsg("消息-" + i);
			Thread producerThread = new Thread();
			producerThread.start();
//			Thread.sleep(2000);
		}

	}
}
