package com.mycompany.myschool.web.entity.vo;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class FavoriteListVo {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "sb_id")
	private String sb_id;
	@XmlElement(name = "sb_name")
	private String sb_name;
	@XmlElement(name = "school_name")
	private String school_name;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "student_name")
	private String student_name;
	@XmlElement(name = "location")
	private String location;
	@XmlElement(name = "cur_school")
	private String cur_school;
	@XmlElement(name = "school_type")
	private String school_type;
	@XmlElement(name = "school_category")
	private String school_category;
	@XmlElement(name = "address")
	private String address;
	@XmlElement(name = "rcmmd_type")
	private String rcmmd_type;
	@XmlElement(name = "const_status")
	private int const_status;
	@XmlElement(name = "favr_cnt")
	private int favr_cnt;
	@XmlElement(name = "const_status_msg")
	private String const_status_msg;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date ins_date;
	
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
	public String getSb_id() {
		return sb_id;
	}
	public void setSb_id(String sb_id) {
		this.sb_id = sb_id;
	}
	public String getSb_name() {
		return sb_name;
	}
	public void setSb_name(String sb_name) {
		this.sb_name = sb_name;
	}
	
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_type() {
		return school_type;
	}
	public void setSchool_type(String school_type) {
		this.school_type = school_type;
	}
	public String getRcmmd_type() {
		return rcmmd_type;
	}
	public void setRcmmd_type(String rcmmd_type) {
		this.rcmmd_type = rcmmd_type;
	}
	public int getConst_status() {
		return const_status;
	}
	public void setConst_status(int const_status) {
		this.const_status = const_status;
	}
	public Date getIns_date() {
		return ins_date;
	}
	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getConst_status_msg() {
		return const_status_msg;
	}
	public void setConst_status_msg(String const_status_msg) {
		this.const_status_msg = const_status_msg;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCur_school() {
		return cur_school;
	}
	public void setCur_school(String cur_school) {
		this.cur_school = cur_school;
	}
	public String getSchool_category() {
		return school_category;
	}
	public void setSchool_category(String school_category) {
		this.school_category = school_category;
	}
	public int getFavr_cnt() {
		return favr_cnt;
	}
	public void setFavr_cnt(int favr_cnt) {
		this.favr_cnt = favr_cnt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
