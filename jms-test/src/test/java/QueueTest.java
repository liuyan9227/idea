import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 * @author liuyan
 * @date 2018/8/7
 */
public class QueueTest {

    /**
     * 1对1--消息生产者
     * @throws JMSException
     */
    @Test
    public void messageProducer() throws JMSException {
        // 1.创建连接工厂 (接口引用指向实现类对象, http端口为8161, tcp端口为61616)
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // 2.创建连接 并 开启
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3.创建Session (param1: 是否开启手动提交事务; param2: 接收者签收模式: AUTO_ACKNOWLEDGE // 自动签收)
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        // 4.创建消息目的地 (创建队列名称)
        Destination destination = session.createQueue("hello-word-topic");
        // 5.创建生产者
        MessageProducer producer = session.createProducer(destination);
        // 6.发送消息 (可以存在多个消息被接收)
        TextMessage textMessage = session.createTextMessage("Hello,MQ1");
        TextMessage textMessage1 = session.createTextMessage("Hello,MQ2");
        producer.send(textMessage);
        producer.send(textMessage1);
        // 7.释放资源
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * 1对1消息消费者
     * @throws JMSException
     * @throws IOException
     */
    @Test
    public void comsumerMessage() throws JMSException, IOException {
        //1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

        //2. 创建并启动连接
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //3. 创建Session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //4. 创建目的地对象 (名字必须要和队列名相同才可以获得队列中的消息)
        Destination destination = session.createQueue("hello-word-topic");

        //5. 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //6. 获取消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // 持续接收消息
        System.in.read();
        //7. 释放资源 (如果循环接收就用不到释放资源了)
        consumer.close();
        session.close();
        connection.close();
    }



}
