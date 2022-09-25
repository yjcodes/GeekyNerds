<%@ page language="java" import="java.util.*, com.demo.bean.User" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Autour One' rel='stylesheet'>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/front.css"> 
<title>Insert title here</title>
</head>
<body>
	<% List<String> ulist = (ArrayList<String>)request.getAttribute("ulist"); 
		if(ulist.isEmpty())
			System.out.println("Ulist empty");
	%>
	<div class="table-responsive-md">
      <table class="table table-hover" border="2">
         <thead class="thead-dark">
 
		<tr><th>User email</th><th>Action</th></tr>
		</thead>
		<%for(String u : ulist) { %>
			
			<tr>
				<td><%=u %></td>
				<td>
					<a href="UnblockServlet?uemail=<%=u %>">Unblock user</a>
				</td>
			 </tr>
		<% } %>
	</table>
  </div>
	<br>
</body>
</html>