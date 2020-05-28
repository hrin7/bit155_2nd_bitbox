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
			System.out.println("look up Fail : " + e.getMessage());
		}
	}

	//글번호에 해당하는 Comment List가져오기
	public List<MyBoardComment> selectCommentList(int diaryNo) {
		List<MyBoardComment> list = null;

		try {
			conn = ds.getConnection();
			String sql = "select comment_num, comment_content, comment_name, comment_date from board_comment where board_num=? order by comment_num";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, diaryNo);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<MyBoardComment>();
			while(rs.next()) {
				MyBoardComment comment = new MyBoardComment();
				comment.setDiaryNo(rs.getInt("comment_num"));
				comment.setCommentName(rs.getString("comment_name"));
				comment.setCommentContent(rs.getString("comment_content"));
				comment.setCommentDate(rs.getString("comment_date"));
				
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
	public int insertComment(Comment comment) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into board_comment(board_num, comment_num, comment_content, comment_name, comment_date)" +
						 "values(?, comment_num_seq.nextval, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getBoardNum());
			pstmt.setString(2, comment.getCommentContent());
			pstmt.setString(3, comment.getCommentName());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("insert오류: " + e.getMessage());
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
	public int deleteComment(Comment comment) {
		int row = 0;

		try {
			conn = ds.getConnection();
			String sql = "delete from board_comment where board_num=? and comment_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getBoardNum());
			pstmt.setInt(2, comment.getCommentNum());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delete : " + e.getMessage());
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
	public int updateComment(Comment comment) {
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "update board_comment set comment_content=? where board_num=? and comment_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getBoardNum());
			pstmt.setInt(3, comment.getCommentNum());
			row = pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("수정dao 에러: " + e.getMessage());
			e.getStackTrace();
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
