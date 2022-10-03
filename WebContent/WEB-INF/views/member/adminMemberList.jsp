<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<style>
	a:link{text-decoration: none;}
	table {text-align: center;}
	table img { width: 80px;}
		/* paging */
	
	table tfoot ol.paging {
		margin-left:30%;
	    list-style:none;
	}
	
	table tfoot ol.paging li {
	    float:left;
	    margin-right:8px;
	}
	
	table tfoot ol.paging li a {
	    display:block;
	    padding:3px 7px;
	    border:1px solid #00B3DC;
	    color:#2f313e;
	    font-weight:bold;
	}
	
	table tfoot ol.paging li a:hover {
	    background:#00B3DC;
	    color:white;
	    font-weight:bold;
	}
	
	.disable {
	    padding:3px 7px;
	    border:1px solid silver;
	    color:silver;
	}
	
	.now {
	   padding:3px 7px;
	    border:1px solid #ff4aa5;
	    background:#ff4aa5;
	    color:white;
	    font-weight:bold;
	}
</style>

<article >
	<script>
        console.log('startPage'+${startPage});
        console.log('endPage'+${endPage});
        console.log('nowPage'+${nowPage});
        console.log('pagePerBlock'+${pagePerBlock});
        console.log('pagePerBlock'+${totalPage});
	</script>
	
<header>
    <h1>Member List</h1>
</header>
<ul class="list-unstyled"><li class="border-top my-3"></li></ul>

<div>

<%-- 리팩토링 해야 함 --%>
<p  class="list-unstyled"><c:if test="${cnt != null}"> <c:if test="${cMsg != null}">${cMsg}</c:if>회원수 : ${cnt} / <c:if test="${sMsg != null}">검색ID : ${sMsg}</c:if></c:if></p>
<table class="table table-bordered">
    <thead>
      <tr>
<%-- MEM_NO, MEM_ID, MEM_PW, MEM_NAME, MEM_JUBUN,
 MEM_PHONE, MEM_ADR, MEM_DATE --%>
        <th>번호</th>
        <th>아이디</th>
        <th>이름</th>
        <th>주민등록번호</th>
        <th>전화번호</th>
        <th>주소</th>
        <th>가입날짜</th>
      </tr>
    </thead>
    
    <tbody>
    <%-- for start --%>
     <c:forEach var="e" items="${list }">
      <tr>
        <td>${e.r_num }</td>
        <td><a href="memberDetail?num=${e.mem_no }">${e.mem_id }</a></td>
        <td>${e.mem_name }</td>
        <td>${e.mem_jubun}</td>
        <td>${e.mem_phone }</td>
        <td>${e.mem_adr}</td>
        <td>${e.mem_date}</td>
      </tr>
     </c:forEach>
     <%-- for end --%> 
    </tbody>
    
<tfoot>
<%-- Start! --%>
<tr><td colspan="7" >
<ol class="paging" >
<c:choose>
<c:when test="${startPage < 6}">
<li class="disable">이전으로</li>
</c:when>
<c:otherwise>
<li><a href="adminMemberList?cPage=${startPage-1}&search=${search}">이전으로</a></li>
</c:otherwise>
</c:choose>

<c:forEach varStatus="i" begin="${startPage}" end="${endPage }" step="1" >
	<c:choose>
	<c:when test="${i.index == nowPage}">
	 <li class="now">${i.index}</li>
	</c:when>
	<c:otherwise>
	<li><a href="adminMemberList?cPage=${i.index}&search=${search}">${i.index}</a></li>
	</c:otherwise>
	</c:choose> 
</c:forEach>
<c:choose>
	<c:when test="${endPage >= totalPage }">
	<li class="disable">다음으로</li>
	</c:when>
	<c:when test="${totalPage > (nowPage+pagePerBlock)}">
	<li><li><a href="adminMemberList?cPage=${endPage+1 }&search=${search}">다음으로</a></li>
	</c:when>
	<c:otherwise>
     <li><li><a href="adminMemberList?cPage=${totalPage }&search=${search}">다음으로</a></li>
	</c:otherwise>
</c:choose>

</ol>
</td>
</tr>

<tr>
<td colspan="7" >
  <form action="adminMemberList" method="get">
	<input type="hidden" name="searchReset" value="1">
	<input class="form-control me-2" type="text" id="search" name="search" value="${search}" placeholder="Search.." style="width: 300px; float: left;">
	<button class="btn btn-outline-secondary" type="submit" id="searchBtn" style="width: 80px; float: left;">Search</button><br>
  </form><br>
</td>
</tr>
</tfoot>
</table>



<div>
    <h1>VIP Member</h1>
<table class="table table-bordered">
    <thead>
      <tr>
        <th>순위</th>
        <th>회원번호</th>
        <th>회원명</th>
        <th>총 구매 개수</th>
      </tr>
    </thead>
    
    <tbody>
     <c:forEach var="e" items="${listAop }">
      <tr>
        <td>${e.rn }</td>
        <td>${e.mem_no }</td>
        <td>${e.mvo.mem_name }</td>
        <td>${e.ordCnt }</td>
      </tr>
     </c:forEach>
    </tbody>
</table>
</div>
</div>
</article>







