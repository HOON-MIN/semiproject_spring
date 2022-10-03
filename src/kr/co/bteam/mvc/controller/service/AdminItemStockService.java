package kr.co.bteam.mvc.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import kr.co.bteam.mvc.dao.AdminItemStockDao;
import kr.co.bteam.mvc.vo.ItemVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.StockVO;
import kr.co.bteam.mvc.vo.SuperDTO;

@Service
public class AdminItemStockService {
	
	@Autowired
	private AdminItemStockDao itemStockDao;
	
	// item,stock등록 트랜잭션 구현
	@Transactional
	public void addServiceItemStock(ItemVO ivo, StockVO svo) {
		itemStockDao.addItem(ivo);
		System.out.println("service영역 : "+ivo.getI_name());
		itemStockDao.addStock(svo);
	}
	
	public int getTotalCntService(SearchVO vo) {
		return itemStockDao.getTotalCnt(vo);
	}
	
	public List<? extends SuperDTO> getList(SearchVO vo) {
		return itemStockDao.getList(vo);
	}
	
	public ItemVO detailItemstock(int num) {
		return itemStockDao.detailItemstock(num);
	}
	
	@Transactional
	public void updateServiceItemStock( ItemVO ivo, StockVO svo) {
		itemStockDao.updateItem(ivo);
		itemStockDao.updateStock(svo);
	}
	
	@Transactional
	public void deleteServiceItemstock(int num) {
		itemStockDao.deleteItem(num);
		itemStockDao.deleteStock(num);
		
	}
}
