<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="mycontext" value="${pageContext.request.contextPath}" />
<div class="d-flex flex-row-reverse mybgColor">
	<c:choose>
		<c:when test="${sessionScope.sessionId == null}">
			<div class="p-2 bg-warning">
				<a href="${mycontext}/web/login/loginForm"
					class="nav-link text-white" id="item2">Login</a>
			</div>
			<div class="p-2 bg-primary">
				<a href="${mycontext}/web/member/joinForm"
					class="nav-link text-white" id="item3">회원가입</a>
			</div>
		</c:when>
		<c:when test="${sessionScope.sessionId != null}">
			<div class="p-2 bg-warning">
				<a href="${mycontext}/web/login/loginPout"
					class="nav-link text-white" id="item2">Logout</a>
			</div>
			<c:choose>
				<c:when test="${sessionScope.sessionId == 'admin' }">
					<div class="p-2 bg-primary">
						<a
							href="${pageContext.request.contextPath}/web/adminorders/adminordersList"
							class="nav-link text-white" id="item4">주문/배송관리</a>
					</div>
					<div class="p-2 bg-success">
						<a href="${pageContext.request.contextPath}/web/adminitemstock/adminitemstockList"
							class="nav-link text-white" id="item6">상품재고관리</a>
					</div>
					<div class="p-2 bg-info">
						<a
							href="${pageContext.request.contextPath}/web/member/adminMemberList"
							class="nav-link text-white" id="item7">회원관리</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="p-2 bg-primary">
						<a href="${mycontext}/web/member/myPage"
							class="nav-link text-white" id="item3">내정보수정</a>
					</div>
					<div class="p-2 bg-success">
						<a href="${mycontext}/web/basket/basketList"
							class="nav-link text-white" id="item1">장바구니</a>
					</div>
					<div class="p-2 bg-info">
						<a href="${mycontext}/web/orders/ordersList"
							class="nav-link text-white" id="item1">MY구매리스트</a>
					</div>
					<div class="p-2 bg-danger">
						<a href="${mycontext}/web/login/logListView"
							class="nav-link text-white" id="item1">MYLogList</a>
					</div>					
<!-- 					<div class="p-2 bg-info"> -->
<!-- 						<a href="#" class="nav-link text-white" id="item1">구매목록</a> -->
<!-- 					</div> -->
<!-- 					<div class="p-2 bg-info"> -->
<!-- 						<a href="#" class="nav-link text-white" id="item1">내정보수정</a> -->
<!-- 					</div> -->
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose>

	<!-- 관리자는 <회원관리> <상품재고관리> <배송관리>  -->
	<c:choose>
		<c:when test="${sessionScope.sessionId == 'admin' }">

		</c:when>
	</c:choose>
</div>