<%@ page language="java" import="com.demo.bean.User,java.text.SimpleDateFormat" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		User u=(User)request.getAttribute("User Details");
	%>
	<h3>City:</h3> <%=u.getUimage() %>
</body>
</html>