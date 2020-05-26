package kr.or.boram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
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
	
	public int login(String userid,String userpwd) {
		
		try {
			conn = ds.getConnection();
			String sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,userid);
			rs = pstmt.executeQuery(); // sql문을 실행한 결과를 rs에다 넣어줌
			
			if(rs.next()) { //클라이언트가 입력한ID가 Member 테이블에 존재한다면 실행 
				if(rs.getString("password").equals(userpwd)) {
					//사용자가 입력한 pwd와 DB에 있는 pwd가 똑같다면 로그인 성공 
					return 1; 
				}
				return 2; //ID는 맞는데 비밀번호가 틀림 
			}else {
				return 0; //해당 ID가 존재하지 않을 경우 
			}
		
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("회원가입 정보 insert중 실패: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		
		return -1; //데이터 베이스 오류
	}

}
