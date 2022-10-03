<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<article>
<script>

</script>
	<header>
		<h1>adminItemStock Demo</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<%-- itemstockForm 들어갈 자리 --%>
		<form action="adminitemstockIn" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="i_name">상품명</label> <input type="text" class="form-control"
					id="i_name" placeholder="상품 입력(1-20)" name="i_name" maxlength="100"
					required="required" pattern=".{1,20}">
				<div class="form-group">
				<button type="button" class="btn btn-primary" id="isbtn1" >중복확인</button>
				</div>
				<div id="target"></div>
			</div>
			<div class="form-group">
				<label for="i_price">상품가격</label> 
				<input type="text" class="form-control"	id="i_price" name="i_price" required="required" placeholder="가격을 입력하세요">
			</div>
            <div class="form-group">
            <label for="i_category">카테고리1</label>
            <input class="form-check-infut" type="radio" name="i_category" id="i_category1" value="상의">
            <label class="form-check=label" for="gender1">상의</label>
            <input class="form-check-infut" type="radio" name="i_category" id=i_category2 value="하의">
            <label class="form-check=label" for="gender2">하의</label>
            <input class="form-check-infut" type="radio" name="i_category" id="i_category3" value="잡화">
            <label class="form-check=label" for="gender2">잡화</label>            
            </div>

			<div class="form-group">
            <label for="i_category">카테고리2</label>
            <input class="form-check-infut" type="radio" name="i_gender" id="i_gender1" value="남성">
            <label class="form-check=label" for="i_gender1">남성</label>
            <input class="form-check-infut" type="radio" name="i_gender" id=i_gender2 value="여성">
            <label class="form-check=label" for="i_gender2">여성</label>
            <input class="form-check-infut" type="radio" name="i_gender" id="i_gender3" value="공용">
            <label class="form-check=label" for="i_gender3">공용</label>            
            </div>
            <div class="form-group">
			<label for="age">상품설명</label> 
				<textarea class="form-control" rows="5" id="i_comm" name="i_comm"
					placeholder="상품설명"></textarea>
			</div>
			<div class="form-group">
				<label for="i_stock">재고수량</label> 
				<input type="text" class="form-control"	id="i_stock" name="i_stock" required="required" placeholder="수량을 입력하세요">
			</div>
			<div class="form-group">
				<label for="s_name">제조사</label> 
				<input type="text" class="form-control"	id="s_name" name="s_name" required="required" placeholder="제조사을 입력하세요">
			</div>
			<div class="form-group">
				<label for="s_phone">제조사번호</label> 
				<input type="text" class="form-control"	id="s_phone" name="s_phone" required="required" placeholder="제조사 번호를 입력하세요">
			</div>			
            <div class="row mb-3">
				<label>*이미지 업로드</label>
			<input type="file" name="mfile" id="mfile"/>
			<img style="width: 50%;" src="${pageContext.request.contextPath}/resources/image/noimage.jpg" id="imgx">
			</div>
						
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-outline-secondary">등록</button>
			</div>
		</form>
	</div>

</article>
<script>
	<%-- 스크립트 영역--%>
	<%-- 이미지 미리보기--%>
	function readURL(input) {
		if (input.files && input.files[0]) {
			// 자바스크립트 I/O 
			var reader = new FileReader();
			//
			reader.onload = function(e) {
				//e.target.result
				console.log("Path :" + e.target.result);
				$('#imgx').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
		$(function() {
			$('#mfile').change(function() {
				console.log($(this).val());
				readURL(this);
		});
			$('#isbtn1').click(function() {
				let param = $('#i_name').val();
				$.ajax({
					url:"itemNameChk?i_name="+param,
				success:function(data){
					console.log(data);
					if (data == 1) {
					$('#target').css('background-color', 'red').css('color','white').html('존재하는 상품명');
					}else {
					$('#target').css('background-color', 'blue').css('color','white').html('사용가능한 상품명입니다!');
					}
				}
				});
			});
		});
</script>
