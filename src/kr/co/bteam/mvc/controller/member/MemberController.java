package kr.co.bteam.mvc.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.controller.service.UserServiceInter;
import kr.co.bteam.mvc.dao.MemberDaoInter;
import kr.co.bteam.mvc.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberDaoInter memberdaointer;
	@Autowired
	private UserServiceInter userServiceInter;

	@GetMapping(value="/joinForm")
	public ModelAndView joinMember() {
		ModelAndView mav = new ModelAndView("member/joinForm");
		return mav;
	}
	

	@PostMapping(value = "/joinMember")
	public String memberIn(MemberVO mvo) {
		System.out.println("id: "+mvo.getMem_id());
		userServiceInter.addMember(mvo);
		return "redirect:/web/main";
	}
	
	
	@GetMapping(value="/idChk")
	public String idCheck(Model m,@RequestParam("id") String id) {
		System.out.println("idAjax:"+id);
		int cnt = userServiceInter.idCheck(id);
		System.out.println("Cnt:"+cnt);
		m.addAttribute("cnt", cnt);
		return "member/member/idChk";
	}

	@RequestMapping(value="/emailChk")
	public String emailCheck(Model m,@RequestParam("email") String email) {
		System.out.println("idAjax:"+ email);
		int mcnt = userServiceInter.emailCheck(email);
		System.out.println("Cnt:"+mcnt);
		m.addAttribute("mcnt", mcnt);
		return "member/member/emailChk";
	}
	@GetMapping("/myPage")
	public ModelAndView myPageLsit( MemberVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView("member/mypage");
		int mem_no = (int) session.getAttribute("sessionNo");
		vo = memberdaointer.memberInfo(mem_no);
		mav.addObject("vo", vo);
		
		return mav;
	}
	
	@PostMapping("/updateIn")
	public String update(MemberVO vo,HttpSession session,HttpServletRequest request) {
		int mem_no = (int) session.getAttribute("sessionNo");
		vo.setMem_no(mem_no);
		memberdaointer.memberUpdate(vo);
		

		return "redirect:/web/main";
}
}
