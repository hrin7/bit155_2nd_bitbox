package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.MyBoardDAO;

public class DeleteMyBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int diaryNo = Integer.parseInt(request.getParameter("diaryNo"));
		
		MyBoardDAO dao = new MyBoardDAO();
		int result = dao.deleteMyBoard(diaryNo);
		System.out.println("리절트 : " + result);
		
		String msg = "";
		if(result > 0) {
			msg = "삭제되었습니다.";
		} else {
			msg = "삭제 실패";
		}
		request.setAttribute("msg", msg);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/myBoardList.my");
		
		return forward;
	}

}