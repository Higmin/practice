package com.practice.rabbitMQ.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/1 14:26
 * @Description : 生产者
 */
public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String routingKey = "";
            String message = "";
            if (argv.length == 0){
                routingKey = "log.Info";
//                routingKey = "log.error";
//                routingKey = "log.warn";
                message = "I am EmitLogTopic msg!";
            }else {
                routingKey = getRouting(argv);
                message = getMessage(argv);
            }

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }
    }

    /**
     * 由于在IDEA中测试，所以暂未实现main方法传参的相关赋值
     * @param argv
     * @return
     */
    private static String getMessage(String[] argv) {
        return null;
    }

    /**
     * 由于在IDEA中测试，所以暂未实现main方法传参的相关赋值
     * @param argv
     * @return
     */
    private static String getRouting(String[] argv) {
        return null;
    }

}
