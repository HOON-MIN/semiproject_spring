package kr.co.bteam.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bteam.mvc.vo.MemberLogVO;
import kr.co.bteam.mvc.vo.MemberVO;

@Repository
public class LoginDao implements LoginDaoInter{
	

	@Autowired
	private SqlSessionTemplate ss;
		
	@Override
	public MemberVO loginCheck(MemberVO dto) {
		
		return ss.selectOne("ptj_member.login",dto);
	
	}

	@Override
	public String gender(String id) {
		return ss.selectOne("ptj_member.gender",id);
	}
	
	@Override
	public String findId(MemberVO dto) {
		return ss.selectOne("ptj_member.findId",dto);
	}
	
	@Override
	public String findPwd(MemberVO dto) {
		return ss.selectOne("ptj_member.findPwd",dto);
	}
	
	@Override
	public void addLoginLogging(MemberLogVO lvo) {
		ss.insert("ptj_member.logger_in",lvo);
	}

	@Override
	public List<MemberLogVO> logList() {
		List<MemberLogVO> list = ss.selectList("ptj_member.logList");
		return list;
	}

	@Override
	public MemberVO myLogList(String mem_id) {
		return ss.selectOne("ptj_member.myloglist",mem_id);
	}

	


}
