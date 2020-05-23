<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="myModal" role="dialog">
  <div class="modal-dialog">
	<div class="modal-content" style="background-color: #ededed;">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
	  </div>
	  <div class="modal-body">
			<div class="cont">
			<div class="form sign-in">
				<h2 style="color: #6a51a7 !important;">Welcome back,</h2>
				
				<!-- 로그인 Form -->
				<form action="#" method="post" name="loginForm" id="loginForm">
					<label>
						<span>ID</span>
						<input type="text" name="id" id="loginId"/>
					</label>
					<label>
						<span>Password</span>
						<input type="password" name="pwd" id="loginPwd"/>
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
				<div class="form sign-up">
				<h2 style="color: #6a51a7 !important;">Time to feel like home,</h2>
				
				<!-- 회원가입 Form -->
				<form action="#" method="post" name="joinForm" id="joinForm">
					<label>
						<span>ID</span>
						<input type="text" name="id" id="joinId"/>
					</label>
					<label>
						<span>Password</span>
						<input type="password" name="pwd" id="joinPwd"/>
					</label>
					<label>
						<span>Name</span>
						<input type="text" name="name" id="name"/>
					</label>
					<label>
						<span>Email</span>
						<input type="email" name="email" id="email"/>
					</label>
					<button type="submit" class="submit" style="background-color: #6a51a7 !important;">Sign Up</button>
				</form>
				
				</div>
			</div>
			</div>
		</div>
	</div>
  </div>
</div>