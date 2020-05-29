package kr.or.boram.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.KanbanBoardDAO;

@WebServlet("/UpdateKanbanListName.ajax")
public class UpdateKanbanListName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateKanbanListName() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	String oriListName = request.getParameter("oriListName");
    	String updateListName = request.getParameter("updateListName");
    	
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	
    	//update list name
    	dao.updateKanbanListName(oriListName, updateListName);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
