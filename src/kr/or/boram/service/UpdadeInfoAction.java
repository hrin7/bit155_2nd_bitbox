package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.FreeBoardDAO;
import kr.or.boram.dto.Board;

public class UpdadeInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		
		FreeBoardDAO freeBoardDao = new FreeBoardDAO();
		
		//Board board = freeBoardDao.selectBoardByNo(Integer.parseInt(no));
		
		//request.setAttribute("board", board);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/freeBoard/updateForm.jsp");
		return forward;
	}

}
