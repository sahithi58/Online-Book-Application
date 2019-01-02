package com.sapestore.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.opensymphony.xwork2.ActionSupport;
import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;

/**
 * This is a controller class for the email functionality on Defaulters Report page. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */

@Controller
public class EmailController {
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(EmailController.class.getName());
	
	/**
	 * Sends email to book return defaulters.
	 * @param ids
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	public String sendEmail(@RequestParam("emailIds") String ids, ModelMap modelMap) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("sendEmail method: START");
		}
		String sepIds[] = ids.split(":");
		for (int i = 0; i < sepIds.length; i++) {
			String details[] = sepIds[i].split("#");
			String from = "admin@sapestore.com";
			String host = "inrelaymail.sapient.com";
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", host);
			Session session = Session.getInstance(properties);
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(details[0]));

				if (details[5].equals("RETURNED")) {
					message.setSubject("Dear Customer, Your late book return has levied a late fee!!");
					message.setText("Dear "
							+ details[1]
							+ ",\r\n"
							+ "\r\nYou returned the rented "
							+ details[2]
							+ " post its due date i.e. "
							+ details[3]
							+ ". Hence you have been charged a late fee of $"
							+ details[4]
							+ ".\r\n"
							+ "\r\nNote : In case of any query, please give a call to our customer support at +1 2444448080.\r\n"
							+ "\r\n" + "\r\nHappy Reading!!\r\n"
							+ "Sape Store Admin");

				} else {
					message.setSubject("Dear Customer, Your book return is pending!!");
					message.setText("Dear "
							+ details[1]
							+ ",\r\n"
							+ "\r\nYou have rented "
							+ details[2]
							+ " and its due date for return was "
							+ details[3]
							+ ". We have not received the book and you have been levied a late fee of $"
							+ details[4]
							+ ".\r\n"
							+ "Please return the book at the earliest to avoid further increase in late fee charge.\r\n"
							+ "\r\nNote In case of any query, please give a call to our customer support at +1 2444448080.\r\n"
							+ "\r\n" + "\r\nHappy Reading!!\r\n"
							+ "Sape Store Admin");

				}
				Transport.send(message);
			} 
			catch (MessagingException mex) {
				LOGGER.error("welcome method: ERROR: " + mex);
				return ApplicationConstants.FAILURE;
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("sendEmail method: END");
		}
		return ActionSupport.NONE;
	}

}
