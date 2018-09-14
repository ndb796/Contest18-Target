package com.mycompany.myschool.web.entity.vo;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class SchoolListVo {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "school_id")
	private String school_id;
	@XmlElement(name = "school_name")
	private String school_name;
	@XmlElement(name = "school_type")
	private String school_type;
	@XmlElement(name = "school_category")
	private String school_category;
	@XmlElement(name = "logoname")
	private String logoname;
	@XmlElement(name = "favr_cnt")
	private int favr_cnt;
	@XmlElement(name = "rcmmd_cnt")
	private int rcmmd_cnt;
	@XmlElement(name = "rcmmd_type")
	private String rcmmd_type;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlSchemaType(name = "date")
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
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
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
	public String getSchool_category() {
		return school_category;
	}
	public void setSchool_category(String school_category) {
		this.school_category = school_category;
	}
	public String getLogoname() {
		return logoname;
	}
	public void setLogoname(String logoname) {
		this.logoname = logoname;
	}
	public int getFavr_cnt() {
		return favr_cnt;
	}
	public void setFavr_cnt(int favr_cnt) {
		this.favr_cnt = favr_cnt;
	}
	public int getRcmmd_cnt() {
		return rcmmd_cnt;
	}
	public void setRcmmd_cnt(int rcmmd_cnt) {
		this.rcmmd_cnt = rcmmd_cnt;
	}
	public String getRcmmd_type() {
		return rcmmd_type;
	}
	public Date getIns_date() {
		return ins_date;
	}
	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}
}
