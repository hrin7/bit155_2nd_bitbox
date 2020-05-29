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
			 ds =(DataSource)envctx.lookup("/jdbc/oracle"); //context.xml 에서 name="jdbc/oracle"
		} catch (Exception e) {
			System.out.println("look up 에러 : " + e.getMessage());
		}
	}
	
	//글 목록 가져오기
	public List<MyBoard> selectMyBoardList(int cpage, int pageSize, String id) {
		List<MyBoard> list = null;
		
		try {
			conn = ds.getConnection(); //dbcp 연결 객체 얻기
			String sql = "select *" + 
						 "from (" + 
						 "	select rownum rn, id, diary_no, diary_title, diary_content, diary_date, diary_file_name, diary_refer, diary_depth, diary_step," + 
						 "					  (select count(*) from diary_comment where diary_no=b.diary_no) as cnt" + 
						 "	from (" + 
						 "		select *" + 
						 "		from diary" + 
						 "		where id = ?"+
						 "		order by diary_refer desc, diary_step asc" + 
						 "	) b" + 
						 "	where rownum <= ?" + 
						 ")" + 
						 "where rn >= ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pageSize - (pageSize-1); // 1*5-(5-1) >> 1
			int end = cpage * pageSize; // 1*5 >> 5
			
			pstmt.setString(1, id);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<MyBoard>();
			while(rs.next()) {
				MyBoard board = new MyBoard();
				board.setId(rs.getString("id"));
				board.setDiaryNo(rs.getInt("diary_no"));
				board.setDiaryTitle(rs.getString("diary_title"));
				board.setDiaryContent(rs.getString("diary_content"));
				board.setDiaryDate(rs.getString("diary_date"));
				board.setDiaryFileName(rs.getString("diary_file_name"));
				board.setDiaryRefer(rs.getInt("diary_refer"));
				board.setDiaryDepth(rs.getInt("diary_depth"));
				board.setDiaryStep(rs.getInt("diary_step"));
				board.setDiaryCommentCount(rs.getInt("cnt"));
				
				list.add(board);
			}
		} catch (Exception e) {
			System.out.println("에러1 : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); //connection pool에 반환하기
			} catch (Exception e2) {
				System.out.println("에러2 : " + e2.getMessage());
			}
		}
		System.out.println("리스트 : " + list); //list가 잘 넘어오는지 확인하기
		return list;
	}
	
	//게시물 총 건수
	public int totalCountMyBoard(String id) {
		int totalCount = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "select count(*) cnt from diary where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {			
			try {				
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return totalCount;
		
	}
	
	//원본글에 대한 refer값 구하기
	public int getRefer() {
		//select nvl(max(diary_refer), 0) from diary -> 처음 글  -> 0 -> refer+1 값
		int refer = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select nvl(max(diary_refer), 0) from diary";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				refer = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("에러 : " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return refer;
	}
	
	//글 작성하기
	public int insertMyBoard(MyBoard board) {
		int row = 0;
		int refer = getRefer();
		int maxRefer = refer + 1;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into diary(id, diary_no, diary_title, diary_content, diary_file_name, diary_date, diary_refer)" +
						 "values(?, diary_no_seq.nextval, ?, ?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getDiaryTitle());
			pstmt.setString(3, board.getDiaryContent());
			pstmt.setString(4, board.getDiaryFileName());			
			pstmt.setInt(5, maxRefer);
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("insert 에러 : " + e.getMessage()); //ORA-01400: cannot insert NULL into ("BITBOX"."DIARY"."ID")
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
			System.out.println("에러 : " + e.getMessage());
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
			String sql = "update diary set diary_title=?, diary_content=?, diary_file_name=? where diary_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getDiaryTitle());
			pstmt.setString(2, board.getDiaryContent());
			pstmt.setString(3, board.getDiaryFileName());
			pstmt.setInt(4, board.getDiaryNo());
			row = pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("에러 : " + e.getMessage());
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
			String sqlComment = "delete from diary_comment where diary_no=?";
			pstmt = conn.prepareStatement(sqlComment);
			pstmt.setInt(1, diaryNo);
			row = pstmt.executeUpdate();
			
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
	public int ReInsertMyBoard(MyBoard board) {
		//refer, depth, step 설정을 하려면 기존 정보 가져오기(현재 읽고 있는 글)
		int result = 0;
		try {
			conn = ds.getConnection();
			
			String id = board.getId();
			int diaryNo = board.getDiaryNo(); //현재 읽고 있는 글의 글번호			
			String title = board.getDiaryTitle();
			String content = board.getDiaryContent();
			String fileName = board.getDiaryFileName();
			
			//답글 
			//현재 내가 읽은 글의 refer, depth, step
			String refer_depth_step_sql = "select diary_refer, diary_depth, diary_step from diary where diary_no=?";
			
			//순서
			String step_sql = "select nvl(min(diary_step), 0) diary_step from diary where diary_refer=? and diary_depth<=? and diary_step>?";
			
			//insert
			String reply_sql= "insert into diary(id, diary_no, diary_title, diary_content, diary_date, diary_file_name, diary_refer, diary_depth, diary_step)" + 
							  "values(?, diary_no_seq.nextval, ?, ?, sysdate, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(refer_depth_step_sql);
			pstmt.setInt(1, diaryNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //데이터가 있다면, 원본글의 refer, depth, step가 존재한다면				
				int refer = rs.getInt("diary_refer");
				int depth = rs.getInt("diary_depth");
				int step = rs.getInt("diary_step");
				
				pstmt = conn.prepareStatement(step_sql); //컴파일
				pstmt.setInt(1, refer);
				pstmt.setInt(2, depth);
				pstmt.setInt(3, step);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					step = rs.getInt("diary_step");
					if(step == 0) {
						String maxStep = "select max(diary_step)+1 maxStep from diary where diary_refer=?";
						pstmt = conn.prepareStatement(maxStep);
						pstmt.setInt(1, refer); //원본글 ref
						rs = pstmt.executeQuery();
						if(rs.next()) {
							step = rs.getInt("maxStep");
						}
					} else {
						String updateStep = "update diary set diary_step=diary_step+1 where diary_refer=? and diary_step>=?";
						pstmt = conn.prepareStatement(updateStep);
						pstmt.setInt(1, refer); //원본글 ref
						pstmt.setInt(2, step);
						pstmt.executeUpdate();
					}
				}
				
				pstmt = conn.prepareStatement(reply_sql); //컴파일
				pstmt.setString(1, id);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				pstmt.setString(4, fileName);
				//답변
				pstmt.setInt(5, refer);
				pstmt.setInt(6, depth+1); //규칙:현재 읽은 글의 depth+1
				pstmt.setInt(7, step);				
				int row = pstmt.executeUpdate();				
				
				if(row > 0) {
					result = row;
				} else {
					result = -1;
				}
			}
	
		} catch (Exception e) {
			System.out.println("답글 에러 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
			
			}
		}
		return result;
	}
	
}
