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
	<h1>로그 리스트</h1>
	<hr>
	<h4>${sessionScope.sessionName }님(${sessionScope.sessionId })의 로그인 기록</h4>
	<table class=table>
		<thead>
			<tr>
				<th>IP</th>
				<th>접속기기</th>
				<th>Status</th>
				<th>로그일시</th>
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