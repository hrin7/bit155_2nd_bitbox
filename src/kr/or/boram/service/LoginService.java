package kr.or.boram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.UserDAO;
import kr.or.boram.dto.UserDTO;

public class LoginService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		
		UserDTO userdto = new UserDTO();
		userdto.setUserid(userid);
		userdto.setUserpwd(userpwd);
		
		UserDAO userdao = new UserDAO();
		int result = userdao.login(userid,userpwd);
		ActionForward forward = new ActionForward();
		
		String msg = "";
		
		if(result == 1) {
			System.out.println("로그인 성공");
			// 로그인 성공시 세션처리후 메인화면으로 이동(서블릿에선 HttpSession 객체를 생성해서 세션처리를 해야됨) 
			HttpSession session = request.getSession();
			if(userdto.getUserid().equals("admin")) {//admin 계정일경우
				session.setAttribute("userID","admin");
			}else {
				//admin 계정으로 로그인한 경우가 아니라면 
				session.setAttribute("userID",userid);
			}
			msg = "로그인 성공 메인페이지로 이동";
			forward.setRedirect(false);
			forward.setPath("/index.jsp");
		}else {
			System.out.println("데이터 베이스 오류!");
		}
		
		request.setAttribute("msg", msg);
		
//		else if(result == 2) {
//			System.out.println("패스워드가 틀립니다");
//			//패스워드가 틀렸다는 알람참 띄운후 (ajax 써야하나 ??)
//			//다시 로그인 화면으로 이동 (필요에 따라서 패스워드를 5번정도 틀릴경우 계정을 잠궈버림) 
//			forward.setRedirect(false);
//			forward.setPath("/index.jsp");
//
//		}else if(result == 0) {
//			System.out.println("존재하지 않는 ID 입니다");
//			// 로그인 화면에서 메세지만 띄우고 싶음 (ajax 써야하나 ??? )
//			forward.setRedirect(false);
//			forward.setPath("/common/signModal.jsp");
//
//		}else {
//			System.out.println("데이터 베이스 오류!");
//		}
		
		
		return forward;
	}

}
