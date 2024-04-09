package com.vku.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
	@Autowired
	JavaMailSender javaMailSender;


	public boolean sendMail(String subject, String content, String toEmail) {
	    try {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	        helper.setTo(toEmail);
	        helper.setSubject(subject);
	        helper.setText(content, true);
	        javaMailSender.send(message);
	        return true; // Gửi email thành công
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        return false; // Gửi email thất bại
	    }
	}


}
