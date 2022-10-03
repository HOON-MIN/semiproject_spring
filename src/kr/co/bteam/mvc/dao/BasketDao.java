package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bteam.mvc.vo.BasketVO;
import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.SearchVO;

@Repository("basket")
public class BasketDao implements BasketDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void addBasket(BasketVO vo) {
		ss.insert("jhy_basket.add", vo);
	}

	@Override
	public List<BasketVO> getBasketList(Map<String, Integer> map) {
		return ss.selectList("jhy_basket.basketlist", map);
	}

	@Override
	public BasketVO getBasketDetail(int b_num) {
		return ss.selectOne("jhy_basket.basketdetail", b_num);
	}

	@Override
	public void updateBasket(BasketVO vo) {
		ss.update("jhy_basket.update", vo);
	}

	@Override
	public void delBasket(int b_num) {
		ss.delete("jhy_basket.del", b_num);
	}

	@Override
	public int getCnt(Map<String, Integer> map) {
		int cnt = ss.selectOne("jhy_basket.totalCount", map);
		return cnt;
	}

	@Override
	public void addBasketOrder(BasketVO bvo) {
		ss.insert("jhy_basket.addorder", bvo);
	}

}
