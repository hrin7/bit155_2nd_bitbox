package kr.or.boram.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.service.DeleteFreeBoardAction;
import kr.or.boram.service.InsertFreeBoardAction;
import kr.or.boram.service.SelectFreeBoardByNoAction;
import kr.or.boram.service.SelectFreeBoardListAction;
import kr.or.boram.service.UpdadeInfoAction;
import kr.or.boram.service.UpdateFreeBoardAction;

@WebServlet("*.free")
public class FreeBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public FreeBoardController() {
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
    	
    	//게시판 목록보기
    	if(url_Command.equals("/selectBoardList.free")) {
    		action = new SelectFreeBoardListAction();
    		forward = action.execute(request, response);
    		
    	//게시판 상세보기
    	} else if(url_Command.equals("/selectBoard.free")) {
    		action = new SelectFreeBoardByNoAction();
    		forward = action.execute(request, response);
    		
    	//게시판 form으로 보내기
    	} else if(url_Command.equals("/insertForm.free")) {
    		forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/freeBoard/insertForm.jsp");
            
        //게시판 글쓰기 로직    
    	} else if(url_Command.equals("/insertBoard.free")) {
    		action = new InsertFreeBoardAction();
    		forward = action.execute(request, response);
    		
    	//게시판 삭제
    	} else if(url_Command.equals("/deleteBoard.free")) {
    		action = new DeleteFreeBoardAction();
    		forward = action.execute(request, response);

    	//게시글 수정	
    	} else if(url_Command.equals("/updateBoard.free")) {
    		action = new UpdateFreeBoardAction();
    		forward = action.execute(request, response);
    	
		//게시글 정보
    	} else if(url_Command.equals("/boardInfo.free")) {
    		action = new UpdadeInfoAction();
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
