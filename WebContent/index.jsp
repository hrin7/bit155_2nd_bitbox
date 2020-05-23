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
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
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