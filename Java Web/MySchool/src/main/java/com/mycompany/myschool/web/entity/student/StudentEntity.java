package com.mycompany.myschool.web.entity.student;

import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.myschool.web.entity.base.SqlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class StudentEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "fname")
	@NotEmpty(message="Please, enter your first name.")
	private String fname;
	@XmlElement(name = "lname")
	@NotEmpty(message="Please, enter your last name.")
	private String lname;
	@XmlElement(name = "location")
	private String location;
	@NotEmpty(message="Please, enter your e-mail address.")
	@Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$", message="Please, enter a vaild e-mail address.")
	@XmlElement(name = "email")
	private String email;
	@XmlElement(name = "pwd")
	@NotEmpty(message="Please, enter your password.")
	private String pwd;
	@XmlElement(name = "gender")
	private String gender;
	@XmlElement(name = "gender_str")
	private String gender_str;
	@XmlElement(name = "current_type")
	private int current_type = -1;
	@XmlElement(name = "current_grade")
	private String current_grade;
	@XmlElement(name = "photo_file")
	private MultipartFile photo_file;
//	private String photo_file;
	@XmlElement(name = "filename")
	private String filename;
	@XmlElement(name = "filesize")
	private long filesize;
	@XmlElement(name = "rcmmd_type")
	private int rcmmd_type;
	@XmlElement(name = "zipcode")
	private String zipcode;
	@XmlElement(name = "address1")
	private String address1;
	@XmlElement(name = "address2")
	private String address2;
	@XmlElement(name = "address3")
	private String address3;
	@XmlElement(name = "address4")
	private String address4;
	@XmlElement(name = "birth_y")
	private String birth_y;
	@XmlElement(name = "birth_m")
	private String birth_m;
	@XmlElement(name = "birth_d")
	private String birth_d;
	@XmlElement(name = "remarks")
	private String remarks;
	//@XmlElement(name = "ins_date")
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlSchemaType(name = "date")
	private Date ins_date;
	//@XmlElement(name = "upd_date")
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date upd_date;
	//@XmlElement(name = "del_date")
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date del_date;
	@XmlElement(name = "del_flg")
	private int del_flg;
	
	private String ins_date_str;
	private String upd_date_str;
	
	private StudentSub1Entity student_sub1_entity;
	private StudentSub2Entity student_sub2_entity;
	private int rcmmd_cnt;
	
	
	private String token_key;
	
	public String getToken_key() {
		return token_key;
	}
	public void setToken_key(String token_key) {
		this.token_key = token_key;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public Date getDel_date() {
		return del_date;
	}
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}
	public int getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}
	public MultipartFile  getPhoto_file() {
		return photo_file;
	}
	public void setPhoto_file(MultipartFile  photo_file) {
		this.photo_file = photo_file;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getBirth_y() {
		return birth_y;
	}
	public void setBirth_y(String birth_y) {
		this.birth_y = birth_y;
	}
	public String getBirth_m() {
		return birth_m;
	}
	public void setBirth_m(String birth_m) {
		this.birth_m = birth_m;
	}
	public String getBirth_d() {
		return birth_d;
	}
	public void setBirth_d(String birth_d) {
		this.birth_d = birth_d;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getRcmmd_type() {
		return rcmmd_type;
	}
	public void setRcmmd_type(int rcmmd_type) {
		this.rcmmd_type = rcmmd_type;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getAddress4() {
		return address4;
	}
	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	public int getCurrent_type() {
		return current_type;
	}
	public void setCurrent_type(int current_type) {
		this.current_type = current_type;
	}
	public String getCurrent_grade() {
		return current_grade;
	}
	public void setCurrent_grade(String current_grade) {
		this.current_grade = current_grade;
	}
	public StudentSub1Entity getStudent_sub1_entity() {
		return student_sub1_entity;
	}
	public void setStudent_sub1_entity(StudentSub1Entity student_sub1_entity) {
		this.student_sub1_entity = student_sub1_entity;
	}
	public StudentSub2Entity getStudent_sub2_entity() {
		return student_sub2_entity;
	}
	public void setStudent_sub2_entity(StudentSub2Entity student_sub2_entity) {
		this.student_sub2_entity = student_sub2_entity;
	}
	public int getRcmmd_cnt() {
		return rcmmd_cnt;
	}
	public void setRcmmd_cnt(int rcmmd_cnt) {
		this.rcmmd_cnt = rcmmd_cnt;
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
	public String getGender_str() {
		return gender_str;
	}
	public void setGender_str(String gender_str) {
		this.gender_str = gender_str;
	}
	
}
