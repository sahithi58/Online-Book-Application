<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore-Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<style> 
button:enabled
{
background-color: #21addd;
color: white;
}
button:disabled
{
background-color: #dddddd;
}
</style>

<script src="js/vendor/modernizr-2.6.2.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	  if(($("#contactText").val()) == "" )
		  {
		
		  document.getElementById("edit").disabled=true;

		  }
	  else
		  {
		  document.getElementById("contactText").disabled=true;
		  document.getElementById("save").disabled=true;
		  document.getElementById("cancel").disabled=true;

		  }
	 
	  
	
})

function editCall()
{		  document.getElementById("contactText").disabled=false;

	document.getElementById("save").disabled=false;
	  document.getElementById("cancel").disabled=false;
	  return false}

function cancelIt() {
		document.getElementById("contactText").value = document.getElementById("hiddenText").value;
	  document.getElementById("contactText").disabled = true;
	}


</script>

</head>
<body>
   <div id="div1" style="border: 1px solid #AAAAAA;margin-left:15px;background-color: #F0F7F8; height: 920px;"> 
  <div style="margin-right: 50px;float: left;margin-top: 10px">
  <div id="message" style="margin-left: 23px;"></div>
  <p style="font-size: 13px;font-weight: bold;margin-left: 24px;">Enter your contact details here</p> 
  </div>
  <div style="float: right;margin-left: 100px;margin-right: 15px; margin-top: 15px;margin-bottom: 15px"> <button id="edit" onclick="editCall()" style="margin-right: 8px; outline: none;font-family: Georgia;" >Edit</button>
  </div>
  <form:form action="/SapeStore/contactUs">
  <input type="hidden" name="hiddenText" id="hiddenText" value="${contactText}"/>
  		<textarea id="contactText" name="contactText" cols="40" rows="10" style="width: 925px; margin-left: 20px; height: 755px">${contactText}</textarea>
  </form:form>
  <div id="message"></div>
  <div align="left" style="margin-top: 10px;margin-left: 7px; margin-bottom: 10px">
  <button id="save" onclick="makeCall()" style="margin-left: 16px;outline: none">Save</button>
  <button id="cancel" style="margin-left: 30px;outline: none"  onclick="cancelIt()">Cancel</button>
  </div>
  </div>
 </body>
</html>