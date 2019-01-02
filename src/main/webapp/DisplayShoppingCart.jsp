<%-- DisplayShoppingCart.jsp  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	padding: 5px;
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
	padding-left: 774px;
	padding-bottom: 2em;
	padding-top: 0.5em;
	padding-right: 2px;
	padding-left: 740px;
	margin-right: 20px;
}

#totalAmtBackground {
	background-color: green;
	/* float: right; */
/* 	padding-left: 774px;
	padding-bottom: 2em;
	padding-top: 0.5em;
	padding-right: 30px;
	padding-left: 521px;
	margin-right: 185px;
	width: 255px; */
}

.greyBox .date { float:left; }
.greyBox .amount { float:right; }

#total {
	display: inline-block;
}

.subtot {
	display: inline-block;
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
if(pages>1){
var element = document.getElementById(positionId);

var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> « Prev </span> ';

for (var page = 1; page <= this.pages; page++)

pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';

pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next »</span>';

element.innerHTML = pagerHtml;
}
}
}


function calcTotal(init,subtotal){
	initFull = document.getElementById("total").innerHTML;
	finalFull = initFull - init + subtotal;
	document.getElementById("total").innerHTML = finalFull;
}

function updateCart(x,isbn) {

	var quantity=document.getElementById("book_quantity"+x).value;
	var trantype = document.getElementById("trantype"+x).value;
	var bookq = document.getElementById("totq"+x).innerHTML;	
	var subtotal = 0;
	if(quantity<=0||quantity==""){
		alert("Please enter a valid quantity");
		document.getElementById("book_quantity"+x).value=1;
		return;
	}
	if(parseInt(quantity)>=(bookq)){
		alert("Sorry! Requested amount exceeds current stock")
		document.getElementById("book_quantity"+x).value=bookq;
	}
	if(trantype == "purchase")
		{
		var pprice=document.getElementById("pprice"+x).innerHTML;
		document.getElementById("prprice"+x).innerHTML = "$"+pprice;
		initSubTotal = document.getElementById("subtotal"+x).innerHTML;
		 subtotal =parseInt(pprice) * parseInt(quantity);
		 calcTotal(parseInt(initSubTotal),parseInt(subtotal));
		 document.getElementById("subtotal"+x).innerHTML = subtotal;		 
		 $.get("mycart/updateBookQuantity?book.isbn="+isbn+"&quantity="+quantity+"&trantype="+trantype,function(data,status){
			 
			 document.getElementById("cartNum").innerHTML=data;
		 }) 
		}
	else{
		var pprice=document.getElementById("rprice"+x).innerHTML;
		document.getElementById("prprice"+x).innerHTML = "$"+pprice;
		initSubTotal = document.getElementById("subtotal"+x).innerHTML;
		 subtotal =parseInt(pprice) * parseInt(quantity);
		 calcTotal(parseInt(initSubTotal),parseInt(subtotal));
		 document.getElementById("subtotal"+x).innerHTML = subtotal;		 
		 $.get("mycart/updateBookQuantity?book.isbn="+isbn+"&quantity="+quantity+"&trantype="+trantype,function(data,status){
			 
			 document.getElementById("cartNum").innerHTML=data;
		 }) 
		}
	
	
	
}

function forRemove(isbn){
	alert(asdf);
	$.get("removeFromShoppingCart?book.isbn="+isbn,function(data,status){
		 
	 }) 
}
</script>


<form:form name="cart" action="displayShoppingCart" method="GET">
	<h2>Your Cart</h2>
	<div class="scroller">
		<table id="tablepaging" class="yui">
			<%-- Display the heading of the shoppingCart --%>
			<tr>
				<th>Item(s)</th>
				<th></th>
				<th>Qty.</th>
				<th>Transaction Type.</th>
				<th>Price</th>
				<th>Subtotal</th>
				<th></th>
			</tr>
			<tr>
				<th colspan="5"><c:if
						test="${ShoppingCart.booksInCart.size()==0 || ShoppingCart.booksInCart==null}">
						<p>No Items in Cart</p>
					</c:if></th>
			</tr>


			<c:forEach items="${ShoppingCart.booksInCart}" var="current"
				varStatus="Count">

				<tr>

					<td style="width: 60px"><img src="${current.thumbPath}"
						width="81" height="112" alt="product name"
						onerror="this.src= '/SapeStore/img/default.png'"></td>
					<td>${current.bookTitle}</td>

					<td><input type="text" name="quantity"
						id="book_quantity${Count.count}" value="${current.cartQuantity}"
						size="2" onchange="updateCart(${Count.count},${current.isbn})"></td>

					<td><select id="trantype${Count.count}" name="trantype"
						onchange="updateCart(${Count.count},${current.isbn})">
							<option value="purchase">Purchase</option>
							<c:if test="${current.rentAvailable=='Y'}">
								<option value="rent" ${current.isRented=="y"?'selected':''}>Rent</option>
							</c:if>
					</select></td>


					<td id="pprice${Count.count}" style="display: none;">${current.bookPrice}</td>
					<!-- hidden -->
					<td id="rprice${Count.count}" style="display: none;">${current.rentPrice}</td>
					<!-- hidden -->
					<td id="totq${Count.count}" style="display: none;">${current.quantity}</td>
					<c:if test="${current.isRented=='y'}">
						<td id="prprice${Count.count}">$${current.rentPrice}</td>
					
        			 

							<td>$
							<div id="subtotal${Count.count}" class="subtot">${current.cartQuantity * current.rentPrice}</div>
						</td>
					</c:if>
					<c:if test="${current.isRented=='n'}">
						<td id="prprice${Count.count}">$${current.bookPrice}</td>
						<td>$
							<div id="subtotal${Count.count}" class="subtot">${current.cartQuantity * current.bookPrice}</div>
						</td>
					</c:if>

					<td><input type="hidden" name="item" value="" /> <a
						href="/SapeStore/removeFromShoppingCart?book.isbn=${current.isbn}"
						class="darkButton">Remove</a></td>

				</tr>


			</c:forEach>
		</table>
	</div>
	-----------------------------------------------------------------------------------------------------------------------------
	<div class="greyBox" style="background-color: #d4d6d8;">
		<span class="date" style="font: sans-serif; font-weight: bold; ">Expected Return Date : ${ShoppingCart.returnDate }</span>
		<span class="amount" style="font: sans-serif; font-weight: bold; ">
		<span>Total Amount Payable :</span> $
		<div id="total">${ShoppingCart.totalPrice}</div>
		</span>
	</div>
	<div style="background-color: #d4d6d8;"></div>
	<br />

	<div>
		<br> <br> <a href="/SapeStore/mycart/continueShopping"
			class="lightButton">Continue Shopping</a>
		<!-- <input type="button" value="Close &amp; Countinue Shopping" class="lightButton"> -->
		<span style="float: right;">
		<c:if test="${ShoppingCart.booksInCart.size()>0 }">
			<a href="/SapeStore/mycart/checkoutCart" class="lightButton">Checkout</a>
		</c:if>
		</span>
		<!-- <input type="button" value="Checkout" class="lightButton checkout"> -->
	</div>


	<!-- <script type="text/javascript">
  	document.getElementById("rprice").style.display = "none";
	document.getElementById("pprice").style.display = "none";
		

		
	
		
	</script> -->
</form:form>