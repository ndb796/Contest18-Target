package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.sns.MyGroupEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlMyGroupData {
	@XmlElement(name = "mygroup")
	private List<MyGroupEntity> items;
	
	public List<MyGroupEntity> getItems() {
		return items;
	}

	public void setItems(List<MyGroupEntity> items) {
		this.items = items;
	}
}
