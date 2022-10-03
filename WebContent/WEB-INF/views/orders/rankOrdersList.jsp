<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<style>
	a:link{text-decoration: none;}
	table img { width: 325px; height: 325px;}
		/* paging */
	th {text-align: center; font-size: 30px;}
	
</style>

<article>
<header>
	<h1>Best Item 3</h1>
</header>
<table>
  <thead>
  	<tr>
  	  <th>Best1</th>
  	  <th>Best2</th>
  	  <th>Best3</th>
  	</tr>
  </thead>
  <tbody>
	<tr>
      <c:forEach var="e" items="${list }">
	  <%--i.i_name, i.i_no, ordTotalCnt, i.i_img --%>
		  <td><a href="${mycontext }/web/item/itemDetail?s_no=${e.ivo.i_no }"><img src="${mycontext}/resources/imagefile/${e.ivo.i_img }"></a></td>
      </c:forEach>
	</tr>    
  </tbody>
</table>
</article>

