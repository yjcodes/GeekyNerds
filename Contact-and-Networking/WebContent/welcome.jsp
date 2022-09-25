<%@ page language="java"
	import="com.demo.bean.User,java.util.*,java.io.*,javax.imageio.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Autour One'
	rel='stylesheet'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/front.css">
<title>Welcome User</title>
<%
	User user = (User) session.getAttribute("userdetails");
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(user.getUimage(), "jpg", baos);
	baos.flush();
	byte[] imageInByteArray = baos.toByteArray();
	baos.close();
	String ub64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
%>
</head>
<body>


	<!-- Navbar Element Start-->
	<nav class="navbar navbar-dark bg-dark">
		<!-- Navbar content -->
		<a class="navbar-brand" href="#"><b>Welcome <%=user.getUfullName()%></b></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item"><a class="nav-link" href="getrequest">Friend
						Requests</a></li>

				<li class="nav-item"><a class="nav-link"
					href="addnewcontact.html">Add new Contact</a></li>

				<li class="nav-item"><a class="nav-link" href="viewcontact">View
						Contacts</a></li>

				<li class="nav-item"><a class="nav-link"
					href="friendsearch.jsp">Search Friend</a></li>

				<li class="nav-item"><a class="nav-link" href="UserInfo.jsp">Search
						User</a></li>

				<li class="nav-item"><a class="nav-link"
					href="BlockedUserListServlet">Blocked Users</a></li>

				<li class="nav-item"><a class="nav-link" href="logout">Logout</a>
				</li>

			</ul>
		</div>
	</nav>
	<!-- Navbar Element End-->

	<!-- User Info Start-->
	<br>
	<br>

	<div class="container bg-dark text-white text-left">
		<br>
		<h2>
			<i></i>&nbsp;<img src="data:image/jpg;base64,<%=ub64%>" width="100"
				height="100" />
		</h2>
		<br>
		<h4>
			<b>Full Name: </b><%=user.getUfullName()%></h4>
		<h4>
			<b>Email: </b><%=user.getUemail()%></h4>
		<h4>
			<b>User Handle: </b><%=user.getUname()%></h4>
		<h4>
			<b>Mobile Number: </b><%=user.getUmob()%></h4>
		<h4>
			<b>Gender: </b><%=user.getUgender()%></h4>
		<h4>
			<b>BirthDate: </b><%=user.getUbdate()%></h4>
		<h4>
			<b>City: </b><%=user.getUcity()%></h4>
		<h4>
			<b>State: </b><%=user.getUstate()%></h4>
		<h4>
			<b>Country: </b><%=user.getUcountry()%></h4>
		<h4>
			<b>Company: </b><%=user.getUcompany()%></h4>
		<br />
		<br />
	</div>
	<!-- User Info End -->
</body>
</html>