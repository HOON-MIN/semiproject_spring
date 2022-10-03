package kr.co.bteam.mvc.controller.orders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.dao.ItemDaoInter;
import kr.co.bteam.mvc.dao.OrdersDaoInter;
import kr.co.bteam.mvc.dao.ReviewDaoInter;
import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.ReviewsVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	private int nowPage = 1;// 현재 페이지 값
	private int nowBlock = 1;// 현재 블럭
	private int totalRecord = 0;// 총 게시물 수
	private int numPerPage = 10;// 한 페이지당 보여질 게시물 수
	private int pagePerBlock = 5;// 한 블럭당 보여질 페이지 수
	private int totalPage = 0;// 전체 페이지 수 => totalRecord/numPerPage
	private int totalBlock = 0;// 전체 블럭 수
	private int beginPerPage = 0;// 각 페이지별 시작 게시물의 index값
	private int endPerPage = 0;// 각 페이지별 마지막 게시물의 index값

	@Autowired
	private OrdersDaoInter ordersDaoInter;
	@Autowired
	private ReviewDaoInter review;
	@Autowired
	private ItemDaoInter itemDaoInter;

	@PostMapping(value = "/orderForm") // itemDetail ->
	public String orderForm(Model m) {
		return "orders/ordersForm";
	}

	@PostMapping(value = "/order") // itemDetail - > orderForm ->
	public ModelAndView ordersForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("orders/ordersForm");
		int s_no = Integer.parseInt(request.getParameter("i_no"));
		int stock = Integer.parseInt(request.getParameter("num"));
		int price = Integer.parseInt(request.getParameter("i_price"));
		int total = price * stock;
		ItemVO vo = itemDaoInter.getDetail(s_no);

		mav.addObject("detail", vo);
		mav.addObject("price", price);
		mav.addObject("stock", stock);
		mav.addObject("total", total);

		return mav;
	}

	@PostMapping(value = "/orderIn")
	public String addOrders(OrdersVO vo, HttpServletRequest request, HttpSession session) {
		int mem_no = (int) session.getAttribute("sessionNo");// 로그인 되어있는 사용자의 회원 번호
		int ord_count = Integer.parseInt(request.getParameter("number"));// 구매할 상품의 상품번호
		vo.setMem_no(mem_no);
		vo.setOrd_count(ord_count);
		ordersDaoInter.addOrders(vo);
		System.out.println(vo.getI_status());
		return "redirect:/web/main";
	}

	// 일반회원 구매리스트 - 최미르
	@RequestMapping(value = "/ordersList")
	public ModelAndView orderList(HttpSession session, SearchVO svo, MemberVO mvo) {
		ModelAndView mav = new ModelAndView("orders/ordersList");
		System.out.println("여기는 일반회원ordersController");
		mvo.setMem_no((int) session.getAttribute("sessionNo"));
		System.out.println("Controller sessionNo : " + (int) session.getAttribute("sessionNo"));
		totalRecord = ordersDaoInter.getCnt(mvo);
		System.out.println("totalRecord : " + totalRecord);
		totalPage = (int) Math.ceil(totalRecord / (double) numPerPage);
		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
		String cPage = svo.getcPage();
		if (cPage != null) {
			nowPage = Integer.parseInt(cPage);
		}
		System.out.println("sessionId : " + session.getAttribute("sessionId"));
		beginPerPage = (nowPage - 1) * numPerPage + 1;
		endPerPage = (beginPerPage - 1) + numPerPage;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginPerPage", beginPerPage);
		map.put("endPerPage", endPerPage);
		map.put("mem_no", (Integer) session.getAttribute("sessionNo"));
		
		List<OrdersVO> list = ordersDaoInter.getList(map);
		System.out.println("order list size => " + list.size());
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

		System.out.println(nowPage);
		System.out.println(pagePerBlock);
		System.out.println(totalPage);

		return mav;
	}

	// 판매 top3 item - 최미르
	@RequestMapping(value = "/rankOrdersList")
	public ModelAndView rankOrdersList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("orders/rankOrdersList");
		List<OrdersVO> list = ordersDaoInter.getRankOrdersList();
		mav.addObject("list", list);
		String r_path = request.getRealPath("/");
		System.out.println("orders에 랭킹 r_path : " + r_path);
		return mav;
	}

}
