package com.practice.rabbitMQ.springbootAndRabbitMQ.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/2 8:29
 * @Description : TODO用一句话描述此类的作用
 */
@Configuration
public class RabbitConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    public static final String EXCHANGE_DIRECT = "exchange.direct";// 直连模式（direct）
    public static final String EXCHANGE_TOPIC = "exchange.topic";// 主题模式（topic）
    public static final String EXCHANGE_FANOUT = "exchange.fanout";//广播模式（fanout）


    public static final String QUEUE_DIRECT = "QUEUE_DIRECT";
    public static final String QUEUE_FANOUT_A = "QUEUE_FANOUT_A";//用于广播队列
    public static final String QUEUE_FANOUT_B = "QUEUE_FANOUT_B";//用于广播队列
    public static final String QUEUE_TOPIC = "QUEUE_TOPIC";

    public static final String ROUTINGKEY_STATUS = "ROUTINGKEY_STATUS";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";

    /**
     * 建立连接
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//必须是prototype类型（作用域：原型模式）
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
//===================================================== start ============================================
    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念(广播模式)
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列(直连模式)
     TopicExchange:多关键字匹配(主题模式)
     */
    //================================= 直连模式 DirectExchange ================================//
    /**
     * 配置 directExchange
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitConfig.EXCHANGE_DIRECT);
    }

    /**
     * 获取队列 QUEUE_DIRECT
     * @return
     */
    @Bean
    public Queue queueDirect() {
        return new Queue(RabbitConfig.QUEUE_DIRECT, true); //队列持久
    }

    /**
     * 获取队列 QUEUE_FANOUT_A
     * @return
     */
    @Bean
    public Queue queueFanoutA() {
        return new Queue(RabbitConfig.QUEUE_FANOUT_A, true); //队列持久
    }

    /**
     * 获取队列 QUEUE_FANOUT_B
     * @return
     */
    @Bean
    public Queue queueFanoutB() {
        return new Queue(RabbitConfig.QUEUE_FANOUT_B, true); //队列持久
    }

    /**
     * 获取队列 QUEUE_TOPIC
     * @return
     */
    @Bean
    public Queue queueTopic() {
        return new Queue(RabbitConfig.QUEUE_TOPIC, true); //队列持久
    }

    /**
     * 绑定队列 及 路由规则
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueDirect()).to(directExchange()).with(RabbitConfig.ROUTINGKEY_STATUS);
    }

    //================================= 主题模式 TopicExchange ================================//
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConfig.EXCHANGE_TOPIC);
    }
    //================================= 广播模式 FanoutExchange ================================//

    /**
     * 配置 fanout_exchange
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(RabbitConfig.EXCHANGE_FANOUT);
    }

    //把所有广播的队列都绑定到这个交换机上去
    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(queueFanoutA()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(queueFanoutB()).to(fanoutExchange());
    }

//===================================================== end ============================================
}
