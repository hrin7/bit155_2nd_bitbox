package kr.or.boram.dto;

public class KanbanComment {
	private String id;
	private int kanbanNo;
	private String kanbanCommentContent;
	private String kanbanCommentDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		return "KanbanComment [id=" + id + ", kanbanNo=" + kanbanNo + ", kanbanCommentContent=" + kanbanCommentContent
				+ ", kanbanCommentDate=" + kanbanCommentDate + "]";
	}
}
