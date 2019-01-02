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
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
</head>
<body>
<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]--> 
<!-- Add your site or application content here -->
<div id="wrapper" class="homeAdmin"> 
  <!-- header starts-->
  <header>
    <div id="header"> <a href="#" title="SapeStore" class="logo"><img src="img/logo.jpg" width="231" height="109" alt="SapeStore"></a>
      <ul class="topLinks hide">
        <li>
          <input name="include_books" type="checkbox" value="include_books" checked>
          <a title="Add books from Partner Store" href="javacript:void(0)">Include books from Partner Store</a></li>
        <li><a class='inline' href="#shoppingCart"><img src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
        <li class="cartNum">0</li>
      </ul>
      <!-- in case of admin hide this and display another one -->
      <nav>
        <ul class="nav">
					<li class="active"><a href="/SapeStore/manageInventory">Manage Inventory</a></li> 

					<li><a href="/SapeStore/manageOrders" >Manage Orders</a></li> 

					<li><a href="/SapeStore/adminReport">Manage Reports</a></li> 

					<li><a href="/SapeStore/managePages" >Manage Pages</a></li> 

					<li><a href="/SapeStore/logout">Logout</a></li>
          </ul>
      </nav>
    </div>
  </header>
  <!-- header ends -->
  <section>
    
    <div id="mainContent">
    <table class="wwFormTable">
      <h2>
      	Book has been successfully added......
   	 </h2>
   	 <form:form method="GET" name="successForm" action="/SapeStore/manageInventory" id="successForm">
   	 <fieldset>
							  <tr>
    						  <td colspan="2"><div align="center"><input type="submit" id="success__ok" name="successOk" value="OK" class="lightButton"/>
								</div></td>
								</tr>
								</fieldset>
	</form:form>
   	 </table>
    </div>
  </section>
  <div class="clearfix"></div>
  <footer>
	  	<div id="footer">
	     	
	  		<div>Powered by <img src="img/sapient_nitro.jpg" width="78" height="14" alt="sapient nitro"></div>
	  	</div>
  </footer>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')</script> 
<script src="js/plugins.js"></script> 
<script src="js/main.js"></script> 
</body>
</html>



