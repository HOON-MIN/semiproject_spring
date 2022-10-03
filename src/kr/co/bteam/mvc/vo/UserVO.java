package kr.co.bteam.mvc.vo;

public class UserVO {
	private int user_no,mem_no,total;
	private String user_gender,mem_jubun,mgender,wgender,cgender;
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	
	public String getMgender() {
		return mgender;
	}
	public void setMgender(String mgender) {
		this.mgender = mgender;
	}
	public String getWgender() {
		return wgender;
	}
	public void setWgender(String wgender) {
		this.wgender = wgender;
	}
	public String getCgender() {
		return cgender;
	}
	public void setCgender(String cgender) {
		this.cgender = cgender;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getMem_jubun() {
		return mem_jubun;
	}
	public void setMem_jubun(String mem_jubun) {
		this.mem_jubun = mem_jubun;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
