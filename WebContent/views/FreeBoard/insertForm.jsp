<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>BITBOX - 게시판 글쓰기</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../../assets/css/main.css" />
		<noscript><link rel="stylesheet" href="../../assets/css/noscript.css" /></noscript>
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
							<h2>Write</h2>
							<p>Please feel free to create it</p>
						</header>
						<section class="wrapper style5">
							<div class="inner">
								<section>
									<h4>Board Insert Form</h4>
									<form method="post" action="#">
										<div class="row gtr-uniform">
											
											<div class="col-6 col-12-small">
												<select name="boardCategory" id="boardCategory">
													<option value="">- Category -</option>
													<option value="1">Free Board</option>
													<option value="1">Notice</option>
												</select>
											</div>
											<div class="col-12">
												<input type="text" name="title" id="title" value="" placeholder="title"/>
											</div>
											<div class="col-6 col-12-mobilep">
												<input type="file" name="file" id="file" value=""/>
											</div>
											<div class="col-12">
												 <textarea id="summernote" name="content"></textarea>
											</div>
											<div class="col-12">
												<ul class="actions">
													<li><input type="submit" value="Create" class="primary" /></li>
													<li><input type="reset" value="Reset" /></li>
												</ul>
											</div>
										</div>
									</form>
									<a href="#" id="goList">목록가기</a>
								</section>
							</div>
						</section>
					</article>

				<!-- Footer -->
				<jsp:include page="/common/footer.jsp"></jsp:include>

			</div>
			
		<!-- Scripts -->
		<script src="/assets/js/jquery.min.js"></script>
		<script src="/assets/js/jquery.scrollex.min.js"></script>
		<script src="/assets/js/jquery.scrolly.min.js"></script>
		<script src="/assets/js/browser.min.js"></script>
		<script src="/assets/js/breakpoints.min.js"></script>
		<script src="/assets/js/util.js"></script>
		<script src="/assets/js/main.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
		<script type="text/javascript">
          //summernote
          $(document).ready(function() {
            	$('#summernote').summernote({
                 	height: 300,                 // 에디터 높이
                 	minHeight: null,             // 최소 높이
                 	maxHeight: null,             // 최대 높이
                 	focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
                 	lang: "ko-KR",               // 한글 설정
                 	placeholder: 'Enter your content'   //placeholder 설정
            	});
         	});
      </script>

	</body>
</html>