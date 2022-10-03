package kr.co.bteam.mvc.aop;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.dao.AdminMemberDaoInter;
import kr.co.bteam.mvc.dao.MemberDaoInter;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;

@Component
@Aspect
public class RankMemberAdvice {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Before("execution(* kr.co.bteam.mvc.controller.*.*Controller.adminMemberList(..))")
	public void rankMember(JoinPoint jp) {
		System.out.println("RankMemberAdvice 동작!");
		// ----------------------------- 선행 처리
		Object[] args = jp.getArgs();
		
		// 인자 : SearchVO
		SearchVO svo = (SearchVO) args[0];
		Model m =  (Model) args[2];
		System.out.println("advice search =>"+svo.getSearch());
		
		String methodName = jp.getSignature().getName();
		System.out.println("methodName => "+methodName);
		
		AdminMemberDaoInter adminMemberList = applicationContext.getBean(methodName, AdminMemberDaoInter.class);
		List<OrdersVO> listAop = adminMemberList.getRankMem();
		
		System.out.println("advice list => "+listAop.size());
		
		m.addAttribute("listAop", listAop);
		
		
		
	}
	
}




