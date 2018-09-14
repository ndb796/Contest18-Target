package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.common.SpecificMstEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlSpecificMstData {
	@XmlElement(name = "specific_mst")
	private List<SpecificMstEntity> items;
	
	public List<SpecificMstEntity> getItems() {
		return items;
	}

	public void setItems(List<SpecificMstEntity> items) {
		this.items = items;
	}
}
