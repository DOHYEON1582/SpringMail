package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.service.MailService;
//@EnableAsync : 비동기식 처리가 가능하도록 설정

@Controller
@EnableAsync
public class MailController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Inject
	private MailService mailService;
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public void sendMail() throws Exception{
		logger.info(" sendMail() 호출");
		
//    	mailService.sendMail(받는메일주소, 제목, 내용);
		//mailService.sendMail("pondes@kakao.com", "메일 전송 테스트", "테스트 메일 내용 1234");
		
		//mailService.preConfigSendMail("테스트 메일 내용 12222");
		//mailService.preConfigSendMail("<img src='https://ssl.pstatic.net/melona/libs/1481/1481325/5c843f339f81885bbb66_20240311170304513.jpg'>");
		
		// 메일에 일반 텍스트가 아닌 정보들을 전송할때(이미지/HTML)
		
		// 전송할 데이터 설정
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html><head></head><body>");
		sb.append("<h1> 안녕하세요 아이티윌 입니다 </h1>");
		sb.append("<a href='https://www.naver.com'>");
		sb.append("<img src='https://naverpa-phinf.pstatic.net/MjAyNDAxMDlfMTYy/MDAxNzA0NzgwMTg4Mzg4.I7YVaDEqDwSIdKBrAEXEQsWik6yr8002ylVMWzUb4TAg.gaR-V31UhjFmalY6nZWNkNXnhg3DTA0FjliZU-6KH6Ig.JPEG/%EB%B8%94%EB%9E%99%EB%A7%88%EC%B9%B4_3%28342X228%29_17047801883689475072379449184898.jpg'>");
		sb.append("</a>");
		sb.append("</body></html>");
		
		mailService.sendMail("pondes@kakao.com", "html테스트", sb.toString());
	}
	
	
	
	
	
	
	
} // controller
