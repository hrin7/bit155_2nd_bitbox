<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>    
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%
 
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    String title = request.getParameter("title");
    String text = request.getParameter("text");
    String time = null;
    
    if(name==null || title==null || text==null){
        throw new Exception("데이터를 입력하세요.");
    }
    
    Connection conn = null;
    Statement stmt = null;
    
    try{
    	Class.forname( com.mysql.jdbc.driver );
        conn = DriverManager.getConnection("com.oracle.jdbc.driver
                                    useUnicode=true&characterEncoding=utf-8","ID","PW");
        //db insert시 한글 깨져서 db명 뒤에 ?useUnicode=true&characterEncoding=utf-8 붙여주니 안깨지고 들어간다.
        if(conn == null)
            throw new Exception("데이터베이스에 연결할 수 없습니다.");
        stmt = conn.createStatement();
        String command = String.format("insert into board" + "(name, title, text, time) values ('%s','%s','%s',now());"
                                        , name, title, text, time );
        
        int rowNum = stmt.executeUpdate(command);
        
        if(rowNum<1)
            throw new Exception("데이터를 DB에 입력할 수 없습니다.");
    }
    finally{
        try{
            stmt.close();
        }
        catch(Exception ignored){
        }
        try{
            conn.close();
        }
        catch(Exception ignored){
        }
    }
    
    response.sendRedirect("index.jsp");
%>
