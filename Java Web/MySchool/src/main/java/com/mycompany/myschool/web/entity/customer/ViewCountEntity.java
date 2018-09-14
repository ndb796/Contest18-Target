package com.mycompany.myschool.web.entity.customer;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class ViewCountEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "sb_id")
	private String sb_id;
	@XmlElement(name = "viewcnt")
	private int viewcnt;
	@XmlElement(name = "realcnt")
	private int realcnt;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlSchemaType(name = "date")
	private Date ins_date;
	//@XmlElement(name = "upd_date")
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
	public String getSb_id() {
		return sb_id;
	}
	public void setSb_id(String sb_id) {
		this.sb_id = sb_id;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public int getRealcnt() {
		return realcnt;
	}
	public void setRealcnt(int realcnt) {
		this.realcnt = realcnt;
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
}
