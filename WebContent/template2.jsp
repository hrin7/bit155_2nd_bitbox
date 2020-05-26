<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Modal Example</h2>
  <!-- Button to Open the Modal -->
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
    Open modal
  </button>

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
  
</div>
<script>
$('#myModal').on('hide.bs.modal', function(event){
	console.log($(this));
	console.log('들어오나??');
});
</script>
</body>
</html>
