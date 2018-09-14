package com.mycompany.myschool.web.entity.board;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CalendarEntity {
	@XmlElement(name = "title")
	private String title;
	@XmlElement(name = "start")
	private String start;
	@XmlElement(name = "end")
	private String end;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	public CalendarEntity() {}
	public CalendarEntity(String title, String start, String end) {
		this.title = title;
		this.start = start;
		this.end = end;
	}
}
