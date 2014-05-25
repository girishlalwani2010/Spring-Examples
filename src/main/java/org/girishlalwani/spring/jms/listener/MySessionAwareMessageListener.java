package org.girishlalwani.spring.jms.listener;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class MySessionAwareMessageListener implements MessageListener {

	private static final Logger LOG = LoggerFactory
			.getLogger(MySessionAwareMessageListener.class);

	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			//delay applied here, as if some one want to see the message in to the queue.
			Thread.sleep(20000);
			LOG.info("Received message: {}", ((TextMessage) message).getText());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			sendMessages();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendMessages() throws JMSException {
		final StringBuilder buffer = new StringBuilder();
		buffer.append("Message '").append("ack").append("' sent at: ")
				.append(new Date());
		final String payload = buffer.toString();
		System.out.println("The payload which is send to Queue by Consumer is"
				+ payload);

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage(payload);

				LOG.info("Sending acknowledgement '{}'", payload);
				return message;
			}
		});
	}

}
