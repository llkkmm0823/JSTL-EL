<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05_When.jsp</title>
</head>
<body>
	<c:if test="${ param.fruit==1 }"></c:if>

	<c:choose>
		<c:when test="${param.fruit==1}">
			<!-- if와 같은 의미로 사용 -->
			<span style="color: red; font-size: 180%; font-weight: bold;">사과</span>
		</c:when>

		<c:when test="${param.fruit==2}">
			<!-- else if와 같은 의미로 사용 -->
			<span style="color: green; font-size: 180%; font-weight: bold;">멜론</span>
		</c:when>

		<c:when test="${param.fruit==3}">
			<!-- else if -->
			<span style="color: yellow; font-size: 180%; font-weight: bold;">바나나</span>
		</c:when>

		<c:otherwise>
			<!-- else와 같은 의미로 사용 -->
			<span style="color: silver; font-size: 180%; font-weight: bold;">기타</span>
		</c:otherwise>

	</c:choose>


</body>
</html>
