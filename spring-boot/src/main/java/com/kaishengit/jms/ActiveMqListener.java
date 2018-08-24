package com.kaishengit.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * @author liuyan
 * @date 2018/8/23
 */
@Component
public class ActiveMqListener {

    private Logger logger = LoggerFactory.getLogger(ActiveMqListener.class);

    @JmsListener(destination = "springboot-queue")
    public void queueListener(String message){
        logger.info("收到queue消息:{}", message);
    }

    @JmsListener(destination = "springboot-topic", containerFactory = "jmsTopicListenerContainerFactory")
    public void topicListener(String message){
        logger.info("收到Topic消息:{}", message);
    }

}
