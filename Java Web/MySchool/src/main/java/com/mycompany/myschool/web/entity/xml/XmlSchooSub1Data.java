package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.customer.CustomerSub1Entity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlSchooSub1Data {
	@XmlElement(name = "school_detail")
	private List<CustomerSub1Entity> items;
	
	public List<CustomerSub1Entity> getItems() {
		return items;
	}

	public void setItems(List<CustomerSub1Entity> items) {
		this.items = items;
	}
}
