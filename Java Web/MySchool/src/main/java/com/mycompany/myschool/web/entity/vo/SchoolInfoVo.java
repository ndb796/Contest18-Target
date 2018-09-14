package com.mycompany.myschool.web.entity.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.mycompany.myschool.web.entity.base.BaseEntity;
import com.mycompany.myschool.web.entity.customer.CustomerRefEntity;

@XmlAccessorType(XmlAccessType.FIELD)
public class SchoolInfoVo extends BaseEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "sb_id")
	private String sb_id;
	@XmlElement(name = "sb_name")
	private String sb_name;
	@XmlElement(name = "school_type")
	private String school_type;
	@XmlElement(name = "school_category")
	private String school_category;
	@XmlElement(name = "title1")
	private String title1;
	@XmlElement(name = "title2")
	private String title2;
	@XmlElement(name = "contents")
	private String contents;
	@XmlElement(name = "built_year")
	private String built_year;
	@XmlElement(name = "homepage")
	private String homepage;
	@XmlElement(name = "zipcode")
	private String zipcode;
	@XmlElement(name = "address1")
	private String address1;
	@XmlElement(name = "address2")
	private String address2;
	@XmlElement(name = "std_info")
	private String std_info;
	@XmlElement(name = "logoname")
	private String logoname;
	
	/*
	 * detail info
	 */
	@XmlElement(name = "features")
	private String features;
	@XmlElement(name = "courses")
	private String courses;
	@XmlElement(name = "fee")
	private String fee;
	@XmlElement(name = "financial")
	private String financial;
	@XmlElement(name = "selection")
	private String selection;
	@XmlElement(name = "access")
	private String access;
	@XmlElement(name = "map")
	private String map;
	
	/*
	 * charger info
	 */
	@XmlElement(name = "chr_lname")
	private String chr_lname;
	@XmlElement(name = "chr_fname")
	private String chr_fname;
	@XmlElement(name = "chr_dept")
	private String chr_dept;
	@XmlElement(name = "chr_tel")
	private String chr_tel;
	@XmlElement(name = "chr_fax")
	private String chr_fax;
	@XmlElement(name = "chr_fb")
	private String chr_fb;
	@XmlElement(name = "chr_tw")
	private String chr_tw;
	@XmlElement(name = "schoolRef")
	private CustomerRefEntity schoolRef;
	
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
	public String getSb_name() {
		return sb_name;
	}
	public void setSb_name(String sb_name) {
		this.sb_name = sb_name;
	}
	public String getSchool_type() {
		return school_type;
	}
	public void setSchool_type(String school_type) {
		this.school_type = school_type;
	}
	public String getSchool_category() {
		return school_category;
	}
	public void setSchool_category(String school_category) {
		this.school_category = school_category;
	}
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getBuilt_year() {
		return built_year;
	}
	public void setBuilt_year(String built_year) {
		this.built_year = built_year;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getChr_lname() {
		return chr_lname;
	}
	public void setChr_lname(String chr_lname) {
		this.chr_lname = chr_lname;
	}
	public String getChr_fname() {
		return chr_fname;
	}
	public void setChr_fname(String chr_fname) {
		this.chr_fname = chr_fname;
	}
	public String getChr_dept() {
		return chr_dept;
	}
	public void setChr_dept(String chr_dept) {
		this.chr_dept = chr_dept;
	}
	public String getChr_tel() {
		return chr_tel;
	}
	public void setChr_tel(String chr_tel) {
		this.chr_tel = chr_tel;
	}
	public String getChr_fax() {
		return chr_fax;
	}
	public void setChr_fax(String chr_fax) {
		this.chr_fax = chr_fax;
	}
	public String getChr_fb() {
		return chr_fb;
	}
	public void setChr_fb(String chr_fb) {
		this.chr_fb = chr_fb;
	}
	public String getChr_tw() {
		return chr_tw;
	}
	public void setChr_tw(String chr_tw) {
		this.chr_tw = chr_tw;
	}
	public String getStd_info() {
		return std_info;
	}
	public void setStd_info(String std_info) {
		this.std_info = std_info;
	}
	public String getLogoname() {
		return logoname;
	}
	public void setLogoname(String logoname) {
		this.logoname = logoname;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getFinancial() {
		return financial;
	}
	public void setFinancial(String financial) {
		this.financial = financial;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public CustomerRefEntity getSchoolRef() {
		return schoolRef;
	}
	public void setSchoolRef(CustomerRefEntity schoolRef) {
		this.schoolRef = schoolRef;
	}
}
