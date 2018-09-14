package com.mycompany.myschool.web.entity.board;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class NewsEntity {
	private int is_new;
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "news_type")
	private int news_type;
	@XmlElement(name = "school_id")
	private String school_id;
	@XmlElement(name = "news_title")
	private String news_title;
	@XmlElement(name = "event_reg")
	private String event_reg;
	@XmlElement(name = "news_contents")
	private String news_contents;
	@XmlElement(name = "news_hit")
	private int news_hit;
	@XmlElement(name = "is_html")
	private String is_html;
	@XmlElement(name = "upd_date")
	private Date upd_date;
	@XmlElement(name = "upd_id")
	private String upd_id;
	@XmlElement(name = "del_date")
	private Date del_date;
	@XmlElement(name = "del_id")
	private String del_id;
	@XmlElement(name = "del_flg")
	private int del_flg;
	
	private String writer;
	private String upd_date_str;
	
	private String[] idArray;
	
	public int getIs_new() {
		return is_new;
	}
	public void setIs_new(int is_new) {
		this.is_new = is_new;
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
	public int getNews_type() {
		return news_type;
	}
	public void setNews_type(int news_type) {
		this.news_type = news_type;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_contents() {
		return news_contents;
	}
	public void setNews_contents(String news_contents) {
		this.news_contents = news_contents;
	}
	public int getNews_hit() {
		return news_hit;
	}
	public void setNews_hit(int news_hit) {
		this.news_hit = news_hit;
	}
	public String getIs_html() {
		return is_html;
	}
	public void setIs_html(String is_html) {
		this.is_html = is_html;
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
	public String getDel_id() {
		return del_id;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public int getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}
	public String getUpd_date_str() {
		return upd_date_str;
	}
	public void setUpd_date_str(String upd_date_str) {
		this.upd_date_str = upd_date_str;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String[] getIdArray() {
		return idArray;
	}
	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}
	
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getEvent_reg() {
		return event_reg;
	}
	public void setEvent_reg(String event_reg) {
		this.event_reg = event_reg;
	}
	public String toString() {
		return idx + "," + news_type + "," + news_title + "," + news_contents + "," + upd_date;
	}
}
