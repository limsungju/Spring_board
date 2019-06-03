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
	<form action="write_save" method="post">
	    <table border="1">
			<tr>
				<td>이름 </td>
				<td><input type="text" name="name" size = "50"> </td>
			</tr>
			<tr>
				<td>제목 </td>
				<td><input type="text" name="title" size = "50"> </td>
			</tr>
			<tr>
				<td>내용 </td>
				<td><textarea name="content" rows="10" cols="52"></textarea> </td>
			</tr>
			<tr>
				<td colspan="2"> 
					<input type="submit" value="저장">&nbsp;&nbsp;
					<a href="list">목록보기</a>
				</td>
			</tr>
		
	    </table>
	</form>
	</div>
	<c:import url="../default/footer.jsp"/>
</body>
</html>