package kr.or.boram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.boram.dto.KanbanBoard;

public class KanbanBoardDAO {
	static DataSource ds;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	static {
		InitialContext ctx;
		try {
			 ctx = new InitialContext();
			 Context envctx= (Context)ctx.lookup("java:comp/env"); //기본설정
			 ds =(DataSource)envctx.lookup("/jdbc/oracle");//context.xml 에서 name="jdbc/oracle"
		}catch (Exception e) {
			System.out.println("look up Fail : " + e.getMessage());
		}
	}
	
	//칸반보드 리스트가져오기
	public List<KanbanBoard> selectList() {
		List<KanbanBoard> list = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select kanban_code, kanban_no, id, kanban_title, kanban_comment_count, kanban_file_count from kanban";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<KanbanBoard>();
			while(rs.next()) {
				KanbanBoard kanban = new KanbanBoard();
				kanban.setKanbanCode(rs.getInt("kanban_code"));
				kanban.setKanbanNo(rs.getInt("kanban_no"));
				kanban.setId(rs.getString("id"));
				kanban.setKanbanTitle(rs.getString("kanban_title"));
				kanban.setKanbanCommentCount(rs.getInt("kanban_comment_count"));
				kanban.setKanbanFileCount(rs.getInt("kanban_file_count"));
				
				list.add(kanban);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); //반환
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return list;
	}

}
