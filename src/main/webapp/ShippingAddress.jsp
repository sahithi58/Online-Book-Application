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

<link rel="stylesheet" href="css/normalize.css" type="text/css">
<link rel="stylesheet" href="css/main.css" type="text/css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://iamrohit.in/lab/js/location.js"></script>
<style type="text/css">
.leftCol{
height:100vh;
}
h4 {
    font-size: 1em;
    margin: .1em 0;
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
	/* 	border-right: 1px solid #AAAAAA;
	border-left: 1px solid #AAAAAA;
	border-bottom: 1px solid #AAAAAA; */
	padding-left: 774px;
	margin-right: 15px;
	padding-bottom: 0.5em;
	padding-top: 0.5em;
	padding-right: 2px;
}
</style>





</head>
<body>

	<script type="text/javascript">
		var characterReg = /^[1-9]\d*$/;
		var nameReg = /^[a-zA-Z0-9 ]{1,100}$/;
		var isbnNameReg = /^[a-zA-Z0-9]{1,100}$/;
		var alphaReg = /^[.a-zA-Z ]{1,100}$/;
		var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 

		function validateInput()

		{
			
			
			
			var flag = true;
			var bool=1;
			var addressLine1 = document.getElementById("addressLine1").value
					.trim();
			var state = document.getElementById("stateId");
			var city = document.getElementById("cityId");
			var postalCode = document.getElementById("postalCode").value.trim();
			var mobileNumber = document.getElementById("mobileNumber").value
					.trim();
			var phone = document.getElementById("phoneNumber").value.trim();

			document.getElementById("validAddressLine1").innerHTML = "";
			document.getElementById("validAddressLine2").innerHTML = "";
			document.getElementById("validPostalCode").innerHTML = "";
			document.getElementById("validMobileNumber").innerHTML = "";
			document.getElementById("validPhoneNumber").innerHTML = "";
			document.getElementById("validCity").innerHTML = "";
			document.getElementById("validState").innerHTML = "";


			
			
			if (addressLine1 == "") {
				document.getElementById("validAddressLine1").innerHTML = "Please provide Address Line 1";
				flag = false;

			}
		
			/* if (state.options[state.selectedIndex].value == 0) {
				document.getElementById("validState").innerHTML = "Please provide state";
				flag = false;

			}
 */
		/* 	if (city.options[city.selectedIndex].value == 0) {
				document.getElementById("validCity").innerHTML = "Please provide city";
				flag = false;

			} */

			if (postalCode == "") {
				document.getElementById("validPostalCode").innerHTML = "Please provide ZipCode Number";
				flag = false;
			}
			else if (isNaN(postalCode)) {
				document.getElementById("validPostalCode").innerHTML = "Invalide Zip Code ";
				flag = false;
			  
			}
			else if (document.shippingaddressform.postalCode.value.length!=5) {
				document.getElementById("validPostalCode").innerHTML = " Zip Code Should be of 5 digit";
				flag = false;
			  
			}

			if (mobileNumber == "") {
				document.getElementById("validMobileNumber").innerHTML = "Please provide Mobile Number";
				flag = false;
				bool=2;

			} else if (!(mobileNumber.match(phoneno))) {
				document.getElementById("validMobileNumber").innerHTML = "Mobile Number format is XXX-XXX-XXXX";
				flag = false;
				

			} 
			/* else  (mobileNumber!=10) {
				document.getElementById("validPostalCode").innerHTML = " Mobile Number Should be of 10 digit";
				flag = false;
			  
			} */
			if (phone == "") {
				document.getElementById("validPhoneNumber").innerHTML = "Please provide the Phone number ";
				flag = false;

			} else if (!(phone.match(phoneno))) {
				document.getElementById("validPhoneNumber").innerHTML = "Phone Number format is XXX-XXX-XXXX";
				flag = false;

			}
			/* else  (phone!=7) {
				document.getElementById("validPostalCode").innerHTML = " Phone Number Should be of 6 digit";
				flag = false;
			  
			} */

			
			
			if (flag == true) {
				
				document.shippingaddressform.submit();
			}

		}
		$(document).ready(function(){
		    $("#stateId").change(function(){
		        
		                var state=$("#stateId").val();
		        
		        $.getJSON("/SapeStore/beforeSavingShipping",{stateId:state},
		                function(json){
		                                //$('#state1').empty();
		                                $('#cityId').empty();
		                    $('#cityId').append($('<option>').text("Select City"));
		                 
		                   // $('#state1').append($('<option>').text("Select City"));
		                    $.each(json, function(i, obj){



		                        
		                                  $('#cityId').append($('<option>').val(obj.cityId).text(obj.cityName));




		                             // $('#state1').append($('<option>').text(obj.cityName));
		                            //$('#city').append($('<option>').text(obj.cityName));

		                    
		                /*     alert(json);
		                //            data = JSON.parse(data);
		                                $("#city").val=data;
		                
		                                alert($("#state").val());
		                  /*   $("#city11").html(data);  */ 
		                });
		            });
		});});
	</script>




	<div id="wrapper">
		<div id="shoppingCartContainer" style="display: none">
			<div id="shoppingCart" class="popup">
				<jsp:include page="DisplayShoppingCart.jsp" flush="true" />
			</div>
		</div>
		<form:form name="form"
			action="/SapeStore/bookListByCat?categoryId=${categoryId}&categoryName=${categoryName}&checkMe=false"
			method="GET" commandName="welcome">
			<header>
			<div id="header">
				<a href="/SapeStore/welcome?checkMe=${checkMe}" title="SapeStore"
					class="logo"><img src="img/logo_option 01.png" width="231"
					height="109" alt="SapeStore"></a>

				<ul class="topLinks">
					<li>
						<!--form:checkbox id="checkMe" path="checkMe" value="Include books from Partner Store" onchange="form.submit();"/-->
						<c:choose>
							<c:when test="${welcome.checkMe==false}">
								<input type="checkbox" name="checkMe" id="checkMe"
									style="font-size: 13px;" onclick="form.submit();">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="checkMe" id="checkMe"
									style="font-size: 13px;" onclick="form.submit();"
									checked="checked">
							</c:otherwise>
						</c:choose> <label for="checkMe" style="font-size: 13px;">Include
							books from Partner Store</label> <input type="hidden" name="categoryId"
						value="${categoryId}" /> <input type="hidden" name="categoryName"
						value="${categoryName}" />
					</li>
					<li><a class='inline' href="#shoppingCart"><img
							src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
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
					<li class="active"><a
						href="/SapeStore/welcome?checkMe=${checkMe}">HOME</a></li>
					

					<li><a href="/SapeStore/welcome?checkMe=${checkMe}">Wishlist</a></li>
					<jsp:include page="Logout.jsp" flush="true" />
					<li><a href="/SapeStore/searchPage?checkMe=${checkMe}"><img
							alt="searchImage" src="img/magnifier-icon.png"></a></li>

				</ul>
				</nav>
			</div>
			</header>
			<!-- header ends -->
			<section>
			<div class="leftCol">
				<h2>Account</h2>
			<nav> <!-- left navigation -->
			      <ul>
			      	<li><a href="/SapeStore/personalInfo">Personal Information</a></li>
			      	<li><a href="/SapeStore/transactionHistory">Transaction History</a></li>
			      	<li><a href="/SapeStore/orderStatus">Order Status</a></li>
			      </ul>  
			        
      </nav>
			</div>
		</form:form>

		<div id="mainContent">
			<h2>${categoryName}</h2>
			<div class="clearfix"></div>

			<form name="addtoshoppingcartForm" action="addToShoppingCart"
				method="GET">
				<ul>
					<c:forEach items="${bookList}" var="current">
						<li><a href="javacript:void(0)" title="${current.bookTitle}">
								<img src="${current.bookFullImage}" width="131" height="180"
								alt="${current.bookTitle}" /> <span>${current.bookTitle}</span>
								<p>${current.bookAuthor}</p>
						</a> <figure> <img src="img/ratings.jpg" width="98"
								height="14" alt="ratings" /></figure>
							<p class="price">$${current.bookPrice}</p> <c:choose>
								<c:when test="${not empty userId}">
									<a
										href="/SapeStore/addToShoppingCart?categoryId=${current.categoryId}&categoryName=${categoryName}&checkMe=${checkMe}&isbn=${current.isbn}"
										title="Add To Cart" class="addCart"> <img
										src="img/add_cart.jpg" width="15" height="13"
										alt="add to cart" />
									</a>
								</c:when>
								<c:otherwise>
									<script type="text/javascript">
										function alertIt() {
											alert("Please login to be able to use the cart.");
										}
									</script>
									<a href="#login" title="Add To Cart" class="addCart inline"
										id="addToCart"><img src="img/add_cart.jpg" width="15"
										height="13" alt="add to cart" /></a>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>
				</ul>
			</form>


			<form:form id="shippingaddressform"
				action="/SapeStore/myorder/saveShippingAddress"
				name="shippingaddressform" commandName="address" method="POST">
				<h1>Shipping Address</h1>
				<hr style="border-style: dotted; border-color: black;" />
				<table style="width: 90%; line-height: 50px;">
					<tbody>

						<tr>
						
							<td class="tdLabel"><label for="thumbImage_id" class="label">Customer
									Name<span class="required">*</span>
							</label></td>
							
							<td><h4>${address.name}</h4></td>
						</tr>

						<tr>
						
							<td class="tdLabel"><label for="fullname" class="label">Address
									Line 1<span class="required">*</span>
							</label></td>
							<td><input type="text" id="addressLine1" name="addressLine1"
								value="${address.addressLine1}"></td>
							<td><div id="validAddressLine1" style="color: #FF0000"></div></td>
							
						</tr>
						<tr>
							<td class="tdLabel"><label for="fullImage" class="label">Address
									Line 2<span class="required"></span>
							</label></td>
							<td><input type="text" id="addressLine2" name="addressLine2"
								value="${address.addressLine2}"></td>
							<td><div id="validAddressLine2" style="color: #FF0000"></div></td>
						</tr>



					

						<tr>
							<td class="tdLabel"><label for="fid_categoryName"
								class="label">State<span class="required">*</span></label></td>
							<td><select name="stateId" id="stateId">
									<option value="${state.stateId}" label=${address.stateName} />
									<c:forEach var="state" items="${stateList}">
										<option value="${state.stateId}" label="${state.stateName}" />

									</c:forEach>

							</select></td>
							<td><div id="validState" style="color: #FF0000"></div></td>
						</tr>

							<tr>
							<td class="tdLabel"><label for="fid_categoryName"
								class="label">City<span class="required">*</span></label></td>
							<td><select name="cityId" id="cityId">
									<option value="${city.cityId}" label=${address.cityName} />
									<c:forEach var="city" items="${cityList}">

										<option value="${city.cityId }" label="${city.cityName}" />

									</c:forEach>

							</select></td>
							<td><div id="validCity" style="color: #FF0000"></div></td>
						</tr>

						<tr>
							<td class="tdLabel"><label for="bookPrice_id" class="label">Zip
									Code<span class="required">*</span>
							</label></td>
							<td><input type="text" name="postalCode" id="postalCode"
								value="${address.postalCode }"></td>
							<td><div id="validPostalCode" style="color: #FF0000"></div></td>
						</tr>

					


						<tr>
							<td class="tdLabel"><label for="rentPrice_id" class="label">Mobile
									Number<span class="required">*</span>
							</label></td>
							<td><input type="text" name="mobileNumber" id="mobileNumber"
								value="${address.mobileNumber}"></td>
							<td><div id="validMobileNumber" style="color: #FF0000"></div></td>
						</tr>
							
								<tr>
							<td class="tdLabel"><label for="rentPrice_id" class="label">Phone
									Number<span class="required"></span>
							</label></td>
							<td><input type="text" id="phoneNumber"
								value="${address.mobileNumber}"></td>
							<td><div id="validPhoneNumber" style="color: #FF0000"></div></td>
						</tr>




					
						<tr align="right">
							<td colspan="9">
								<div align="right" style="margin-top: 80px">
									<input id="update" name="update" type="button"
										style="right: 15em; position: relative; width: 12em; height: 30px; top: 1px; background-color: rgb(33, 173, 221); color: white;"
										value="Save And Continue" onclick="validateInput()" />

								</div>
							</td>
						</tr>

					</tbody>
				</table>
			</form:form>
		</div>

		</section>



		<div id="pageNavPosition1" align="center"></div>
		<div class="clearfix"></div>

		<footer>
		<div id="footer">
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
				Powered by <img src="img/sapient_nitro.jpg" width="78" height="14"
					alt="sapient nitro">
			</div>
		</div>
		</footer>
	</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="/SapeStore/js/vendor/jquery-1.9.1.min.js"><\/script>')</script> 
<script src="/SapeStore/js/plugins.js"></script> 
<script src="/SapeStore/js/main.js"></script> 
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


</body>
</html>
