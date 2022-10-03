package kr.co.bteam.mvc.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import kr.co.bteam.mvc.dao.AdminListcountInter;
import kr.co.bteam.mvc.vo.SearchVO;

@Component
@Aspect
public class AdminListCntModulesAdvice {

	// PageListInter를 getBean받아오기 위해서
	@Autowired
	private ApplicationContext applicationContext;
	
	@Before("execution(* kr.co.bteam.mvc.controller.*.*Controller.admin*List(..))")
	public void allCountModule(JoinPoint jp) {
		// ------------ 선행처리 영역
		Object[] args = jp.getArgs();

		// 첫번째 인자 : SearchVO, 두번째 인자 : ModelAndView cPage
		SearchVO svo =  (SearchVO) args[0];
		ModelAndView mav = (ModelAndView) args[1];
		System.out.println("advice category1 =>"+svo.getCategory());
		System.out.println("advice search1 =>"+svo.getSearch());
		
		String myDao = jp.getSignature().getName();
		System.out.println("myDao : "+myDao);
		
		AdminListcountInter adminListcountInter =  applicationContext.getBean(myDao, AdminListcountInter.class);
		int cnt = adminListcountInter.getAllItemcount(svo);
		System.out.println("advice cnt=>"+cnt);
		System.out.println("advice category2 =>"+svo.getCategory());
		System.out.println("advice search2 =>"+svo.getSearch());
		if (svo.getCategory() != "") {
			mav.addObject("cMsg", svo.getCategory());
		}else {
			mav.addObject("cMsg","총");
		}
		mav.addObject("sMsg", svo.getSearch());
		mav.addObject("cnt", cnt);
	}
}
