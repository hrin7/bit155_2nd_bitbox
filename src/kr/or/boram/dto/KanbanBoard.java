package kr.or.boram.dto;

public class KanbanBoard {
	private String id;
	private int kanbanNo;
	private String kanbanTitle;
	private String kanbanContent;
	private String kanbanDate;
	private String kanbanFileName;
	private int kanbanFileSize;
	private int kanbanFileCount;
	private int kanbanCommentCount;
	private int kanbanCode;
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
	public String getKanbanTitle() {
		return kanbanTitle;
	}
	public void setKanbanTitle(String kanbanTitle) {
		this.kanbanTitle = kanbanTitle;
	}
	public String getKanbanContent() {
		return kanbanContent;
	}
	public void setKanbanContent(String kanbanContent) {
		this.kanbanContent = kanbanContent;
	}
	public String getKanbanDate() {
		return kanbanDate;
	}
	public void setKanbanDate(String kanbanDate) {
		this.kanbanDate = kanbanDate;
	}
	public String getKanbanFileName() {
		return kanbanFileName;
	}
	public void setKanbanFileName(String kanbanFileName) {
		this.kanbanFileName = kanbanFileName;
	}
	public int getKanbanFileSize() {
		return kanbanFileSize;
	}
	public void setKanbanFileSize(int kanbanFileSize) {
		this.kanbanFileSize = kanbanFileSize;
	}
	public int getKanbanFileCount() {
		return kanbanFileCount;
	}
	public void setKanbanFileCount(int kanbanFileCount) {
		this.kanbanFileCount = kanbanFileCount;
	}
	public int getKanbanCommentCount() {
		return kanbanCommentCount;
	}
	public void setKanbanCommentCount(int kanbanCommentCount) {
		this.kanbanCommentCount = kanbanCommentCount;
	}
	public int getKanbanCode() {
		return kanbanCode;
	}
	public void setKanbanCode(int kanbanCode) {
		this.kanbanCode = kanbanCode;
	}
	@Override
	public String toString() {
		return "KanbanBoard [id=" + id + ", kanbanNo=" + kanbanNo + ", kanbanTitle=" + kanbanTitle + ", kanbanContent="
				+ kanbanContent + ", kanbanDate=" + kanbanDate + ", kanbanFileName=" + kanbanFileName
				+ ", kanbanFileSize=" + kanbanFileSize + ", kanbanFileCount=" + kanbanFileCount
				+ ", kanbanCommentCount=" + kanbanCommentCount + ", kanbanCode=" + kanbanCode + "]";
	}
}
