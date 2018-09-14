package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.board.EventEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlEventData {
	@XmlElement(name = "event")
	private List<EventEntity> items;
	
	public List<EventEntity> getItems() {
		return items;
	}

	public void setItems(List<EventEntity> items) {
		this.items = items;
	}
}
