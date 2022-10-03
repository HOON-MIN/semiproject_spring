package kr.co.bteam.mvc.dao;

import java.util.List;

import kr.co.bteam.mvc.vo.MemberVO;

public interface MemberDaoInter{
	public void addMember(MemberVO dto);
	public int idcheck(String mem_id);
	public int emailcheck(String mem_email);
	public MemberVO memberInfo(int mem_no); //박태진 마이페이지
	public void memberUpdate(MemberVO vo);//박태진 마이페이지 - 수정
	public List<MemberVO> getChartList();//박태진 사용자 기록 전체 리스트
	public MemberVO getChart(MemberVO vo);//박태진 사용자 개인 기록
}
