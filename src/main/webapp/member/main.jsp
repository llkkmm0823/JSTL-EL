<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
</head>
<body>
<%
if(session.getAttribute("loginUser")==null)
	response.sendRedirect("login.do"); // 바로 페이지로 갈 경우 경로가 노출됨_항상 서블릿 거치기
%>
<%-- <c:if test="${empty loginUser}">
	<%response.sendRedirect("login.do"); %>
</c:if> 
라이브러리 추가한 후 이렇게 작성해도 무방--%>


<table>
	<tr><td>${loginUser.userid}(${loginUser.name})님이 로그인 하셨습니다.</td></tr>
	<tr><td>email : ${loginUser.email}</td></tr>
	<tr><td>전화번호 : ${loginUser.phone}</td></tr>
	<tr><td> <input type="button" value="로그아웃" onClick="location.href='logout.do'"/>
	<!-- 
	어떤 태그이든 onClick속성을 써서 페이지 이동을 하고자 한다면, 
	위와 같이 location.href를 onClick속성에 지정하여 이동  
	onClick속성에는 페이지 이동 기능이 없기 때문에 페이지만 쓴다고 이동하지 않음. 반드시 location.href로 페이지를 지정
	-->
</table>
</body>
</html>