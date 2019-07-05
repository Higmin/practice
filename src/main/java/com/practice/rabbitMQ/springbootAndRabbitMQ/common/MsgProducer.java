package com.practice.rabbitMQ.springbootAndRabbitMQ.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/2 8:36
 * @Description : TODO用一句话描述此类的作用
 */
@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;
    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    /**
     * 直连模式，一个交换机可以绑定一个或多个消息队列，也就是消息通过一个交换机，可以分发到不同的队列当中去。
     * @param msg
     */
    public void sendMsg(String msg) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_DIRECT, RabbitConfig.ROUTINGKEY_STATUS, msg, correlationId);
    }

    /**
     * 广播模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
     * @param msg
     */
    public void sendAll(String msg) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_FANOUT,"", msg);
    }
    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("消息生产者回调函数==>   回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功消费");
        } else {
            logger.info("消息消费失败:" + cause);
        }
    }
}
