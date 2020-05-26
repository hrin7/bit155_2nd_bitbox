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
		<script type="text/javascript" src="assets/js/signUp.js"></script> <!-- 회원가입시 로직함수 -->
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script><!-- 이쁜 알람창 CDN -->
		<script type="text/javascript">
		$(function() {
			if("${requestScope.msg}" == "정상적으로 회원가입이 완료되었습니다") {
				alert("${requestScope.msg}");
			}else if("${requestScope.msg}" == "회원가입중 에러 발생...다시 회원가입 바람"){
				alert("${requestScope.msg}");
			}
		});
		
		//비동기 방식으로 ID중복체크 클릭시 중복되는ID면 알람창으로 해당ID를 사용할수 없다고 알려주기
		function id_duplicate_check(){
			var userID = $('#id').val();
			//alert("중복체크 클릭시 "+userID);
			var idRegExp = /^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사
			
			if(userID == null || userID == ""){
				swal("ID를 입력해주세요");
				return false;
			}
			
			if(!idRegExp.test(userID)) { //입력받은 ID 값이 정규표현식에 일치하는지 테스트 
		          swal("아이디는 4~12자 영문 대소문자 또는 숫자 입력바람");
		          return false;
		    }
			
			$.ajax({
				url : "UserIdCheck.ajax", //요청할 URL 주소
				type : "POST",
				data :{id : userID}, 
				dataType : "text",
				success : function(result){ //성공적으로 데이터를 보냈다면 result에 반환값이 담김
					
					console.log("반환값"+result);
					if(result == 1){
						swal("사용할수 없는 ID입니다");
					}else if(result == 0){
						swal("사용 가능한 ID입니다");
					}else{
						swal("데이터 베이스 오류발생");
					}
				}
					
			});
		}
		</script>
	</head>
	<body class="landing is-preload">

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
							<%-- <c:set var="id" value="${sessionScope.userid}"/>
							<c:choose>
								<c:when test="${id != null}">
									<a href='#' class="button primary">Logout</a>
								</c:when>
								<c:otherwise> --%>
									<a href='#' class='button primary btn btn-info btn-lg' data-toggle='modal' data-target='#myModal'>Sign</a>
							<%-- 	</c:otherwise>
							</c:choose> --%>
						</li>
					</ul>
				</div>
			</section>

		</div>
		
		<!-- sign Modal -->
		<jsp:include page="common/signModal.jsp"></jsp:include>
		
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