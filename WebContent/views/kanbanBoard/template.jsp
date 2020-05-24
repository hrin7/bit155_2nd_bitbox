<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>BitBox - Memo</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../../assets/css/main.css" />
		<link rel="stylesheet" href="../../assets/css/modal.css">
		<link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
		<style type="text/css">
			#createListBtn {
				width: 300px;
				height: 50px;
				padding: 10px;
				background-color: lightgray;
				border-radius: 5px;
				cursor:pointer;
				float: left;
				margin: 10px;
			}
			#createListBtn:hover {
				background-color: silver;
			}
			.memoSectionDiv {
				margin: 10px;
				float: left;
				width: 300px;
				padding: 5px;
				background-color: #f6f8fc;
				border-radius: 5px;
				color: #707090;
				align-content: center;
			}
			.memoTopHr {
				width: 290px;
				height: 5px;
				background-color: #c781ff;
				border-radius: 5px;
			}
			.memoTitle {
				width: 270px;
				margin: 5px 10px;
				padding: 5px;
			}
			.createMemoContentBtn {
				width: 270px;
				margin: 10px;
				padding: 5px;
				border-radius: 5px;
				cursor:pointer;
				font-size: 15px;
			}
			.memoContent {
				width: 270px;
				margin: 10px;
				padding: 5px;
				background-color: white;
				border-radius: 5px;
				cursor:pointer;
			}
			.createMemoContentBtn:hover, .memoContent:hover {
				background-color: lightgray;
			}
			.shadow {
				box-shadow: 0px 0px 5px lightgray;
			}
		</style>
	</head>
	<body class="is-preload">

		<!-- Page Wrapper -->
		<div id="page-wrapper">

			<!-- Header -->
			<jsp:include page="/common/header.jsp"></jsp:include>

				<!-- Main -->
					<article id="main">
						<header>
							<h2>Generic Page</h2>
							<p>Aliquam ut ex ut interdum donec amet imperdiet eleifend</p>
						</header>
						<section class="wrapper style5">
							<div class="inner">
								
								<div id="outer">
									<div id="createListBtnBefore"><div id="createListBtn">+ Add a list</div></div>
								</div>

							</div>
						</section>
					</article>

				<!-- Footer -->
				<jsp:include page="/common/footer.jsp"></jsp:include>

			</div>
			
			
			<!-- The Modal -->
			<div class="modal fade" id="myModal">
			  <div class="modal-dialog modal-lg">
			    <div class="modal-content">
			    
			      <!-- Modal Header -->
			      <div class="modal-header">
			        <h4 class="modal-title">Modal Heading</h4>
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			      </div>
			      
			      <!-- Modal body -->
			      <div class="modal-body">
			        Modal body..
			      </div>
			      
			      <!-- Modal footer -->
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			      </div>
			      
			    </div>
			  </div>
			</div>
			
		<!-- Scripts -->
		<script src="../../assets/js/jquery.min.js"></script>
		<script src="../../assets/js/jquery.scrollex.min.js"></script>
		<script src="../../assets/js/jquery.scrolly.min.js"></script>
		<script src="../../assets/js/browser.min.js"></script>
		<script src="../../assets/js/breakpoints.min.js"></script>
		<script src="../../assets/js/util.js"></script>
		<script src="../../assets/js/main.js"></script>
		<script type="text/javascript">
			////////////////////////////////////////////
			var memoSectionDivHtml = "";
			memoSectionDivHtml += '<div class="memoSectionDiv shadow">';
			memoSectionDivHtml += 	'<div class="memoTopHr"></div>';
			memoSectionDivHtml += 	'<div class="memoTitle">';
			memoSectionDivHtml += 		'<input type="text" placeholder="Enter list title"/>';
			memoSectionDivHtml += 		'<button class="button primary small addListBtn">Add List</button>';
			memoSectionDivHtml += 		'<a href="javascript:void(0);" class="listCancelBtn"><i class="ri-close-fill ri-xl"></i></a>';
			memoSectionDivHtml += 	'</div>';
			memoSectionDivHtml += '</div>';
				
			$('#outer').on('click', '.addListBtn', function() {
				let titleText = $(this).prev().val();
				//버튼의 형제들 삭제
				$(this).siblings().remove();
				//부모밑에 text를 append
				$(this).parent().append(titleText);
				$(this).parent().parent().append('<div id="createMemoContentBtnBefore"><div class="createMemoContentBtn">+ Add a card</div></div>');
				//그리고 버튼은 마지막에 삭제 >> 순서를 이렇게 해야 append가 된다..
				$(this).remove();
				$('#createListBtn').show();
			});
			
			$('#outer').on('click', '.listCancelBtn', function() {
				$(this).parent().parent().hide();
				$('#createListBtn').show();
			});
			
			$('#createListBtn').click(function() {
				$(this).hide();
				$('#createListBtnBefore').before(memoSectionDivHtml);
			});
			/////////////////////////////////////////////
			
			var memoContentHtml = "";
			memoContentHtml += "<div class='addCardOuter'>";
			memoContentHtml +=     "<input type='text' placeholder='Enter a title for a this card...' class='shadow'/>";
			memoContentHtml +=     '<button class="button primary small addCardBtn">Add Card</button>';
			memoContentHtml +=     '<a href="javascript:void(0);" class="cardCancelBtn"><i class="ri-close-fill ri-xl"></i></a>';
			memoContentHtml += "</div>";
			
			var createMemoContentBtn = ""; 
			$('#outer').on('click', '.createMemoContentBtn', function() {
				//다른 열려있는 card만들기 창 닫아주기
				$('.cardCancelBtn').each(function(index, ele) {
					$(this).parent().hide();
					createMemoContentBtn.show();
				});
				createMemoContentBtn = $(this);
				createMemoContentBtn.hide();
				createMemoContentBtn.parent().before(memoContentHtml);
			});
			
			$('#outer').on('click', '.cardCancelBtn', function() {
				console.log($(this));
				$(this).parent().hide();
				createMemoContentBtn.show();
			});
			
			$('#outer').on('click', '.addCardBtn', function() {
				let titleText = "<div class='memoContent shadow' data-toggle='modal' data-target='#myModal'>"+$(this).prev().val()+"</div>";
				//버튼의 형제들 삭제
				$(this).siblings().remove();
				//부모밑에 text를 append
				$(this).parent().append(titleText);
				//그리고 버튼은 마지막에 삭제 >> 순서를 이렇게 해야 append가 된다..
				$(this).remove();
				createMemoContentBtn.show();
			});
		</script>

	</body>
</html>