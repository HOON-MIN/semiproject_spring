<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}" />
<%--  본문 시작  --%>
<article >
<div class="container mt-5">
	<div class="row">

		<div class="col">
			<h3 class="text-white mybgColor" id="colTitle"> Kosmo Web Page</h3>
		<%--	<p><c:if test="${today != null}"> 오늘의 날짜 : ${today}</c:if></p>--%>
		</div>
		<div class="col">


<nav style="background-color: #ffffff">
  <div class="nav nav-tabs" id="nav-tab" role="tablist">
    <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">Home</button>
    <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab" data-bs-target="#nav-profile" type="button" role="tab" aria-controls="nav-profile" aria-selected="false">Profile</button>
    <button class="nav-link" id="nav-contact-tab" data-bs-toggle="tab" data-bs-target="#nav-contact" type="button" role="tab" aria-controls="nav-contact" aria-selected="false">Notice</button>
  </div>
</nav>
<div class="tab-content" id="nav-tabContent">
  <div class="tab-pane fade show active" id="nav-home" 
  role="tabpanel" aria-labelledby="nav-home-tab">
  <table class="table mytable-condensed">
    <tbody>
      <tr>
        <td><a href="${mycontext}/web/login/logListView">로그리스트</a></td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr>
      <tr>
        <td>kosmo 화면 설계 UI 내용 입니다.</td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr>
      <tr>
        <td>kosmo 화면 설계 UI 내용 입니다.</td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr>
      <tr>
        <td>kosmo 화면 설계 UI 내용 입니다.</td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr> 
      <tr>
        <td>kosmo 화면 설계 UI 내용 입니다.</td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr>            
    </tbody>
  </table>
</div>
<div class="tab-pane fade" id="nav-profile" role="tabpanel" 
  aria-labelledby="nav-profile-tab">대통령실 관계자는 윤 대통령의 지시에 대해 "임대차 3법을 시행한 지 2년이 돼 가는데, 이런 시기에는 전세 시장의 불확실성이 높아질 수 있다"며 "임대차 3법이 2년이 되는 시기니까 이 점을 짚어보면 좋겠다는 차원"이라고 부연했다.
  
</div>
<div class="tab-pane fade" id="nav-contact" role="tabpanel" 
  aria-labelledby="nav-contact-tab">
  <table class="table mytable-condensed">
    <tbody>
      <tr>
        <td>kosmo 화면 설계 UI 내용 입니다.</td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr>
      <tr>
        <td>kosmo 화면 설계 UI 내용 입니다.</td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr>
      <tr>
        <td>kosmo 화면 설계 UI 내용 입니다.</td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr>
      <tr>
        <td>kosmo 화면 설계 UI 내용 입니다.</td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr> 
      <tr>
        <td>kosmo 화면 설계 UI 내용 입니다.</td>
        <td>관리자</td>
        <td>2022-06-21</td>
      </tr>            
    </tbody>
  </table>
</div>
</div>


		</div>
	</div>
</div>
 <%-- comment end --%>
    </article>
<%--  본문 끝  --%>