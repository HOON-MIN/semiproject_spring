package kr.co.bteam.mvc.dao;

import java.util.List;

import kr.co.bteam.mvc.vo.MemberVO;

public interface MemberDaoInter{
	public void addMember(MemberVO dto);
	public int idcheck(String mem_id);
	public int emailcheck(String mem_email);
	public MemberVO memberInfo(int mem_no); //������ ����������
	public void memberUpdate(MemberVO vo);//������ ���������� - ����
	public List<MemberVO> getChartList();//������ ����� ��� ��ü ����Ʈ
	public MemberVO getChart(MemberVO vo);//������ ����� ���� ���
}
