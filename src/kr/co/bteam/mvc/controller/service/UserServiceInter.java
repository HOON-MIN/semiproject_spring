package kr.co.bteam.mvc.controller.service;

import java.util.List;

import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.MemberLogVO;

public interface UserServiceInter {
	  // 아이디 중복확인
    public int idCheck(String id);
    public void addMember(MemberVO dto);
    public MemberVO loginCheck(MemberVO dto);
    public String gender(String id);
    public int emailCheck(String mem_email);
    public String findId(MemberVO dto);
	public String findPwd(MemberVO dto);
	public void addLoginLogging(MemberLogVO lvo);
	public List<MemberLogVO> logList();
	public MemberVO myLogList(String mem_id);
}
