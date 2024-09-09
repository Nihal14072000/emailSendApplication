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
	
	public User checkLogin(User user) {
		User u = emailDao.getByMailId(user);
		if(null != u) {
			return u;
		}else {
			return null;
		}
	}

	public User registerUser(User user) {
		User u1 = emailDao.getUserByMailId(user);
		if(!user.getMailId().equalsIgnoreCase(u1.getMailId())) {
			String body= "Dear "+user.getfName() + " " + user.getlName() + ",\r\n You have been registered to site successfully. Please login to check.";
			user = emailDao.saveUser(user,body);
			mailSend.sendMail(user.getMailId(), "Registration Seuccessful", body);
			return user;
		}else {
			System.out.println("User exist already");
			return null;
		}
		
	}
}
