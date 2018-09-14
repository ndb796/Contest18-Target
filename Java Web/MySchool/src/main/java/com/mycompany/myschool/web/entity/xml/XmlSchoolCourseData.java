package com.mycompany.myschool.web.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.myschool.web.entity.customer.CustomerCoursesEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="root")
public class XmlSchoolCourseData {
	@XmlElement(name = "school_course")
	private List<CustomerCoursesEntity> items;
	
	public List<CustomerCoursesEntity> getItems() {
		return items;
	}

	public void setItems(List<CustomerCoursesEntity> items) {
		this.items = items;
	}
}
