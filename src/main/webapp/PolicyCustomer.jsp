<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-i e9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore - Privacy Policy</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<style>
.list {
    list-style: inherit;
}  
</style>
<link rel="stylesheet" href="css/normalize.css" type="text/css">
<link rel="stylesheet" href="css/main.css" type="text/css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>

</head>
<body>
<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]--> 

<!-- Add your site or application content here -->


<div id="wrapper"> 
<div style="display:none">
  <div id="shoppingCart" class="popup">
      <jsp:include page="DisplayShoppingCart.jsp" flush="true" />    
  </div>
</div>
<form:form name="form" action="bookListByCat"  method="post">
  <!-- header starts-->
  <header>
    <div id="header"> <a href="#" title="SapeStore" class="logo"><img src="img/logo_option 01.png" width="231" height="109" alt="SapeStore"></a>
      <ul class="topLinks">
        <li>
						<!--form:checkbox id="checkMe" path="checkMe" value="Include books from Partner Store" onchange="form.submit();"/-->
						<input type="checkbox" name="checkMe" id="checkMe" style="font-size: 13px;"
						value="Include books from Partner Store" onclick="form.submit();">
						<label for="checkMe" style="font-size: 13px;">Include
							books from Partner Store</label> 
							<input type="hidden" name="categoryId" value="%{categoryId}" /> 
							<input type="hidden" name="categoryName" value="%{categoryName}" />
         		</li>
        <li><a class='inline' href="#shoppingCart"><img src="img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
        <li class="cartNum">${ShoppingCart.totalQuantity}</li>
      </ul>
      <nav>
        <ul class="nav">
 					<li class="active"><a href="/SapeStore/welcome?checkMe=${checkMe}">HOME</a></li>
					<li><a href="/SapeStore/welcome?checkMe=${checkMe}">Search</a></li>
					<li><a href="/SapeStore/welcome?checkMe=${checkMe}">Account</a></li>
					<li><a href="/SapeStore/mycart/wishlist">Wishlist</a></li>
					<jsp:include page="Logout.jsp" flush="true" />
        </ul>
      </nav>
    </div>
  </header>
  <!-- header ends -->
  <section>
     <div class="leftCol" style="height: 1000px">
      <h2>Book Categories</h2>
      <nav> 
        <!-- left navigation -->
        
        <ul>
        <c:forEach items="${catList}" var="current">
        	         		<li 
         		<c:if test="%{#globalCategoryName==categoryName}">
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
    </section>
  </form:form>
  <section style="margin-left: 262px; margin-top: 40px;">
    <h1 style="margin-top: -5px;">Privacy Policy</h1>
<div id="contactDiv" style="margin-left:2px; margin-top: 25px; margin-right: 20px">
	<textarea id="policyText" name="policyText"  cols="40" rows="10" style="width: 698px;
	height: 755px;
	border-style: none;
	border-color: transparent;
	overflow: auto;
	outline: none;
	background: #dfe5e6;

">${policyText}</textarea>
</div>    
</section>
 <div class="clearfix"></div>
  <footer>
  	<div id="footer">
     	<div style=" float: left; margin-left: 386px;">
     		<a href="/SapeStore/contactUsCustomer" style="color: #21addd;">Contact Us</a>
       	</div>
       	<div style="float: left;margin-left: 6px; color: #21addd"> | </div>
       	
       	<div style="float: left;margin-left: 7px;">
 			<a href="/SapeStore/policyCustomer" style="color: #21addd;">Privacy Policy</a>
  		</div>
  		<div>Powered by <img src="img/sapient_nitro.jpg" width="78" height="14" alt="sapient nitro"></div>
  	</div>
  </footer>
</div>

<!-- This contains the hidden content for shopping cart popup -->

<!-- This contains the hidden content for login popup -->
<div style="display:none">
  <div id="login" class="popup">
  	<jsp:include page="login.jsp" flush="true" /> 
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


</script>
</body>
</html>