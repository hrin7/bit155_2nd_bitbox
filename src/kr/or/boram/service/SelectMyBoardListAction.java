package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.MyBoardDAO;
import kr.or.boram.dto.MyBoard;

public class SelectMyBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String cpage = request.getParameter("cp");
		String pageSize = request.getParameter("ps");
		int pageCount = 0;
		
		if(cpage == null) {
			cpage = "1";
		}
		if(pageSize == null) {
			pageSize = "10";
		}		
		int cp = Integer.parseInt(cpage);
		int ps = Integer.parseInt(pageSize);		
				
		MyBoardDAO dao = new MyBoardDAO();
		
		//게시물 총 건수
		int totalCountMyBoard = dao.totalCountMyBoard();
		request.setAttribute("totalCountMyBoard", totalCountMyBoard);
		
		
		
		if(totalCountMyBoard % ps == 0) {
			pageCount = totalCountMyBoard / ps; // 20 << 100/5
		} else {
			pageCount = (totalCountMyBoard / ps) + 1;
		}
		
		//글 목록 가져오기
		List<MyBoard> list = dao.selectMyBoardList(cp, ps);
		request.setAttribute("myBoardList", list);
		request.setAttribute("cpage", cp);
		request.setAttribute("pageSize", ps);
		request.setAttribute("pageCount", pageCount);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("WEB-INF/views/myBoard/myBoardList.jsp");
		
		return forward;
	}

}