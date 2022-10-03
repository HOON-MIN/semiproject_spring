package kr.co.bteam.mvc.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.mvc.dao.LoginDaoInter;
import kr.co.bteam.mvc.dao.MemberDaoInter;
import kr.co.bteam.mvc.vo.MemberVO;
import kr.co.bteam.mvc.vo.MemberLogVO;

@Service
public class UserService implements UserServiceInter {
	
	

	@Autowired
	private MemberDaoInter memberDaointer;
	
	@Autowired
	private LoginDaoInter loginDaointer;
	
	
		//회원가입
		@Override
		public void addMember(MemberVO dto) {
			memberDaointer.addMember(dto);		
		}
		
		//아이디중복체크
		@Override
		public int idCheck(String mem_id) {
			int cnt = memberDaointer.idcheck(mem_id);
			return cnt;
		}
		
		//이메일중복체크
		@Override
		public int emailCheck(String mem_email) {
			int mcnt = memberDaointer.emailcheck(mem_email);
			return mcnt;
		}
		
		
		
		//로그인 시 성별 출력
		@Override
		public String gender(String mem_id) {
			return loginDaointer.gender(mem_id);
		}
		
		//로그인
		@Override
		public MemberVO loginCheck(MemberVO dto) {
			return loginDaointer.loginCheck(dto);
		}
		
		//아이디 찾기 
		@Override
		public String findId(MemberVO dto) {
			return loginDaointer.findId(dto);
		}

		//비밀번호 찾기
		@Override
		public String findPwd(MemberVO dto) {
			return loginDaointer.findPwd(dto);
			
		}
		
		//로그인로그남기기
		@Override
		public void addLoginLogging(MemberLogVO lvo) {
			loginDaointer.addLoginLogging(lvo);
		}

		//로그인로그 리스트
		@Override
		public List<MemberLogVO> logList() {
			return loginDaointer.logList();
		}

		@Override
		public MemberVO myLogList(String mem_id) {
			return loginDaointer.myLogList(mem_id);
		}

	


}
