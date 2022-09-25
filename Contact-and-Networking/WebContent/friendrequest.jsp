<%@ page language="java" import="java.util.*,com.demo.bean.User,java.util.ArrayList,java.io.*,javax.imageio.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
<title>Accept Request</title>
</head>
<body>
<%
	ArrayList<User> ulist=(ArrayList<User>)request.getAttribute("ulist");
%>
<div>
<div class="table-responsive-md">
<table class="table table-hover" border="2">
  <thead class="thead-dark"></thead>
 
        <tbody>
        <form action="actionfriendrequest">
        <%
        	for(User user:ulist){
        		   ByteArrayOutputStream baos = new ByteArrayOutputStream();
			       ImageIO.write( user.getUimage(), "jpg", baos );
			       baos.flush();
			       byte[] imageInByteArray = baos.toByteArray();
			       baos.close();
			       String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
      	 %>
          <tr>
          <td>
          	  <img src="data:image/jpg;base64,<%=b64%>" width="100" height="100"/>
          </td>
          <td>
          <%=user.getUemail() %>
          	<input type="hidden" name="senderEmail" value="<%=user.getUemail()%>" />
          	<%=user.getUfullName() %>
          </td>
          <td>
             <button class="btn btn-dark btn-sm"  type="submit" name="actionbutton" value="accept">Accept </button>
	      </td>
          <td>
        	<button class="btn btn-dark btn-sm" type="submit" name="actionbutton" value="ignore" /> Ignore</button>
	      </td>
	      <td>
	        <button class="btn btn-dark btn-sm" type="submit" name="actionbutton" value="block" /> Block </button>
	      </td>
          </tr>
          <%} %>
         </form>
        </tbody>
      </table>
</div>
</body>
</html>