package kr.co.bteam.mvc.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import kr.co.bteam.mvc.dao.AdminItemStockDao;
import kr.co.bteam.mvc.dao.AdminOrdersDao;
import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.StockVO;
import kr.co.bteam.mvc.vo.SuperDTO;

@Service
public class AdminOrdersStockService {
	
	@Autowired
	private AdminOrdersDao adminOrdersDao; 
	
	// �����ڿ� ��totalnum��ȯ
	public int getAdminServiceCnt(SearchVO svo) {
		return adminOrdersDao.getAdminCnt(svo);
	}
	// ������ �ֹ� list
	public List<? extends SuperDTO> getAdminServiceList(SearchVO svo) {
		return adminOrdersDao.getAdminList(svo);
	}
	
	// ������ �󼼺���
	public OrdersVO detailAdminServiceOrders(int num) {
		return adminOrdersDao.detailAdminOrders(num);
	}
	
	// �����ϱ�
	@Transactional
	public void updateAdminServiceOrders(OrdersVO ovo) {
		adminOrdersDao.updateAdminOrders(ovo);
	}
	
	// ������ �ֹ� ���� üũ
	public int deleteAdminServiceOrdersCheck(int ord_no) {
		return adminOrdersDao.deleteAdminOrdersCheck(ord_no);
	}
	// ������ �ֹ� ����
	@Transactional
	public void deleteAdminServiceOrders(int ord_no) {
		adminOrdersDao.deleteAdminOrders(ord_no);
	}
	
	

}
