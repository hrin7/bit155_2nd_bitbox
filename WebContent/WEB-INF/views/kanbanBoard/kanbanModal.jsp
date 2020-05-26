<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- The Modal -->
<div class="modal fade" id="myModal">
	<div class="modal-dialog modal-lg shadow">
		<div class="modal-title">

			<!-- Modal Header -->
			<div class="modal-header">
				<h2 class="modal-title" id="cardName"></h2>
				<h5 class="modal-title" id="listName"></h5>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<h2 class="font-design" style="float: left;">Description</h2>
				<button class="button primary small" style="padding: 0px 5px;" id="editBtn">Edit</button>
				<br><br><br>
				<p id="kanbanContent" style="clear: both;"></p>
				<br>
				<p id="kanbanDate"></p>
				
				<h2 class="font-design">Activity</h2>
				<input type="text"/>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="button primary small" data-dismiss="modal">Close</button>
			</div>

		</div>
	</div>
</div>
