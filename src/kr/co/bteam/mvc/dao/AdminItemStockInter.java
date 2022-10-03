package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.StockVO;
import kr.co.bteam.mvc.vo.SuperDTO;

public interface AdminItemStockInter extends AdminListcountInter {
	// item,stock 신규등록
	public void addItem(ItemVO ivo);
	public void addStock(StockVO svo);
	// 상품리스트 페이징 메서드
//	public int getTotalCnt(SearchVO vo);
	public List<? extends SuperDTO> getList(SearchVO vo);
	// 상품 디테일 메서드
	public ItemVO detailItemstock(int num);
	// 상품 수정하기
	public void updateItem(ItemVO vo);
	public void updateStock(StockVO vo);
	
	// 상품 삭제하기
	public void deleteItem(int num);
	public void deleteStock(int num);
	
	// 상품명 중복체크
	public int itemNameChk(Map<String, String> map);
}
