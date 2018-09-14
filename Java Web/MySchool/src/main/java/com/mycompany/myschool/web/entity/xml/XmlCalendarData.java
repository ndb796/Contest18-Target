package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.board.CalendarEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlCalendarData {
	@XmlElement(name = "events")
	private List<CalendarEntity> items;
	
	public List<CalendarEntity> getItems() {
		return items;
	}

	public void setItems(List<CalendarEntity> items) {
		this.items = items;
	}
}
