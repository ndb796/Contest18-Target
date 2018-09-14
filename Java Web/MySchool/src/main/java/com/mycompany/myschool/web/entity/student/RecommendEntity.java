package com.mycompany.myschool.web.entity.student;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class RecommendEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "rcmmd_id")
	private String rcmmd_id;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "sb_id")
	private String sb_id;
	@XmlElement(name = "rcmmd_type")
	private String rcmmd_type;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date ins_date;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date upd_date;
	
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
	public String getRcmmd_id() {
		return rcmmd_id;
	}
	public void setRcmmd_id(String rcmmd_id) {
		this.rcmmd_id = rcmmd_id;
	}
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getSb_id() {
		return sb_id;
	}
	public void setSb_id(String sb_id) {
		this.sb_id = sb_id;
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
	public String getRcmmd_type() {
		return rcmmd_type;
	}
	public void setRcmmd_type(String rcmmd_type) {
		this.rcmmd_type = rcmmd_type;
	}
}
