<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page
       import="java.util.*,java.io.*,com.sapestore.hibernate.entity.BookCategory,com.sapestore.controller.ProductController"%>

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

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<script
       src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>


$(document).ready(function(){
       $("#manageInv").css({
              position: 'relative',
              right: '24em',
              width: '8em',
              height: '30px',
              'background-color': '#21addd',
              color: 'white',
       });
       
       $("#update").css({
              position: 'relative',
              width: '8em',
              height: '30px',
              top: '1px',
              'background-color' : '#21addd',
              color: 'white',
       });
});

function submitForm() {
       $(document).ready(function(){

              
              $("#quantity_id").after('<span class="error"></span>');
              $("#bookPrice_id").after('<span class="error"></span>');
              $("#rentPrice_id").after('<span class="error"></span>');
              $("#bookTitle_id").after('<span class="error"></span>');
              $("#bookAuthor_id").after('<span class="error"></span>');
              $("#isbn_id").after('<span class="error"></span>');
              $("#publisherName_id").after('<span class="error"></span>');
              $("#bookShortDesc_id").after('<span class="error"></span>');
              $("#bookDetailDesc_id").after('<span class="error">"</span>');
              document.getElementById("thumbImageError").innerHTML = "";
              document.getElementById("fullImageError").innerHTML = "";
            
        

              
              
              var flag=true;
              $(".error").remove();
           var characterReg = /^[1-9]\d*$/;
           var priceReg = /^([1-9]*[.])?[0-9]*$/;
           var priceRegg = /^-{0,1}\d*\.{0,1}\d+$/;
           
           var nameReg = /^[a-zA-Z0-9 ]{1,100}$/;
           var bookTitleReg = /^[a-zA-Z0-9!:@#$%^&* ]{1,100}$/;
           var isbnNameReg = /^[a-zA-Z0-9-]{1,100}$/;
           var alphaReg=/^[.a-zA-Z ]{1,100}$/;
         //  var detailsReg=/^[-.,a-zA-Z0-9 ]{1,150}$/;
           
           var quantityVal= $("#quantity_id").val();
           var quantityValT=quantityVal.trim();
           if(!characterReg.test(quantityValT)) {
               $("#quantity_id").after('<span class="error">  Error007: Please provide book Quantity.</span>');
               flag=false;
           }
           
           var bookPrice= $("#bookPrice_id").val();
           var bookPriceT=bookPrice.trim();
           if(bookPriceT =="") {
               $("#bookPrice_id").after('<span class="error"> Error006: Please provide Selling Price.</span>');
               flag=false;
           }else if(!characterReg.test(bookPriceT) || parseInt(bookPrice)<0) {
               $("#bookPrice_id").after('<span class="error"> Error006: Invalid  selling  price</span>');
               flag=false;
           }


           var r=$("#rentAvailable_id").val().trim();
           var rentPrice= $("#rentPrice_id").val();
           var rentPriceT=rentPrice.trim();
           if((rentPriceT=="") && (r=="Y")) {
               $("#rentPrice_id").after('<span class="error"> Error012:   Please provide Rent Price.</span>');
               flag=false;
           }else if((!characterReg.test(rentPriceT)  || parseInt(rentPrice)<0   || (parseInt(rentPrice*4)>parseInt(bookPrice))) && (r=="Y")) {
               $("#rentPrice_id").after('<span class="error"> Error012: Invalid  rent price.</span>');
               flag=false;
           }  
  
         
           var bookTitle= $("#bookTitle_id").val();
           var bookTitleT=bookTitle.trim();
           if(!bookTitleReg.test(bookTitleT)) {
               $("#bookTitle_id").after('<span class="error"> Error003: Please provide book Title.</span>');
               flag=false;
           }
           
           var bookAuthor= $("#bookAuthor_id").val();
           var bookAuthorT=bookAuthor.trim();
           if(!alphaReg.test(bookAuthorT)) {
               $("#bookAuthor_id").after('<span class="error">       Error004: Please provide book Author.</span>');
               flag=false;
           }
           
           var isbn= $("#isbn_id").val();
           var isbnT=isbn.trim();
           if(!isbnNameReg.test(isbnT)) {
               $("#isbn_id").after('<span class="error">      Error009: Please provide book ISBN.</span>');
               flag=false;
           }

           var publisherName= $("#publisherName_id").val();
           var publisherNameT=publisherName.trim();
           if(!nameReg.test(publisherNameT)) {
               $("#publisherName_id").after('<span class="error">    Error010: Please provide book publisher.</span>');
               flag=false;
           }
           
           var bookShortDesc= $("#bookShortDesc_id").val();
           var bookShortDescT=bookShortDesc.trim();
           if(bookShortDescT.length==0) {
               $("#bookShortDesc_id").after('<span class="error"> Error008: Please provide Short Description.</span>');
               flag=false;
           }
           if(bookShortDescT.length>150) {
               $("#bookShortDesc_id").after('<span class="error">    Maximum 150 characters."</span>');
               flag=false;
           }
           
           var bookDetailDesc= $("#bookDetailDesc_id").val();
           var bookDetailDescT=bookDetailDesc.trim();
           if(bookDetailDescT.length>500) {
               $("#bookDetailDesc_id").after('<span class="error">   Maximum 500 characters."</span>');
               flag=false;
           }

           if (jQuery('#thumbImagefile').val() == "" && jQuery('#thumbImage').attr('src') == "") {
               document.getElementById("thumbImageError").innerHTML = "Please provide book thumbnail image.";
               flag=false;

        }
        if (jQuery('#fullImagefile').val() == "" && jQuery('#fullImage').attr('src') == "") {
               document.getElementById("fullImageError").innerHTML = "Please provide book full image.";
               flag=false;
        } 
           
           /* var fileinput = document.getElementById("fullImage");
           if(fileinput.value==null) {
               //alert("image is mandatory");
               flag=false;
           } */
          
           
           if(flag==true){
                     document.updateForm.submit();
                    }
});
}
</script>
<script type="text/javascript">
function dis_able()
{      
       var r=$("#rentAvailable_id").val().trim();
       if(r=="N"){
              document.getElementById("rentPrice_id").disabled=true;
       }
       else{
              document.getElementById("rentPrice_id").disabled=false;
       }
}
/* function HandleBrowseClick()
{
    var fileinput = document.getElementById("thumbImage");
    fileinput.click();
} */
function Handlechange()
{
var fileinput = document.getElementById("thumbImage");
if (fileinput) {
       var startIndex = (fileinput.indexOf('\\') >= 0 ? fileinput.lastIndexOf('\\') : fileinput.lastIndexOf('/'));
       var filename = fileinput.substring(startIndex);
       if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
              filename = filename.substring(1);
       }
       var textinput = document.getElementById("filename");
       textinput.value = filename.value;
}

}

/* function HandleBrowseClickFullImage()
{
    var fileinput = document.getElementById("fullImage");
    fileinput.click();
} */
function HandlechangeFullImage()
{
var fileinput = document.getElementById("fullImage");
if (fileinput) {
       var startIndex = (fileinput.indexOf('\\') >= 0 ? fileinput.lastIndexOf('\\') : fileinput.lastIndexOf('/'));
       var filename = fileinput.substring(startIndex);
       if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
              filename = filename.substring(1);
       }
       var textinput = document.getElementById("filenameFullImage");
       textinput.value = filename.value;
}

}

function goBack() {
    window.history.back();
}
</script>

<script type="text/javascript">
function loadThumb(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#thumbImage')
                .attr('src', e.target.result)
                .width(81)
                .height(112);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
</script>

<script type="text/javascript">
function loadFullImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#fullImage')
                .attr('src', e.target.result)
                .width(81)
                .height(112);
        };

        reader.readAsDataURL(input.files[0]);
    }
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
                                  src="img/logo.jpg" width="231" height="109" alt="SapeStore">
                           </a>
                           <ul class="topLinks hide">
                                  <li><input name="include_books" type="checkbox"
                                         value="include_books" checked> <a
                                         title="Add books from Partner Store" href="#">Include books
                                                from Partner Store</a></li>
                                  <li><a class='inline' href="#shoppingCart"><img
                                                src="img/icon_cart.jpg" width="15" height="12" alt="cart">
                                  </a></li>
                                  <li class="cartNum">0</li>
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
                     <div id="mainContent" class="addAddress">
                           <h2>Edit Book Details</h2>
                           <div class="clearfix"></div>
                           <form:form id="fid" name="updateForm" action="/SapeStore/updateInventory" enctype="multipart/form-data" method="post" commandName="updateInv">
                           <table>
                                  <fieldset>
                                  
                                  <div>
                                         <tr>
                                  <td class="tdLabel"><label for="thumbImage_id" class="label">Book Thumbnail Image<span class="required">*</span></label></td>
                                  <td>
                                         <img alt="Book Thumbnail" id="thumbImage" width="81" height="112" src="${updateBooks.thumbPath}">
                                         <!-- <input type="button" value="Browse Image" id="fakeBrowse" name="fakeBrowse" onclick="HandleBrowseClick();"/> -->
                                                <label id="filename" style="font-size: 13px;"></label>
                                         <input type="file" id="thumbImagefile" name="thumbImage" value="${updateBooks.thumbPath}" accept="image/gif, image/jpeg, image/jpg, image/png, image/bmp" id="thumbImage_id" placeholder="Book Thumbnail Image" style="opacity: 1" onchange="loadThumb(this);"/>
                                  	<%-- <input type="text" value="${updateBooks.thumbPath}" />
                                  	<input type="text" value="${updateBooks.fullPath}" /> --%>
                                  </td>
                                  <td><div id="thumbImageError" style="color: #FF0000"></div></td>
                                         </tr>
                                         </div>
                                         <td class="tdLabel"><label for="fullImage" class="label">Book Detail Image<span class="required">*</span></label></td>
                                         <td>
                                                <img alt="Book Detail Image" id="fullImage" width="81" height="112" src="${updateBooks.fullPath}">
                                         <!--   <input type="button" value="Browse Image" id="fakeBrowsefullImage" name="fakeBrowsefullImage" onclick="HandleBrowseClickFullImage();"/> -->
                                                <label id="filenameFullImage" style="font-size: 13px;"></label>
                                                <input type="file" id="fullImagefile" name="fullImage" value="" accept="image/gif, image/jpeg, image/jpg, image/png, image/bmp" id="fullImage" placeholder="Book Detail Image" required="required" style="opacity: 1" onchange="loadFullImage(this);"/>
                                         </td>
                                         <td><div id="fullImageError" style="color: #FF0000"></div></td>
                                         </tr>
                                         </div>
                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="bookTitle_id" class="label">Book Title<span class="required">*</span></label></td>
                                         <td><input type="text" name="bookTitle" value="${updateBooks.bookTitle}" id="bookTitle_id" placeholder="Book title"/></td>
                                                </tr>
                                         </div>
                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="bookAuthor_id" class="label">Book Author<span class="required">*</span></label></td>
                                                <td><input type="text" name="bookAuthor" value="${updateBooks.bookAuthor}" id="bookAuthor_id" placeholder="Book Author"/></td>
                                                </tr>
                                         </div>
                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="isbn_id" class="label">ISBN<span class="required">*</span></label></td>
                                                <td><input type="text" name="isbn" value="${updateBooks.isbn}" id="isbn_id" placeholder="ISBN" readonly/>
                                                       ${errormessage}<input type="hidden" id="oldIsbn" name="oldIsbn" value="${updateBooks.isbn}" readonly/>
                                                </td>
                                                </tr>
                                         </div>
                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="fid_categoryName" class="label">Book Category<span class="required">*</span></label></td>
                                         <td>
                                                <form:select path="categoryId">
                                                <form:option value="${updateBooks.categoryId}" label="${updateBooks.categoryName}"/>
                                                
                                                <form:options items="${categoryList}" itemValue="categoryId" itemLabel="categoryName"/>
                                                </form:select>
                                               
                                         </td>
                                         </tr>
                                         </div>
                                         <div></div>
                                                <tr>
                                         <td class="tdLabel"><label for="bookPrice_id" class="label">Selling Price (In $)<span class="required">*</span></label></td>
                                         <td><input type="text" name="bookPrice" value="${updateBooks.bookPrice}" id="bookPrice_id" placeholder="Book Price (In $)"/></td>
                                                </tr>
                                         </div>
                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="rentPrice_id" class="label">Rent Price (In $)<span class="required">*</span></label></td>
                                                <td><input type="text" name="rentPrice" value="${updateBooks.rentPrice}" id="rentPrice_id" placeholder="Rent Price"/></td>
                                                </tr>
                                         </div>
                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="quantity_id" class="label">Number of Books<span class="required">*</span></label></td>
                                         <td><input type="text" name="quantity" value="${updateBooks.quantity}" id="quantity_id" placeholder="11"/></td>
                                                </tr>
                                         </div>
                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="publisherName_id" class="label">Publisher Name<span class="required">*</span></label></td>
                                         <td><input type="text" name="publisherName" value="${updateBooks.publisherName}" id="publisherName_id" placeholder="Publisher Name"/></td>
                                                </tr>
                                         </div>

                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="rentAvailable_id" class="label">Available for Rent</label></td>
                                         <td><select name="rentAvailable" id="rentAvailable_id" onchange="dis_able()">
                                                <option value="Y">Yes</option>
                                                <option value="N">No</option>
                                                       </select>
                                                </td>
                                                </tr>
                                         </div>
                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="bookShortDesc_id" class="label">Short Description<span class="required">*</span></label></td>
                                         <td><textarea name="bookShortDesc" cols="25" rows="3" id="bookShortDesc_id" placeholder="Short Description">${updateBooks.bookShortDesc}
                                                       </textarea></td>
                                                </tr>
                                         </div>
                                         <div>
                                                <tr>
                                         <td class="tdLabel"><label for="bookDetailDesc_id" class="label">Detail Description</label></td>
                                         <td><textarea name="bookDetailDesc" cols="25" rows="6" id="bookDetailDesc_id" placeholder="Detail Description ">${updateBooks.bookDetailDesc}
                                                       </textarea></td>
                                                </tr>
                                         </div>
                                  </fieldset>
                                  <br>

                                  <div id="cancel" style="width: 42em;">   
                                  <tr>
                           <td colspan="3">
                           <div align="right">
                                  <input id="update" name="update" type="button" style="right: 25em;" value="Update Store" onclick="submitForm()">
                                  <button type="button" id="manageInv" name="manageInv" value="Submit" onclick="goBack()">Cancel</button>
                                  </div></td>
                                  </tr>
                                  </div>
                                  </table>
                           </form:form>
                     </div>
              </section>

              <div class="clearfix"></div>
              <footer>
                     <div id="footer">
                           
                            <div>Powered by <img src="img/sapient_nitro.jpg" width="78" height="14" alt="sapient nitro"></div>
                     </div>
                     </div>
              </footer>
       </div>
       <script
              src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
       <script>
              window.jQuery
                           || document
                                         .write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')
       </script>
       <script src="js/plugins.js"></script>
       <script src="js/main.js"></script>
</body>
</html>
