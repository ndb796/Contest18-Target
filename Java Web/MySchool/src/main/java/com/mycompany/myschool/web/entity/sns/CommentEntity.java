package com.mycompany.myschool.web.entity.sns;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
public class CommentEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "std_idx")
	private int std_idx;
	@XmlElement(name = "grp_idx")
	private int grp_idx;
	@XmlElement(name = "board_id")
	private String board_id;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "comments")
	private String comments;
	@XmlElement(name = "ins_date")
	@XmlSchemaType(name = "date")
	private Date ins_date;
	@XmlElement(name = "upd_date")
	private Date upd_date;
	
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
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public int getStd_idx() {
		return std_idx;
	}
	public void setStd_idx(int std_idx) {
		this.std_idx = std_idx;
	}
	public int getGrp_idx() {
		return grp_idx;
	}
	public void setGrp_idx(int grp_idx) {
		this.grp_idx = grp_idx;
	}
	
}
