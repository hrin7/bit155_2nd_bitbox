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

@WebServlet("/UpdateKanbanCardName.ajax")
public class UpdateKanbanCardName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateKanbanCardName() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	HttpSession session = request.getSession();
    	KanbanBoard kanbanBoard = new KanbanBoard();
    	kanbanBoard.setKanbanTitle(request.getParameter("cardTitle"));
    	kanbanBoard.setKanbanNo(Integer.parseInt(request.getParameter("kanbanNo")));
    	
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	
    	//update card name
    	dao.updateKanbanCardName(kanbanBoard);
    	
//    	PrintWriter out = response.getWriter();
//		out.print(kanbanNo);
//		out.close();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
