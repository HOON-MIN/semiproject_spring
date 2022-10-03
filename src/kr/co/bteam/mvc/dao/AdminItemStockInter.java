package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.StockVO;
import kr.co.bteam.mvc.vo.SuperDTO;

public interface AdminItemStockInter extends AdminListcountInter {
	// item,stock �űԵ��
	public void addItem(ItemVO ivo);
	public void addStock(StockVO svo);
	// ��ǰ����Ʈ ����¡ �޼���
//	public int getTotalCnt(SearchVO vo);
	public List<? extends SuperDTO> getList(SearchVO vo);
	// ��ǰ ������ �޼���
	public ItemVO detailItemstock(int num);
	// ��ǰ �����ϱ�
	public void updateItem(ItemVO vo);
	public void updateStock(StockVO vo);
	
	// ��ǰ �����ϱ�
	public void deleteItem(int num);
	public void deleteStock(int num);
	
	// ��ǰ�� �ߺ�üũ
	public int itemNameChk(Map<String, String> map);
}
