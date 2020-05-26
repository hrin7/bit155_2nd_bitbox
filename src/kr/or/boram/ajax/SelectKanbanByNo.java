package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.KanbanBoardDAO;
import kr.or.boram.dto.KanbanBoard;
import net.sf.json.JSONObject;

@WebServlet("/SelectKanbanByNo.ajax")
public class SelectKanbanByNo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectKanbanByNo() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	KanbanBoard kanban = dao.selectKanbanByNo(Integer.parseInt(request.getParameter("kanbanNo")));
		
		JSONObject obj = JSONObject.fromObject(kanban);
		PrintWriter out = response.getWriter();
		out.print(obj);
		out.close();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
