package com.practice.designPatterns.ProducerAndConsumer.waitAndNotify;

import java.util.Queue;

/**
 * @author Higmin
 * @date 2020/3/26 13:38
 *
 *  利用  wait/notify 机制 实现 生产者-消费者模型
 *
 *  wait/notify 是线程通信的一种方式
 *
 **/
public class Producer implements Runnable{
	private final Queue msgQueue;
	private String msg;
	private Integer maxSize;

	public Producer(Queue msgQueue, Integer maxSize) {
		this.msgQueue = msgQueue;
		this.maxSize = maxSize;
	}

	public void produceMsg(String msg){
		this.msg = msg;
		run();
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++){
			synchronized (msgQueue){
				while (msgQueue.size() == maxSize){
					System.out .println("消息队列已满！请等待生产！");
					try {
						msgQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				msg = "模拟消息-" + i;
				msgQueue.offer(msg);
				msgQueue.notifyAll();
//		System.out.println("生产者" + Thread.currentThread().getName() +"消息：" + msg);
			}
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
}
