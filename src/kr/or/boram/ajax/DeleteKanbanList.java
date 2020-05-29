package kr.or.boram.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.KanbanBoardDAO;

@WebServlet("/DeleteKanbanList.ajax")
public class DeleteKanbanList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteKanbanList() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	int kanbanCode = Integer.parseInt(request.getParameter("kanbanCode"));
    	
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	//리스트 삭제
    	dao.deleteKanbanList(kanbanCode);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
