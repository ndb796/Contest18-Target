package com.mycompany.myschool.web.entity.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.mycompany.myschool.web.entity.base.BaseEntity;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatisticsVo extends BaseEntity {
	@XmlElement(name = "col")
	private String col;
	@XmlElement(name = "cnt")
	private int cnt;
	
	@XmlElement(name = "col2")
	private String col2;
	@XmlElement(name = "cnt2")
	private int cnt2;
	
	@XmlElement(name = "col3")
	private String col3;
	@XmlElement(name = "cnt3")
	private String cnt3;
	
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getCol2() {
		return col2;
	}
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	public int getCnt2() {
		return cnt2;
	}
	public void setCnt2(int cnt2) {
		this.cnt2 = cnt2;
	}
	public String getCol3() {
		return col3;
	}
	public void setCol3(String col3) {
		this.col3 = col3;
	}
	public String getCnt3() {
		return cnt3;
	}
	public void setCnt3(String cnt3) {
		this.cnt3 = cnt3;
	}
	
	
}
