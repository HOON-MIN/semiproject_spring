<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js" />
</script>
<style>
.container {
	margin-top: 40px;
	background-color: white;
	padding: 64px;
}
</style>
<div class="container">
	<div>${sessionScope.sessionId }</div>
	<h1>�α� ����Ʈ</h1>
	<hr>
	<h4>${sessionScope.sessionName }��(${sessionScope.sessionId })�� �α��� ���</h4>
	<table class=table>
		<thead>
			<tr>
				<th>IP</th>
				<th>���ӱ��</th>
				<th>Status</th>
				<th>�α��Ͻ�</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="e" items="${loglist.memberlog}">
			<tr>
			<td>${e.reip}</td>
			<td>${e.uagent}</td>
			<td>${e.status}</td>
			<td>${e.sstime}</td>
			</tr>
			</c:forEach>	
		</tbody>
	</table>
</div>

</body>
</html>