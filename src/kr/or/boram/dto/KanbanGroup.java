package kr.or.boram.dto;

public class KanbanGroup {
	private int kanbanCode;
	private String listName;
	public int getKanbanCode() {
		return kanbanCode;
	}
	public void setKanbanCode(int kanbanCode) {
		this.kanbanCode = kanbanCode;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	@Override
	public String toString() {
		return "KanbanGroup [kanbanCode=" + kanbanCode + ", listName=" + listName + "]";
	}
}
