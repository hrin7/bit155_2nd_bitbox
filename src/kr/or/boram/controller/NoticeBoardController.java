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
import kr.or.boram.service.DeleteNoticeBoardAction;
import kr.or.boram.service.InsertFreeBoardAction;
import kr.or.boram.service.InsertNoticeBoardAction;
import kr.or.boram.service.InsertReFreeBoardAction;
import kr.or.boram.service.SelectFreeBoardByNoAction;
import kr.or.boram.service.SelectFreeBoardListAction;
import kr.or.boram.service.SelectNoticeBoardByNoAction;
import kr.or.boram.service.SelectNoticeBoardListAction;
import kr.or.boram.service.UpdateFreeBoardAction;
import kr.or.boram.service.UpdateFreeInfoAction;
import kr.or.boram.service.UpdateNoticeBoardAction;
import kr.or.boram.service.UpdateNoticeInfoAction;
import kr.or.boram.service.UpdateNoticeOkService;


@WebServlet("*.notice")
public class NoticeBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeBoardController() {
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
    	if(url_Command.equals("/selectBoardList.notice")) {
    		action = new SelectNoticeBoardListAction();
    		forward = action.execute(request, response);
    		
    	//게시판 상세보기
    	} else if(url_Command.equals("/selectBoard.notice")) {
    		action = new SelectNoticeBoardByNoAction();
    		forward = action.execute(request, response);
    		
    	//글쓰기 form으로 보내기
    	} else if(url_Command.equals("/insertForm.notice")) {
    		forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/noticeBoard/insertForm.jsp");
            
        //게시판 글쓰기
    	} else if(url_Command.equals("/insertBoard.notice")) {
    		action = new InsertNoticeBoardAction();
    		forward = action.execute(request, response);
    	
    	//게시판 삭제
    	} else if(url_Command.equals("/deleteBoard.notice")) {
    		System.out.println("글목록 삭제중 ...");
    		action = new DeleteNoticeBoardAction();
    		forward = action.execute(request, response);

    	//게시글 수정	
    	} else if(url_Command.equals("/updateBoard.notice")) {
    		action = new UpdateNoticeBoardAction();
    		forward = action.execute(request, response);
    	
		//게시글 정보
    	} else if(url_Command.equals("/boardInfo.notice")) {
    		action = new UpdateNoticeInfoAction();
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
