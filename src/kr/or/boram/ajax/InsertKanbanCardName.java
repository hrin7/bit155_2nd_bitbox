package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.boram.dao.KanbanBoardDAO;
import kr.or.boram.dto.KanbanBoard;

@WebServlet("/InsertKanbanCardName.ajax")
public class InsertKanbanCardName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertKanbanCardName() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	HttpSession session = request.getSession();
    	KanbanBoard kanbanBoard = new KanbanBoard();
    	kanbanBoard.setId("hyerin");
    	kanbanBoard.setKanbanTitle(request.getParameter("cardTitle"));
    	
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	int kanbanCode = dao.insertKanbanCardName(request.getParameter("listName"));
    	kanbanBoard.setKanbanCode(kanbanCode);
    	
    	//insert하고 현재 시퀀스값 가져오기
    	int kanbanNo = dao.insertKanbanCardName(kanbanBoard);
    	
    	PrintWriter out = response.getWriter();
		out.print(kanbanNo);
		out.close();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
