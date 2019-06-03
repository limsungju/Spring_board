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
<form action="modify" method="post">
<input type="hidden" name="id" value="${content_view.id}">
<table border="1">
	<tr><td>번호</td><td>${content_view.id}</td></tr>
	<tr><td>조회수</td><td>${content_view.hit}</td></tr>
	<tr>
	<td>이름</td><td><input type="text" name="name" value="${content_view.name}"></td>
	</tr>
	<tr>
	<td>제목</td><td><input type="text" name="title" value="${content_view.title}"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea rows="10" name="content" >${content_view.content}</textarea></td>
	</tr>
	<tr><td colspan="2">
		<input type="submit" value="수정"> &nbsp;&nbsp;
		<a href="list">목록보기</a> &nbsp;&nbsp;
		<a href="delete?id=${content_view.id }">삭제</a>&nbsp;&nbsp;
		<a href="reply_view?id=${content_view.id }">답변</a>
	</td></tr>
</table>
</form>
</div>
<c:import url="../default/footer.jsp"/>
</body>
</html>