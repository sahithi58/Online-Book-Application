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

.sel_page{
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
<script 
	src="https://code.jquery.com/jquery-1.12.4.min.js" 
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" 
	crossorigin="anonymous">
</script> 



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
		            $("#errormessage").fadeOut("slow");
		            
				        }
			        else
				    {			
								
								
								
								
								   $.getJSON("/SapeStore/login",{name:name,password:password},
        function(json){
        	 $("#cboxOverlay").css('display','none'); 
        	 $("#cboxWrapper").css('display','none'); 
        	 
        	 if(json.isAdmin=='Y')
        		 {
        		 window.location.replace("/SapeStore/admin/manageInventory");
        		 
        		 }  else{
        	 
        	 location.reload(true);}
        	 });
        	 
    
			        	        }});
					
		    });			
					
		            
		
		});
				
	
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
<script>
	function beforeDispatch() {
		document.updateForm.submit();
	}
	function beforeReturn() {
		document.updateForm.submit();
	}

	function dispatchClick(control) {
		var cid=control.id;
		var substr="dispatchCheckIndex";
		if(cid.lastIndexOf(substr, 0) == 0)
			{
			var str2=cid.substring(18);
			var str1 = "dispatchTextIndex";
			var textI = str1.concat(str2);
			document.getElementById(textI).childNodes[0].nextSibling.value = document.getElementById(cid).checked;
			}
	}
	
	function returnClick(control) {
		var cid=control.id;
		var substr="returnCheckIndex";
		if(cid.lastIndexOf(substr, 0) == 0)
			{
			var str2=cid.substring(16);
			var str1 = "returnTextIndex";
			var textI = str1.concat(str2);
			document.getElementById(textI).childNodes[0].nextSibling.value = document.getElementById(cid).checked;
			}
	}
</script>
</head>

<body>

<script type="text/javascript">
/* $(document).ready(function(){
	$("#loginPop").click();
}); */
function changeVal()
{
	document.getElementById('account').value = '-1';
}

function validateSearch(){
	var x = document.forms["searchsubmitform"]["booktitle"].value;
	var y = document.forms["searchsubmitform"]["bookauthor"].value;
	var z = document.forms["searchsubmitform"]["publisher"].value;
	var a = document.forms["searchsubmitform"]["category"].value;
	
	if(!x&&!y&&!z&&a=="Select a Category+0"){
		document.getElementById("errorinsearch").innerHTML = "Please provide at least one of the search filter value";
		return false;
		}else{
		return true;
	}
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
					<li class="cartNum" id="cartNum">${ShoppingCart.totalQuantity}</li>
					<c:choose>
					<c:when test="${not empty userId}">
							<select id="account" name="account" style="font-size: 12px;" onchange="changeVal()">
							<option value="-1" style="font-size: 10px;">Welcome ${username}</option>
							<option value="1" style="font-size: 10px;">Edit Profile</option>
							<option value="2" style="font-size: 10px;">Order Status Tracking</option>
							<option value="3" style="font-size: 10px;">Transaction History</option>
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
					<li><a href="/SapeStore/searchPage?checkMe=${checkMe}"><img alt="searchImage" src="img/magnifier-icon.png"></a></li>
					
				</ul>
				</nav>
			</div>
			</header>
			<!-- header ends -->
			<section>
			<div class="leftCol">
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
		
	<div id="mainContent" name="maincontent">
      <h2>
      	&nbsp; Search Books<br>
      	----------------------------------------------------
      	<br>
      </h2>
      <div class="clearfix"></div>
	  <div class="searchform" >
	  	<form action="searchBook" name="searchsubmitform" onsubmit = "return validateSearch()">
	  		<div style="float: left; margin-right : 55px">
	  		<label style="font-family: SapientSansMedium; font-size: 15px; padding-bottom: 20px">Book Title</label><br>
	  		<input type="text" name="booktitle" id="booktitle" value= "${searchResult.bookTitle}" style="width: 315px; height: 15px; float: left; margin-top: 4px; background: #cacfd0">
	  		</div>
	  		<div style="float: left">
	  		<label style="font-family: SapientSansMedium; font-size: 15px; margin-down: 20px">Book Author</label><br>
			<input type="text" name="bookauthor" id="bookauthor" value= "${searchResult.bookAuthor}"style="width: 315px; height: 15px;  margin-top: 4px; margin-bottom: 10px;  background: #cacfd0">
			</div>
			<div style="float: left; clear: left; margin-right : 55px">  	
			<label style="font-family: SapientSansMedium; font-size: 15px;">Publisher</label><br>
	  		<input type="text" name="publisher" id="publisher" value= "${searchResult.publisher}"style="width: 315px; height: 15px;  margin-top: 4px;  background: #cacfd0">
	  		</div>	
			<div style=" float: left">
			<label style="font-family: SapientSansMedium; font-size: 15px; margin-down: 18px">Book Category</label><br>
			<select name="category" style="width: 320px; height: 21p; margin-top: 3px; margin-bottom: 20px; background: #cacfd0">
					<option value= "Select a Category+0">Select a Catgeory</option>
				<c:forEach items="${catList}" var="current">
					<option value= "${current.categoryName}+${current.categoryId}" ${current.categoryId == selectedcatid? 'selected':''}>${current.categoryName}</option>
				</c:forEach>
			</select>
			</div>
			<div style="clear: left;">	
				<div style="float: left;">
				<input type="checkbox" name="mostcomments" ${mostCommentsCheck} style="margin-left: 40px"> 
				<label style="font-family: SapientSansMedium; font-size: 15px; margin-right: 150px">Search Book With Most Comments</label>
				</div>
				<div style="float: left;" >
				<input type="checkbox" name="partnerstorecheck" ${partnerCheck}>
				<label style="font-family: SapientSansMedium; font-size: 15px;">Search in Partner Store</label>	
				</div>
			</div>
			<div style="margin-left: 265px; margin-top: 40px; clear: both;">
				<input type= "submit" value="Search" style="height: 25px; width: 130px; background-color: #33AFFF"/>
			</div>
			<div id="errorinsearch" style="color: red">
			</div>
		</form>
	  </div>
	  <!-- <div id="paginationWrap" style="width: 100%; float: left;">
	  	<ul class="pagination">
        <li><a href="javacript:void(0)" title="previous">&lt;</a></li>
        <li><a href="javacript:void(0)" title="1">1</a></li>
        <li><a href="javacript:void(0)" title="2">2</a></li>
        <li><a href="javacript:void(0)" title="3">3</a></li>
        <li><a href="javacript:void(0)" title="4">4</a></li>
        <li><a href="javacript:void(0)" title="5">5</a></li>
        <li><a href="javacript:void(0)" title="next">&gt;</a></li>
      </ul>
	  </div> -->
	  <input type="hidden" id="booksCount" value="${booksCount}"/>
      <input type="hidden" id="newBooksCount" value="${newBooksCount}"/>
      <input type="hidden" id="page" value="${page}"/>
       <input type="hidden" id="whichSearch" value="${searchBook}"/>
      <div  style="width: 100%; float: left;">
      <ul id="paginationUl" class="pagination" style="width: 30%;"></ul>
      </div>
	         <c:set var="listofbooks" scope="session" value="${bookList}"/>
             <c:if test="${empty listofbooks}">
             	${nobooks}
             </c:if> 
             <ul> 
        	  <c:forEach items="${bookList}" var="current">
              	<li><a href="/SapeStore/bookDetails?isbn=${current.isbn}" title="${current.bookTitle}">
		        		<img src="${current.bookFullImage}" onerror="this.src= '/SapeStore/img/default.png';" width="131" height="180" alt="${current.bookTitle}"/>
		        		<span>${current.bookTitle}</span> 
		            	<p>${current.bookAuthor}</p>
		            </a>
		            <c:choose>
		            <c:when test="${current.averageRating >= 0 && current.averageRating < 0.5}">
		            <figure><img src="img/ratings-0.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 0.5 && current.averageRating < 1.5}">
		            <figure><img src="img/ratings-1.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 1.5 && current.averageRating < 2.5}">
		            <figure><img src="img/ratings-2.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 2.5 && current.averageRating < 3.5}">
		            <figure><img src="img/ratings-3.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 3.5 && current.averageRating < 4.5}">
		            <figure><img src="img/ratings-4.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.averageRating >= 4.5}">
		            <figure><img src="img/ratings-5.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:when>
		            <c:otherwise>
		            <figure><img src="img/ratings-0.jpg" width="98" height="14" alt="ratings" /></figure>
		            </c:otherwise>
		            </c:choose>
		            <p class="price">$${current.bookPrice}</p>
		            <form name="addtoshoppingcartForm" action="addToShoppingCart" method="GET">
		            <c:choose>
		            <c:when test="${not empty userId}">
		           	<a href="/SapeStore/mycart/addToShoppingCart?book.isbn=${current.isbn}&book.categoryId=${categoryId}&book.categoryName=${categoryName}&book.isRent=n" title="Add To Cart" class="addCart">
		            	<img src="img/add_cart.jpg" width="15" height="13" alt="add to cart" />
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
							id="addToCart"><img src="img/add_cart.jpg" width="15" height="13" alt="add to cart" /></a>
		            </c:otherwise>
		            </c:choose>
		            </form>
	            </li>
        	</c:forEach>
           </ul>
        
        </div>
        </section>
        <div id="pageNavPosition1"  align="center"></div>
        <div   class="clearfix"></div>
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
    
var comCount = document.getElementById("booksCount").value;
var page = document.getElementById("page").value;
var whichSearch = document.getElementById("whichSearch").value;

var pageNo = parseInt(page);

var x=Math.ceil(comCount/10);

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
		var html = '<li><a href="/SapeStore/searchPage?page='+1+'" title="'+1+'">&lt;</a></li>';
		}else{
			var html = '<li><a href="/SapeStore/searchPage?page='+1+'" title="'+1+'">&lt;</a></li>';
			}
		var i = iterator;
		var lim = i+4;

		if(pageNo == 0 || pageNo == 1){
			html = html + '<li><a href="/SapeStore/searchPage?page='+1+'" title="'+1+'">Prev</a></li>';
		}else{
			html = html + '<li><a href="/SapeStore/searchPage?page='+(pageNo-1)+'" title="'+(pageNo-1)+'">Prev</a></li>';
		}
		
		for(i; i<=lim; i++){
		               if(i<=x){
		                    
		            	   if(i == parseInt(page)){
		                 	html = html + '<li><a class="sel_page" href="/SapeStore/searchPage?page='+i+'" title="'+i+'">'+i+'</a></li>';
				               }
		            	   else{
		            		   html = html + '<li><a href="/SapeStore/searchPage?page='+i+'" title="'+i+'">'+i+'</a></li>';

			            	   }
			               }
		        } 
		 if(pageNo <= (x-1)){
				html = html + '<li><a href="/SapeStore/searchPage?page='+(pageNo+1)+'" title="'+(pageNo+1)+'">Next</a></li>';
			    }
		if(i<=x){
			html = html + '<li><a href="/SapeStore/searchPage?page='+x+'" title="'+x+'">&gt;</a></li>';
		}
		document.getElementById("paginationUl").innerHTML = html;
	
}
</script>
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