package kr.co.bteam.mvc.controller.orders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.controller.service.AdminItemStockService;
import kr.co.bteam.mvc.controller.service.AdminOrdersStockService;
import kr.co.bteam.mvc.dao.AdminOrdersDaoInter;
import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

@Controller
@RequestMapping("/adminorders")
public class AdminOrdersController {

	@Autowired
	private AdminOrdersStockService adminOrdersStockService;
	
	private int nowPage = 1;// 현재 페이지 값
	private int nowBlock = 1;// 현재 블럭
	private int totalRecord = 0;// 총 게시물 수
	private int numPerPage = 5;// 한 페이지당 보여질 게시물 수
	private int pagePerBlock = 5;// 한 블럭당 보여질 페이지 수
	private int totalPage = 0;// 전체 페이지 수 => totalRecord/numPerPage
	private int totalBlock = 0;// 전체 블럭 수
	private int beginPerPage = 0;// 각 페이지별 시작 게시물의 index값
	private int endPerPage = 0;// 각 페이지별 마지막 게시물의 index값
	
	// 관리자회원 구매리스트 
		@RequestMapping(value = "/adminordersList")
		public ModelAndView adminOrdersList(SearchVO svo, ModelAndView mav, HttpSession session ,MemberVO mvo ) {
			mav.setViewName("adminorders/adminordersList");
//			int mem_no = (String) session.getAttribute("sessionNo");
//			session.setAttribute("sessionId", mvo.getMem_id());
//			session.setAttribute("sessionNo", mvo.getMem_no());
			System.out.println("컨트롤러 cnt전 search :"+svo.getSearch());
			System.out.println("컨트롤러 cnt전 category :"+svo.getCategory());
			// aop에서 cnt값 받아오기
			
			totalRecord = (int) mav.getModel().get("cnt");
			System.out.println("컨트롤러 관리자 주문량 : "+totalRecord);
			totalPage = (int) Math.ceil(totalRecord / (double) numPerPage);
			totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
			
		
			if (svo.getSearchreset().equals("1")) {
				System.out.println("cPage 관리자 리셋 =>"+ svo.getcPage());
				nowPage = Integer.parseInt(svo.getcPage());
			}else {
				System.out.println("cPage 관리자 페이지 번호 선택시 =>"+svo.getcPage());
				nowPage = Integer.parseInt(svo.getcPage());
			}
					
			beginPerPage = (nowPage - 1) * numPerPage + 1;
			endPerPage = (beginPerPage - 1) + numPerPage;
			System.out.println("컨트롤러 cnt후 search :"+svo.getSearch());
			System.out.println("컨트롤러 cnt후 category :"+svo.getCategory());			
			svo.setBeginPerPage(beginPerPage);
			svo.setEndPerPage(endPerPage);
//			svo.setCategory("배송중");
			List<? extends SuperDTO> list = adminOrdersStockService.getAdminServiceList(svo);
			
			
			int startPage = (int) ((nowPage - 1) / pagePerBlock) * pagePerBlock + 1;
			int endPage = startPage + pagePerBlock - 1;
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			mav.addObject("list", list);
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("nowPage", nowPage);
			mav.addObject("pagePerBlock", pagePerBlock);
			mav.addObject("totalPage", totalPage);
			mav.addObject("category", svo.getCategory());
			mav.addObject("search", svo.getSearch());
			System.out.println("startPage :"+ startPage);
			System.out.println("endPage :"+ endPage);
			System.out.println("nowPage :"+ nowPage);
			System.out.println("pagePerBlock :"+ pagePerBlock);
			System.out.println("totalPage :"+ totalPage);
			return mav;
		}
		
		// 관리자 주문 디테일 페이지
		@GetMapping(value = "adminDetailForm")
		public ModelAndView adminOrdersDetail(OrdersVO ovo) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("vo", adminOrdersStockService.detailAdminServiceOrders(ovo.getOrd_no()));
			mav.setViewName("adminorders/adminOrdersDetail");
			return mav;
		}
		
		// 주문 수정 및 배송정보 자동 기입
		@PostMapping(value = "/adminordersUpdate")
		public ModelAndView adminorersUpdate(OrdersVO ovo) {
			ModelAndView mav = new ModelAndView();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("수정 컨트롤러 주문상태 : "+ovo.getI_status());
						if (ovo.getI_status().equals("주문취소")) {
							adminOrdersStockService.updateAdminServiceOrders(ovo);
						}else {
							adminOrdersStockService.updateAdminServiceOrders(ovo);
							Thread.sleep(5000);
							ovo.setI_status("배송중");
							adminOrdersStockService.updateAdminServiceOrders(ovo);
							Thread.sleep(5000);
							ovo.setI_status("배송완료");
							// 리뷰구분
							ovo.setRcnt(1);
							adminOrdersStockService.updateAdminServiceOrders(ovo);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			
			mav.addObject("vo", adminOrdersStockService.detailAdminServiceOrders(ovo.getOrd_no()));
			mav.setViewName("redirect:adminordersList");
			return mav;
		}
		
		
		@GetMapping(value = "adminordersDelete")
		public ModelAndView admindeleteOrders(OrdersVO ovo) {
			ModelAndView mav = new ModelAndView();
			int checkNum = adminOrdersStockService.deleteAdminServiceOrdersCheck(ovo.getOrd_no());
			if (checkNum == 1) {
				System.out.println("컨트롤러 삭제 주문번호 : " + ovo.getOrd_no());
				adminOrdersStockService.deleteAdminServiceOrders(ovo.getOrd_no());
				mav.setViewName("redirect:adminordersList");
			}else {
				mav.setViewName("error/paramException");
				mav.addObject("emsg", "배송상태를 주문취소로 변경해주세요~!");
			}
			
			return mav;
		}
		
	
	
}
