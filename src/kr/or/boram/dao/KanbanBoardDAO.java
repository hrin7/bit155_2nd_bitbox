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
			 Context envctx = (Context)ctx.lookup("java:comp/env"); //기본설정
			 ds = (DataSource)envctx.lookup("/jdbc/oracle");//context.xml 에서 name="jdbc/oracle"
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
	public List<List<KanbanBoard>> selectList(String id) {
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
			
			String sql = "select kanban_code, kanban_no, id, kanban_title, kanban_content, kanban_comment_count, kanban_file_count "
					   + "from kanban where kanban_code=? and id=? order by kanban_no";
			pstmt = conn.prepareStatement(sql);
			
			allList = new ArrayList<>();
			for(KanbanGroup kanbanGroup : kanbanGroupList) {
				pstmt.setInt(1, kanbanGroup.getKanbanCode());
				pstmt.setString(2, kanbanGroup.get);
				rs = pstmt.executeQuery();
				
				groupList = new ArrayList<>();
				while(rs.next()) {
					KanbanBoard kanban = new KanbanBoard();
					kanban.setKanbanCode(rs.getInt("kanban_code"));
					kanban.setKanbanNo(rs.getInt("kanban_no"));
					kanban.setId(rs.getString("id"));
					kanban.setKanbanTitle(rs.getString("kanban_title"));
					kanban.setKanbanContent(rs.getString("kanban_content"));
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
	
	//단일 select 함수
	public KanbanBoard selectKanbanByNo(int kanbanNo) {
		KanbanBoard kanban = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select id, kanban_no, kanban_title, kanban_content, kanban_date, kanban_file_name, kanban_file_count, kanban_comment_count, kanban_code" + 
						 "  from kanban" + 
						 " where kanban_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, kanbanNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				kanban = new KanbanBoard();
				kanban.setId(rs.getString("id"));
				kanban.setKanbanNo(kanbanNo);
				kanban.setKanbanTitle(rs.getString("kanban_title"));
				kanban.setKanbanContent(rs.getString("kanban_content"));
				kanban.setKanbanDate(rs.getString("kanban_date"));
				kanban.setKanbanFileName(rs.getString("kanban_file_name"));
				kanban.setKanbanFileCount(rs.getInt("kanban_file_count"));
				kanban.setKanbanCommentCount(rs.getInt("kanban_comment_count"));
				kanban.setKanbanCode(rs.getInt("kanban_code"));
			}
		} catch (Exception e) {
			System.out.println("단일 select에서 에러남: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();//반환하기
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return kanban;
	}
	
	//칸반그룹(list) 만들기
	public int insertKanbanGroup(String listName) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into kanban_group(kanban_code, list_name)" +
						 "values(kanban_code_seq.nextval, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, listName);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("insertKanbanGroup 오류: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
			    conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		return row;
	}
	
	//칸반 card이름 insert하기위해 listName으로 kanbanCode 구하기
	public int insertKanbanCardName(String listName) {
		int kanbanCode = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "select kanban_code from kanban_group where list_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, listName);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				kanbanCode = rs.getInt("kanban_code");
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("insertKanbanCardName1 오류: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
			    conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		return kanbanCode;
	}
	
	//칸반 card이름 insert하고 번호 반환하기
	public int insertKanbanCardName(KanbanBoard kanbanBoard) {
		int row = 0;
		int kanbanNo = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into kanban(id, kanban_no, kanban_title, kanban_code, kanban_date)" +
						 "values(?, kanban_no_seq.nextval, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kanbanBoard.getId());
			pstmt.setString(2, kanbanBoard.getKanbanTitle());
			pstmt.setInt(3, kanbanBoard.getKanbanCode());
			
			row = pstmt.executeUpdate();
			
			if(row > 0) {
				String selectNoSeq = "select kanban_no_seq.currval kanban_no from dual";
				pstmt = conn.prepareStatement(selectNoSeq);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					kanbanNo = rs.getInt("kanban_no");
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("insertKanbanCardName2 오류: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
			    conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		return kanbanNo;
	}
	
	//칸반 list이름 update하기
	public int updateKanbanListName(String oriListName, String updateListName) {
		int row = 0;
		int kanbanCode = 0;
		String listName = null;
		
		try {
			conn = ds.getConnection();
			//파라미터로 넘어온 listName으로 칸반코드 찾기
			String selectSql = "select kanban_code from kanban_group where list_name=?";
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setString(1, oriListName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				kanbanCode = rs.getInt("kanban_code");
			}
			
			String sql = "update kanban_group set list_name=? where kanban_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateListName);
			pstmt.setInt(2, kanbanCode);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("updateKanbanListName 오류: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
			    conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		return row;
	}
	
	//칸반 card이름 update하기
	public int updateKanbanCardName(KanbanBoard kanbanBoard) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "update kanban set kanban_title=? where kanban_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kanbanBoard.getKanbanTitle());
			pstmt.setInt(2, kanbanBoard.getKanbanNo());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("updateKanbanCardName 오류: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
			    conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		return row;
	}

	//칸반 card 내용 update하기
	public int updateKanbanCardContent(KanbanBoard kanbanBoard) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "update kanban set kanban_content=? where kanban_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kanbanBoard.getKanbanContent());
			pstmt.setInt(2, kanbanBoard.getKanbanNo());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("updateKanbanCardContent 오류: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
			    conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		return row;
	}

	//칸반 card file update하기
	public int updateKanbanCardFile(KanbanBoard kanbanBoard) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "update kanban set kanban_file_name=? where kanban_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kanbanBoard.getKanbanContent());
			pstmt.setInt(2, kanbanBoard.getKanbanNo());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("updateKanbanCardContent 오류: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
			    conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		return row;
	}
}
