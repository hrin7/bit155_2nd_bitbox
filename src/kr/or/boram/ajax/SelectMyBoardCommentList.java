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

@WebServlet("/SelectMyBoardCommentList.ajax")
public class SelectMyBoardCommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectMyBoardCommentList() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	int boardNum = Integer.parseInt(request.getParameter("boardNum"));
    	
    	CommentDao dao = new CommentDao();
		List<Comment> commentList = dao.selectCommentList(boardNum);
		
		JSONArray obj = JSONArray.fromObject(commentList);
		
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
