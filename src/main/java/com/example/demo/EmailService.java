package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.sendMail.SendMail;

@Service
public class EmailService {

	@Autowired
	private emailSendDao emailDao;
	
	@Autowired
	private SendMail mailSend;

	@Value("${spring.mail.username}")
	private String from;

	/** send mail to my mail ID **/
	public void sendMailCall() {
		String body = createBody();
		mailSend.sendMail("nehal.kagwadkar@gmail.com", "Spring mail test", body);
	}

	/** send mail to other mail ID **/
	public void sendMailCall(String mailId) {
		String body = createBody();
		mailSend.sendMail(mailId, "Spring mail test", body);
	}

	private String createBody() {
		String body = "Dear User,\r\n" + "\r\n"
				+ "I hope this email finds you well. This mail is being send from Spring Application "
				+ "Thank you!\r\n" + "\r\n" + "Best,\r\n" + "\r\n" + "Nehal ";
		return body;
	}

	public List<Employee> getAll() {
		return emailDao.getAll();
	}
	
	public String checkLogin(String mailId) {
		List<Employee> empList = emailDao.getByMailId(mailId);
		if(null != empList) {
			return "Y";
		}else {
			return "N";
		}
	}
}
