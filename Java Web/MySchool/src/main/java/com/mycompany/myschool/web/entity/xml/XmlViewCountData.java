package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.customer.ViewCountEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlViewCountData {
	@XmlElement(name = "view_cnt")
	private List<ViewCountEntity> items;
	
	public List<ViewCountEntity> getItems() {
		return items;
	}

	public void setItems(List<ViewCountEntity> items) {
		this.items = items;
	}
}
