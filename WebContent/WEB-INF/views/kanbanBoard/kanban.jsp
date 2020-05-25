<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>BitBox - Memo</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/modal.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/kanban.css">
		<link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
		<noscript><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/noscript.css" /></noscript>
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
							<!-- <div class="inner" style="overflow: auto; white-space:nowrap;"> -->
								
								<div id="outer" style="overflow: auto; white-space: nowrap;">
									<c:set var="kanbanGroup" value="${requestScope.kanbanGroupList}"/>
									<c:forEach var="all" items="${requestScope.allList}" varStatus="status">
										<div class="memoSectionDiv shadow">
											<div class="memoTopHr"></div>
												<div class="memoTitle">${kanbanGroup[status.index].listName}</div>
												<c:forEach var="list" items="${all}">
													<div class='memoContent shadow' data-toggle='modal' data-target='#myModal'>
														${list.kanbanTitle}<br>
														<i class="ri-chat-3-line"></i>
														<i class="ri-attachment-2"></i>
													</div>
												</c:forEach>
											
											<div id="createMemoContentBtnBefore">
												<div class="createMemoContentBtn">+ Add a card</div>
											</div>
										</div>
									</c:forEach>
									<div id="createListBtnBefore"><div id="createListBtn">+ Add a list</div></div>
								</div>

							<!-- </div> -->
						</section>
					</article>

				<!-- Footer -->
				<jsp:include page="/common/footer.jsp"></jsp:include>

			</div>
			
			<!-- The Modal -->
			<jsp:include page="kanbanModal.jsp"></jsp:include>
			
		<!-- Scripts -->
		<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrollex.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrolly.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/browser.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/breakpoints.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/util.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/kanban.js"></script>

	</body>
</html>