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

import kr.or.boram.dto.MemberInfoDTO;

public class MemberInfoDAO {

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
	
	//해당 멤버의 정보를 삭제하는 
	public int deleteMember(String id) {
		try {
			conn = ds.getConnection();
			String sql = "delete from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate(); //해당ID의 것만삭제되니까 1건이 뜨거나 업으면 0건이 뜰꺼임

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
		return -1; //삭제과정에서 데이터 베이스 오류
	}
	
	//회원정보 수정하고 수정버튼 눌를시 DB에 확정적으로 업데이트가 되도록 하는 함수 
	public int updateMemberOk(String id,String pwd,String name,String nickname,String email) {
		//해당 ID의 회원정보를 수정한다 
		try {
			conn = ds.getConnection();
			String sql_update = "update member set password=?, name=?, nickname=? , email=? where id=?";
			pstmt = conn.prepareStatement(sql_update);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, nickname);
			pstmt.setString(4, email);
			pstmt.setString(5, id);
			
			return pstmt.executeUpdate(); //업데이트 된게 있으면 1이상 없으면 0반환 
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
		return -1; //데이터 베이스 오류시 
	}
	
	
	//해당 ID의 회원목록을 얻어오는 함수 
	public MemberInfoDTO updateMember(String id) {
		String sql = "select * from member where id=?";
		MemberInfoDTO memberinfo = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {			
				memberinfo = new MemberInfoDTO();
				
				memberinfo.setId(rs.getString("id"));
				memberinfo.setPassword(rs.getString("password"));
				memberinfo.setName(rs.getString("name"));
				memberinfo.setNickname(rs.getString("nickname"));
				memberinfo.setEmail(rs.getString("email"));
				memberinfo.setFile_name(rs.getString("file_name"));				
				
			}
			
			return memberinfo; 
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
		
			return null;
	}
	
	
	
	
	
	//모든 회원 정보 리스트 출력하는 함수 
	public List<MemberInfoDTO> memberSelect() {
	
		List<MemberInfoDTO> board_list = null;
		String sql = "select * from member";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			board_list = new ArrayList<MemberInfoDTO>();
			while(rs.next()) {
				
				MemberInfoDTO memberinfo = new MemberInfoDTO();
				
				memberinfo.setId(rs.getString("id"));
				memberinfo.setPassword(rs.getString("password"));
				memberinfo.setName(rs.getString("name"));
				memberinfo.setNickname(rs.getString("nickname"));
				memberinfo.setEmail(rs.getString("email"));
				memberinfo.setFile_name(rs.getString("file_name"));
				
				board_list.add(memberinfo);
				
			}
			
			return board_list; 
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
		
			return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
