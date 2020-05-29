package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.MyBoardCommentDAO;
import kr.or.boram.dto.MyBoardComment;
import net.sf.json.JSONArray;

@WebServlet("/InsertMyBoardComment.ajax")
public class InsertMyBoardComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertMyBoardComment() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	
    	MyBoardComment comment = new MyBoardComment();
    	comment.setDiaryNo(Integer.parseInt(request.getParameter("diaryNo")));
    	comment.setDiaryCommentContent(request.getParameter("diaryCommentContent"));
    	
    	MyBoardCommentDAO dao = new MyBoardCommentDAO();
    	//댓글등록
    	dao.insertMyBoardComment(comment);
    	//댓글 select
    	List<MyBoardComment> commentResult = dao.selectMyBoardCommentList(Integer.parseInt(request.getParameter("diaryNo")));
		
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
