package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.FreeBoardCommentDAO;
import kr.or.boram.dao.FreeBoardDAO;

public class DeleteFreeBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		FreeBoardDAO freeBoardDao = new FreeBoardDAO();
		
		int no = Integer.parseInt(request.getParameter("no")); //삭제하려는 게시글 번호
		int result = freeBoardDao.deleteBoard(no);
		
		String msg = "";
		if(result > 0) {
			msg = "삭제되었습니다.";
		} else {
			msg = "삭제 실패";
		}
		
		request.setAttribute("msg", msg);
		
		ActionForward forward = new ActionForward();
		forward.setPath("selectBoardList.free");
		return forward;
	}

}
