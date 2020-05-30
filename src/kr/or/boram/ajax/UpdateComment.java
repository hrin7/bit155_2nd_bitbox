package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.BoardCommentDAO;
import kr.or.boram.dto.BoardComment;
import net.sf.json.JSONArray;

@WebServlet("/UpdateComment.ajax")
public class UpdateComment {
	private static final long serialVersionUID = 1L;
	
	public UpdateComment() {
		super();
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	int no = Integer.parseInt(request.getParameter("no"));
    	int commentNo = Integer.parseInt(request.getParameter("commentNo"));
    	String commentContent = request.getParameter("commentContent");
    	
    	BoardComment boardComment = new BoardComment();
    	boardComment.setNo(no);
    	boardComment.setCommentNo(commentNo);
    	boardComment.setCommentContent(commentContent);
    	
    	BoardCommentDAO commentDao = new BoardCommentDAO();
    	
    	//댓글삭제
    	commentDao.updateComment(boardComment);
    	//댓글 select 
    	List<BoardComment> commentResult = commentDao.selectCommentList(commentNo);
    	
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
