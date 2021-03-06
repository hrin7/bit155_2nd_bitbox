package kr.or.boram.dto;

public class BoardAndFileAndReply {
	private int boardCode;
	private int no;
	private String id;
	private String title;
	private String content;
	private int views;
	private String writeDate;
	private int commentCount;
	private int fileNo;
	private String fileName;
	private int replyNo;
	private int refer;
	private int depth;
	private int step;
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
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	@Override
	public String toString() {
		return "BoardAndFileAndReply [boardCode=" + boardCode + ", no=" + no + ", id=" + id + ", title=" + title
				+ ", content=" + content + ", views=" + views + ", writeDate=" + writeDate + ", commentCount="
				+ commentCount + ", fileNo=" + fileNo + ", fileName=" + fileName + ", replyNo=" + replyNo + ", refer="
				+ refer + ", depth=" + depth + ", step=" + step + "]";
	}
}
