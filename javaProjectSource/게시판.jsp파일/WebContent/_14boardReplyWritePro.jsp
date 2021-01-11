<%@page import="com.green.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% request.setCharacterEncoding("UTF-8"); %>

	<%
		int num = Integer.parseInt(request.getParameter("num"));
	%>
	
	<jsp:useBean id="board" class="com.green.board.BoardDTO">
		<jsp:setProperty name="board" property="*" />
	</jsp:useBean>
	
	<%
		BoardDAO.getInstance().boardReplyWrite(num, board);
		response.sendRedirect("_04boardList.jsp");
	%>
	
</body>
</html>