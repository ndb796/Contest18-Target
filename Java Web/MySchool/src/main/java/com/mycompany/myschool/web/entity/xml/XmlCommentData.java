package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.sns.CommentEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlCommentData {
	@XmlElement(name = "comment")
	private List<CommentEntity> items;
	
	public List<CommentEntity> getItems() {
		return items;
	}

	public void setItems(List<CommentEntity> items) {
		this.items = items;
	}
}
