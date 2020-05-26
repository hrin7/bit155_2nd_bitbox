package kr.or.boram.dto;

public class BoardType {
	private int boardCode;
	private String boardName;
	
	public int getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	@Override
	public String toString() {
		return "BoardType [boardCode=" + boardCode + ", boardName=" + boardName + "]";
	}
}
