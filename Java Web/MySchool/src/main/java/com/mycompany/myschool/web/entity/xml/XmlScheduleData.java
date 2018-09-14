package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.board.ScheduleEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlScheduleData {
	@XmlElement(name = "schedule")
	private List<ScheduleEntity> items;
	
	public List<ScheduleEntity> getItems() {
		return items;
	}

	public void setItems(List<ScheduleEntity> items) {
		this.items = items;
	}
}
