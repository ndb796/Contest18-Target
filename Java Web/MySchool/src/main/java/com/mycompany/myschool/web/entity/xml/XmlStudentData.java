package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.student.StudentEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlStudentData {
	@XmlElement(name = "student")
	private List<StudentEntity> items;
	
	public List<StudentEntity> getItems() {
		return items;
	}

	public void setItems(List<StudentEntity> items) {
		this.items = items;
	}
}
