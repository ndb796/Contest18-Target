package com.mycompany.myschool.web.entity.sns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EmotionEntity {
	@XmlElement(name = "no")
	private int no;
	@XmlElement(name = "idx")
	private int idx;
	@XmlElement(name = "board_id")
	private String board_id;
	@XmlElement(name = "emotion_h")
	private int emotion_h;
	@XmlElement(name = "emotion_f")
	private int emotion_f;
	@XmlElement(name = "emotion_k")
	private int emotion_k;
	@XmlElement(name = "emotion_n")
	private int emotion_n;
	@XmlElement(name = "emotion_s")
	private int emotion_s;
	
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
	public int getEmotion_h() {
		return emotion_h;
	}
	public void setEmotion_h(int emotion_h) {
		this.emotion_h = emotion_h;
	}
	public int getEmotion_f() {
		return emotion_f;
	}
	public void setEmotion_f(int emotion_f) {
		this.emotion_f = emotion_f;
	}
	public int getEmotion_k() {
		return emotion_k;
	}
	public void setEmotion_k(int emotion_k) {
		this.emotion_k = emotion_k;
	}
	public int getEmotion_n() {
		return emotion_n;
	}
	public void setEmotion_n(int emotion_n) {
		this.emotion_n = emotion_n;
	}
	public int getEmotion_s() {
		return emotion_s;
	}
	public void setEmotion_s(int emotion_s) {
		this.emotion_s = emotion_s;
	}
}
