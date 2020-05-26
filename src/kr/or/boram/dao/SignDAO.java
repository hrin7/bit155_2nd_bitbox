package kr.or.boram.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SignDAO {
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
	
	//ID 중복체크 클릭시 실행 함수 
	public int idCheck(String userid) {
		try {
			System.out.println("들어가짐1??"+userid);
			conn = ds.getConnection();
			String sql = "select id from member where id=?";
			pstmt = conn.prepareStatement(sql);
			System.out.println("들어가짐2??"+userid);
			pstmt.setString(1,userid);
			rs = pstmt.executeQuery(); // sql문을 실행한 결과를 rs에다 넣어줌
			System.out.println("들어가짐3??"+userid);
			
			while(rs.next()) {
				System.out.println("값이 있음"+rs.getString("id"));
				return 1;
			}
			
				return 0; //해당 ID가 DB에 존재하지 않을 경우 ID 사용가능  
			
//			if(rs.next()) { //ID 중복체크시 클라이언트가 입력한ID가 DB에 있다면 실행 
//				return 1; //ID 사용불가 
//			}else {
//				return 0; //해당 ID가 DB에 존재하지 않을 경우 ID 사용가능  
//			}
		
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("아이디 중복 체크 중 실패: " + e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		
		return -1; //데이터 베이스 오류
	}

	//회원가입시 DB에 있는 id,닉네임,이메일 중복검사 
	public int signUpCheck(String id,String nickname,String email) {
		try {
			conn = ds.getConnection();
			String sql_1 = "select id,nickname,email from member";
			pstmt = conn.prepareStatement(sql_1);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("id").equals(id)) { //DB에저장된 ID와 클라이언트가 입력한 ID가 똑같다면
					return 10; // 중복된 ID 입니다 
				}else if(rs.getString("nickname").equals(nickname)) {
					return 11; // 중복된 닉네임 입니다 
				}else if(rs.getString("email").equals(email)) {
					return 12; // 중복된 이메일 입니다 
				}
			}					
			return 1; //중복 검사에 성공했을시 1반환 
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("회원가입 정보 중복체크중 실패: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
		
		return -1; //데이터 베이스 오류시 -1을 넣음 
		
	}
	
	
	
	//회원가입시 DB에 회원정보 넣는 함수 
	public int signUpInsert(String id,String pwd,String name,String nickname,String email) {

		try {
			conn = ds.getConnection();
			
			//위에서 조건식에서 중복된ID,닉네임,이메일이 실행되지 않았다면 아래에서 회원DB테이블에 클라이언트가 입력한 회원정보를 넣음
			String sql_2 = "insert into member(id,password,name,nickname,email) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql_2);
			
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			pstmt.setString(3,name);
			pstmt.setString(4,nickname);
			pstmt.setString(5,email);

		
			return pstmt.executeUpdate(); //쿼리문이 정상적으로 insert 되었다면 1이 반환될것이다 ... 
			
		
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
