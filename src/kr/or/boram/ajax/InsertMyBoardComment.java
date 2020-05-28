package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.CommentDao;
import kr.or.bit.dto.Comment;
import net.sf.json.JSONArray;

@WebServlet("/InsertComment.ajax")
public class InsertMyBoardComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertMyBoardComment() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	Comment comment = new Comment();
    	comment.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
    	comment.setCommentName(request.getParameter("name"));
    	comment.setCommentContent(request.getParameter("content"));
    	
    	CommentDao dao = new CommentDao();
    	//댓글등록
    	dao.insertComment(comment);
    	//댓글 select
    	List<Comment> commentResult = dao.selectCommentList(Integer.parseInt(request.getParameter("boardNum")));
		
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
