package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.KanbanBoardDAO;
import kr.or.boram.dto.KanbanBoardAndGroup;
import kr.or.boram.dto.KanbanGroup;

public class SelectKanbanListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		KanbanBoardDAO dao = new KanbanBoardDAO();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userID");

		//칸반그룹 가져오기
		List<KanbanGroup> kanbanGroupList = dao.selectKanbanGroupList(id);
		request.setAttribute("kanbanGroupList", kanbanGroupList);
		
		//보드리스트 가져오기
		List<KanbanBoardAndGroup> kanbanList = dao.selectList(id);
		request.setAttribute("kanbanList", kanbanList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/kanbanBoard/kanban.jsp");
		
		return forward;
	}

}
