<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>다이어리 상세보기</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../../assets/css/main.css" />
		<link href="https://cdn.jsdelivr.net/npm/remixicon@2.4.0/fonts/remixicon.css" rel="stylesheet"> <!-- 아이콘 -->
		<style type="text/css">
			#outer { margin: 0 auto; }
			.align { margin: 20px 0; }
		</style>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script type="text/javascript">
			$(function() {
				if ("${requestScope.msg}" != "") {
					alert("${requestScope.msg}");
				}
				
				//사진
				$('#image').attr('src', 'upload/${board.boardFile}');
				/* $('#hiddenImg').val('${board.boardFile}'); */
			});
		</script>
</head>
<body class="is-preload">
	<div id="page-wrapper">
	
		<!-- Header -->
		<jsp:include page="../../header.jsp"></jsp:include>
		
		<section id="main" class="container">
			<header>
				<h2>Free Board</h2>
				<p>게시글 상세보기</p>
			</header>
			
			<div class="row">
				<div class="col-12">
					<!-- Form -->
					<section class="box">
						
						<c:set var="board" value="${requestScope.board}"/>
						<h3>${board.boardSubject}</h3>
						<blockquote>
							${board.boardName}<br>
							${board.boardDate}  조회수 ${board.boardReadcount}<br>
							<c:if test="${!empty board.boardFile}">
							첨부파일  <a href='upload/${board.boardFile}' download>${board.boardFile}</a>
							</c:if>
						</blockquote> 
						<pre><c:if test="${!empty board.boardFile}"><img id="image" width="500px"/><br></c:if>${board.boardContent}</pre>
						
						<a href="updateInfo.board?boardNum=${board.boardNum}" class="button alt">수정</a>
						<a href="deleteBoard.board?boardNum=${board.boardNum}" class="button alt">삭제</a>
						<a href="../insertReBoardForm.board?boardNum=${board.boardNum}" class="button alt">답글</a>
						<hr>
						
						<!-- 댓글 -->
						<div id="com"></div>
						
						<b>${requestScope.name}</b>
						<textarea name="comment" id="comment" placeholder="Enter your comment" rows="3"></textarea>
						<br>
						<button class="button special small" id="commWrite">등록</button>
						<br><br>
						<a href="boardList.board" id="goList">목록으로</a>
						
					</section>
				</div>
			</div>
			
		</section>
		
		<!-- Footer -->
		<jsp:include page="../../footer.jsp"></jsp:include>
		
	</div>
	<!-- Scripts -->
		<script src="../assets/js/jquery.min.js"></script>
		<script src="../assets/js/jquery.dropotron.min.js"></script>
		<script src="../assets/js/jquery.scrollex.min.js"></script>
		<script src="../assets/js/browser.min.js"></script>
		<script src="../assets/js/breakpoints.min.js"></script>
		<script src="../assets/js/util.js"></script>
		<script src="../assets/js/main.js"></script>
		<script type="text/javascript">

			//댓글 목록
			$.ajax({
				url: "SelectCommentList.ajax",
				data: {boardNum:'${board.boardNum}'},
				dataType: "json",
				success: function(resData) {
					makeComment(resData);
				}
			});
			
			//댓글 등록
			$('#commWrite').click(function() {
				if($('#comment').val() == "") {
					alert('내용을 입력하세요');
					return false;
				}
				
				$.ajax({
					url: "InsertComment.ajax",
					data: {
						boardNum:'${board.boardNum}',
						name:'${requestScope.name}',
						content:$('#comment').val()
					},
					dataType: "json",
					success: function(resData) {
						$('#com').empty();
						makeComment(resData);
						$('#comment').val("");
					}
				});
			});
			
			//댓글 삭제
			$('#com').on('click', '.deleteComment', function() {
				$.ajax({
					url: "DeleteComment.ajax",
					data: {
						boardNum: '${board.boardNum}',
						commentNum: $(this).data("value")
					},
					dataType: "json",
					success: function(resData) {
						$('#com').empty();
						makeComment(resData);
					}
				});
			});
			
			//댓글 수정
			var check = true;
			$('#com').on('click', '.updateComment', function() {
				if(check) {
					check = false;
					//클릭한 a태그의 class를 cancelUpdate로 바꾸고 아이콘 바꾸기
					$(this).attr('class', 'cancelUpdate');
					$(this).html("<i class='ri-close-line'></i>");
					
					//this(a태그)의 부모태그(blockquote태그)를 parentTag변수에 담기
					var parentTag = $(this).parent();
					//자식태그 중 code(댓글내용이 들어있는 태그)를 찾아서 변수에 담기
					var code = parentTag.find('code');
					//댓글내용 변수에 담기
					var codeText = parentTag.find('code').text();
					//댓글내용이 있는 code태그 삭제
					$(code).remove();
					//input태그 append하기(value에는 기존의 값 셋팅하고, 포커스주기)
					var html = "";
					html += '<div id="updateDiv">';
					html += '<input type="text" value="'+codeText+'" name="content" id="updateContent">';
					html += '<button class="button special small alt" id="commUpdateBtn">Edit</button>';
					html += '</div>';
					parentTag.append(html);
					parentTag.find('input').focus();
					
					var commentNum = $(this).data("value");
					$('#commUpdateBtn').click(function() {
						if($('#comment').val() == "") {
							alert('내용을 입력하세요');
							return false;
						}
						
						$.ajax({
							url: "UpdateComment.ajax",
							data: {
								boardNum: '${board.boardNum}',
								commentNum: commentNum,
								content: $('#updateContent').val()
							},
							dataType: "json",
							success: function(resData) {
								$('#com').empty();
								makeComment(resData);
							}
						});
					});
				}
				
				//수정 취소 눌렀을 경우 리스트 다시 불러오기
				$('#com').on('click', '.cancelUpdate', function() {
					$.ajax({
						url: "SelectCommentList.ajax",
						data: {boardNum:'${board.boardNum}'},
						dataType: "json",
						success: function(resData) {
							$('#com').empty();
							makeComment(resData);
						}
					});
					check = true;
				});
			});
			
			//게시판 목록 그리는 함수
			function makeComment(result) {
				var html = "";
				$.each(result, function(index, obj) {
					html += "<blockquote>" + obj.commentName + " " + obj.commentDate;
					html += " <a href='javascript:void(0);' data-value='" + obj.commentNum + "' class='updateComment' ><i class='ri-pencil-line'></i></a>";
					html += " <a href='javascript:void(0);' data-value='" + obj.commentNum + "' class='deleteComment'><i class='ri-delete-bin-line'></i></a><br>";
					html += "<code>" + obj.commentContent + "</code></blockquote>";
				});
				$('#com').append(html);
			}
			
		</script>
</body>
</html>