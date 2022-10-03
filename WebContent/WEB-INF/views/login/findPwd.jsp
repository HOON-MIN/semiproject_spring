<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
h4 {
	padding: 40px 0;
	text-align: center;
}

.container {
	margin-top: 80px;
	padding-bottom: 40px;
	padding-left: 64px;
	padding-right: 64px;
	width: 640px;
	background-color: white;
}

.links {
	display: flex;
	justify-content: center;
}

.button {
	text-align: center;
}

.links a {
	text-align: center;
	text-decoration: none;
	color: #232323;
}

.links span {
	padding: 0 8px;
}
</style>
<script type="text/javascript">
	var msg = "${msg}";

	if (msg != "") {
		alert(msg);
	}
</script>

<div class="container">
	<h4>��й�ȣ ã��</h4>
	<form action="findPwd" method="post">
		<div class="mb-3">
			<label class="form-label" for="mem_id">���̵�</label> 
			<input
				class="form-control" 
				type="text" 
				name="mem_id" 
				id="mem_id" 
				value="${sessionScope.sessionId }"
				required/>
		</div>
		<div class="row">
			<div class="col-md-8 mb-3">
				<label for="mem_q">��й�ȣ ã�� ����</label> <select
					class="selectpicker d-block w-100" id="mem_q" name="mem_q"
					data-style="btn-inverse">
					<option value="">������ �����ϼ���</option>
					<option value="�߾�">�߾￡ ���� ��Ҵ�?</option>
					<option value="�ʵ��б�">�ٳ�� �ʵ��б� �̸���?</option>
				</select>
				<div class="invalid-feedback">������ �������ּ���.</div>
			</div>
			<div class="col-md-4 mb-3">
				<label for="code">��й�ȣ ã�� ����</label> <input type="text"
					class="form-control" id="mem_a" name="mem_a" placeholder=""
					required>
				<div class="invalid-feedback">���� �����ּ���</div>
			</div>
		</div>
		<div class="mb-3 button">
			<button class="btn btn-outline-dark" id="btn1" type="submit">ã��</button>
		</div>
	</form>
</div>

</body>
</html>