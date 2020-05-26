<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>DIARY</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
		<script type="text/javascript">
			$(function() {
				$('#hiddenImg').val('${myBoard.diaryFile}');
			});
		</script>
	</head>
	<body class="is-preload">

		<!-- Page Wrapper -->
		<div id="page-wrapper">

			<!-- Header -->
			<jsp:include page="/common/header.jsp"></jsp:include>

				<!-- Main -->
				<article id="main">
					<header>
						<h2>DIARY</h2>
						<p>keeping a daily journal can change your life</p>
					</header>
					<section class="wrapper style5">
						<div class="inner">							
							<div class="row">
					<div class="col-12">
						<!-- Form -->
						<section class="box">
							
							<form method="post" action="<%=request.getContextPath()%>/myBoardInsert.my" enctype="multipart/form-data">
								<div class="row gtr-uniform gtr-50">
									<div class="col-6 col-12-mobilep">
										<input type="text" name="title" id="title" placeholder="Title" />
									</div>
									<div class="col-6 col-12-mobilep">
										<input type="file" name="file" id="file"/>
									</div>
									<div class="col-12">
										<textarea id="summernote" name="message"></textarea>
									</div>
									<div class="col-12">
										<ul class="actions">
											<li><input type="submit" value="확인" /></li>
										</ul>
									</div>
								</div>
							</form>
							
							<a href="<%=request.getContextPath()%>/myBoardList.my" id="">LIST</a>
							<hr />
							
						</section>
					</div>

							</div>
						</div>
					</section>
				</article>

				<!-- Footer -->
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>
			
			<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
			
			<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
			<script type="text/javascript">
				//summernote
				$(document).ready(function() {
					$('#summernote').summernote({
						  height: 300,					// 에디터 높이
						  minHeight: null,				// 최소 높이
						  maxHeight: null,				// 최대 높이
						  focus: true,					// 에디터 로딩후 포커스를 맞출지 여부
						  lang: "ko-KR"					// 한글 설정
					});
				});
			</script>

	</body>
</html>