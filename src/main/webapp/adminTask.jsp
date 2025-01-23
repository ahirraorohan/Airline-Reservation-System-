<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<center>
	<% String userName = (String) session.getAttribute("userKey"); %>
	<h3> <font color="blue">Welcome</font> <font color="green"><%=userName %>!!</font> </h3>
	<body bgcolor="pink"> 
		<a href="AddFlight.jsp">Add Flight</a><br>
		<a href="viewFlight.jsp">View Flight</a><br>
	</body>
</center>
</html>