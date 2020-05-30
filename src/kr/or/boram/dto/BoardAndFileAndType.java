package kr.or.boram.dto;

public class BoardAndFileAndType {
	private int boardCode;
	private String boardName;
	private int no;
	private String id;
	private String title;
	private String content;
	private int views;
	private String writeDate;
	private int commentCount;
	private int fileNo;
	private String fileName;
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
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	@Override
	public String toString() {
		return "BoardAndFileAndType [boardCode=" + boardCode + ", boardName=" + boardName + ", no=" + no + ", id=" + id
				+ ", title=" + title + ", content=" + content + ", views=" + views + ", writeDate=" + writeDate
				+ ", commentCount=" + commentCount + ", fileNo=" + fileNo + ", fileName=" + fileName + "]";
	}
}
