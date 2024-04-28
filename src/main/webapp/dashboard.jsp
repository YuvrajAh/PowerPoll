<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.io.*" %>
<%      try{System.out.println(session.getAttribute("isAuthenticated"));
		String a =(String) session.getAttribute("isAuthenticated");
		if(a.equals("1")){
%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		<h1>This is the dashboard page;</h1>
<%
	}else{
			response.getWriter().append("failed please get back to login page and login: ").append(request.getContextPath()); 
			response.sendRedirect("login.jsp");
		}
		
	}catch(Exception ex){
		ex.printStackTrace();
		response.getWriter().append("failed please get back to login page and login: ").append(request.getContextPath());
		response.sendRedirect("login.jsp");
	}
%>
</body>
</html>