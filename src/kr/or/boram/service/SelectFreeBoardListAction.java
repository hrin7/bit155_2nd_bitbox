package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.FreeBoardDAO;
import kr.or.boram.dto.Board;

public class SelectFreeBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		FreeBoardDAO freeBoardDao = new FreeBoardDAO();
		
		//게시판리스트 가져오기
		List<Board> boardList = freeBoardDao.boardList();
		request.setAttribute("boardList", boardList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("WEB-INF/views/freeBoard/freeBoardList.jsp");
		return forward;
		
		
	}

}
