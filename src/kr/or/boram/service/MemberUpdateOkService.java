package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.MemberInfoDAO;

public class MemberUpdateOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		
		//DTO에 담아서 꺼내서 써도 되긴하는데 파라미터로 받아서 그냥처리하자 
		
		MemberInfoDAO memberinfo = new MemberInfoDAO();
		int result = memberinfo.updateMemberOk(id, pwd, name, nickname, email);
		
		ActionForward forward = new ActionForward();
		String msg ="";
		if(result > 0) {
			// 정상적으로 update 되었다면 MembList 페이지로 이동해서 update된 결과 보여주기 
			System.out.println("정상적으로 업데이트 되었습니다!");
			msg = "Complete Update!";
		}else if(result == 0) {
			System.out.println("업데이트 된게 없음...");
			msg = "NOT Update!";
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
