package kr.co.bteam.mvc.controller.itemstock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.controller.service.ReviewsService;
import kr.co.bteam.mvc.dao.ItemDaoInter;
import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.ReviewsVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	private int nowPage = 1;// 현재 페이지 값
	private int nowBlock = 1;// 현재 블럭
	private int totalRecord = 0;// 총 게시물 수
	private int numPerPage = 5;// 한 페이지당 보여질 게시물 수
	private int pagePerBlock = 5;// 한 블럭당 보여질 페이지 수
	private int totalPage = 0;// 전체 페이지 수 => totalRecord/numPerPage
	private int totalBlock = 0;// 전체 블럭 수
	private int beginPerPage = 0;// 각 페이지별 시작 게시물의 index값
	private int endPerPage = 0;// 각 페이지별 마지막 게시물의 index값

	@Autowired
	private ItemDaoInter item;
	
	@Autowired
	private ReviewsService service;
	
	// 상품 리스트 출력
	@GetMapping(value = "/itemList")
	public ModelAndView itemList(Model model, SearchVO svo, HttpSession session , ItemVO ivo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("item/itemList");
		// url의 query가 m, w인지에 따라 i_gender 값을 지정해주는 if문
		if (svo.getGender().equals("m")) {
			svo.setGender("남성");
		} else if (svo.getGender().equals("w")) {
			svo.setGender("여성");
		} else if(svo.getGender().equals("c")){
			svo.setGender("공용");
		}
		
		totalRecord = item.getCnt(svo);
		System.out.println("totalRecord : " + totalRecord);
		totalPage = (int) Math.ceil(totalRecord / (double) numPerPage);
		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
		String r_path = request.getRealPath("/");
     	System.out.println("r_path : "+r_path);
		if (svo.getSearchreset().equals("1")) {
			nowPage = Integer.parseInt(svo.getcPage());
		}else {
			nowPage = Integer.parseInt(svo.getcPage());
		}
		
		beginPerPage = (nowPage - 1) * numPerPage + 1;
		endPerPage = (beginPerPage - 1) + numPerPage;

		svo.setBeginPerPage(beginPerPage);
		svo.setEndPerPage(endPerPage);
		
		List<? extends SuperDTO> list = item.getItemList(svo);
		int startPage = (int) ((nowPage - 1) / pagePerBlock) * pagePerBlock + 1;
		int endPage = startPage + pagePerBlock - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		System.out.println(svo.getCategory());
		System.out.println(svo.getSearch());
		System.out.println(svo.getGender());
		
		mav.addObject("list", list);
		mav.addObject("category", svo.getCategory());
		mav.addObject("search", svo.getSearch());
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("nowPage", nowPage);
		mav.addObject("pagePerBlock", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.addObject("g", svo.getGender());
		return mav;
	}

	// 수량 체크해서 총 금액 계산하는 메서드
	@GetMapping(value = "/numchk")
	public String numberCheck(Model m, int num) {
		m.addAttribute("numchk", num);
		return "item/item/numchk";
	}

	// 잡화 페이지 리스트
//	@GetMapping(value = "/categoryList")
//	public ModelAndView categoryList(SearchVO svo, ItemVO ivo, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("item/categoryList");
//		totalRecord = item.getCnt(svo);
//		System.out.println("Cnt : "+item.getCnt(svo));
//		System.out.println("totalRecord : " + totalRecord);
//		totalPage = (int) Math.ceil(totalRecord / (double) numPerPage);
//		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
//        if (svo.getSearchreset().equals("1")) {
//			nowPage = Integer.parseInt(svo.getcPage());
//		}else {
//			nowPage = Integer.parseInt(svo.getcPage());
//		}
//		beginPerPage = (nowPage - 1) * numPerPage + 1;
//		endPerPage = (beginPerPage - 1) + numPerPage;
//
//		svo.setBeginPerPage(beginPerPage);
//		svo.setEndPerPage(endPerPage);
//
//		int startPage = (int) ((nowPage - 1) / pagePerBlock) * pagePerBlock + 1;
//		int endPage = startPage + pagePerBlock - 1;
//		if (endPage > totalPage) {
//			endPage = totalPage;
//		}
//		List<? extends SuperDTO> list = item.getCategoryList(svo);
//		mav.addObject("list", list);
//		mav.addObject("category", svo.getCategory());
//		mav.addObject("search", svo.getSearch());
//		mav.addObject("startPage", startPage);
//		mav.addObject("endPage", endPage);
//		mav.addObject("nowPage", nowPage);
//		mav.addObject("pagePerBlock", pagePerBlock);
//		mav.addObject("totalPage", totalPage);
//		return mav;
//	}

	// 상품 리스트에서 상품명 클릭 시 상세보기 페이지로 넘어갈때 해당 상품의 PK를 기준으로 데이터를 뽑아서 보여주는 리스트
	@GetMapping(value = "/itemDetail")
	public String itemDetail(Model m, int s_no) {
		System.out.println("s_no : " +s_no);
		ItemVO vo = item.getDetail(s_no);
		int i_no = vo.getI_no();
		m.addAttribute("detail", vo);
		
		List<ReviewsVO> rvo = service.getReviews(i_no); 
		//controller - service - dao 순
		//상품번호에 해당하는 리뷰 리스트 
		m.addAttribute("list", rvo);
		
		return "item/itemDetail";
	}
	
	// 잡화 리스트에서 상품명 클릭 시 상세보기 페이지로 넘어가는 리스트
//	@GetMapping(value = "/categoryDetail")
//	public String categoryDetail(Model m, int s_no) {
//		System.out.println("s_no : " + s_no);
//		// s_no(stock테이블의 pk) = i_no(item테이블의 pk)
//		ItemVO vo = item.getDetail(s_no);
//		m.addAttribute("detail", vo);// detail.jsp로 상세정보를 보내주기
//		return "item/categoryDetail";
//	}

	// 상세보기 페이지에서 구매 버튼 클릭 시 구매 정보를 입력하는 폼으로 이동
	
}