package com.practice.rabbitMQ.springbootAndRabbitMQ.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/2 10:10
 * @Description : TODO用一句话描述此类的作用
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_FANOUT_A)
public class MsgReceiver2 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content) {
        logger.info("(广播模式)接收处理队列 QUEUE_FANOUT_A 当中的消息： " + content);
    }

}
