package com.koreait.board;

public class BoardDTO {
	private int idx;
	private String userid;
	private String title;
	private String content;
	private String regdate;
	private String hit;
	private String likke;
	private String file;

	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getLike() {
		return likke;
	}
	public void setLike(String like) {
		this.likke = like;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "BoardDTO [idx=" + idx + ", userid=" + userid + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", hit=" + hit + ", like=" + likke + ", file=" + file + "]";
	}



		
	
}
