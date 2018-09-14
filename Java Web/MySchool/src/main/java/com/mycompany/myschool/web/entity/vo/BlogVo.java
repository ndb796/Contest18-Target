package com.mycompany.myschool.web.entity.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BlogVo implements Comparable<BlogVo> {
	
	@Override
	public int compareTo(BlogVo arg0) {
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
	@XmlElement(name = "gender")
	private String gender;
	@XmlElement(name = "gender_str")
	private String gender_str;
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "current_type")
	private int current_type;
	@XmlElement(name = "current_grade")
	private String current_grade;
	
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurrent_type() {
		return current_type;
	}
	public void setCurrent_type(int current_type) {
		this.current_type = current_type;
	}
	public String getCurrent_grade() {
		return current_grade;
	}
	public void setCurrent_grade(String current_grade) {
		this.current_grade = current_grade;
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
	public String getGender_str() {
		return gender_str;
	}
	public void setGender_str(String gender_str) {
		this.gender_str = gender_str;
	}
	
}
