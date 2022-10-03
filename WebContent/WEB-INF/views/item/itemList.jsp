<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>
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
		<h1>�Ƿ�</h1>
	</header>
	 <p>��ǰ�� : ${itemCount} / �˻��� : ${search}</p>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div>
		<table class="table table-bordered" style="text-align: center;">
			<thead>
				<tr>
					<th>��ȣ</th>
					<th>����</th>
					<th>����</th>
					<th>ī�װ�</th>
					<th>����</th>
				</tr>
			</thead>
			<tbody>
				<%-- for start --%>
				<c:forEach var="e" items="${list}">
					<tr>
						<td>${e.r_num}</td>
						<td><a href="itemDetail?s_no=${e.i_no}">${e.i_name}</a></td>
						<td>${e.i_price}</td>
						<td>${e.i_category}</td>
						<td><img src="${mycontext}/resources/imagefile/${e.i_img}"
							style="height: 130; width: 100px"></td>
					</tr>
				</c:forEach>
				<%-- for end --%>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8">
						<ol class="paging">
								<c:choose>
									<c:when test="${startPage < 6}">
										<li class="disabled">��������</li>
									</c:when>
									<c:otherwise>
										<li><a href="itemList?gender=${g}&cPage=${startPage-1}&category=${category}&search=${search}">��������</a></li>
									</c:otherwise>
								</c:choose>
								<c:forEach varStatus="i" begin="${startPage}" end="${endPage }"
									step="1">
									<c:choose>
										<c:when test="${i.index == nowPage}">
											<li class="now">${i.index}</li>
										</c:when>
										<c:otherwise>
											<li><a href="itemList?gender=${g}&cPage=${i.index}&category=${category}&search=${search}">${i.index}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${endPage >= totalPage }">
										<li class="disable">��������</li>
									</c:when>
									<c:when test="${totalPage > (nowPage+pagePerBlock)}">
										<li>
										<li><a href="itemList?gender=${g}&cPage=${endPage+1 }&category=${category}&search=${search}">��������</a></li>
									</c:when>
									<c:otherwise>
										<li>
										<li><a href="itemList?gender=${g}&cPage=${totalPage}&category=${category}&search=${search}">��������</a></li>
									</c:otherwise>
								</c:choose>
						</ol>
					</td>
				</tr>
				<tr>
					<th colspan="5">
						<form class="d-flex" action="itemList">
						<input type="hidden" name="searchreset" value="1">
						<input type="hidden" name="gender" value="${g}">
						<select class="btn btn-outline-secondary" name="category"
								id="category">
								<option value="">--����--</option>
								<option value="����">����</option>
								<option value="����">����</option>
								<option value="��ȭ">��ȭ</option>
							</select>
							<input class="" type="text"
								placeholder="Search.." style="width: 300px" name="search">
							<button class="btn btn-outline-secondary" type="submit">Search</button>
						</form>
					</th>
				</tr>
			</tfoot>
		</table>
	</div>
</article>