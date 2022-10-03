package kr.co.bteam.mvc.dao;

import java.util.List;

import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.SearchVO;
import kr.co.bteam.mvc.vo.SuperDTO;

public interface AdminMemberDaoInter extends AdminListcountInter {
	public int getAdminCnt(SearchVO vo);// memberList 출력하기위한 총 member 수 가져오기
	public MemberVO getDetail(int num);// member 상세정보 페이지
	public void upMember(MemberVO vo); // member 정보 수정
	public void delMember(int num); // member 삭제
	public List<OrdersVO> getRankMem(); // ranking AOP
	public List<? extends SuperDTO> getList(SearchVO vo);
}
