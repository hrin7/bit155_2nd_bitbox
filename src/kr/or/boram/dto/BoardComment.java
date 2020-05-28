package kr.or.boram.dto;

public class BoardComment {
	private int no;
	private int commentNo;
	private String commentContent;
	private String id;
	private String commentDate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	
	@Override
	public String toString() {
		return "BoardComment [no=" + no + ", commentNo=" + commentNo + ", commentContent=" + commentContent + ", id="
				+ id + ", commentDate=" + commentDate + "]";
	}
}
