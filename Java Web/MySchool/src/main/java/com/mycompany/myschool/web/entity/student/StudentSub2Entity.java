package com.mycompany.myschool.web.entity.student;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class StudentSub2Entity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "school1")
	private String school1;
	@XmlElement(name = "school2")
	private String school2;
	@XmlElement(name = "school3")
	private String school3;
	@XmlElement(name = "location1")
	private String location1;
	@XmlElement(name = "location2")
	private String location2;
	@XmlElement(name = "location3")
	private String location3;
	@XmlElement(name = "subject1")
	private String subject1;
	@XmlElement(name = "subject2")
	private String subject2;
	@XmlElement(name = "subject3")
	private String subject3;
	@XmlElement(name = "job1")
	private String job1;
	@XmlElement(name = "job2")
	private String job2;
	@XmlElement(name = "job3")
	private String job3;
	@XmlElement(name = "field1")
	private String field1;
	@XmlElement(name = "field2")
	private String field2;
	@XmlElement(name = "field3")
	private String field3;
	@XmlElement(name = "school_ord")
	private int school_ord;
	@XmlElement(name = "subject_ord")
	private int subject_ord;
	@XmlElement(name = "location_ord")
	private int location_ord;
	@XmlElement(name = "job_ord")
	private int job_ord;
	@XmlElement(name = "field_ord")
	private int field_ord;
	@XmlElement(name = "remarks")
	private String remarks;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlSchemaType(name = "date")
	private Date ins_date;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date upd_date;
	
	private String ins_date_str;
	private String upd_date_str;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getSchool1() {
		return school1;
	}
	public void setSchool1(String school1) {
		this.school1 = school1;
	}
	public String getSchool2() {
		return school2;
	}
	public void setSchool2(String school2) {
		this.school2 = school2;
	}
	public String getSchool3() {
		return school3;
	}
	public void setSchool3(String school3) {
		this.school3 = school3;
	}
	public String getLocation1() {
		return location1;
	}
	public void setLocation1(String location1) {
		this.location1 = location1;
	}
	public String getLocation2() {
		return location2;
	}
	public void setLocation2(String location2) {
		this.location2 = location2;
	}
	public String getLocation3() {
		return location3;
	}
	public void setLocation3(String location3) {
		this.location3 = location3;
	}
	public String getSubject1() {
		return subject1;
	}
	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}
	public String getSubject2() {
		return subject2;
	}
	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}
	public String getSubject3() {
		return subject3;
	}
	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}
	public String getJob1() {
		return job1;
	}
	public void setJob1(String job1) {
		this.job1 = job1;
	}
	public String getJob2() {
		return job2;
	}
	public void setJob2(String job2) {
		this.job2 = job2;
	}
	public String getJob3() {
		return job3;
	}
	public void setJob3(String job3) {
		this.job3 = job3;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getIns_date() {
		return ins_date;
	}
	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	public int getSchool_ord() {
		return school_ord;
	}
	public void setSchool_ord(int school_ord) {
		this.school_ord = school_ord;
	}
	public int getSubject_ord() {
		return subject_ord;
	}
	public void setSubject_ord(int subject_ord) {
		this.subject_ord = subject_ord;
	}
	public int getLocation_ord() {
		return location_ord;
	}
	public void setLocation_ord(int location_ord) {
		this.location_ord = location_ord;
	}
	public int getJob_ord() {
		return job_ord;
	}
	public void setJob_ord(int job_ord) {
		this.job_ord = job_ord;
	}
	public int getField_ord() {
		return field_ord;
	}
	public void setField_ord(int field_ord) {
		this.field_ord = field_ord;
	}
	
	public String getIns_date_str() {
		return ins_date_str;
	}
	public void setIns_date_str(String ins_date_str) {
		this.ins_date_str = ins_date_str;
	}
	public String getUpd_date_str() {
		return upd_date_str;
	}
	public void setUpd_date_str(String upd_date_str) {
		this.upd_date_str = upd_date_str;
	}
	public String toString() {
		return "school_ord=" + school_ord +
				",location_ord=" + location_ord + 
				",subject_ord=" + subject_ord + 
				",job_ord=" + job_ord + 
				",field_ord=" + field_ord;
	}
}
