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
	<script>
		$(function() {
			// 			$('#select1').change(function() {
			// 				$('#categoryTarget').submit();
			// 			});
		});
	</script>

	<header>
		<h1>AdminOrders List</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div>

		<%-- 리팩토링 해야 함 --%>
		<p class="list-unstyled">
			<c:if test="${cnt != null}">
				<c:if test="${cMsg != null}">${cMsg}</c:if> 주문수 : ${cnt} / <c:if
					test="${sMsg != null}">검색어 : ${sMsg}</c:if>
			</c:if>
		</p>
		<table class="table table-bordered" style="text-align: center;">
			<thead>
				<tr>
					<%-- 관리자 로그인 시 구매목록 컬럼 --%>
					<th>NO</th>
					<th>주문 번호</th>
					<th>주문인</th>
					<th>회원 이름</th>
					<th>상품명</th>
					<th>주문 수량</th>
					<th>총 주문 금액</th>
					<th>주문 상태</th>
					<th>수령 장소</th>
					<th>주문날짜</th>
					<th>수령날짜</th>
					<th>주문관리</th>
				</tr>
			</thead>
			<tbody>
				<%-- for start --%>
				<c:forEach var="e" items="${list}">
					<tr>
						<%-- 관리자 로그인 시 구매목록 컬럼 --%>
						<td>${e.r_num }</td>
						<td><a href="adminDetailForm?ord_no=${e.ord_no }">${e.ord_no }</a></td>
						<td>${e.ord_name }</td>
						<td>${e.mvo.mem_name }</td>
						<td>${e.ivo.i_name }</td>
						<td>${e.ord_count}</td>
						<td>${e.totalPrice }</td>
						<td>${e.i_status }</td>
						<td>${e.ord_address}</td>
						<td>${e.ord_date }</td>
						<td>${e.ord_edate }</td>
						<td>
							<form class="d-flex" action="adminordersUpdate" method="post">
								<input type="hidden" id="ord_no" name="ord_no" value="${e.ord_no }"> 
								<input type="hidden" id="ord_name" name="ord_name" value="${e.ord_name }"> 
								<input type="hidden" id="ord_address" name="ord_address" value="${e.ord_address }"> 
								<select
									class="btn btn-outline-secondary" name="i_status">
									<option value="">--선택--</option>
									<option value="상품준비중">상품준비중</option>
									<option value="발송완료">발송완료</option>
									<option value="주문취소">주문취소</option>
								</select>
								<button class="btn btn-outline-secondary" type="submit">수정</button>
							</form>
							<form class="d-flex" action="adminordersDelete" method="get">
								<input type="hidden" name="ord_no" value="${e.ord_no}" />
								<button class="btn btn-outline-secondary" type="submit">삭제</button>
							</form>
						</td>
					</tr>
				</c:forEach>
				<%-- for end --%>
			</tbody>
			<tfoot>

				<%-- Start! --%>
				<tr>
					<td colspan="11">
						<ol class="paging">
							<c:choose>
								<c:when test="${startPage < 6}">
									<li class="disable">이전으로</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="adminordersList?cPage=${startPage-1}&category=${category}&search=${search}">이전으로</a></li>
								</c:otherwise>
							</c:choose>

							<c:forEach varStatus="i" begin="${startPage}" end="${endPage }"
								step="1">
								<c:choose>
									<c:when test="${i.index == nowPage}">
										<li class="now">${i.index}</li>
									</c:when>
									<c:otherwise>
										<li><a
											href="adminordersList?cPage=${i.index}&category=${category}&search=${search}">${i.index}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${endPage >= totalPage }">
									<li class="disable">다음으로</li>
								</c:when>
								<c:when test="${totalPage > (nowPage+pagePerBlock)}">
									<li>
									<li><a
										href="adminordersList?cPage=${endPage+1}&category=${category}&search=${search}">다음으로</a></li>
								</c:when>
								<c:otherwise>
									<li>
									<li><a href="adminordersList?cPage=${totalPage }">다음으로</a></li>
								</c:otherwise>
							</c:choose>

						</ol>
					</td>
				</tr>
				<tr>
					<th colspan="11" style="text-align: right;">
						<form class="d-flex" action="adminordersList" method="get"
							id="categoryTarget" name="category">
							<input type="hidden" name="searchreset" value="1"> <select
								class="btn btn-outline-secondary" name="category" id="select1">
								<option value="">--선택--</option>
								<option value="상품준비중">상품준비중</option>
								<option value="발송완료">발송완료</option>
								<option value="배송중">배송중</option>
								<option value="배송완료">배송완료</option>
								<option value="주문취소">주문취소</option>
							</select> <input class="form-control me-2" name="search" type="text"
								placeholder="Search.." style="width: 300px">
							<button class="btn btn-outline-secondary" type="submit">Search</button>
						</form>
					</th>
				</tr>
			</tfoot>
		</table>

	</div>
</article>

<script>
	$(function() {

		$('#delbtn').click(function() {
			location = "adminordersDelete?ord_no=${e.ord_no}";
		});
	});
</script>






