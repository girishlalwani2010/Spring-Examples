package org.girishlalwani.spring.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleMessageListener implements MessageListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageListener.class);

    public void onMessage(Message message) {
        try {
        	//delay applied here, as if some one want to see the message in to the queue.
        	Thread.sleep(10000);
            LOG.info("Received reply message: {}   at Destination {}", ((TextMessage)message).getText(),message.getJMSDestination());
        } 
        catch(InterruptedException e)
        {
        	e.printStackTrace();
        }
        catch (JMSException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
