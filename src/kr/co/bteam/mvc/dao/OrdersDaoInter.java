package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;

public interface OrdersDaoInter {

	public int getCnt(MemberVO mvo);
	public void addOrders(OrdersVO vo);
	public List<OrdersVO> getList(Map<String, Integer> map);
	

	// 판매 top3 item : 최미르
	public List<OrdersVO> getRankOrdersList();	
	
	// 리뷰 카운터 관련
	public void updateRcnt(int ord_no);
}
