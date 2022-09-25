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
	<h1>Taking new contact(not user) details</h1>
	<%String cname=(String) request.getAttribute("cname"); %>
	<%String cemail=(String) request.getAttribute("cemail"); %>
	
	<form action="addcontact" method="post">
		<table>
			<tr><td>Name: </td><td><input class="form-control" type="text" name="cname" value="<%=cname %>" readonly></td></tr>
			<tr><td>Email: </td><td><input class="form-control" type="text" name="cemail" value="<%=cemail %>" readonly></td></tr>
			<tr><td>Phone number: &nbsp</td><td><input class="form-control" type="number" minlength="10" maxlength="10" name="num" id="phone" placeholder="Enter phone number" autofocus></td></tr>
			<tr>
		    <td>Gender: </td>
			<td><input type="radio" name="gender" value="female">Female &nbsp
		    <input type="radio" name="gender" value="male">Male &nbsp
			<input type="radio" name="gender" value="other">Other</td>
			</tr>
			<tr><td>Date of Birth:</td><td><input class="form-control" type="date" name="date" id="date" placeholder="Enter birth date"></td></tr>
			<tr><td>Address: </td><td><input class="form-control" type="text" name="address" id="addr" placeholder="Enter address"></td></tr>
			<tr><td>Country: </td>
			<td><select class="form-control" name="country">
			<option value="india">India</option>
			<option value="canada">Canada</option>
			</select>
			</td>
			</tr>
			<tr>
			<td>State: </td>
			<td><select class="form-control" name="state">
			<option value="maha">Maharashtra</option>
			<option value="mp">MadhyaPradesh</option>
			</select></td>
			</tr>
			<tr>
			<td>City: </td>
			<td><select class="form-control" name="city">
			<option value="pune">Pune</option>
			<option value="mumbai">Mumbai</option>
			</select></td></tr>
			<tr><td>Company: </td><td><input class="form-control" type="text" name="company" id="company" placeholder="Enter company"></td></tr>
			<tr><td>Contact image:</td><td> <input class="form-control" type="file" name="image"  id="image" accept="image/*" placeholder="Image"></td></tr>
			<tr><td><button class="btn btn-dark btn-sm"  type="submit" name="btn" value="Add Contact">Add Contact</button></td>
			<td><button class="btn btn-dark btn-sm"  type="reset" name="btn" value="Cancel">Cancel</button></td></tr>
		</table>
	</form>
      <div>
</body>
</html>