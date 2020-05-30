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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	</head>
	
	<body class="is-preload">

		<!-- Page Wrapper -->
		<div id="page-wrapper">

			<!-- Header -->
			<header id="header">
			<h1><a href="index.jsp">BitBox</a></h1>
			<nav id="nav">
				<ul>
					<li class="special">
						<a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<!-- 로그인 시 보이는 메뉴 -->
								<%
									if(session.getAttribute("userID") == "admin"){
									
								%>
									<!-- admin 계정으로 로그인한 경우  -->
									<li>
									<img src="<%=request.getContextPath()%>/images/user.png" width="150px;" height="150px;">
									<a href="#"><%=session.getAttribute("userID")+"님" %></a>
									<a href="<%=request.getContextPath()%>/logout.user" class="button">logout</a>
									
									</li>
									<li><br><a href="myBoardList.my">My Board</a></li>
									<li><a href="selectList.kanban">KanBan</a></li>
									<li><a href="template.jsp">Scheduler</a></li>
									<li><a href="template.jsp">To-do-List</a></li>
									
									<!-- 로그인 안해도 보이는 메뉴 -->
									<li><br><a href="template.jsp">Notice</a></li>
									<li><a href="selectBoardList.free">Free Board</a></li>
									
									<!-- admin 계정으로 로그인시 보이는 메뉴 -->
									<li><a href="#">회원정보관리</a></li>
								<%
									}else if(session.getAttribute("userID") == null || session.getAttribute("userID") == ""){
								%>
															
									<!-- 로그인 안해도 보이는 메뉴 -->
									<li><br><a href="template.jsp">Notice</a></li>
									<li><a href="elements.html">Free Board</a></li>
								
								<%
									}else if(session.getAttribute("userID") != "admin"){
								%>
									<!-- 일반 계정으로 로그인한 경우  -->
									<li>
									<img src="<%=request.getContextPath()%>/images/user.png" width="150px;" height="150px;">
									<a href="#"><%=session.getAttribute("userID")+"님" %></a>
									<a href="<%=request.getContextPath()%>/logout.user" class="button">logout</a>
									</li>
									<li><br><a href="myBoardList.my">My Board</a></li>
									<li><a href="selectList.kanban">KanBan</a></li>
									<li><a href="template.jsp">Scheduler</a></li>
									<li><a href="template.jsp">To-do-List</a></li>
								
									<!-- 로그인 안해도 보이는 메뉴 -->
									<li><br><a href="template.jsp">Notice</a></li>
									<li><a href="selectBoardList.free">Free Board</a></li>
								<%			
									}
								%>
								</ul>
							</div>
						</li>
					</ul>
				</nav>
			</header>

			<!-- Main -->
			<article id="main">
				<section class="wrapper style5">
					<!-- <div class="inner"> -->
						<div id="outer">
							<c:forEach var="kanbanGroup" items="${requestScope.kanbanGroupList}">
								<div class="memoSectionDiv shadow" data-title='${kanbanGroup.listName}' data-code='${kanbanGroup.kanbanCode}' ondrop='drop(event)' ondragover='allowDrop(event)'>
									<div class="memoTopHr"></div>
									<div class="memoTitle">${kanbanGroup.listName}</div>
									<div class="deleteListDiv"><a href="javascript:void(0);" data-code='${kanbanGroup.kanbanCode}' class="deleteListBtn" style="text-decoration: none !important;"><i class="ri-close-fill"></i></a></div>
									
									<c:forEach var="kanban" items="${requestScope.kanbanList}" varStatus="status">
										<c:if test="${kanbanGroup.kanbanCode == kanban.kanbanCode}">
											<c:if test="${!empty kanban.kanbanTitle}">
												<div class='memoContent shadow' data-toggle='modal' data-target='#myModal' data-value='${kanban.kanbanNo}' data-title='${kanban.listName}' style="clear: both;" draggable="true" ondragstart='drag(event)'>
													${kanban.kanbanTitle}<br>
													<c:if test="${!empty kanban.kanbanContent}">
														<i class="ri-align-left"></i>
													</c:if>
													<c:if test="${kanban.kanbanCommentCount != 0}">
														<i class="ri-chat-3-line"></i>${kanban.kanbanCommentCount}
													</c:if>
													<c:if test="${kanban.kanbanFileCount != 0}">
														<i class="ri-attachment-2"></i>${kanban.kanbanFileCount}
													</c:if>
												</div>
											</c:if>
										</c:if>
									</c:forEach>
									
									<div id="createMemoContentBtnBefore">
										<div class="createMemoContentBtn">+ Add a card</div>
									</div>
								</div>
							</c:forEach>
							<div id="createListBtnBefore"><div id="createListBtn" class="shadow">+ Add a list</div></div>
						</div>

					<!-- </div> -->
				</section>
			</article>

			<!-- Footer -->
			<footer id="footer">
				<ul class="icons">
					<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
					<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
					<li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
					<li><a href="#" class="icon brands fa-dribbble"><span class="label">Dribbble</span></a></li>
					<li><a href="#" class="icon solid fa-envelope"><span class="label">Email</span></a></li>
				</ul>
				<ul class="copyright">
					<li>&copy; Untitled</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
				</ul>
			</footer>

			</div>
			
			
			
			<!-- The Modal -->
			<div class="modal fade" id="myModal">
				<div class="modal-dialog modal-lg shadow">
					<div class="modal-title">
			
						<!-- Modal Header -->
						<div class="modal-header">
							<div style="float: left;"><h2 class="modal-title" id="cardName"></h2></div>
							<div style="display: inline-block; float: right;"><a href="javascript:void(0);" id="deleteCardBtn"><i class="ri-close-fill"></i>카드삭제</a></div>
							<h5 class="modal-title" id="listName" style="clear: both;"></h5>
							<input type="hidden" id="kanbanNo"/>
						</div>
			
						<!-- Modal body -->
						<div class="modal-body">
							<h2 class="font-design" style="float: left;">Description</h2>
							<button class="button primary small" style="padding: 0px 5px;" id="editBtn">Edit</button>
							<br><br><br>
							<p id="kanbanContent" style="clear: both;"></p>
							<br>
							<!-- <form name="uploadForm" id="uploadForm" enctype="multipart/form-data" method="post">
								<div id="fileArea">
									<input type="file" name="file" id="file" class="font-design"/>
								</div>
								<input type="submit" value="Upload" class="button primary small"/>
							</form> -->
							<p id="kanbanDate" style="font-size: 10px;"></p>
							
							<!-- 댓글 -->
							<h2 class="font-design">Activity</h2>
							<div id="com"></div>
						
							<b class="font-design">${sessionScope.userID}</b>
							<input type="text" name="comment" id="comment" placeholder="Enter your comment"/>
							<br>
							<button class="button special small font-design" id="commWrite">등록</button>
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
		<script src="assets/js/jquery.scrollex.min.js"></script>
		<script src="assets/js/jquery.scrolly.min.js"></script>
		<script src="assets/js/browser.min.js"></script>
		<script src="assets/js/breakpoints.min.js"></script>
		<script src="assets/js/util.js"></script>
		<script src="assets/js/main.js"></script>
		<script type="text/javascript">
			console.log(window.location.pathname);
			let url = window.location.pathname;
			if(url == "/2nd_bitbox/index.jsp" || url == "/2nd_bitbox/logout.do" || url == "/2nd_bitbox/loginOk.do") {
				$('header').attr('class', 'alt');
			}
		</script>
	</body>
</html>