package com.practice.rabbitMQ.publishAndSubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/1 11:30
 * @Description : 日志生产者
 *
 * 生成日志消息的生产者程序与前一个教程没有太大的不同。最重要的变化是我们现在想要将消息发布到我们的日志交换而不是无名交换。
 * 我们需要在发送时提供routingKey，但是对于扇出交换，它的值会被忽略。这里是EmitLog.java程序的代码 ：
 */
public class EmitLog {
    private static final String EXCHANGE_NAME ="logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            String message = argv.length < 1 ? "info: Hello World!" :
                    String.join(" ", argv);

            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
