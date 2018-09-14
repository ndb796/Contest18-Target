package com.mycompany.myschool.web.entity.customer;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSub2Entity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "school_id")
	private String school_id;
	@XmlElement(name = "chr_fname")
	private String chr_fname;
	@XmlElement(name = "chr_lname")
	private String chr_lname;
	@XmlElement(name = "chr_dept")
	private String chr_dept;
	@XmlElement(name = "chr_tel")
	private String chr_tel;
	@XmlElement(name = "chr_fax")
	private String chr_fax;
	@XmlElement(name = "chr_fb")
	private String chr_fb;
	@XmlElement(name = "chr_tw")
	private String chr_tw;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date ins_date;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date upd_date;
	@XmlElement(name = "upd_id")
	private String upd_id;
	
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
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	
	public String getChr_fname() {
		return chr_fname;
	}
	public void setChr_fname(String chr_fname) {
		this.chr_fname = chr_fname;
	}
	public String getChr_lname() {
		return chr_lname;
	}
	public void setChr_lname(String chr_lname) {
		this.chr_lname = chr_lname;
	}
	public String getChr_dept() {
		return chr_dept;
	}
	public void setChr_dept(String chr_dept) {
		this.chr_dept = chr_dept;
	}
	public String getChr_tel() {
		return chr_tel;
	}
	public void setChr_tel(String chr_tel) {
		this.chr_tel = chr_tel;
	}
	public String getChr_fax() {
		return chr_fax;
	}
	public void setChr_fax(String chr_fax) {
		this.chr_fax = chr_fax;
	}
	public String getChr_fb() {
		return chr_fb;
	}
	public void setChr_fb(String chr_fb) {
		this.chr_fb = chr_fb;
	}
	public String getChr_tw() {
		return chr_tw;
	}
	public void setChr_tw(String chr_tw) {
		this.chr_tw = chr_tw;
	}
	public Date getIns_date() {
		return ins_date;
	}
	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	public String getUpd_id() {
		return upd_id;
	}
	public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}
}
