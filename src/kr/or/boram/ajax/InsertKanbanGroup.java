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
import kr.or.boram.dto.KanbanGroup;

@WebServlet("/InsertKanbanGroup.ajax")
public class InsertKanbanGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertKanbanGroup() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	HttpSession session = request.getSession();
    	
    	KanbanGroup kanbanGroup = new KanbanGroup();
    	kanbanGroup.setId((String)session.getAttribute("userID"));
    	kanbanGroup.setListName(request.getParameter("listName"));
    	
    	//insert하고 현재 시퀀스값 가져오기
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	int kanbanCode = dao.insertKanbanGroup(kanbanGroup);
    	
    	PrintWriter out = response.getWriter();
		out.print(kanbanCode);
		out.close();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
