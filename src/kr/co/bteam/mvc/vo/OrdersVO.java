package kr.co.bteam.mvc.vo;

public class OrdersVO {
	private int ord_no, mem_no, i_no, ord_count, totalPrice, r_num, ordCnt, rn,rcnt ;
	private String i_status, ord_address, ord_name, ord_date, ord_edate, ordTotalCnt;

	private ItemVO ivo;
	private MemberVO mvo;

	
	
	public int getRcnt() {
		return rcnt;
	}

	public void setRcnt(int rcnt) {
		this.rcnt = rcnt;
	}

	public ItemVO getIvo() {
		return ivo;
	}

	public void setIvo(ItemVO ivo) {
		this.ivo = ivo;
	}

	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}

	public String getOrdTotalCnt() {
		return ordTotalCnt;
	}

	public void setOrdTotalCnt(String ordTotalCnt) {
		this.ordTotalCnt = ordTotalCnt;
	}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public int getOrdCnt() {
		return ordCnt;
	}

	public void setOrdCnt(int ordCnt) {
		this.ordCnt = ordCnt;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
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

	public int getOrd_count() {
		return ord_count;
	}

	public void setOrd_count(int ord_count) {
		this.ord_count = ord_count;
	}

	public String getI_status() {
		return i_status;
	}

	public void setI_status(String i_status) {
		this.i_status = i_status;
	}

	public String getOrd_address() {
		return ord_address;
	}

	public void setOrd_address(String ord_address) {
		this.ord_address = ord_address;
	}

	public String getOrd_name() {
		return ord_name;
	}

	public void setOrd_name(String ord_name) {
		this.ord_name = ord_name;
	}

	public String getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(String ord_date) {
		this.ord_date = ord_date;
	}

	public String getOrd_edate() {
		return ord_edate;
	}

	public void setOrd_edate(String ord_edate) {
		this.ord_edate = ord_edate;
	}
}
