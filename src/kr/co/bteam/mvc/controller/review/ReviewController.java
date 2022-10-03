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
		int cnt = reviewDaoInter.chkReviews(rvo);// 배송완료인 상품의 주문번호로 리뷰테이블에 데이터가 있는지 체크
		System.out.println("getOrd_no = "+rvo.getMem_no());
		System.out.println("리뷰폼 컨트 cnt = " + cnt);
		
		if(cnt==1) {
			mav.addObject("emsg", "리뷰를 이미 작성하셨습니다!!");
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
		//여기에 업데이트
		ordersDaoInter.updateRcnt(vo.getOrd_no());
		return "redirect:/web/orders/ordersList";
	}
	
	
	
}





