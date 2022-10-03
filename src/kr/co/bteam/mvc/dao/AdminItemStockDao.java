package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.StockVO;
import kr.co.bteam.mvc.vo.SuperDTO;

@Repository("adminitemstockList")
public class AdminItemStockDao implements AdminItemStockInter {
	
	@Autowired
	private SqlSessionTemplate ss;
	
	// item,stock 신규등록
	@Override
	public void addItem(ItemVO ivo) {
		ss.insert("ybh_itemStock.additem", ivo);
	}
	@Override
	public void addStock(StockVO svo) {
		ss.insert("ybh_itemStock.addstock", svo);
	}
	
	// 총 상품수 / 페이징 처리 메서드 
	
	public int getTotalCnt(SearchVO vo) {
		System.out.println("여기는 total dao");
		System.out.println("category : "+vo.getCategory());
		System.out.println("search : "+vo.getSearch());
		return ss.selectOne("ybh_itemStock.totalCount", vo);
	}
	
	@Override
	public List<? extends SuperDTO> getList(SearchVO vo) {
		System.out.println("여기는 dao");
		return ss.selectList("ybh_itemStock.listpage", vo);
	}
	// 상품 detail보기
	@Override
	public ItemVO detailItemstock(int num) {
		return ss.selectOne("ybh_itemStock.idetail", num);
	}
	// 상품 수정하기
	@Override
	public void updateItem(ItemVO vo) {
		ss.update("ybh_itemStock.updateitem", vo);
	}
	@Override
	public void updateStock(StockVO vo) {
		ss.update("ybh_itemStock.updateitemstock", vo);
		
	}
	
	@Override
	public void deleteItem(int num) {
		ss.delete("ybh_itemStock.deleteitem", num);
	}
	@Override
	public void deleteStock(int num) {
		ss.delete("ybh_itemStock.deletestock", num);
		
	}
	// aop TotalCount
	@Override
	public int getAllItemcount(SearchVO vo) {
		System.out.println("여기는 advice용 total dao");
		return ss.selectOne("ybh_itemStock.totalCount", vo);
	}
	// itemNameChk 중복체크
	@Override
	public int itemNameChk(Map<String, String> map) {
		return ss.selectOne("ybh_itemStock.itemNameChk", map);
	}
	


}
