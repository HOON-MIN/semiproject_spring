<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}" />

<article>
	<header>
		<h1>��ٱ��� �� ������</h1>

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
				<label for="b_num">��ȣ</label> <input type="text"
					class="form-control" id="b_num" name="b_num"
					value="${basketdetail.b_num}" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="i_name">��ǰ �̸�</label> <input type="text"
					class="form-control" id="i_name" name="i_name" readonly="readonly"
					value="${basketdetail.itemvo.i_name}">
			</div>
			<div class="form-group">
				<label for="i_price">�ܰ�</label> <input type="text"
					class="form-control" id="i_price" name="i_price"
					value="${basketdetail.itemvo.i_price}" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="totalPrice">�� �ݾ�</label> <input type="text"
					class="form-control" id="totalPrice" name="totalPrice"
					value="${basketdetail.ordersvo.totalPrice}" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="b_stock">��ǰ ����</label> <input type="text"
					class="form-control" id="b_stock" name="b_stock"
					value="${basketdetail.b_stock}">
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary" id="updatebtn">����</button>
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary" id="delbtn">����</button>
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary" id="buybtn">�ֹ���������</button>
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
							alert("�����Ǿ����ϴ�.");
						});
					});

			$('#delbtn').click(
					function() {
						$('#basketupdate').attr("action",
								"${mycontext}/web/basket/basketdel");
						$('#basketupdate').submit(function() {
							alert("�����Ǿ����ϴ�.");
						});
					});
			$('#buybtn').click(
					function() {
						$('#basketupdate').attr("action", "${mycontext}/web/basket/basketorder");
						$('#basketupdate').submit(function() {
						alert('������������ �̵��մϴ�');
			});
		});
	});
});
</script>