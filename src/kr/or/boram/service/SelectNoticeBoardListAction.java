package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.NoticeBoardDAO;
import kr.or.boram.dto.Board;

public class SelectNoticeBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
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
		
		NoticeBoardDAO noticeBoardDao = new NoticeBoardDAO();
		
		//게시물 총 건수
		int totalBoardCount = noticeBoardDao.totalBoardCount();
		request.setAttribute("totalBoardCount", totalBoardCount);
		
		if(totalBoardCount % ps == 0) {
			pageCount = totalBoardCount / ps;
		} else {
			pageCount = (totalBoardCount / ps) + 1;
		}
		
		//게시판리스트 가져오기
		List<Board> boardList = noticeBoardDao.boardList(cp, ps);
		request.setAttribute("boardList", boardList);
		request.setAttribute("cpage", cpage);
		request.setAttribute("pageSize", ps);
		request.setAttribute("pageCount", pageCount);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("WEB-INF/views/noticeBoard/noticeBoardList.jsp");
		return forward;
		
		
	}


}
