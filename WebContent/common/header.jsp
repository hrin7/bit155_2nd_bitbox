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
						<li>
							<img src="../images/user.png" width="150px;" height="150px;">
							<a href="#">누구누구님</a>
						</li>
						<li><br><a href="myBoardList.my">My Board</a></li>
						<li><a href="selectList.kanban">KanBan</a></li>
						<li><a href="template.jsp">Scheduler</a></li>
						<li><a href="template.jsp">To-do-List</a></li>
						
						<!-- 로그인 안해도 보이는 메뉴 -->
						<li><br><a href="template.jsp">Notice</a></li>
						<li><a href="selectBoardList.free">Free Board</a></li>
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
	if(url == "/2nd_bitbox/index.jsp" || url == "/2nd_bitbox/logout.do" || url == "/2nd_bitbox/loginOk.do") {
		$('header').attr('class', 'alt');
	}
</script>