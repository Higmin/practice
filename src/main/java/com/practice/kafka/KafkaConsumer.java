package com.practice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * @author Higmin
 * @date 2019/11/26 8:26
 * kafka 消费者
 **/
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	private Properties props;
	private org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer;
	public final static String TOPIC = "TEST-TOPIC";

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init(){
		props = new Properties();
		props.put("bootstrap.servers", "192.168.183.150:9092,192.168.183.151:9092,192.168.183.152:9092"); // kafka 地址
		props.put("group.id", "test"); // 设置消费组
		props.put("enable.auto.commit", "true"); //开启自动提交
		props.put("auto.commit.interval.ms", "1000"); // 自动提交时间间隔
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // key反序列化
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // value反序列化
		consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(props);
	}

	/**
	 * 消费消息 -- 自动偏移量提交
	 * 消费消息由两种方式：
	 * 1. 自动偏移量提交：使用自动偏移量提交还可以“至少一次”交付，但要求是必须在每次后续调用之前或关闭使用者之前使用每次调用poll（Duration）所返回的所有数据。
	 * 2. 手动偏移控制：使用手动偏移控制的优点是可以直接控制何时将 Records 视为“消耗”。
	 */
	public void consumeMsg(){
		// 可以订阅多个 topic 中间用逗号隔开
		consumer.subscribe(Arrays.asList(TOPIC));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				long offset = record.offset();
				String key = record.key();
				String msg = record.value();
				logger.info("消费成功！ offset: " + offset + "  key: " + key + "  value: " +  msg);
			}
		}
	}

	/**
	 * 销毁
	 */
	@PreDestroy
	public void destroy(){
		consumer.close();
	}

	public static void main(String[] args) {
		KafkaConsumer consumer = new KafkaConsumer();
		logger.info("开始消费消息...");
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				consumer.init();
				while (true){
					try {
						consumer.consumeMsg();
					} catch (Exception e) {
						if (consumer != null) {
							try {
								consumer.destroy();
							} catch (Throwable e1) {
								logger.error("Turn off Kafka consumer error! " + e);
							}
						}
					}
				}
			}
		});

	}

}
