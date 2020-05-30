<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    
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
							<!-- <li><a href="template.jsp">Scheduler</a></li>
							<li><a href="template.jsp">To-do-List</a></li> -->
							
							<!-- 로그인 안해도 보이는 메뉴 -->
							<li><br><a href="selectNoticeList.notice?boardCode=1">Notice</a></li>
							<li><a href="selectBoardList.free?boardCode=2">Free Board</a></li>
							
							<!-- admin 계정으로 로그인시 보이는 메뉴 -->
							<li><a href="memberInfoRead.admin">회원정보관리</a></li>
						<%
							}else if(session.getAttribute("userID") == null || session.getAttribute("userID") == ""){
						%>
													
							<!-- 로그인 안해도 보이는 메뉴 -->
							<li><br><a href="selectNoticeList.notice?boardCode=1">Notice</a></li>
							<li><a href="selectBoardList.free?boardCode=2">Free Board</a></li>
						
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
							<li><br><a href="selectNoticeList.notice?boardCode=1">Notice</a></li>
							<li><a href="selectBoardList.free?boardCode=2">Free Board</a></li>
						<%			
							}
						%>
					</ul>
				</div>
			</li>
		</ul>
	</nav>
</header>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	console.log(window.location.pathname);
	let url = window.location.pathname;
	if(url == "/2nd_bitbox/index.jsp" || url == "/2nd_bitbox/logout.user" || url == "/2nd_bitbox/login.user") {
		$('header').attr('class', 'alt');
	}
</script>