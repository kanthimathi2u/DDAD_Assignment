package com.deloitte.assignment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Custom exception to handle team outing exceptions
 * 
 */

public class TeamOutException extends Exception {
	private static final Logger logger = LoggerFactory.getLogger(TeamOutException.class);

	private static final long serialVersionUID = -8064375427647152246L;

	public TeamOutException(String message) {
		super(message);
		logger.debug("Exception has occured: " + message);
	}

}
