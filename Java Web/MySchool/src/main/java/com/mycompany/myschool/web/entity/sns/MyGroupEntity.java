package com.mycompany.myschool.web.entity.sns;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MyGroupEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "group_id")
	private String group_id;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "mystudent_id")
	private String mystudent_id;
	@XmlElement(name = "upd_date")
	private Date upd_date;
	@XmlElement(name = "grp_status")
	private int grp_status;
	
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
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getMystudent_id() {
		return mystudent_id;
	}
	public void setMystudent_id(String mystudent_id) {
		this.mystudent_id = mystudent_id;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	public int getGrp_status() {
		return grp_status;
	}
	public void setGrp_status(int grp_status) {
		this.grp_status = grp_status;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	
}
