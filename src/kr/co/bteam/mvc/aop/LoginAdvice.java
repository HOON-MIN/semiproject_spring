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
		System.out.println("ȸ�����ԿϷ�");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Around("execution(* kr.co.bteam.mvc.controller.login.LoginController.loginP*(..))")
	public ModelAndView loginLogger(ProceedingJoinPoint jp) {
		System.out.println("����!");
        Object[] fd = jp.getArgs();
	    
		ModelAndView rpath=null;
		String methodName = jp.getSignature().getName();
		System.out.println("methodName =>"+methodName);
		
		if(methodName.equals("loginProcess")) {  // loginó���� �Ǿ��� �� ����ǵ��� Advice�� ����
			// �α��� ���� �� ���� �޾Ƽ� ���� ������ ���̽��� ����
			System.out.println("�α���");
			MemberLogVO lvo = new MemberLogVO();
			userAgent = (String) fd[3];
			System.out.println(userAgent);
			try {
				System.out.println("���νõ� ����");
				rpath = (ModelAndView) jp.proceed();
				if(fd[0] instanceof HttpSession && fd[1] instanceof HttpServletRequest) {
					HttpSession session = (HttpSession) fd[0];
					HttpServletRequest request = (HttpServletRequest) fd[1];
					System.out.println("����Ǵ°Ŵ�?");
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
			System.out.println("�α׾ƿ�");
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
					//dao ����
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
