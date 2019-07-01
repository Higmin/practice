package com.practice.rabbitMQ.workQueues.taskAndWorker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/1 10:03
 * @Description : 消费者
 *
 * 消息确认，消息持久性
 *
 * (1.)消息确认
 * 默认情况下，手动消息确认已打开,我们 把手动确认消息关闭 boolean autoAck = false;
 * 应用：channel.basicConsume（TASK_QUEUE_NAME，autoAck，deliverCallback，consumerTag  - > {}）;
 *
 * (2.)消息持久性
 * boolean durable = true ;
 * 应用:1.channel.queueDeclare（“task_queue”，durable，false，false，null）;
 *      2.channel.basicPublish（“”，“task_queue”，MessageProperties.PERSISTENT_TEXT_PLAIN，message.getBytes（））;
 * (3.)公平派遣（背景:在有两个工人的情况下，当所有奇怪的消息都很重，甚至消息很轻时，一个工人将经常忙，而另一个工作人员几乎不会做任何工作。那么，RabbitMQ对此一无所知，仍然会均匀地发送消息。发生这种情况是因为RabbitMQ只是在消息进入队列时调度消息。它不会查看消费者未确认消息的数量。它只是盲目地向第n个消费者发送每个第n个消息。）
 * 我们可以使用basicQos方法和  prefetchCount = 1设置 公平派遣
 * 这告诉RabbitMQ一次不向一个worker发送一条消息。或者，换句话说，在处理并确认前一个消息之前，不要向工作人员发送新消息。相反，它会将它发送给下一个仍然很忙的工人。
 * 应用：int prefetchCount = 1 ;
 *      channel.basicQos（prefetchCount）;
 */
public class Worker {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");
            try {
                doWork(message);
            } finally {
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
