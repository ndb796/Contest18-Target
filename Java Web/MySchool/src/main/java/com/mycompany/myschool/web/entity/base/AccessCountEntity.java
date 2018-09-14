package com.mycompany.myschool.web.entity.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AccessCountEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "sch_cnt")
	private long sch_cnt;
	@XmlElement(name = "std_cnt")
	private long std_cnt;
	@XmlElement(name = "access_cnt")
	private long access_cnt;
	
	@XmlElement(name = "access_date")
	private String access_date;
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
	public long getSch_cnt() {
		return sch_cnt;
	}
	public void setSch_cnt(long sch_cnt) {
		this.sch_cnt = sch_cnt;
	}
	public long getStd_cnt() {
		return std_cnt;
	}
	public void setStd_cnt(long std_cnt) {
		this.std_cnt = std_cnt;
	}
	public String getAccess_date() {
		return access_date;
	}
	public void setAccess_date(String access_date) {
		this.access_date = access_date;
	}
	public long getAccess_cnt() {
		return access_cnt;
	}
	public void setAccess_cnt(long access_cnt) {
		this.access_cnt = access_cnt;
	}
	
}
