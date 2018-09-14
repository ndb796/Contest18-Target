package com.mycompany.myschool.web.entity.customer;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.web.multipart.MultipartFile;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerEntity {
	private int startRecord;
	private String searchValue;
	private String orderType;
	private String orderDir;
	
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "school_id")
	private String school_id;
	@XmlElement(name = "school_name")
	private String school_name;
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
	@XmlElement(name = "loc_code")
	private String loc_code;
	@XmlElement(name = "zipcode")
	private String zipcode;
	@XmlElement(name = "address")
	private String address;
	@XmlElement(name = "address1")
	private String address1;
	@XmlElement(name = "address2")
	private String address2;
	@XmlElement(name = "address3")
	private String address3;
	@XmlElement(name = "address4")
	private String address4;
	@XmlElement(name = "std_info")
	private String std_info;
	@XmlElement(name = "photo_file")
	private MultipartFile photo_file;
	@XmlElement(name = "logoname")
	private String logoname;
	@XmlElement(name = "origin_name")
	private String origin_name;
	@XmlElement(name = "logosize")
	private long logosize;
	@XmlElement(name = "paid_type")
	private int paid_type;
	@XmlElement(name = "school_short_name")
	private String school_short_name;
	@XmlElement(name = "hiragana")
	private String hiragana;
	
	@XmlElement(name = "remarks")
	private String remarks;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date ins_date;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date upd_date;
	@XmlElement(name = "upd_id")
	private String upd_id;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date del_date;
	@XmlElement(name = "del_flg")
	private int del_flg;
	
	private String upd_date_str;
	
	public String getUpd_date_str() {
		return upd_date_str;
	}
	public void setUpd_date_str(String upd_date_str) {
		this.upd_date_str = upd_date_str;
	}
	public int getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	public String getSchool_short_name() {
		return school_short_name;
	}
	public void setSchool_short_name(String school_short_name) {
		this.school_short_name = school_short_name;
	}
	public String getHiragana() {
		return hiragana;
	}
	public void setHiragana(String hiragana) {
		this.hiragana = hiragana;
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
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
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
	public String getLoc_code() {
		return loc_code;
	}
	public void setLoc_code(String loc_code) {
		this.loc_code = loc_code;
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
	public String getStd_info() {
		return std_info;
	}
	public void setStd_info(String std_info) {
		this.std_info = std_info;
	}
	
	public MultipartFile getPhoto_file() {
		return photo_file;
	}
	public void setPhoto_file(MultipartFile photo_file) {
		this.photo_file = photo_file;
	}
	public String getLogoname() {
		return logoname;
	}
	public void setLogoname(String logoname) {
		this.logoname = logoname;
	}
	public long getLogosize() {
		return logosize;
	}
	public void setLogosize(long logosize) {
		this.logosize = logosize;
	}
	public int getPaid_type() {
		return paid_type;
	}
	public void setPaid_type(int paid_type) {
		this.paid_type = paid_type;
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
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
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
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getAddress4() {
		return address4;
	}
	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
