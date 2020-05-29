<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<title>Spectral by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/index.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/sign.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/modal.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/idCheck_msg.js"></script> <!-- ID중복체크 로직함수+메시지띄우기(동률)-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/signUp.js"></script> <!-- 회원가입시 로직함수(동률)-->
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script><!-- 이쁜 알람창 CDN(동률) -->
		<noscript><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/noscript.css" /></noscript>
</head>
<body class="landing is-preload">

	<!-- Page Wrapper -->
		
		
	<!-- Header -->
			<jsp:include page="<%=request.getContextPath()%>/common/header.jsp"></jsp:include>
	
	<!-- 아이디 찾기 폼 -->
	
	
		
		
		
		
		
	<!-- Scripts -->
		<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrollex.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrolly.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/browser.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/breakpoints.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/util.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/sign.js"></script>		
</body>
</html>