package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.student.StudentSub2Entity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlStudentSub2Data {
	@XmlElement(name = "specific")
	private List<StudentSub2Entity> items;
	
	public List<StudentSub2Entity> getItems() {
		return items;
	}

	public void setItems(List<StudentSub2Entity> items) {
		this.items = items;
	}
}
