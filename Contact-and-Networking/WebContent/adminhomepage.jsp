<%@ page language="java" import="com.demo.bean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%AdminDetails admin=(AdminDetails)session.getAttribute("admindetails"); %>
</head>
<body>
<h2>Admin Info: </h2>
	<form>
        Admin Name : <%=admin.getAname()%>
		Admin Email : <%=admin.getAemail()%>
   		Admin Mobile : <%=admin.getAmob()%>
    </form>



<a href="disableduserlist">Disable History</a>
<a href="disableuser">Disable User</a>
<a href="processSummary">User Summary</a>

</body>
</html>