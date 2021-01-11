<%@page import="com.green.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 로드</title>
</head>
<body>
	<%
		BoardDAO.getInstance().realPath = application.getRealPath("/");
		BoardDAO.getInstance().fileLoad();
		
		response.sendRedirect("_04boardList.jsp");
	%>
</body>
</html>