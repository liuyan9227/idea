package com.kaishengit.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author liuyan
 * @date 2018/8/8
 */
public class ConsumerListener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("====>" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
