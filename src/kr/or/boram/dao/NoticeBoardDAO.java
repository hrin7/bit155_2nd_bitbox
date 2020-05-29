package kr.or.boram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.boram.dto.Board;
import kr.or.boram.dto.NoticeBoard;

public class NoticeBoardDAO {
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
	
	public NoticeBoard boardSelect(int no) {
		
		String sql = "select * from board where no=?";
		
	      NoticeBoard notice  = null;
	      
	      try {
	         conn = ds.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1,no);
	         
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {         
	        	 notice = new NoticeBoard();
	        	 notice.setNo(rs.getInt("no"));
	        	notice.setId(rs.getString("id"));
	        	notice.setTitle(rs.getString("title"));
	        	notice.setContent(rs.getString("content"));

	         }
	         
	       
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            rs.close();
	            pstmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }   
	      }
	      
	         return notice;
	}
	
	//게시판 목록보기
	   public List<NoticeBoard> boardList(){
	      List<NoticeBoard> boardList = null;
	      
	      try {
	         conn = ds.getConnection();
	         String sql = "select board_code, no, id, title, content, views, write_date, comment_count from board where board_code=1 order by no desc";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         boardList = new ArrayList<NoticeBoard>();
	         
	         while(rs.next()) {
	        	NoticeBoard board = new NoticeBoard();
	            board.setBoardCode(rs.getInt("board_code"));
	            board.setNo(rs.getInt("no"));
	            board.setId(rs.getString("id"));
	            board.setTitle(rs.getString("title"));
	            board.setContent(rs.getString("content"));
	            board.setViews(rs.getInt("views"));
	            board.setWriteDate(rs.getString("write_date"));
	            board.setCommentCount(rs.getInt("comment_Count"));
	            
	            boardList.add(board);
	            
	         }
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      } finally {
	         try {
	            pstmt.close();
	            rs.close();
	            conn.close();
	         } catch (Exception e2) {
	            System.out.println(e2.getMessage());
	         }
	      }
	      return boardList;
	   }

	public int deleteBoard(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
