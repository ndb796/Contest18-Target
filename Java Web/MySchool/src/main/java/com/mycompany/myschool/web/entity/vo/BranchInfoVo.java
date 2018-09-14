package com.mycompany.myschool.web.entity.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.mycompany.myschool.web.entity.base.BaseEntity;
import com.mycompany.myschool.web.entity.customer.CustomerRefEntity;

@XmlAccessorType(XmlAccessType.FIELD)
public class BranchInfoVo extends BaseEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "sb_id")
	private String sb_id;
	@XmlElement(name = "school_id")
	private String school_id;
	@XmlElement(name = "sb_type")
	private int sb_type;
	@XmlElement(name = "sb_ord")
	private int sb_ord;
	@XmlElement(name = "sb_name")
	private String sb_name;
	@XmlElement(name = "sb_title1")
	private String sb_title1;
	@XmlElement(name = "sb_title2")
	private String sb_title2;
	@XmlElement(name = "sb_contents")
	private String sb_contents;
	@XmlElement(name = "sb_features")
	private String sb_features;
	@XmlElement(name = "sb_policy")
	private String sb_policy;
	@XmlElement(name = "sb_tel")
	private String sb_tel;
	@XmlElement(name = "sb_fax")
	private String sb_fax;
	@XmlElement(name = "sb_dept")
	private String sb_dept;
	@XmlElement(name = "sb_zipcode")
	private String sb_zipcode;
	@XmlElement(name = "sb_address")
	private String sb_address;
	@XmlElement(name = "sb_address1")
	private String sb_address1;
	@XmlElement(name = "sb_address2")
	private String sb_address2;
	@XmlElement(name = "sb_address3")
	private String sb_address3;
	@XmlElement(name = "sb_address4")
	private String sb_address4;
	@XmlElement(name = "sb_access")
	private String sb_access;
	@XmlElement(name = "photo1_name")
	private String photo1_name;
	@XmlElement(name = "photo1_size")
	private long photo1_size;
	@XmlElement(name = "org1_name")
	private String org1_name;
	@XmlElement(name = "photo2_name")
	private String photo2_name;
	@XmlElement(name = "photo2_size")
	private long photo2_size;
	@XmlElement(name = "org2_name")
	private String org2_name;
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
	public String getSb_zipcode() {
		return sb_zipcode;
	}
	public void setSb_zipcode(String sb_zipcode) {
		this.sb_zipcode = sb_zipcode;
	}
	public String getSb_address1() {
		return sb_address1;
	}
	public void setSb_address1(String sb_address1) {
		this.sb_address1 = sb_address1;
	}
	public String getSb_address2() {
		return sb_address2;
	}
	public void setSb_address2(String sb_address2) {
		this.sb_address2 = sb_address2;
	}
	public String getSb_access() {
		return sb_access;
	}
	public void setSb_access(String sb_access) {
		this.sb_access = sb_access;
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
	
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public int getSb_type() {
		return sb_type;
	}
	public void setSb_type(int sb_type) {
		this.sb_type = sb_type;
	}
	public int getSb_ord() {
		return sb_ord;
	}
	public void setSb_ord(int sb_ord) {
		this.sb_ord = sb_ord;
	}
	public String getSb_title1() {
		return sb_title1;
	}
	public void setSb_title1(String sb_title1) {
		this.sb_title1 = sb_title1;
	}
	public String getSb_title2() {
		return sb_title2;
	}
	public void setSb_title2(String sb_title2) {
		this.sb_title2 = sb_title2;
	}
	public String getSb_contents() {
		return sb_contents;
	}
	public void setSb_contents(String sb_contents) {
		this.sb_contents = sb_contents;
	}
	public String getSb_features() {
		return sb_features;
	}
	public void setSb_features(String sb_features) {
		this.sb_features = sb_features;
	}
	public String getSb_tel() {
		return sb_tel;
	}
	public void setSb_tel(String sb_tel) {
		this.sb_tel = sb_tel;
	}
	public String getSb_fax() {
		return sb_fax;
	}
	public void setSb_fax(String sb_fax) {
		this.sb_fax = sb_fax;
	}
	public String getSb_dept() {
		return sb_dept;
	}
	public void setSb_dept(String sb_dept) {
		this.sb_dept = sb_dept;
	}
	public String getPhoto1_name() {
		return photo1_name;
	}
	public void setPhoto1_name(String photo1_name) {
		this.photo1_name = photo1_name;
	}
	public long getPhoto1_size() {
		return photo1_size;
	}
	public void setPhoto1_size(long photo1_size) {
		this.photo1_size = photo1_size;
	}
	public String getOrg1_name() {
		return org1_name;
	}
	public void setOrg1_name(String org1_name) {
		this.org1_name = org1_name;
	}
	public String getPhoto2_name() {
		return photo2_name;
	}
	public void setPhoto2_name(String photo2_name) {
		this.photo2_name = photo2_name;
	}
	public long getPhoto2_size() {
		return photo2_size;
	}
	public void setPhoto2_size(long photo2_size) {
		this.photo2_size = photo2_size;
	}
	public String getOrg2_name() {
		return org2_name;
	}
	public void setOrg2_name(String org2_name) {
		this.org2_name = org2_name;
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
	public String getSb_address3() {
		return sb_address3;
	}
	public void setSb_address3(String sb_address3) {
		this.sb_address3 = sb_address3;
	}
	public String getSb_address4() {
		return sb_address4;
	}
	public void setSb_address4(String sb_address4) {
		this.sb_address4 = sb_address4;
	}
	public String getSb_address() {
		return sb_address;
	}
	public void setSb_address(String sb_address) {
		this.sb_address = sb_address;
	}
	public String getSb_policy() {
		return sb_policy;
	}
	public void setSb_policy(String sb_policy) {
		this.sb_policy = sb_policy;
	}

}
