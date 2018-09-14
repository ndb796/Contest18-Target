package com.mycompany.myschool.web.entity.customer;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImageEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "school_id")
	private String school_id;
	@XmlElement(name = "img_ord")
	private int img_ord;
	@XmlElement(name = "filename")
	private String filename;
	@XmlElement(name = "filesize")
	private long filesize;
	@XmlElement(name = "origin_name")
	private String origin_name;
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
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public int getImg_ord() {
		return img_ord;
	}
	public void setImg_ord(int img_ord) {
		this.img_ord = img_ord;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
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
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	
}
