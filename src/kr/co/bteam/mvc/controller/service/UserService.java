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
	
	
		//ȸ������
		@Override
		public void addMember(MemberVO dto) {
			memberDaointer.addMember(dto);		
		}
		
		//���̵��ߺ�üũ
		@Override
		public int idCheck(String mem_id) {
			int cnt = memberDaointer.idcheck(mem_id);
			return cnt;
		}
		
		//�̸����ߺ�üũ
		@Override
		public int emailCheck(String mem_email) {
			int mcnt = memberDaointer.emailcheck(mem_email);
			return mcnt;
		}
		
		
		
		//�α��� �� ���� ���
		@Override
		public String gender(String mem_id) {
			return loginDaointer.gender(mem_id);
		}
		
		//�α���
		@Override
		public MemberVO loginCheck(MemberVO dto) {
			return loginDaointer.loginCheck(dto);
		}
		
		//���̵� ã�� 
		@Override
		public String findId(MemberVO dto) {
			return loginDaointer.findId(dto);
		}

		//��й�ȣ ã��
		@Override
		public String findPwd(MemberVO dto) {
			return loginDaointer.findPwd(dto);
			
		}
		
		//�α��ηα׳����
		@Override
		public void addLoginLogging(MemberLogVO lvo) {
			loginDaointer.addLoginLogging(lvo);
		}

		//�α��ηα� ����Ʈ
		@Override
		public List<MemberLogVO> logList() {
			return loginDaointer.logList();
		}

		@Override
		public MemberVO myLogList(String mem_id) {
			return loginDaointer.myLogList(mem_id);
		}

	


}
