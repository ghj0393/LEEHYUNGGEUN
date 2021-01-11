<%@page import="com.green.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>더미 파일 추가하기</title>
</head>
<body>
	<%
		BoardDAO.getInstance().setDummyData();
		response.sendRedirect("_04boardList.jsp");
	%>
</body>
</html>