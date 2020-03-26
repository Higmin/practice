package com.practice.designPatterns.ProducerAndConsumer.waitAndNotify;


import java.util.Queue;

/**
 * @author Higmin
 * @date 2020/3/26 13:38
 *
 *  利用  wait/notify 机制 实现 生产者-消费者模型
 *
 *  wait/notify 是线程通信的一种方式
 **/
public class Producer implements Runnable{
	private final Queue msgQueue;
	private String msg;

	public Producer(Queue msgQueue) {
		this.msgQueue = msgQueue;
	}

	public void produceMsg(String msg){
		this.msg = msg;
		run();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++){
			msg = "模拟消息-" + i;
			msgQueue.offer(msg);
//		System.out.println("生产者" + Thread.currentThread().getName() +"消息：" + msg);
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
