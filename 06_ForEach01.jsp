<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06_ForEach.jsp</title>
</head>
<body>

	<%
	String[] movieList = { "타이타닉", "시네마 천국", "혹성 탈출", "킹콩" };
	request.setAttribute("mList", movieList);
	//Attribute는 해쉬맵 형식의 리스트이므로 키 값만 존재하면 어떤 자료들도 보관할 수 있음
	//ArrayList등도 위의 배열과 같이 손쉽게 보관하거나 전달할 수 있음
	%>

	<!--저장되었던 배열을 JSP문법으로 꺼내서 출력한다면  -->
	<%
	String[] mList = (String[]) request.getAttribute("mList");
	for (String s : mList)
		out.print("<h2>" + s + "</h2>");
	%>
	<br />
	<br />

	<!--EL과 JSTL을 이용한 반복실행문 - 배열의 요소를 이용  -->
	<c:forEach items="${mList}" var="movie">
		<h2>${movie}</h2>
	</c:forEach>

	<!--
	items="" : 반복실행에 이용할 리스트 또는 배열을 지정
	var ="movie" : 배열 또는 리스트의 값을 한 번에 하나씩 저장할 때 사용할 변수 이름
  -->




</body>
</html>
