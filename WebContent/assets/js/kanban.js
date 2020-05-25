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
	let titleText = "";
		titleText += "<div class='memoContent shadow' data-toggle='modal' data-target='#myModal'>" + $(this).prev().val() + "<br>";
		titleText += 	'<i class="ri-chat-3-line"></i>';
		titleText += 	'<i class="ri-attachment-2"></i>';
		titleText += "</div>";
	//버튼의 형제들 삭제
	$(this).siblings().remove();
	//부모밑에 text를 append
	$(this).parent().append(titleText);
	//그리고 버튼은 마지막에 삭제 >> 순서를 이렇게 해야 append가 된다..
	$(this).remove();
	createMemoContentBtn.show();
});

$('.memoContent').draggable();






