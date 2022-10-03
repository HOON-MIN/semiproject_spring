<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<article >
        <header>
            <h1>Member Detail</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
   <div class="container">
<%-- Form 들어갈 자리 --%>   
   <fieldset>
         <legend>Member Detail Form</legend>
         <form method="post" action="memberUpdate" id="upForm" name="upForm" enctype="multipart/form-data" >
         <div class="row mb-3">
            <label>회원번호</label>
         <input type="text" name="mem_no" id="mem_no" value="${vo.mem_no }"/>
         </div>
         <div class="row mb-3">
            <label>아이디</label>
         <input type="text" name="mem_id" id="mem_id" value="${vo.mem_id }"/>
         <span id="target"></span>
         </div>
         <div class="row mb-3"><%--MEM_NO, MEM_ID, MEM_PW, MEM_NAME, 
  								 MEM_JUBUN, MEM_PHONE, MEM_ADR, MEM_DATE --%>
            <label>비밀번호</label>
         <input type="text" name="mem_pw" id="mem_pw" value="${vo.mem_pw }"/>
         </div>
         <div class="row mb-3">
            <label>이름</label>
         <input type="text" name="mem_name" id="mem_name" value="${vo.mem_name }"/>
         </div>
         <div class="row mb-3">
            <label>주민등록번호</label>
         <input type="text" name="mem_jubun" id="mem_jubun" value="${vo.mem_jubun }"/>
         </div>
         <div class="row mb-3">
            <label>전화번호</label>
         <input type="text" name="mem_phone" id="mem_phone" value="${vo.mem_phone }"/>
         </div>
         <div class="row mb-3">
            <label>주소</label>
         <input type="text" name="mem_adr" id="mem_adr" value="${vo.mem_adr }"/>
         </div>
         <div class="row mb-3">
            <label>가입날짜</label>
         <input type="text" name="mem_date" id="mem_date" value="${vo.mem_date }"/>
         </div>
         
                                             
         <div class="form-group" style="text-align: right; margin-top: 10px;" >
         <input type="button" value="수정" id="upbtn" class="btn btn-outline-secondary"/>
         <input type="button" value="삭제" id="delbtn" class="btn btn-outline-secondary"/>
         </div>
         </form>
   </fieldset>      
   </div>
</article>
<script>
<%-- 스크립트 영역--%>
$(function () {
	<%-- memberDetail 페이지에서 회원정보 수정 버튼 --%>
	$('#upbtn').click(function() {
		$('#upForm').submit();
	});

	<%-- memberDetail 페이지에서 회원정보 삭제 버튼 --%>
	$('#delbtn').click(function() {
		location = "memberDelete?num=${vo.mem_no}";
	});
	
});

</script>
