<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page errorPage="error.jsp" %>
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

<link rel="stylesheet" href="/SapeStore/css/normalize.css">
<link rel="stylesheet" href="/SapeStore/css/main.css">
<script src="/SapeStore/js/vendor/modernizr-2.6.2.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<%-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> --%>
<script src="/SapeStore/js/vendor/jquery-1.9.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/SapeStore/css/override.css">

<script>
  $(function() {
    $( "#tabs" ).tabs();
  });
</script>
<style type="text/css">

</style>
</head>
<body>
<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]--> 

<!-- Add your site or application content here -->
<div id="wrapper" class="homeAdmin"> 
  <!-- header starts-->
  <header>
    <div id="header"> <a href="/SapeStore/manageInventory" title="SapeStore" class="logo"><img src="img/logo.jpg" width="231" height="109" alt="SapeStore"></a>
      <ul class="topLinks hide">
        <li>
          <input name="include_books" type="checkbox" value="include_books">
          <a title="Add books from Partner Store" href="javacript:void(0)">Include books from Partner Store</a></li>
        <li><a class='inline' href="#shoppingCart"><img src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
        <li class="cartNum">0</li>
      </ul>
      <!-- in case of admin hide this and display another one -->
            				<ul class="topLinks">
					<li>
					<c:choose>
					<c:when test="${not empty userId}">
						Welcome ${username}
					</c:when>
					</c:choose>
					</li>
				</ul>
      <nav>
        <ul class="nav">
					<li><a href="/SapeStore/admin/manageInventory">Manage Inventory</a></li> 

					<li  class="active"><a href="/SapeStore/admin/manageOrders" >Manage Orders</a></li> 

					<li><a href="/SapeStore/admin/adminReport">Manage Reports</a></li> 

					<li><a href="/SapeStore/admin/managePages" >Manage Pages</a></li> 

					<li><a href="/SapeStore/logout">Logout</a></li>
          </ul>
      </nav>
    </div>
  </header>
  <!-- header ends -->
  	<section style="height: 650px; margin-bottom: 20px;">
  
    <div id="tabs" style="border: none; height: 650px;">
	  <ul style="padding-top: 20px; padding-left: 22px; padding-right: 3px;">
	    <li><a href="/SapeStore/purchasedOrders" style="outline: none">Purchased Orders</a></li>
	  	<li><a href="/SapeStore/rentedOrders" style="outline: none">Rented Orders</a></li>
		<!-- javascript:void(0) --> 
	  </ul>
	</div>
	
	
	</section>
	 <div class="clearfix"></div>
	<footer>
		<div id="footer">
	     	
	  		<div>Powered by <img src="img/sapient_nitro.jpg" width="78" height="14" alt="sapient nitro"></div>
	  	</div>
  </footer>
</div>

</body>
</html>