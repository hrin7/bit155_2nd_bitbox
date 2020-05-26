package kr.or.boram.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.KanbanBoardDAO;

@WebServlet("/InsertKanbanGroup.ajax")
public class InsertKanbanGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertKanbanGroup() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	dao.insertKanbanGroup(request.getParameter("listName"));
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
