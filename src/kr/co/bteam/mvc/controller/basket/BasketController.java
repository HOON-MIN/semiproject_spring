package kr.co.bteam.mvc.controller.basket;

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
import org.springframework.web.servlet.ModelAndView;

import kr.co.bteam.mvc.controller.service.BasketOrderService;
import kr.co.bteam.mvc.dao.BasketDaoInter;
import kr.co.bteam.mvc.dao.OrdersDaoInter;
import kr.co.bteam.mvc.vo.BasketVO;
import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;

@Controller
@RequestMapping("/basket")
public class BasketController {
	
	private int nowPage = 1;// ���� ������ ��
	private int nowBlock = 1;// ���� ��
	private int totalRecord = 0;// �� �Խù� ��
	private int numPerPage = 10;// �� �������� ������ �Խù� ��
	private int pagePerBlock = 5;// �� ���� ������ ������ ��
	private int totalPage = 0;// ��ü ������ �� => totalRecord/numPerPage
	private int totalBlock = 0;// ��ü �� ��
	private int beginPerPage = 0;// �� �������� ���� �Խù��� index��
	private int endPerPage = 0;// �� �������� ������ �Խù��� index��
	
	@Autowired
	private BasketDaoInter basket;
	
	@Autowired
	private OrdersDaoInter ordersDaoInter;
	
	@Autowired
	private BasketOrderService basketOrderService;
	
	// ��ٱ��Ͽ� ��ǰ�� �߰��ϴ� �޼���
	@PostMapping(value = "/addBasket")
	public ModelAndView addBasket(BasketVO vo, ItemVO ivo, HttpSession session, HttpServletRequest request) {
		
		int mem_no = (int) session.getAttribute("sessionNo");
		int i_no = ivo.getI_no();
		int b_stock = Integer.parseInt(request.getParameter("num"));

		vo.setMem_no(mem_no);
		vo.setI_no(i_no);
		vo.setB_stock(b_stock);
		
		System.out.println("��ٱ����� ȸ����ȣ : "+vo.getMem_no());
		System.out.println("��ٱ�����  ��ǰ��ȣ"+vo.getI_no());
		
		basket.addBasket(vo);
		System.out.println("��ٱ��� �Է�");
		ModelAndView mav = new ModelAndView("redirect:basketList");
		return mav;
	}
	
	// �α��� �� ȸ���� ��ٱ��� ����Ʈ ��� �޼���
	@GetMapping(value = "/basketList")
	public ModelAndView getBasketList(BasketVO vo, SearchVO svo, HttpSession session){
		ModelAndView mav = new ModelAndView("basket/basketList");
		int mem_no = (int) session.getAttribute("sessionNo");
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("mem_no", mem_no);
		
		totalRecord = basket.getCnt(map);
		System.out.println("��ٱ���totalRecord : " + totalRecord);
		totalPage = (int) Math.ceil(totalRecord / (double) numPerPage);
		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
		if (svo.getSearchreset().equals("1")) {
			nowPage = Integer.parseInt(svo.getcPage());
		}else {
			nowPage = Integer.parseInt(svo.getcPage());
		}
		System.out.println("sessionNo : " + session.getAttribute("sessionNo"));
		beginPerPage = (nowPage - 1) * numPerPage + 1;
		endPerPage = (beginPerPage - 1) + numPerPage;
		
		map.put("beginPerPage", beginPerPage);
		map.put("endPerPage", endPerPage);
		
		svo.setBeginPerPage(beginPerPage);
		svo.setEndPerPage(endPerPage);
		
		List<BasketVO> list = basket.getBasketList(map);
		int startPage = (int) ((nowPage - 1) / pagePerBlock) * pagePerBlock + 1;
		int endPage = startPage + pagePerBlock - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		for(BasketVO e : list) {
			System.out.println(e);
		}
		mav.addObject("list", list);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("nowPage", nowPage);
		mav.addObject("pagePerBlock", pagePerBlock);
		mav.addObject("totalPage", totalPage);
		
		return mav;
	}
	
	// ��ٱ��� ����Ʈ���� �󼼺���
	@GetMapping(value = "/basketDetail")
	public ModelAndView getBasketDetail(BasketVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int mem_no = (int) session.getAttribute("sessionNo");
		mav.addObject("basketdetail", basket.getBasketDetail(vo.getB_num()));
		System.out.println(mem_no);
		System.out.println(vo.getB_num());
		mav.setViewName("basket/basketDetail");
		return mav;
	}
	
	// ��ٱ��� ���� �����ϴ� �޼���
	@PostMapping(value = "/basketupdate")
	public String updateBasket(BasketVO bvo, int b_stock) {
		bvo.setB_stock(b_stock);
		basket.updateBasket(bvo);
		System.out.println("����� ������Ʈ ��Ʈ�ѷ�");
		return "redirect:basketList";
	}
	
	// ��ٱ��� ��� �����ϴ� �޼���
	@PostMapping(value = "/basketdel")
	public String delBasket(int b_num, HttpSession session) {
		basket.delBasket(b_num);
		return "redirect:basketList";
	}
	
	// ��ٱ��Ͽ��� �����ϴ� �޼���
	@PostMapping(value = "/basketorder")
	public ModelAndView ordersForm(BasketVO bvo) {
		ModelAndView mav = new ModelAndView("basket/basketordersForm");
		basket.updateBasket(bvo);
		bvo = basket.getBasketDetail(bvo.getB_num());
		System.out.println("B_stock"+bvo.getB_stock());
		mav.addObject("detail", bvo);
		mav.addObject("totalPrice", bvo.getOrdersvo().getTotalPrice());
		System.out.println("����� ��ٱ��� ���� ��Ʈ�ѷ� : "+bvo.getB_num());
		
		return mav;
	}

	// ��ٱ��Ͽ��� �ֹ� ��ư Ŭ����
	@PostMapping(value = "/basketorderIn")
	public ModelAndView addOrders(BasketVO bvo, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/web/main");
		int mem_no = (int) session.getAttribute("sessionNo");
		int b_num = bvo.getB_num();
		bvo.setMem_no(mem_no);
		System.out.println("basketorder mem_no=>"+mem_no);
		basketOrderService.deleteServiceBasket(bvo, b_num);
		return mav;
	}
}
