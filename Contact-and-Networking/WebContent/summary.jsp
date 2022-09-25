<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Summary</title>
</head>
<body>
	<label>Name : </label>                 <!-- /* Admin details which it gets from session*/ -->
	<%
		String str=(String) request.getAttribute("admName");
		if(str!=null)
		out.println(str);
	%>
	<br>
	<label>Email : </label>
	<%
	str=(String) request.getAttribute("admEmail");
	if(str!=null)
	out.println(str);
	%>
	<br>
	<label>Mob : </label>
	<%
	str=(String) request.getAttribute("admMob");                 
	if(str!=null)
	out.println(str);
	%>
	<br>
	
	<label>Number of total users : </label>
	<%
	int noOfUsers=(Integer) request.getAttribute("totUsers");                /*  <!-- Gives total number of users registered --> */
	if(noOfUsers!=0)
	out.println(noOfUsers);
	%>
	
	<br>
	<label>Total Number of different cities from which the users are based on : </label>  <!--  Gives the number of different cities the users are from  -->
	<%
	int noOfCities=(Integer) request.getAttribute("totCities");
	if(noOfCities!=0)
	out.println(noOfCities);
	%>
	<br><br>
	
	<a href="adminhomepage.jsp" >Back</a>
	


<!-- Button for redirecting to delete and disable page -->

</body>
</html>