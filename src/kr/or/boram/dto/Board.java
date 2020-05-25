package kr.or.boram.dto;

public class Board {
	private int boardCode;
	private int no;
	private String id;
	private String title;
	private String content;
	private int views;
	private String writeDate;
	private int commentCount;
	
	public int getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	@Override
	public String toString() {
		return "Board [boardCode=" + boardCode + ", no=" + no + ", id=" + id + ", title=" + title + ", content="
				+ content + ", views=" + views + ", writeDate=" + writeDate + ", commentCount=" + commentCount + "]";
	}
}
