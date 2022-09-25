<%@ page language="java" import="com.demo.bean.AdminDetails" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Admin</title>
<%AdminDetails admin=(AdminDetails)session.getAttribute("admin details"); %>
</head>
<body>
<h2>Welcome <%=admin.getAname()%></h2>
</body>
</html>