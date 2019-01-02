package com.sapestore.exception;

import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * Custom exception class
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */
public class ExceptionHandler {

	/**
	 * returns stack trace
	 * 
	 * @param ex
	 * @return String
	 */
	public static String getStackStrace(Throwable ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String sStackTrace = new String(sw.getBuffer());
		return sStackTrace;
	}
}
