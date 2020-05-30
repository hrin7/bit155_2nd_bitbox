package kr.or.boram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.boram.dto.BoardComment;

public class BoardCommentDAO {
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
	
	//글번호에 해당하는 Comment List 가져오기
	public List<BoardComment> selectCommentList(int no){
		List<BoardComment> list = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select comment_no, comment_content, id, comment_date from board_comment where no=? order by comment_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<BoardComment>();
			while(rs.next()) {
				BoardComment boardComment = new BoardComment();
				boardComment.setCommentNo(rs.getInt("comment_no"));
				boardComment.setId(rs.getString("id"));
				boardComment.setCommentContent(rs.getString("comment_content"));
				boardComment.setCommentDate(rs.getString("comment_date"));
				
				list.add(boardComment);
			}
		} catch (Exception e) {
			System.out.println("댓글목록에러 : " + e.getMessage());
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
	
	//댓글 등록하기
	public int insertComment(BoardComment boardComment) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into board_comment(no, comment_no, comment_content, id, comment_date)" + 
						 "values(?, comment_no_seq.nextval, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardComment.getNo());
			pstmt.setString(2, boardComment.getCommentContent());
			pstmt.setString(3, boardComment.getId());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert 오류 : " + e.getMessage());
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
	public int deleteComment(BoardComment boardComment) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "delete from board_comment where no=? and comment_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardComment.getNo());
			pstmt.setInt(2, boardComment.getCommentNo());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delete : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return row;
	}
	
	//댓글 수정하기
	public int updateComment(BoardComment boardComment) {
		int row = 0;
		try {
			conn =ds.getConnection();
			String sql = "update board_comment set comment_content=? where no=? and comment_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardComment.getCommentContent());
			pstmt.setInt(2, boardComment.getNo());
			pstmt.setInt(3, boardComment.getCommentNo());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("수정dao 에러: " + e.getMessage());
			e.getStackTrace();
		} finally {
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
