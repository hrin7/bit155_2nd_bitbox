<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>MY BOARD</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script type="text/javascript">
		$(function() {
			if("${requestScope.msg}" != "") {
				alert("${requestScope.msg}");
			}
			//console.log("${requestScope.msg}")
			
			//사진
			$('#image').attr('src', 'upload/${myBoard.diaryFileName}');
			/* $('#hiddenImg').val('${board.boardFile}'); */
		});
		</script>
   
	</head>
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
                    	
                     	<!-- 잘 넘어 왔나 찍어보기 -->
                     	<!-- ${requestScope.myBoard} -->
                     	<c:set var="myBoard" value="${requestScope.myBoard}" />                        
                        <blockquote>
                        	<h3>${myBoard.diaryTitle}</h3><br>
                           	${myBoard.diaryDate}<br>
                           	<c:if test="${!empty myBoard.diaryFileName}">
								첨부파일 <a href='upload/${myBoard.diaryFileName}' download>${myBoard.diaryFileName}</a>
							</c:if>
                        </blockquote>
                        <pre>
							<c:if test="${!empty myBoard.diaryFileName}">
								<p align="left"><img id="image"/></p>
							</c:if>
							${myBoard.diaryContent}
                        </pre>
                        <hr>
                        <a href="myBoardUpdateInfo.my?diaryNo=${myBoard.diaryNo}" class="button small">Edit</a>
                        <a href="myBoardDelete.my?diaryNo=${myBoard.diaryNo}" class="button small">Delete</a>
                        <a href="myBoardReInsertForm.my?diaryNo=${myBoard.diaryNo}" class="button primary small">Reply</a>                       
                        <br><br><br>
                        
                        <!-- 댓글 -->
                        <div id="com"></div>
                        <textarea name="comment" id="comment" placeholder="Comment" rows="3"></textarea>
                        <br>
                        <p align="right"><button class="button small" id="commentInsert"><i class="ri-check-line"></i>OK</button></p>
                        <br><br>
                        <a href="<%=request.getContextPath()%>/myBoardList.my" id="">LIST</a>

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
      
		<script type="text/javascript">
		
		//댓글 목록
		$.ajax({
			url: "SelectMyBoardCommentList.ajax",
			data: {diaryNo:'${myBoard.diaryNo}'},
			dataType: "json",
			success: function(resData) {
				makeComment(resData);
			}
		});
		
		//댓글 등록
		$('#commentInsert').click(function() {
			if($('#comment').val() == "") {
				alert('댓글을 입력하세요');
				return false;
			}
			
			$.ajax({
				url: "InsertMyBoardComment.ajax",
				data: {
					diaryNo:'${myBoard.diaryNo}',
					diaryCommentContent:$('#comment').val()
				},
				dataType: "json",
				success: function(resData) {
					$('#com').empty();
					makeComment(resData);
					$('#comment').val("");
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
				html += '<input type="text" value="'+ codeText +'" name="diaryCommentContent" id="updateContent">';
				html += '<button class="button special small alt" id="commUpdateBtn">Edit</button>';
				html += '</div>';
				parentTag.append(html);
				parentTag.find('input').focus();				
				
				var diaryCommentNo = $(this).data("value");
				$('#commUpdateBtn').click(function() {
					if($('#updateContent').val() == "") {
						alert('댓글을 입력하세요');
						return false;
					}
					
					$.ajax({
						url: "UpdateMyBoardComment.ajax",
						data: {
							diaryNo:'${myBoard.diaryNo}',
							diaryCommentNo: diaryCommentNo,
							diaryCommentContent: $('#updateContent').val()
						},
						dataType: "json",
						success: function(resData) {
							$('#com').empty();
							makeComment(resData);
							check = true;
						}
					});
				});
			}
			
			//수정 눌렀을 경우 리스트 다시 불러오기
			$('#com').on('click', '.cancelUpdate', function() {
				$.ajax({
					url: "SelectMyBoardCommentList.ajax",
					data: {diaryNo:'${myBoard.diaryNo}'},
					dataType: "json",
					success: function(resData) {
						$('#com').empty();
						makeComment(resData);
					}
				});
				check = true;
			});
		});
		
		//댓글 삭제
		$('#com').on('click', '.deleteComment', function() {
			$.ajax({
				url: "DeleteMyBoardComment.ajax",
				data: {
					diaryNo:'${myBoard.diaryNo}',
					diaryCommentNo: $(this).data("value")
				},
				dataType: "json",
				success: function(resData) {
					$('#com').empty();
					makeComment(resData);
				}
			});
		});
		
		//게시판 목록 그리는 함수
		function makeComment(result) {
			var html = "";
			$.each(result, function(index, obj) {
				html += "<blockquote>" + obj.diaryCommentDate + " ";
				html += "<a href='javascript:void(0);' data-value='" + obj.diaryCommentNo + "' class='updateComment'><i class='ri-pencil-line'></i></a>";
				html += "<a href='javascript:void(0);' data-value='" + obj.diaryCommentNo + "' class='deleteComment'><i class='ri-delete-bin-line'></i></a><br>";
				html += "<code>" + obj.diaryCommentContent + "</code></blockquote>";
			});
			$('#com').append(html);
		};
		
		</script>

   </body>
   
</html>
