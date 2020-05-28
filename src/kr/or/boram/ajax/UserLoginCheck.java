package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.SignDAO;
import kr.or.boram.dao.UserDAO;

/**
 * Servlet implementation class UserLoginCheck
 */
@WebServlet("/UserLoginCheck.ajax")
public class UserLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserLoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//ajax로 보낸 데이터(객체)를 서블릿단에서 어떻게 가져오지 ??? 
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");

		System.out.println("userid값 : "+userid);
		System.out.println("userpwd값 : "+userpwd);
				
		int result = new UserDAO().login(userid,userpwd); 
		System.out.println("결과값:"+result);
		
		PrintWriter out = response.getWriter();
		out.print(result); //보낼 데이터 전송 
		out.close();
	}

}

















