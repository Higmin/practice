package com.practice.rabbitMQ.mqTest;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/2 7:53
 * @Description : TODO用一句话描述此类的作用
 */
public class MessageConsumer {

    //队列名称
    private static final String QUEUE_NAME = "first_queue";
    //主机IP
    private static final String HOST="127.0.0.1";
    //端口
    private static final Integer PORT=5672;
    //用户名
    private static final String USERNAME="evans";
    //密码
    private static final String PASSWORD="evans";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建工厂类
        ConnectionFactory factory = new ConnectionFactory();
        //设置参数
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        //创建连接
        Connection connection =factory.newConnection();
        //创建Channel
        Channel channel=connection.createChannel();
        //声明Queue
        /*
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments)
         * 队列名称,是否持久保存,是否为专用的队列,是否允许自动删除,配置参数
         * 此处的配置与RabbitMQ管理界面的配置一致
         */
        channel.queueDeclare(QUEUE_NAME,true,false,true,null);
        //这里需要复写handleDelivery方法进行消息自定义处理
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("Consume Get Message : "+message);
            }
        };
        channel.basicConsume(QUEUE_NAME,consumer);
    }
}
