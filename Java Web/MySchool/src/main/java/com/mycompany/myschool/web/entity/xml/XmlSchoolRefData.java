package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.customer.CustomerRefEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlSchoolRefData {
	@XmlElement(name = "school_ref")
	private List<CustomerRefEntity> items;
	
	public List<CustomerRefEntity> getItems() {
		return items;
	}

	public void setItems(List<CustomerRefEntity> items) {
		this.items = items;
	}
}
