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

@WebServlet("/UpdateKanbanComment.ajax")
public class UpdateKanbanComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateKanbanComment() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	int kanbanNo = Integer.parseInt(request.getParameter("kanbanNo"));
    	int kanbanCommentNo = Integer.parseInt(request.getParameter("kanbanCommentNo"));
    	String content = request.getParameter("content");
    	
    	KanbanComment comment = new KanbanComment();
    	comment.setKanbanNo(kanbanNo);
    	comment.setKanbanCommentNo(kanbanCommentNo);;
    	comment.setKanbanCommentContent(content);;
    	
    	KanbanCommentDAO dao = new KanbanCommentDAO();
    	//댓글수정
    	dao.updateKanbanComment(comment);
    	//댓글 select
    	List<KanbanComment> commentResult = dao.selectKanbanCommentList(kanbanNo);
		
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
