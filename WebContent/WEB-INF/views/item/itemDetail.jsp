<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<article>
	<header>
		<h1>Item Detail Demo</h1>

	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	
	<div>
		<form action="" method="post" name="purchase" id="purchase" >
		<input type="hidden" id="i_no" name="i_no" value="${detail.i_no }">
		<div class="form-group">
			<label for="i_name">상품명</label> <input type="text"
				class="form-control" id="i_name" name="i_name"
				value="${detail.i_name }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="i_price">금액</label> <input type="text"
				class="form-control" id="i_price" name="i_price" readonly="readonly"
				value="${detail.i_price }">
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
			<label for="imgn">상품이미지</label>
			<div>
				<img style="width: 200px; padding: 10px;"
					src="${pageContext.request.contextPath}/resources/imagefile/${detail.i_img}"  alt="">
			</div>
		</div>

		<div class="form-group">
			<label for="imgn">재고 : ${detail.stockvo.s_stock }</label>
				<input type="number" id="num" name="num" max="${detail.stockvo.s_stock}" min="0" >
			</div>
		<div style="text-align: left;">
		<span style="float: left; margin-right: 5px;">총금액 :  </span> <div class="form-group" id="target1" > </div>
			<div>
			<table class="table">
				<thead>
				<tr>
				<th>No</th>
				<th>item</th>
				<th>writer</th>
				<th>comment</th>
				<th>score</th>
				<th>date</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="review" items="${list}">
					<tr>
					<c:forEach var="member" items="${review.mvo }">
					<td>${review.r_name }</td>
					<td>${review.r_num }</td>
					<td>${member.mem_name }</td>
					<td>${review.r_comm }</td>
					<td>${review.r_score }</td>
					<td>${review.r_date }</td>
					</c:forEach>
					</tr>					
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
		<div class="form-group" style="text-align: right; margin-top: 10px;">
			<button type="submit" class="btn btn-primary" id="buybtn">구매</button>
			<button type="submit" class="btn btn-primary" id="basketbtn">장바구니</button>
		</div>
		
		</form>
		</div>
		<c:choose>
		<c:when test="${detail.i_gender=='여성' }">
		<div class="form-group" style="text-align: right; margin-top: 10px;">
			<button type="button" class="btn btn-primary" onclick="location.href='itemList?gender=w'">목록으로</button>
		</div>
		</c:when>
		<c:when test="${detail.i_gender=='남성' }">
		<div class="form-group" style="text-align: right; margin-top: 10px;">
			<button type="button" class="btn btn-primary" onclick="location.href='itemList?gender=m'">목록으로</button>
		</div>
		</c:when>
</c:choose>
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
		         console.log(total);
		         $('#target1').html(' '+total.toLocaleString('ko-KR')+ "원");
		      }
		   });
		      });
		      <%--장바구니로 이동  --%>
		      $('#basketbtn').click(function() {
		    	 	 console.log("아이디 = ${sessionScope.sessionId}");
		    	 if (${sessionScope.sessionId == null}) {
		    		 alert('로그인후 진행해 주세요~!!');
		    		 $('#purchase').attr('action', "${mycontext}/web/main");
				}else {
			         $('#purchase').submit(function() {
			        	 alert('장바구니에 담겼습니다!');
				     $('#purchase').attr('action', "${mycontext}/web/basket/addBasket");
			         });
		      }
		      });
		      <%--구매페이지로 이동  --%>
		      $('#buybtn').click(function() {
		    	  if (${sessionScope.sessionId == null}) {
		    		  alert('로그인후 진행해 주세요~!!');
		    		  $('#purchase').attr('action', "${mycontext}/web/main");
					}else {
				         $('#purchase').submit(function() {
				            alert('구매페이지로 이동합니다');
						 $('#purchase').attr('action', "${mycontext}/web/orders/order");
				         }); 
				
		      }
		      });
	});
</script>