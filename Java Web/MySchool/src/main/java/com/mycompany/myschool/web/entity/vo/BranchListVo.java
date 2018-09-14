package com.mycompany.myschool.web.entity.vo;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class BranchListVo {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "sb_id")
	private String sb_id;
	@XmlElement(name = "sb_name")
	private String sb_name;
	@XmlElement(name = "sb_type")
	private String sb_type;
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
	public String getSb_type() {
		return sb_type;
	}
	public void setSb_type(String sb_type) {
		this.sb_type = sb_type;
	}
	public void setRcmmd_type(String rcmmd_type) {
		this.rcmmd_type = rcmmd_type;
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
