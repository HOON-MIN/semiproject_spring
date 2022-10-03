<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>
<article>
	<header>
		<h1>OrdersForm Demo</h1>
	</header>
	
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	
	<form method="post" id="orderform" action="orderIn">
	<div>
		<div class="form-group">
			<label for="i_name">상품명</label> <input type="text"
				class="form-control" id="i_name" name="i_name"
				value="${detail.i_name }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="i_price">금액</label> <input type="text"
				class="form-control" id="i_price" name="i_price" readonly="readonly"
				value="${total}">
		</div>
		<div class="form-group">
			<label for="i_category">분류</label> <input type="text"
				class="form-control" id="i_category" name="i_category"
				value="${detail.i_category }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="i_gender">gender</label> <input type="text"
				class="form-control" id="i_gender" name="i_gender"
				value="${detail.i_gender }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="number">수량</label> <input type="text"
				class="form-control" id="number" name="number"
				value="${stock }" readonly="readonly"></div>
		</div>
		
								<!--사용자에게 입력 받는 구간  -->
		<input type="hidden" id="i_no" name="i_no" value="${detail.i_no }">
		<div class="form-group">
			<label for="ord_name">주문자 이름</label> <input type="text"
				class="form-control" id="ord_name" name="ord_name"
				>
		</div><div class="form-group">
			<label for="ord_address">주소</label> <input type="text"
				class="form-control" id="ord_address" name="ord_address">
		</div>
									<!-- 최종 구매 금액 -->
		<div id="target2" style="text-align: right;"><h2> total()</h2></div>	
									<!-- 버튼 -->
		<div class="form-group" style="text-align: right; margin-top: 10px;">
			<button type="submit" class="btn btn-primary" >구매</button>
			<button type="button" class="btn btn-primary" 
			onclick="location.href='${mycontext}/item/itemDetail?s_no=${detail.i_no}'">목록</button>
		</div>
		
		</form>
		
		</article>
<script>
<%--구매페이지에서 총금액  --%>
$(function total() {
   let total = ${price*stock}
   $('#target2').html('총 금액 :'+total.toLocaleString('ko-KR')+ "원");
})
</script>