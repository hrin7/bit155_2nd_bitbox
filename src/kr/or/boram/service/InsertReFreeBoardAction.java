package kr.or.boram.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.boram.action.Action;
import kr.or.boram.action.ActionForward;
import kr.or.boram.dao.BoardDAO;
import kr.or.boram.dto.BoardAndFileAndReply;

public class InsertReFreeBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String no = "";
		String boardCode = "";
		String title = "";
		String content = "";
		String fileName = "";
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		System.out.println(uploadpath);
		int size = 1024 * 1024 * 10;
		
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(
					request,
					uploadpath,
					size,
					"UTF-8",
					new DefaultFileRenamePolicy()
			);
			Enumeration filenames = multi.getFileNames();
			
			String file = (String)filenames.nextElement();
			fileName = multi.getFilesystemName(file);
			
			no = multi.getParameter("no");
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			boardCode = multi.getParameter("searchCode");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		HttpSession session = request.getSession();
		
		BoardAndFileAndReply board = new BoardAndFileAndReply();
		System.out.println(session.getAttribute("userID"));
		board.setId((String)session.getAttribute("userID"));
		board.setTitle(title);
		board.setContent(content);
		board.setBoardCode(Integer.parseInt(boardCode));
		board.setNo(Integer.parseInt(no));
		board.setFileName(fileName);

		BoardDAO freeBoardDao = new BoardDAO();
		int result = freeBoardDao.insertReBoard(board);
		
		String msg = "";
		if(result > 0) {
			msg = "글이 등록되었습니다.";
		} else {
			msg = "글동록 실패";	
		}
		
		
		ActionForward forward = new ActionForward();
		request.setAttribute("msg", msg);
	    forward.setPath("selectBoardList.free");
	    
	    return forward;
	}

}
