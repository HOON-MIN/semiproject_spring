package kr.co.bteam.mvc.aop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.controller.service.UserServiceInter;
import kr.co.bteam.mvc.vo.MemberLogVO;
import kr.co.bteam.mvc.vo.MemberVO;

@Component
@Aspect
public class LoginAdvice {
	private String userAgent;
	
	@Autowired
	private UserServiceInter userServiceInter;
	
	@After("execution(* kr.co.bteam.mvc.controller.service.UserService.addMember(..))")
	public void loginComplete(JoinPoint jp) {
		try {
		System.out.println("회원가입완료");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Around("execution(* kr.co.bteam.mvc.controller.login.LoginController.loginP*(..))")
	public ModelAndView loginLogger(ProceedingJoinPoint jp) {
		System.out.println("동작!");
        Object[] fd = jp.getArgs();
	    
		ModelAndView rpath=null;
		String methodName = jp.getSignature().getName();
		System.out.println("methodName =>"+methodName);
		
		if(methodName.equals("loginProcess")) {  // login처리가 되었을 때 적용되도록 Advice를 구현
			// 로그인 했을 때 정보 받아서 각각 데이터 베이스에 저장
			System.out.println("로그인");
			MemberLogVO lvo = new MemberLogVO();
			userAgent = (String) fd[3];
			System.out.println(userAgent);
			try {
				System.out.println("프로시드 실행");
				rpath = (ModelAndView) jp.proceed();
				if(fd[0] instanceof HttpSession && fd[1] instanceof HttpServletRequest) {
					HttpSession session = (HttpSession) fd[0];
					HttpServletRequest request = (HttpServletRequest) fd[1];
					System.out.println("실행되는거니?");
					String uid = (String) session.getAttribute("sessionId");
					System.out.println("uid====>"+uid);
					if(uid != null) {
						lvo.setIdn(uid);
						lvo.setStatus("login");
						lvo.setReip(request.getRemoteAddr());
						lvo.setUagent(userAgent);
						userServiceInter.addLoginLogging(lvo);
					}
				}
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(methodName.equals("loginPout")) { // logout
			System.out.println("로그아웃");
			System.out.println("userAgent =>"+userAgent);
			MemberLogVO lvo = new MemberLogVO();
			if(fd[0] instanceof HttpSession && fd[1] instanceof HttpServletRequest) {
				HttpSession session = (HttpSession) fd[0];
				HttpServletRequest request = (HttpServletRequest) fd[1];
				String uid = (String) session.getAttribute("sessionId");
				if(uid != null) {
					lvo.setIdn(uid);
					lvo.setStatus("logout");
					lvo.setReip(request.getRemoteAddr());
					lvo.setUagent(userAgent);
					//dao 전달
					userServiceInter.addLoginLogging(lvo);
				}
			}
			try {
				rpath = (ModelAndView) jp.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		System.out.println("Test!"+rpath);
		return rpath;
	}
}
