package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.base.UserEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlUserData {
	@XmlElement(name = "user")
	private List<UserEntity> items;
	
	public List<UserEntity> getItems() {
		return items;
	}

	public void setItems(List<UserEntity> items) {
		this.items = items;
	}
}
