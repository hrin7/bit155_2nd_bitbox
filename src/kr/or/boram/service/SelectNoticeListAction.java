package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.BoardDAO;
import kr.or.boram.dto.Board;

public class SelectNoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String boardCode = request.getParameter("boardCode");
		if(boardCode == null) {
			boardCode = "1";
		}
		String cpage = request.getParameter("cp");
		String pageSize = request.getParameter("ps");
		int pageCount = 0;
		
		if(cpage == null) {
			cpage = "1";
		}
		if(pageSize == null) {
			pageSize = "5";
		}
		
		int cp = Integer.parseInt(cpage);
		int ps = Integer.parseInt(pageSize);
		
		BoardDAO dao = new BoardDAO();
		
		//게시물 총 건수
		int totalBoardCount = dao.totalBoardCount();
		request.setAttribute("totalBoardCount", totalBoardCount);
		
		if(totalBoardCount % ps == 0) {
			pageCount = totalBoardCount / ps;
		} else {
			pageCount = (totalBoardCount / ps) + 1;
		}
		
		//게시판리스트 가져오기
		List<Board> boardList = dao.boardList(cp, ps, Integer.parseInt(boardCode));
		request.setAttribute("boardList", boardList);
		request.setAttribute("cpage", cpage);
		request.setAttribute("pageSize", ps);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("boardCode", boardCode);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("WEB-INF/views/noticeBoard/noticeBoardList.jsp");
		return forward;
		
		
	}


}
