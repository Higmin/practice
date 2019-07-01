package com.practice.rabbitMQ.Routing;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/1 11:20
 * @Description : 本包是案例--4 （路由）
 * 使用；
 *  通过 String severity = "Info";    //Info为日志的一个级别，通过绑定不同的参数，消费者可以通过不同的参数来消费指定的 某些 消息
 *  1.消费端： channel.queueBind（queueName，EXCHANGE_NAME，severity）;
 *  2.生产端： channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
 *
 *  另外：我们可以使用相同的绑定参数 ，来实现生产端的消息广播到所有匹配的队列
 */
public class Test {
}
