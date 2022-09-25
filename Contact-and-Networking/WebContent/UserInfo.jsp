<%@ page language="java" import="java.io.*,javax.imageio.*,java.util.*" contentType="text/html; charset=ISO-8859-1"
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
<title>UserInfo</title>
<script>
function getData() {
	var xmlhttp = new XMLHttpRequest();
	var selectValue = document.getElementById("menu").value;
	var textValue = document.getElementById("textBox").value;
	url = "ViewUserServlet?check=" + selectValue + "&choice=" + textValue;
	addUrl = "AddFriendServlet?uemail=";
	blockUrl = "BlockUserServlet?uemail=";
	xmlhttp.open("GET", url, true);
	
	xmlhttp.onreadystatechange = function() {
		
		if(xmlhttp.readyState == 4) {
			jsondata = xmlhttp.responseText;
			jsob = JSON.parse(jsondata);
			users = jsob.data;
			var str = "<table border='2'>";
			str += "<tr><th>User email</th><th>User Full Name</th><th>User Name</th><th>User city</th><th>User company</th><th>ADD FRIEND</th><th>BLOCK FRIEND</th></tr>"
			for(var i = 0; i < jsob.length; i++) {
				  str += "<tr><td>"  + jsob[i].uemail + "</td><td>" + jsob[i].ufullName + "</td><td>" + jsob[i].uname + "</td><td>" + jsob[i].ucity 
				+ "</td><td>" + jsob[i].ucompany + "</td><td><a href=" + addUrl + jsob[i].uemail + ">Add Friend</a></td><td><a href=" + blockUrl + jsob[i].uemail + ">Block Friend</td></tr>";
		    
			}
			str += "</table>";
			document.getElementById("displayUserDetails").innerHTML = str;
		}
	}
	xmlhttp.send();
}

function encode64(inputStr){
    var b64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var outputStr = "";
    var i = 0;
    
    while (i<inputStr.length){
       var byte1 = inputStr.charCodeAt(i++) & 0xff;
       var byte2 = inputStr.charCodeAt(i++) & 0xff;
       var byte3 = inputStr.charCodeAt(i++) & 0xff;

       var enc1 = byte1 >> 2;
       var enc2 = ((byte1 & 3) << 4) | (byte2 >> 4);
       
       var enc3, enc4;
       if (isNaN(byte2)){
			enc3 = enc4 = 64;
       } else{
         enc3 = ((byte2 & 15) << 2) | (byte3 >> 6);
         if (isNaN(byte3)){
            enc4 = 64;
         } else {
             enc4 = byte3 & 63;
         }
       }
       outputStr +=  b64.charAt(enc1) + b64.charAt(enc2) + b64.charAt(enc3) + b64.charAt(enc4);
    } 
    return outputStr;
 }
</script>
</head>
<body>
<br><br>
	
	<div class="container">
	<a class="btn btn-primary" role="button" href="BlockedUserListServlet">Block user list</a> 
	<br><br>
		<form action="ViewUserServlet" method="get">
		   <div class="form-group">
             <label for="email">Apply Filters : &nbsp;</label>
	 	  <select id="menu" name ="check">
	 		<option value="name" selected>Full Name</option>
	 		<option value="city">City</option>
	 		<option value="company">Company</option>
	 	</select> 
	 	</div>
	 	
	 	<br>
	 	 <div class="form-group">
             <label for="email">Input value :&nbsp;&nbsp;</label>
	 	  
	 	 <input type="text" id="textBox" name="choice"> 
	 	</div>
	 	 <div class="form-group">
	 	<button class="btn btn-dark btn-sm" type="button" name="btn" value="submit category" onclick="getData()">Search User</button>
		 </div>
		<br>
		<br>
		<br>
	 	</form>
	 </div>
		<div id="displayUserDetails">
		
		</div>
</body>
</html>