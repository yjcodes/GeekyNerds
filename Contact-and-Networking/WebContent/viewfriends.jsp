<%@ page language="java" import="java.util.*,com.demo.bean.User,java.io.*,javax.imageio.*" contentType="text/html; charset=ISO-8859-1"
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
<form action="block_unfriend">
<%
	List<User> ulist=(ArrayList<User>)request.getAttribute("ulist");
%>
<div class="table-responsive-md">
<table class="table table-hover" border="2">
  <thead class="thead-dark">
   <tr><th>user name</th><th>user email</th><th>user gender</th><th>user city</th><th>user company</th> <th></th><th></th><th></th></tr>
  </thead>
<%
	for(User u:ulist){
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	       ImageIO.write( u.getUimage(), "jpg", baos );
	       baos.flush();
	       byte[] imageInByteArray = baos.toByteArray();
	       baos.close();
	       String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);

%>   
   <tr>
   <td>
          	  <img src="data:image/jpg;base64,<%=b64%>" width="100" height="100"/>
   </td>
   <td><%=u.getUfullName()%></td>
   <td><%=u.getUemail()%></td>
    <td><%=u.getUgender()%></td>
     <td><%=u.getUcity()%></td>
      <td><%=u.getUcompany()%></td>
      <td><button class="btn btn-dark btn-sm" type="submit" name="block" value="BLOCK">BLOCK</button><input type="hidden" name="sourceId" value="<%=u.getUemail()%>" /></td>
      <td><button class="btn btn-dark btn-sm" type="submit" name="unfriend" value="UNFRIEND">UNFRIEND</button></td>
   </tr>
<%} %>
</table>

</form>
</body>
</html>