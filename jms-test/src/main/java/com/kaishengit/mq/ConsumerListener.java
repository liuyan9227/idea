package com.kaishengit.mq;

import sun.plugin2.message.Message;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author liuyan
 * @date 2018/8/8
 */
public class ConsumerListener implements MessageListener {

    @Override
    public void onMessage(javax.jms.Message message) {
        TextMessage textMessage = (TextMessage)message;

        try {
            System.out.println("====>" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
