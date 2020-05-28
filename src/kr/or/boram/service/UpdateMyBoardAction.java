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

public class UpdateMyBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String diaryNo = "";
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
	               size, //10mb
	               "UTF-8",
	               new DefaultFileRenamePolicy() //파일중복되면 자동으로 이름변경
	         );
	         Enumeration filenames = multi.getFileNames();
	         
	         String file = (String)filenames.nextElement();
	         fileName = multi.getFilesystemName(file);
	         
	         //수정 시, 파일선택을 하지 않았으면 기존의 파일명으로 설정하기
	         if(fileName == null) {
	        	 fileName = multi.getParameter("image");
	         }
	         oriFileName = multi.getOriginalFileName(file);
	         
	         title = multi.getParameter("title");
	         content = multi.getParameter("message");
	         diaryNo = multi.getParameter("diaryNo");
	        
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	    
		HttpSession session = request.getSession();
		  
		MyBoard board = new MyBoard();
		board.setDiaryNo(Integer.parseInt(diaryNo));
		board.setDiaryTitle(title);
		board.setDiaryContent(content);
		board.setDiaryFileName(fileName);

		MyBoardDAO dao = new MyBoardDAO();
	    int result = dao.updateMyBoard(board);
	    //System.out.println("보드 : " + board);
	    System.out.println("result : " + result);
	    
		String msg = "";
		if(result > 0) {
			msg = "수정되었습니다.";
		} else {
			msg = "수정 실패";
		}
		
		request.setAttribute("msg", msg);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("myBoardSelect.my?diaryNo=" + diaryNo);
		
		return forward;
	}

}
