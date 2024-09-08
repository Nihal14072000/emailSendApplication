package com.example.demo.sendMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.Employee;
import com.example.demo.emailSendDao;

@Service
public class SendMail {
	

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String from;
	
	@Autowired
	private emailSendDao emailDao;

	/* common method to send mail **/
	public void sendMail(String toMail, String Subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();
		Employee emp = new Employee();
		message.setTo(toMail);
		message.setFrom(from);
		message.setText(body);
		message.setSubject(Subject);
		javaMailSender.send(message);

		/** saving entry in database **/
		emp.setName(toMail);
		emp.setRole("Reciever");
		emp.setMessageBody(body);
		emailDao.save(emp);
	}
}
