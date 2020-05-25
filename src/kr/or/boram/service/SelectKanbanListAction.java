package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.KanbanBoardDAO;
import kr.or.boram.dto.KanbanBoard;

public class SelectKanbanListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		KanbanBoardDAO dao = new KanbanBoardDAO();
		
		//보드리스트 가져오기
		List<KanbanBoard> list = dao.selectList();
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/kanbanBoard/kanban.jsp");
		
		return forward;
	}

}
