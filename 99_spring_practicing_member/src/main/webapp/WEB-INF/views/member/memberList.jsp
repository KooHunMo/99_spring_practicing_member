<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<div>
		<input type="button" value="Excel Export" onclick="location.href='${contextPath}/member/memberExcelExport'" />
		<input type="button" value="메인으로" onclick="location.href='${contextPath}/member/main'" />
	</div>
	<h3>회원 리스트</h3>
	<table border="1">
	 <tbody align="center" >
	  <tr>
		<td>회원아이디</td>
		<td>회원이름</td>
		<td>휴대폰번호</td>
		<td>주소</td>
		<td>가입일</td>
	   </tr>
	   <c:choose>
	     <c:when test="${empty memberList}">			
			<tr>
		       <td colspan=5 >
				  <strong>조회된 회원이 없습니다.</strong>
			   </td>
		     </tr>
		 </c:when>
		 <c:otherwise>
		     <c:forEach var="memberDto" items="${memberList}">
	            <tr>       
					<td>
						<a href="${contextPath}/member/memberDetail?memberId=${memberDto.memberId}">
					      <strong>${memberDto.memberId}</strong>
						</a>
					</td>
					<td>
				      <a href="${contextPath}/member/memberDetail?memberId=${memberDto.memberId}">
					    <strong>${memberDto.memberName}</strong><br>
					  </a>
					</td>
					<td>
					  <strong>${memberDto.hp1}-${memberDto.hp2}-${memberDto.hp3}</strong><br>
					</td>
					<td>
					  <strong>${memberDto.roadAddress}</strong><br>
					  <strong>${memberDto.jibunAddress}</strong><br>
					  <strong>${memberDto.namujiAddress}</strong><br>
					</td>
					<td>
					  <strong><fmt:formatDate value="${memberDto.joinDate}" pattern="yyyy-MM-dd"/> </strong>
				    </td>
				</tr>
				</c:forEach>
			</c:otherwise>
		  </c:choose>	
	 </tbody>
	</table>
</body>
</html>

