package com.mycompany.myschool.web.entity.customer;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class CustomerBranchEntity {
	private int startRecord;
	private String searchValue;
	private String orderType;
	private String orderDir;
	
	private int no;
	private int idx;
	private String sb_id;
	private String school_id;
	private int sb_type;
	private int sb_ord;
	private String sb_name;
	private String sb_sch_founder_pub;
	private String sb_sch_founder_prv;
	private String sb_title1;
	private String sb_title2;
	private String sb_features;
	private String sb_policy;
	private String sb_tel;
	private String sb_fax;
	private String sb_dept;
	private String sb_prefecture_id;
	private String sb_zipcode;
	private String sb_address;
	private String sb_address1;
	private String sb_address2;
	private String sb_address3;
	private String sb_address4;
	private String sb_access;
	private MultipartFile photo1_file;
	private String photo1_name;
	private long photo1_size;
	private String org1_name;
	private MultipartFile photo2_file;
	private String photo2_name;
	private long photo2_size;
	private String org2_name;
	private String sb_contents;
	private Timestamp ins_date;
	private Timestamp upd_date;
	private String upd_id;
	private Timestamp del_date;
	private int del_flg;

	private String keywords;
	private String[] keywordsArray;
	private String[] sbIdArray;
	
	private int favr_cnt;
	private int rcmmd_cnt;

	public String[] getSbIdArray() {
		return sbIdArray;
	}
	public void setSbIdArray(String[] sbIdArray) {
		this.sbIdArray = sbIdArray;
	}
	public MultipartFile getPhoto1_file() {
		return photo1_file;
	}
	public void setPhoto1_file(MultipartFile photo1_file) {
		this.photo1_file = photo1_file;
	}
	public MultipartFile getPhoto2_file() {
		return photo2_file;
	}
	public void setPhoto2_file(MultipartFile photo2_file) {
		this.photo2_file = photo2_file;
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
	public String getSb_contents() {
		return sb_contents;
	}
	public void setSb_contents(String sb_contents) {
		this.sb_contents = sb_contents;
	}
	public String getSb_name() {
		return sb_name;
	}
	public void setSb_name(String sb_name) {
		this.sb_name = sb_name;
	}
	public int getFavr_cnt() {
		return favr_cnt;
	}
	public void setFavr_cnt(int favr_cnt) {
		this.favr_cnt = favr_cnt;
	}
	public int getRcmmd_cnt() {
		return rcmmd_cnt;
	}
	public void setRcmmd_cnt(int rcmmd_cnt) {
		this.rcmmd_cnt = rcmmd_cnt;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
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
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getSchool_id() {
		return this.school_id;
	}
	public void setSb_type(int sb_type) {
		this.sb_type = sb_type;
	}
	public int getSb_type() {
		return this.sb_type;
	}
	public void setSb_ord(int sb_ord) {
		this.sb_ord = sb_ord;
	}
	public int getSb_ord() {
		return this.sb_ord;
	}
	public void setSb_title1(String sb_title1) {
		this.sb_title1 = sb_title1;
	}
	public String getSb_title1() {
		return this.sb_title1;
	}
	public void setSb_title2(String sb_title2) {
		this.sb_title2 = sb_title2;
	}
	public String getSb_title2() {
		return this.sb_title2;
	}
	public void setSb_features(String sb_features) {
		this.sb_features = sb_features;
	}
	public String getSb_features() {
		return this.sb_features;
	}
	public void setSb_tel(String sb_tel) {
		this.sb_tel = sb_tel;
	}
	public String getSb_tel() {
		return this.sb_tel;
	}
	public void setSb_fax(String sb_fax) {
		this.sb_fax = sb_fax;
	}
	public String getSb_fax() {
		return this.sb_fax;
	}
	public void setSb_dept(String sb_dept) {
		this.sb_dept = sb_dept;
	}
	public String getSb_dept() {
		return this.sb_dept;
	}
	public void setSb_zipcode(String sb_zipcode) {
		this.sb_zipcode = sb_zipcode;
	}
	public String getSb_zipcode() {
		return this.sb_zipcode;
	}
	public void setSb_address1(String sb_address1) {
		this.sb_address1 = sb_address1;
	}
	public String getSb_address1() {
		return this.sb_address1;
	}
	public void setSb_address2(String sb_address2) {
		this.sb_address2 = sb_address2;
	}
	public String getSb_address2() {
		return this.sb_address2;
	}
	public void setSb_access(String sb_access) {
		this.sb_access = sb_access;
	}
	public String getSb_access() {
		return this.sb_access;
	}
	public void setIns_date(Timestamp ins_date) {
		this.ins_date = ins_date;
	}
	public Timestamp getIns_date() {
		return this.ins_date;
	}
	public void setUpd_date(Timestamp upd_date) {
		this.upd_date = upd_date;
	}
	public Timestamp getUpd_date() {
		return this.upd_date;
	}
	public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}
	public String getUpd_id() {
		return this.upd_id;
	}
	public void setDel_date(Timestamp del_date) {
		this.del_date = del_date;
	}
	public Timestamp getDel_date() {
		return this.del_date;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}
	public int getDel_flg() {
		return this.del_flg;
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
	public String getSb_prefecture_id() {
		return sb_prefecture_id;
	}
	public void setSb_prefecture_id(String sb_prefecture_id) {
		this.sb_prefecture_id = sb_prefecture_id;
	}
	
	public String getSb_sch_founder_pub() {
		return sb_sch_founder_pub;
	}
	public void setSb_sch_founder_pub(String sb_sch_founder_pub) {
		this.sb_sch_founder_pub = sb_sch_founder_pub;
	}
	public String getSb_sch_founder_prv() {
		return sb_sch_founder_prv;
	}
	public void setSb_sch_founder_prv(String sb_sch_founder_prv) {
		this.sb_sch_founder_prv = sb_sch_founder_prv;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String[] getKeywordsArray() {
		return keywordsArray;
	}
	public void setKeywordsArray(String[] keywordsArray) {
		this.keywordsArray = keywordsArray;
	}
	public String getSb_policy() {
		return sb_policy;
	}
	public void setSb_policy(String sb_policy) {
		this.sb_policy = sb_policy;
	}
	
}