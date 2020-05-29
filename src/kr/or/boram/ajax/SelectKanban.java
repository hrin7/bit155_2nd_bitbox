package kr.or.boram.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.boram.dao.KanbanBoardDAO;
import kr.or.boram.dto.KanbanBoardAndGroup;
import kr.or.boram.dto.KanbanGroup;
import net.sf.json.JSONArray;

@WebServlet("/SelectKanban.ajax")
public class SelectKanban extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectKanban() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("utf-8");
    	KanbanBoardDAO dao = new KanbanBoardDAO();
    	
    	HttpSession session = request.getSession();
    	String id = (String)session.getAttribute("userID");
    	
    	Map<String, Object> allList = new HashMap<>();
    	
    	//칸반그룹 가져오기
		List<KanbanGroup> kanbanGroupList = dao.selectKanbanGroupList(id);
		allList.put("kanbanGroupList", kanbanGroupList);
		
		//보드리스트 가져오기
		List<KanbanBoardAndGroup> kanbanList = dao.selectList(id);
		allList.put("kanbanList", kanbanList);
    	
    	JSONArray obj = JSONArray.fromObject(allList);
		PrintWriter out = response.getWriter();
		out.print(obj);
		out.close();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
