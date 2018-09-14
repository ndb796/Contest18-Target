package com.mycompany.myschool.web.entity.base;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserEntity extends BaseEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "user_id")
	private String user_id;
	@XmlElement(name = "email")
	private String email;
	@XmlElement(name = "pwd")
	private String pwd;
	@XmlElement(name = "join_type")
	private int join_type;
	@XmlElement(name = "mem_type")
	private int mem_type;
	@XmlElement(name = "reg_status")
	private String reg_status;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date upd_date;
	@XmlElement(name = "upd_date_str")
	private String upd_date_str;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date last_login_date;
	
	private String[] userIdArray;
	
	public String[] getUserIdArray() {
		return userIdArray;
	}
	public void setUserIdArray(String[] userIdArray) {
		this.userIdArray = userIdArray;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getJoin_type() {
		return join_type;
	}
	public void setJoin_type(int join_type) {
		this.join_type = join_type;
	}
	public int getMem_type() {
		return mem_type;
	}
	public void setMem_type(int mem_type) {
		this.mem_type = mem_type;
	}
	public String getReg_status() {
		return reg_status;
	}
	public void setReg_status(String reg_status) {
		this.reg_status = reg_status;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	public Date getLast_login_date() {
		return last_login_date;
	}
	public void setLast_login_date(Date last_login_date) {
		this.last_login_date = last_login_date;
	}
	public String getUpd_date_str() {
		return upd_date_str;
	}
	public void setUpd_date_str(String upd_date_str) {
		this.upd_date_str = upd_date_str;
	}
	
}
