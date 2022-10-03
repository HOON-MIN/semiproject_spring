<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<style>
.container {
	margin-top: 80px;
	padding-top : 40px;
	padding-bottom : 40px;
	padding-left: 64px;
	padding-right : 64px;
	width: 640px;
	background-color: white;
}
.row{
	display :flex;
	justify-content: center;
	margin-top : 40px;
}
h2{
	text-align: center;
	
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
     <div class="container card" >
        
  <h2>아이디는</h2> 
  <h2>[ ${resid } ]</h2>
  <h2> 입니다</h2>
  		
  <div class="row">
 	<div class="col-md-3 mb-3">
  <button type="button" class="btn btn-dark btn-sm" onclick="location.href='/semi_B_PJT_0712/web/login/loginForm'">로그인페이지</button>
  	</div>
  	<div class="col-md-3 mb-3">
  <button type="button" class="btn btn-dark btn-sm" onclick="location.href='/semi_B_PJT_0712/web/login/memberPw'">비밀번호찾기</button>
  	</div>
  </div>
  </div>
</body>
</html>