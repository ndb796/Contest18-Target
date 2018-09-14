package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.customer.CustomerSub2Entity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlSchoolSub2Data {
	@XmlElement(name = "school_charger")
	private List<CustomerSub2Entity> items;
	
	public List<CustomerSub2Entity> getItems() {
		return items;
	}

	public void setItems(List<CustomerSub2Entity> items) {
		this.items = items;
	}
}
