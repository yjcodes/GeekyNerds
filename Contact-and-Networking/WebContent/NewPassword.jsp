<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<div class="container">
<form action="NewPassword" method="post">
<table>
<h2>Create New Password</h2></h2>
<div class="form-group">
<tr>
<td><label for="newpass">Enter new Password:&nbsp &nbsp &nbsp</label></td>
<td><input class="form-control" type="text" name="pass"></td></tr>
</div>

<div class="form-group">
<tr>
<td><label for="confirmpass">Confirm Password:&nbsp</label></td>
<td><input class="form-control" type="text" name="cpass"></td></tr>
</div>
<tr><td><button class="btn btn-dark btn-sm" type="submit" name="btn" value="Submit">Submit</button></td></tr>
</table>
</form>
</div>
</body>
</html>