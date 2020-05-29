package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.MemberInfoDAO;

public class MemberDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		System.out.println("삭제할려는 ID"+ id);
		
		MemberInfoDAO memberinfo = new MemberInfoDAO();
		int result = memberinfo.deleteMember(id);
		
		ActionForward forward = new ActionForward();
		String msg ="";
		
		if(result == 1) {
			System.out.println("정상적으로 삭제 되었습니다!");
			msg = "Complete Delete!";
		}else if(result == 0) {
			System.out.println("삭제 된게 없음...");
			msg = "Not Delete!";
		}else {
			System.out.println("업데이트중 데이터베이스 오류발생");
			msg = "DATABASE ERROR!";
		}
		
		forward.setRedirect(false);
		forward.setPath("memberInfoRead.admin");
		request.setAttribute("msg", msg);
		return forward;
	}

}
