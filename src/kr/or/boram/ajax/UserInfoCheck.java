package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.dao.SignDAO;

/**
 * Servlet implementation class UserInfoCheck
 */
@WebServlet("/UserInfoCheck.ajax")
public class UserInfoCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//ajax로 보낸 데이터(객체)를 서블릿단에서 어떻게 가져오지 ??? 
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		System.out.println("id값 : "+id);
		System.out.println("nickname값 : "+nickname);
		System.out.println("email값 : "+email);
				
		int result = new SignDAO().signUpCheck(id, nickname, email); // 10(중복된ID) , 11(중복된닉네임) , 12(중복된이메일) , -1(데이터베이스 오류) , 1(중복검사시 중복된내용 없을경우)
		System.out.println("결과값:"+result);
		
		PrintWriter out = response.getWriter();
		out.print(result); //보낼 데이터 전송 
		out.close();
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String json = request.getParameter("userData");
//		System.out.println("얻오온데이터"+json);
//				
//	}


}
