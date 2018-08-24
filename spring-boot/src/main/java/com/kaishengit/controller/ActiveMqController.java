package com.kaishengit.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author liuyan
 * @date 2018/8/23
 */
@Controller
public class ActiveMqController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/queue")
    public void queueSpringBoot(){
        // 创建消息队列的对象
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("springboot-queue");
        // jms模版发送消息,需要对列名和消息
        jmsTemplate.send(activeMQQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("hello,springboot");
                return textMessage;
            }
        });
    }


    @GetMapping("/topic")
    public void topicSpringBoot(){
        // 创建消息队列的对象
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("springboot-topic");
        // jms模版发送消息,需要对列名和消息
        jmsTemplate.send(activeMQTopic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("hello,springboot-topic");
                return textMessage;
            }
        });
    }


}
