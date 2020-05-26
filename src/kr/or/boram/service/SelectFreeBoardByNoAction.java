package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.FreeBoardDAO;

public class SelectFreeBoardByNoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String boardCode = request.getParameter("boardCode");
		FreeBoardDAO freeBoardDao = new FreeBoardDAO();
		
		//조회수 증가
		freeBoardDao.updateViews(Integer.parseInt(no));
		//게시판 상세보기
		List<Object> boardAndBoardName = freeBoardDao.selectBoardByNo(Integer.parseInt(no), Integer.parseInt(boardCode));
		request.setAttribute("boardAndBoardName", boardAndBoardName);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/freeBoard/boardDetail.jsp");
		return forward;
	}

}
