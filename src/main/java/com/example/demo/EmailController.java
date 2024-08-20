package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping(value = "/")
	public String callMethodDefault() {
		return "Welcome To My Page";
	}

	@GetMapping(value = "/sendMailToMe")
	public String callMethod() {
		emailService.sendMailCall();
		return "MailSent";
	}
	
	@GetMapping(value = "/inputs")
	public String getInputData() {
		//emailService.sendMailCall();
		return "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<h1>My First Heading</h1>\r\n"
				+ "<p>My first paragraph.</p>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>";
	}
	
	@GetMapping(value = "/sendMail")
	public String sendMailToUser(@RequestParam ("mailId") String mailId) {
		return "Mail send to " + mailId;
	}
}
