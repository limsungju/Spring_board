<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="../default/header.jsp"/>
<div align="center">
<table border="1">
	<caption><font size='6'>게시판</font></caption>
	<tr>
		<th>번호</th><th>이름</th><th>제목</th><th>날짜</th><th>조회수</th>
		<th>group</th><th>step</th><th>indent</th>
	</tr>
	<c:forEach items="${list}" var="dto">
	<tr>
		<td>${dto.id}</td><td>${dto.name}</td>
		<td>
		<c:forEach begin="1" end="${dto.indent }">-></c:forEach>
		<a href="content_view?id=${dto.id }">${dto.title}</a></td>
		<td>${dto.savedate }</td><td>${dto.hit }</td><td>${dto.idgroup }</td>
		<td>${dto.step }</td><td>${dto.indent }</td>
	</tr>
	</c:forEach>
	<tr><td colspan="8"><a href ="write_view">글작성</a></td></tr>
</table>
</div>
<c:import url="../default/footer.jsp"/>
</body>
</html>