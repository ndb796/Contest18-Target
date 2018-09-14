package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.customer.CustomerKeyEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlSchoolKeyData {
	@XmlElement(name = "school_key")
	private List<CustomerKeyEntity> items;
	
	public List<CustomerKeyEntity> getItems() {
		return items;
	}

	public void setItems(List<CustomerKeyEntity> items) {
		this.items = items;
	}
}
