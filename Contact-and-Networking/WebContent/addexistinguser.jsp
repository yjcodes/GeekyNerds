<%@ page language="java" import="com.demo.bean.User,java.util.*,java.io.*,javax.imageio.*" contentType="text/html; charset=ISO-8859-1"
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

	<% User user=(User) request.getAttribute("user"); 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write( user.getUimage(), "jpg", baos );
	    baos.flush();
	    byte[] imageInByteArray = baos.toByteArray();
	    baos.close();
	    String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
	%>
	<%--redirect to user homepage --%>
	<div class="container">
	<form action="addexistingtocontact" method="post">
		<table>
		
			<tr><td>Name: </td><td><input class="form-control" type="text" name="name" value="<%=user.getUname() %>" readonly></td></tr>
			<tr><td>Email: </td><td><input class="form-control" type="text" name="email" value="<%=user.getUemail() %>" readonly></td></tr>
			<tr><td>Phone: </td><td><input class="form-control" type="text" name="phone" value="<%=user.getUmob() %>" readonly></td></tr>
			<tr><td>Gender: </td><td><input class="form-control" type="text" name="gender" value="<%=user.getUgender() %>" readonly></td></tr>
			<tr><td>Date of Birth: </td><td><input class="form-control"  type="text" name="bdate" value="<%=user.getUbdate() %>" readonly></td></tr>
			<tr><td>Address: </td><td><input class="form-control" type="text" name="address" value="<%=user.getUaddress() %>" readonly></td></tr>
			<tr><td>Country: </td><td><input class="form-control" type="text" name="country" value="<%=user.getUcountry() %>" readonly></td></tr>
			<tr><td>State: </td><td><input class="form-control" type="text" name="state" value="<%=user.getUstate() %>" readonly></td></tr>
			<tr><td>City: </td><td><input class="form-control" type="text" name="city" value="<%=user.getUcity() %>" readonly></td></tr>
			<tr><td>Company: </td><td><input class="form-control" type="text" name="company" value="<%=user.getUcompany() %>" readonly></td></tr>
			<tr><td>Image: </td><td><input class="form-control" type="text" name="bimage" value="<%=b64%>" hidden></td></tr>
			<%-- <tr><td>Image: <img src="data:image/jpg;base64,<%=b64%>" width="100" height="100" name="image"/></td></tr> --%>
			<tr><td><button class="btn btn-dark btn-sm" type="submit" name="btn" value="Add contact">Add Contact</button></td>
			<td><button class="btn btn-dark btn-sm" type="reset" name="btn" value="Cancel">Cancel</button></td>
		</table>
	</form>
    </div>
</body>
</html>