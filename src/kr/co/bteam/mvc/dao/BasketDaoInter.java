package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.bteam.mvc.vo.BasketVO;
import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.SearchVO;

public interface BasketDaoInter {
	public void addBasket(BasketVO vo);
	public List<BasketVO> getBasketList(Map<String, Integer> map);
	public BasketVO getBasketDetail(int b_num);
	public void updateBasket(BasketVO vo);
	public void delBasket(int b_num);
	public int getCnt(Map<String, Integer> map);
	public void addBasketOrder(BasketVO bvo);
}
