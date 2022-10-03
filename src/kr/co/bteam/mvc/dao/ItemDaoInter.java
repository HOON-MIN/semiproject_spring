package kr.co.bteam.mvc.dao;

import java.util.List;

import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

public interface ItemDaoInter {
	public List<? extends SuperDTO> getItemList(SearchVO vo);
	public List<? extends SuperDTO> getCategoryList(SearchVO vo);
	public ItemVO getDetail(int num);
	public ItemVO getCategoryDetail(int num);
	public int getCnt(SearchVO svo);
}
