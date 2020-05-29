package kr.or.boram.ajax;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UpdateKanbanCardFile.ajax")
public class UpdateKanbanFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateKanbanFile() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("왜안들어오쥬..");
        String fileName = "";
        
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
           
           System.out.println(fileName);
        } catch (IOException e) {
           e.printStackTrace();
        }
        
//        HttpSession session = request.getSession();
//        
//        KanbanBoard kanban = new KanbanBoard();
//        kanban.setKanbanFileName(fileName);
//        
//        KanbanBoardDAO dao = new KanbanBoardDAO();
//        int result = dao.insertBoard(board);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
