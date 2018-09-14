package com.mycompany.myschool.web.entity.board;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
public class EventEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "evt_id")
	private String evt_id;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "group_id")
	private String group_id;
	@XmlElement(name = "evt_type")
	private int evt_type;
	@XmlElement(name = "evt_reg")
	private int evt_reg;
	@XmlElement(name = "evt_title")
	private String evt_title;
	@XmlElement(name = "evt_date_s")
	private String evt_date_s;
	@XmlElement(name = "evt_date_e")
	private String evt_date_e;
	@XmlElement(name = "evt_time_s")
	private String evt_time_s;
	@XmlElement(name = "evt_time_e")
	private String evt_time_e;
	@XmlElement(name = "evt_location")
	private String evt_location;
	@XmlElement(name = "evt_contents")
	private String evt_contents;
	@XmlElement(name = "remarks")
	private String remarks;
	@XmlElement(name = "ins_date")
	@XmlSchemaType(name = "date")
	private Date ins_date;
	@XmlElement(name = "upd_date")
	private Date upd_date;
	@XmlElement(name = "del_date")
	private Date del_date;
	@XmlElement(name = "del_flg")
	private int del_flg;
	
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
	public String getEvt_id() {
		return evt_id;
	}
	public void setEvt_id(String evt_id) {
		this.evt_id = evt_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getEvt_date_s() {
		return evt_date_s;
	}
	public void setEvt_date_s(String evt_date_s) {
		this.evt_date_s = evt_date_s;
	}
	public String getEvt_date_e() {
		return evt_date_e;
	}
	public void setEvt_date_e(String evt_date_e) {
		this.evt_date_e = evt_date_e;
	}
	public String getEvt_time_s() {
		return evt_time_s;
	}
	public void setEvt_time_s(String evt_time_s) {
		this.evt_time_s = evt_time_s;
	}
	public String getEvt_time_e() {
		return evt_time_e;
	}
	public void setEvt_time_e(String evt_time_e) {
		this.evt_time_e = evt_time_e;
	}
	public String getEvt_location() {
		return evt_location;
	}
	public void setEvt_location(String evt_location) {
		this.evt_location = evt_location;
	}
	public String getEvt_contents() {
		return evt_contents;
	}
	public void setEvt_contents(String evt_contents) {
		this.evt_contents = evt_contents;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Date getDel_date() {
		return del_date;
	}
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}
	public int getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}
	public int getEvt_type() {
		return evt_type;
	}
	public void setEvt_type(int evt_type) {
		this.evt_type = evt_type;
	}
	public int getEvt_reg() {
		return evt_reg;
	}
	public void setEvt_reg(int evt_reg) {
		this.evt_reg = evt_reg;
	}
	
}
