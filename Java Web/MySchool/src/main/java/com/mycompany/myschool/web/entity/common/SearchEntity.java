package com.mycompany.myschool.web.entity.common;

import java.sql.Timestamp;

public class SearchEntity {
	private int startRecord;
	private String searchValue;
	private String orderType;
	private String orderDir;
	
	private int no;
	private int idx;
	private String sb_id;
	private String sch_id;
	private String cust_id;
	private String name;
	private String school_name;
	private String school_copy;
	private String history;
	private String station;
	private String op_time;
	private int sb_type;
	private String hiragana;
	private String hurigana;
	private String yomigana;
	private String sht_name;
	private String sht_name_yg;
	private String sch_cate_id;
	private String sch_founder_id;
	private String sch_founder_prv;
	private String sch_founder_pub;
	private String prefecture_id;
	private String zipcode;
	private String address;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String chr_dept;
	private String chr_tel;
	private String chr_fax;
	private String chr_tel_memo;
	private String freedial;
	private String freedial_memo;
	private String foundyear;
	private String pamphlet;
	private String pamphletstart;
	private String memo;
	private String url_pc;
	private String url_mobile;
	private String url_career_pc;
	private String email;
	private Timestamp ins_date;
	private Timestamp upd_date;
	private String upd_id;
	private Timestamp del_date;
	private int del_flg;

	String keyword;
	int school_type;
	int favor_cnt;
	
	private String keywords;
	private String[] keywordsArray;
	
	private int favr_cnt;
	private int rcmmd_cnt;
	private String rcmmd_type;
	
	public String getSchool_copy() {
		return school_copy;
	}
	public void setSchool_copy(String school_copy) {
		this.school_copy = school_copy;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
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
	public String getSch_id() {
		return sch_id;
	}
	public void setSch_id(String sch_id) {
		this.sch_id = sch_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSb_type() {
		return sb_type;
	}
	public void setSb_type(int sb_type) {
		this.sb_type = sb_type;
	}
	public String getOp_time() {
		return op_time;
	}
	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}
	public String getHiragana() {
		return hiragana;
	}
	public void setHiragana(String hiragana) {
		this.hiragana = hiragana;
	}
	public String getHurigana() {
		return hurigana;
	}
	public void setHurigana(String hurigana) {
		this.hurigana = hurigana;
	}
	public String getYomigana() {
		return yomigana;
	}
	public void setYomigana(String yomigana) {
		this.yomigana = yomigana;
	}
	public String getSht_name() {
		return sht_name;
	}
	public void setSht_name(String sht_name) {
		this.sht_name = sht_name;
	}
	public String getSht_name_yg() {
		return sht_name_yg;
	}
	public void setSht_name_yg(String sht_name_yg) {
		this.sht_name_yg = sht_name_yg;
	}
	public String getSch_cate_id() {
		return sch_cate_id;
	}
	public void setSch_cate_id(String sch_cate_id) {
		this.sch_cate_id = sch_cate_id;
	}
	public String getSch_founder_id() {
		return sch_founder_id;
	}
	public void setSch_founder_id(String sch_founder_id) {
		this.sch_founder_id = sch_founder_id;
	}
	public String getPrefecture_id() {
		return prefecture_id;
	}
	public void setPrefecture_id(String prefecture_id) {
		this.prefecture_id = prefecture_id;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getChr_tel_memo() {
		return chr_tel_memo;
	}
	public void setChr_tel_memo(String chr_tel_memo) {
		this.chr_tel_memo = chr_tel_memo;
	}
	public String getFreedial() {
		return freedial;
	}
	public void setFreedial(String freedial) {
		this.freedial = freedial;
	}
	public String getFreedial_memo() {
		return freedial_memo;
	}
	public void setFreedial_memo(String freedial_memo) {
		this.freedial_memo = freedial_memo;
	}
	public String getFoundyear() {
		return foundyear;
	}
	public void setFoundyear(String foundyear) {
		this.foundyear = foundyear;
	}
	public String getPamphlet() {
		return pamphlet;
	}
	public void setPamphlet(String pamphlet) {
		this.pamphlet = pamphlet;
	}
	public String getPamphletstart() {
		return pamphletstart;
	}
	public void setPamphletstart(String pamphletstart) {
		this.pamphletstart = pamphletstart;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUrl_pc() {
		return url_pc;
	}
	public void setUrl_pc(String url_pc) {
		this.url_pc = url_pc;
	}
	public String getUrl_mobile() {
		return url_mobile;
	}
	public void setUrl_mobile(String url_mobile) {
		this.url_mobile = url_mobile;
	}
	public String getUrl_career_pc() {
		return url_career_pc;
	}
	public void setUrl_career_pc(String url_career_pc) {
		this.url_career_pc = url_career_pc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getIns_date() {
		return ins_date;
	}
	public void setIns_date(Timestamp ins_date) {
		this.ins_date = ins_date;
	}
	public Timestamp getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Timestamp upd_date) {
		this.upd_date = upd_date;
	}
	public String getUpd_id() {
		return upd_id;
	}
	public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}
	public Timestamp getDel_date() {
		return del_date;
	}
	public void setDel_date(Timestamp del_date) {
		this.del_date = del_date;
	}
	public int getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
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
	public String getSch_founder_prv() {
		return sch_founder_prv;
	}
	public void setSch_founder_prv(String sch_founder_prv) {
		this.sch_founder_prv = sch_founder_prv;
	}
	public String getSch_founder_pub() {
		return sch_founder_pub;
	}
	public void setSch_founder_pub(String sch_founder_pub) {
		this.sch_founder_pub = sch_founder_pub;
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
	public String getRcmmd_type() {
		return rcmmd_type;
	}
	public void setRcmmd_type(String rcmmd_type) {
		this.rcmmd_type = rcmmd_type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getSchool_type() {
		return school_type;
	}
	public void setSchool_type(int school_type) {
		this.school_type = school_type;
	}
	public int getFavor_cnt() {
		return favor_cnt;
	}
	public void setFavor_cnt(int favor_cnt) {
		this.favor_cnt = favor_cnt;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	
}