package com.practice.rabbitMQ.workQueues.helloWorld_new;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/1 9:31
 * @Description : 发送消息 更改 ==> 通过传参的方式进行改进，可以发送任意消息（尔不是只能发送helloWorld）
 * 我们将稍微修改前一个示例中的Send.java代码，以允许从命令行发送任意消息。该程序将任务安排到我们的工作队列，所以我们将其命名为  Send.java
 */
public class Send {

    private final static String TASK_QUEUE_NAME = "hello_new";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
            //更改 ==> 通过传参的方式进行改进，可以发送任意消息（尔不是只能发送helloWorld）
            String message = String.join("",argv);
            channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
