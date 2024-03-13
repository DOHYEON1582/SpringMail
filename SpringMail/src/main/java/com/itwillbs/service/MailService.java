package com.itwillbs.service;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service(value = "mailService")
public class MailService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	// 메일 전송에 필요한 객체 
	@Inject
	private JavaMailSender mailSender;
	
	@Inject
	private SimpleMailMessage preConfigMailSender;
	
	// 메일 전송하기 동작
	//@Async : 해당 메서드는 비동기 방식으로 처리
	@Async
	public void sendMail(String to, String subject, String body){
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
			
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			//messageHelper.setText(body);
			//messageHelper.setFrom(from); 발신자설정 -> 지금은 불가능
			messageHelper.setText(body,true);
			
			mailSender.send(message);
			logger.debug(" 메일 전송 완료! ");
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Async
	public void preConfigSendMail(String msg) throws Exception{
		SimpleMailMessage simpleMSG = new SimpleMailMessage(preConfigMailSender);
		
		simpleMSG.setText(msg);
		
		mailSender.send(simpleMSG);
		
	}
	
	
	
	
	
} //mailService
