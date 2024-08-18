package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String from;
	
	public void sendMailCall() {
		String body = createBody();
		sendMail("nehal.kagwadkar@gmail.com", "Spring mail test", body);
	}
	
	private String createBody() {
		String body = "Dear Nehal Kagwadkar,\r\n"
				+ "\r\n"
				+ "I hope this email finds you well. This mail is being send from Spring Application "
				+ "Thank you!\r\n"
				+ "\r\n"
				+ "Best,\r\n"
				+ "\r\n"
				+ "Nehal ";
		return body;
	}

	public void sendMail(String toMail, String Subject, String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toMail);
		message.setFrom(from);
		message.setText(body);
		message.setSubject(Subject);
		javaMailSender.send(message);
	}
}
