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

//리스트 만들기
$('#createListBtn').click(function() {
	$(this).hide();
	$('#createListBtnBefore').before(memoSectionDivHtml);
	$('input').focus();
});

//리스트 이름 입력받은 후 만들기 버튼눌렀을 때
$('#outer').on('click', '.addListBtn', function() {
	let listName = $(this).prev().val();
	if(listName == "") {
		alert('list title을 입력하세요.');
		$(this).prev().focus();
		return;
	}
	$('input').blur();
	
	//버튼의 형제들 삭제
	$(this).siblings().remove();
	//부모밑에 text를 append
	$(this).parent().append(listName);
	$(this).parent().parent().append('<div id="createMemoContentBtnBefore"><div class="createMemoContentBtn">+ Add a card</div></div>');
	//그리고 버튼은 마지막에 삭제 >> 순서를 이렇게 해야 append가 된다..
	$(this).remove();
	$('#createListBtn').show();
	
	//생성된 리스트(그룹) DB에 insert하기
	$.ajax({
		url: "InsertKanbanGroup.ajax",
		data: {listName: listName},
		success: function() {
		}
	});
});

//리스트 만들기 캔슬
$('#outer').on('click', '.listCancelBtn', function() {
	$(this).parent().parent().hide();
	$('#createListBtn').show();
});

//리스트 이름 바꾸기
//$('#outer').on('click', '.memoTitle', function() {
//	let memoTitle = $(this);
//	let listName = $(this).text();
//	$(this).before("<input type='text' value='"+listName+"' id='listNameInput'/>");
//	$('#listNameInput').focus();
//	$(this).hide();
//	
//	$('#listNameInput').blur(function() {
//		if($(this).val() == "") {
//			alert('list Name을 입력하세요');
//			$(this).focus();
//			return;
//		}
//		
//		memoTitle.show();
//		memoTitle.text($(this).val());
//		$(this).remove();
//		
//		if(listName != $(this).val()) {
//			$.ajax({
//				url: "UpdateKanbanListName.ajax",
//				data: {
//					oriListName: listName,
//					updateListName: $(this).val()
//				},
//				success: function() {
//					$('#listNameInput').blur();
//				}
//			});
//		}
//	});
//});
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
//function makeKanbanList(resData) {
//	let html = "";
//	$.each(resData[0].allKanbanList, function(index, obj) {
//		html += '<div class="memoSectionDiv shadow" data-title="'+resData[0].kanbanGroupList[index].listName+'">';
//		html += 	'<div class="memoTopHr"></div>';
//		html += 	'<div class="memoTitle">'+resData[0].kanbanGroupList[index].listName+'</div>';
//		$.each(obj, function(index2, obj2) {
//			html += "<div class='memoContent shadow' data-toggle='modal' data-target='#myModal' data-value='"+obj2.kanbanNo+"' data-title='"+resData[0].kanbanGroupList[index].listName+"'>";
//			html += 	obj2.kanbanTitle+'<br>';
//			if(obj2.kanbanContent != "") {
//				html += '<i class="ri-align-left"></i>';
//			}
//			if(obj2.kanbanCommentCount != 0) {
//				html += '<i class="ri-chat-3-line"></i>'+obj2.kanbanCommentCount;
//			}
//			if(obj2.kanbanFileCount != 0) {
//				html += '<i class="ri-chat-3-line"></i>'+obj2.kanbanFileCount;
//			}
//			html += '</div>';
//		});
//		html += 	'<div id="createMemoContentBtnBefore">';
//		html += 		'<div class="createMemoContentBtn">+ Add a card</div>';
//		html += 	'</div>';
//		html += '</div>';
//	});
//	html += '<div id="createListBtnBefore"><div id="createListBtn">+ Add a list</div></div>';
//	$('#outer').append(html);
//}

//모달 닫을때마다 리스트 만들기
$('#myModal').on('hide.bs.modal', function(event){
	//전체 리스트
//	$.ajax({
//		url: "SelectKanban.ajax",
//		dataType: "json",
//		success: function(resData) {
//			$('#outer').empty();
//			makeKanbanList(resData);
//		}
//	});
	location.reload();
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

//모달창이 떴을 때 내용 업뎃하기
$('#myModal').on('shown.bs.modal', function () {
//	setTimeout(function(){
		//상세보기에서 카드내용 클릭해서 update
		$('#kanbanContent').click(function() {
			let cardContentText = $(this).text();
			//기존에 내용이 있으면
			if(cardContentText != "") {
				$(this).after("<textarea placeholder='Add a more detailed description...' style='resize: none;' id='textarea'>"+cardContentText+"</textarea>");
				setTimeout(function(){
					$("#textarea").focus();
					$('#kanbanContent').hide();
				}, 100);
				
				//textarea에서 나왔을 때 카드내용 update
				$('#textarea').blur(function() {
					let updateCardContent = $(this).val();
					//기존의 내용과 다르면 ajax 호출
					if(updateCardContent != cardContentText) {
						updateContent(updateCardContent);
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
						updateContent(updateCardContent);
					}
				});
			}
		});
//	}, 100);
});

//내용 update하는 함수
function updateContent(updateCardContent) {
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



