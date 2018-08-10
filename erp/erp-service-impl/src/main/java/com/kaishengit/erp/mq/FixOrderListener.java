package com.kaishengit.erp.mq;

import com.kaishengit.erp.service.FixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author liuyan
 * @date 2018/8/9
 */
public class FixOrderListener implements SessionAwareMessageListener {

    Logger logger = LoggerFactory.getLogger(FixOrderListener.class);

    @Autowired
    private FixService fixService;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;

        try {
            String json = textMessage.getText();
            logger.info("接受队列的json数据{}", json);

            fixService.comOrderfromFrontMq(json);
        } catch (JMSException e){
            e.printStackTrace();
            session.recover();
        }
    }
}
