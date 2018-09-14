package com.mycompany.myschool.web.entity.sns;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BoardEntity implements Comparable<BoardEntity> {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "board_id")
	private String board_id;
	@XmlElement(name = "student_id")
	private String student_id;
	@XmlElement(name = "group_id")
	private String group_id;
	@XmlElement(name = "board_type")
	private int board_type;
	@XmlElement(name = "contents")
	private String contents;
	@XmlElement(name = "ins_date")
	@XmlSchemaType(name = "date")
	private Date ins_date;
	@XmlElement(name = "upd_date")
	private Date upd_date;
	
	private EmotionEntity emotionEntity;
	private List<CommentEntity> commentList;
	
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
	
	public EmotionEntity getEmotionEntity() {
		return emotionEntity;
	}
	public void setEmotionEntity(EmotionEntity emotionEntity) {
		this.emotionEntity = emotionEntity;
	}
	public int getBoard_type() {
		return board_type;
	}
	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
	public List<CommentEntity> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentEntity> commentList) {
		this.commentList = commentList;
	}
	
	@Override
	public int compareTo(BoardEntity o) {
		return o.getIdx() - this.idx;
	}
}
