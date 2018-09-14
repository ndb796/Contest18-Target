package com.mycompany.myschool.web.entity.customer;

import java.sql.Timestamp;

public class CustomerRefEntity {
	private int idx;
	private String sb_id;
	private String filename1;
	private long filesize1;
	private int filetype1;
	private int downcnt1;
	private String filename2;
	private long filesize2;
	private int filetype2;
	private int downcnt2;
	private String filename3;
	private long filesize3;
	private int filetype3;
	private int downcnt3;
	private Timestamp upd_date;

	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getIdx() {
		return this.idx;
	}
	public void setSb_id(String sb_id) {
		this.sb_id = sb_id;
	}
	public String getSb_id() {
		return this.sb_id;
	}
	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}
	public String getFilename1() {
		return this.filename1;
	}
	public void setFilesize1(long filesize1) {
		this.filesize1 = filesize1;
	}
	public long getFilesize1() {
		return this.filesize1;
	}
	public void setFiletype1(int filetype1) {
		this.filetype1 = filetype1;
	}
	public int getFiletype1() {
		return this.filetype1;
	}
	public void setDowncnt1(int downcnt1) {
		this.downcnt1 = downcnt1;
	}
	public int getDowncnt1() {
		return this.downcnt1;
	}
	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}
	public String getFilename2() {
		return this.filename2;
	}
	public void setFilesize2(long filesize2) {
		this.filesize2 = filesize2;
	}
	public long getFilesize2() {
		return this.filesize2;
	}
	public void setFiletype2(int filetype2) {
		this.filetype2 = filetype2;
	}
	public int getFiletype2() {
		return this.filetype2;
	}
	public void setDowncnt2(int downcnt2) {
		this.downcnt2 = downcnt2;
	}
	public int getDowncnt2() {
		return this.downcnt2;
	}
	public void setFilename3(String filename3) {
		this.filename3 = filename3;
	}
	public String getFilename3() {
		return this.filename3;
	}
	public void setFilesize3(long filesize3) {
		this.filesize3 = filesize3;
	}
	public long getFilesize3() {
		return this.filesize3;
	}
	public void setFiletype3(int filetype3) {
		this.filetype3 = filetype3;
	}
	public int getFiletype3() {
		return this.filetype3;
	}
	public void setDowncnt3(int downcnt3) {
		this.downcnt3 = downcnt3;
	}
	public int getDowncnt3() {
		return this.downcnt3;
	}
	public void setUpd_date(Timestamp upd_date) {
		this.upd_date = upd_date;
	}
	public Timestamp getUpd_date() {
		return this.upd_date;
	}

}