package com.sapestore.exception;

/**
 * Custom exception class
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */
public class SapeStoreException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6745341992488917337L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message
	 */
	public SapeStoreException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause
	 * 
	 * @param message
	 * @param cause
	 */
	public SapeStoreException(String message, Throwable cause) {
		super(message, cause);
	}

}
