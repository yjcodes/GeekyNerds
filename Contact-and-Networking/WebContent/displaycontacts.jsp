<%@ page language="java" import="java.util.*,com.demo.bean.User,com.demo.bean.ContactDetails,java.io.*,javax.imageio.*" contentType="text/html; charset=ISO-8859-1"
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
  <table>
  
  <td>
<table>

<tr>
<td>
<form action="SearchServlet" method="get">
Search By Email: <input type="text" name="prodName" id="name"  >
            
<button type="submit" name="btn" id="sub" value="search">Search</button>

<!-- <a href="SearchServlet?name=name1">Search</a> -->
</form>
</td>

<td>

</td>
</tr>
</table>
</td>
<td>
<form action="filter">
<td>Filter : </td>
  <td><select name="filter">
     <option value="city">Filter By City  </option>
       
      <td>
     <input type="text" name="filter1" id="filter2"  >
     <button type="submit" name="btn" id="sub1" value="search">Search</button>
      </td>
     
    
    
   </select></td> <td></td>
 </form>

</td>


</table>


 
 <form action="delete">
 <%
 	List<User> ulist=(ArrayList<User>)request.getAttribute("ulist");
 	
      
      List<ContactDetails> clist=(ArrayList<ContactDetails>)request.getAttribute("clist");
 %>
<div class="table-responsive-md">
   <table class="table table-hover" border="2">
       <thead class="thead-dark">
 

 <tr><th>Contact Image</th><th>User Email</th><th>User Name</th><th>User Mob</th><th>User City</th><th>Edit</th><th><button type="submit" class="btn btn-light btn-sm" name="btn" id="sub2" value="delete">Delete</button></th></tr>
       </thead>
   
 <%for(User u:ulist) { 
	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
     ImageIO.write( u.getUimage(), "jpg", baos );
     baos.flush();
     byte[] imageInByteArray = baos.toByteArray();
     baos.close();
     String ub64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);

 
 %>   
 <tr><td><img src="data:image/jpg;base64,<%=ub64%>" width="100" height="100"/></td>
  <td><%=u.getUemail() %></td>
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

<%for(ContactDetails c:clist) {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write( c.getCimage(), "jpg", baos );
    baos.flush();
    byte[] imageInByteArray = baos.toByteArray();
    baos.close();
    String cb64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);


%>   
<tr><td><img src="data:image/jpg;base64,<%=cb64%>" width="100" height="100"/></td>
  <td><%=c.getCemail() %></td>
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

 </table>
 </div>
 </form>
<a class="btn btn-primary" role="button" href="addnewcontact.html">Add Contact</a>
</body>
</html>