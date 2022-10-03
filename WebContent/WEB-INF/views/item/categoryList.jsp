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
		<h1>잡화</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div>
		<table class="table table-bordered" style="text-align: center;">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>가격</th>
					<th>카테고리</th>
					<th>사진</th>
				</tr>
			</thead>
			<tbody>
				<%-- for start --%>
				<c:forEach var="e" items="${category}">
					<tr>
						<td>${e.i_no}</td>
						<td><a href="categoryDetail?s_no=${e.i_no}">${e.i_name}</a></td>
						<td>${e.i_price}</td>
						<td>${e.i_category}</td>
						<td><img src="${mycontext}/resources/imagefile/${e.i_img}"
							style="width: 200px"></td>
					</tr>
				</c:forEach>
				<%-- for end --%>
			</tbody>

			<tfoot>
				<%-- Start! --%>
				<tr>
					<td colspan="8">
						<ol class="paging">
							<c:choose>
								<c:when test="${startPage < 6}">
									<li class="disable">이전으로</li>
								</c:when>
								<c:otherwise>
									<li><a href="categoryList?cPage=${nowPage-pagePerBlock}&search=${search}">이전으로</a></li>
								</c:otherwise>
							</c:choose>

							<c:forEach varStatus="i" begin="${startPage}" end="${endPage}"
								step="1">
								<c:choose>
									<c:when test="${i.index == nowPage}">
										<li class="now">${i.index}</li>
									</c:when>
									<c:otherwise>
										<li><a href="categoryList?cPage=${i.index}&search=${search}">${i.index}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${endPage >= totalPage }">
									<li class="disable">다음으로</li>
								</c:when>
								<c:when test="${totalPage > (nowPage+pagePerBlock)}">
									<li>
									<li><a href="categoryList?cPage=${nowPage+pagePerBlock}&search=${search}">다음으로</a></li>
								</c:when>
								<c:otherwise>
									<li>
									<li><a href="categoryList?cPage=${totalPage}&search=${search}">다음으로</a></li>
								</c:otherwise>
							</c:choose>
						</ol>
					</td>
				</tr>
				<tr>
					<td colspan="8" style="text-align: right;">
						<form class="d-flex" action="categoryList">
							<input class="form-control me-2" type="text"
								placeholder="Search.." style="width: 300px" name="search">
							<button class="btn btn-outline-secondary" type="submit">Search</button>
						</form>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</article>