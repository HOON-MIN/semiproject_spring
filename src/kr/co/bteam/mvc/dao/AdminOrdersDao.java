package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

@Repository("adminOrdersList")
public class AdminOrdersDao implements AdminOrdersDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	
	// 관리자용 총totalnum반환
//	@Override
	public int getAdminCnt(SearchVO svo) {
		int cnt = ss.selectOne("ybh_orders.adminTotoalCount", svo);
		System.out.println("cnt 관리자 반환값 : "+cnt);
		return cnt;
	}
	// 관리자 주문 list
	@Override
	public List<? extends SuperDTO> getAdminList(SearchVO svo) {
		System.out.println("dao category : "+svo.getCategory());
		System.out.println("dao search : "+svo.getSearch());
		return ss.selectList("ybh_orders.adminlistpage", svo);
	}
	// 관리자 상세보기
	@Override
	public OrdersVO detailAdminOrders(int num) {
		System.out.println("dao 주문 번호 : "+num);
		return ss.selectOne("ybh_orders.adminDetailOrdes", num);
	}
	// 관리자 수정
	@Override
	public void updateAdminOrders(OrdersVO ovo) {
		ss.update("ybh_orders.updateOrders", ovo);
	}
	
	// 관리자 주문 삭제
	@Override
	public void deleteAdminOrders(int ord_no) {
		ss.delete("ybh_orders.deleteOrders", ord_no);
	}
	// 관리자 주문 삭제 체크(배송상태가 주문취소일때만)
	@Override
	public int deleteAdminOrdersCheck(int ord_no) {
		System.out.println("dao 주문번호 : "+ord_no);
		return ss.selectOne("ybh_orders.deleteCheck", ord_no);
	}

	@Override
	public int getAllItemcount(SearchVO svo) {
		int cnt = ss.selectOne("ybh_orders.adminTotoalCount", svo);
		System.out.println("cnt advice dao 관리자 반환값 : "+cnt);
		return cnt;
	}


	
	

}
