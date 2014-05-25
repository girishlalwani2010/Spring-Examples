package org.girishlalwani.spring.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class SomeHandler implements ErrorHandler {
	private static final Logger LOG = LoggerFactory.getLogger(SomeHandler.class);
	
    @Override
    public void handleError(Throwable t) {
    	LOG.warn("Error in listener", t);
}
}