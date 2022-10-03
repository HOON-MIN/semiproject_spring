package kr.co.bteam.mvc.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.controller.service.UserServiceInter;
import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.MemberLogVO;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private UserServiceInter userServiceInter; 
	
	@RequestMapping(value="/loginForm")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView("login/loginForm");
		return mav;
	}
	
	//------------ 아이디, 비밀번호 찾기
	@RequestMapping(value="/memberId")
	public ModelAndView findIdForm() {
		ModelAndView mav = new ModelAndView("login/findId");
		return mav;
	}
	
	@RequestMapping(value="/memberPw")
	public ModelAndView findPwdForm() {
		ModelAndView mav = new ModelAndView("login/findPwd");
		return mav;
	}
	
	@RequestMapping("/findId")
	public String findId(Model m ,MemberVO dto , HttpSession session) {
		System.out.println(dto.getMem_name());
		System.out.println(dto.getMem_email());
		String resid = userServiceInter.findId(dto);
		m.addAttribute("resid",resid);
		System.out.println(resid);
		if(resid == null) {
			m.addAttribute("msg","등록 된 아이디 정보가 없습니다");
			return "login/findId";			
		}else {
			m.addAttribute("resid",resid);
			session.setAttribute("sessionId", resid);
			return "login/findResId";
		}
	}
	
	@RequestMapping("/findPwd")
	public String findPwd(Model m ,MemberVO dto , HttpSession session) {
		System.out.println(dto.getMem_id());
		System.out.println(dto.getMem_q());
		System.out.println(dto.getMem_a());
		String respwd = userServiceInter.findPwd(dto);
		m.addAttribute("respw",respwd);
		System.out.println(respwd);
		if(respwd == null) {
			m.addAttribute("msg","답이 틀렸습니다");
			return "login/findPwd";			
		}else {
			m.addAttribute("respwd", respwd);
			session.removeAttribute("sessionId");
			return "login/findResPwd";
		}
	}
	//------------------------------------
	
	
	//------------로그인 / 로그아웃 
	@PostMapping(value="/loginProcess")
	public ModelAndView loginProcess(HttpSession session, HttpServletRequest request, MemberVO dto , @RequestHeader("User-Agent") String userAgent , String mem_id) {
		System.out.println("User Agent => "+ userAgent);
		System.out.println("Reip : "+request.getRemoteAddr());
		ModelAndView mav = new ModelAndView("redirect:/web/main");
		MemberVO mvo = userServiceInter.loginCheck(dto);
		String gender = userServiceInter.gender(mem_id);
		System.out.println(gender);
		if(mvo == null) {
			mav.setViewName("error/paramException");
			mav.addObject("emsg", "로그인 실패 입니다.");
		}else {
			session.setAttribute("sessionName", mvo.getMem_name());
			session.setAttribute("sessionId", mvo.getMem_id());
			session.setAttribute("sessionGender", gender);
			session.setAttribute("sessionNo", mvo.getMem_no());
		}
		return mav;
	}
	
	@GetMapping(value="/loginPout")
	public ModelAndView loginPout(HttpSession session,HttpServletRequest request ,@RequestHeader("User-Agent") String userAgent) {
		ModelAndView mav = new ModelAndView();
		System.out.println("logoutForm");
		session.removeAttribute("sessionName");
		session.removeAttribute("sessionId");
		session.removeAttribute("sessionGender");
		mav.setViewName("redirect:/web/orders/rankOrdersList");
		return mav;
	}
	//----------------------------------------
	
	
	//--------------로그인 로그 관련
	@RequestMapping(value="/logListView")
	public String logList(Model m, HttpSession session) {
		List<MemberLogVO> list = userServiceInter.logList();
		String id = (String) session.getAttribute("sessionId");
		System.out.println(id);
		MemberVO llist = userServiceInter.myLogList(id);
		//m.addAttribute("loglist",list);
		m.addAttribute("loglist",llist);
		return "login/logList";
	}
	
	
}
