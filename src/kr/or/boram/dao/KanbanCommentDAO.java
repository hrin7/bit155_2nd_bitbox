package kr.or.boram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.boram.dto.KanbanComment;

public class KanbanCommentDAO {
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

	//글번호에 해당하는 Comment List가져오기
	public List<KanbanComment> selectKanbanCommentList(int kanbanNo) {
		List<KanbanComment> list = null;

		try {
			conn = ds.getConnection();
			String sql = "select kanban_comment_no, kanban_no, kanban_comment_content, kanban_comment_date from kanban_comment where kanban_no=? order by kanban_comment_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, kanbanNo);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()) {
				KanbanComment comment = new KanbanComment();
				comment.setKanbanCommentNo(rs.getInt("kanban_comment_no"));
				comment.setKanbanNo(rs.getInt("kanban_no"));
				comment.setKanbanCommentContent(rs.getString("kanban_comment_content"));
				comment.setKanbanCommentDate(rs.getString("kanban_comment_date"));
				
				list.add(comment);
			}
		} catch (Exception e) {
			System.out.println("코멘트 리스트 에러 : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return list;
	}
	
	//글쓰기 함수
	public int insertKanbanComment(KanbanComment comment) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into kanban_comment(kanban_comment_no, kanban_no, kanban_comment_content, kanban_comment_date)" + 
						 "values(kanban_comment_no_seq.nextval, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getKanbanNo());
			pstmt.setString(2, comment.getKanbanCommentContent());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("insertKanbanComment 오류: " + e.getMessage());
		} finally {
			try {
				if (pstmt != null) try {pstmt.close();} catch(Exception e) {}
			    if (conn != null) try {conn.close();} catch(Exception e) {}
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		return row;
	}
	
	//댓글 삭제하기
	public int deleteKanbanComment(KanbanComment comment) {
		int row = 0;

		try {
			conn = ds.getConnection();
			String sql = "delete from kanban_comment where kanban_no=? and kanban_comment_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getKanbanNo());
			pstmt.setInt(2, comment.getKanbanCommentNo());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteKanbanComment에러 : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println("delete : " + e2.getMessage());
			}
		}
		return row;
	}
	
	//댓글 수정하기
	public int updateKanbanComment(KanbanComment comment) {
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "update kanban_comment set kanban_comment_content=? where kanban_no=? and kanban_comment_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getKanbanCommentContent());
			pstmt.setInt(2, comment.getKanbanNo());
			pstmt.setInt(3, comment.getKanbanCommentNo());
			row = pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("updateKanbanComment 에러: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return row;
	}
}
