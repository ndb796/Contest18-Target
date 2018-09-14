package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.sns.GroupEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlGroupData {
	@XmlElement(name = "group")
	private List<GroupEntity> items;
	
	public List<GroupEntity> getItems() {
		return items;
	}

	public void setItems(List<GroupEntity> items) {
		this.items = items;
	}
}
