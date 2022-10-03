<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article>
	<header>
		<h1>adminOrders Detail Form</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<%-- updateForm 들어갈 자리 --%>
		<fieldset>
			<legend>adminOrders update Form</legend>
			<form method="post" action="adminordersUpdate" id="update"
				name="update">
				<input type="hidden" id="ord_no" name="ord_no" value="${vo.ord_no }">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th colspan="2" style="text-align: center;">주문자명</th>
							<th colspan="2" style="text-align: center;">회원명</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2" style="text-align: center;"><input
								type="text" name="ord_name" id="ord_name" value="${vo.ord_name}" />
							</td>
							<td colspan="2" style="text-align: center;">${vo.mvo.mem_name}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th>상품명</th>
							<th>상품가격</th>
							<th>주문수량</th>
							<th>총가격</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="text-align: center;">${vo.ivo.i_name}</td>
							<td style="text-align: center;">${vo.ivo.i_price}</td>
							<td style="text-align: center;">${vo.ord_count}</td>
							<td style="text-align: center;">${vo.totalPrice}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">상품사진</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4"><img style="width: auto;"
								src="${pageContext.request.contextPath}/resources/imagefile/${vo.ivo.i_img}"
								id="imgx"></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">배송주소</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4"><input type="text" name="ord_address"
								id="ord_address" value="${vo.ord_address}" /></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th>주문날짜</th>
							<th>배송완료날짜</th>
							<th colspan="2">배송상태</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${vo.ord_date}"</td>
							<td>${vo.ord_edate}"</td>
							<td colspan="2"><select class="btn btn-outline-secondary"
								name="i_status" id="i_status">
									<option value="">${vo.i_status}</option>
									<option value="발송완료">발송완료</option>
									<option value="주문취소">주문취소</option>
							</select></td>
						</tr>
					</tbody>
				</table>
				<div class="form-group" style="text-align: right; margin-top: 10px;">
					<input type="button" value="주문정보수정" id="upordersbtn"
						class="btn btn-outline-secondary" /> <input type="button"
						value="삭제" id="delordersbtn" class="btn btn-outline-secondary" />
				</div>
			</form>
		</fieldset>

	</div>
	<script>
		$(function() {
			$('#upordersbtn').click(function() {
				$('#update').submit();
			});
			$('#delordersbtn').click(function() {
				location = "adminordersDelete?ord_no=${vo.ord_no}";
			});
		});
	</script>
</article>
