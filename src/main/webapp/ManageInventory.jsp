<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SapeStore-Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

<link rel="stylesheet" href="/SapeStore/css/normalize.css">
<link rel="stylesheet" href="/SapeStore/css/main.css">
<script src="/SapeStore/js/english.js"></script>

<script src="/SapeStore/js/vendor/modernizr-2.6.2.min.js"></script>
<script src="/SapeStore/js/vendor/jquery-1.9.1.min.js"></script>
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
       background: #dfe5e6;
       padding-left: 774px;
       padding-bottom: 2em;
       padding-top: 0.5em;
       padding-right: 2px;
       padding-left: 700px;
       margin-right: 10px;
}

#pageNavPosition1 {
	position: absolute; top: 450px;  margin-right: 7px; 
}

.sel_page{
	background: #de2728;
    color: #fff !important;
}
</style>

<script type="text/javascript">   
   
function submitForm(isbn) {              
              var id = document.getElementById(isbn);
              var title = id.getElementsByClassName("bookTitle_td")[0].innerHTML.trim();
              var author = id.getElementsByClassName("bookAuthor_td")[0].innerHTML.trim();
              var publisher = id.getElementsByClassName("publisherName_td")[0].innerHTML.trim();
              var rentAvailable = id.getElementsByClassName("rentAvailable_td")[0].innerHTML.trim();
              var quantity = id.getElementsByClassName("quantity_td")[0].innerHTML.trim();
              
              var bookPrice = id.getElementsByClassName("bookPrice_td")[0].innerHTML.trim();
              var rentPrice = id.getElementsByClassName("rentPrice_td")[0].innerHTML.trim();
              var isbn = id.getElementsByClassName("isbn_td")[0].innerHTML;
              var bookShortDesc = id.getElementsByClassName("bookShortDesc_td")[0].innerHTML.trim();
              var bookDetailDesc = id
                           .getElementsByClassName("bookDetailDesc_td")[0].innerHTML.trim();
              var thumbPath = id
              .getElementsByClassName("thumbPath_td")[0].innerHTML.trim();
              
              var categoryName = id.getElementsByClassName("categoryName_td")[0].innerHTML.trim();
              
              var fullPath = id
              .getElementsByClassName("fullPath_td")[0].innerHTML.trim();
              
              var categoryId=id.getElementsByClassName("categoryId_td")[0].innerHTML.trim();
              document.getElementById("name_to_submit").value = name;
              document.getElementById("title_to_submit").value = title;
              document.getElementById("author_to_submit").value = author;
              document.getElementById("publisher_to_submit").value = publisher;
              document.getElementById("rent_to_submit").value = rentAvailable;
              document.getElementById("quantity_to_submit").value = quantity;

              document.getElementById("bookPrice_to_submit").value = bookPrice;
              document.getElementById("rentPrice_to_submit").value = rentPrice;
              document.getElementById("isbn_to_submit").value = isbn;
              document.getElementById("bookShortDesc_to_submit").value = bookShortDesc;
              document.getElementById("bookDetailDesc_to_submit").value = bookDetailDesc;
              document.getElementById("thumbPath_to_submit").value = thumbPath;
              document.getElementById("fullPath_to_submit").value = fullPath;
              document.getElementById("categoryName_to_submit").value = categoryName;
              document.getElementById("categoryId_to_submit").value = categoryId;
              document.editForm.submit();
              
       }
       

function handleSubmit(isbn)
{
    var delSubmit = document.getElementById("delSubmit");
    
       var id = document.getElementById(isbn);
       var rentAvailable = id.getElementsByClassName("rentAvailable_td")[0].innerHTML;
       var quantity = id.getElementsByClassName("quantity_td")[0].innerHTML;
       var author = id.getElementsByClassName("bookAuthor_td")[0].innerHTML;
       var rentedQuantity = id.getElementsByClassName("rentedQuantity_td")[0].innerHTML;
       var x=rentAvailable;
       var del=false;

	if(quantity!=0){
       
       if(rentAvailable.trim()=='Y')
           del=confirm("Book is available for rent. You want to delete it?");
       else
           del=confirm("Book is not available for rent. You want to delete it?");
       if(del==true){
       if(rentedQuantity==0){
           var isbn = id.getElementsByClassName("isbn_td")[0].innerHTML;
           document.getElementById("isbn_to_submit").value = isbn;
           if(confirm("Book is not rented, will be deleted"))
           delSubmit.click(); 
       }else
    	   confirm("Book is rented, can't be deleted");
       }           
	}
	else confirm("Quantity is already zero.");
    
}

</script>
</head>

<body>
       <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

       <!-- Add your site or application content here -->
       <div id="wrapper" class="homeAdmin">
              <!-- header starts-->
              <header>
                     <div id="header">
                           <a href="/SapeStore/admin/manageInventory" title="SapeStore" class="logo"><img
                                  src="/SapeStore/img/logo.jpg" width="231" height="109" alt="SapeStore">
                           </a>
                           <ul class="topLinks">
                                  <li><c:choose>
                                                <c:when test="${not empty userId}">
                                         Welcome ${username}
                                  </c:when>
                                         </c:choose></li>
                           </ul>
                           <!-- in case of admin hide this and display another one -->
                           <nav>
                                  <ul class="nav">
                                                
                                  <li class="active"><a href="/SapeStore/admin/manageInventory">Manage Inventory</a></li> 

                                  <li><a href="/SapeStore/admin/manageOrders" >Manage Orders</a></li> 

                                  <li><a href="/SapeStore/admin/adminReport">Manage Reports</a></li> 

                                  <li><a href="/SapeStore/admin/managePages" >Manage Pages</a></li> 

                                         <li><a href="/SapeStore/logout">Logout</a></li>
                                  </ul>
                                  
                           </nav>
                     </div>

              </header>
              <!-- header ends -->
              <section>

                     <div id="mainContent" style="padding-right: 20px; width: 950px;">
                           <h2>Inventory Summary</h2>

						  <input type="hidden" id="booksCount" value="${numberOfRecords}"/>
					      <input type="hidden" id="page" value="${page}"/>
                           <form:form name="addBooks" action="/SapeStore/admin/addBooksAdmin"
                                  method="POST">
                                  <input type="submit" value="Add a book"
                                         style="height: 25px; font-weight: bold; font-size: initial; width: 106px; background-color: #21addd; color: white; height: 30px">
                           </form:form>

                           <form:form action="/SapeStore/editBooks"  method="POST" name="editForm" commandName="updateBooks">
                                         <input type="hidden" name="name" id="name_to_submit"/>
                                         <input type="hidden" name="bookTitle" id="title_to_submit"/>
                                         <input type="hidden" name="bookAuthor" id="author_to_submit"/>
                                         <input type="hidden" name="publisherName" id="publisher_to_submit"/>
                                         <input type="hidden" name="rentAvailable" id="rent_to_submit"/>
                                         <input type="hidden" name="quantity" id="quantity_to_submit"/>
                                         <input type="hidden" name="bookPrice" id="bookPrice_to_submit"/>
                                         <input type="hidden" name="rentPrice" id="rentPrice_to_submit"/>
                                         <input type="hidden" name="isbn" id="isbn_to_submit"/>
                                         <input type="hidden" name="bookShortDesc" id="bookShortDesc_to_submit"/>
                                         <input type="hidden" name="bookDetailDesc" id="bookDetailDesc_to_submit"/>
                                         <input type="hidden" name="thumbPath" id="thumbPath_to_submit"/>
                                         <input type="hidden" name="categoryName" id="categoryName_to_submit"/>
                                         <input type="hidden" name="fullPath" id="fullPath_to_submit"/>
                                         <input type="hidden" name="categoryId" id="categoryId_to_submit"/>
                                  
                                  <table id="tablepaging" class="yui">

                                         <thead>
                                                <tr style="border-bottom: dashed 1px #b7bcbd">
                                                       <th><a href="/SapeStore/admin/category" style="color: #000000">Category </a></th>
                                                       <th><a href="/SapeStore/admin/bookname" style="color: #000000">Book Name</a></th>
                                                       <th><a href="/SapeStore/admin/author" style="color: #000000">Author</a></th>
                                                       <th><a href="/SapeStore/admin/publisher" style="color: #000000">Publisher</a></th>
                                                       <th style="text-align: center">For Rent</th>
                                                       <th><a href="/SapeStore/admin/quantity" style="color: #000000">Quantity</a></th>
                                                       <th colspan="2" style="text-align: center;">Action</th>
                                                </tr>
                                         </thead>


                                         <c:forEach items="${adminInventoryList}" var="current">
                                                
                                                <c:if test="${current.quantity<=5 }">
                                                
                                                <tr id="${current.isbn}" style="height: 60px;color: #FF0000 ">
                                                              <td class="categoryName_td" style="width: 120px; word-break: break-word">${current.categoryName}
                                                              </td>
                                                              <td class="bookTitle_td" style="width: 110px; word-break: break-word">${current.bookTitle}
                                                              </td>
                                                              <td class="bookAuthor_td"
                                                                     style="width: 110px; word-break: break-word">${current.bookAuthor}
                                                              </td>
                                                              <td class="publisherName_td"
                                                                     style="width: 110px; word-break: break-word">${current.publisherName}
                                                              </td>
                                                              <td class="rentAvailable_td" style="text-align: center; width: 190px">${current.rentAvailable}
                                                              </td>
                                                              <td class="quantity_td" style="text-align: center; width: 95px; word-break: break-word">${current.quantity}
                                                              </td>

                                                          <td style="display: none" class="rentedQuantity_td" >${current.rentedQuantity}
                                                              </td> 
                                                              <td style="display: none" class="rentPrice_td">${current.rentPrice}
                                                              </td>
                                                              <td style="display: none" class="categoryId_td">${current.categoryId}
                                                              </td>
                                                              <td style="display: none" class="isbn_td">${current.isbn}</td>
                                                              <td style="display: none" class="bookShortDesc_td">${current.bookShortDesc}
                                                              </td>
                                                              <td style="display: none" class="bookDetailDesc_td">${current.bookDetailDesc}
                                                              </td>
                                                              <td style="display: none" class="thumbPath_td">${current.thumbPath}
                                                              </td>
                                                              <td style="display: none" class="fullPath_td">${current.fullPath}
                                                              </td>
                                                              <td style="display: none" class="bookPrice_td">${current.bookPrice}</td> 
                                                              <td style="float: inherit;">
                                                                     <button type="submit" name="editBook" title="Edit"
                                                                            onclick="submitForm('${current.isbn}')">
                                                                           <img src="/SapeStore/img/editnew.png" width="20" height="20"
                                                                                  align="right">
                                                                     </button>


                                                              </td>
                                                              <td style="float: inherit;"><button type="button"
                                                                           name="deleteBook" title="Delete"
                                                                            onclick="handleSubmit('${current.isbn}');">
                                                                           <img src="/SapeStore/img/deletenew4.png" width="20" height="20"
                                                                                  align="right">
                                                                     </button></td>
                                                       <td><input type="submit" name="delSubmit" id="delSubmit"
										hidden></td>    
                                                       
                                                       </tr>
                                                </c:if>
                                                <c:if test="${current.quantity>5 }">
                                                       <tr id="${current.isbn}" style="height: 60px">
                                                              <td class="categoryName_td" style="width: 120px; word-break: break-word">${current.categoryName}
                                                              </td>
                                                              <td class="bookTitle_td" style="width: 110px; word-break: break-word">${current.bookTitle}
                                                              </td>
                                                              <td class="bookAuthor_td"
                                                                     style="width: 110px; word-break: break-word">${current.bookAuthor}
                                                              </td>
                                                              <td class="publisherName_td"
                                                                     style="width: 110px; word-break: break-word">${current.publisherName}
                                                              </td>
                                                              <td class="rentAvailable_td"
                                                                     style="text-align: center; width: 190px">${current.rentAvailable}
                                                              </td>
                                                              <td class="quantity_td"
                                                                     style="text-align: center; width: 95px; word-break: break-word">${current.quantity}
                                                              </td>
<td style="display: none" class="rentedQuantity_td" >${current.rentedQuantity}
                                                              </td> 
                                                              <td style="display: none" class="bookPrice_td">${current.bookPrice}</td>

                                                              <td style="display: none" class="rentPrice_td">${current.rentPrice}
                                                              </td>
                                                              
                                                               <td style="display: none" class="categoryId_td">${current.categoryId}
                                                              </td>
                                                              <td style="display: none" class="isbn_td">${current.isbn}</td>
                                                              <td style="display: none" class="bookShortDesc_td">${current.bookShortDesc}
                                                              </td>
                                                              <td style="display: none" class="bookDetailDesc_td">${current.bookDetailDesc}
                                                              </td>
                                                              <td style="display: none" class="thumbPath_td">${current.thumbPath}
                                                              </td>
                                                              <td style="display: none" class="fullPath_td">${current.fullPath}
                                                              </td>
                                                              <td style="float: inherit;">
                                                                     <button type="submit" name="editBook" title="Edit"
                                                                            onclick="submitForm('${current.isbn}')">
                                                                           <img src="/SapeStore/img/editnew.png" width="20" height="20"
                                                                                  align="right">
                                                                     </button>


                                                              </td>
                                                              <td style="float: inherit;"><button type="button"
                                                                           name="deleteBook" title="Delete"
                                                                            onclick="handleSubmit('${current.isbn}');">
                                                                           <img src="/SapeStore/img/deletenew4.png" width="20" height="20"
                                                                                  align="right">
                                                                     </button></td>
															<td><input type="submit" name="delSubmit" id="delSubmit"
										hidden></td>    
															
                                                       </tr>
                                                </c:if>

                                         </c:forEach>
                                  </table>

                                  <%-- <div id="pageNavPosition" style="color: #FF0000">
                                  <c:forEach var="current" begin="0" end="${numberOfRecords/10}" step="1">
                                  <a href="/SapeStore/admin/viewbooks/${current+1}" style="color: #FF0000">${current+1}</a>&nbsp;&nbsp;
                                  </c:forEach>
                                  </div> --%>
                                  
      							<div id="pageNavPosition" style="color: #FF0000">
                     <ul id="paginationUl" class="pagination" style="width: 100%;"></ul>
                                  
                                  </div>
                           </form:form>

                     </div>
              </section>
              <div class="clearfix"></div>
              <footer>
                     <div id="footer">

                           <div>
                                  Powered by <img src="/SapeStore/img/sapient_nitro.jpg" width="78" height="14"
                                         alt="sapient nitro">
                           </div>
                     </div>
              </footer>
       </div>

<script>
var comCount = document.getElementById("booksCount").value;
var page = document.getElementById("page").value;

var pageNo = parseInt(page);

console.log("Total books count: "+comCount);
var x=Math.ceil(comCount/10);
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
	var html = '<li><a href="/SapeStore/admin/viewbooks?page='+iterator+'" title="'+iterator+'">&lt;</a></li>';
}else{
	var html = '<li><a href="/SapeStore/admin/viewbooks?page='+(iterator-1)+'" title="'+(iterator-1)+'">&lt;</a></li>';
	}
var i = iterator;
var lim = i+4;
if(pageNo == 0 || pageNo == 1){
html = html + '<li><a href="/SapeStore/admin/viewbooks?page='+1+'" title="'+1+'">Prev</a></li>';
}else{
	html = html + '<li><a href="/SapeStore/admin/viewbooks?page='+(pageNo-1)+'" title="'+(pageNo-1)+'">Prev</a></li>';
	}
for(i; i<=lim; i++){ 
               if(i<=x){
                    if(i == parseInt(page)){
                 		html = html + '<li><a class="sel_page" href="/SapeStore/admin/viewbooks?page='+i+'" title="'+i+'">'+i+'</a></li>';
	                    
	                }
                    else{
                 		html = html + '<li><a href="/SapeStore/admin/viewbooks?page='+i+'" title="'+i+'">'+i+'</a></li>';
                    }
 	           }
        } 
if(pageNo <= (x-1)){
html = html + '<li><a href="/SapeStore/admin/viewbooks?page='+(pageNo+1)+'" title="'+iterator+'">Next</a></li>';
}
if(i<=x){
	html = html + '<li><a href="/SapeStore/admin/viewbooks?page='+i+'" title="'+i+'">&gt;</a></li>';
}
document.getElementById("paginationUl").innerHTML = html;
}


</script>

       <!-- <script>
                 document.getElementById("maincontent").innerHTML = inventory summary;
             document.getElementById("category").innerHTML = category;
             document.getElementById("bookname").innerHTML = bookname;
             document.getElementById("author").innerHTML = author;
             document.getElementById("publisher").innerHTML = publisher;
             document.getElementById("forrent ").innerHTML = forrent;
             document.getElementById("action").innerHTML = action;
            
      </script>
-->


</body>
</html>
