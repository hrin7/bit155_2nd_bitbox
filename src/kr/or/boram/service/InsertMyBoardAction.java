package kr.or.boram.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.MyBoardDAO;
import kr.or.boram.dto.MyBoard;

public class InsertMyBoardAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String title = "";
		String content = "";
		String fileName = "";
		String oriFileName = "";
		  
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		System.out.println(uploadpath);
		int size = 1024 * 1024 * 10; //업로드 파일에 대한 기본 정보(10mb)
  
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(
					request, //기존에 있는 request 객체의 주소값
					uploadpath,
					size, //10mb 이내
					"UTF-8",
					new DefaultFileRenamePolicy() //파일이 중복되면 자동으로 이름 변경
			);
			
			Enumeration filenames = multi.getFileNames();
			String file = (String)filenames.nextElement();
			fileName = multi.getFilesystemName(file);
			oriFileName = multi.getOriginalFileName(file);
			
			title = multi.getParameter("title");
			content = multi.getParameter("message");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		
		MyBoard board = new MyBoard();
		board.setDiaryTitle(title);
		board.setDiaryContent(content);
		board.setDiaryFileName(fileName);
      
		//파일 처리하는 부분
//		if(filename == null) {
//		emp.setImage("user.png");
//		} else {
//		emp.setImage(filename);
//		}
		
		//System.out.println("보드 : " + board); //board 찍어보기
		MyBoardDAO dao = new MyBoardDAO();
		int result = dao.insertMyBoard(board);
		//System.out.println("result : " + result); //result 찍어보기
		
		String msg = "";
		
		if(result > 0) {
			msg = "글이 등록되었습니다.";
		} else {
			msg = "글쓰기 실패";
		}
		
		request.setAttribute("msg", msg);
		ActionForward forward = new ActionForward();      
		forward.setPath("myBoardList.my");
		
		return forward;
	}
	
}