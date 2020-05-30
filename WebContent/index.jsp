<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Spectral by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/index.css" />
		<link rel="stylesheet" href="assets/css/sign.css" />
		<link rel="stylesheet" href="assets/css/modal.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/js/idCheck_msg.js"></script> <!-- ID중복체크 로직함수+메시지띄우기(동률)-->
		<script type="text/javascript" src="assets/js/signUp.js"></script> <!-- 회원가입시 로직함수(동률)-->
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script><!-- 이쁜 알람창 CDN(동률) -->
		<script src="https://www.google.com/recaptcha/api.js" async defer></script><!-- 구글 리캡차 v2 API -->
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		<script type="text/javascript">
			/* $(function() {
				if("${requestScope.msg}" == "정상적으로 회원가입이 완료되었습니다") {
					alert("${requestScope.msg}");
				}else if("${requestScope.msg}" == "회원가입중 에러 발생...다시 회원가입 바람"){
					alert("${requestScope.msg}");
				}else if("${requestScope.msg}" == "로그인 성공 메인페이지로 이동"){
					alert("${requestScope.msg}");
				}
			}); */
		</script>
		<!-- 로그아웃후 메인페이지로 이동하는데 뒤로가기 버튼을 막는 함수 -->
		 <script type="text/javascript">
			 window.history.forward();
			 function noBack(){window.history.forward();}
		</script>
	</head>
	<!-- 로그아웃후 메인페이지로 뒤로가기가 안될 페이지 설정 -->
	<body class="landing is-preload" onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
		
		<!-- Page Wrapper -->
		<div id="page-wrapper">
		
			<!-- Header -->
			<jsp:include page="/common/header.jsp"></jsp:include>

			<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<h2>Spectral</h2>
					<p>Another fine responsive<br />
					site template freebie<br />
					crafted by <a href="http://html5up.net">HTML5 UP</a>.</p>
					<ul class="actions special">
						<li>
							<c:set var="id" value="${sessionScope.userID}"/>
							<c:choose>
								<c:when test="${id == null || id == ''}">
									<a href='#' class='button primary btn btn-info btn-lg' data-toggle='modal' data-target='#myModal'>Sign</a>
								</c:when>
								<c:otherwise>
									<a href="<%=request.getContextPath()%>/logout.user" class="button primary">Logout</a>
							</c:otherwise>
							</c:choose> 
						</li>
					</ul>
				</div>
			</section>
		
		</div>
		
		<!-- sign Modal -->
		<jsp:include page="/common/signModal.jsp"></jsp:include>
		
		<!-- Scripts -->
		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/jquery.scrollex.min.js"></script>
		<script src="assets/js/jquery.scrolly.min.js"></script>
		<script src="assets/js/browser.min.js"></script>
		<script src="assets/js/breakpoints.min.js"></script>
		<script src="assets/js/util.js"></script>
		<script src="assets/js/main.js"></script>
		<script src="assets/js/sign.js"></script>
		
	</body>
</html>