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

<link rel="stylesheet" href="/SapeStore/css/normalize.css"
	type="text/css">
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

.sel_page {
	background: #de2728;
	color: #fff !important;
}
</style>
<script type="text/javascript">

 function Pager(tableName, itemsPerPage) {

this.tableName = tableName;

this.itemsPerPage = itemsPerPage;

this.currentPage = 1;

this.pages = 0;

this.inited = false;

this.showRecords = function(from, to) {

var rows = document.getElementById(tableName).rows;

// i starts from 1 to skip table header row

for (var i = 1; i < rows.length; i++) {

if (i < from || i > to)

rows[i].style.display = 'none';

else

rows[i].style.display = '';

}

}

this.showPage = function(pageNumber) {

if (! this.inited) {

alert("not inited");

return;

}

var oldPageAnchor = document.getElementById('pg'+this.currentPage);

oldPageAnchor.className = 'pg-normal';

this.currentPage = pageNumber;

var newPageAnchor = document.getElementById('pg'+this.currentPage);

newPageAnchor.className = 'pg-selected';

var from = (pageNumber - 1) * itemsPerPage + 1;

var to = from + itemsPerPage - 1;

this.showRecords(from, to);

}

this.prev = function() {

if (this.currentPage > 1)

this.showPage(this.currentPage - 1);

}

this.next = function() {

if (this.currentPage < this.pages) {

this.showPage(this.currentPage + 1);

}

}

this.init = function() {

var rows = document.getElementById(tableName).rows;

var records = (rows.length - 1);

this.pages = Math.ceil(records / itemsPerPage);

this.inited = true;

}

this.showPageNav = function(pagerName, positionId) {

if (! this.inited) {

alert("not inited");

return;

}

if(this.pages>1){
var element = document.getElementById(positionId);

var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> « Prev </span> ';

for (var page = 1; page <= this.pages; page++)

pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';

pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next »</span>';

element.innerHTML = pagerHtml;
}

}

}

</script>
<!--  <script>

$(document).ready(function(){
	/* $("#dispatch").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	$("#return").css({
		'background-color' : '#21addd',
		color: 'white',
	});
	
});
</script> -->
<script>
function updateWishList(x,isbn) {

	var quantity=document.getElementById("book_quantityy"+x).value;
	var trantype = document.getElementById("trantypex"+x).value;
	
	/* alert(quantity);
	alert(trantype); */
	if(trantype == "purchase")
		{
		var pprice=document.getElementById("ppprice"+x).value;
		 subtotal =parseInt(pprice) * parseInt(quantity);
		 document.getElementById("pprprice"+x).innerHTML = subtotal;
		 
		}
	else{
		var pprice=document.getElementById("rpprice"+x).value;
		 subtotal =parseInt(pprice) * parseInt(quantity);
		 document.getElementById("pprprice"+x).innerHTML = subtotal;
		
		}
	
	
	
}
</script>
</head>

<body>

	<!-- 	<script type="text/javascript">
$(document).ready(function(){
	$("#loginPop").click();
});
function changeVal()
{
	document.getElementById('account').value = '-1';
}
</script> -->
	<div id="wrapper">
		<div id="shoppingCartContainer" style="display: none">
			<div id="shoppingCart" class="popup">
				<jsp:include page="DisplayShoppingCart.jsp" flush="true" />
			</div>
		</div>
		<input type="hidden" id="categoryId" value="${categoryId}" /> <input
			type="hidden" id="categoryName" value="${categoryName}" />
		<form:form name="form"
			action="/SapeStore/bookListByCat?categoryId=${categoryId}&categoryName=${categoryName}&checkMe=false"
			method="GET" commandName="welcome">
			<header>
			<div id="header">
				<a href="/SapeStore/welcome?checkMe=${checkMe}" title="SapeStore"
					class="logo"><img src="/SapeStore/img/logo_option 01.png"
					width="231" height="109" alt="SapeStore"></a>

				<ul class="topLinks">
					<li>
						<!--form:checkbox id="checkMe" path="checkMe" value="Include books from Partner Store" onchange="form.submit();"/-->
						<c:choose>
							<c:when test="${checkMe==false}">
								<input type="checkbox" name="checkMe" id="checkMe"
									style="font-size: 13px;" onclick="form.submit();">
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="checkMe" id="checkMe"
									style="font-size: 13px;" onclick="form.submit();"
									checked="checked">
							</c:otherwise>
						</c:choose> <label for="checkMe" style="font-size: 13px;" href="#">Include
							books from Partner Store</label> <input type="hidden" name="categoryId"
						value="${categoryId}" /> <input type="hidden" name="categoryName"
						value="${categoryName}" />
					</li>
					<li><a class='inline' href="#shoppingCart"><img
							src="/SapeStore/img/icon_cart.jpg" width="15" height="12"
							alt="cart"></a></li>
					<li class="cartNum">${ShoppingCart.totalQuantity}</li>
					<c:choose>
						<c:when test="${not empty userId}">
							<select id="account" name="account" style="font-size: 12px;"
								onChange="window.location.href=this.value">
								<option value="-1" style="font-size: 10px;">Welcome
									${username}</option>
								<option value="/SapeStore/personalInfo" style="font-size: 10px;">Edit
									Profile</option>
								<option value="/SapeStore/orderStatus" style="font-size: 10px;">Order
									Status Tracking</option>
								<option value="/SapeStore/transactionHistory"
									style="font-size: 10px;">Transaction History</option>
							</select>
						</c:when>
					</c:choose>
				</ul>
				<nav>
				<ul class="nav">
					<li ><a
						href="/SapeStore/welcome?checkMe=${checkMe}">HOME</a></li>
					<c:choose>
						<c:when test="${not empty userId}">
							<li><a href="/SapeStore/personalInfo">Account</a></li>
						</c:when>

					</c:choose>
					<c:choose>
						<c:when test="${not empty userId}">
							<li class="active"><a href="/SapeStore/mycart/wishlist">Wishlist</a></li>
						</c:when>
					</c:choose>
					<jsp:include page="Logout.jsp" flush="true" />
					<li><a href="/SapeStore/searchPage?checkMe=${checkMe}"><img
							alt="searchImage" src="/SapeStore/img/magnifier-icon.png"></a></li>

				</ul>
				</nav>
			</div>
			</header>
			<!-- header ends -->

			<input type="hidden" id="booksCount" value="${booksCount}" />
			<input type="hidden" id="newBooksCount" value="${newBooksCount}" />
			<input type="hidden" id="page" value="${page}" />

		
		<section>
		<div class="leftCol" style="height:1000px;">
	
			<h2>Book Categories</h2>
			<br>
			<nav> <!-- left navigation -->
			<ul>
				<c:forEach items="${catList}" var="current">
					<li
						<c:if test="${categoryName==current.categoryName}">
         		class="highlighted"
         		</c:if>>
						<a
						href="/SapeStore/bookListByCat?categoryId=${current.categoryId}&categoryName=${current.categoryName}&checkMe=${checkMe}"
						title="${current.categoryName}"> ${current.categoryName }</a>
					</li>
				</c:forEach>

			</ul>
			</nav>
		</div>
</form:form>

		<!-- <div id="mainContent"> --> <%-- <h2>
      	${categoryName}
      </h2> --%> <!-- form for wishlist starts -->
		<div id="clearfix" style="height:8px;" >
			<form:form>
				<h2>Your Wishlist</h2>
				<div class="scroller">
					<table id="tablepaging" class="yui" style="width: 75%; max-width: 750px;">
						<tr>
							<th>Item(s)</th>
							<th>Quantity</th>
							<th>Available</th>
							<th>TransactionType</th>
							<th>Price</th>
							</tr>
							<tr>
							<th><c:if test="${addbook.size()==0}">No books added to wishlist</c:if></th></tr>
						
						<c:forEach items="${addbook}" var="book" varStatus="Count">
							<tr style="border-top: dashed 1px #ccc;">
								<%-- <td style="float: left;"><img src="${book.thumbPath}"
									width="80" height="110"
									onerror="this.src= '/SapeStore/img/default.png'"></td>
								<td style="float: left; width: 10px; height: 10px"><a
									href="/SapeStore/bookDetails?isbn=${book.isbn}">${book.bookTitle }</a></td>
								 --%>
								<td>
							    <div style="display: inline-block; width: 35%;">
							    	<img src="${book.thumbPath}" onerror="this.src= '/SapeStore/img/default.png';" width="80" height="100" alt="${current.bookTitle}"/>
							    </div>
							    <div style="display: inline-block; width: 60%;">
							    	<div>
							    		<a href="/SapeStore/bookDetails?isbn=${book.isbn}">${book.bookTitle }</a>
							    	</div>
							    	<%-- <div style="margin-top: 5px; font-size: 11px;">
							    		${book.bookAuthor }
							    	</div> --%>
							    </div>	
							    </td>
							    
								<td>
									<input type="text" name="quantity"
									id="book_quantityy${Count.count}" value="1"
									size="2" onchange="updateWishList(${Count.count},${book.isbn})">
								</td>
								<td>
									<c:choose>
										<c:when test="${not empty book.quantity}">Yes</c:when>
										<c:otherwise>No</c:otherwise>
									</c:choose>
								</td>
								<td>
									<select id="trantypex${Count.count}" name="trantype"
									onchange="updateWishlist(${Count.count})">
										<option value="purchase">Purchase</option>
										<c:if test="${current.rentAvailable=='Y'}">
										<option value="rent">Rent</option></c:if>
								</select>
								</td>
								<td id="pprprice${Count.count}">$${ book.bookPrice}</td>
								<td>
							
								<a href="/SapeStore/mycart/addToShoppingCarts?book.isbn=${book.isbn}&book.categoryId=${book.categoryId}&book.categoryName=${book.categoryName}&book.isRent=n"><img
										src="/SapeStore/img/icon_cart.jpg" width="15" height="15"
										alt="cart"></a></td>
							</tr>
							<input id="ppprice${Count.count}" type="hidden" value = "${book.bookPrice}"/>
							<input id="rpprice${Count.count}" type="hidden" value = "${book.rentPrice}"/>
						</c:forEach>
						
					</table>
				</div>
<div id="mainContent">
			<ul id="paginationUl" class="pagination" style="width: 25%;"></ul>
		</div>
		

			</form:form>


		</div>

		<!-- ends --> <%-- <div class="clearfix"></div>
	
	        <form name="addtoshoppingcartForm" action="addToShoppingCart" method="GET">
        <ul>
        <c:forEach items="${bookList}" var="current">
              <li><a href="/SapeStore/bookDetails?isbn=${current.isbn}" title="${current.bookTitle}">
		        		<img src="${current.bookFullImage}" onerror="this.src= '/SapeStore/img/default.png';" width="131" height="180" alt="${current.bookTitle}"/>
		        		<span>${current.bookTitle}</span>
		            	<p>${current.bookAuthor}</p>
		            </a>
		            <c:choose>
		            <c:when test="${current.averageRating >= 0 && current.averageRating < 0.5}">
		            <figure><img src="/SapeStore/img/ratings-0.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 0.5 && current.averageRating < 1.5}">
		            <figure><img src="/SapeStore/img/ratings-1.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 1.5 && current.averageRating < 2.5}">
		            <figure><img src="/SapeStore/img/ratings-2.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 2.5 && current.averageRating < 3.5}">
		            <figure><img src="/SapeStore/img/ratings-3.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 3.5 && current.averageRating < 4.5}">
		            <figure><img src="/SapeStore/img/ratings-4.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 4.5}">
		            <figure><img src="/SapeStore/img/ratings-5.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:otherwise>
		            <figure><img src="/SapeStore/img/ratings-0.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:otherwise>
		            </c:choose>
		            <p class="price">$${current.bookPrice}</p>
		            <c:choose>
		            <c:when test="${not empty userId}">
		           	<a href="mycart/addToShoppingCart?book.isbn=${current.isbn}&book.categoryId=${categoryId}&book.categoryName=${categoryName}&page=${page}" title="Add To Cart" class="addCart">
		            	<img src="/SapeStore/img/add_cart.jpg" width="15" height="13" alt="add to cart" />
		            </a>
		            </c:when>
		            <c:otherwise>
		            <script type="text/javascript">
		            	function alertIt()
		            	{
		            		alert("Please login to be able to use the cart.");
		            	}
		            </script>
		            <a href="#login" title="Add To Cart" class="addCart inline"
							id="addToCart"><img src="/SapeStore/img/add_cart.jpg" width="15" height="13" alt="add to cart" /></a>
		            </c:otherwise>
		            </c:choose>
	            </li>
        </c:forEach>
        </ul>
        </form>
        </div> --%></section>
		

<div class="clearfix"></div>
	 <footer>
  
     <div id="footer"style="margin-bottom: 1px;">
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



	<script type="text/javascript">
		var pager = new Pager('tablepaging', 10);
		pager.init();
		pager.showPageNav('pager', 'pageNavPosition1');
		pager.showPage(1);
	</script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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

	var pageNo = parseInt(page);
	
	console.log("Total books count: "+comCount);
	console.log("selected books count: "+newBooksCount);
	var x=Math.ceil(comCount/2);
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
		var html = '<li><a href="/SapeStore/mycart/wishlist?page='+iterator+'" title="'+iterator+'">&lt;</a></li>';
	}else{
		var html = '<li><a href="/SapeStore/mycart/wishlist?page='+(iterator-1)+'" title="'+(iterator-1)+'">&lt;</a></li>';
		}
	var i = iterator;
	var lim = i+4;
	if(pageNo == 0 || pageNo == 1){
	html = html + '<li><a href="/SapeStore/mycart/wishlist?page='+1+'" title="'+iterator+'">Prev</a></li>';
	}else{
		html = html + '<li><a href="/SapeStore/mycart/wishlist?page='+(pageNo-1)+'" title="'+iterator+'">Prev</a></li>';
		}
	for(i; i<=lim; i++){ 
	               if(i<=x){
	                    if(i == parseInt(page)){
	                 		html = html + '<li><a class="sel_page" href="/SapeStore/mycart/wishlist?page='+i+'" title="'+i+'">'+i+'</a></li>';
		                    
		                }
	                    else{
	                 		html = html + '<li><a href="/SapeStore/mycart/wishlist?page='+i+'" title="'+i+'">'+i+'</a></li>';
	                    }
	 	           }
	        } 
    if(pageNo <= (x-1)){
	html = html + '<li><a href="/SapeStore/mycart/wishlist?page='+(pageNo+1)+'" title="'+(pageNo+1)+'">Next</a></li>';
    }
    if(i<=x){
		html = html + '<li><a href="/SapeStore/mycart/wishlist?page='+i+'" title="'+i+'">&gt;</a></li>';
    }
	document.getElementById("paginationUl").innerHTML = html;
    }


</script>

</body>
</html>