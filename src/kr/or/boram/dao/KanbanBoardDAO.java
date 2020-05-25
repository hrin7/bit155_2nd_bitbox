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
import kr.or.boram.dto.KanbanGroup;

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
	
	//KanbanCode, 리스트이름 구하기
	public List<KanbanGroup> selectKanbanCode() {
		List<KanbanGroup> kanbanGroupList = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select kanban_code, list_name from kanban_group";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			kanbanGroupList = new ArrayList<KanbanGroup>();
			while(rs.next()) {
				KanbanGroup kanbanGroup = new KanbanGroup();
				kanbanGroup.setKanbanCode(rs.getInt("kanban_code"));
				kanbanGroup.setListName(rs.getString("list_name"));
				kanbanGroupList.add(kanbanGroup);
			}
		} catch (Exception e) {
			System.out.println("칸반그룹 가져오기 에러 : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); //반환
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return kanbanGroupList;
	}
	
	//칸반보드 리스트가져오기
	public List<List<KanbanBoard>> selectList() {
		List<KanbanGroup> kanbanGroupList = null;
		List<KanbanBoard> groupList = null;
		List<List<KanbanBoard>> allList = null;
		
		try {
			conn = ds.getConnection();
			//그룹코드 가져오기
			String codeSql = "select kanban_code, list_name from kanban_group";
			pstmt = conn.prepareStatement(codeSql);
			rs = pstmt.executeQuery();
			kanbanGroupList = new ArrayList<>();
			while(rs.next()) {
				KanbanGroup kanbanGroup = new KanbanGroup();
				kanbanGroup.setKanbanCode(rs.getInt("kanban_code"));
				kanbanGroup.setListName(rs.getString("list_name"));
				kanbanGroupList.add(kanbanGroup);
			}
			
			String sql = "select kanban_code, kanban_no, id, kanban_title, kanban_comment_count, kanban_file_count from kanban where kanban_code=?";
			pstmt = conn.prepareStatement(sql);
			
			allList = new ArrayList<>();
			for(KanbanGroup kanbanGroup : kanbanGroupList) {
				pstmt.setInt(1, kanbanGroup.getKanbanCode());
				rs = pstmt.executeQuery();
				
				groupList = new ArrayList<>();
				while(rs.next()) {
					KanbanBoard kanban = new KanbanBoard();
					kanban.setKanbanCode(rs.getInt("kanban_code"));
					kanban.setKanbanNo(rs.getInt("kanban_no"));
					kanban.setId(rs.getString("id"));
					kanban.setKanbanTitle(rs.getString("kanban_title"));
					kanban.setKanbanCommentCount(rs.getInt("kanban_comment_count"));
					kanban.setKanbanFileCount(rs.getInt("kanban_file_count"));
					groupList.add(kanban);
				}
				allList.add(groupList);
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
		return allList;
	}

}
