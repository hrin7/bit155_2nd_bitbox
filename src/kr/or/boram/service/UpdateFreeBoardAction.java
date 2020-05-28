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
import kr.or.boram.dto.Board;

public class UpdateFreeBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String boardCode = "";
		String no = "";
		String title = "";
		String content = "";
		String fileName = "";
		String oriFileName = "";
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
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
			oriFileName = multi.getOriginalFileName(file);
			
			boardCode = multi.getParameter("searchCode");
			no = multi.getParameter("no");
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		HttpSession session = request.getSession();
		
		Board board = new Board();
		board.setNo(Integer.parseInt(no));
		board.setTitle(title);
		board.setContent(content);
		board.setBoardCode(Integer.parseInt(boardCode));
		
		FreeBoardDAO freeBoardDao = new FreeBoardDAO();
		int result = freeBoardDao.updateBoard(board);
		String msg = "";
		if(result > 0) {
			msg = "수정되었습니다.";
		} else {
			msg = "수정실패";
		}
		
		request.setAttribute("msg", msg);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("selectBoard.free?no=" + no + "&boardCode=" + boardCode);
		
		return forward;
	}

}
