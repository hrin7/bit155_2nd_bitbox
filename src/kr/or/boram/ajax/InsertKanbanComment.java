package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.KanbanCommentDAO;
import kr.or.boram.dto.KanbanComment;
import net.sf.json.JSONArray;

@WebServlet("/InsertKanbanComment.ajax")
public class InsertKanbanComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertKanbanComment() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	KanbanComment comment = new KanbanComment();
    	comment.setKanbanNo(Integer.parseInt(request.getParameter("kanbanNo")));
    	comment.setKanbanCommentContent(request.getParameter("content"));
    	
    	KanbanCommentDAO dao = new KanbanCommentDAO();
    	//댓글등록
    	dao.insertKanbanComment(comment);
    	//댓글 select
    	List<KanbanComment> commentResult = dao.selectKanbanCommentList(Integer.parseInt(request.getParameter("kanbanNo")));
		
    	JSONArray obj = JSONArray.fromObject(commentResult);
    	
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
