package com.practice.designPatterns.ProducerAndConsumer.blockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author Higmin
 * @date 2020/3/26 8:50
 *
 * 利用阻塞队列实现 生产者-消费者模型
 *
 *  阻塞队列 API中，添加元素的方法竟然有三个：add,put,offer。
 *  add方法在添加元素的时候，若超出了度列的长度会直接抛出异常
 *  put方法，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待空间，以加入元素。
 *  offer方法在添加元素时，如果发现队列已满无法添加的话，会直接返回false。
 *
 **/
public class Producer implements Runnable {

	private final BlockingQueue msgQueue;
	private String msg;

	public Producer(BlockingQueue msgQueue) {
		this.msgQueue = msgQueue;
	}

	public void produceMsg(String msg){
		this.msg = msg;
		run();
	}

	@Override
	public void run() {
		try {
			msgQueue.put(msg);
			System.out.println("生产者生产消息：" + msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
