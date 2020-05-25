package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.FreeBoardDAO;
import kr.or.boram.dto.Board;

public class SelectFreeBoardByNoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		FreeBoardDAO freeBoardDao = new FreeBoardDAO();
		
		//조회수 증가
		int viewsResult = freeBoardDao.updateViews(Integer.parseInt(no));
		//게시판 상세보기
		Board board = freeBoardDao.selectBoardByNo(Integer.parseInt(no));
		System.out.println(board);
		request.setAttribute("board", board);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/FreeBoard/boardDetail.jsp");
		return forward;
	}

}
