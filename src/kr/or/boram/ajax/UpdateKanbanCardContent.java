package kr.or.boram.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.KanbanBoardDAO;
import kr.or.boram.dto.KanbanBoard;

@WebServlet("/UpdateKanbanCardContent.ajax")
public class UpdateKanbanCardContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateKanbanCardContent() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	KanbanBoard kanbanBoard = new KanbanBoard();
    	kanbanBoard.setKanbanContent(request.getParameter("kanbanContent"));
    	kanbanBoard.setKanbanNo(Integer.parseInt(request.getParameter("kanbanNo")));
    	
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	
    	//update card content
    	dao.updateKanbanCardContent(kanbanBoard);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
