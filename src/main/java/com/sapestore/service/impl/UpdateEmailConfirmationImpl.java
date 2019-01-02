package com.sapestore.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.EmailAddressRetrieveDao;
import com.sapestore.service.UpdateEmailConfirmation;
import com.sapestore.vo.RegisterVO;

@Service("emailAddressService1")
public class UpdateEmailConfirmationImpl implements UpdateEmailConfirmation {
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(EmailConfirmationServiceImpl.class.getName());
	
	@Autowired
	private EmailAddressRetrieveDao emailAddressRetrieve;
	
	@Autowired
    private JavaMailSender mailSender;
	
    @Autowired
    private VelocityEngine velocityEngine;

	protected String subject;
	
	@Override
	public String retrieveMailId(String userId) {
		// TODO Auto-generated method stub
		
		return emailAddressRetrieve.retrieveMailId(userId);
		
	}


	@Override
	public void sendAccountUpdateEmail(final RegisterVO register, String userId) {
		final String userEmailId=retrieveMailId(userId);
		final String subject="Profile Updated";

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(userEmailId);
                
                message.setFrom(new InternetAddress("sapestore2k16@sapient.com"));
                
                Map<String, Object> userModel = new HashMap<String, Object>();
                userModel.put("register", register);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                        "velocity/emailtemplate.vm", userModel);
                message.setSubject(subject);
                message.setText(text, true);
            }

			
        };
        try {
            // mailSender.send(mailMessage);
            mailSender.send(preparator);
            System.out.println("mail send");
            if(LOGGER.isDebugEnabled()){
    			LOGGER.debug("email sent successfully");
    		}
    		
        } catch (Exception e) {
            if(LOGGER.isDebugEnabled()){
    			LOGGER.debug("email not sent successfully");
    		}
            System.out.println("mail error not send");
            e.printStackTrace();
        }
    }

}
