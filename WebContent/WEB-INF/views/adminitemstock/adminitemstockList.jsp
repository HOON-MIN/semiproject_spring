<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article>
	<script>
		$(function() {
			//         $('#selectitem').change(function() {
			//     		$('#target').submit();
			//     	});
			$('#additembtn').click(function() {
				location = "adminitemstockForm";
			});
		});
	</script>
	<header>
		<h1>AdminitemstockList</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div>
		<p class="list-unstyled">
			<c:if test="${cnt != null}">
				<c:if test="${cMsg != null}">${cMsg}</c:if>상품수 : ${cnt} / <c:if
					test="${sMsg != null}">검색어 : ${sMsg}</c:if>
			</c:if>
		</p>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>번호</th>
					<th>상품번호</th>
					<th>상품명</th>
					<th>상품가격</th>
					<th>카테고리1</th>
					<th>카테고리2</th>
					<th>상품이미지</th>
					<th>상품재고량</th>
					<th>제조사</th>
				</tr>
			</thead>
			<tbody>
				<%-- for start --%>
				<c:forEach var="e" items="${list}">
					<tr>
						<td>${e.r_num}</td>
						<td>${e.i_no}</td>
						<td><a href="adminitemstockDetail?i_no=${e.i_no}">${e.i_name}</a></td>
						<td>${e.i_price}</td>
						<td>${e.i_category}</td>
						<td>${e.i_gender}</td>
						<td><img style="width: 100px;"
							src="${pageContext.request.contextPath}/resources/imagefile/${e.i_img}"></td>
						<td>${e.stockvo.s_stock}</td>
						<td>${e.stockvo.s_name}</td>
					</tr>
				</c:forEach>
				<%-- for end --%>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="9" style="text-align: center;">
						<form action="adminitemstockList" method="get">
							<nav aria-label="Page navigation example">
								<ul class="pagination pagination-sm"
									style="margin: auto; width: 60%">
									<c:choose>
										<c:when test="${startPage < 6}">
											<li class="page-item disabled">이전으로</li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="adminitemstockList?cPage=${startPage-1}&category=${category}&search=${search}">이전으로</a></li>
										</c:otherwise>
									</c:choose>
									<!--        <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li> -->
									<c:forEach varStatus="i" begin="${startPage}" end="${endPage }"
										step="1">
										<c:choose>
											<c:when test="${i.index == nowPage}">
												<li class="page-item">${i.index}</li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="adminitemstockList?cPage=${i.index}&category=${category}&search=${search}">${i.index}</a></li>
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
												href="adminitemstockList?cPage=${endPage+1 }&category=${category}&search=${search}">다음으로</a></li>
										</c:when>
										<c:otherwise>
											<li>
											<li><a class="page-link"
												href="adminitemstockList?cPage=${totalPage}&category=${category}&search=${search}">다음으로</a></li>
										</c:otherwise>
									</c:choose>
								</ul>
								<%--       <input type="hidden" name="cPage" value="${cPage}"> --%>
								<%--       <input type="hidden" name="nowPage" value="${nowPage}"> --%>
								<%--       <input type="hidden" name="category" value="${category}"> --%>
								<%--       <input type="hidden" name="search" value="${search}"> --%>
							</nav>
						</form>
					</th>
				</tr>
				<tr>
					<th colspan="10" style="text-align: right;">
						<form class="d-flex" action="adminitemstockList" method="get">
							<input type="hidden" name="searchreset" value="1"> <select
								class="btn btn-outline-secondary" name="category" id="category">
								<option value="">--선택--</option>
								<option value="남성">남성</option>
								<option value="여성">여성</option>
								<option value="공용">공용</option>
							</select> <input class="form-control me-2" name="search" type="text"
								placeholder="Search.." style="width: 300px">
							<button class="btn btn-outline-secondary" type="submit">Search</button>
						</form>
					</th>
				</tr>
				<tr>
					<td colspan="10" style="text-align: right;">
						<%-- jQuery를 사용해서 글작성 폼으로 이동시키기
              - id 추가하기 : selector를 사용해서 핸들링
              - footer.jsp 
       --%>
						<button type="button" class="btn btn-outline-secondary"
							id="additembtn">상품등록</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</article>
