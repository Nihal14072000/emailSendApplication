package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
