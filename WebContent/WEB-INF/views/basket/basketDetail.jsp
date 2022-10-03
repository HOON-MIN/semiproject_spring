<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}" />

<article>
	<header>
		<h1>장바구니 상세 페이지</h1>

	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>

	<div>
		<form method="post" name="basketupdate" id="basketupdate"
			enctype="multipart/form-data">
			<input type="hidden" id="b_num" name="b_num"
				value="${basketdetail.b_num}">
			<input type="hidden" id="i_no" name="i_no"
				value="${basketdetail.i_no}">
			<input type="hidden" id="i_category" name="i_category" value="${basketdetail.itemvo.i_category}">
			<input type="hidden" id="i_gender" name="i_gender" value="${basketdetail.itemvo.i_gender}">
			<div class="form-group">
				<label for="b_num">번호</label> <input type="text"
					class="form-control" id="b_num" name="b_num"
					value="${basketdetail.b_num}" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="i_name">상품 이름</label> <input type="text"
					class="form-control" id="i_name" name="i_name" readonly="readonly"
					value="${basketdetail.itemvo.i_name}">
			</div>
			<div class="form-group">
				<label for="i_price">단가</label> <input type="text"
					class="form-control" id="i_price" name="i_price"
					value="${basketdetail.itemvo.i_price}" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="totalPrice">총 금액</label> <input type="text"
					class="form-control" id="totalPrice" name="totalPrice"
					value="${basketdetail.ordersvo.totalPrice}" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="b_stock">상품 갯수</label> <input type="text"
					class="form-control" id="b_stock" name="b_stock"
					value="${basketdetail.b_stock}">
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary" id="updatebtn">수정</button>
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary" id="delbtn">삭제</button>
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary" id="buybtn">주문페이지로</button>
			</div>
		</form>
	</div>
</article>
<script>
	$(function() {
		$(function() {
			$('#updatebtn').click(
					function() {
						$('#basketupdate').attr("action",
								"${mycontext}/web/basket/basketupdate");
						$('#basketupdate').submit(function() {
							alert("수정되었습니다.");
						});
					});

			$('#delbtn').click(
					function() {
						$('#basketupdate').attr("action",
								"${mycontext}/web/basket/basketdel");
						$('#basketupdate').submit(function() {
							alert("삭제되었습니다.");
						});
					});
			$('#buybtn').click(
					function() {
						$('#basketupdate').attr("action", "${mycontext}/web/basket/basketorder");
						$('#basketupdate').submit(function() {
						alert('구매페이지로 이동합니다');
			});
		});
	});
});
</script>