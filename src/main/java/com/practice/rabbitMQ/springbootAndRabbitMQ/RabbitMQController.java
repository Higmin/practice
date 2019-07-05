package com.practice.rabbitMQ.springbootAndRabbitMQ;

import com.practice.rabbitMQ.springbootAndRabbitMQ.common.MsgProducer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther : guojianmin
 * @Date : 2019/7/2 10:14
 * @Description : RabbitMQController
 */
@RestController
public class RabbitMQController {

    @Resource
    private MsgProducer msgProducer;

    @ApiOperation(value = "RabbitMQ 发消息（直连模式）", notes = "RabbitMQ 发消息（直连模式）")
    @ApiImplicitParam(name = "msg", value = "消息内容", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST)
    public Boolean sendMsg(@RequestParam String msg){

        msgProducer.sendMsg(msg);
        return true;
    }

    @ApiOperation(value = "RabbitMQ 发消息", notes = "RabbitMQ 发消息")
    @ApiImplicitParam(name = "msg", value = "消息内容", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/sendMsgAll",method = RequestMethod.POST)
    public Boolean sendMsgAll(@RequestParam String msg){

        msgProducer.sendAll(msg);
        return true;
    }
}
