package kr.or.boram.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.service.LoginService;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
        super();
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	System.out.println("requestURI : " + requestURI);
    	System.out.println("contextPath : " + contextPath);
    	System.out.println("url_Command : " + url_Command);
    	
    	ActionForward forward = null;
    	Action action = null;
    	
    	if(url_Command.equals("/login.user")) {
    		
    		action = new LoginService();
    		forward = action.execute(request, response);
    		
    		RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
			dis.forward(request, response);
    		//UI + 로직
//    		action = new InsertEmpAction();
//    		forward = action.execute(request, response);
    		
    		//UI만 제공 시
//    		String viewPage = "/login.jsp";
//    		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
//			dis.forward(request, response);  
    	}else if(url_Command.equals("/logout.user")) {
    		
    		HttpSession session = request.getSession();
    		session.invalidate();
    		
    		response.sendRedirect(contextPath+"/index.jsp");
    		
    		

    	}  
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
