package kr.co.bteam.mvc.dao;

import java.util.List;

import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

public interface AdminMemberDaoInter extends AdminListcountInter {
	public int getAdminCnt(SearchVO vo);// memberList ����ϱ����� �� member �� ��������
	public MemberVO getDetail(int num);// member ������ ������
	public void upMember(MemberVO vo); // member ���� ����
	public void delMember(int num); // member ����
	public List<OrdersVO> getRankMem(); // ranking AOP
	public List<? extends SuperDTO> getList(SearchVO vo);
}
