<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
   <head>
      <title>BITBOX - 게시판상세보기</title>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
      <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
      <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
      <noscript><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/noscript.css" /></noscript>
   </head>
   <body class="is-preload">

      <!-- Page Wrapper -->
      <div id="page-wrapper">

         <!-- Header -->
         <jsp:include page="/common/header.jsp"></jsp:include>
			
            <!-- Main -->
               <article id="main">
                  <header>
                     <h2>Free Board Detail</h2>
                     <p>This is Free Board Detail</p>
                     
                  </header>
                  <section class="wrapper style5">
                     <div class="inner">
                        <c:set var="board" value="${requestScope.boardAndBoardName}"/>
                        <blockquote>
	                        <h2>${board.title}</h2>
                        	<b>${board.id}</b>  ${board.writeDate}<br>
                        	readCount ${board.views} in board ${board.boardName}<br>
                        	<c:if test="${!empty board.fileName}">
								첨부파일   <a href='upload/${board.fileName}' download><b>${board.fileName}</b></a>
							</c:if>
                        </blockquote> 
                        <pre><code>${board.content}</code></pre>
                        
                        <a href="boardInfo.free?no=${board.no}&boardCode=${board.boardCode}" class="button small" id="editBtn">Edit</a>
                        <a href="deleteBoard.free?no=${board.no}" class="button small" id="deleteBtn">Delete</a>
                        <a href="<%=request.getContextPath()%>/insertReFreeBoardForm.free?no=${board.no}" class="button small" id="replyBtn">Reply</a>
                        <hr>
                        
                        <!-- 댓글 -->
                        <div id="com"></div>
                        
                        <b>${sessionScope.userID}</b>
                        <textarea name="comment" id="comment" placeholder="Enter your comment" rows="3"></textarea>
                        <br>
                        <button class="button primary small" id="commWrite">Write</button>
                        <br><br>
                        <a href="<%=request.getContextPath()%>/selectBoardList.free" id="goList">목록으로</a>

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
      
      	//댓글 목록
      	$.ajax({
      		url: "SelectFreeCommentList.ajax",
      		data: {no:'${board.no}'},
      		dataType: "json",
      		success: function(resData){
      			makeComment(resData);
      		}
      	});
      	
      	//댓글 등록
      	$('#commWrite').click(function(){
      		console.log('${board.no}');
      		if($('#comment').val() == ""){
      			alert('내용을 입력하세요');
      			return false;
      		}
      		
      		$.ajax({
      			url: "InsertFreeComment.ajax",
      			data: {
      				no:'${board.no}',
      				id:'${requestScope.id}',
      				commentContent:$('#comment').val()
      			},
      			dataType: "json",
      			success: function(resData){
      				$('#com').empty();
      				makeComment(resData);
      				$('#comment').val("");
      			}
      		});
      	});
      	
      	//댓글삭제
      	$('#com').on('click', '.deleteComment', function(){
      		$.ajax({
      			url: "DeleteFreeComment.ajax",
      			data: {
      				no: '${board.no}',
      				commentNo: $(this).data("value")
      			},
      			dataType: "json",
      			success: function(resData){
      				$('#com').empty();
      				makeComment(resData);
      			}
      		});
      	});
      	
      	//댓글 수정
		var check = true;
      	$('#com').on('click', '.updateComment', function(){
      		if(check){
      			check = false;
      			//클리학 a태그의 class를 cancelUpdate로 바꾸고 아이콘 바꾸기
      			$(this).attr('class', 'cancelUpdate');
      			$(this).html("<i class='ri-close-line'></i>");
      			
      			//this(a태그)의 부모태그(blockquote태그)를 parentTag변수에 담기
      			var parentTag = $(this).parent();
      			//자식태그중 code(댓글내용이 들어있는 태그)를 찾아서 변수에 담기
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
				
				var commentNo = $(this).data("value");
				$('#commUpdateBtn').click(function(){
					if($('#updateContent').val() == ""){
						alert('내용을 입력하세요');
						return false;
					}
					
					$.ajax({
						url: "UpdateFreeComment.ajax",
						data: {
							no: '${board.no}',
							commentNo: commentNo,
							commentContent: $('#updateContent').val()
						},
						dataType: "json",
						success: function(resData){
							$('#com').empty();
							makeComment(resData);
							check = true;
						}
					})
				});
      		}
      		
      		//수정 취소 눌렀을 경우 리스트 다시 불러오기
      		$('#com').on('click', '.cancelUpdate', function(){
      			$.ajax({
      				url: "SelectFreeCommentList.ajax",
      				data: {no:'${board.no}'},
      				dataType: "json",
      				success: function(resData){
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
				html += "<blockquote>" + obj.id + " " + obj.commentDate;
				html += " <a href='javascript:void(0);' data-value='" + obj.commentNo + "' class='updateComment' ><i class='ri-pencil-line'></i></a>";
				html += " <a href='javascript:void(0);' data-value='" + obj.commentNo + "' class='deleteComment'><i class='ri-delete-bin-line'></i></a><br>";
				html += "<code>" + obj.commentContent + "</code></blockquote>";
			});
			$('#com').append(html);
		}
      	
      	
		//로그인안하면 접근불가
		if('${sessionScope.userID}' == "") {
			$('#editBtn').hide();
			$('#deleteBtn').hide();
			$('#replyBtn').hide();
			$('#comment').hide();
			$('#commWrite').hide();
		} else {
			//로그인 한 사용자랑 글쓴이와 같지 않으면
			if('${sessionScope.userID}' != '${requestScope.boardAndBoardName.id}'){
				$('#editBtn').hide(); //수정버튼 숨기기
				$('#deleteBtn').hide(); //삭제버튼 숨기기
			//로그인 한 사용자랑 글쓴이와 같으면
			} else if('${sessionScope.userID}' == '${requestScope.boardAndBoardName.id}'){
				$('#editBtn').show();
				$('#deleteBtn').show();
			}
		}
		

		
      	
      </script>
   </body>
</html>