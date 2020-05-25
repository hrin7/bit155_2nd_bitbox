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
		MyBoardDAO dao = new MyBoardDAO();
		
		//글 목록 가져오기
		List<MyBoard> list = dao.selectMyBoardList();
		request.setAttribute("myBoardList", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/views/myBoard/myBoardList.jsp");
		
		return forward;
	}

}