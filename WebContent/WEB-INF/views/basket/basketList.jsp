<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}" />
<style>
a:link {
	text-decoration: none;
}

table img {
	width: 80px;
}
/* paging */
table tfoot ol.paging {
	margin-left: 30%;
	list-style: none;
}

table tfoot ol.paging li {
	float: left;
	margin-right: 8px;
}

table tfoot ol.paging li a {
	display: block;
	padding: 3px 7px;
	border: 1px solid #00B3DC;
	color: #2f313e;
	font-weight: bold;
}

table tfoot ol.paging li a:hover {
	background: #00B3DC;
	color: white;
	font-weight: bold;
}

.disable {
	padding: 3px 7px;
	border: 1px solid silver;
	color: silver;
}

.now {
	padding: 3px 7px;
	border: 1px solid #ff4aa5;
	background: #ff4aa5;
	color: white;
	font-weight: bold;
}
</style>

<article>
	<header>
		<h1>장바구니</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>번호</th>
						<th>상품 이름</th>
						<th>단가</th>
						<th>총 금액</th>
						<th>상품 갯수</th>
					</tr>
				</thead>

				<tbody>
					<%-- for start --%>
					<c:forEach var="b" items="${list}">
						<tr>
							<td>${b.r_num}</td>
							<td><a href="basketDetail?b_num=${b.b_num}">${b.itemvo.i_name}</a></td>
							<td>${b.itemvo.i_price}</td>
							<td>${b.ordersvo.totalPrice}</td>
							<td>${b.b_stock}</td>
						</tr>
					</c:forEach>
					<%-- for end --%>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="6">
							<ol class="paging">
									<c:choose>
										<c:when test="${startPage < 6}">
											<li class="page-item disabled">이전으로</li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="basketList?cPage=${startPage-1}">이전으로</a></li>
										</c:otherwise>
									</c:choose>
									<c:forEach varStatus="i" begin="${startPage}" end="${endPage }"
										step="1">
										<c:choose>
											<c:when test="${i.index == nowPage}">
												<li class="page-item">${i.index}</li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="basketList?cPage=${i.index}">${i.index}</a></li>
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
									<c:choose>
										<c:when test="${endPage >= totalPage }">
											<li class="page-item">다음으로</li>
										</c:when>
										<c:when test="${totalPage > (nowPage+pagePerBlock)}">
											<li>
											<li><a class="page-link"
												href="basketList?cPage=${endPage+1 }">다음으로</a></li>
										</c:when>
										<c:otherwise>
											<li>
											<li><a class="page-link"
												href="basketList?cPage=${totalPage}">다음으로</a></li>
										</c:otherwise>
									</c:choose>
								</ol>
						</th>
					</tr>
				</tfoot>
			</table>
	</div>
</article>