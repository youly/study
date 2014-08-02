package com.lastww.study.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by liuweiwei on 14-8-2.
 */
public class Activemq {

    private String queue = "test";

    public void produce() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queue);
            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            for (int i = 0; i < 10; i++) {
                TextMessage textMessage = session.createTextMessage("Hello, world:" + i);
                messageProducer.send(textMessage);
            }
            System.out.println("message has been send");

            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queue);
            MessageConsumer messageConsumer = session.createConsumer(destination);

            boolean flag = true;
            while (flag) {
                Message message = messageConsumer.receive(1000);
                System.out.println(message.toString());
                System.out.println("received message:" + ((TextMessage) message).getText());
            }

            messageConsumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Activemq mq = new Activemq();
        mq.produce();
        mq.consume();
    }
}
