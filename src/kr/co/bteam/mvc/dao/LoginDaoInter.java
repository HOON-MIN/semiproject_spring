package kr.co.bteam.mvc.dao;

import java.util.List;

import kr.co.bteam.mvc.vo.MemberLogVO;
import kr.co.bteam.mvc.vo.MemberVO;

public interface LoginDaoInter {
	public MemberVO loginCheck(MemberVO dto);
	public String gender(String id);
	public String findId(MemberVO dto);
	public String findPwd(MemberVO dto);
	public void addLoginLogging(MemberLogVO lvo);
	public List<MemberLogVO> logList();
	public MemberVO myLogList(String mem_id);
}
