package kr.or.boram.dto;

public class KanbanComment {
	private int kanbanCommentNo;
	private int kanbanNo;
	private String kanbanCommentContent;
	private String kanbanCommentDate;
	public int getKanbanCommentNo() {
		return kanbanCommentNo;
	}
	public void setKanbanCommentNo(int kanbanCommentNo) {
		this.kanbanCommentNo = kanbanCommentNo;
	}
	public int getKanbanNo() {
		return kanbanNo;
	}
	public void setKanbanNo(int kanbanNo) {
		this.kanbanNo = kanbanNo;
	}
	public String getKanbanCommentContent() {
		return kanbanCommentContent;
	}
	public void setKanbanCommentContent(String kanbanCommentContent) {
		this.kanbanCommentContent = kanbanCommentContent;
	}
	public String getKanbanCommentDate() {
		return kanbanCommentDate;
	}
	public void setKanbanCommentDate(String kanbanCommentDate) {
		this.kanbanCommentDate = kanbanCommentDate;
	}
	@Override
	public String toString() {
		return "KanbanComment [kanbanCommentNo=" + kanbanCommentNo + ", kanbanNo=" + kanbanNo
				+ ", kanbanCommentContent=" + kanbanCommentContent + ", kanbanCommentDate=" + kanbanCommentDate + "]";
	}
}
