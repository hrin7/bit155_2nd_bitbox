package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.BoardDAO;

public class UpdateNoticeService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("해당 글 목록 ID " + no);
		
		BoardDAO dao = new BoardDAO();
		
		//Board dto = dao.boardSelect(no);
		
		//System.out.println(dto.getId());
		//System.out.println(dto.getContent());
		
		//request.setAttribute("noticeList", dto);
			ActionForward forward = new ActionForward();
	      forward.setRedirect(false);
	      forward.setPath("/WEB-INF/views/noticeboard/noticeupdateForm.jsp");
	      
	      
		
		return forward;
	}

}
