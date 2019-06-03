<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../default/header.jsp"/>
	<div align="center">
		<c:if test="${memberData != null }">
			<table border="0" style="width:300px;">
			<caption><h1>${memberData.id } 정 보</h1></caption>
				<tr><th>아이디</th><th>${memberData.id }</th></tr>
				<tr><th>비밀번호</th><th>${memberData.pw }</th></tr>
				
			</table>
		</c:if>
	</div>
	<c:import url="../default/footer.jsp"/>
</body>
</html>
