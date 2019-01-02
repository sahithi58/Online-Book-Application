package com.sapestore.exception;

/**
 * Custom exception class
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */
public class SapeStoreSystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4155553550533488357L;

	public SapeStoreSystemException(String Message) {
		super(Message);
	}

	public SapeStoreSystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
