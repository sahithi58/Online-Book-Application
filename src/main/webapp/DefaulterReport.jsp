<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page errorPage="error.jsp" %>
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

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">

<script src="js/vendor/modernizr-2.6.2.min.js"></script>
<script src="js/vendor/jquery-1.9.1.min.js"></script>

<script type="text/javascript">
function getEmail()
{
	var checkedValues="";
	$('input:checkbox:checked').each(function() {
		if(this.value!=null)
		{
			checkedValues=checkedValues+this.value;
			}
		checkedValues=checkedValues+":";
		});
if(checkedValues!="")
	{
	
	$.ajax({
		type : "GET",
		url  : "/SapeStore/sendEmail",
		dataType : 'text/javascript',
		data : {'emailIds' : checkedValues},
		success : function(result){
			alert("success");}
		});
	document.getElementById("message").innerHTML="Email sent successfully.";
	}
else
	document.getElementById("message").innerHTML="Please select atleast one defaulter.";
}

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
		var oldPageAnchor = document.getElementById('pge'+this.currentPage);
		oldPageAnchor.className = 'pge-normal';
		this.currentPage = pageNumber;
		var newPageAnchor = document.getElementById('pge'+this.currentPage);
		newPageAnchor.className = 'pge-selected';
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
		var element = document.getElementById(positionId);
		var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pge-normal"> « Prev </span> ';
		for (var page = 1; page <= this.pages; page++){
			pagerHtml += '<span id="pge' + page + '" class="pge-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> ';	
		}
		pagerHtml += '<span onclick="'+pagerName+'.next();" class="pge-normal"> Next »</span>';
		element.innerHTML = pagerHtml;
	}
}
</script>


<style type="text/css">
.pge-normal {
	color: #de2728;
	font-size: 14px;
	cursor: pointer;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

.pge-selected {
	color: #fff;
	font-size: 14px;
	background: #de2728;
	padding: 2px 4px 2px 4px;
	font-weight: bold
}

table.paging {
	border-collapse: collapse;
	font-size: small;
}

table.paging td {
	padding: 5px;
}

table.paging .even {
	background-color: #EEE8AC;
}

table.paging .odd {
	background-color: #F9FAD0;
}

table.paging th {
	padding: 5px;
	height: auto;
}

table.paging th a {
	text-decoration: none;
	text-align: center;
	padding-right: 20px;
	font-weight: bold;
	white-space: nowrap;
}

table.paging tfoot td {
	background-color: #E1ECF9;
}

table.paging thead td {
	vertical-align: middle;
	background-color: #E1ECF9;
	border: none;
}

table.paging thead .tableHeader {
	font-size: larger;
	font-weight: bold;
}

table.paging thead .filter {
	text-align: right;
}

table.paging tfoot {
	background-color: #E1ECF9;
	text-align: center;
}

table.paging .tablesorterPager {
	padding: 10px 0 10px 0;
}

table.paging .tablesorterPager span {
	padding: 0 5px 0 5px;
}

table.paging .tablesorterPager input.prev {
	width: auto;
	margin-right: 10px;
}

table.paging .tablesorterPager input.next {
	width: auto;
	margin-left: 10px;
}

table.paging .pagedisplay {
	font-size: 10pt;
	width: 30px;
	border: 0px;
	background-color: #E1ECF9;
	text-align: center;
	vertical-align: top;
}

#hello {
	float: right;
	background: #f0f7f8;
	border-right: 1px solid #AAAAAA;
	border-left: 1px solid #AAAAAA;
	border-bottom: 1px solid #AAAAAA;
	padding-left: 774px;
	padding-bottom: 0.5em;
	padding-top: 0.5em;
	padding-right: 2px;
	margin-right: 16px;
	padding-left: 773px;
	margin-bottom: 25px;
}

#message {
	background: #f0f7f8;
	border-right: 1px solid #AAAAAA;
	border-left: 1px solid #AAAAAA;
	border-top: 1px solid #AAAAAA;
	width: 918px;
	margin-right: 0px;
	margin-left: 15px;
	padding-top: 10px;
	padding-left: 15px;
	border-right-width: 1px;
	padding-right: 15px;
	color: red;
	height: 0px;
}

#sendMail {
	background: #f0f7f8;
	height: 50px;
	margin-left: 15px;
	margin-right: 16px;
	border-right: 1px solid #AAAAAA;
	border-left: 1px solid #AAAAAA;
}

#btn {
	float: right;
	margin-top: 6px;
	margin-bottom: 10px;
	margin-right: 15px;
	background-color: #21addd;
	color: white;
	font-family: Georgia;
}
</style>
</head>
<body>


	<table id="tablepage" class="paging"
		style="border-top: white; border-bottom-width: 0px; width: 950px; height: 91px;">
		<thead>
			<tr>
				<th>Customer Name</th>
				<th>Order No.</th>
				<th>Book Category</th>
				<th>Book Name</th>
				<th>Due Date</th>
				<th>Return Date</th>
				<th>Return Status</th>
				<th>Rent Price</th>
				<th>Late Fee</th>
				<th>email</th>
			</tr>
		</thead>
		<div id="message"></div>
		<div id="sendMail">
			<button id="btn" onclick="getEmail()" style="outline: none">Send Email</button>
		</div>
		
		<c:forEach items="${adminReportsList}" var="current">

			<tbody>
				<tr>
					<td>${current.customerName}</td>
					<td>${current.orderID}</td>
					<td>${current.categoryName}</td>
					<td>${current.bookTitle}</td>
					<td>${current.expectedReturnDate}</td>
					<c:choose>
					<c:when test="${empty current.actualReturnDate}">
						<td>--/--/--</td>
					</c:when>
					<c:otherwise>
						<td>${current.actualReturnDate}</td>
					</c:otherwise>
					</c:choose>
					<td>${current.returnStatus}</td>
					<td>$${current.rentPrice}</td>
					<td>$${current.lateFee}</td>
					<td><input type="checkbox"
						value='${current.email}#${current.customerName}#${current.bookTitle}#${current.expectedReturnDate}#${current.lateFee}#${current.returnStatus}' />
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<div id="hello" align="center"></div>

	<script type="text/javascript">
		var pager = new Pager('tablepage', 10);
		pager.init();
		pager.showPageNav('pager', 'hello');
		pager.showPage(1);
	</script>

</body>
</html>