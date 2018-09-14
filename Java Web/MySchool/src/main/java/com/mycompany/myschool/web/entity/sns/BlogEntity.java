package com.mycompany.myschool.web.entity.sns;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BlogEntity implements Comparable<BlogEntity> {
	
	@Override
	public int compareTo(BlogEntity arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "blog_type")
	private int blog_type;
	@XmlElement(name = "show_flg")
	private int show_flg;
	@XmlElement(name = "school_id")
	private String school_id;
	@XmlElement(name = "title")
	private String title;
	@XmlElement(name = "contents")
	private String contents;
	@XmlElement(name = "upd_date")
	@XmlSchemaType(name = "date")
	private Date upd_date;
	@XmlElement(name = "upd_date_str")
	private String upd_date_str;
	@XmlElement(name = "upd_id")
	private String upd_id;
	@XmlElement(name = "del_date")
	@XmlSchemaType(name = "date")
	private Date del_date;
	@XmlElement(name = "del_flg")
	private int del_flg;
	
	private String txt_search;
	
	private int startPage;
	private int rows_per_page;
	
	private int[] idxsArray;

	public BlogEntity() {
		
	}

	public BlogEntity(int idx) {
		this.idx = idx;
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

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public int getBlog_type() {
		return blog_type;
	}

	public void setBlog_type(int blog_type) {
		this.blog_type = blog_type;
	}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public int[] getIdxsArray() {
		return idxsArray;
	}

	public void setIdxsArray(int[] idxsArray) {
		this.idxsArray = idxsArray;
	}

	public int getShow_flg() {
		return show_flg;
	}

	public void setShow_flg(int show_flg) {
		this.show_flg = show_flg;
	}

	public String getUpd_date_str() {
		return upd_date_str;
	}

	public void setUpd_date_str(String upd_date_str) {
		this.upd_date_str = upd_date_str;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getRows_per_page() {
		return rows_per_page;
	}

	public void setRows_per_page(int rows_per_page) {
		this.rows_per_page = rows_per_page;
	}

	public String getTxt_search() {
		return txt_search;
	}

	public void setTxt_search(String txt_search) {
		this.txt_search = txt_search;
	}

}
