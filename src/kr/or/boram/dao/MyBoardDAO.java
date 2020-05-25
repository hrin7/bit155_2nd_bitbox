package kr.or.boram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.boram.dto.MyBoard;

public class MyBoardDAO {
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
	
	//글 목록 가져오기
	public List<MyBoard> selectBoardList() {
		List<MyBoard> list = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select id, diary_no, diary_title, diary_content, diary_date, diary_refer, diary_depth, diary_step, diary_file_name, diary_comment_count from diary order by diary_no desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<MyBoard>();
			while(rs.next()) {
				MyBoard board = new MyBoard();
				board.setId(rs.getString("id"));
				board.setDiaryNo(rs.getInt("diary_no"));
				board.setDiaryTitle(rs.getString("diary_title"));
				board.setDiaryContent(rs.getString("diary_content"));
				board.setDiaryDate(rs.getString("diary_date"));
				board.setDiaryRefer(rs.getInt("diary_refer"));
				board.setDiaryDepth(rs.getInt("diary_depth"));
				board.setDiaryStep(rs.getInt("diary_step"));								
				board.setDiaryFileName(rs.getString("diary_file_name"));
				board.setDiaryCommentCount(rs.getInt("diary_comment_count"));
				
				list.add(board);
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
		System.out.println(list); //list가 잘 넘어오는지 확인하기
		return list;
	}
	
	

}
