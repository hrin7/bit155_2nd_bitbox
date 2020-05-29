////////////////////////////////////////////
var memoSectionDivHtml = "";
memoSectionDivHtml += '<div class="memoSectionDiv shadow">';
memoSectionDivHtml += 	'<div class="memoTopHr"></div>';
memoSectionDivHtml += 	'<div class="memoTitle">';
memoSectionDivHtml += 		'<input type="text" placeholder="Enter list title"/>';
memoSectionDivHtml += 	'</div>';
memoSectionDivHtml += 	'<div class="memoTitle-sub">';
memoSectionDivHtml += 		'<button class="button primary small addListBtn">Add List</button>';
memoSectionDivHtml += 		'<a href="javascript:void(0);" class="listCancelBtn"><i class="ri-close-fill ri-xl"></i></a>';
memoSectionDivHtml += 	'</div>';
memoSectionDivHtml += 	'<div class="deleteListDiv"><a href="javascript:void(0);" class="deleteListBtn"><i class="ri-close-fill"></i></a></div>';
memoSectionDivHtml += '</div>';

//리스트 만들기
$(document).on('click', '#createListBtn', function() {
	$(this).hide();
	$('#createListBtnBefore').before(memoSectionDivHtml);
	$('#createListBtnBefore').prev().find('.deleteListDiv').hide();
	$('input').focus();
});

//리스트 이름 입력받은 후 만들기 버튼눌렀을 때
$('#outer').on('click', '.addListBtn', function() {
	var deleteListBtnTag = $(this).parent().next().find('a');
	let listName = $(this).parent().prev().find('input').val();
	if(listName == "") {
		alert('list title을 입력하세요.');
		$(this).parent().prev().find('input').focus();
		return;
	}
	$('input').blur();
	
	$('.deleteListDiv').show();
	//인풋박스 삭제
	$(this).parent().prev().find('input').remove();
	//부모밑에 text를 append
	$(this).parent().prev().append(listName);
	$(this).parent().parent().attr('data-title', listName);
	$(this).parent().parent().append('<div id="createMemoContentBtnBefore"><div class="createMemoContentBtn">+ Add a card</div></div>');
	//그리고 버튼은 마지막에 삭제 >> 순서를 이렇게 해야 append가 된다..
	$(this).parent().remove();
	$(this).remove();
	$('#createListBtn').show();
	
	//생성된 리스트(그룹) DB에 insert하기
	$.ajax({
		url: "InsertKanbanGroup.ajax",
		data: {listName: listName},
		dataType: "html",
		success: function(resData) {
			console.log("list insert 완료");
			deleteListBtnTag.attr('data-code', resData);
		}
	});
});

//리스트 만들기 캔슬
$('#outer').on('click', '.listCancelBtn', function() {
	$(this).parent().parent().hide();
	$('#createListBtn').show();
});

//리스트 이름 바꾸기
$('#outer').on('click', '.memoTitle', function() {
	let memoTitle = $(this);
	let listName = $(this).text();
	$(this).next().hide();
	$(this).before("<input type='text' value='"+listName+"' id='listNameInput'/>");
	$('#listNameInput').focus();
	$(this).hide();
	
	$('#listNameInput').blur(function() {
		if($(this).val() == "") {
			alert('list Name을 입력하세요');
			$(this).focus();
			return;
		}
		
		$('.deleteListDiv').show();
		memoTitle.show();
		memoTitle.text($(this).val());
		$(this).remove();
		
		if(listName != $(this).val()) {
			$.ajax({
				url: "UpdateKanbanListName.ajax",
				data: {
					oriListName: listName,
					updateListName: $(this).val()
				},
				success: function() {
					$('#listNameInput').blur();
				}
			});
		}
	});
});

//리스트 삭제하기
$('#outer').on('click', '.deleteListBtn', function() {
	swal({
		title: "list를 정말 삭제하시겠습니까?",
		text: "Once deleted, you will not be able to recover this list!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
	.then((willDelete) => {
	    if(willDelete) {
			$.ajax({
				url: "DeleteKanbanList.ajax",
				traditional : true,
				data: {
					kanbanCode: $(this).data('code')
				},
				success: function() {
					$.ajax({
						url: "SelectKanban.ajax",
						dataType: "json",
						success: function(resData) {
							$('#outer').empty();
							makeKanbanList(resData);
							swal("Poof! Your imaginary list has been deleted!", {
								icon: "success",
							});
						}
					});
				}
			});
		} else {
		    swal("Your list is safe!");
		}
	});
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
	$('input').focus();
});

$('#outer').on('click', '.cardCancelBtn', function() {
	console.log($(this));
	$(this).parent().hide();
	createMemoContentBtn.show();
});

$('#outer').on('click', '.addCardBtn', function() {
	let listName = $(this).parent().parent().data('title');
	let cardTitle = $(this).prev().val();
	if(cardTitle == "") {
		alert('card title을 입력하세요.');
		$(this).prev().focus();
		return;
	}
	
	let siblings = $(this).siblings();
	let parent = $(this).parent();
	let thisthis = $(this);
	
	//생성된 card 이름을 DB에 insert하기
	$.ajax({
		url: "InsertKanbanCardName.ajax",
		dataType: "html",
		data: {
			cardTitle: cardTitle,
			listName: listName
		},
		success: function(resData) {
			console.log(resData);
			var titleText = "";
			titleText += "<div class='memoContent shadow' data-toggle='modal' data-target='#myModal' data-value='"+resData+"'>" + cardTitle + "<br>";
			titleText += "</div>";
			//버튼의 형제들 삭제
			siblings.remove();
			//부모밑에 text를 append
			parent.append(titleText);
			//그리고 버튼은 마지막에 삭제 >> 순서를 이렇게 해야 append가 된다..
			thisthis.remove();
			createMemoContentBtn.show();
		}
	});
});


////////////////////////////////////////////////////////////////////////////


//목록 그리는 함수
function makeKanbanList(resData) {
	let html = "";
	$.each(resData[0].kanbanGroupList, function(index, obj) {
		html += '<div class="memoSectionDiv shadow" data-title="'+obj.listName+'">';
		html += 	'<div class="memoTopHr"></div>';
		html += 	'<div class="memoTitle">'+obj.listName+'</div>';
		html += 	'<div class="deleteListDiv"><a href="javascript:void(0);"  data-code="'+obj.kanbanCode+'" class="deleteListBtn" style="text-decoration: none !important;"><i class="ri-close-fill"></i></a></div>';
		$.each(resData[0].kanbanList, function(index, obj2) {
			if(obj.kanbanCode == obj2.kanbanCode) {
				if(obj2.kanbanTitle != "") {
					html += "<div class='memoContent shadow' data-toggle='modal' data-target='#myModal' data-value='"+obj2.kanbanNo+"' data-title='"+obj2.listName+"'>";
					html += 	obj2.kanbanTitle+'<br>';
					if(obj2.kanbanContent != "") {
						html += '<i class="ri-align-left"></i>';
					}
					if(obj2.kanbanCommentCount != 0) {
						html += '<i class="ri-chat-3-line"></i>'+obj2.kanbanCommentCount;
					}
					if(obj2.kanbanFileCount != 0) {
						html += '<i class="ri-chat-3-line"></i>'+obj2.kanbanFileCount;
					}
					html += '</div>';
				}
			}
		});
		html += 	'<div id="createMemoContentBtnBefore">';
		html += 		'<div class="createMemoContentBtn">+ Add a card</div>';
		html += 	'</div>';
		html += '</div>';
	});
	html += '<div id="createListBtnBefore"><div id="createListBtn">+ Add a list</div></div>';
	$('#outer').append(html);
}

//모달 닫을때마다 리스트 만들기
$('#myModal').on('hide.bs.modal', function(event){
	//전체 리스트
	$.ajax({
		url: "SelectKanban.ajax",
		dataType: "json",
		success: function(resData) {
			$('#outer').empty();
			makeKanbanList(resData);
		}
	});
//	location.reload();
});

//상세글 보기
var kanbanNo = "";
$('#outer').on('click', '.memoContent', function() {
	let title = $(this).data("title");
	kanbanNo = $(this).data("value");
	
	$.ajax({
		url: "SelectKanbanByNo.ajax",
		data: {kanbanNo: kanbanNo},
		dataType: "json",
		success: function(resData) {
			//console.log(resData);
			$('#listName').text('in list ' + title);
			$('#cardName').text(resData.kanbanTitle);
			$('#kanbanDate').text(resData.kanbanDate);
			$('#kanbanNo').val(resData.kanbanNo);
			//내용이 없으면 textarea 뿌려주기
			if(resData.kanbanContent == "") {
				let html = "";
				html += "<textarea rows='3' placeholder='Add a more detailed description...' style='resize: none;' id='textarea'></textarea>";
				$('#kanbanContent').html(html);
				$('#editBtn').hide();
			} else {
				$('#kanbanContent').text(resData.kanbanContent);
				$('#editBtn').show();
			}
		}
	});
})

//상세보기에서 카드이름 update
$('#cardName').click(function() {
	let cardName = $(this).text();
	$(this).parent().prepend("<input type='text' value='"+cardName+"' id='cardNameInput'/>");
	$("#cardNameInput").focus();
	$('#cardName').hide();
	
	//카드이름 update
	$("#cardNameInput").blur(function() {
		let updateCardName = $(this).val();
		if(updateCardName == "") {
			alert("card title을 입력하세요");
			return;
		}
		$.ajax({
			url: "UpdateKanbanCardName.ajax",
			data: {
				cardTitle: updateCardName,
				kanbanNo: kanbanNo
			},
			success: function() {
				$('#cardName').text(updateCardName);
				$('#cardName').show();
				$("#cardNameInput").remove();
			}
		});
	});
});

//모달창이 떴을 때
$('#myModal').on('shown.bs.modal', function () {
	//상세보기에서 카드내용 클릭해서 update
	$('#kanbanContent').click(function() {
		let cardContentText = $(this).text();
		updateContentLogic(cardContentText, $(this));
	});
	
	//상세보기에서 Edit버튼 클릭해서 update
	$('#editBtn').click(function() {
		let cardContentText = $(this).siblings('#kanbanContent').text();
		updateContentLogic(cardContentText, $(this));
	});
	
	//카드 삭제하기
	$('#deleteCardBtn').click(function() {
		swal({
			title: "card를 정말 삭제하시겠습니까?",
			text: "Once deleted, you will not be able to recover this card!",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
		.then((willDelete) => {
		    if(willDelete) {
				$.ajax({
					url: "DeleteKanbanCard.ajax",
					traditional : true,
					data: {kanbanNo: $('#kanbanNo').val()},
					success: function() {
						swal("Poof! Your imaginary card has been deleted!", {
							icon: "success",
						});
						$('#myModal').modal('hide');
					}
				});
			} else {
			    swal("Your card is safe!");
			}
		});
	});
	
	/////////////////// 댓글 //////////////////////
	setTimeout(function() {
		console.log("글번호: "+$('#kanbanNo').val());
		//댓글 목록
		$.ajax({
			url: "SelectKanbanCommentList.ajax",
			data: {kanbanNo: $('#kanbanNo').val()},
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
				url: "InsertKanbanComment.ajax",
				data: {
					kanbanNo: $('#kanbanNo').val(),
					content: $('#comment').val()
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
			console.log($(this).data("value"));
			$.ajax({
				url: "DeleteKanbanComment.ajax",
				data: {
					kanbanNo: $('#kanbanNo').val(),
					kanbanCommentNo: $(this).data("value")
				},
				dataType: "json",
				success: function(resData) {
					$('#com').empty();
					makeComment(resData);
				}
			});
		});
	
		//댓글 수정
		$('#com').on('click', '.updateComment', function() {
			//this(a태그)의 부모태그(blockquote태그)를 parentTag변수에 담기
			var parentTag = $(this).parent();
			//자식태그 중 code(댓글내용이 들어있는 태그)를 찾아서 변수에 담기
			var code = parentTag.find('code');
			//댓글내용 변수에 담기
			var codeText = code.text();
			
			//다른 열려있는 창 닫아주기
			$('.cancelUpdate').each(function(index, ele) {
				$(this).siblings('#updateDiv').hide();
				$(this).parent().find('code').show();
				$(this).parent().attr('class', 'updateComment');
				$(this).html("<i class='ri-pencil-line'></i>");
			});
			
			//클릭한 a태그의 class를 cancelUpdate로 바꾸고 아이콘 바꾸기
			$(this).attr('class', 'cancelUpdate');
			$(this).html("<i class='ri-close-line'></i>");
			
			
			//댓글내용이 있는 code태그 숨기기
			code.hide();
			
			//input태그 append하기(value에는 기존의 값 셋팅하고, 포커스주기)
			var html = "";
			html += '<div id="updateDiv">';
			html += '<input type="text" value="'+codeText+'" name="content" id="updateContent">';
			html += '<button class="button special small alt" id="commUpdateBtn">Edit</button>';
			html += '</div>';
			parentTag.append(html);
			parentTag.find('input').focus();
			
			var commentNo = $(this).data("value");
			$('#commUpdateBtn').click(function() {
				if($('#updateContent').val() == "") {
					alert('내용을 입력하세요');
					return false;
				}
				
				$.ajax({
					url: "UpdateKanbanComment.ajax",
					data: {
						kanbanNo: $('#kanbanNo').val(),
						kanbanCommentNo: commentNo,
						content: $('#updateContent').val()
					},
					dataType: "json",
					success: function(resData) {
						$('#com').empty();
						makeComment(resData);
					}
				});
			});
			
			//수정 취소 눌렀을 경우 리스트 다시 불러오기
			$('#com').on('click', '.cancelUpdate', function() {
				$.ajax({
					url: "SelectKanbanCommentList.ajax",
					data: {kanbanNo: $('#kanbanNo').val()},
					dataType: "json",
					success: function(resData) {
						$('#com').empty();
						makeComment(resData);
					}
				});
			});
		});
	
		//댓글 목록 그리는 함수
		function makeComment(result) {
			var html = "";
			$.each(result, function(index, obj) {
				html += "<blockquote>" + obj.kanbanCommentDate + "<br>";
				html += "<code>" + obj.kanbanCommentContent + "</code>";
				html += " <a href='javascript:void(0);' data-value='" + obj.kanbanCommentNo + "' class='updateComment' ><i class='ri-pencil-line'></i></a>";
				html += " <a href='javascript:void(0);' data-value='" + obj.kanbanCommentNo + "' class='deleteComment'><i class='ri-delete-bin-line'></i></a><br>";
				html += "</blockquote>";
			});
			$('#com').append(html);
		}
	}, 100);
	/////////////////////////////////////////////////////
});

//내용 update하는 로직함수
function updateContentLogic(cardContentText, obj) {
	//기존에 내용이 있으면
	if(cardContentText != "") {
		obj.after("<textarea placeholder='Add a more detailed description...' style='resize: none;' id='textarea'>"+cardContentText+"</textarea>");
		//setTimeout(function(){
			$("#textarea").focus();
			$('#kanbanContent').hide();
		//}, 100);
		
		//textarea에서 나왔을 때 카드내용 update
		$('#textarea').blur(function() {
			let updateCardContent = $(this).val();
			//기존의 내용과 다르면 ajax 호출
			if(updateCardContent != cardContentText) {
				updateContentAjax(updateCardContent);
			} else {
				$('#kanbanContent').show();
				$('#kanbanContent').text(updateCardContent);
				$('#editBtn').show();
				$("#textarea").remove();
			}
		});
	//기존에 내용이 없으면
	} else {
		//textarea에서 나왔을 때 카드내용 update
		$('#textarea').blur(function() {
			let updateCardContent = $(this).val();
			if(updateCardContent != "") {
				updateContentAjax(updateCardContent);
			}
		});
	}
}

//내용 update하는 에이작스
function updateContentAjax(updateCardContent) {
	console.log("에이젝스호출");
	$.ajax({
		url: "UpdateKanbanCardContent.ajax",
		type: "post",
		async : false,
		data: {
			kanbanContent: updateCardContent,
			kanbanNo: kanbanNo
		},
		success: function() {
			console.log("내용업뎃완료");
			if(updateCardContent == "") {
				$('#editBtn').hide();
				return;
			} else {
				$('#kanbanContent').show();
				$('#kanbanContent').text(updateCardContent);
				$('#editBtn').show();
				$("#textarea").remove();
			}
		}
	});
}

/////////////////////////////////////////////////////////////

