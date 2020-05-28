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

@WebServlet("/DeleteComment.ajax")
public class DeleteMyBoardComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteMyBoardComment() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	int boardNum = Integer.parseInt(request.getParameter("boardNum"));
    	int commentNum = Integer.parseInt(request.getParameter("commentNum"));
    	
    	Comment comment = new Comment();
    	comment.setBoardNum(boardNum);
    	comment.setCommentNum(commentNum);
    	
    	CommentDao dao = new CommentDao();
    	//댓글삭제
    	dao.deleteComment(comment);
    	//댓글 select
    	List<Comment> commentResult = dao.selectCommentList(boardNum);
		
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
