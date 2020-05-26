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
						<section class="wrapper style5" style="overflow: auto; white-space: nowrap;">
							<!-- <div class="inner"> -->
								<div id="outer">
									<c:set var="kanbanGroup" value="${requestScope.kanbanGroupList}"/>
									<c:forEach var="all" items="${requestScope.allList}" varStatus="status">
										<div class="memoSectionDiv shadow" data-title='${kanbanGroup[status.index].listName}'>
											<div class="memoTopHr"></div>
												<div class="memoTitle">${kanbanGroup[status.index].listName}</div>
												<c:forEach var="list" items="${all}">
													<div class='memoContent shadow' data-toggle='modal' data-target='#myModal' data-value='${list.kanbanNo}' data-title='${kanbanGroup[status.index].listName}'>
														${list.kanbanTitle}<br>
														<c:if test="${!empty list.kanbanContent}">
															<i class="ri-align-left"></i>
														</c:if>
														<c:if test="${list.kanbanCommentCount != 0}">
															<i class="ri-chat-3-line"></i>${list.kanbanCommentCount}
														</c:if>
														<c:if test="${list.kanbanFileCount != 0}">
															<i class="ri-attachment-2"></i>${list.kanbanFileCount}
														</c:if>
														
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
			<div class="modal fade" id="myModal">
				<div class="modal-dialog modal-lg shadow">
					<div class="modal-title">
			
						<!-- Modal Header -->
						<div class="modal-header">
							<h2 class="modal-title" id="cardName"></h2>
							<h5 class="modal-title" id="listName"></h5>
						</div>
			
						<!-- Modal body -->
						<div class="modal-body">
							<h2 class="font-design" style="float: left;">Description</h2>
							<button class="button primary small" style="padding: 0px 5px;" id="editBtn">Edit</button>
							<br><br><br>
							<p id="kanbanContent" style="clear: both;"></p>
							<br>
							<p id="kanbanDate"></p>
							
							<h2 class="font-design">Activity</h2>
							<input type="text"/>
						</div>
			
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="button primary small" data-dismiss="modal">Close</button>
						</div>
			
					</div>
				</div>
			</div>
			
		<!-- Scripts -->
		<script src="<%=request.getContextPath()%>/assets/js/kanban.js"></script>

	</body>
</html>