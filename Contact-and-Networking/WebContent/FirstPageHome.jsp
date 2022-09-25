<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
<meta charset="utf-8">
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
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/front.css">
</head>
<body>
	<!-- Navbar Element Start-->
	<nav class="navbar navbar-dark bg-dark">
		<!-- Navbar content -->
		<a class="navbar-brand" href="#"><b>ConnectME</b></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item"><a class="nav-link"
					href="registeruser.html">Register</a></li>

				<li class="nav-item"><a class="nav-link" href="adminindex.html">Login Admin</a></li>

				<li class="nav-item"><a class="nav-link" href="userindex.html">Login User</a></li>

			</ul>
		</div>

	</nav>
	<!-- Navbar Element End-->

	<!-- About Us Start-->
	<br>
	<br>
	<div class="container" style="max-width: 800px" id="about us">
		<blockquote class="blockquote text-center">
			<h1 class="text-center">
				<b>ConnectME</b>
			</h1>
			<footer class="blockquote-footer"
				style="font-size: 18px; color: #DC143C; text-align: center">
				<cite title="Source Title">In touch with tomorrow</cite>
			</footer>
		</blockquote>
		<p class="abtpara"
			style="font-size: 20px; color: #000000; text-align: justify;">
			ConnectME is a social platform which allows you to explore people all
			over the world, connect with new peoples and make new friends. Our
			platform allows you to add and manage your conatcts. To start
			connecting with new people just <B>Register</B> and <B>Login</B> for
			free.
		</p>
	</div>
	<!-- About us End -->

	<!--Our Services start-->

	<div class="container" id="service">
		<br>
		<h1 class="text-center">Features</h1>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<!-- <div class="card-deck">-->
					<div class="card bg-dark">
						<div class="card-body text-center">
							<i class="fa fa-envelope-open"
								style="font-size: 50px; color: white;"></i> <br /> <br />
							<h3 class="servhead">Security And Ease</h3>
							<p class="card-text" style="font-size: 17px; color: white;">ConnectME
								is free to use. Its gives secure sign-up and login feature which is
								easy to use. The security and privacy of user is at it's best.
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card bg-dark">
						<div class="card-body text-center">
							<i class="fa fa-handshake-o"
								style="font-size: 50px; color: white;"></i> <br /> <br />
							<h3 class="servhead">Maintain Contacts</h3>
							<p class="card-text" style="font-size: 17px; color: white;">ConnectME
								provides an easy way to manage your contacts. you can easily
								add, edit and delete your contacts.You can even block/unblock someone.
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card bg-dark">
						<div class="card-body text-center">
							<i class="fa fa-address-book-o"
								style="font-size: 50px; color: white;"></i> <br /> <br />
							<h3 class="servhead">Find Friends</h3>
							<p class="card-text" style="font-size: 17px; color: white;">ConnectME
								helps you to explore more and helps you to connect with new people
								around the globe. It helps you to develope a huge network.
						</div>
					</div>
				</div>
			</div>
			<br>
		</div>
	</div>
</body>
</html>