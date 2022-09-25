<%@ page language="java" import="com.demo.bean.User,com.demo.bean.ContactDetails,java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Autour One' rel='stylesheet'>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/front.css"> 
</head>
<body>


 <form action="delete">
 <%
 	List<ContactDetails> citylist=(ArrayList<ContactDetails>)request.getAttribute("CityDetails");
   List<User> ucity=(ArrayList<User>)request.getAttribute("UserDetailsCity");
   
   List<ContactDetails> ccity=(ArrayList<ContactDetails>)request.getAttribute("ContactDetailsCity");
   String owneremail=(String)request.getAttribute("owneremail");

 %>
 <br/></br> <br/></br>
<div class="table-responsive-md">
<table class="table table-hover" border="2">
  <thead class="thead-dark">
  <tr>
 <th>User Email</th>
 <th>User Name</th>
 <th>User Mob</th>
 <th>User City</th>
 <th>Edit</th>
 <th><button type="submit" class="btn btn-light btn-sm"  name="btn" id="sub2" value="delete">Delete</button></th>
 </tr>
 </thead>
  <tbody>
   <%for(ContactDetails c:ccity) {%>   
   <tr><td><%=c.getCemail() %></td>
   <td><%=c.getCfullName() %></td>
    <td><%=c.getCmob() %></td>
     <td><%=c.getCcity() %></td>
      
       <td>
         <a href="edit?cEmail=<%=c.getCemail()%>">edit</a>
      </td>
      <td>
         
          <input type="checkbox" name="delete"  value="<%=c.getCemail() %>" >Delete<br>
      </td>
     
   </tr>
<%} %>
<%for(User u:ucity) {%>   
  <tr><td><%=u.getUemail() %></td>
   <td><%=u.getUfullName() %></td>
    <td><%=u.getUmob() %></td>
     <td><%=u.getUcity() %></td>
      
       <td>
         <h5>User</h5>
      </td>
      <td>
         
          <input type="checkbox" name="delete"  value="<%=u.getUemail() %>" >Delete<br>
      </td>
     
   </tr>
  
<%} %>
</tbody>
</table>
</div>
</form>
<a class="btn btn-primary" role="button" href="viewcontact?owneremail=<%=owneremail %>">Go To Contact List</a>
</body>
</html>