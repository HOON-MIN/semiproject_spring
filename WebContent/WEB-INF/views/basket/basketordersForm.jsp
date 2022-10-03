<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>
<article>
   <header>
      <h1>BasketOrderForm</h1>
   </header>
   
   <ul class="list-unstyled">
      <li class="border-top my-3"></li>
   </ul>
   
   <form method="post" action="basketorderIn">
   <input type="hidden" id="b_num" name="b_num"
				value="${detail.b_num}">
   <div>
      <div class="form-group">
         <label for="i_name">상품명</label> <input type="text"
            class="form-control" id="itemvo.i_name" name="itemvo.i_name"
            value="${detail.itemvo.i_name }" readonly="readonly">
      </div>
      <div class="form-group">
         <label for="totalprice">금액</label> <input type="text"
            class="form-control" id="ordersvo.totalPrice" name="ordersvo.totalPrice" readonly="readonly"
            value="${totalPrice }">
      </div>
      <div class="form-group">
         <label for="i_category">분류</label> <input type="text"
            class="form-control" id="itemvo.i_category" name="itemvo.i_category"
            value="${detail.itemvo.i_category }" readonly="readonly">
      </div>
      <div class="form-group">
         <label for="i_gender">gender</label> <input type="text"
            class="form-control" id="itemvo.i_gender" name="itemvo.i_gender"
            value="${detail.itemvo.i_gender }" readonly="readonly">
      </div>
      <div class="form-group">
         <label for="b_stock">수량</label> <input type="text"
            class="form-control" id="b_stock" name="b_stock"
            value="${detail.b_stock }" readonly="readonly"></div>
      </div>
      
                        <!--사용자에게 입력 받는 구간  -->
      <input type="hidden" id="i_no" name="i_no" value="${detail.i_no }">
      <div class="form-group">
         <label for="ord_name">주문자 이름</label> <input type="text"
            class="form-control" id="ordersvo.ord_name" name="ordersvo.ord_name" required="required"
            >
      </div><div class="form-group">
         <label for="ord_address">주소</label> <input type="text"
            class="form-control" id="ordersvo.ord_address" name="ordersvo.ord_address" required="required">
      </div>
                           <!-- 버튼 -->
      <div class="form-group" style="text-align: right; margin-top: 10px;">
         <button type="submit" class="btn btn-primary" id="purBtn">구매</button>
         <button type="button" class="btn btn-primary" 
         onclick="location.href='${mycontext}/web/basket/basketList'">목록</button>
      </div>
      
      </form>
      
      </article>
<script>
<%--구매페이지에서 총금액  --%>
$(function total() {
   let total = ${price*stock}
   $('#target2').html('총 금액 :'+total.toLocaleString('ko-KR')+ "원");
});

$(function () {
	$('#purBtn').click(function() {
		alert("구매가 완료되었습니다.");
		
	});
});







</script>