package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.FreeBoardDAO;
import kr.or.boram.dto.Board;

public class UpdateFreeInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String boardCode = request.getParameter("boardCode");
		
		FreeBoardDAO freeBoardDao = new FreeBoardDAO();
		Board board = freeBoardDao.selectBoardByNo(Integer.parseInt(no));
		
		request.setAttribute("board", board);
		List<Object> boardAndBoardName = freeBoardDao.selectBoardByNo(Integer.parseInt(no), Integer.parseInt(boardCode));
		
		request.setAttribute("boardAndBoardName", boardAndBoardName);
		request.setAttribute("board", board);

		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/freeBoard/updateForm.jsp");
		return forward;
	}

}