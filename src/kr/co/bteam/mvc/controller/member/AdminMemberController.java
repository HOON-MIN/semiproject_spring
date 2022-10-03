package kr.co.bteam.mvc.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.dao.AdminMemberDaoInter;
import kr.co.bteam.mvc.dao.MemberDaoInter;
import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

@Controller
@RequestMapping(value = "/member")
public class AdminMemberController {
	@Autowired
	private AdminMemberDaoInter adminMemberDaoInter;
	
	private int nowPage = 1;// 현재 페이지 값
	private int nowBlock = 1;// 현재 블럭
	private int totalRecord = 0;// 총 게시물 수
	private int numPerPage = 10;// 한 페이지당 보여질 게시물 수
	private int pagePerBlock = 5;// 한 블럭당 보여질 페이지 수
	private int totalPage = 0;// 전체 페이지 수 => totalRecord/numPerPage
	private int totalBlock = 0;// 전체 블럭 수
	private int beginPerPage = 0;// 각 페이지별 시작 게시물의 index값
	private int endPerPage = 0;// 각 페이지별 마지막 게시물의 index값
	
	@RequestMapping("/adminMemberList")
	public ModelAndView adminMemberList(SearchVO svo, ModelAndView mav, Model m) {
//		ModelAndView mav = new ModelAndView();
		System.out.println("시작페이지 : "+svo.getBeginPerPage());
		System.out.println("마지막페이지 : "+svo.getEndPerPage());
		System.out.println("검색 : "+svo.getSearch());
		System.out.println("분류 : "+svo.getCategory());
		
		totalRecord =  (int) mav.getModel().get("cnt");
		System.out.println("adminMemberList - totalRecord : "+totalRecord);
		totalPage = (int) Math.ceil(totalRecord / (double) numPerPage);
		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
		if (svo.getcPage() != null) {
			nowPage = Integer.parseInt(svo.getcPage());
		}
		beginPerPage = (nowPage - 1) * numPerPage + 1;
		endPerPage = (beginPerPage - 1) + numPerPage;
		
		svo.setBeginPerPage(beginPerPage);
		svo.setEndPerPage(endPerPage);
	
		List<? extends SuperDTO> list = adminMemberDaoInter.getList(svo);
		
		int startPage = (int) ((nowPage - 1) / pagePerBlock) * pagePerBlock + 1;
		int endPage = startPage + pagePerBlock - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		mav.addObject("list", list);
		mav.addObject("category", svo.getCategory());
		mav.addObject("search", svo.getSearch());
		mav.addObject("totalRecord", totalRecord);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("nowPage", nowPage);
		mav.addObject("pagePerBlock", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		mav.setViewName("member/adminMemberList");
		System.out.println("여기는 adminmemberList 컨트롤러 영역입니다.!");
		return mav;
	}
// 	관리자 모드
//	@RequestMapping("/adminMemberList")
//	public String memberList(Model m, SearchVO svo) {
//		int totalRecord = memberDaoInter.getAdminCnt(svo);
//		System.out.println("adminMemberList의 totalRecord : "+totalRecord);
//		m.addAttribute("totalRecord", totalRecord);
//		return "member/adminMemberList";
//	}
	
	// detail
	@GetMapping(value = "/memberDetail")
	public ModelAndView memberDetail(int num) {
		ModelAndView mav = new ModelAndView("member/adminMemberDetail");
		mav.addObject("vo", adminMemberDaoInter.getDetail(num));
		return mav;
	}

	// update
	@PostMapping(value = "memberUpdate")
	public String memberUp(MemberVO vo) {
		System.out.println("제목 변경 : " + vo.getMem_id());
		adminMemberDaoInter.upMember(vo);
		return "redirect:/member/adminMemberList";
	}

	// delete
	@GetMapping(value = "memberDelete")
	public String memberDel(int num) {
		adminMemberDaoInter.delMember(num);
		return "redirect:/member/adminMemberList";
	} 
	
}
