<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	/* 
		주소를 외워서 관리자 페이지로 이동할려하면 강제로 인덱스 페이지로 이동시킴 보안에 준비해야됨 
	
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
		<title>BITBOX 관리자 - 회원정보수정 페이지 </title>
			<meta charset="utf-8" />
			<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
			<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
			<link href="https://cdn.jsdelivr.net/npm/remixicon@2.4.0/fonts/remixicon.css" rel="stylesheet"> <!-- 아이콘 -->
			<noscript><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/noscript.css" /></noscript>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	</head>

<body class="is-preload">

	<!-- Page Wrapper -->
		<div id="page-wrapper">
		
		
					<h2>Member Management</h2>
					<p>회원정보 관리 페이지 입니다</p>
			
			<!-- Header -->
			<jsp:include page="/common/header.jsp"></jsp:include>
				<form method="post" action="memberInfoUpdateOk.admin">
						<table>
                        		<thead>
									<tr>
										<th >아이디</th>
			                            <th >패스워드</th>
			                            <th >회원 이름</th>
			                            <th >회원 닉네임</th>
			                            <th >이메일</th>
			                            <th>프로필 사진 이름</th>
									</tr>
								</thead>
								<tbody>
										<c:set var="list" value="${requestScope.memberUpdateInfo}"/>
											<tr>
												<td>
													<input type="text" name="id" value="${list.id}" readonly>
												</td>
												<td>
													<input type="text" name="pwd" value="${list.password}">
												</td>
												<td>
													<input type="text" name="name" value="${list.name}">
												</td>
												<td>
													<input type="text" name="nickname" value="${list.nickname}">
												</td>
												<td>
													<input type="text" name="email" value="${list.email}">
												</td>
												<td>
													<input type="text" name="file_name" value="${list.file_name}" readonly>
												</td>
											</tr>									
											<tr>
										  	  <td colspan="4"></td>
											  <td><input type="submit" value="수정하기"></td>
											  <td><button type="button" onclick="history.go(-1)">회원정보 리스트이동</button></td>
											  
										    </tr>
								</tbody>
						</table>
					</form>
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