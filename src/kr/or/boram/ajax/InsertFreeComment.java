package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.boram.dao.FreeBoardCommentDAO;
import kr.or.boram.dto.BoardComment;
import net.sf.json.JSONArray;

@WebServlet("/InsertFreeComment.ajax")
public class InsertFreeComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public InsertFreeComment() {
		super();
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
	    BoardComment boardComment = new BoardComment();
	    boardComment.setNo(Integer.parseInt(request.getParameter("no")));
	    boardComment.setId((String)session.getAttribute("userID"));
	    boardComment.setCommentContent(request.getParameter("commentContent"));
	    
	    FreeBoardCommentDAO commentDao = new FreeBoardCommentDAO();
	    //댓글등록
	    commentDao.insertComment(boardComment);
	    //댓글 sclect
	    List<BoardComment> commentResult = commentDao.selectCommentList(Integer.parseInt(request.getParameter("no")));
	    
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
