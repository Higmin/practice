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
		Thread consumerThread = new Thread(new Consumer(msgQueue));
		consumerThread.start(); // 监听队列，消费消息
		for (int i = 0; i < 10; i++){
			Producer producer = new Producer(msgQueue);
			producer.produceMsg("消息-" + i);
			Thread producerThread = new Thread();
			producerThread.start();
			Thread.sleep(2000);
		}

	}
}
