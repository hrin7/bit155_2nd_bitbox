package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.FreeBoardCommentDAO;
import kr.or.boram.dto.BoardComment;
import net.sf.json.JSONArray;


@WebServlet("/SelectFreeCommentList.ajax")
public class SelectFreeCommentList extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public SelectFreeCommentList() {
		super();
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("no"));
		
		FreeBoardCommentDAO commentDao = new FreeBoardCommentDAO();
		List<BoardComment> commentList = commentDao.selectCommentList(no);
		
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
