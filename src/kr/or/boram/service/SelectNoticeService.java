package kr.or.boram.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.NoticeBoardDAO;
import kr.or.boram.dto.Board;
import kr.or.boram.dto.NoticeBoard;

public class SelectNoticeService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		NoticeBoardDAO dao = new NoticeBoardDAO();
		
		List<NoticeBoard> noticeList = dao.boardList();
		
		for(int i =0; i < noticeList.size() ; i++) {
			System.out.println(i + "번째 내용" + noticeList.get(i).getContent());
		}
		
				//게시판리스트 가져오기
			
				request.setAttribute("noticeList", noticeList);
				
				ActionForward forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("WEB-INF/views/noticeboard/noticeboardList.jsp");
				return forward;
		
	
	}

}
