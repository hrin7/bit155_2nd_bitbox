/*$(function() {
			if("${requestScope.msg}" == "정상적으로 회원가입이 완료되었습니다") {
				alert("${requestScope.msg}");
			}else if("${requestScope.msg}" == "회원가입중 에러 발생...다시 회원가입 바람"){
				alert("${requestScope.msg}");
			}else if("${requestScope.msg}" == "로그인 성공 메인페이지로 이동"){
				alert("${requestScope.msg}");
			}
		});
	실행이 안됨 이유가 뭐지????????????? 	
*/

		//비동기 방식으로 ID중복체크 클릭시 중복되는ID면 알람창으로 해당ID를 사용할수 없다고 알려주기
		function id_duplicate_check(){
			var userID = $('#id').val();
			//alert("중복체크 클릭시 "+userID);
			var idRegExp = /^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사
			
			if(userID == null || userID == ""){
				swal("ID를 입력해주세요");
				return false;
			}
			
			if(!idRegExp.test(userID)) { //입력받은 ID 값이 정규표현식에 일치하는지 테스트 
		          swal("아이디는 4~12자 영문 대소문자 또는 숫자 입력바람");
		          return false;
		    }
			
			$.ajax({
				url : "UserIdCheck.ajax", //요청할 URL 주소
				type : "POST",
				data :{id : userID}, 
				dataType : "text",
				success : function(result){ //성공적으로 데이터를 보냈다면 result에 반환값이 담김
					
					console.log("반환값"+result);
					if(result == 1){
						swal("사용할수 없는 ID입니다");
					}else if(result == 0){
						swal("사용 가능한 ID입니다");
					}else{
						swal("데이터 베이스 오류발생");
					}
				}
					
			});
		}
		