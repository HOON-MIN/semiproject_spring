package kr.co.bteam.mvc.vo;

import java.util.List;

public class ReviewsVO {
	private int r_no, ord_no, mem_no, i_no, r_score,r_num;
	private String r_name, r_comm, r_date;
	private OrdersVO ordersvo;
	private ItemVO itemvo;
	private List<MemberVO> mvo;
	
	
	
	
	public List<MemberVO> getMvo() {
		return mvo;
	}
	public void setMvo(List<MemberVO> mvo) {
		this.mvo = mvo;
	}
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	
	public OrdersVO getOrdersvo() {
		return ordersvo;
	}
	public void setOrdersvo(OrdersVO ordersvo) {
		this.ordersvo = ordersvo;
	}
	public ItemVO getItemvo() {
		return itemvo;
	}
	public void setItemvo(ItemVO itemvo) {
		this.itemvo = itemvo;
	}
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public int getOrd_no() {
		return ord_no;
	}
	public void setOrd_no(int ord_no) {
		this.ord_no = ord_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public int getI_no() {
		return i_no;
	}
	public void setI_no(int i_no) {
		this.i_no = i_no;
	}
	public int getR_score() {
		return r_score;
	}
	public void setR_score(int r_score) {
		this.r_score = r_score;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getR_comm() {
		return r_comm;
	}
	public void setR_comm(String r_comm) {
		this.r_comm = r_comm;
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
}
