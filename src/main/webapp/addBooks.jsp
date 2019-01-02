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

<link rel="stylesheet" href="/SapeStore/css/normalize.css">
<link rel="stylesheet" href="/SapeStore/css/main.css">
<script src="/SapeStore/js/vendor/modernizr-2.6.2.min.js"></script>

<script src="/SapeStore/js/AddBookenglish.js"></script>
</head>
<body>
	<script type="text/javascript">
		var numberReg = /^[01-9]{1,100}$/;
		var nameReg = /^[a-zA-Z0-9 ]{1,100}$/;
		var isbnNameReg = /^[a-zA-Z0-9\-]{1,100}$/;
		var alphaReg = /^[.a-zA-Z ]{1,100}$/;
		function dis_able() {
			var r = $("#rentAvailable_id").val().trim();
			if (r == "N") {
				document.getElementById("rentPrice_id").value = "";
				document.getElementById("rentPrice_id").disabled = true;
			} else {
				document.getElementById("rentPrice_id").value = "";
				document.getElementById("rentPrice_id").disabled = false;
			}
		}
	/* 	function HandleBrowseClick() {
			var fileinput = document.getElementById("thumbImage");

			fileinput.click();
		} */
		function Handlechange() {
			var fileinput = document.getElementById("thumbImage");
			if (fileinput) {
				var startIndex = (fileinput.indexOf('\\') >= 0 ? fileinput
						.lastIndexOf('\\') : fileinput.lastIndexOf('/'));
				var filename = fileinput.substring(startIndex);
				if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
					filename = filename.substring(1);
				}
				var textinput = document.getElementById("filename");
				textinput.value = filename.value;

			}

		}
	/* 	function HandleBrowseClickFullImage() {
			var fileinput = document.getElementById("fullImage");
			fileinput.click();
		} */
		function HandlechangeFullImage() {
			var fileinput = document.getElementById("fullImage");
			if (fileinput) {
				var startIndex = (fileinput.indexOf('\\') >= 0 ? fileinput
						.lastIndexOf('\\') : fileinput.lastIndexOf('/'));
				var filename = fileinput.substring(startIndex);
				if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
					filename = filename.substring(1);
				}
				var textinput = document.getElementById("filenameFullImage");
				textinput.value = filename.value;
			}

		}
		function validateInput()

		{
				document.getElementById("validTitle").innerHTML = "";
			document.getElementById("validAuthor").innerHTML = "";
			document.getElementById("validIsbn").innerHTML = "";
			document.getElementById("validPublisherName").innerHTML = "";
			document.getElementById("validPrice").innerHTML = "";
			document.getElementById("validQuantity").innerHTML = "";
			document.getElementById("validRentPrice").innerHTML = "";
			document.getElementById("validBookShortDesc").innerHTML = "";
			document.getElementById("fullImageError").innerHTML = "";
			document.getElementById("thumbImageError").innerHTML = "";
			document.getElementById("bookCategoryError").innerHTML = "";
			document.getElementById("validBookDetailDesc").innerHTML = "";
			
			
			var bookCategory = document.getElementById("bookCategory").value;
			var bookTitle = document.getElementById("addAddress_bookTitle").value;
			var bookAuthor = document.getElementById("addAddress_bookAuthor").value
					.trim();
			var bookIsbn = document.getElementById("addAddress_isbn").value;
			var bookPublisherName = document
					.getElementById("addAddress_publisherName").value;
			var bookPrice = document.getElementById("addAddress_bookPrice").value;
			var bookQuantity = document.getElementById("addAddress_quantity").value;
			var bookRentPrice = document.getElementById("rentPrice_id").value;
			var bookShortDesc = document
					.getElementById("addAddress_bookShortDesc").value;
			var bookDetailDesc=document.getElementById("addAddress_bookDetailDesc").value;
			var flag=true;

			if (bookTitle == "") {
				
				document.getElementById("validTitle").innerHTML = bookTitleError;
				flag=false;
				
			}

			if (bookAuthor == "") {
				document.getElementById("validAuthor").innerHTML = bookAuthorError;
				flag=false;
				
			} else if (!alphaReg.test(bookAuthor)) {
				document.getElementById("validAuthor").innerHTML = bookAuthorNumberError;
				flag=false;
				
			}
			if (bookIsbn == "") {
				document.getElementById("validIsbn").innerHTML = bookIsbnError;
				flag=false;
				
			}
			else if(!isbnNameReg.test(bookIsbn)){
				document.getElementById("validIsbn").innerHTML = "Invalid Isbn No.";
				flag=false;
				}
			if (bookPublisherName == "") {
				document.getElementById("validPublisherName").innerHTML = bookPublisherError;
				flag=false;
				
			}
			if (bookPrice == ""||bookPrice==0) {
				document.getElementById("validPrice").innerHTML = bookPriceError;
				flag=false;
				
			} else if (!numberReg.test(bookPrice)) {
				document.getElementById("validPrice").innerHTML = bookPriceNanError;
				flag=false;
				

			}

			if (bookQuantity == 0) {
				document.getElementById("validQuantity").innerHTML = bookQuantityError;
				flag=false;
				
			} else if (!numberReg.test(bookQuantity)) {
				document.getElementById("validQuantity").innerHTML = bookQuantityNanError;
				flag=false;
				
			}
			if(document.getElementById("rentAvailable_id").value=='Y'){
			if (bookRentPrice == ""||bookRentPrice==0) {
				document.getElementById("validRentPrice").innerHTML = bookRentPriceError;
				flag=false;
				
			} else if (!numberReg.test(bookRentPrice) || bookRentPrice*4>bookPrice) {
                document.getElementById("validRentPrice").innerHTML = bookRentPriceNanError;
                flag=false;
                

          }

			}
			if (bookShortDesc == "") {
				document.getElementById("validBookShortDesc").innerHTML = bookShortDescError;
				flag=false;
				
			}
			else if(bookShortDesc.length>150){
				document.getElementById("validBookShortDesc").innerHTML = "Short Description should be less than 150 characters";
				flag=false;
			}

			if(bookDetailDesc.length>500){
				document.getElementById("validBookDetailDesc").innerHTML="Detailed Description should be less than 500 characters";
			flag=false;
				}
			if (bookCategory == "") {

				document.getElementById("bookCategoryError").innerHTML = bookCategoryError;
				flag=false;
				

			}

			if (jQuery('#thumbImage').val() == "") {
				document.getElementById("thumbImageError").innerHTML = bookThumbnailError;
				flag=false;
				

			}
			if (jQuery('#fullImage').val() == "") {
				document.getElementById("fullImageError").innerHTML = bookDetailError;
				flag=false;
				

			}

			if(flag==true) {
				document.addBooksForm.submit();
			}
		}

		function submitTheForm() {
			document.addBooksForm.submit();
			
			document.addBooksForm.reset();
			
		}

		function goBack() {
			window.location.href="/SapeStore/admin/manageInventory"; 
			
		}
	</script>

	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<!-- Add your site or application content here -->
	<div id="wrapper" class="homeAdmin">
		<!-- header starts-->
		<header>
			<div id="header">
				<a href="#" title="SapeStore" class="logo"><img
					src="img/logo.jpg" width="231" height="109" alt="SapeStore"></a>
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
						<li class="active"><a href="/SapeStore/admin/manageInventory">Manage
								Inventory</a></li>

						<li><a href="/SapeStore/admin/manageOrders">Manage Orders</a></li>

						<li><a href="/SapeStore/admin/adminReport">Manage Reports</a></li>

						<li><a href="/SapeStore/admin/managePages">Manage Pages</a></li>

						<li><a href="/SapeStore/logout">Logout</a></li>
					</ul>
				</nav>
			</div>
		</header>
		<!-- header ends -->
		<section>
			<div id="mainContent" class="addAddress">
				<h2>Add Book to Store</h2>
				${successmessage}
				<div class="clearfix"></div>
				<form:form method="POST" name="addBooksForm"
					action="/SapeStore/admin/addBooks" id="addAddress"
					enctype="multipart/form-data" commandName="addBooks" >
					<table class="wwFormTable">
						
							<div>
								<tr>
									<td class="tdLabel"><label for="thumbImage" class="label">Book
											Thumbnail Image<span class="required">*</span>
									</label></td>
									<td><!-- <input type="button" value="Browse Image"
										id="fakeBrowse" name="fakeBrowse"
										onclick="HandleBrowseClick();" />  --><label id="filename"
										style="font-size: 13px;"></label> <input type="file"
										id="thumbImage" name="thumbImage"
										accept="image/jpeg, image/jpg, image/png, image/bmp"
										required="required" style="opacity: 1"
										onChange="Handlechange()" value="${addBooks.thumbImage}" /></td>
									<td><div id="thumbImageError" style="color: #FF0000"></div></td>
								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="fullImage" class="label">Book
											Detail Image<span class="required">*</span>
									</label></td>
									<td><!-- <input type="button" value="Browse Image"
										id="fakeBrowsefullImage" name="fakeBrowsefullImage"
										onclick="HandleBrowseClickFullImage();" /> --><label
										id="filenameFullImage" style="font-size: 13px;"></label> <input
										type="file" id="fullImage" name="fullImage" 
										accept="image/gif, image/jpeg, image/jpg, image/png, image/bmp"
										placeholder="Book Detail Image"
										required="required" style="opacity: 1"
										onChange="HandlechangeFullImage()"
										value="${addBooks.fullImage}" /></td>
									<td><div id="fullImageError" style="color: #FF0000"></div></td>
								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="addAddress_bookTitle"
										class="label">Book Title<span class="required">*</span></label></td>
									<td><input type="text" name="bookTitle"
										value="${addBooks.bookTitle}" id="addAddress_bookTitle"
										placeholder="Book Title" /></td>
									<td><div id="validTitle" style="color: #FF0000"></div></td>
								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="addAddress_bookAuthor"
										class="label">Book Author<span class="required">*</span></label></td>
									<td><input type="text" name="bookAuthor"
										value="${addBooks.bookAuthor}" id="addAddress_bookAuthor"
										placeholder="Book Author" /></td>
									<td><div id="validAuthor" style="color: #FF0000"></div></td>
								</tr>
							</div>
							<div>
								<tr>
									
									<td class="tdLabel"><label for="addAddress_isbn"
										class="label">ISBN<span class="required">*</span></label></td>
									<td><input type="text" name="isbn"
										value="${addBooks.isbn}" id="addAddress_isbn"
										placeholder="ISBN" /></td>
									<td>${errormessage}<div id="validIsbn" style="color: #FF0000"></div></td>
								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="addAddress_publisherName"
										class="label">Publisher Name<span class="required">*</span></label></td>
									<td><input type="text" name="publisherName"
										value="${addBooks.publisherName}"
										id="addAddress_publisherName" placeholder="Publisher Name" />
									</td>
									<td><div id="validPublisherName" style="color: #FF0000"></div></td>

								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="addAddress_categoryName"
										class="label">Book Category<span class="required">*</span></label></td>
									<td><form:select path="categoryId" id="bookCategory">
											<form:option value="" label="Select a category" />
											<c:forEach items="${categoryList}" var="current">
												<c:choose>
													<c:when
														test="${current.categoryName==addBooks.categoryName}">
														<form:option value="${current.categoryId}"
															label="${current.categoryName}" selected="selected" />
													</c:when>
													<c:otherwise>
														<form:option value="${current.categoryId}"
															label="${current.categoryName}" />
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</form:select></td>
									<td><div id="bookCategoryError" style="color: #FF0000"></div></td>

								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="addAddress_bookPrice"
										class="label">Selling Price (In $)<span
											class="required">*</span></label></td>
									<td><input type="text" name="bookPrice"
										id="addAddress_bookPrice" value="${addBooks.bookPrice}"
										placeholder="Book Price (In $)" /></td>
									<td><div id="validPrice" style="color: #FF0000"></div></td>

								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="rentPrice_id"
										class="label">Rent Price (In $)<span class="required">*</span></label></td>
									<td><input type="text" name="rentPrice"
										value="${addBooks.rentPrice}" id="rentPrice_id"
										placeholder="Rent Price" /></td>
									<td><div id="validRentPrice" style="color: #FF0000"></div></td>
								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="addAddress_quantity"
										class="label">Number of Books<span class="required">*</span></label></td>
									<td><input type="text" name="quantity"
										id="addAddress_quantity" value="${addBooks.quantity}"
										placeholder="Quantity" /></td>
									<td><div id="validQuantity" style="color: #FF0000"></div></td>
								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="rentAvailable_id"
										class="label">Available for Rent<span class="required">*</span></label></td>
									<td><select name="rentAvailable" id="rentAvailable_id"
										onchange="dis_able()">
											<option value="Y">Yes</option>
											<c:choose>
												<c:when test="${addBooks.rentAvailable=='N'}">
													<option value="N" selected="selected">No</option>
												</c:when>
												<c:otherwise>
													<option value="N">No</option>
												</c:otherwise>
											</c:choose>

									</select> </td>
								</tr>
							</div>

							<div>
								<tr>
									<td class="tdLabel"><label for="addAddress_bookShortDesc"
										class="label">Short Description<span class="required">*</span></label></td>
									<td><textarea name="bookShortDesc" cols="25" rows="3"
											id="addAddress_bookShortDesc" placeholder="Short Description">${addBooks.bookShortDesc}</textarea>
									</td>
									<td><div id="validBookShortDesc" style="color: #FF0000"></div></td>
								</tr>
							</div>
							<div>
								<tr>
									<td class="tdLabel"><label for="addAddress_bookDetailDesc"
										class="label">Detailed Description</label></td>
									<td><textarea name="bookDetailDesc" cols="25" rows="6"
											id="addAddress_bookDetailDesc"
											placeholder="Detailed Description">${addBooks.bookDetailDesc}</textarea></td>
											<td><div id="validBookDetailDesc" style="color: #FF0000"></div></td>
								</tr>
							</div>
							<tr>
								<td colspan="2"><div align="center">
										<input type="button" id="addAddress__addBooks"
											name="method:addBooks" value="Add to Store"
											onclick="validateInput()" class="lightButton addtoStore" />
										<button type="button" id=addAddress__cancel name="cancel"
											value="Submit" onclick="goBack()"
											class="lightButton addtoStore">Cancel</button>
									</div></td>
							</tr>
						
					</table>
				</form:form>
			</div>
		</section>

		<div class="clearfix"></div>
		<footer>
			<div id="footer">
				<div>
					Powered by <img src="img/sapient_nitro.jpg" width="78" height="14"
						alt="sapient nitro">
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