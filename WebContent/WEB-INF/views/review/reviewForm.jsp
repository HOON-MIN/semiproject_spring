<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<article>
	<header>
		<h1>후기 작성 Form</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<%--memberForm 들어 갈 자리. --%>
		<form action="reviewIn" method="post">
		<input type="hidden" name="ord_no" id="ord_no" value="${vo.ord_no }">
			<%-- 사용자의 아이피를 받아와서 보이지 않는 속성으로 reip 란 파라미터로 지정한다. --%>
			<%--상품번호, 제목, 내용, 점수 --%>
			<div class="form-group">
				<label for="i_no">상품번호</label> 
				<input type="text" class="form-control"
					id="i_no" name="i_no" value="${vo.i_no }" required="required" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="r_name">제목</label> 
				<input type="text" class="form-control"
					id="r_name" placeholder="후기 제목을 입력하세요." value="${vo.r_name }" name="r_name" required="required">
			</div>
			<div class="form-group">
				<label for=r_comm>내용</label> 
				<input type="text" class="form-control"
					id="r_comm" name="r_comm" value="${vo.r_comm }" required="required">
			</div>
			<div class="form-group">
				<label for="r_score">점수</label> 
				<input type="number" class="form-control"
					id="r_score" value="${vo.r_score }" 
					name="r_score" max="5" min="0" step="1" required="required">
			</div>

			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>
</article>







