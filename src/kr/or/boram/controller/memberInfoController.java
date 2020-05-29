package kr.or.boram.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.MemberInfoDAO;
import kr.or.boram.dto.MemberInfoDTO;
import kr.or.boram.service.MemberDeleteService;
import kr.or.boram.service.MemberListGetService;
import kr.or.boram.service.MemberUpdateGetService;
import kr.or.boram.service.MemberUpdateOkService;
import kr.or.boram.service.SignUpService;


@WebServlet("*.admin")
public class memberInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	    	
		/*
		 * if(url_Command.equals("/memberInfoRead.admin")) {
		 * 
		 * System.out.println("회원정보관리 페이지로 이동중...");
		 * 
		 * MemberInfoDAO info = new MemberInfoDAO(); List<MemberInfoDTO> memberInfo =
		 * info.memberSelect();
		 * 
		 * request.setAttribute("MemberInfo",memberInfo);
		 * 
		 * response.sendRedirect("/WEB-INF/views/MemberInfoBoard/MemberList.jsp");
		 * 
		 * //1.member 테이블에 있는 회원정보 얻어오는 서비스 //action = new MemberListGetService();
		 * //forward = action.execute(request, response);
		 * 
		 * } else
		 */if(url_Command.equals("/memberInfoRead.admin")) {
	    		
	    		System.out.println("회원정보관리 페이지로 이동중...");
	    		
//	    		MemberInfoDAO info = new MemberInfoDAO();
//	    		List<MemberInfoDTO> memberInfo = info.memberSelect();
//	    		
//	    		request.setAttribute("MemberInfo",memberInfo);
//	    		
//	    		response.sendRedirect("/MemberList.jsp");
	    		
	    		//1.member 테이블에 있는 회원정보 얻어오는 서비스  
	    		action = new MemberListGetService();
	    		forward = action.execute(request, response);
	    		  
	    	}else if(url_Command.equals("/memberInfoUpdate.admin")) {
	    		System.out.println("회원정보수정 페이지로 이동중...");
	    		String id = request.getParameter("id");
	    		System.out.println("해당 수정ID"+id);
	    		//1.member 테이블에 있는 해당 회원정보 얻어오는 서비스 -> 해당 회원정보를 얻으면 수정폼에 해당 회원정보를 뿌려줌   
	    		action = new MemberUpdateGetService();
	    		forward = action.execute(request, response);
	    	}else if(url_Command.equals("/memberInfoUpdateOk.admin")) {
	    		System.out.println("회원정보수정중 ...");
	    		
	    		//1.해당 회원 목록을 수정후 수정하기 버튼을 누르면 DB에 수정처리하는 서비스단 
	    		action = new MemberUpdateOkService();
	    		forward = action.execute(request, response);
	    	}else if(url_Command.equals("/memberInfoDelete.admin")) {
	    		System.out.println("해당 회원정보 삭제중 ...");
	    		
	    		//1.해당 회원 목록을 삭제하는 서비스단 
	    		action = new MemberDeleteService();
	    		forward = action.execute(request, response);
	    	}
	    	
	    	
	    	
	    
	    	if(forward != null) {
	    		if(forward.isRedirect()) { 
	    			System.out.println("리다이렉트 주소"+forward.getPath());
	    			response.sendRedirect(forward.getPath());
	    		} else { 
	    			System.out.println("포워드 주소"+forward.getPath());
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
