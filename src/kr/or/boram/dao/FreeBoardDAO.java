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
import kr.or.boram.dto.BoardType;

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
		} catch (Exception e) {
			System.out.println("look up Fail : " + e.getMessage());
		}
	}
	
	//게시판 목록보기
	public List<Board> boardList(){
		List<Board> boardList = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select board_code, no, id, title, content, views, write_date, comment_count from board where board_code=2 order by no desc";
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
	
	//게시글 상세보기
	public List<Object> selectBoardByNo(int no, int board_code) {
		List<Object> boardAndBoardName = new ArrayList<>();
		Board board = null;
		String boardName = "";
		
		try {
			conn = ds.getConnection();
			String sql2 = "select board_name from board_type where board_code=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, board_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardName = rs.getString("board_name");
				boardAndBoardName.add(boardName);
			}
			
			String sql = "select board_code, no, id, title, content, write_date, views from board where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				board.setNo(rs.getInt("no"));
				board.setId(rs.getString("id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriteDate(rs.getString("write_date"));
				board.setViews(rs.getInt("views"));
				board.setBoardCode(rs.getInt("board_code"));
				boardAndBoardName.add(board);
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
		return boardAndBoardName;
	}
	
	//다중 select, 게시판코드로 검색
	public List<BoardType> SelectBoardType(){
		List<BoardType> list = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select board_code, board_name from board_type";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardType>();
			while(rs.next()) {
				BoardType boardType = new BoardType();
				boardType.setBoardCode(rs.getInt("board_code"));
				boardType.setBoardName(rs.getString("board_name"));
				list.add(boardType);
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
		System.out.println(list);
		return list;
		
	}
	
	//게시글 작성
	public int insertBoard(Board board) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String selectsql = "select no from board";
			pstmt = conn.prepareStatement(selectsql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(board.getNo() == rs.getInt("no")) {
					return -1;
				}
			}
			
			String sql = "insert into board(board_code ,no , id , title , content , write_date)"
					+ "values(?, no_seq.nextval ,'jinwon',?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoardCode());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("insert오류:" + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	//게시글 삭제
	public int deleteBoard(int no) {
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql_board_delete = "delete from board where no=?";
			pstmt = conn.prepareStatement(sql_board_delete);
			pstmt.setInt(1, no);
			row = pstmt.executeUpdate();
			
			if(row > 0) {
				conn.commit();
			}
		} catch (Exception e) {
			System.out.println("delte : " + e.getMessage());
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
	
	//게시글 수정하기
	public int updateBoard(Board board) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql_update = "update board set board_code=?, title=?, content=? where no=?";
			pstmt = conn.prepareStatement(sql_update);
			pstmt.setInt(1, board.getBoardCode());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getNo());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("수정 dao 에러 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println("수정 dao 에러 : " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	//조회수 증가 함수
	public int updateViews(int no) {
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "update board set views = views+1 where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
}
