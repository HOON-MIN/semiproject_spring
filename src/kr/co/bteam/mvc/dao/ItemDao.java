package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;
import kr.co.bteam.mvc.vo.UserVO;

@Repository("item")
public class ItemDao implements ItemDaoInter{

	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public int getCnt(SearchVO svo) {
		int cnt = ss.selectOne("jhy_item.totalCount", svo);
		return cnt;
	}
	
	@Override
	public List<? extends SuperDTO> getItemList(SearchVO vo) {
		return ss.selectList("jhy_item.list", vo);
	}
	
	@Override
	public List<? extends SuperDTO> getCategoryList(SearchVO vo) {
		return ss.selectList("jhy_item.category", vo);
	}

	@Override
	public ItemVO getDetail(int num) {
		return ss.selectOne("jhy_item.detail", num);
	}

	@Override
	public ItemVO getCategoryDetail(int num) {
		return ss.selectOne("jhy_item.detail", num);
	}

	
}
