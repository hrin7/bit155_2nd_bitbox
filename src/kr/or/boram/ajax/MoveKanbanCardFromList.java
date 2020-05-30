package kr.or.boram.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.KanbanBoardDAO;

@WebServlet("/MoveKanbanCardFromList.ajax")
public class MoveKanbanCardFromList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MoveKanbanCardFromList() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	int kanbanNo = Integer.parseInt(request.getParameter("kanbanNo"));
    	int updateKanbanCode = Integer.parseInt(request.getParameter("updateKanbanCode"));
    	
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	//카드 삭제
    	dao.moveKanbanCardFromList(kanbanNo, updateKanbanCode);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
