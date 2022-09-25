<%@ page language="java" import="java.io.*,javax.imageio.*,java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UserInfo1</title>
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
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			jsondata = xmlhttp.responseText;
			jsob = JSON.parse(jsondata);
			users = jsob.data;
			var str = "<table border='2'>";
			str += "<tr><th>User email</th><th>User Name</th><th>User Full name</th><th>User city</th><th>User company</th><th>User id</th><th>ADD FRIEND</th><th>BLOCK FRIEND</th></tr>"
			for(var i = 0; i < jsob.length; i++) {
				  str += "<tr><td>"  + jsob[i].uemail + "</td><td>" + jsob[i].ufullName + "</td><td>" + jsob[i].uname + "</td><td>" + jsob[i].ucity 
				+ "</td><td>" + jsob[i].ucompany + "</td><td>" + jsob[i].uid + 
				"</td><td><a href=" + addUrl + jsob[i].uemail + ">Add Friend</a></td><td><a href=" + blockUrl + jsob[i].uemail + ">Block Friend</td></tr>";
		   
		  		   /* ByteArrayOutputStream baos = new ByteArrayOutputStream();
			       ImageIO.write( jsob[i].uimage, "jpg", baos );
			       baos.flush();
			       byte[] imageInByteArray = baos.toByteArray();
			       baos.close();
			       String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
			       alert(b64); 
			       var img = "data:image/jpg;base64,";
			       str1 = "<img src=" + img + b64 + "/>"; 
			      "<img src=\"" + imageName + "\" \/>"
			       str2 = "width="240" height="300"/>"
			       str +=  "<tr><td>";
			       str +=  str1;
			       str += "</td><td>" + jsob[i].ufullName +"</td>" + "</td><td><a href=" + addUrl + jsob[i].uemail + ">Add Friend</a></td><td><a href=" + blockUrl + jsob[i].uemail + ">Block Friend</td></tr>"; 
       */
				 	//var image = document.getElementById("get_img");
		            //image.src = "data:image/gif;base64," + encode64(jsob[i].uimage);
		            //str1 = "<img src='data:image/png;base64," + encode64(jsob[i].uimage)  + "'/>"; 
		            /*str1 = "<img src = 'C:\Users\krish\Downloads\JavaServlets\KanchanAndPhalguni\WebContent\sample1.png'/>"
		            str +=  "<tr><td>";
			       str +=  str1;
			       str += "</td><td>" + jsob[i].ufullName +"</td>" + "</td><td><a href=" + addUrl + jsob[i].uemail + ">Add Friend</a></td><td><a href=" + blockUrl + jsob[i].uemail + ">Block Friend</td></tr>"; 
     				*/
		            
		    
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
	<a href="BlockedUserListServlet">Block user list</a> <br><br>
		<form action="ViewUserServlet" method="get">
	 	<select id="menu" name ="check">
	 		<option value="name" selected>Name</option>
	 		<option value="city">City</option>
	 		<option value="company">Company</option>
	 	</select> 
	 	<br>
	 	Input value : <input type="text" id="textBox" name="choice"> <br>
	 	<br>
	 	<input type="button" name="btn" value="submit category" onclick="getData()">
		<br>
		<br>
		<br>
	 	</form>
		<div id="displayUserDetails">
		
		</div>
</body>
</html>