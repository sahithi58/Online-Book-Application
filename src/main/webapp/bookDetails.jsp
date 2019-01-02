<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore-Book Detail</title>
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
.rating-static {
  width: 60px;
  height: 16px;
  display: block;
  background: "/SapeStore/img/ratings.jpg";
}
.reviewtext {
	font-size: 12px;
	color: #1d1d1d;
	face: SapientSansRegular;
}
</style>


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


</head>

<body>



	<div id="wrapper">
		<div id="shoppingCartContainer" style="display: none">
			<div id="shoppingCart" class="popup">
				<jsp:include page="DisplayShoppingCart.jsp" flush="true" />
			</div>
		</div>
		               <div id="writecommentcontainer" style="display: none">
                     <div id="writecomment" class="popup">
                            <jsp:include page="Comment.jsp" flush="true" /> 
                     </div>
              </div> 
	
		<form:form name="form" action="/SapeStore/bookListByCat?categoryId=${categoryId}&categoryName=${categoryName}&checkMe = false" method="GET" commandName="welcome">
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
						<label for="checkMe" style="font-size: 13px;" href="#">Include
							books from Partner Store</label> 
							<input type="hidden" name="categoryId" value="${bookDetails.categoryId}" /> 
							<input type="hidden" name="categoryName" value="${bookDetails.categoryName}" />
					</li>
					<li><a class='inline' href="#shoppingCart"><img
							src="/SapeStore/img/icon_cart.jpg" width="15" height="12" alt="cart"></a></li>
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
				<li><a href="/SapeStore/mycart/wishlist">Wishlist</a> </li></c:when>
				
				<c:otherwise>
		           <li> <a href="#login" class="inline">Wishlist</a></li>
		           
		            </c:otherwise>
		            </c:choose> 
					<jsp:include page="Logout.jsp" flush="true" />
					<li><a href="/SapeStore/searchPage?checkMe=${checkMe}"><img alt="searchImage" src="/SapeStore/img/magnifier-icon.png"></a></li>
					
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
         		<c:if test="${bookDetails.categoryName==current.categoryName}">
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
		
	<div id="mainContent">
	<table>
		<tr>
			<td><div id="imgdiv" style="height: 250px; width: 152px;">
			<img src="/SapeStore/${bookDetails.thumbPath}" width="152" height="222" alt="${bookDetails.bookTitle}" onerror="this.src= '/SapeStore/img/default.png'"/></div>
			</td>
			<td><div id="desc" style="height: 250px; width: 350px;">
				<table>
					<tr>
						<td style="font-size:20px; color:#000000; face:SapientCentroSl;">${bookDetails.bookTitle}</td>
					</tr>
					<tr>
						<td style="font-size:12px; color:#000000; face:SapientSansRegular;">${bookDetails.bookAuthor}</td>
					</tr>
					<tr>
						<td>
						<c:choose>
		            	<c:when test="${bookDetails.averageRating >= 0 && bookDetails.averageRating < 0.5}">
		            	<figure><img src="/SapeStore/img/ratings-0.jpg" width="98" height="14" style="float:left;" alt="ratings" /></figure>
		            	</c:when>
		            	<c:when test="${bookDetails.averageRating >= 0.5 && bookDetails.averageRating < 1.5}">
		            	<figure><img src="/SapeStore/img/ratings-1.jpg" width="98" height="14" style="float:left;" alt="ratings" /></figure>
		            	</c:when>
		            	<c:when test="${bookDetails.averageRating >= 1.5 && bookDetails.averageRating < 2.5}">
		            	<figure><img src="/SapeStore/img/ratings-2.jpg" width="98" height="14" style="float:left;" alt="ratings" /></figure>
		            	</c:when>
		            	<c:when test="${bookDetails.averageRating >= 2.5 && bookDetails.averageRating < 3.5}">
		            	<figure><img src="/SapeStore/img/ratings-3.jpg" width="98" height="14" style="float:left;" alt="ratings" /></figure>
		            	</c:when>
		            	<c:when test="${bookDetails.averageRating >= 3.5 && bookDetails.averageRating < 4.5}">
		            	<figure><img src="/SapeStore/img/ratings-4.jpg" width="98" height="14" style="float:left;" alt="ratings" /></figure>
		            	</c:when>
		            	<c:when test="${bookDetails.averageRating >= 4.5}">
		            	<figure><img src="/SapeStore/img/ratings-5.jpg" width="98" height="14" style="float:left;" alt="ratings" /></figure>
		            	</c:when>
		            	<c:otherwise>
		            	<figure><img src="/SapeStore/img/ratings-0.jpg" width="98" height="14" style="float:left;" alt="ratings" /></figure>
		            	</c:otherwise>
		            	</c:choose>
		            	<div style="display: block; font-size:10px; width:250px;">
		            		<span class="reviewtext" style="font-size:10px;">${ratings} Ratings</span>
		            		<a href="/SapeStore/readAll/${bookDetails.isbn}" style="font-size:10px; color:#ff0000; face:SapientSansRegular;">${ratings} Reviews</a>
		            	</div>
		            	</td>
					</tr>
					<tr>
						<td style="font-size:10px; color:#000000; face:SapientSansRegular;">Publisher: ${bookDetails.publisherName}</td>
					</tr>
					<tr>
                                         <td>
                                         <c:choose>
                           <c:when test="${not empty userId}">
                                         <a href="/SapeStore/mycart/addToShoppingCart?book.isbn=${bookDetails.isbn}&book.categoryId=${bookDetails.categoryId}&book.categoryName=${bookDetails.categoryName}&book.isRent=n">
                                         <input type="button" name = "buy" style="height: 25px; width: 125px; background-color: #21addd; color:white; face:SapientSansRegular" value="Buy for $ ${bookDetails.bookPrice}" class="addCart"/></a></c:when>
                                         <c:otherwise>
                          <a href="#login" class="inline"><input type="button" name = "buy" style="height: 25px; width: 125px; background-color: #21addd; color:white; face:SapientSansRegular" value="Buy for $ ${bookDetails.bookPrice}" class="addCart"/></a>
                         
                          </c:otherwise>
                          </c:choose>
                                         
                                         <c:choose>
                           <c:when test="${not empty userId}">
                                         <span><a href="/SapeStore/mycart/addToShoppingCart?book.isbn=${bookDetails.isbn}&book.categoryId=${bookDetails.categoryId}&book.categoryName=${bookDetails.categoryName}&book.isRent=n">
                                         <img src="/SapeStore/img/add_cart.jpg" width="20" height="20" alt="add to cart" /></a></span></c:when>
                                         <c:otherwise>
                         <a href="#login" class="inline"><img src="/SapeStore/img/add_cart.jpg" width="20" height="20" alt="add to cart" /></a>
                         
                         </c:otherwise>
                         </c:choose>
                                         </td>
                                  </tr>
                                  <c:if test="${bookDetails.rentAvailable=='Y'}">
                                  <tr>
                                         <td>
                                         <c:choose>
                           <c:when test="${not empty userId}">
                                         <a href="/SapeStore/mycart/addToShoppingCart?book.isbn=${bookDetails.isbn}&book.categoryId=${bookDetails.categoryId}&book.categoryName=${bookDetails.categoryName}&book.isRent=y">
                                         <input type="button" name = "rent" style="height: 25px; width: 125px; background-color: #21addd; color:white; face:SapientSansRegular" value="Rent for $ ${bookDetails.rentPrice}" class="addCart"/></a></c:when>
                                         <c:otherwise>
                          <a href="#login" class="inline"><input type="button" name = "rent" style="height: 25px; width: 125px; background-color: #21addd; color:white; face:SapientSansRegular" value="Rent for $ ${bookDetails.rentPrice}" class="addCart"/></a>
                         
                         </c:otherwise>
                         </c:choose>
                                         
                                         <c:choose>
                           <c:when test="${not empty userId}">
                                         <span><a href="/SapeStore/mycart/addToShoppingCart?book.isbn=${bookDetails.isbn}&book.categoryId=${bookDetails.categoryId}&book.categoryName=${bookDetails.categoryName}&book.isRent=y">
                                         <img src="/SapeStore/img/add_cart.jpg" width="20" height="20" alt="add to cart" /></a></span></c:when>
                                         <c:otherwise>
                          <a href="#login" class="inline"><img src="/SapeStore/img/add_cart.jpg" width="20" height="20" alt="add to cart" /></a>
                         </c:otherwise>
                         </c:choose>
                                         </td>
                                  </tr>
</c:if>
				</table>
			</div>
			</td>
			<td> <div id="buttons" style="height: 250px; width: 222px;">
				<c:choose>
				<c:when test="${not empty userId}">
				<a href="mycart/addToWishList?book.isbn=${bookDetails.isbn}" class="one tooltip" title="Add to wish list" style="color:red ; font-size: 10px";><span>&#43;</span><u>Add to Wish list</u></a><span style="font-size: 10px";>|</span>  </c:when>
				
				<c:otherwise>
		            <a href="#login" class="inline" title="Add to wish list" style="color:red ; font-size: 10px";><span>&#43;</span><u>Add to Wish list</u></a><span style="font-size: 10px";>|</span>
		           
		            </c:otherwise>
		            </c:choose>
				
				
				
				<!-- <a href="#writecomment" title="Write Review" class ="inline cboxElement" style="color:red ; font-size: 10px";><span>&#9999;</span><u>Write a Review</u></a> -->
				<%-- <a href="/SapeStore/postcomment?book.isbn=${bookDetails.isbn}&book.bookTitle=${bookDetails.bookTitle}&book.bookAuthor=${bookDetails.bookAuthor}&book.thumbPath=${bookDetails.thumbPath }"  style="color:red ; font-size: 10px;"><span>&#9999;</span>Write a Review</a> --%>
				<c:choose>
				<c:when test="${not empty userId}">
				<a href="#writecomment" title="Write Review" class ="inline cboxElement" style="color:red ; font-size: 10px";><span>&#9999;</span><u>Write a Review</u></a></c:when>
					<c:otherwise>
		            <a href="#login" class="inline" title="Add to wish list" style="color:red ; font-size: 10px";><span>&#43;</span><u>Write a Review</u></a>
		           
		            </c:otherwise>
		            </c:choose>
				</div>
			</td>
		</tr>
	</table>
	
	<table>
		<tr>
			<td style="font-size: 22px; color:#000000; face:SapientCentroSl;">Description</td>
		</tr>
		<tr>
			<td><p>${bookDetails.bookDetailDesc}</p></td>
		</tr>
	</table>
	<table>
		<tr>
			<td style="font-size: 22px; color: #000000; face: SapientCentroSl;">Customer Reviews
			<a href="/SapeStore/readAll/${bookDetails.isbn}" style="color:red ; font-size: 10px;">Read All</a></td>
		</tr>
		<c:forEach items="${bookComments}" var="current" end="3">
		<tr>
			<td colspan="3" style="font-size: 14px; color: #000000; face: SapientSansRegular; text-align: left;">
			<div style="width: 400px;">
			<div style="display: inline-block; width: 20%;"><b>${current.userId}</b></div>
			<div style="display: inline-block; width: 25%;">
			<c:choose>
		            <c:when test="${current.bookRating >= 0 && current.bookRating < 0.5}">
		            <figure><img src="/SapeStore/img/ratings-0.jpg" width="98" height="14" style="float:right;" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.bookRating >= 0.5 && current.bookRating < 1.5}">
		            <figure><img src="/SapeStore/img/ratings-1.jpg" width="98" height="14" style="float:right;" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.bookRating >= 1.5 && current.bookRating < 2.5}">
		            <figure><img src="/SapeStore/img/ratings-2.jpg" width="98" height="14" style="float:right;" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.bookRating >= 2.5 && current.bookRating < 3.5}">
		            <figure><img src="/SapeStore/img/ratings-3.jpg" width="98" height="14" style="float:right;" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.bookRating >= 3.5 && current.bookRating < 4.5}">
		            <figure><img src="/SapeStore/img/ratings-4.jpg" width="98" height="14" style="float:right;" alt="ratings" /></figure>
		            </c:when>
		            <c:when test="${current.bookRating >= 4.5}">
		            <figure><img src="/SapeStore/img/ratings-5.jpg" width="98" height="14" style="float:right;" alt="ratings" /></figure>
		            </c:when>
		            <c:otherwise>
		            <figure><img src="/SapeStore/img/ratings-0.jpg" width="98" height="14" style="float:right;" alt="ratings" /></figure>
		            </c:otherwise>
		    </c:choose>
		    </div>
		    
			<div style="display: inline-block; width: 50%;float: right; font-size:12px; color:#000000; face:SapientSansRegular;">${current.bookCommentDate}</div>
			</div>
			</td>
		</tr>
		<tr>
		<td>
		<p>${current.bookComments}</p> 
		</td>
		</tr>
		</c:forEach>
		</table>
	</div>
	
	<%-- <div style="display:none;" >
	<div id= "writecomment">
    	<h2>Write a Review</h2>
  	<div class="leftcol">
       	<nav>
      	<ul>
        	<li> <img src="img/book.jpg" alt="product name"></li>
        	<li style="font-size:15px; color:#000000; face:SapientCentroSl;">${bookDetails.bookTitle}</li>
        	<li  style="font-size:18px; color:#000000; face:SapientSansRegular;">${bookDetails.bookAuthor}</li>
     	 </ul>
    	</nav>
    	 </div>
    <div id="maincontent">               
        <form method="post" action="addReviews?isbn=${bookDetails.isbn}" class="popup">
            <fieldset>

              <label for="comment" style="font-size: 10px;" >Your Comments:</label><br>
              <textarea  rows="4" cols="40" name="comment"></textarea><br><br>
               <label for="rating" style="font-size: 10px;">Your Rating:</label>
                <div class="rating" style="overflow:hidden;">
                <input type="radio" class="rating-input" id="rating-input-1-5" name="rating" value="1">
                <label for="rating-input-1-5" class="rating-star"></label>
                <input type="radio" class="rating-input" id="rating-input-1-4" name="rating" value="2">
                <label for="rating-input-1-4" class="rating-star"></label>
                <input type="radio" class="rating-input"  id="rating-input-1-3" name="rating" value="3">
                <label for="rating-input-1-3" class="rating-star"></label>
                <input type="radio" class="rating-input" id="rating-input-1-2" name="rating" value="4">
                <label for="rating-input-1-2" class="rating-star"></label>
                <input type="radio" class="rating-input" id="rating-input-1-1" name="rating" value="5">
                <label for="rating-input-1-1" class="rating-star"></label></div>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="Post Comment" class="lightButton"><a href="bookDetails/${bookDetails.isbn}" title="cancel" style="color:red ; font-size: 15px;">Cancel Post</a>
            </fieldset>
          </form> 
    </div>
    </div>
</div> --%>
	
	<footer>
  
     <div id="footer">
     	<div style=" float: left; margin-left: 386px;">
     		<a href="/SapeStore/contactUsCustomer" style="color: #21addd;">Contact Us</a>
       	</div>
       	<div style="float: left;margin-left: 6px; color: #21addd"> | </div>
       	
       	<div style="float: left;margin-left: 7px;">
 			<a href="/SapeStore/policyCustomer" style="color: #21addd;">Privacy Policy</a>
  		</div>
  		<div>Powered by <img src="/SapeStore/img/sapient_nitro.jpg" width="78" height="14" alt="sapient nitro"></div>
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



	<%-- <div id="bookDisplay" style="float: left">
		<img style="float:left;" height="222" width="152" src="${book.book_thumb_image}"/>
	</div>
	<div id=bookDisplay" style="float: left">
		
	</div>
	 --%>
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