package com.mycompany.myschool.web.entity.customer;

import java.sql.Timestamp;

public class CustomerCoursesEntity {
	private int idx;
	private String sb_id;
	private int curs_ord;
	private String curs_title;
	private String curs_contents1;
	private String curs_contents2;
	private Timestamp upd_date;

	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getIdx() {
		return this.idx;
	}
	public void setSb_id(String sb_id) {
		this.sb_id = sb_id;
	}
	public String getSb_id() {
		return this.sb_id;
	}
	public void setCurs_ord(int curs_ord) {
		this.curs_ord = curs_ord;
	}
	public int getCurs_ord() {
		return this.curs_ord;
	}
	public void setCurs_title(String curs_title) {
		this.curs_title = curs_title;
	}
	public String getCurs_title() {
		return this.curs_title;
	}
	public void setCurs_contents1(String curs_contents1) {
		this.curs_contents1 = curs_contents1;
	}
	public String getCurs_contents1() {
		return this.curs_contents1;
	}
	public void setCurs_contents2(String curs_contents2) {
		this.curs_contents2 = curs_contents2;
	}
	public String getCurs_contents2() {
		return this.curs_contents2;
	}
	public void setUpd_date(Timestamp upd_date) {
		this.upd_date = upd_date;
	}
	public Timestamp getUpd_date() {
		return this.upd_date;
	}

}