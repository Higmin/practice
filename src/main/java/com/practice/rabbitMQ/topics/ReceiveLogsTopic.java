package com.practice.rabbitMQ.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/1 14:32
 * @Description : 消费者
 *
 *  设置路由时，可以通过 #、* 来 灵活设置绑定参数  ：其中 # 代表（hash）可以替换零个或多个单词   * 代表（星号）可以替代一个单词。 }
 */
public class ReceiveLogsTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        if (argv.length < 1) {
            //由于在IDEA中测试，所以暂时需要在没有传参数的情况下，设置默认值
//            System.err.println("Usage: ReceiveLogsTopic [info] [warning] [error]");
//            System.exit(1);
//            argv = new String[]{"log.Info"};
//            argv = new String[]{"log.Inf#"};//#（hash）可以替换零个或多个单词。
            argv = new String[]{"log.*"};//*（星号）可以替代一个单词。
        }

        for (String bindingKey : argv) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
