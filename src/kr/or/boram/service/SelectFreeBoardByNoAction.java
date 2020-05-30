package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.FreeBoardDAO;
import kr.or.boram.dto.BoardAndFileAndType;

public class SelectFreeBoardByNoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		int boardCode = Integer.parseInt(request.getParameter("boardCode"));
		FreeBoardDAO freeBoardDao = new FreeBoardDAO();
		
		HttpSession session = request.getSession();
		
		//조회수 증가
		freeBoardDao.updateViews(no);
		//게시판 상세보기
		BoardAndFileAndType boardAndBoardName = freeBoardDao.selectBoardByNo(no, boardCode);
		
		request.setAttribute("boardAndBoardName", boardAndBoardName);
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/freeBoard/boardDetail.jsp");
		return forward;
	}

}
