package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.sns.BoardEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlBoardData {
	@XmlElement(name = "board")
	private List<BoardEntity> items;
	
	public List<BoardEntity> getItems() {
		return items;
	}

	public void setItems(List<BoardEntity> items) {
		this.items = items;
	}
}
