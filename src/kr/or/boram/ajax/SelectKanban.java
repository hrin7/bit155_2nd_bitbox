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

import kr.or.boram.dao.KanbanBoardDAO;
import kr.or.boram.dto.KanbanBoard;
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
    	
    	Map<String, Object> map = new HashMap<>();
    	
    	//칸반 그룹 가져오기(code, listName)
		List<KanbanGroup> kanbanGroupList = dao.selectKanbanCode();
		
		//보드리스트 가져오기
    	List<List<KanbanBoard>> allKanbanList = dao.selectList();
    	
    	map.put("kanbanGroupList", kanbanGroupList);
    	map.put("allKanbanList", allKanbanList);
		
    	JSONArray obj = JSONArray.fromObject(map);
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
