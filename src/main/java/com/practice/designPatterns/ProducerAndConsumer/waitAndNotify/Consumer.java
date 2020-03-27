package com.practice.designPatterns.ProducerAndConsumer.waitAndNotify;

import java.util.Queue;

/**
 * @author Higmin
 * @date 2020/3/26 13:39
 *
 *  利用  wait/notify 机制 实现 生产者-消费者模型
 *
 *  wait/notify 是线程通信的一种方式
 *
 **/
public class Consumer implements Runnable{
	private final Queue msgQueue;
	private String msg;

	public Consumer(Queue msgQueue) {
		this.msgQueue = msgQueue;
	}

	public void soncumerMsg(){
		run();
	}

	@Override
	public void run() {
		while(true){
			synchronized (msgQueue){
				while(msgQueue.isEmpty()){
//				System.out.println("消息为空！" );
					try {
						msgQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Object msg = msgQueue.poll();
				System.out.println("消费者" + Thread.currentThread().getName() +"消息：" + msg.toString());
				msgQueue.notifyAll();
			}
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
