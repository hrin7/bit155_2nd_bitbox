package kr.or.boram.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.FreeBoardDAO;
import kr.or.boram.dao.NoticeBoardDAO;
import kr.or.boram.dto.Board;

public class InsertNoticeBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String boardCode = "";
		String id = "";
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
					request,
					uploadpath,
					size, //10mb
					"UTF-8",
					new DefaultFileRenamePolicy() //파일중복은 자동으로 이름 변경
			);
			Enumeration filenames = multi.getFileNames();
			
			String file = (String)filenames.nextElement();
			fileName = multi.getFilesystemName(file);
			oriFileName = multi.getOriginalFileName(file);
			
			id = multi.getParameter("id");
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			boardCode = multi.getParameter("searchCode");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		HttpSession session = request.getSession();
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setBoardCode(Integer.parseInt(boardCode));
		//board.setId((String)session.getAttribute("id"));
		
		NoticeBoardDAO noticeBoardDao = new NoticeBoardDAO();
		
		int result = noticeBoardDao(board);
		
		String msg = "";
		if(result > 0) {
			msg = "게시글이 등록되었습니다.";
		} else {
			msg = "게시글 등록실패";
		}
		
		ActionForward forward = new ActionForward();
		request.setAttribute("msg", msg);
		forward.setPath("selectBoardList.notice");
		
		return forward;
	}

	private int noticeBoardDao(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}
		
	

}
