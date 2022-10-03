package kr.co.bteam.mvc.vo;

import java.util.List;

public class MemberVO {
	private int mem_no, r_num;
	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_jubun;
	private String mem_email;
	private String mem_adr;
	private String mem_date;
	private String mem_phone;
	private String mem_q;
	private String mem_a;
	private String mem_gender;
	private List<MemberLogVO> memberlog;
	private List<UserVO> uvo;

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public String getMem_gender() {
		return mem_gender;
	}

	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}

	public List<UserVO> getUvo() {
		return uvo;
	}

	public void setUvo(List<UserVO> uvo) {
		this.uvo = uvo;
	}

	public List<MemberLogVO> getMemberlog() {
		return memberlog;
	}

	public void setMemberlog(List<MemberLogVO> memberlog) {
		this.memberlog = memberlog;
	}

	public String getMem_q() {
		return mem_q;
	}

	public void setMem_q(String mem_q) {
		this.mem_q = mem_q;
	}

	public String getMem_a() {
		return mem_a;
	}

	public void setMem_a(String mem_a) {
		this.mem_a = mem_a;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_jubun() {
		return mem_jubun;
	}

	public void setMem_jubun(String mem_jubun) {
		this.mem_jubun = mem_jubun;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_adr() {
		return mem_adr;
	}

	public void setMem_adr(String mem_adr) {
		this.mem_adr = mem_adr;
	}

	public String getMem_date() {
		return mem_date;
	}

	public void setMem_date(String mem_date) {
		this.mem_date = mem_date;
	}
}
