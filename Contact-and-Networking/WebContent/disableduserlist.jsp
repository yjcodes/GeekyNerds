<%@ page language="java" import="java.util.*,com.demo.bean.User" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Disable USer List</title>
</head>
<body>
 
<% List<User> ulist=(ArrayList<User>)request.getAttribute("ulist");%>
 
<form action="enable">
<table border="2">

<tr><th>User Name</th><th>User Fullname</th><th>User City</th><th><button type="submit" name="btn" id="sub2" value="enable">Enable</button></th></tr>
<%for(User u:ulist) {%>   
	<tr>
		<td><%=u.getUname() %></td>
        <td><%=u.getUfullName() %></td>
        <td><%=u.getUcity() %></td>
        <td>
          <input type="checkbox" name="enable"  value="<%=u.getUname() %>" >Enable<br>
    	</td>
    </tr>
<%} %>

</table>
</form>
</body>
</html>