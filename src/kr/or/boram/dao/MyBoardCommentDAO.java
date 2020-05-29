package kr.or.boram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.boram.dto.MyBoardComment;

public class MyBoardCommentDAO {
	static DataSource ds;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	static {
		InitialContext ctx;
		try {
			 ctx = new InitialContext();
			 Context envctx= (Context)ctx.lookup("java:comp/env"); //기본설정
			 ds =(DataSource)envctx.lookup("/jdbc/oracle"); //context.xml 에서 name="jdbc/oracle"
		}catch (Exception e) {
			System.out.println("look up 에러 : " + e.getMessage());
		}
	}

	//해당 글번호에 해당하는 댓글리스트 가져오기
	public List<MyBoardComment> selectMyBoardCommentList(int diaryNo) {
		List<MyBoardComment> list = null;

		try {
			conn = ds.getConnection();
			String sql = "select diary_comment_no, diary_comment_content, diary_comment_date from diary_comment where diary_no=? order by diary_comment_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, diaryNo);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<MyBoardComment>();
			while(rs.next()) {
				MyBoardComment comment = new MyBoardComment();
				comment.setDiaryCommentNo(rs.getInt("diary_comment_no"));
				comment.setDiaryCommentContent(rs.getString("diary_comment_content"));
				comment.setDiaryCommentDate(rs.getString("diary_comment_date"));
				
				list.add(comment);
				System.out.println("댓글 리스트 : " + list);
			}
			
		} catch (Exception e) {
			System.out.println("댓글 리스트 에러 : " + e.getMessage());
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
	
	//댓글 작성하기
	public int insertMyBoardComment(MyBoardComment comment) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into diary_comment(id, diary_no, diary_comment_no, diary_comment_content, diary_comment_date)" +
						 "values(?, ?, diary_comment_no_seq.nextval, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comment.getId());			
			pstmt.setInt(2, comment.getDiaryNo());
			pstmt.setString(3, comment.getDiaryCommentContent());			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("댓글 insert 에러 : " + e.getMessage());
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
	
	//댓글 수정하기
	public int updateMyBoardComment(MyBoardComment comment) {
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "update diary_comment set diary_comment_content=? where diary_no=? and diary_comment_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getDiaryCommentContent());
			pstmt.setInt(2, comment.getDiaryNo());
			pstmt.setInt(3, comment.getDiaryCommentNo());
			row = pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("댓글 수정 dao 에러 : " + e.getMessage());
			e.getStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close(); //반환
			} catch (Exception e2) {
				System.out.println("댓글 수정 dao 에러2 : " + e2.getMessage());
			}
		}
		return row;
	}
	
	//댓글 삭제하기
	public int deleteMyBoardComment(MyBoardComment comment) {
		int row = 0;

		try {
			conn = ds.getConnection();
			String sql = "delete from diary_comment where diary_no=? and diary_comment_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getDiaryNo());
			pstmt.setInt(2, comment.getDiaryCommentNo());			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("댓글 삭제 에러 : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println("댓글 삭제 에러 : " + e2.getMessage());
			}
		}
		return row;
	}

}
