package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

public interface AdminOrdersDaoInter extends AdminListcountInter {
	// �����ڿ� �ֹ����� ����¡ �޼���
//	public int getAdminCnt(SearchVO svo);
	public List<? extends SuperDTO> getAdminList(SearchVO svo);
	// �ֹ����� ��������
	public OrdersVO detailAdminOrders(int num);
	
	// �ֹ� ����
	public void updateAdminOrders(OrdersVO ovo);
	
	// �ֹ� ����
	public void deleteAdminOrders(int ord_no);
	
	// �ֹ���� ���¿����� ������ ����
	public int deleteAdminOrdersCheck(int ord_no);
}
