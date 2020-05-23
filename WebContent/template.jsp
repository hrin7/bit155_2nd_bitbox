<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>프젝이름 - 기능</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">

		<!-- Page Wrapper -->
		<div id="page-wrapper">

			<!-- Header -->
			<jsp:include page="/common/header.jsp"></jsp:include>

				<!-- Main -->
					<article id="main">
						<header>
							<h2>Generic Page</h2>
							<p>Aliquam ut ex ut interdum donec amet imperdiet eleifend</p>
						</header>
						<section class="wrapper style5">
							<div class="inner">
								
								<!-- 이부분 지우고 필요한 버튼이나 엘리먼츠들을 elements.html에서 찾아서 작성하면 됨 -->
								<div class="table-wrapper">
									<table>
										<thead>
											<tr>
												<th>Name</th>
												<th>Description</th>
												<th>Price</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Item One</td>
												<td>Ante turpis integer aliquet porttitor.</td>
												<td>29.99</td>
											</tr>
											<tr>
												<td>Item Two</td>
												<td>Vis ac commodo adipiscing arcu aliquet.</td>
												<td>19.99</td>
											</tr>
											<tr>
												<td>Item Three</td>
												<td> Morbi faucibus arcu accumsan lorem.</td>
												<td>29.99</td>
											</tr>
											<tr>
												<td>Item Four</td>
												<td>Vitae integer tempus condimentum.</td>
												<td>19.99</td>
											</tr>
											<tr>
												<td>Item Five</td>
												<td>Ante turpis integer aliquet porttitor.</td>
												<td>29.99</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- 여기까지 -->

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

	</body>
</html>