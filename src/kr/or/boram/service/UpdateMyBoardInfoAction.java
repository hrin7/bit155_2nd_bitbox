package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.MyBoardDAO;
import kr.or.boram.dto.MyBoard;
import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;

public class UpdateMyBoardInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String diaryNo = request.getParameter("diaryNo");
		MyBoardDAO dao = new MyBoardDAO();
		
		//작성된 글 보기(상세보기)		
		MyBoard board = dao.selectMyBoardByNo(Integer.parseInt(diaryNo));
		
		request.setAttribute("myBoard", board);		
		
		ActionForward forward = new ActionForward();
		forward.setPath("WEB-INF/views/myBoard/myBoardUpdate.jsp");
		
		return forward;
	}

}
