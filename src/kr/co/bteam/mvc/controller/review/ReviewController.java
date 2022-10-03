package kr.co.bteam.mvc.controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.dao.OrdersDaoInter;
import kr.co.bteam.mvc.dao.ReviewDaoInter;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.ReviewsVO;

@Controller
@RequestMapping(value = "/review")
public class ReviewController {

	@Autowired
	private ReviewDaoInter reviewDaoInter;
	
	@Autowired
	private OrdersDaoInter ordersDaoInter; 
	
	@GetMapping(value = "/reviewForm")
	public ModelAndView reviewForm(ReviewsVO rvo,OrdersVO ovo ,HttpSession session) {
		System.out.println("getOrd_no = "+rvo.getOrd_no());
		ModelAndView mav = new ModelAndView();
		int mem_no = (int) session.getAttribute("sessionNo");
		int cnt = reviewDaoInter.chkReviews(rvo);// ��ۿϷ��� ��ǰ�� �ֹ���ȣ�� �������̺� �����Ͱ� �ִ��� üũ
		System.out.println("getOrd_no = "+rvo.getMem_no());
		System.out.println("������ ��Ʈ cnt = " + cnt);
		
		if(cnt==1) {
			mav.addObject("emsg", "���並 �̹� �ۼ��ϼ̽��ϴ�!!");
			mav.setViewName("error/paramException");
		}
		mav.addObject("vo", rvo);
		return mav;
	}
	
	@PostMapping(value = "/reviewIn")
	public String addReview(ReviewsVO vo,HttpServletRequest request,HttpSession session) {
		int mem_no = (int) session.getAttribute("sessionNo");
		vo.setMem_no(mem_no);
		reviewDaoInter.addReview(vo);
		//���⿡ ������Ʈ
		ordersDaoInter.updateRcnt(vo.getOrd_no());
		return "redirect:/web/orders/ordersList";
	}
	
	
	
}





