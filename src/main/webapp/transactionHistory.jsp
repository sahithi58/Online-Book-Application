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

table th {
	text-align: left;
	padding:15px 0 15px 0;
	font-size: 13px;
	text-transform: uppercase;
}

table td {
	font-size: 12px;
}

.sel_page{
	background: #de2728;
    color: #fff !important;
}

</style>

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
		<form:form name="form" action="/SapeStore/bookListByCat?categoryId=${categoryId}&categoryName=${categoryName}&checkMe=false" method="GET" commandName="welcome">
			<header>
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
					<li class="cartNum" id="cartNum">${ShoppingCart.totalQuantity}</li>
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
					
            <c:choose>
				<c:when test="${not empty userId}">
					<li><a href="/SapeStore/mycart/wishlist">Wishlist</a></li>
				</c:when>
			</c:choose>					<jsp:include page="Logout.jsp" flush="true" />
					<li><a href="/SapeStore/searchPage?checkMe=${checkMe}"><img alt="searchImage" src="/SapeStore/img/magnifier-icon.png"></a></li>
					
				</ul>
				</nav>
			</div>
			</form:form>
			</header>
			<!-- header ends -->
			<section>
			<div class="leftCol" style="height:600px;">
				<h2>Account</h2>
			<div class="clearfix">	
			<nav> <!-- left navigation -->
			      <ul>
			      	<li><a href="/SapeStore/personalInfo">Personal Information</a></li>
			      	<li class="highlighted"><a href="/SapeStore/transactionHistory">Transaction History</a></li>
			      	<li><a href="/SapeStore/orderStatus">Order Status</a></li>
			      </ul>  
			        
      </nav>
        
		 </div>
	
	<input type="hidden" id="tranxCount" value="${tranxCount}"/>
      <input type="hidden" id="newTranxCount" value="${newTranxCount}"/>
      <input type="hidden" id="page" value="${page}"/>
    
      </div>
        </section>
        <div id="pageNavPosition1"  align="center"></div>
        <div id="mainContent">
        <span style="font-size: 22px;">Transaction History</span>
        	<table style="width:100%">
			  <tr style="border-top: dashed 1px #ccc;">
			    <th>Name & Author</th>
			    <th>Date</th> 
			    <th>Type</th>
			    <th>Due Date</th>
			    <th>Price</th>
			    <th>Penalty</th>
			  </tr>
			  
			  <c:forEach items="${transactionHistoryList}" var="transaction">
				<tr style="border-top: dashed 1px #ccc;">
			    <td>
			    <div style="display: inline-block; width: 30%;">
			    	<img src="${current.bookFullImage}" onerror="this.src= '/SapeStore/img/default.png';" width="80" height="100" alt="${current.bookTitle}"/>
			    </div>
			    <div style="display: inline-block; width: 65%;">
			    	<div>
			    		${transaction.bookTitle}
			    	</div>
			    	<div style="margin-top: 5px; font-size: 11px;">
			    		${transaction.bookAuthor }
			    	</div>
			    </div>	
			    </td>
			    <td>${transaction.date}</td>
			    <td>${transaction.type}</td>
			    <td>${transaction.dueDate}</td>
			    <td>$ ${transaction.bookPrice}</td>
			    <td>NA</td>
			  </tr>
			  
			  </c:forEach>
			  <tr style="border-top: dashed 1px #ccc;">
			  	<td colspan="6" style="text-align: center;">${errorMsg}</td>
			  </tr>
			</table>
			<ul id="paginationUl" class="pagination" style="width: 25%;"></ul>
        </div>
       
   <footer>
  
     <div id="footer" style="margin-top: 585px;">
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
var comCount = document.getElementById("tranxCount").value;
var newBooksCount = document.getElementById("newTranxCount").value;
var page = document.getElementById("page").value;

var pageNo = parseInt(page);

console.log("Total tranx count: "+comCount);
console.log("selected tranx count: "+newBooksCount);
var x=Math.ceil(comCount/4);
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
	var html = '<li><a href="/SapeStore/transactionHistory?page='+iterator+'" title="'+iterator+'">&lt;</a></li>';
}else{
	var html = '<li><a href="/SapeStore/transactionHistory?page='+(iterator-1)+'" title="'+(iterator-1)+'">&lt;</a></li>';
	}
var i = iterator;
var lim = i+4;
if(pageNo == 0 || pageNo == 1){
html = html + '<li><a href="/SapeStore/transactionHistory?page='+1+'" title="'+iterator+'">Prev</a></li>';
}else{
	html = html + '<li><a href="/SapeStore/transactionHistory?page='+(pageNo-1)+'" title="'+iterator+'">Prev</a></li>';
	}
for(i; i<=lim; i++){ 
               if(i<=x){
                    if(i == parseInt(page)){
                 		html = html + '<li><a class="sel_page" href="/SapeStore/transactionHistory?page='+i+'" title="'+i+'">'+i+'</a></li>';
	                    
	                }
                    else{
                 		html = html + '<li><a href="/SapeStore/transactionHistory?page='+i+'" title="'+i+'">'+i+'</a></li>';
                    }
 	           }
        } 
if(pageNo <= (x-1)){
html = html + '<li><a href="/SapeStore/transactionHistory?page='+(pageNo+1)+'" title="'+iterator+'">Next</a></li>';
}
if(i<=x){
	html = html + '<li><a href="/SapeStore/transactionHistory?page='+i+'" title="'+i+'">&gt;</a></li>';
}
document.getElementById("paginationUl").innerHTML = html;
}
	
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