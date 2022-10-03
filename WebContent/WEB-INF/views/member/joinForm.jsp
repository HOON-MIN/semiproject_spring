<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
h4, footer {
	padding: 40px 0;
	text-align: center;
}

.container {
	margin-top: 64px;
	margin-bottom : 64px;
	padding : 0 64px;
	width: 640px;
	background-color: white;
}

.ok {
	color: #000;
	display: none;
}

.no , .noPwd , .emailChk{
	color: tomato;
	display: none;
}
</style>


<div class="container">
	<div class="input-form-backgroud row">
		<div class="input-form col-md-12 mx-auto">
			<h4 class="mb-3">회원가입</h4>
			<form class="validation-form" method="post" action="joinMember">

				<div class="mb-3">
					<label for="mem_id">아이디</label>
						<input type="text" class="form-control" id="mem_id" name="mem_id"
							required oninput="checkId()">
					
					<div>
						<span class="ok">사용 가능한 아이디 입니다</span> 
						<span class="no">이 아이디는 이미 사용중입니다</span>
					</div>
				</div>


				<div class="row">
				<div class="col-md-6 mb-3">
					<label for="mem_pw">비밀번호 </label> <input type="password"
						class="form-control" id="mem_pw" name="mem_pw" required>
					<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
					<span class="noPwd">비밀번호가 일치 하지 않습니다</span>
				</div>
				<div class="col-md-6 mb-3">
					<label for="pwdChk">비밀번호 확인</label> <input type="password"
						class="form-control" id="pwdChk" name="pwdChk">
					<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
					
				</div>
			
				</div>


					<div class="mb-3">
						<label for="mem_name">이름</label> <input type="text"
							class="form-control" id="mem_name" name="mem_name"
							placeholder="김OO" required>
						<div class="invalid-feedback">이름을 입력해주세요.</div>
					</div>

					<div class="mb-3">
						<label for="mem_jubun">주민번호 </label> <input type="text"
							class="form-control" id="mem_jubun" name="mem_jubun"
							placeholder="000000-1234567" required>
						<div class="invalid-feedback">주민번호를 입력해주세요.</div>
					</div>
				
				<div class="mb-3">
					<label for="mem_email">휴대폰번호</label> 
					<input type="mem_phone"
						class="form-control" 
						id="mem_phone" 
						name="mem_phone"
						placeholder="010-0000-0000" 
						required>
					<div class="invalid-feedback">휴대폰를 입력해주세요</div>
				</div>				


				<div class="mb-3">
					<label for="mem_email">이메일</label> <input type="mem_email"
						class="form-control" id="mem_email" name="mem_email"
						placeholder="you@example.com" required>
					<div class="invalid-feedback">이메일을 입력해주세요.</div>
					<span class="emailChk">이미 등록된 이메일 입니다</span>
				</div>
				
				<div class="row">
				
					<label for="mem_adr">주소</label> <input type="text"
						class="form-control" id="mem_adr" name="mem_adr"
						placeholder="서울특별시 강남구" required>
					<div class="invalid-feedback">주소를 입력해주세요.</div>
				</div>

				<div class="col-md-6 mb-3">
					<label for="mem_adr2"> 상세주소 <span class="text-muted">&nbsp;(필수아님)</span>
					</label> <input type="text" class="form-control" id="mem_adr2"
						placeholder="상세주소를 입력해주세요.">
				</div>
				</div>

				<div class="row">
					<div class="col-md-8 mb-3">
						<label for="mem_q">비밀번호 찾기 질문</label> 
						<select	class="selectpicker d-block w-100" 
						id="mem_q" 
						name="mem_q"
						data-style="btn-inverse">
							<option value="">질문을 선택하세요</option>
							<option value="추억">추억에 남는 장소는?</option>
							<option value="초등학교">다녔던 초등학교 이름은?</option>
						</select>
						<div class="invalid-feedback">질문을 선택해주세요.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="code">비밀번호 찾기 정답</label> 
						<input type="text"
							class="form-control" 
							id="mem_a"
							name="mem_a"
							placeholder="" 
							required>
						<div class="invalid-feedback">답을 적어주세요</div>
					</div>
				</div>
			
				<hr class="mb-4">

				<div class="custom-control custom-checkbox text-center">
					<input type="checkbox" class="custom-control-input" id="aggrement"
						required> <label class="custom-control-label"
						for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
				</div>
				
				<div class="mb-4 text-center">
					<button class="btn btn-primary btn-dark text-white btn-block"
						type="submit">가입완료</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
<script>
	//아이디 중복확인
	function checkId() {
		var param = $('#mem_id').val();
		console.log(param);
		//alert("param"+param);
		$.ajax({
			url:"idChk?id="+param,
			success:function(data){
				console.log(data);
				if(data == 1){
					 $('.no').css("display","inline-block");
	                 $('.ok').css("display", "none");
				}else{
	                 $('.ok').css("display","inline-block"); 
	                 $('.no').css("display", "none");
				}
			}
		});
	};
	
	
	//이메일 중복 확인
	$('#mem_email').focusout(function() {
		var param = $('#mem_email').val();
		$.ajax({
			url : "emailChk?email=" + param,
			success : function(data) {
				if(data ==1){
					$('.emailChk').css("display", "inline-block");
				}
			}
		});
	});
	
	$('#mem_email').focusin(function() {
		$('.emailChk').css("display", "none");
	});
	
	
	//비밀번호 1,2 맞는지 확인
	$('#pwdChk').focusout(function() {
		var pwd1 = $('#pwdChk').val();
		var pwd2 = $('#mem_pw').val();
		if (pwd1 != pwd2) {
			$('.noPwd').css("display", "inline-block");
		}
	});
	
	$('#pwdChk').focusin(function() {
		$('.noPwd').css("display", "none");
	});
</script>
</html>