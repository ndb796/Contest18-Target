package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.student.FavoriteEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlFavoriteData {
	@XmlElement(name = "favorite")
	private List<FavoriteEntity> items;
	
	public List<FavoriteEntity> getItems() {
		return items;
	}

	public void setItems(List<FavoriteEntity> items) {
		this.items = items;
	}
}
