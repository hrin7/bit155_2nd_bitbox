package kr.or.boram.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.MemberInfoDAO;
import kr.or.boram.dto.MemberInfoDTO;

public class MemberListGetService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		MemberInfoDAO info = new MemberInfoDAO();
		List<MemberInfoDTO> memberInfo = info.memberSelect();
		
		
		/*
		 * for(int i=0;i<memberInfo.size();i++) {
		 * System.out.println(i+"번쨰 객체 주소"+memberInfo.get(i)); }
		 */
		 
		
		request.setAttribute("MemberInfo",memberInfo);
		String viewpage = request.getContextPath();
		ActionForward forward = new ActionForward();
		
//		forward.setRedirect(true); //true 바꿔서 sendredirect 하게해줌 
//		System.out.println("현재 뷰페이지"+viewpage); //   /2nd_bitbox
//		forward.setPath(viewpage+"/WEB-INF/views/MemberInfoBoard/MemberList.jsp"); //-> 이것도 안됨 
//		forward.setPath("/WEB-INF/views/MemberInfoBoard/MemberList.jsp"); -> 이것도 안됨 
		// 뒤로가기 하면 당연히 그전에 했던 수행결과물이 보이는건데 이건 당연한 거임 
		
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/MemberInfoBoard/MemberList.jsp");
		
		return forward;
	}

}
