package com.practice.rabbitMQ.Routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/1 13:38
 * @Description : 生产者 ： 实现消费者消费同一个 topic 下的某一部分消息（而不是此topic下的全部消息）
 *
 * 使用；
 * 通过 String severity = "Info";    //Info为日志的一个级别，通过绑定不同的参数，消费者可以通过不同的参数来消费指定的 某些 消息
 * 1.消费端： channel.queueBind（queueName，EXCHANGE_NAME，severity）;
 * 2.生产端： channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
 *
 * 另外：我们可以使用相同的绑定参数 ，来实现生产端的消息广播到所有匹配的队列
 */
public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            String severity = "";
            String message = "";
            if (argv.length == 0){
                severity = "Info";
                message = "I am msg!";
            }else {
                severity = getSeverity(argv);
                message = getMessage(argv);
            }

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
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
    private static String getSeverity(String[] argv) {
        return null;
    }

}
