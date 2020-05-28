package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.MyBoardDAO;
import kr.or.boram.dto.MyBoard;


public class SelectMyBoardByNoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String diaryNo = request.getParameter("diaryNo");
		//System.out.println(diaryNo);
		
		MyBoardDAO dao = new MyBoardDAO();			
		MyBoard board = dao.selectMyBoardByNo(Integer.parseInt(diaryNo));
		
		HttpSession session = request.getSession();
		
		request.setAttribute("myBoard", board);
		//System.out.println(board); //잘 넘어 왔나 찍어보기
		
		ActionForward forward = new ActionForward();
		forward.setPath("WEB-INF/views/myBoard/myBoardDetail.jsp");
		
		return forward;
	}

}
