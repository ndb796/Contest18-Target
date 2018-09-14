package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.student.RecommendEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlSnsConsultData {
	@XmlElement(name = "sns_consult")
	private List<RecommendEntity> items;
	
	public List<RecommendEntity> getItems() {
		return items;
	}

	public void setItems(List<RecommendEntity> items) {
		this.items = items;
	}
}
