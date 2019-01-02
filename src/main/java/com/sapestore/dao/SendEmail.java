package com.sapestore.dao;

//File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

public void sendmail(String mail,String username,String password) {    
   // Recipient's email ID needs to be mentioned.
   String to = mail;
System.out.println(mail);
   // Sender's email ID needs to be mentioned
   String from = "durga0110@gmail.com";

   // Assuming you are sending email from localhost
   String host = "localhost";

   // Get system properties
   Properties properties = System.getProperties();

   // Setup mail server
   properties.setProperty("mail.smtp.host", host);

   // Get the default Session object.
   Session session = Session.getDefaultInstance(properties);

   try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: header field
      message.setSubject("Registration for SapeStore");

      // Now set the actual message
      message.setText("Username =" +"" +username + "\n"+"password=" +"" +password +"\n"+"You have been succesfully registered");

      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
   }catch (MessagingException mex) {
      mex.printStackTrace();
   }
}
}
