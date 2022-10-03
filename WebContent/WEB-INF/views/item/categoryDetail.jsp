<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<article>
	<header>
		<h1>잡화 상세 페이지</h1>

	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>

	<div>
		<form method="post" name="purchase" id="purchase">
			<input type="hidden" id="i_no" name="i_no" value="${detail.i_no}">
			<div class="form-group">
				<label for="i_name">상품명</label> <input type="text"
					class="form-control" id="i_name" name="i_name"
					value="${detail.i_name}" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="i_price">금액</label> <input type="text"
					class="form-control" id="i_price" name="i_price"
					readonly="readonly" value="${detail.i_price}">
			</div>
			<div class="form-group">
				<label for="i_category">분류</label> <input type="text"
					class="form-control" id="i_category" name="i_category"
					value="${detail.i_category}" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="imgn">이미지</label>
				<div>
					<img style="width: 200px;"
						src="${pageContext.request.contextPath}/resources/item/${detail.i_img}">
				</div>
			</div>
			<div class="form-group">
				<label for="num">재고 : ${detail.stockvo.s_stock}</label> <input
					type="number" id="num" name="num" max="${detail.stockvo.s_stock}"
					min="0">
			</div>
			<div>
				<label>총 금액</label>
				<div class="form-group" id="target1"></div>

			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary" id="buybtn">구매</button>
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary" id="basketbtn">장바구니</button>
			</div>
		</form>
	</div>
</article>
<script>
	$(function() {
		   <%--detail에서 상품 수량변동 시 총 금액 리스트에 출력해주는 메서드--%>
		      $('#num').change(function() {
		      let price= $('#i_price').val();
		      var number = $('#num').val();
		      var s_no = $('#i_no').val();
		         console.log('price ='+price);
		         console.log('number ='+number);
		      $.ajax({
		         url:"numchk?num="+number,
		      success:function(data){
		         console.log(data);
		         var total = data * price;
		         
		         $('#target1').html('총 금액 :'+total.toLocaleString('ko-KR')+ "원");
		      }
		      
		   });
		      });
		      <%--장바구니로 이동  --%>
		      $('#basketbtn').click(function() {
		         $('#purchase').attr("action", "${mycontext}/basket/addBasket")
		         $('#purchase').submit(function() {
		            alert('장바구니에 담겼습니다!');
		         });
		      });
		      <%--구매페이지로 이동  --%>
		      $('#buybtn').click(function() {
		         $('#purchase').attr("action", "order");
		         $('#purchase').submit(function() {
		            alert('구매페이지로 이동합니다');
		         });
		      });
	});
</script>