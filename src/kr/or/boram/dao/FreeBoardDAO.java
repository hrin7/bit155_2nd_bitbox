package kr.or.boram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.boram.dto.Board;

public class FreeBoardDAO {
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
	
	//게시판 목록보기
	public List<Board> boardList(){
		List<Board> boardList = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select board_code, no, id, title, content, views, write_date, comment_count from board order by no desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			boardList = new ArrayList<Board>();
			while(rs.next()) {
				Board board = new Board();
				board.setBoardCode(rs.getInt("board_code"));
				board.setNo(rs.getInt("no"));
				board.setId(rs.getString("id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setViews(rs.getInt("views"));
				board.setWriteDate(rs.getString("writeDate"));
				board.setCommentCount(rs.getInt("commentCount"));
				
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
}
