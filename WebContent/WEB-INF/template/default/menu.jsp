<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-expand-sm mybgColor">
	<div class="container-fluid">
		<ul class="navbar-nav">
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/web/main"
				class="nav-link active">Home</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/web/item/itemList?gender=m"
				class="nav-link">남성 의류</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/web/item/itemList?gender=w"
				class="nav-link">여성 의류</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/web/item/itemList?gender=c"
				class="nav-link">공용 의류</a></li>
			<li class="nav-item"><a class="nav-link" href="http://192.168.0.83/bteam2/';">가맹점문의</a>
			</li>
		</ul>
		<form class="d-flex">
			<input class="form-control me-2" type="text" placeholder="Search"
				name="searchv" id="searchv">
			<button class="btn btn-primary" type="button">Search</button>
		</form>
	</div>
</nav>