<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>BITBOX - 게시판 수정하기</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<noscript><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/noscript.css" /></noscript>
		<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
		<style type="text/css">
        	#outer { margin: 0 auto; }
        	.align { margin: 20px 0; }
      	</style>
	</head>
	<body class="is-preload">

		<!-- Page Wrapper -->
		<div id="page-wrapper">

			<!-- Header -->
			<jsp:include page="/common/header.jsp"></jsp:include>

				<!-- Main -->
					<article id="main">
						<header>
							<h2>Board Edit</h2>
							<p>Please feel free to Edit it</p>
						</header>
						
						<section class="wrapper style5">
							<div class="inner">
								<section>
									<h4>Board Edit Form</h4>
									<form method="post" action="<%=request.getContextPath()%>/updateBoard.free" enctype="multipart/form-data">
										<div class="row gtr-uniform">
											<c:set var="board" value="${requestScope.board}"/>
											
											<div class="col-6 col-12-small">
												<select name="searchCode" id="searchCode" disabled>
													<option value="">- Category -</option>
												</select>
											</div>
											<div class="col-12">
												<input type="hidden" name="no" id="no" value="${board.no}">
												<input type="text" name="title" id="title" value="${board.title}"/>
											</div>
											<div class="col-6 col-12-mobilep">
												<input type="file" name="file" id="file" value="" style="color:black"/>
											</div>
											<div class="col-12">
												 <textarea id="summernote" name="content">${board.content}</textarea>
											</div>
											<div class="col-12">
												<ul class="actions">
													<li><input type="submit" value="Edit" class="primary" /></li>
													<li><input type="reset" value="Reset" /></li>
												</ul>
											</div>
										</div>
									</form>
									<a href="<%=request.getContextPath()%>/selectBoardList.free" id="goList">목록가기</a>
								</section>
							</div>
						</section>
					</article>

				<!-- Footer -->
				<jsp:include page="/common/footer.jsp"></jsp:include>

			</div>
			
		<!-- Scripts -->
		<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrollex.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrolly.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/browser.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/breakpoints.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/util.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
	        //summernote
		  	$('#summernote').summernote({
		       	height: 300,                 // 에디터 높이
		       	minHeight: null,             // 최소 높이
		       	maxHeight: null,             // 최대 높이
		       	focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		       	lang: "ko-KR",               // 한글 설정
		       	placeholder: 'Enter your content'   //placeholder 설정
		  	});
		
			//ajax 카테고리
			$.ajax({
				url: "SelectBoardType.ajax",
				dataType: "json",
				success: function(resData){
					console.log(resData);
					let html = "";
					$.each(resData, function(index, obj) {
						if(obj.boardCode == 2){
							html += "<option value='"+obj.boardCode+"' selected>"+obj.boardName+"</option>";
						} else {
							html += "<option value='"+obj.boardCode+"'>"+obj.boardName+"</option>";
						}
					});
					$('#searchCode').append(html);
				}
			}) 
		});
		    </script>

	</body>
</html>