package kr.or.boram.action;

public class ActionForward {
	private boolean isRedirect = false; //true면 redirect(O) false면 redirect(X)
	private String path = null; //이동경로(뷰의 주소)
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
