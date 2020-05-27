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
	let listName = $(this).prev().val();
	if(listName == "") {
		alert('list title을 입력하세요.');
		$(this).prev().focus();
		return;
	}
	
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
	})
});

$('#outer').on('click', '.listCancelBtn', function() {
	$(this).parent().parent().hide();
	$('#createListBtn').show();
});

$('#createListBtn').click(function() {
	$(this).hide();
	$('#createListBtnBefore').before(memoSectionDivHtml);
	$('input').focus();
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
	$.each(resData[0].allKanbanList, function(index, obj) {
		html += '<div class="memoSectionDiv shadow" data-title="'+resData[0].kanbanGroupList[index].listName+'">';
		html += 	'<div class="memoTopHr"></div>';
		html += 	'<div class="memoTitle">'+resData[0].kanbanGroupList[index].listName+'</div>';
		$.each(obj, function(index2, obj2) {
			html += "<div class='memoContent shadow' data-toggle='modal' data-target='#myModal' data-value='"+obj2.kanbanNo+"' data-title='"+resData[0].kanbanGroupList[index].listName+"'>";
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
		});
		html += 	'<div id="createMemoContentBtnBefore">';
		html += 		'<div class="createMemoContentBtn">+ Add a card</div>';
		html += 	'</div>';
		html += '</div>';
	});
	html += '<div id="createListBtnBefore"><div id="createListBtn">+ Add a list</div></div>';
	$('#outer').append(html);
}

//모달 닫기 감지해서 닫을때마다 리스트 만들기
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
				html += "<textarea rows='4' placeholder='Add a more detailed description...' style='resize: none;'></textarea>";
				$('#kanbanContent').html(html);
				$('#editBtn').hide();
			} else {
				$('#kanbanContent').text(resData.kanbanContent);
				$('#editBtn').show();
			}
		}
	});
})

//상세보기에서 카드이름 바꾸기
$('#cardName').click(function() {
	let cardName = $(this).text();
	$(this).parent().prepend("<input type='text' value='"+cardName+"' id='cardNameInput'/>");
	$("#cardNameInput").focus();
	$('#cardName').hide();
	
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

