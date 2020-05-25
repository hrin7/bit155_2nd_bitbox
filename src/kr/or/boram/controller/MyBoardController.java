package kr.or.boram.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.service.InsertBoardAction;
import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.service.SelectMyBoardListAction;

@WebServlet("*.my")
public class MyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyBoardController() {
        super();
    }
	
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
    	
//    	if(url_Command.equals("")) {
//    		//UI + 로직
//    		action = new InsertEmpAction();
//    		forward = action.execute(request, response);
//    		
//    		//UI만 제공 시
//    		String viewPage = "/login.jsp";
//    		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
//			dis.forward(request, response);  
//    	}
    	
    	//마이보드리스트 가져오기
    	if(url_Command.equals("/myBoardList.my")) {
    		action = new SelectMyBoardListAction();
    		forward = action.execute(request, response);
    	}
    	
    	//글 작성하는 폼으로 보내기
    	else if(url_Command.equals("/myBoardInsertForm.my")) {
    		forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/views/myBoard/myBoardInsert.jsp");
    	}
    	
    	//글 작성하기
    	else if(url_Command.equals("/myBoardInsert.my")) {
    		action = new InsertBoardAction();
    		forward = action.execute(request, response);
    	}
    	
    	//뷰 지정하기
    	if(forward != null) {
    		if(forward.isRedirect()) { //true면 redirect 하겠다.
    			response.sendRedirect(forward.getPath());
    		} else { //false(모든 자원) 사용
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}    
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
