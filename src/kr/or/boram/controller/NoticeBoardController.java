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
import kr.or.boram.service.InsertNoticeAction;
import kr.or.boram.service.InsertReFreeBoardAction;
import kr.or.boram.service.SelectNoticeByNoService;
import kr.or.boram.service.SelectNoticeListAction;
import kr.or.boram.service.UpdateFreeBoardAction;
import kr.or.boram.service.UpdateFreeInfoAction;

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
    	if(url_Command.equals("/selectNoticeList.notice")) {
    		action = new SelectNoticeListAction();
    		forward = action.execute(request, response);
    		
    	//게시판 상세보기
    	} else if(url_Command.equals("/selectNotice.notice")) {
    		action = new SelectNoticeByNoService();
    		forward = action.execute(request, response);
    		
    	//글쓰기 form으로 보내기
    	} else if(url_Command.equals("/insertNoticeForm.notice")) {
    		forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/noticeBoard/insertForm.jsp");
            
        //게시판 글쓰기
    	} else if(url_Command.equals("/insertNotice.notice")) {
    		action = new InsertNoticeAction();
    		forward = action.execute(request, response);
    	
    	//게시판 삭제
    	} else if(url_Command.equals("/deleteNotice.notice")) {
    		action = new DeleteFreeBoardAction();
    		forward = action.execute(request, response);

    	//게시글 수정	
    	} else if(url_Command.equals("/updateNotice.notice")) {
    		action = new UpdateFreeBoardAction();
    		forward = action.execute(request, response);
    	
		//게시글 정보
    	} else if(url_Command.equals("/noticeInfo.notice")) {
    		action = new UpdateFreeInfoAction();
    		forward = action.execute(request, response);
    		
    	//답글쓰기 form으로 이동
    	} else if(url_Command.equals("/insertReNoticeForm.notice")) {
    		String no = request.getParameter("no");
    		request.setAttribute("no", no);
    		forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/noticeBoard/insertReNotice.jsp");
    	//답글쓰기
    	} else if(url_Command.equals("/insertReNotice.notice")) {
    		action = new InsertReFreeBoardAction();
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
