package com.practice.rabbitMQ.mqTest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/2 7:52
 * @Description : TODO用一句话描述此类的作用
 */
public class MessageProducer {
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

    public static void main(String[] args) throws Exception {
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
        Long start = System.currentTimeMillis();
        for (int i=0;i<100;i++){
            //发布消息
            /*
             * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
             * exchange名称,RoutingKey,消息参数(消息头等)（持久化时需要设置）,消息体
             * MessageProperties有4中针对不同场景可以进行选择
             */
            channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,("Message:"+i).getBytes());
        }
        Long end = System.currentTimeMillis();
        System.out.println("System cost :"+(end-start));
        channel.close();
        connection.close();
    }
}
