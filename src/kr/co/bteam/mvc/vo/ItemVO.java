package kr.co.bteam.mvc.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ItemVO{
	private int i_no, i_price, r_num;
	private String i_name, i_category, i_comm, i_img, i_gender;
	private StockVO stockvo;
	private MultipartFile mfile;
	
	
	
	public MultipartFile getMfile() {
		return mfile;
	}
	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	private List<BasketVO> bvo;
	
	public List<BasketVO> getBvo() {
		return bvo;
	}
	public void setBvo(List<BasketVO> bvo) {
		this.bvo = bvo;
	}
	public StockVO getStockvo() {
		return stockvo;
	}
	public void setStockvo(StockVO stockvo) {
		this.stockvo = stockvo;
	}
	public int getI_no() {
		return i_no;
	}
	public void setI_no(int i_no) {
		this.i_no = i_no;
	}
	public int getI_price() {
		return i_price;
	}
	public void setI_price(int i_price) {
		this.i_price = i_price;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
	public String getI_category() {
		return i_category;
	}
	public void setI_category(String i_category) {
		this.i_category = i_category;
	}
	public String getI_comm() {
		return i_comm;
	}
	public void setI_comm(String i_comm) {
		this.i_comm = i_comm;
	}
	public String getI_img() {
		return i_img;
	}
	public void setI_img(String i_img) {
		this.i_img = i_img;
	}
	public String getI_gender() {
		return i_gender;
	}
	public void setI_gender(String i_gender) {
		this.i_gender = i_gender;
	}
}
