<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<link rel="stylesheet" href="css/main.css" type="text/css">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<script src="js/english.js"></script>
<meta http-equiv="refresh" content="3000;url=http://www.google.com/" />
<title>Form Validation</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="/SapeStore/js/vendor/jquery-1.9.1.min.js"><\/script>')</script> 
<script src="/SapeStore/js/plugins.js"></script> 
<script src="/SapeStore/js/main.js"></script> 

<style type="text/css">
 #submit.lightButton { background:#21addd; color:#fff; font-size:18px; font-family:Georgia, "Times New Roman", Times, serif; border:solid 1px #1d97c1; padding:5px 10px;}

.pg-normal {
	color: #de2728;
	font-size: 14px;
	cursor: pointer;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

.pg-selected {
	color: #fff;
	font-size: 14px;
	background: #de2728;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

#submit{background-color: #008CBA;}


table.yui {
	border-collapse: collapse;
	font-size: small;
}

table.yui td {
	padding: 5px;
}

table.yui .even {
	background-color: #EEE8AC;
}

table.yui .odd {
	background-color: #F9FAD0;
}

table.yui th {
	padding-top: 13px;
	height: auto;
}

table.yui th a {
	text-decoration: none;
	text-align: center;
	padding-right: 20px;
	font-weight: bold;
	white-space: nowrap;
}

table.yui tfoot td {
	background-color: #E1ECF9;
}

table.yui thead td {
	vertical-align: middle;
	background-color: #E1ECF9;
	border: none;
}

table.yui thead .tableHeader {
	font-size: larger;
	font-weight: bold;
}

table.yui thead .filter {
	text-align: right;
}

table.yui tfoot {
	background-color: #E1ECF9;
	text-align: center;
}

table.yui .tablesorterPager {
	padding: 10px 0 10px 0;
}

table.yui .tablesorterPager span {
	padding: 0 5px 0 5px;
}

table.yui .tablesorterPager input.prev {
	width: auto;
	margin-right: 10px;
}

table.yui .tablesorterPager input.next {
	width: auto;
	margin-left: 10px;
}

table.yui .pagedisplay {
	font-size: 10pt;
	width: 30px;
	border: 0px;
	background-color: #E1ECF9;
	text-align: center;
	vertical-align: top;
}

.homeAdmin #mainContent table {
	width: 99%;
}
#pageNavPosition {
	float: right;
	background: #f0f7f8;
	border-right: 1px solid #AAAAAA;
	border-left: 1px solid #AAAAAA;
	border-bottom: 1px solid #AAAAAA;
	padding-left: 774px;
	margin-right: 15px;
	padding-bottom: 0.5em;
	padding-top: 0.5em;
	padding-right: 2px;
}

</style>


<script type="text/javascript">

$(document).ready(function(){
    $("#login_2").click(function(){

		
         
        
        var stateId=$("#stateId").val();
        $.post("/SapeStore/beforeRegister",{stateId:stateId},
        function(data,status){
              
           
			$("#searchForm1").submit();
                     
        });
    });
});
</script>
<script type="text/javascript">

	   $(document).ready(function(){
		    $("#button").click(function(){
			    var name=$("#userId").val();
			    var password=$("#password").val();
			   
		        $.get("/SapeStore/logintest",{name:name,password:password},
		        function(data,status){
			       

			         var message="Please provide the correct login credentials";
			        if(data=="true")
				        {
		            $("#errormessage").html(message); 
				        }
			        else
				    {			
								
								
								
								
								   $.getJSON("/SapeStore/login",{name:name,password:password},
        function(json){
        	
        	 $("#cboxOverlay").css('display','none'); 
        	 $("#cboxWrapper").css('display','none'); 
        	
        	         		
        		 window.location.replace("/SapeStore/welcome");
        		 
        	 });
        	 
    
			        	        }});
					
		    });			
					
		            
		
		});
				
	
</script>


 <script>
                                                $(document).ready(function(){
                                                                $(".inline").colorbox({inline:true, width:"auto", height:"auto"});
                                                                $(".callbacks").colorbox({
                                                                                onOpen:function(){ alert('onOpen: colorbox is about to open'); },
                                                                                onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
                                                                                onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
                                                                                onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
                                                                                onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
                                                                });
                                                                
    });

                               
                                </script> 

      
       <script 
       	src="https://code.jquery.com/jquery-1.12.4.min.js" 
       	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" 
       	crossorigin="anonymous">
       </script>
		
       <script type="text/javascript">
       $(document).ready(function(){
           $("#loginName").change(function(){
               
       	var loginName=$("#loginName").val();
              
               $("#login_Name").empty();
               $.get("/SapeStore/checkU",{loginName:loginName},
                      

            	   function(data,status){
   			        

   			         var message="Please provide the correct login credentials";
   			        if(data=="true")
   				        {
   		            $("#errormessage1").empty(); 
                          
   				        }
   			        else{
   			         $("#errormessage1").html("user already exist");
   	   			        }
                  
                   
       });});});
       </script>  
		
       <script type="text/javascript">
       $(document).ready(function(){
           $("#state").change(function(){
               
       	var state=$("#state").val();
              
               $.getJSON("/SapeStore/beforeRegis1",{stateId:state},
                       function(json){
                       	
                       	$('#city1').empty();
                            $('#city1').append($('<option>').val( "111111" ).text("Select City"));  
                        
                     
                           $.each(json, function(i, obj){


                               
                           	  $('#city1').append($('<option>').val( obj.cityId ).text(obj.cityName));




                        
                       });
                   });
       });});
       </script>  

       <script type="text/javascript">
       	function validateInput()

       	{

       		var nameReg = /^[a-zA-Z]{1,100}$/; 
       		document.getElementById("full_Name").innerHTML = "";
       		document.getElementById("login_Name").innerHTML = "";
       		document.getElementById("passwordAlert").innerHTML = "";
       		document.getElementById("retypePasswordAlert").innerHTML = "";
       		document.getElementById("emailAlert").innerHTML = "";
       		document.getElementById("addressAlert").innerHTML = "";
       		document.getElementById("cityAlert").innerHTML = "";
       		document.getElementById("stateAlert").innerHTML = "";
       		document.getElementById("genderAlert").innerHTML = "";
       		document.getElementById("zipAlert").innerHTML = "";
       		document.getElementById("phoneAlert").innerHTML = "";
       		document.getElementById("mobileAlert").innerHTML = "";

       		var fullName = document.getElementById("fullName").value;
       	
       		var loginName = document.getElementById("loginName").value;
       		var password = document.getElementById("pass1").value;
       		var cpassword = document.getElementById("pass2").value;
       		var mail = document.getElementById("email").value;
       		var address = document.getElementById("addressLine1").value;
       		var city = document.getElementById("city1").value;
       		
       		var gender = document.getElementById("gender").value;
       		var state = document.getElementById("state").value;
       		var zip = document.getElementById("zip").value;
       		var phone = document.getElementById("phoneNumber").value;
       		var mobile = document.getElementById("mobileNumber").value;

       		var flag = true;



       		if (fullName == "") {
				
       			document.getElementById("full_Name").innerHTML = enterNameAlert;
       			document.myForm.fullName.focus();
       			flag = false;

       		}
       		else if (!nameReg.test(fullName)) {
				document.getElementById("full_Name").innerHTML =enterNameAlert1;
				flag=false;
				
			} 
       		
       		
       		else if (!(isNaN(fullName))) {
       			document.getElementById("full_Name").innerHTML = enterNameAlert1;
       			flag = false;}

       		if (loginName == "") {
       			document.getElementById("login_Name").innerHTML = loginNameAlert;
       			document.myForm.loginName.focus();
       			flag = false;

       		}
       		if (mail == "") {
       			document.getElementById("emailAlert").innerHTML = emailAlert;
       			flag = false;

       		} else if (mail.indexOf("@") < 1
       				|| (mail.lastIndexOf(".") - mail.indexOf("@") < 2)) {
       			document.getElementById("emailAlert").innerHTML = wrongEmailIdAlert;
       			document.myForm.email.focus();
       			flag = false;
       		}
       		if (password == "") {
       			document.getElementById("passwordAlert").innerHTML = passwordAlert;
       			flag = false;
       		}

       		else if (password.search(/[0-9]/) < 0) {

       			document.getElementById("passwordAlert").innerHTML = passwordConditionAlert;
       			flag = false;
       		}

       		if (cpassword == "") {
       			// alert(retypePasswordAlert);
       			document.getElementById("retypePasswordAlert").innerHTML = retypePasswordAlert;
       			document.myForm.pass2.focus();
       			flag = false;
       		} else if (password != cpassword) {
       			document.getElementById("retypePasswordAlert").innerHTML = mismatch;
       			flag = false;

       		}

       		if (address == "") {
       			document.getElementById("addressAlert").innerHTML = addressAlert;
       			document.myForm.address1.focus();
       			flag = false;

       		}
       		if (city ==0 ) {
				
           		
       			document.getElementById("cityAlert").innerHTML = cityAlert;
       			document.myForm.cityId.focus();
       			flag = false;

       		}

       	else if (city=="111111") {
       			document.getElementById("cityAlert").innerHTML = cityAlert;
       			flag = false;

       		}  

       		if (gender == "") {
       			document.getElementById("genderAlert").innerHTML = genderAlert;
       			document.myForm.genderId.focus();
       			flag = false;

       		}

       		if (state == "") {
       			document.getElementById("stateAlert").innerHTML = stateAlert;
       			document.myForm.stateId.focus();
       			flag = false;

       		}

       		if (zip == "") {
       			document.getElementById("zipAlert").innerHTML = zip1;
       			flag = false;
       		} else if (isNaN(zip)) {
       			document.getElementById("zipAlert").innerHTML = zipAlert;
       			flag = false;
       		} else if (zip.length != 5) {
       			document.getElementById("zipAlert").innerHTML = zipAlert;
       			flag = false;
       		}

       		if (mobile == "") {
       			document.getElementById("mobileAlert").innerHTML = mobileAlert;
       			flag = false;
       		} else if (isNaN(mobile)) {
       			document.getElementById("mobileAlert").innerHTML = formatAlert;
       			flag = false;
       		} else if (mobile.length != 10) {
       			document.getElementById("mobileAlert").innerHTML = incorrectLength1;
       			flag = false;
       		}

       		if (phone != "") {

       			if (isNaN(phone)) {
       				document.getElementById("phoneAlert").innerHTML = formatAlert;
       				flag = false;
       			}

       			else if (phone.length != 10) {
       				document.getElementById("phoneAlert").innerHTML = incorrectLength;
       				flag = false;
       			}
       		}

       		if (flag == true) {
       			return true;
       			
       		}
       		return false;
       	}

       	function loadJS(file) {
       		if (file == "js/english.js") {
       			replacejscssfile("js/english.js", "js/French.js", "js");
       			loadForm();
       		} else {
       			replacejscssfile("js/French.js", "js/english.js", "js");
       			loadForm();
       		}
       	}

       	function createjscssfile(filename, filetype) {
       		if (filetype == "js") { //if filename is a external JavaScript file
       			var fileref = document.createElement('script')
       			fileref.setAttribute("type", "text/javascript")
       			fileref.setAttribute("src", filename)
       		} else if (filetype == "css") { //if filename is an external CSS file
       			var fileref = document.createElement("link")
       			fileref.setAttribute("rel", "stylesheet")
       			fileref.setAttribute("type", "text/css")
       			fileref.setAttribute("href", filename)
       		}
       		return fileref
       	}

       	function replacejscssfile(oldfilename, newfilename, filetype) {
       		var targetelement = (filetype == "js") ? "script"
       				: (filetype == "css") ? "link" : "none" //determine element type to create nodelist using
       		var targetattr = (filetype == "js") ? "src"
       				: (filetype == "css") ? "href" : "none" //determine corresponding attribute to test for
       		var allsuspects = document.getElementsByTagName(targetelement)
       		for (var i = allsuspects.length; i >= 0; i--) { //search backwards within nodelist for matching elements to remove
       			if (allsuspects[i]
       					&& allsuspects[i].getAttribute(targetattr) != null
       					&& allsuspects[i].getAttribute(targetattr).indexOf(
       							oldfilename) != -1) {
       				var newelement = createjscssfile(newfilename, filetype)
       				allsuspects[i].parentNode.replaceChild(newelement,
       						allsuspects[i])
       			}
       		}
       	}
       </script>

         </head>

       <body>





       	
       	<div id="wrapper">
       		<div id="shoppingCartContainer" style="display: none">
       			<div id="shoppingCart" class="popup">
       				<jsp:include page="DisplayShoppingCart.jsp" flush="true" />
       			</div>
       		</div>
       		<form:form name="form" action="/SapeStore/bookListByCat?categoryId=${categoryId}&categoryName=${categoryName}&checkMe=false" method="GET" commandName="welcome">
       			<header>
       			<div id="header">
       				<a href="/SapeStore/welcome?checkMe=${checkMe}" title="SapeStore" class="logo"><img
       					src="img/logo_option 01.png" width="231" height="109"
       					alt="SapeStore"></a>

       				<ul class="topLinks">
       					<li>
       						<!--form:checkbox id="checkMe" path="checkMe" value="Include books from Partner Store" onchange="form.submit();"/-->
       						<c:choose>
       							<c:when test="${welcome.checkMe==false}">
       								<input type="checkbox" name="checkMe" id="checkMe" style="font-size: 13px;"
       									onclick="form.submit();">
       							</c:when>
       							<c:otherwise>
       								<input type="checkbox" name="checkMe" id="checkMe" style="font-size: 13px;"
       									onclick="form.submit();" checked="checked">
       							</c:otherwise>
       						</c:choose>
       						<label for="checkMe" style="font-size: 13px;">Include
       							books from Partner Store</label> 
       							<input type="hidden" name="categoryId" value="${categoryId}" /> 
       							<input type="hidden" name="categoryName" value="${categoryName}" />
       					</li>
       					<li><a class='inline' href="#shoppingCart"><img
       							src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
       					<li class="cartNum">${ShoppingCart.totalQuantity}</li>
       					<c:choose>
       					<c:when test="${not empty userId}">
       							<select id="account" name="account" style="font-size: 12px;" onchange="changeVal()">
       							<option value="-1" style="font-size: 10px;">Welcome ${username}</option>
       							<option value="/SapeStore/personalInfo" style="font-size: 10px;">Edit Profile</option>
       							<option value="/SapeStore/orderStatus" style="font-size: 10px;">Order Status Tracking</option>
       							<option value="/SapeStore/transactionHistory" style="font-size: 10px;">Transaction History</option>
       							</select>
       					</c:when>
       					</c:choose>
       				</ul>
       				<nav>
       				<ul class="nav">
       					<li class="active"><a href="/SapeStore/welcome?checkMe=${checkMe}">HOME</a></li>
       										<c:choose>
       					<c:when test="${not empty userId}">
       					<li><a href="/SapeStore/personalInfo">Account</a></li>
       					</c:when>

       					</c:choose>
       					
       					<li><a href="/SapeStore/mycart/wishlist">Wishlist</a></li>
       					<jsp:include page="Logout.jsp" flush="true" />
       					<li><a href="/SapeStore/searchPage?checkMe=${checkMe}"><img alt="searchImage" src="img/magnifier-icon.png"></a></li>
       					
       				</ul>
       				</nav>
       			</div>
       			</header>
       			<!-- header ends -->
       			
       			<div class="leftCol" style="height:654px;">
       				<h2>Book Categories</h2>
       				<nav> <!-- left navigation -->
       			        <ul>
             		<c:forEach items="${catList}" var="current">
                		<li 
                		<c:if test="${categoryName==current.categoryName}">
                		class="highlighted"
                		</c:if>
                		>
              				<a href="/SapeStore/bookListByCat?categoryId=${current.categoryId}&categoryName=${current.categoryName}&checkMe=${checkMe}" title="${current.categoryName}" >
              				${current.categoryName }</a>
              				
                		</li>
             	</c:forEach>
            		   
               </ul>
               </nav>
               </div>
       		</form:form>

       	<!-- select language -->
       	<!-- <select onchange="loadJS(this.value)">
                     <option selected="selected">--select a language--</option>
                     <option value="js/english.js">English</option>
                     <option value="js/French.js">French</option>

              </select>
       -->



       		<br>
       	<h2 style="margin-top: 20px;margin-left:250px;">Customer Registration</h2>
       	<hr>
       	<form:form id="myForm" name="myForm" action="/SapeStore/register"
       		onsubmit="return validateInput();" commandName="user">

       		<fieldset ${fieldset}>
       			<table border:none cellspacing="4" cellpadding="4" style="margin-top: 20px;margin-left:20px;">

       				<tr>
       					<td align="left" id="full_name">Full
       						Name<font color="red">*</font></td>
       					<td><input type="text" name="fullName" id="fullName"
       						value="${user.fullName}" ${fieldset} /></td>
       					<td><div id="full_Name" style="color: #FF0000"></div></td>
       				</tr>

       				<tr>
       					<td align="left" id="Login_name">Prefferd
       						Login Name<font color="red">*</font></td>
       					<td><input type="text" name="loginName" id="loginName"
       						value="${user.loginName}" ${fieldset} /></td>
						<td><div id="login_Name" style="color: #FF0000"></div></td> 
       					
       					 <td><div id="errormessage1" style="color: #FF0000; float:left;"></div></td> 
       				</tr>
       				<tr>
       					<td align="left">Login Password<font color="red">*</font></td>

       					<td><input type="password" name="password" id="pass1"
       						value="${user.password}" ${fieldset} /></td>
       					<td><div id="passwordAlert" style="color: #FF0000"></div></td>
       				</tr>

       				<tr>
       					<td align="left">Retype Password<font color="red">*</font></td>

       					<td><input type="password" name="pass2" id="pass2" ${fieldset} /></td>
       					<td><div id="retypePasswordAlert" style="color: #FF0000"></div></td>
       				</tr>


       				<tr>
       					<td align="left">EMail<font color="red">*</font></td>
       					<td><input type="text" name="email" id="email"
       						value="${user.email}" ${fieldset} /></td>
       					<td><div id="emailAlert" style="color: #FF0000"></div></td>
       				</tr>

       				<tr>
       					<td align="left">Address Line 1<font color="red">*</font></td>

       					<td><input type="text" name="address1" id="addressLine1"
       						value="${user.address1}" ${fieldset} /></td>
       					<td><div id="addressAlert" style="color: #FF0000"></div></td>
       				</tr>

       				<tr>
       					<td align="left">Address Line 2</td>

       					<td><input type="text" name="address2" id="addressLine2"
       						value="${user.address2}" ${fieldset} /></td>
       				</tr>
       		

       				<tr>
       						<td align="left">State<font color="red">*</font></td>
       					<td><select name="stateId" id="state"  style="width: 180px;" ${fieldset} >
       							<option value="${user.stateId}" >Select State</option>
       							<c:forEach var="state" items="${stateList}">
       								<option value="${state.stateId }" >${state.stateName}</option>
       							</c:forEach>
       					</select></td>
       					<td><div id="stateAlert" style="color: #FF0000"></div></td>
       				</tr>
       				
       				

       				<tr>
       					<td align="left" class="selects">City<font color="red">*</font></td>
       						<td>	<select name="cityId"  id="city1" style="width: 180px;" >
       						<option value="${user.cityId}" >Select City</option>
       							<%--  <c:forEach var="city" items="${cityList}">
       								<option value="${city.cityId }" >${city.cityName}</option>
       							</c:forEach> --%>
       													
       					</select></td>
       					
       				
       					
       					
       					<td><div id="cityAlert" style="color: #FF0000"></div></td>
       				</tr>

       				<tr>
       					<td align="left">Gender<font color="red">*</font></td>
       					<td><select name="genderId" id="gender" style="width: 180px;" ${fieldset} >
       							<option value="${user.genderId}"  >Select Gender</option> 
       							<c:forEach var="gender" items="${genderList}">
       								<option value="${gender.genderId}" >${gender.genderName}</option>

       							</c:forEach>

       					</select></td>
       						<!-- <select name="cityId"  id="city1"></select> -->
       				<!-- 	//<select id="state1"></select> -->
       					<td><div id="genderAlert" style="color: #FF0000"></div></td>
       				</tr>
       				
       				<tr>
       					<td align="left">Zip Code<font color="red">*</font></td>
       					<td><input type="text" name="zip" id="zip" value="${user.zip}"
       						${fieldset} /></td>
       					<td><div id="zipAlert" style="color: #FF0000"></div></td>
       				</tr>
       				<tr>
       					<td align="left">Phone Number</td>
       					<td><input type="text" name="phoneNumber" id="phoneNumber"
       						value="${user.phoneNumber}" ${fieldset} /></td>
       					<td><div id="phoneAlert" style="color: #FF0000"></div></td>
       				</tr>

       				<tr>
       					<td align="left">Mobile Number<font color="red">*</font></td>
       					<td><input type="text" name="mobileNumber" id="mobileNumber"
       						value="${user.mobileNumber}" ${fieldset} /></td>
       					<td><div id="mobileAlert" style="color: #FF0000"></div></td>
       				</tr>
       				<tr>
       					<td align="left"></td>
       					<td><input class="lightButton" type="submit" id="submit" value="Create Profile" ${fieldset} /></td>
       				</tr>
       			</table>
       		</fieldset>
       	</form:form>
       	<div style="margin-top: 15px;">
       		<h2>${message}</h2>
       	</div>
       	<footer>
       	<div id="footer" style="margin-top: 120px;">
       		<div style="float: left; margin-left: 386px;">
       			<a href="/SapeStore/contactUsCustomer" style="color: #21addd;">Contact
       				Us</a>
       		</div>
       		<div style="float: left; margin-left: 6px; color: #21addd">|</div>

       		<div style="float: left; margin-left: 7px;">
       			<a href="/SapeStore//policyCustomer" style="color: #21addd;">Privacy
       				Policy</a>
       		</div>
       		<div>
       			Powered by <img src="/SapeStore/img/sapient_nitro.jpg" width="78"
       				height="14" alt="sapient nitro">
       		</div>
       	</div>
       	</footer>
       	</div>
       <div style="display:none">
         <div id="login" class="popup">

         	
       <%@include file="login.jsp" %>
         </div>
       </div>
       


       <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
       <script>window.jQuery || document.write('<script src="/SapeStore/js/vendor/jquery-1.9.1.min.js"><\/script>')</script> 
       <script src="/SapeStore/js/plugins.js"></script> 
       <script src="/SapeStore/js/main.js"></script> 
 
       </body>

       </html>




              
       
