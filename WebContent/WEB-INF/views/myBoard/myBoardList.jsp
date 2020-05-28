<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>MY BOARD</title>
			<meta charset="utf-8" />
			<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
			<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
			<link href="https://cdn.jsdelivr.net/npm/remixicon@2.4.0/fonts/remixicon.css" rel="stylesheet"> <!-- 아이콘 -->
			<noscript><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/noscript.css" /></noscript>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	</head>
	<script type="text/javascript">
		$(function() {
			if("${requestScope.msg}" != "") {
				alert("${requestScope.msg}");
            }
        });
	</script>
	<body class="is-preload">
	      
		<!-- Page Wrapper -->
		<div id="page-wrapper">
		
			<!-- Header -->
			<jsp:include page="/common/header.jsp"></jsp:include>
			
			<!-- Main -->
			<article id="main">
				<header>
					<h2>MY BOARD</h2>
					<p>keeping a daily journal can change your life</p>
				</header>
				
				<section class="wrapper style5">
					<div class="inner">
					
						<!-- Table -->                     
						<div class="table-wrapper">
                        	<table>
                        		<thead>
									<tr>
										<th width="10%">글번호</th>
										<th width="65%">제목</th>
										<th width="25%">작성일</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="myBoardList" items="${requestScope.myBoardList}">
										<tr>
											<td>${myBoardList.diaryNo}</td>
											<td>
												<c:forEach var="i" begin="1" end="${myBoardList.diaryDepth}">
													&nbsp;&nbsp;&nbsp;
												</c:forEach>
												<!-- depth가 0보다 크면 답글. 답글이 있을 때는 답글 이미지를 붙여준다 -->
												<c:if test="${myBoardList.diaryDepth>0}">ㄴ</c:if>
												<a href="myBoardSelect.my?diaryNo=${myBoardList.diaryNo}">${myBoardList.diaryTitle}</a>
												<c:if test="${!empty myBoardList.diaryFileName}">
                                    				<i class="ri-attachment-2"></i>
                                    			</c:if>
											</td>
											<td>${myBoardList.diaryDate}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<p align="right"><a href="<%=request.getContextPath()%>/myBoardInsertForm.my" class="button small" id="writeBtn"><i class="ri-pencil-line"> write</i></a></p>
														
							<!-- 페이징 -->							
							<p align="center">
							<!--이전 링크 -->
							<c:choose>
								<c:when test="${requestScope.cpage > 1}">
									<a href="boardList.board?cp=${requestScope.cpage-1}&ps=${requestScope.pageSize}"><i class="ri-arrow-left-s-line"></i></a>
								</c:when>
								<c:otherwise>
									<a href="#"><i class="ri-arrow-left-s-line"></i></a>
								</c:otherwise>
							</c:choose>
							<!-- page 목록 나열하기 -->
							<c:forEach var="i" begin="1" end="${requestScope.pageCount}" step="1">
								<c:choose>
									<c:when test="${requestScope.cpage == i}">
										<a href="#">${i}</a>
									</c:when>
									<c:otherwise>
										<a href="boardList.board?cp=${i}&ps=${requestScope.pageSize}">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!--다음 링크 -->
							<c:choose>
								<c:when test="${requestScope.cpage < requestScope.pageCount}">
									<a href="boardList.board?cp=${requestScope.cpage+1}&ps=${requestScope.pageSize}"><i class="ri-arrow-right-s-line"></i></a>
								</c:when>
								<c:otherwise>
									<a href="#"><i class="ri-arrow-right-s-line"></i></a>
								</c:otherwise>
	                        </c:choose>
							</p>
							
						</div>
					</div>
				</section>
			</article>
        	<!-- Footer -->
			<jsp:include page="/common/footer.jsp"></jsp:include>
		</div>
		
		<!-- Scripts -->
		<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrollex.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.scrolly.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/browser.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/breakpoints.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/util.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
		
		<script type="text/javascript">		
		//개인 계정으로만 접근 가능
		//console.log('${sessionScope.admin}');		
//		if('${sessionScope.admin}' == "") {
//			$('#writeBtn').attr('href', '#').click(function() {
//				alert('로그인 후 이용가능합니다.');
//			});
//		}
		</script>
      
	</body>
</html>