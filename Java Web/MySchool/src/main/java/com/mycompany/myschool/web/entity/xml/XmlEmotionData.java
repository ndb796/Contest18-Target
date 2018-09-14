package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.sns.EmotionEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlEmotionData {
	@XmlElement(name = "emotion")
	private List<EmotionEntity> items;
	
	public List<EmotionEntity> getItems() {
		return items;
	}

	public void setItems(List<EmotionEntity> items) {
		this.items = items;
	}
}
