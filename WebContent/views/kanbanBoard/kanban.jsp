<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>BitBox - Memo</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../../assets/css/main.css" />
		<link rel="stylesheet" href="../../assets/css/modal.css">
		<link rel="stylesheet" href="../../assets/css/kanban.css">
		<link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	</head>
	
	<body class="is-preload">

		<!-- Page Wrapper -->
		<div id="page-wrapper">

			<!-- Header -->
			<jsp:include page="/common/header.jsp"></jsp:include>

				<!-- Main -->
					<article id="main">
						<section class="wrapper style5">
							<div class="inner">
								
								<div id="outer">
									<div id="createListBtnBefore"><div id="createListBtn">+ Add a list</div></div>
								</div>

							</div>
						</section>
					</article>

				<!-- Footer -->
				<jsp:include page="/common/footer.jsp"></jsp:include>

			</div>
			
			<!-- The Modal -->
			<jsp:include page="kanbanModal.jsp"></jsp:include>
			
		<!-- Scripts -->
		<script src="../../assets/js/jquery.min.js"></script>
		<script src="../../assets/js/jquery.scrollex.min.js"></script>
		<script src="../../assets/js/jquery.scrolly.min.js"></script>
		<script src="../../assets/js/browser.min.js"></script>
		<script src="../../assets/js/breakpoints.min.js"></script>
		<script src="../../assets/js/util.js"></script>
		<script src="../../assets/js/main.js"></script>
		<script src="../../assets/js/kanban.js"></script>

	</body>
</html>