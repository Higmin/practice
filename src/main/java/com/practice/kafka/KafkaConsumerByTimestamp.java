package com.practice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * @author Higmin
 * @date 2019/11/27 9:20
 * <p>
 * 示例三 ：
 * <p>
 * kafka 消费者 ==> 以时间戳查询消息
 * 例如，我们希望从主题“TEST-TOPIC”第 0 分区距离当前时间相差 12 小时之前的位置开始拉取消息
 **/
public class KafkaConsumerByTimestamp {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	private Properties props;
	private org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer;
	public final static String TOPIC = "TEST-TOPIC";

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		props = new Properties();
		props.put("bootstrap.servers", "192.168.183.150:9092,192.168.183.151:9092,192.168.183.152:9092"); // kafka 地址
		props.put("group.id", "test"); // 设置消费组
		props.put("client.id", "test"); // 设置消费者ID
		props.put("fetch.max.bytes", 1024);// 为了便于测试，这里设置一次fetch 请求取得的数据最大值为1KB,默认是5MB
		props.put("enable.auto.commit", "true"); //开启自动提交
		props.put("auto.commit.interval.ms", "1000"); // 自动提交设置偏移量提交时间间隔
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // key反序列化
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // value反序列化
		consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(props); // 创建消费者
	}

	/**
	 * 消费消息
	 */
	public void consumeMsg() throws Exception {
		// 可以订阅多个 主题 中间用逗号隔开
		consumer.subscribe(Arrays.asList(TOPIC));
		/**
		 * 了解 ==> consumer 消费速度控制
		 * 提供 pause(Collection<TopicPartition> partitions)和resume(Collection<TopicPartition>partitions)方法，
		 *分别用来暂停某些分区在拉取操作时返回数据给客户端和恢复某些分区向客户端返回数据操作。通过这两个方法可以对消费速度加以控制，结合业务使用。
		 */
		while (true) {
			// 等待拉取消息
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
			for (ConsumerRecord<String, String> record : records) {
				// 简单打印出消息内容,模拟业务处理
				long offset = record.offset();
				String key = record.key();
				String msg = record.value();
				logger.info("消费成功！ offset: " + offset + "  key: " + key + "  value: " + msg);
			}
		}
	}

	/**
	 * 销毁
	 */
	@PreDestroy
	public void destroy() {
		consumer.close();
	}

	public static void main(String[] args) {
		KafkaConsumer consumer = new KafkaConsumer();
		logger.info("开始消费消息...");
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				consumer.init();
				while (true) {
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
					} finally {
						consumer.destroy();
					}
				}
			}
		});
	}
}
