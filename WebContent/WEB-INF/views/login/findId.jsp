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
<script type="text/javascript">



var msg = "${msg}";

if (msg != "") {
	alert(msg);
}


</script>

<div class="container">	
	<h4>아이디 찾기</h4>
	<form action="findId" method="post">
		<div class="mb-3">
			<label class="form-label" for="mem_name">이름</label> 
			<input class="form-control" type="text" name="mem_name" id="mem_name" />
		</div>
		<div class="mb-3">
			<label class="form-label" for="mem_email">이메일</label> 
			<input class="form-control" type="text" name="mem_email" id="mem_email" />
		</div>
		<div class="mb-3 button">
		<button class="btn btn-outline-dark" id="btn1"type="submit">찾기</button>
		</div>
	</form>
</div>

</body>
</html>