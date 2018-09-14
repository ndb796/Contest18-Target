package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.student.StudentSub1Entity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlStudentSub1Data {
	@XmlElement(name = "cur_school")
	private List<StudentSub1Entity> items;
	
	public List<StudentSub1Entity> getItems() {
		return items;
	}

	public void setItems(List<StudentSub1Entity> items) {
		this.items = items;
	}
}
