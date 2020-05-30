package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.BoardDAO;
import kr.or.boram.dao.NoticeBoardDAO;
import kr.or.boram.dto.Board;

public class UpdateNoticeInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String boardCode = request.getParameter("boardCode");
		
		NoticeBoardDAO noticeBoardDao = new NoticeBoardDAO();
		Board board = noticeBoardDao.selectBoardByNo(Integer.parseInt(no));
		
		request.setAttribute("board", board);
		List<Object> boardAndBoardName = noticeBoardDao.selectBoardByNo(Integer.parseInt(no), Integer.parseInt(boardCode));
		
		request.setAttribute("boardAndBoardName", boardAndBoardName);
		request.setAttribute("board", board);

		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/noticeBoard/updateForm.jsp");
		return forward;
	}

}
