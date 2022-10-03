package kr.co.bteam.mvc.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bteam.mvc.vo.MemberLogVO;
import kr.co.bteam.mvc.vo.MemberVO;

//Dao단은 @Repository 사용 => Dao를 Bean으로 등록시켜줌
@Repository
public class MemberDao implements MemberDaoInter{
	
	

	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void addMember(MemberVO dto) {
		ss.insert("ptj_member.add", dto);
	}
	
	@Override
	public int idcheck(String mem_id) {
		return ss.selectOne("ptj_member.idChk",mem_id);
	}

	@Override
	public int emailcheck(String mem_email) {
		return ss.selectOne("ptj_member.emailChk",mem_email);
	}

	@Override
	public MemberVO memberInfo(int mem_no) {
		return ss.selectOne("ptj_member.info",mem_no);
	}

	@Override
	public void memberUpdate(MemberVO vo) {
		ss.update("ptj_member.memberupdate",vo);
		
	}

	@Override
	public List<MemberVO> getChartList() {
		// TODO Auto-generated method stub
		 return ss.selectList("ptj_member.userlist");
	}

	@Override
	public MemberVO getChart(MemberVO vo) {
		// 구현 안함
		return null;
	}







}
