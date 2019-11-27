package com.practice.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * @author Higmin
 * @date 2019/11/26 8:25
 *  kafka 生产者
 **/
public class KafkaProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	private Properties props;
	private org.apache.kafka.clients.producer.KafkaProducer<String, String> producer;
	public final static String TOPIC = "TEST-TOPIC";

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		props = new Properties();
		/**
		 * 用于自举（bootstrapping ），producer只是用它来获得元数据（topic, partition, replicas）
		 * 实际用户发送消息的socket会根据返回的元数据来确定
		 */
		props.put("bootstrap.servers", "192.168.183.150:9092,192.168.183.151:9092,192.168.183.152:9092"); // kafka 地址
		props.put("transactional.id", "my-transactional-id"); // 事务id
		// producer发送消息后是否等待broker的ACK，默认是0 。（ 1 表示等待ACK，保证消息的可靠性）
		props.put("request.required.acks", "1");
		// 泛型参数分别表示 The first is the type of the Partition key, the second the type of the message
		producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props, new StringSerializer(), new StringSerializer());
		producer.initTransactions(); // 初始化事务
	}

	/**
	 * 生产消息 => 发送到 Kafka topic
	 * key:消息的 key，同时也会作为 partition 的 key
	 * msg:发送的消息
	 */
	public void produceMsg(String key, String msg) {
		try {
			producer.beginTransaction(); // 开启事务
			// 生产消息
			producer.send(new ProducerRecord<>(TOPIC, key, msg));
			producer.commitTransaction(); // 提交事务
			logger.info(msg + "发送成功！");
		} catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
			// We can't recover from these exceptions, so our only option is to close the producer and exit.
			producer.close();
		} catch (KafkaException e) {
			// For all other exceptions, just abort the transaction and try again.
			producer.abortTransaction(); // 中止事务
		}
	}

	/**
	 * 销毁
	 */
	@PreDestroy
	public void destroy() {
		producer.close();
	}

	public static void main(String[] args) {
		logger.info("开始发送消息 ...");
		KafkaProducer producer = new KafkaProducer();
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			public void run() {
				producer.init();
				while (true) {
					try {
						long timestamp = System.currentTimeMillis();
						producer.produceMsg("test", timestamp + "发送消息");
						Thread.sleep(2000);
					} catch (Throwable e) {
						if (producer != null) {
							try {
								producer.destroy();
							} catch (Throwable e1) {
								System.out.println("Turn off Kafka producer error! " + e);
							}
						}
					}
				}
			}
		});
	}
}