<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>    
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
    <script>
        $( document ).ready(function() {
            $('#GoCoderImg1').resizable();        
            $('#GoCoderImg2').draggable();        
        });
    </script>
</head>
<body>
    <img src="https://t1.daumcdn.net/cfile/tistory/234774445960F69422" id="GoCoderImg1">
    <img src="https://t1.daumcdn.net/cfile/tistory/234774445960F69422" id="GoCoderImg2">
</body>
</html>
