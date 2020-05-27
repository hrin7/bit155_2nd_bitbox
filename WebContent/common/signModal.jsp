<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	//회원가입 체크 로직 부분 
	function check() {
		  
		      if (!checkUserId(joinForm.id.value)) {
		          return false;
		      }else if(!checkPassword(joinForm.id.value,joinForm.pwd.value)) {
		          return false;
		      }else if(!checkName(joinForm.name.value)) {
		          return false;
		      }else if(!checkNickName(joinForm.nickname.value,joinForm.id.value)) {
		          return false;
		      }else if(!checkEmail(joinForm.email.value)) {
		          return false;
		      }
		      
		      //모든 정규표현식에 다일치 할경우 DB에있는 데이터랑 중복여부 판단을 ajax로 ??? 
		      
		      //id , nickname , email 을 객체형태로 담음 ??
		      var flag = false;		  
		      var userData = {"id":$('#id').val() , "nickname":$('#nickname').val() , "email":$('#email').val() };

		      // alert("userDate 객체"+userData.id+userData.nickname+userData.email	); //객체 값 뽑는것까진 성공 
		      
		      $.ajax({
					url : "UserInfoCheck.ajax", //요청할 URL 주소
					type : "POST",
					async : false, 
					data :{"id":$('#id').val(),"nickname":$('#nickname').val(),"email":$('#email').val()}, 
					dataType : "html", //보낼데이터가 html타입이 아니라 json 형태로 보내서 서버에서 받고싶음
					success : function(result){ //성공적으로 데이터를 보냈다면 result에 반환값이 담김	
						console.log("result 값:"+result);
						if(result == 10){
							swal("중복된 ID");
						}else if(result == 11){
							swal("중복된 닉네임");
						}else if(result == 12){
							swal("중복된 이메일");
						}else if(result == -1){
							swal("데이터 베이스 오류");
						}else if(result == 1){
							flag = true; // DB에있는 값이랑 중복된값이 없을떄만 true 값을줘서 실질적으로 회원정보를 DB테이블에 저장함
						}
					}
						
				});	
		      
		      
		      return flag;
		      
		  }//check() 끝
	
		  // 공백확인 함수
		  function checkExistData(value, dataName) {
			  
		      if (value == "") {
		    	  
		          alert(dataName + " 입력해주세요!");
		          return false;
		      }
		      return true;
		  }

		  //ID 체크 함수 	
		  function checkUserId(id) {
		      //Id가 입력되었는지 확인하기
		      if (!checkExistData(id, "아이디를"))
		          return false;
		
		      var idRegExp = /^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사
		      if (!idRegExp.test(id)) { //입력받은 ID 값이 정규표현식에 일치하는지 테스트 
		          alert("아이디는 4~12자 영문 대소문자 또는 숫자 입력바람");
		          joinForm.id.value = "";
		          joinForm.id.focus();
		          return false;
		      }
		      return true; //확인이 완료되었을 때
		  }

		  //PWD 체크 함수 
		  function checkPassword(id,password) {
			  
		        //비밀번호가 입력되었는지 확인하기
		        if (!checkExistData(password, "비밀번호를"))
		            return false;
		 
		        var password1RegExp =  /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/; //비밀번호 유효성 검사
		        
		        //패스워드가 입력되었을 경우 비밀번호 정규표현식 진행 
		        if (!password1RegExp.test(password)) {
		            alert("패스워드는 8~12 자리 영문, 숫자, 특수문자의 조합으로 입력해주세요.");
		            joinForm.pwd.value = "";
		            joinForm.pwd.focus();
		            return false;
		        }
		 
		        //아이디와 비밀번호가 같을 때..
		        if (id == password) {
		            alert("아이디와 비밀번호는 같을 수 없습니다!");
		            joinForm.pwd.value = "";
		            joinForm.pwd.focus();
		            return false;
		        }
		        return true; //확인이 완료되었을 때
		  }    
		  
		  //이름 체크 함수 
		  function checkName(name) {
		        if (!checkExistData(name, "이름을"))
		            return false;
		 
		        var nameRegExp = /^[가-힣]{2,4}$/; //한글만 입력할수 있는 정규표현식(2글자~4글자)
		        if (!nameRegExp.test(name)) {
		            alert("이름이 올바르지 않습니다(한글로만 2~4글자 입력 바람)");
		            joinForm.name.value = "";
		            joinForm.name.focus();
		            return false;
		        }
		        return true; //확인이 완료되었을 때
		    }
		  
		  //닉네임 체크 함수 
		  function checkNickName(nickname,id){
			  //닉네임이 입력되었는지 확인하기
		      if (!checkExistData(nickname, "닉네임을"))
		          return false;
		
		      var idRegExp = /^[a-zA-z0-9]{4,12}$/; //닉네임 유효성 검사 
		      if (!idRegExp.test(nickname)) { //입력받은 닉네임 값이 정규표현식에 일치하는지 테스트 
		          alert("닉네임은 4~12자 영문 대소문자 또는 숫자 입력바람");
		          joinForm.nickname.value = "";
		          joinForm.nickname.focus();
		          return false;
		      }
		      
		      //아이디와 닉네임이 같을경우 
	         if (nickname == id) {
	            alert("닉네임과 ID는 같을 수 없습니다!");
	            joinForm.nickname.value = "";
	            joinForm.nickname.focus();
	            return false;
	         }
		      return true; //확인이 완료되었을 때
		  }
		  
		  //이메일 체크 함수 
		  function checkEmail(email){
			  //mail이 입력되었는지 확인하기
		        if (!checkExistData(email, "이메일을"))
		            return false;
		 
		        var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{3}$/;   
		        if (!emailRegExp.test(email)) {
		            alert("이메일 형식이 올바르지 않습니다!");
		            joinForm.email.value = "";
		            joinForm.email.value = "";
		            return false;
		        }
		        return true; //확인이 완료되었을 때
		  }

		  
		  
		 ////////////////////////////////////////// 로그인 체크 함수 부분 //////////////////////////////////////////////////////
		 
		 function loginCheck(){
			 
			 var flag = false;		  
		     var userData = {"userid":$('#userid').val() , "userpwd":$('#userpwd').val() };

		      //alert("userDate 객체"+userData.userid+userData.userpwd); //객체 값 뽑는것까진 성공 
		      
		      $.ajax({
					url : "UserLoginCheck.ajax", //요청할 URL 주소
					type : "POST",
					async : false, 
					data :{"userid":$('#userid').val() , "userpwd":$('#userpwd').val() }, 
					dataType : "html", //보낼데이터가 html타입이 아니라 json 형태로 보내서 서버에서 받고싶음
					success : function(result){ //성공적으로 데이터를 보냈다면 result에 반환값이 담김	
						console.log("result 값:"+result);
						if(result == 0){
							swal("해당 ID는 존재하지 않습니다");
						}else if(result == 1){
							flag = true; //로그인 성공시 
						}else if(result == 2){
							swal("패스워드가 틀립니다(5회 틀릴시 24시간동안 계정잠금)");
						}else if(result == -1){
							swal("데이터 베이스 오류");
						}
					}  
						
				});	
		      
		      
		      return flag;
			 
			 
			 
		 }//loginCheck() 끝 
		 
		  
</script>
	
<div class="modal fade" id="myModal" role="dialog">
  <div class="modal-dialog">
	<div class="modal-content" style="background-color: #ededed;">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
	  </div>
	  <div class="modal-body">
			<div class="cont" style="height: 600px;">
			<div class="form sign-in">
				<h2 style="color: #6a51a7 !important;">Welcome back,</h2>
				
				<!-- 로그인 Form -->
				<form action="login.user" method="post" name="loginForm" id="loginForm" onsubmit="return loginCheck();">
					<label>
						<span>ID</span>
						<input type="text" name="userid" id="userid"/>
					</label>
					<label>
						<span>Password</span>
						<input type="password" name="userpwd" id="userpwd"/>
					</label>
					<p class="forgot-pass">Forgot password?</p>
					<button type="submit" class="submit" style="background-color: #6a51a7 !important;">Sign In</button>
				</form>
				
			</div>
			<div class="sub-cont">
				<div class="img">
				<div class="img__text m--up">
					<h2>New here?</h2>
					<p>Sign up and discover great amount of new opportunities!</p>
				</div>
				<div class="img__text m--in">
					<h2>One of us?</h2>
					<p>If you already has an account, just sign in. We've missed you!</p>
				</div>
				<div class="img__btn">
					<span class="m--up">Sign Up</span>
					<span class="m--in">Sign In</span>
				</div>
				</div>
				<div class="form sign-up" style="padding-top: 0px;">
				
				<!-- 회원가입 Form -->
				<form action="join.do" method="post" name="joinForm" id="joinForm" onsubmit="return check();">
					<label style="margin-top: 5px; width: 500px;">
						<span style="display:block;">ID</span>
						<input type="text" name="id" id="id" style="display: inline;width: 200px;"/>
						<button type="button" onclick="id_duplicate_check();" style="background:black;display: inline;width: 120px;padding-left: 0px;padding-right: 0px;">ID중복체크</button>
						<span  style="display: block;">영문 대소문자 또는 숫자 4~12자 </span>
					</label>
					<label style="margin-top: 5px; width: 420px;">
						<span>Password</span>
						<input type="password" name="pwd" id="pwd"/>
						<span>영문,숫자,특수문자 조합 8~12자</span>
					</label>
					<label style="margin-top: 5px; width: 420px;">
						<span>Name</span>
						<input type="text" name="name" id="name"/>
						<span>ID/PWD 분실시 가입정보를 통해 찾을수 있으므로 정확히 입력바람</span>
					</label>
					<label style="margin-top: 5px; width: 420px;">
						<span>Nickname</span>
						<input type="text" name="nickname" id="nickname"/>
						<span>영문 대소문자 또는 숫자 4~12자</span>
					</label>
					<label style="margin-top: 5px; width: 420px;">
						<span>Email</span>
						<input type="email" name="email" id="email" />	
						<span>ex)bit155@naver.com</span>
					</label>
					<button type="submit" class="submit" style="background-color: #6a51a7 !important;margin-top: 10px;margin-bottom: 0px;">Sign Up</button>
				</form>
				
				</div>
			</div>
			</div>
		</div>
	</div>
  </div>
</div>




