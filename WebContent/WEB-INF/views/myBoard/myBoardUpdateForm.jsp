<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>게시판 글수정</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
		<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
		<style type="text/css">
			#outer { margin: 0 auto; }
			.align { margin: 20px 0; }
		</style>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$('#hiddenImg').val('${board.boardFile}');
			});
		</script>
	</head>
<body class="is-preload">
	<div id="page-wrapper">
	
		<!-- Header -->
		<jsp:include page="<%=request.getContextPath()%>/common/header.jsp"></jsp:include>
		
		<section id="main" class="container">
			<header>
				<h2>Free Board</h2>
				<p>자유롭게 작성하세요.</p>
			</header>
			
			<div class="row">
				<div class="col-12">
					<!-- Form -->
					<section class="box">
						
						<h3>Free Board</h3>
						<form method="post" action="<%=request.getContextPath()%>/updateBoard.board" enctype="multipart/form-data">
							<div class="row gtr-uniform gtr-50">
								<div class="col-6 col-12-mobilep">
									<input type="hidden" name="boardNum" value="${board.boardNum}" />
									<input type="text" name="title" id="title" value="${board.boardSubject}" />
								</div>
								<div class="col-6 col-12-mobilep">
									<input type="file" name="file" id="file"/>
									<input type="hidden" name="image" id="hiddenImg"/>
								</div>
								<div class="col-12">
									<textarea id="summernote" name="content">${board.boardContent}</textarea>
								</div>
								<div class="col-12">
									<ul class="actions">
										<li><input type="submit" value="Write" /></li>
										<li><input type="reset" value="Reset" class="alt" /></li>
									</ul>
								</div>
							</div>
						</form>
						<a href="<%=request.getContextPath()%>/boardList.board" id="goList">목록가기</a>
						<hr />
						
					</section>
				</div>
			</div>
			
		</section>
		
		<!-- Footer -->
		<jsp:include page="<%=request.getContextPath()%>/common/footer.jsp"></jsp:include>
		
	</div>
	<!-- Scripts -->
		<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.dropotron.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrollex.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/browser.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/breakpoints.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/util.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
		<script type="text/javascript">
			//summernote
			$(document).ready(function() {
				$('#summernote').summernote({
					  height: 300,                 // 에디터 높이
					  minHeight: null,             // 최소 높이
					  maxHeight: null,             // 최대 높이
					  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
					  lang: "ko-KR"					// 한글 설정
				});
			});
		</script>
</body>
</html>