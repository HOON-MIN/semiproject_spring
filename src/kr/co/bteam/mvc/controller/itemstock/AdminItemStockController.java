package kr.co.bteam.mvc.controller.itemstock;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.controller.service.AdminItemStockService;
import kr.co.bteam.mvc.dao.AdminItemStockInter;
import kr.co.bteam.mvc.dao.AdminListcountInter;
import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.StockVO;
import kr.co.bteam.mvc.vo.SuperDTO;

@Controller
@RequestMapping("/adminitemstock")
public class AdminItemStockController {

		@Autowired
		private AdminItemStockService itemStockService;
		
		@Autowired
		private AdminItemStockInter adminItemStockInter; 
		// 상품등록 Form으로 이동하는 메서드
		@GetMapping(value = "adminitemstockForm")
		public ModelAndView adminitemstockForm() {
			ModelAndView mav = new ModelAndView("adminitemstock/adminitemstockForm");
			return mav;
		}
		
		// 상품등록 정보를 받아서 입력하는 메서드
		@PostMapping(value = "adminitemstockIn")
		public String adminitemstockIn(ItemVO ivo, StockVO svo,
				HttpServletRequest request) {
			System.out.println("상품명 : "+ivo.getI_name());
			//request를 가지고 이미지의 경로를 받아서 출력
			String img_path = "resources\\imagefile";
			String r_path = request.getRealPath("/");
			System.out.println("r_path : "+r_path);
			
			String oriFn = ivo.getMfile().getOriginalFilename();
			// 이미지의 사이즈 및 contentType확인
			long size = ivo.getMfile().getSize();
			String contentType = ivo.getMfile().getContentType();
			System.out.println("파일 크기 : "+size);
			System.out.println("파일 크기 : "+contentType);
			System.out.println("oriFn : "+oriFn);
			StringBuffer path = new StringBuffer();
			path.append(r_path).append(img_path).append("\\");
			path.append(oriFn);
			System.out.println("Fullpath : "+ path);
			// 추상경로(이미지를 저장할 경로) File 객체로 생성
			File f = new File(path.toString());
			try {
				ivo.getMfile().transferTo(f);
				ivo.setI_img(oriFn);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
				
			itemStockService.addServiceItemStock(ivo, svo);
			return "redirect:adminitemstockList";
		}
		
		private int nowPage = 1;// 현재 페이지 값
		private int nowBlock = 1;// 현재 블럭
		private int totalRecord = 0;// 총 게시물 수
		private int numPerPage = 5;// 한 페이지당 보여질 게시물 수
		private int pagePerBlock = 5;// 한 블럭당 보여질 페이지 수
		private int totalPage = 0;// 전체 페이지 수 => totalRecord/numPerPage
		private int totalBlock = 0;// 전체 블럭 수
		private int beginPerPage = 0;// 각 페이지별 시작 게시물의 index값
		private int endPerPage = 0;// 각 페이지별 마지막 게시물의 index값
		
		@RequestMapping(value = "/adminitemstockList")
		public ModelAndView adminitemstockList(SearchVO svo, ModelAndView mav, HttpSession session) {
			
			System.out.println("시작페이지 : "+svo.getBeginPerPage());
			System.out.println("마지막페이지 : "+svo.getEndPerPage());
			System.out.println("검색 : "+svo.getSearch());
			System.out.println("분류 : "+svo.getCategory());
			//aop에서 cnt값 받아오기
			totalRecord = (int) mav.getModel().get("cnt");
			System.out.println("orders의 상품 총 품목수 : "+totalRecord);
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
			
			svo.setBeginPerPage(beginPerPage);
			svo.setEndPerPage(endPerPage);
		
			List<? extends SuperDTO> list = itemStockService.getList(svo);
			
			int startPage = (int) ((nowPage - 1) / pagePerBlock) * pagePerBlock + 1;
			int endPage = startPage + pagePerBlock - 1;
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			
			
			mav.addObject("list", list);
			mav.addObject("category", svo.getCategory());
			mav.addObject("search", svo.getSearch());
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("nowPage", nowPage);
			mav.addObject("pagePerBlock", pagePerBlock);
			mav.addObject("totalPage", totalPage);
			mav.setViewName("adminitemstock/adminitemstockList");
			System.out.println("startPage :"+ startPage);
			System.out.println("endPage :"+ endPage);
			System.out.println("nowPage :"+ nowPage);
			System.out.println("pagePerBlock :"+ pagePerBlock);
			System.out.println("totalPage :"+ totalPage);
			return mav;
		}
		// detail 페이지
		@GetMapping(value = "adminitemstockDetail")
		public ModelAndView adminitemstockDetail(int i_no) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("vo", itemStockService.detailItemstock(i_no));
			mav.setViewName("adminitemstock/adminitemstockDetail");
			return mav;
		}
		
		@PostMapping(value = "adminitemUpdate")
		public String adminitemUpdate(ItemVO ivo, StockVO svo, HttpServletRequest request) {
			System.out.println("가격 변경 : "+ivo.getI_price());
			String img_path= "resources\\imagefile";
			String r_path = request.getRealPath("/");
			System.out.println("r_path : "+r_path);
			String oriFn = ivo.getMfile().getOriginalFilename();
			long size = ivo.getMfile().getSize();
			String contentType = ivo.getMfile().getContentType();
			System.out.println("파일 크기 : "+size);
			System.out.println("파일 크기 : "+contentType);
			System.out.println("oriFn : "+oriFn);
			StringBuffer path = new StringBuffer();
			path.append(r_path).append(img_path).append("\\");
			path.append(oriFn);
			System.out.println("Fullpath : "+ path);
			File f = new File(path.toString());
			try {
				ivo.getMfile().transferTo(f);
				ivo.setI_img(oriFn);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			svo.setS_no(ivo.getI_no());
			itemStockService.updateServiceItemStock(ivo, svo);
			
			return "redirect: adminitemstockList";
		}
		
		@GetMapping(value = "adminitemDelete")
		public String admindelteItemstock(int i_no) {
			itemStockService.deleteServiceItemstock(i_no);
			return "redirect: adminitemstockList";
		}
		
		@GetMapping(value = "/itemNameChk")
		public ModelAndView itemNameChk(@RequestParam("i_name") String i_name) {
			ModelAndView mav = new ModelAndView("adminitemstock/adminitemstock/inamechk");
			Map<String, String> map = new HashMap<String, String>();
			map.put("i_name",i_name);
			int cnt = adminItemStockInter.itemNameChk(map);
			System.out.println("컨트롤러 i_name"+i_name);
			System.out.println("컨트롤러 cnt=>"+cnt);
			mav.addObject("cnt", cnt);
			return mav;
		}
		
		
}
