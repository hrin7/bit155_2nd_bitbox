package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.MemberInfoDAO;
import kr.or.boram.dto.MemberInfoDTO;

public class MemberUpdateGetService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		//System.out.println("수정할 회원 ID " + id);
		
		MemberInfoDAO memberdao = new MemberInfoDAO();			
		MemberInfoDTO memberdto = memberdao.updateMember(id);
		
		// 여기 까진 넘오옴 성공 
		//System.out.println("회원ID"+memberdto.getId());
		//System.out.println("회원이름"+memberdto.getName());
		
		//이제 회원목록에 담긴dto를 request.setAttribute 해서 수정폼페이지로 보내기 
		request.setAttribute("memberUpdateInfo",memberdto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/MemberInfoBoard/MemberListUpdate.jsp");
		
		return forward;
	}

}
