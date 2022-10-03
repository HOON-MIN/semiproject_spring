<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
h4{
	padding: 40px 0;
	text-align: center;
}
.container {
	margin-top: 80px;
	padding-bottom : 40px;
	padding-left: 64px;
	padding-right : 64px;
	width: 640px;
	background-color: white;
}
.links{
	display:flex;
	justify-content: center;
}

.button{
	text-align: center;
}

.links a{
	text-align : center;
	text-decoration: none;
	color : #232323;
}

.links span{
	padding : 0 8px; 
}
</style>
<div class="container">	
	<h4>로그인폼</h4>
	<form action="loginProcess" method="post">
		<div class="mb-3">
			<label class="form-label" for="mem_id">아이디</label> 
			<input class="form-control" type="text" name="mem_id" id="mem_id" />
		</div>
		<div class="mb-3">
			<label class="form-label" for="mem_pw">비밀번호</label> 
			<input class="form-control" type="password" name="mem_pw" id="mem_pw" />
		</div>
		<div class="mb-3 button">
		<button class="btn btn-outline-dark btn-sm" type="submit">로그인</button>
		</div>
	</form>

    <div class="links">
         <a href="memberId">아이디 찾기</a> <span>|</span> <a href="memberPw">비밀번호 찾기</a> <span>|</span> <a href="${pageContext.request.contextPath}/web/member/joinForm">회원가입</a>
	</div>
</div>

</body>


</html>