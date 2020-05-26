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
                     ${requestScope.boardAndBoardName[0]}
                  </header>
                  <section class="wrapper style5">
                     <div class="inner">
                        <c:set var="board" value="${requestScope.boardAndBoardName[1]}"/>
                        <h2>${board.title}</h2>
                        <blockquote>
                           <br>
                           ${board.writeDate}  조회수 : ${board.views}<br>
                           
                        </blockquote> 
                        <pre><code>${board.content}</code></pre>
                        
                        <a href="boardInfo.free?no=${board.no}" class="button small">Edit</a>
                        <a href="deleteBoard.free?no=${board.no}" class="button small">Delete</a>
                        <a href="#" class="button small">Reply</a>
                        <hr>
                        
                        <!-- 댓글 -->
                        <div id="com">
                           <blockquote>hyerin 20.20.20
                              <a href='javascript:void(0);' data-value='' class='updateComment' ><i class='ri-pencil-line'></i></a>
                              <a href='javascript:void(0);' data-value='' class='deleteComment'><i class='ri-delete-bin-line'></i></a><br>
                              <code>코맨트ㅇ비니다</code>
                           </blockquote>
                        </div>
                        
                        <b></b>
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
   </body>
</html>