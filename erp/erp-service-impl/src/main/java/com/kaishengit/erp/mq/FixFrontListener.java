package com.kaishengit.erp.mq;

import com.kaishengit.erp.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author liuyan
 * @date 2018/8/10
 */
public class FixFrontListener implements SessionAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(FixFrontListener.class);

    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        // 获得message的实现类对象
        TextMessage textMessage = (TextMessage) message;

        try {
            // 获取对象中的json数据
            String json = textMessage.getText();
            // 记录日志
            logger.info("接受队列的json数据{}", json);
            // 将json数据发送至service中
            orderService.comFixStateMq(json);
        } catch (JMSException e){
            e.printStackTrace();
            session.recover();
        }
    }
}
