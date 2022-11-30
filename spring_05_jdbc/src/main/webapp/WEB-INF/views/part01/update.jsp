<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="frm" action="update.do" method="post">
	<input type="hidden" name="num" value="${dto.num}"/>
	<p><span>이름</span><input type="text" name="name" value="${dto.name}"/></p>
	<p><span>나이</span><input type="text" name="age" value="${dto.age}"/></p>
	<p><span>지역</span><input type="text" name="loc" value="${dto.loc}"/></p>
	<p><input type="submit" value="commit"/></p>
	</form>
</body>
</html>