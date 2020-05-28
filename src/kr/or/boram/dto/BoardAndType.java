package kr.or.boram.dto;

public class BoardAndType {
	private int boardCode;
	private int no;
	private int views;
	private int commentCount;
	private String id;
	private String title;
	private String content;
	private String writeDate;
	private String boardName;
	
	//Getter and Setter
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
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
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
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	//Override toString
	@Override
	public String toString() {
		return "BoardAndType [boardCode=" + boardCode + ", no=" + no + ", views=" + views + ", commentCount="
				+ commentCount + ", id=" + id + ", title=" + title + ", content=" + content + ", writeDate=" + writeDate
				+ ", boardName=" + boardName + "]";
	}
}
