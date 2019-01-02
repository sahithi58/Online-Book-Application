<%-- <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore-Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="/SapeStore/css/normalize.css" type="text/css">
<link rel="stylesheet" href="/SapeStore/css/main.css" type="text/css">
<script src="/SapeStore/js/vendor/modernizr-2.6.2.min.js"></script>

<style type="text/css">
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

.required label:after {
	 
    color: #e32;
    content: ' *';
    background-position: right top;
    display:inline;
}
</style>
<script>
function validateForm() {
    var x = document.forms["orderstatusform"]["orderId"].value;
    if (isNaN(x) ) {
        alert("Enter valid Order Id: Not a Number");
        return false;
    }else if(x.length < 1 || x.length > 10){
    	alert("Enter valid Order Id: Invalid Length");
    	return false;
    }
    else
    	alert("Ok");
    }

</script>
<script>

$(document).ready(function(){
	$("#dispatch").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	$("#return").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	
});
</script>

</head>

<body>


	<div id="wrapper">
		<div id="shoppingCartContainer" style="display: none">
			<div id="shoppingCart" class="popup">
				<jsp:include page="DisplayShoppingCart.jsp" flush="true" />
			</div>
		</div>
		<header>
		<form:form name="form" action="/SapeStore/bookListByCat?categoryId=${categoryId}&categoryName=${categoryName}&checkMe=false" method="GET" commandName="welcome">
			
			<div id="header">
				<a href="/SapeStore/welcome?checkMe=${checkMe}" title="SapeStore" class="logo"><img
					src="/SapeStore/img/logo_option 01.png" width="231" height="109"
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
						<label for="checkMe" style="font-size: 13px;">Include books from Partner Store</label> 
							<input type="hidden" name="categoryId" value="${categoryId}" /> 
							<input type="hidden" name="categoryName" value="${categoryName}" />
					</li>
					<li><a class='inline' href="#shoppingCart"><img src="/SapeStore/img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
					<li class="cartNum">${ShoppingCart.totalQuantity}</li>
				<c:choose>
					<c:when test="${not empty userId}">
							<select id="account" name="account" style="font-size: 12px;" onChange="window.location.href=this.value">
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
					<li><a href="/SapeStore/searchPage?checkMe=${checkMe}"><img alt="searchImage" src="/SapeStore/img/magnifier-icon.png"></a></li>
					
				</ul>
				</nav>
			</div>
			</form:form>
			</header>
			<!-- header ends -->
			
	<div class="leftCol" style="height:600px;">
		<h2>Account</h2>
			<div class="clearfix">	
			<nav> <!-- left navigation -->
			      <ul>
			      	<li><a href="/SapeStore/personalInfo">Personal Information</a></li>
			      	<li><a href="/SapeStore/transactionHistory">Transaction History</a></li>
			      	<li class="highlighted"><a href="/SapeStore/orderStatus">Order Status</a></li>
			      </ul>  
			 </nav>
        </div>
      </div>
     
       
        <div   class="clearfix">
        	<h2 style="padding-top:10px">Order Status Tracking</h2>
        	<hr>
        	
        	<form id="orderstatusform" action="getorderstatus" onsubmit="return validateForm()" method="post"> 
        		<div class="required">
        		 <label for="orderId" style="font-size: 20px; color:#222;margin-left:20px;" >Order Number</label><br>
        		 <input type="text" name="orderId" style="margin-left:20px;">
  				 <input type="submit" value="Get Status" class="lightButton">
  				</div>
			</form>
			
			<div id="currentstatus" style="diplay:none">
			<div id="showstatus">
				<p style="font-size:12px">${status}.</p>
				</div>
			</div>
        </div>
       
   <footer>
  
     <div id="footer">
     	<div style=" float: left; margin-left: 386px;">
     		<a href="/SapeStore/contactUsCustomer" style="color: #21addd;">Contact Us</a>
       	</div>
       	<div style="float: left;margin-left: 6px; color: #21addd"> | </div>
       	
       	<div style="float: left;margin-left: 7px;">
 			<a href="/SapeStore/policyCustomer" style="color: #21addd;">Privacy Policy</a>
  		</div>
  		<div  style="float: right;margin-left: 10px;">Powered by <img src="/SapeStore/img/sapient_nitro.jpg" width="78" height="14" alt="sapient nitro"></div>
  	</div>
 </footer>
 
 </div>
 <!-- This contains the hidden content for shopping cart popup -->

<!-- This contains the hidden content for login popup -->
<div style="display:none">
  <div id="login" class="popup">

  	
<%@include file="login.jsp" %>
  </div>
</div>

<!-- Forgot password-->
<div style="display:none">
  <form method="post" action="" id="forgotPassword" onsubmit="return ValidateForm();">
    <fieldset>
      <label for="email">Enter your email id<span class="required">*</span></label>
      <input type="email" placeholder="Name" required="" name="name">
      <input type="submit" value="Submit" class="lightButton">
      <div id="someHiddenDiv" style="display:none">Your password has been sent to your registered mail.</div>
    </fieldset>
  </form>
</div>
<script type="text/javascript">
		var pager = new Pager('tablepaging', 10);
		pager.init();
		pager.showPageNav('pager', 'pageNavPosition1');
		pager.showPage(1);
	</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')</script> 
<script src="js/plugins.js"></script> 
<script src="js/main.js"></script> 
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

	var comCount = document.getElementById("booksCount").value;
	var newBooksCount = document.getElementById("newBooksCount").value;
	var page = document.getElementById("page").value;
	var catId = document.getElementById("categoryId").value;
	var catName = document.getElementById("categoryName").value;
	var checkMe = document.getElementById("checkMeJs").value;

	var pageNo = parseInt(page);
	
	console.log("Total books count: "+comCount);
	console.log("selected books count: "+newBooksCount);
	var x=Math.ceil(comCount/15);
	console.log("Total pages count: "+x);
	var iterator = 1;
	if(page){
	               var temp = Math.floor(pageNo/5);
	               iterator = temp + 1;
	               if(iterator > 1){
	               iterator = (iterator-1)*5;
	               }
	        }
    if(x > 1){
	if(iterator == 1){
		var html = '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+iterator+'&checkMe='+checkMe+'" title="'+iterator+'">&lt;</a></li>';
	}else{
		var html = '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+(iterator-1)+'&checkMe='+checkMe+'" title="'+(iterator-1)+'">&lt;</a></li>';
		}
	var i = iterator;
	var lim = i+4;
	if(pageNo == 0 || pageNo == 1){
	html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+1+'&checkMe='+checkMe+'" title="'+iterator+'">Prev</a></li>';
	}else{
		html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+(pageNo-1)+'&checkMe='+checkMe+'" title="'+iterator+'">Prev</a></li>';
		}
	for(i; i<=lim; i++){ 
	               if(i<=x){
	                    if(i == parseInt(page)){
	                 		html = html + '<li><a class="sel_page" href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+i+'&checkMe='+checkMe+'" title="'+i+'">'+i+'</a></li>';
		                    
		                }
	                    else{
	                 		html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+i+'&checkMe='+checkMe+'" title="'+i+'">'+i+'</a></li>';
	                    }
	 	           }
	        } 
    if(pageNo <= (x-1)){
	html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+(pageNo+1)+'&checkMe='+checkMe+'" title="'+iterator+'">Next</a></li>';
    }
    if(i<=x){
		html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+i+'&checkMe='+checkMe+'" title="'+i+'">&gt;</a></li>';
    }
	document.getElementById("paginationUl").innerHTML = html;
    }


</script>
 
</body>
</html> --%>



<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore-Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="/SapeStore/css/normalize.css" type="text/css">
<link rel="stylesheet" href="/SapeStore/css/main.css" type="text/css">
<script src="/SapeStore/js/vendor/modernizr-2.6.2.min.js"></script>

<style type="text/css">
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
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

.required label:after {
	 
    color: #e32;
    content: ' *';
    background-position: right top;
    display:inline;
}

.lightButton { background:#21addd; 
color:#fff; font-size:18px; 
font-family:Georgia, "Times New Roman", Times, serif;
 border:solid 1px #1d97c1; 
 padding:5px 10px;
 }
</style>
<script>
function validateForm() {
    var x = document.forms["orderstatusform"]["orderId"].value;
    if (isNaN(x) || x.length < 1 || x.length > 10 ) {
        alert("Enter valid Order Id: Not a Number");
        return false;
    }
   
  }

</script>
<script>

$(document).ready(function(){
	$("#dispatch").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	$("#return").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	
});
</script>


<!-- <script>
$(document).ready(function(){
    $("#getstatus").click(function(){
    
        $("#currentstatus").css("display", "block");
    });
});
</script> -->
</head>

<body>

<script type="text/javascript">
$(document).ready(function(){
	$("#loginPop").click();
});
function changeVal()
{
	document.getElementById('account').value = '-1';
}
</script>
	<div id="wrapper">
		<div id="shoppingCartContainer" style="display: none">
			<div id="shoppingCart" class="popup">
				<jsp:include page="DisplayShoppingCart.jsp" flush="true" />
			</div>
		</div>
		<header>
		<form:form name="form" action="/SapeStore/bookListByCat?categoryId=${categoryId}&categoryName=${categoryName}&checkMe=false" method="GET" commandName="welcome">
			
			<div id="header">
				<a href="/SapeStore/welcome?checkMe=${checkMe}" title="SapeStore" class="logo"><img
					src="/SapeStore/img/logo_option 01.png" width="231" height="109"
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
						<label for="checkMe" style="font-size: 13px;">Include books from Partner Store</label> 
							<input type="hidden" name="categoryId" value="${categoryId}" /> 
							<input type="hidden" name="categoryName" value="${categoryName}" />
					</li>
					<li><a class='inline' href="#shoppingCart"><img src="/SapeStore/img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
					<li class="cartNum">${ShoppingCart.totalQuantity}</li>
				<c:choose>
					<c:when test="${not empty userId}">
							<select id="account" name="account" style="font-size: 12px;" onChange="window.location.href=this.value">
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
					<li><a href="/SapeStore/searchPage?checkMe=${checkMe}"><img alt="searchImage" src="/SapeStore/img/magnifier-icon.png"></a></li>
					
				</ul>
				</nav>
			</div>
			</form:form>
			</header>
			<!-- header ends -->
			
	<div class="leftCol" style="height:600px;">
		<h2>Account</h2>
			<div class="clearfix">	
			<nav> <!-- left navigation -->
			      <ul>
			      	<li><a href="/SapeStore/personalInfo">Personal Information</a></li>
			      	<li><a href="/SapeStore/transactionHistory">Transaction History</a></li>
			      	<li class="highlighted"><a href="/SapeStore/orderStatus">Order Status</a></li>
			      </ul>  
			 </nav>
        </div>
      </div>
     
       
        <div   class="clearfix">
        	<h2 style="padding-top:10px; margin-left:10px;">Order Status Tracking</h2>
        	<hr>
        	
        	<form id="orderstatusform" action="getorderstatus" onsubmit="return validateForm()" method="post"> 
        		<div class="required">
        		 <label for="orderId" style="font-size: 20px; color:#222; margin-left:10px" >Order Number</label><br>
        		 <input type="text" name="orderId" style="margin-left:10px;">
        		 <!-- <a href="#showstatus"> --><!-- onclick="document.getElementById('currentstatus').style.display='block';" -->
  				<input id="getstatus" type="submit" value="Get Status" class="lightButton" >
  				</div>
			</form>
			<p style="font-size:12px;">${htmlStatus }</p>
			<%--  <%-- <div id="currentstatus" style="display:none;">  
			<div id="showstatus">
				<p style="font-size:12px;">${htmlStatus }${status }</p>
			 </div>
			 </div>  --%>
			 <br>
		
		<c:if test="${not empty myorders}">
			<table  id="tablepaging" class="yui" style="width:70%; height: 91px; border-bottom-width: 0px; float: inherit; ">
			<thead>
			<tr  style= "font-size: 20px; color:#222; margin-left:10px;padding-top:10px; font:sans-serif; "> <b>Order Details</b>   </tr>
			 <tr>
			 <th>OrderItemID</th>
			 <th>BookName</th>
			 <th>Quantity</th>
		     <th>TransactionType</th>
		     <th>Status</th>
			<!--  <th>DueDate</th> --> 
			 </tr>
			</thead>
			<tbody> 
			<c:forEach items="${myorders}" var="current"  varStatus="loop">						
					
							 <tr
								id="index${current.orderItemId}"> 
								<td>${current.orderItemId}
								</td>
								<td>${current.bookTitle}
								</td>
								
								<td>${current.orderQuantity}
								</td>
								<td>${current.purchaseType}
								</td>
							<td>${current.orderStatus}</td>	
								<%-- <td>${current.expectedReturnDate}
								</td> --%>
				
					
			 </tr>
							</c:forEach>
							</tbody>
						
					</table>
			 
			 </c:if>
        </div>
       
   <footer>
  
     <div id="footer">
     	<div style=" float: left; margin-left: 386px;">
     		<a href="/SapeStore/contactUsCustomer" style="color: #21addd;">Contact Us</a>
       	</div>
       	<div style="float: left;margin-left: 6px; color: #21addd"> | </div>
       	
       	<div style="float: left;margin-left: 7px;">
 			<a href="/SapeStore/policyCustomer" style="color: #21addd;">Privacy Policy</a>
  		</div>
  		<div  style="float: right;margin-left: 10px;">Powered by <img src="/SapeStore/img/sapient_nitro.jpg" width="78" height="14" alt="sapient nitro"></div>
  	</div>
 </footer>
 
 </div>
 <!-- This contains the hidden content for shopping cart popup -->

<!-- This contains the hidden content for login popup -->
<div style="display:none">
  <div id="login" class="popup">

  	
<%@include file="login.jsp" %>
  </div>
</div>

<!-- Forgot password-->
<div style="display:none">
  <form method="post" action="" id="forgotPassword" onsubmit="return ValidateForm();">
    <fieldset>
      <label for="email">Enter your email id<span class="required">*</span></label>
      <input type="email" placeholder="Name" required="" name="name">
      <input type="submit" value="Submit" class="lightButton">
      <div id="someHiddenDiv" style="display:none">Your password has been sent to your registered mail.</div>
    </fieldset>
  </form>
</div>
<script type="text/javascript">
		var pager = new Pager('tablepaging', 10);
		pager.init();
		pager.showPageNav('pager', 'pageNavPosition1');
		pager.showPage(1);
	</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')</script> 
<script src="js/plugins.js"></script> 
<script src="js/main.js"></script> 
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

	var comCount = document.getElementById("booksCount").value;
	var newBooksCount = document.getElementById("newBooksCount").value;
	var page = document.getElementById("page").value;
	var catId = document.getElementById("categoryId").value;
	var catName = document.getElementById("categoryName").value;
	var checkMe = document.getElementById("checkMeJs").value;

	var pageNo = parseInt(page);
	
	console.log("Total books count: "+comCount);
	console.log("selected books count: "+newBooksCount);
	var x=Math.ceil(comCount/15);
	console.log("Total pages count: "+x);
	var iterator = 1;
	if(page){
	               var temp = Math.floor(pageNo/5);
	               iterator = temp + 1;
	               if(iterator > 1){
	               iterator = (iterator-1)*5;
	               }
	        }
    if(x > 1){
	if(iterator == 1){
		var html = '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+iterator+'&checkMe='+checkMe+'" title="'+iterator+'">&lt;</a></li>';
	}else{
		var html = '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+(iterator-1)+'&checkMe='+checkMe+'" title="'+(iterator-1)+'">&lt;</a></li>';
		}
	var i = iterator;
	var lim = i+4;
	if(pageNo == 0 || pageNo == 1){
	html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+1+'&checkMe='+checkMe+'" title="'+iterator+'">Prev</a></li>';
	}else{
		html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+(pageNo-1)+'&checkMe='+checkMe+'" title="'+iterator+'">Prev</a></li>';
		}
	for(i; i<=lim; i++){ 
	               if(i<=x){
	                    if(i == parseInt(page)){
	                 		html = html + '<li><a class="sel_page" href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+i+'&checkMe='+checkMe+'" title="'+i+'">'+i+'</a></li>';
		                    
		                }
	                    else{
	                 		html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+i+'&checkMe='+checkMe+'" title="'+i+'">'+i+'</a></li>';
	                    }
	 	           }
	        } 
    if(pageNo <= (x-1)){
	html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+(pageNo+1)+'&checkMe='+checkMe+'" title="'+iterator+'">Next</a></li>';
    }
    if(i<=x){
		html = html + '<li><a href="/SapeStore/bookListByCat?categoryId='+catId+'&categoryName='+catName+'&page='+i+'&checkMe='+checkMe+'" title="'+i+'">&gt;</a></li>';
    }
	document.getElementById("paginationUl").innerHTML = html;
    }


</script>
 
</body>
</html>