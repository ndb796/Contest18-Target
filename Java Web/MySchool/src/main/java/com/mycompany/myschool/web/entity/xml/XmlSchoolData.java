package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.customer.CustomerEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlSchoolData {
	@XmlElement(name = "school")
	private List<CustomerEntity> items;
	
	public List<CustomerEntity> getItems() {
		return items;
	}

	public void setItems(List<CustomerEntity> items) {
		this.items = items;
	}
}
