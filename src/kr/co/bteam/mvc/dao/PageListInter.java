package kr.co.bteam.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

public interface PageListInter {
	public List<? extends SuperDTO> getList(Map<String, Integer> map);
}
