<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	/* 
	회원정보 수정하기
	1.DB 쿼리 : 2개 (수정정보 : select , 수정정보반영 : update)
	 1.1 : select * from koreamember where id=?
	 1.2 : update koreamember set ename=? where id=?		 
	2.화면 1개(기존에 입력내용 보여주는 것)-> 처리 1개 (수정처리)
	 2.1  DB select 한 결과 화면 출력 
	      <input type="text" value="rs.getString(id)">
	      수정안하고 .. 화면 .. 전송(x) : <td>rs.getString("id")</td>
	      수정안하고 .. 화면 .. 전송   : <input type="text" value="rs.getString(id)" readonly>
	      수정하고 ..화면  ..전송   :  <input type="text" value="rs.getString(id)">
	
	*/
    request.setCharacterEncoding("UTF-8");
	
	if(session.getAttribute("userID") == null || !session.getAttribute("userID").equals("admin") ){
		//강제로 페이지 이동
		out.print("<script>location.href='index.jsp'</script>");
	}

%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>BITBOX 관리자 - 회원정보목록 페이지 </title>
			<meta charset="utf-8" />
			<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
			<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
			<link href="https://cdn.jsdelivr.net/npm/remixicon@2.4.0/fonts/remixicon.css" rel="stylesheet"> <!-- 아이콘 -->
			<noscript><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/noscript.css" /></noscript>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	</head>
	<script type="text/javascript">
		$(function() {
			if("${requestScope.msg}" != "") {
				alert("${requestScope.msg}");
            }
        });
	</script>
<body class="is-preload">

	<!-- Page Wrapper -->
		<div id="page-wrapper">
		
		
					<h2>Member Management</h2>
					<p>회원정보 관리 페이지 입니다</p>
			
			<!-- Header -->
			<jsp:include page="/common/header.jsp"></jsp:include>
			
						<table>
                        		<thead>
									<tr>
										<th >아이디</th>
			                            <th >패스워드</th>
			                            <th >회원 이름</th>
			                            <th >회원 닉네임</th>
			                            <th >이메일</th>
			                            <th>프로필 사진 이름</th>
			                            <th colspan="2"></th>
									</tr>
								</thead>
								<tbody>
										<c:forEach var="list" items="${requestScope.MemberInfo}">
											<tr>
												<td>${list.id}</td>
												<td>${list.password}</td>
												<td>${list.name}</td>
												<td>${list.nickname}</td>
												<td>${list.email}</td>
												<td>${list.file_name}</td>
												<td><a href="memberInfoUpdate.admin?id=${list.id}">[수정]</a></td>
												<td><a href="memberInfoDelete.admin?id=${list.id}">[삭제]</a></td>
											</tr>									
										</c:forEach>
								</tbody>
						</table>
					
		</div>	
		
		<!-- Footer -->
			<jsp:include page="/common/footer.jsp"></jsp:include>
			
		<!-- Scripts -->
		<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrollex.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrolly.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/browser.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/breakpoints.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/util.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>			
</body>
</html>