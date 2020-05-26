package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.FreeBoardDAO;
import kr.or.boram.dto.Board;
import kr.or.boram.dto.BoardType;
import net.sf.json.JSONArray;

@WebServlet("/SelectBoardType.ajax")
public class SelectBoardType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public SelectBoardType() {
		super();
	}
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
    	FreeBoardDAO freeBoardDao = new FreeBoardDAO();
		List<BoardType> list = freeBoardDao.SelectBoardType();
		
		JSONArray obj = JSONArray.fromObject(list);
		
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
