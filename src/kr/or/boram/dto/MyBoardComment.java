package kr.or.boram.dto;

public class MyBoardComment {
	private String id;
	private int diaryNo;
	private int diaryCommentNo;
	private String diaryCommentContent;
	private String diaryCommentDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDiaryNo() {
		return diaryNo;
	}
	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}
	public int getDiaryCommentNo() {
		return diaryCommentNo;
	}
	public void setDiaryCommentNo(int diaryCommentNo) {
		this.diaryCommentNo = diaryCommentNo;
	}
	public String getDiaryCommentContent() {
		return diaryCommentContent;
	}
	public void setDiaryCommentContent(String diaryCommentContent) {
		this.diaryCommentContent = diaryCommentContent;
	}
	public String getDiaryCommentDate() {
		return diaryCommentDate;
	}
	public void setDiaryCommentDate(String diaryCommentDate) {
		this.diaryCommentDate = diaryCommentDate;
	}
	
	@Override
	public String toString() {
		return "MyBoardComment [id=" + id + ", diaryNo=" + diaryNo + ", diaryCommentNo=" + diaryCommentNo
				+ ", diaryCommentContent=" + diaryCommentContent + ", diaryCommentDate=" + diaryCommentDate
				+ ", getId()=" + getId() + ", getDiaryNo()=" + getDiaryNo() + ", getDiaryCommentNo()="
				+ getDiaryCommentNo() + ", getDiaryCommentContent()=" + getDiaryCommentContent()
				+ ", getDiaryCommentDate()=" + getDiaryCommentDate() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
