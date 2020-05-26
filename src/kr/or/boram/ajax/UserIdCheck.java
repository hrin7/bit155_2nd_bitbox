package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.SignDAO;

@WebServlet("/UserIdCheck.ajax")
public class UserIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String userID = request.getParameter("id");
		System.out.println("유저 아이디"+userID);
		
		int result = new SignDAO().idCheck(userID);
		System.out.println("결과값:"+result); // 0 - 아이디 사용가능 , 1 - 아이디 사용불가능 , -1 - 데이터 베이스 오류
		
		//response.getWriter().write(result);
		
		PrintWriter out = response.getWriter();
		out.print(result); //보낼 데이터 전송 
		out.close();
	}

}