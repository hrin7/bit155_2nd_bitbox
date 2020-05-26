package kr.or.boram.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.SignDAO;

public class SignUpService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");

		SignDAO dao = new SignDAO();
		ActionForward forward = new ActionForward();
		String msg = "";
	
		int result = dao.signUpInsert(id, pwd, name, nickname, email);

		if(result == 1) {
			System.out.println("member 테이블에 insert 성공 메인화면으로 이동합니다");
			msg = "정상적으로 회원가입이 완료되었습니다";
			try {
				response.sendRedirect("/index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//forward.setPath("/index.jsp"); //정상적으로 회원가입 완료시 index페이지로 이동하는 거기서 새로고침을 누르면 주소가 join.do로 되있기때문에 에러가 뜸
		}else{
			System.out.println("insert 실패 ... ");
			msg = "회원가입중 에러 발생...다시 회원가입 바람";
			forward.setPath("/index.jsp");
		}
      
	    request.setAttribute("msg", msg);
		
		return forward;
	}

}
