<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>122_ParameterTo.jsp</title>
</head>
<body>
<%request.setCharacterEncoding("UTF-8"); %>
당신이 입력한 정보입니다(고전적 방식). <hr>
아이디 : <%=request.getParameter("id") %><br>
비밀번호 : <%=request.getParameter("pwd") %><br>
선택한 아이템 :
<%
	String [] item = request.getParameterValues("item");
	for(String s : item)
		out.print(s+" ,");
%>


<br><br>
당신이 입력한 정보입니다(EL 방식). <hr>
아이디 : ${param.id}<br>
비밀번호 : ${param["pwd"]}<br>
선택한 아이템 :
<c:forEach var="item" items="${paramValues.item}" varStatus="status">
	${item} <c:if test="${not status.last}">,</c:if>
</c:forEach><br><br>
<%--체크박스의 다중 선택 value들이 파라미터로 전달되어 EL로 수신하는 키워드 : ${paramValues.item} --%>


</body>
</html>
