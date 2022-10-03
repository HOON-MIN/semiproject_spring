package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;

@Repository("orderList")
public class OrdersDao implements OrdersDaoInter{

		@Autowired
		private SqlSessionTemplate ss;
		
		
		@Override
		public int getCnt(MemberVO mvo) {
			int cnt = ss.selectOne("cmr_orders.totalCount", mvo);
			System.out.println("cnt "+cnt);
			return cnt;
		}
	
		@Override
		public void addOrders(OrdersVO vo) {
				ss.insert("cmr_orders.add",vo);
		}
	
		@Override
		public List<OrdersVO> getList(Map<String, Integer> map) {
			List<OrdersVO> list = ss.selectList("cmr_orders.listpage", map);
			System.out.println("ordersDao getList : "+list);
			return list;
		}

		// 판매 top3 item
		@Override
		public List<OrdersVO> getRankOrdersList() {
			return ss.selectList("cmr_orders.bestOrder");
		}
		// 리뷰 카운터 관련
		@Override
		public void updateRcnt(int ord_no) {
			ss.update("cmr_orders.updatercnt",ord_no);
			
		}
	
	}


