package kr.or.bit.dto;

public class MyBoard {
	private String id;
	private int diaryNo;
	private String diaryTitle;
	private String diaryContent;
	private String diaryDate;
	private int diaryRefer;
	private int diaryDepth;
	private int diaryStep;
	private String diaryFileName;
	private int diaryCommentCount;
	
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
	public String getDiaryTitle() {
		return diaryTitle;
	}
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}
	public String getDiaryContent() {
		return diaryContent;
	}
	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}
	public String getDiaryDate() {
		return diaryDate;
	}
	public void setDiaryDate(String diaryDate) {
		this.diaryDate = diaryDate;
	}
	public int getDiaryRefer() {
		return diaryRefer;
	}
	public void setDiaryRefer(int diaryRefer) {
		this.diaryRefer = diaryRefer;
	}
	public int getDiaryDepth() {
		return diaryDepth;
	}
	public void setDiaryDepth(int diaryDepth) {
		this.diaryDepth = diaryDepth;
	}
	public int getDiaryStep() {
		return diaryStep;
	}
	public void setDiaryStep(int diaryStep) {
		this.diaryStep = diaryStep;
	}
	public String getDiaryFileName() {
		return diaryFileName;
	}
	public void setDiaryFileName(String diaryFileName) {
		this.diaryFileName = diaryFileName;
	}
	public int getDiaryCommentCount() {
		return diaryCommentCount;
	}
	public void setDiaryCommentCount(int diaryCommentCount) {
		this.diaryCommentCount = diaryCommentCount;
	}
	
	@Override
	public String toString() {
		return "MyBoard [id=" + id + ", diaryNo=" + diaryNo + ", diaryTitle=" + diaryTitle + ", diaryContent="
				+ diaryContent + ", diaryDate=" + diaryDate + ", diaryRefer=" + diaryRefer + ", diaryDepth="
				+ diaryDepth + ", diaryStep=" + diaryStep + ", diaryFileName=" + diaryFileName + ", diaryCommentCount="
				+ diaryCommentCount + ", getId()=" + getId() + ", getDiaryNo()=" + getDiaryNo() + ", getDiaryTitle()="
				+ getDiaryTitle() + ", getDiaryContent()=" + getDiaryContent() + ", getDiaryDate()=" + getDiaryDate()
				+ ", getDiaryRefer()=" + getDiaryRefer() + ", getDiaryDepth()=" + getDiaryDepth() + ", getDiaryStep()="
				+ getDiaryStep() + ", getDiaryFileName()=" + getDiaryFileName() + ", getDiaryCommentCount()="
				+ getDiaryCommentCount() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}	
	
}