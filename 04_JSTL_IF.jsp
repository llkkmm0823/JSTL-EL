<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04_JSTL_IF.jsp</title>
</head>
<body>
<!-- c:if test 조건절에 true이면 /c:if 까지의 내용을 웹페이지에 적용 또는 표시하고
아니면 표시-적용없이 지나감. test 안의 조건식은 EL문법을 이용함  -->
<c:if test = "${param.color==1}">
	<span style ="color:red;font-size:180%;font-weight:bold;">빨강</span>
</c:if>

<c:if test = "${param.color==2}">
	<span style ="color:green;font-size:180%;font-weight:bold;">초록</span>
</c:if>

<c:if test = "${param.color==3}">
	<span style ="color:blue;font-size:180%;font-weight:bold;">파랑</span>
</c:if>

<!--taglib를 추가하지 않으면 jstl태그가 모두 무시되어 color에 아무 값을 넣지 않아도 빨강 초록 파랑이 모두 출력됨
span태그만 출력됨.
추가했을 경우엔 
http://localhost:8090/WEB07_JSP_SERVLET/05/04_JSTL_IF.jsp?color=3  
color 뒤에 설정해놓은 값을 넣었을 때만 span태그가 표시됨-->


</body>
</html>
