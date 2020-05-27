<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>DIARY</title>
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
                     <h2>DIARY</h2>
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
								<img id="image" width="500px"/>
								<br>
							</c:if>
							${myBoard.diaryContent}
                        </pre>
                        <a href="myBoardUpdateInfo.my?diaryNo=${myBoard.diaryNo}" class="button small">Edit</a>
                        <a href="myBoardDelete.my?diaryNo=${myBoard.diaryNo}" class="button small">Delete</a>
                        <a href="myBoardReInsertForm.my?diaryNo=${myBoard.diaryNo}" class="button small">Reply</a>
                        <hr>
                        
                        <!-- 댓글 -->
                        <div id="com">
                           <blockquote>
                              <a href='javascript:void(0);' data-value='' class='updateComment'><i class='ri-pencil-line'></i></a>
                              <a href='javascript:void(0);' data-value='' class='deleteComment'><i class='ri-delete-bin-line'></i></a><br>
                              <!-- <pre><code> 코멘트 </code></pre> -->
                           </blockquote>
                        </div>
                        
                        <b></b>
                        <textarea name="comment" id="comment" placeholder="Enter your comment" rows="3"></textarea>
                        <br>
                        <button class="button primary small" id="commWrite">Write</button>
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

   </body>
</html>