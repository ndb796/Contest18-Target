package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.sns.MediaEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlMediaData {
	@XmlElement(name = "media")
	private List<MediaEntity> items;
	
	public List<MediaEntity> getItems() {
		return items;
	}

	public void setItems(List<MediaEntity> items) {
		this.items = items;
	}
}
