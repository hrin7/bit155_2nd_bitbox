<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>글작성하기</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
	    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
		<style type="text/css">
			#outer { margin: 0 auto; }
			.align { margin: 20px 0; }
		</style>
		
	</head>
<body class="is-preload">
	<div id="page-wrapper">
	
		<!-- Header -->
		<jsp:include page="/common/header.jsp"></jsp:include>
		
		<section id="main" class="container">
			<header>
				<h2>DIARY</h2>
				<p>keeping a daily journal can change your life</p>
			</header>
			
			<div class="row">
				<div class="col-12">
					<!-- Form -->
					<section class="box">
					
						<h3>Free Board</h3>
						<form method="post" action="<%=request.getContextPath()%>/insertBoard.board" enctype="multipart/form-data">
							<div class="row gtr-uniform gtr-50">
								<div class="col-6 col-12-mobilep">
									<input type="text" name="title" id="title" placeholder="Title" />
								</div>
								<div class="col-6 col-12-mobilep">
									<input type="file" name="file" id="file"/>
								</div>
								<div class="col-12">
									<textarea id="summernote" name="content"></textarea>
								</div>
								<div class="col-12">
									<ul class="actions">
										<li><input type="submit" value="Write" /></li>
										<li><input type="reset" value="Reset" class="alt" /></li>
									</ul>
								</div>
							</div>
						</form>
						<a href="<%=request.getContextPath()%>/myBoardList.my" id="goList">LIST</a>
						<hr />
						
					</section>
				</div>
			</div>
			
		</section>
		
		<!-- Footer -->
		<jsp:include page="/common/footer.jsp"></jsp:include>
		
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
					  lang: "ko-KR",					// 한글 설정
					  placeholder: 'Enter your content'	//placeholder 설정
				});
			});
		</script>
</body>
</html>