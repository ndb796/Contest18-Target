package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.customer.CustomerImageEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlSchoolImageData {
	@XmlElement(name = "school_image")
	private List<CustomerImageEntity> items;
	
	public List<CustomerImageEntity> getItems() {
		return items;
	}

	public void setItems(List<CustomerImageEntity> items) {
		this.items = items;
	}
}
