package com.mycompany.myschool.web.entity.student;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class StudentSub1Entity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "cur_school_type1")
	private int cur_school_type1;
	@XmlElement(name = "cur_school_type2")
	private int cur_school_type2;
	@XmlElement(name = "cur_school_code")
	private String cur_school_code;
	@XmlElement(name = "cur_school_name")
	private String cur_school_name;
	@XmlElement(name = "cur_school_grade")
	private int cur_school_grade;
	@XmlElement(name = "lang_score")
	private float lang_score;
	@XmlElement(name = "eng_score")
	private float eng_score;
	@XmlElement(name = "hist_score")
	private float hist_score;
	@XmlElement(name = "civil_score")
	private float civil_score;
	@XmlElement(name = "mat_score")
	private float mat_score;
	@XmlElement(name = "sci_score")
	private float sci_score;
	@XmlElement(name = "rank")
	private String rank;
	@XmlElement(name = "remarks")
	private String remarks;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlSchemaType(name = "date")
	private Date ins_date;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date upd_date;
	
	private String ins_date_str;
	private String upd_date_str;
	
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
	public int getCur_school_type1() {
		return cur_school_type1;
	}
	public void setCur_school_type1(int cur_school_type1) {
		this.cur_school_type1 = cur_school_type1;
	}
	public int getCur_school_type2() {
		return cur_school_type2;
	}
	public void setCur_school_type2(int cur_school_type2) {
		this.cur_school_type2 = cur_school_type2;
	}
	public String getCur_school_code() {
		return cur_school_code;
	}
	public void setCur_school_code(String cur_school_code) {
		this.cur_school_code = cur_school_code;
	}
	public String getCur_school_name() {
		return cur_school_name;
	}
	public void setCur_school_name(String cur_school_name) {
		this.cur_school_name = cur_school_name;
	}
	public int getCur_school_grade() {
		return cur_school_grade;
	}
	public void setCur_school_grade(int cur_school_grade) {
		this.cur_school_grade = cur_school_grade;
	}
	public float getLang_score() {
		return lang_score;
	}
	public void setLang_score(float lang_score) {
		this.lang_score = lang_score;
	}
	public float getEng_score() {
		return eng_score;
	}
	public void setEng_score(float eng_score) {
		this.eng_score = eng_score;
	}
	public float getHist_score() {
		return hist_score;
	}
	public void setHist_score(float hist_score) {
		this.hist_score = hist_score;
	}
	public float getCivil_score() {
		return civil_score;
	}
	public void setCivil_score(float civil_score) {
		this.civil_score = civil_score;
	}
	public float getMat_score() {
		return mat_score;
	}
	public void setMat_score(float mat_score) {
		this.mat_score = mat_score;
	}
	public float getSci_score() {
		return sci_score;
	}
	public void setSci_score(float sci_score) {
		this.sci_score = sci_score;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
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
	public String getIns_date_str() {
		return ins_date_str;
	}
	public void setIns_date_str(String ins_date_str) {
		this.ins_date_str = ins_date_str;
	}
	public String getUpd_date_str() {
		return upd_date_str;
	}
	public void setUpd_date_str(String upd_date_str) {
		this.upd_date_str = upd_date_str;
	}
	
}
