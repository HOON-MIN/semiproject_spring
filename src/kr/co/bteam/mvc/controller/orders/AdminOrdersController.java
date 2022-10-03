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
	
	private int nowPage = 1;// ���� ������ ��
	private int nowBlock = 1;// ���� ��
	private int totalRecord = 0;// �� �Խù� ��
	private int numPerPage = 5;// �� �������� ������ �Խù� ��
	private int pagePerBlock = 5;// �� ���� ������ ������ ��
	private int totalPage = 0;// ��ü ������ �� => totalRecord/numPerPage
	private int totalBlock = 0;// ��ü �� ��
	private int beginPerPage = 0;// �� �������� ���� �Խù��� index��
	private int endPerPage = 0;// �� �������� ������ �Խù��� index��
	
	// ������ȸ�� ���Ÿ���Ʈ 
		@RequestMapping(value = "/adminordersList")
		public ModelAndView adminOrdersList(SearchVO svo, ModelAndView mav, HttpSession session ,MemberVO mvo ) {
			mav.setViewName("adminorders/adminordersList");
//			int mem_no = (String) session.getAttribute("sessionNo");
//			session.setAttribute("sessionId", mvo.getMem_id());
//			session.setAttribute("sessionNo", mvo.getMem_no());
			System.out.println("��Ʈ�ѷ� cnt�� search :"+svo.getSearch());
			System.out.println("��Ʈ�ѷ� cnt�� category :"+svo.getCategory());
			// aop���� cnt�� �޾ƿ���
			
			totalRecord = (int) mav.getModel().get("cnt");
			System.out.println("��Ʈ�ѷ� ������ �ֹ��� : "+totalRecord);
			totalPage = (int) Math.ceil(totalRecord / (double) numPerPage);
			totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
			
		
			if (svo.getSearchreset().equals("1")) {
				System.out.println("cPage ������ ���� =>"+ svo.getcPage());
				nowPage = Integer.parseInt(svo.getcPage());
			}else {
				System.out.println("cPage ������ ������ ��ȣ ���ý� =>"+svo.getcPage());
				nowPage = Integer.parseInt(svo.getcPage());
			}
					
			beginPerPage = (nowPage - 1) * numPerPage + 1;
			endPerPage = (beginPerPage - 1) + numPerPage;
			System.out.println("��Ʈ�ѷ� cnt�� search :"+svo.getSearch());
			System.out.println("��Ʈ�ѷ� cnt�� category :"+svo.getCategory());			
			svo.setBeginPerPage(beginPerPage);
			svo.setEndPerPage(endPerPage);
//			svo.setCategory("�����");
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
		
		// ������ �ֹ� ������ ������
		@GetMapping(value = "adminDetailForm")
		public ModelAndView adminOrdersDetail(OrdersVO ovo) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("vo", adminOrdersStockService.detailAdminServiceOrders(ovo.getOrd_no()));
			mav.setViewName("adminorders/adminOrdersDetail");
			return mav;
		}
		
		// �ֹ� ���� �� ������� �ڵ� ����
		@PostMapping(value = "/adminordersUpdate")
		public ModelAndView adminorersUpdate(OrdersVO ovo) {
			ModelAndView mav = new ModelAndView();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("���� ��Ʈ�ѷ� �ֹ����� : "+ovo.getI_status());
						if (ovo.getI_status().equals("�ֹ����")) {
							adminOrdersStockService.updateAdminServiceOrders(ovo);
						}else {
							adminOrdersStockService.updateAdminServiceOrders(ovo);
							Thread.sleep(5000);
							ovo.setI_status("�����");
							adminOrdersStockService.updateAdminServiceOrders(ovo);
							Thread.sleep(5000);
							ovo.setI_status("��ۿϷ�");
							// ���䱸��
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
				System.out.println("��Ʈ�ѷ� ���� �ֹ���ȣ : " + ovo.getOrd_no());
				adminOrdersStockService.deleteAdminServiceOrders(ovo.getOrd_no());
				mav.setViewName("redirect:adminordersList");
			}else {
				mav.setViewName("error/paramException");
				mav.addObject("emsg", "��ۻ��¸� �ֹ���ҷ� �������ּ���~!");
			}
			
			return mav;
		}
		
	
	
}
