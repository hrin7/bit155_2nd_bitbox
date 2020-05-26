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
		} catch (Exception e) {
			System.out.println("look up Fail : " + e.getMessage());
		}
	}
	
	//글 목록 가져오기
	public List<MyBoard> selectMyBoardList() {
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
	
	//글 작성하기
	public int insertMyBoard(MyBoard board) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into diary(id, diary_no, diary_title, diary_content, diary_date)"
						+ "values('soyoung', diary_no_seq.nextval, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, board.getDiaryTitle());
			pstmt.setString(2, board.getDiaryContent());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
			//System.out.println("insert 에러 : " + e.getMessage()); //ORA-01400: cannot insert NULL into ("BITBOX"."DIARY"."ID")
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
	
	//작성된 글 보기(상세보기)
	public MyBoard selectMyBoardByNo(int diaryNo) {
		MyBoard board = null;
		
		try {
			conn = ds.getConnection();
			String sql="select id, diary_no, diary_title, diary_content, diary_file_name, diary_date from diary where diary_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, diaryNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new MyBoard();
				board.setId(rs.getString("id"));
				board.setDiaryNo(rs.getInt("diary_no"));
				board.setDiaryTitle(rs.getString("diary_title"));
				board.setDiaryContent(rs.getString("diary_content"));
				board.setDiaryFileName(rs.getString("diary_file_name"));					
				board.setDiaryDate(rs.getString("diary_date"));		
			}
			
		} catch (Exception e) {
			System.out.println("오류 : " + e.getMessage());
			e.getStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); //반환하기
			} catch (Exception e2) {
				
			}
		}
		return board;
	}
	
	//수정하기
	public int updateMyBoard(MyBoard board) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "update diary set diary_title=?, diary_content=? where diary_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getDiaryTitle());
			pstmt.setString(2, board.getDiaryContent());
			pstmt.setInt(3, board.getDiaryNo());
			row = pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("에러: " + e.getMessage());
			e.getStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close(); //반환
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	
		return row;
	}
	
	//삭제하기
	public int deleteMyBoard(int diaryNo) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "delete from diary where diary_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, diaryNo);
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
	
	//답글 작성하기
		
	//원본글에 대한 refer값 구하기
	
	//게시물 총 건수
	
	
}
