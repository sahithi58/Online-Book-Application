<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
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
<!-- <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"> -->
<!--<link rel="stylesheet" href="/SapeStore/css/normalize.css">
 <link rel="stylesheet" href="/SapeStore/css/main.css"> -->
<script src="/SapeStore/js/vendor/modernizr-2.6.2.min.js"></script>
<script>
function radioValidation() {
  
	 if(document.getElementById('msg').value != '') 
		 document.getElementById('msg').innerHTML = "";
    if(document.getElementById('review').value == '') 
    {      
  	 	/* alert("Please Provide Details!"); */
  	 	/* var msg = ' <span style="color:red;"> Please Write Reviews!</span>'; */
  	 	var msg = ' <span style="color:red;"> Please Write Reviews!</span>';
  	 	 document.getElementById('reviewtext').innerHTML = msg;
         document.getElementById('review').focus();
   		return false;   
   }
    else{
    	 document.getElementById('reviewtext').innerHTML = "";
    }
    var rating = document.getElementsByName("rating");
    var formValid = false;
	
    var i = 0;
    while (!formValid && i < rating.length) {
        if (rating[i].checked) 
        	formValid = true;
        i++;        
    }
	
    
    if (!formValid){
    	/* alert("Select appropriate rating"); */
    	var msg = 'You must select rating!';
        document.getElementById('msg').innerHTML = msg;
        return false;
    }
    else{
    	document.getElementById('reviewtext').innerHTML = "";
    }
    return formValid;
}
</script>
<style>
.panel{
	display:inline-block;
	width:40%;
	margin:1em;
}
.container{
	display:inline-block;
	width:40%;
	margin:1em;
}
.rating {
          overflow: hidden;
          display: inline-block;
      }
      .rating-input {
          position: absolute;
          left: 0;
          visibility:hidden;
      }
      .rating-star {        
          display: block;
          float: right;        
          width: 16px;
          height: 16px;
          background: url('http://kubyshkin.ru/samples/star-rating/star.png') 0 -16px;
      }
      .rating-star:hover,
      .rating-star:hover ~ .rating-star,
      .rating-input:checked ~ .rating-star {
      background-position: 0 0;
      }
#writecomment1 .lightButton { background:#21addd; color:#fff; font-size:12px; font-family:Georgia, "Times New Roman", Times, serif; border:solid 1px #1d97c1; padding:5px 10px;}
#writecomment1 {height:280px; width:620px;}

</style>
</head>

<body  style="opacity:1; background:#cacfd0" >
<div id= "writecomment1"  style=" display:inline-block; margin-top:0px;/*  overflow:hidden; */"> 
    <h4>Write a Review</h4>  
    <table><tr >
        <!-- <div style= "overflow:hidden; float:left; background:#cacfd0;"> -->
        <td style=" position:static; float:left;">
        <img src="/SapeStore/${bookDetails.thumbPath}" width="120" height="140" alt="book Image" onerror="this.src= '/SapeStore/img/default.png'"/>
        </td>
        <td style="margin-left:20px; float:left;position:relative;">
        	<div><form method="post" action="/SapeStore/reviewcontroller/${bookDetails.isbn}" style="position:relative;">
	            <label for="comment" style="font-size: 15px;" >Your Comments:</label><div style="display:inline;font-size:12px;" id="reviewtext"></div><br>
	             <textarea rows="5" cols="63" name="comment" style="background:#f5f5f5;" id="review"></textarea><br><br>
<!-- 	              <div class="rating" style="display:inline ; overflow:hidden; margin-left:0px ;/*  padding-right:20px; */"> -->
	              <label for="rating" style="font-size: 10px;" >Your Rating:</label>
	             <div class="rating" style=" overflow:hidden; margin-left:0px ;">
	              <input type="radio" class="rating-input" id="rating-input-1-1" name="rating" value="5" >
	              <label for="rating-input-1-1" class="rating-star"></label>
	              <input type="radio" class="rating-input" id="rating-input-1-2" name="rating" value="4" >
	              <label for="rating-input-1-2" class="rating-star"></label>
	              <input type="radio" class="rating-input"  id="rating-input-1-3" name="rating" value="3">
	               <label for="rating-input-1-3" class="rating-star"></label>
	               <input type="radio" class="rating-input" id="rating-input-1-4" name="rating" value="2">
	               <label for="rating-input-1-4" class="rating-star"></label>
	              <input type="radio" class="rating-input" id="rating-input-1-5" name="rating" value="1">
	              <label for="rating-input-1-5" class="rating-star"></label><br></div>
	              <span  id="msg" style="color:red; width:3000px;font-size:10px;"></span><span style="float:right;"><input type="submit" value="Post Comment" class="lightButton" onclick="return radioValidation();"><a href="/SapeStore/cancelreviews/${bookDetails.isbn}" title="cancel" style="color:red ; font-size: 10px;"><u>Cancel Post</u></a></span>
            	<br>
            	<!-- <span  id="msg" style="margin-left:0px;width:20%;font-size:10px;"></span> --> 
            </form></div>
           </td>
           </tr>
            <%-- <tr>
            <td>
        	  <div style="font-size:10px;width:20%;"><b>${bookDetails.bookTitle}</b></div><p style="font-size:10px;" >${bookDetails.bookAuthor}</p></td>
            </tr> --%>
        	
        	</table>
        	 <div style="font-size:10px;width:20%;position:relative;"><b>${bookDetails.bookTitle}</b><p style="font-size:10px;" >${bookDetails.bookAuthor}</p></div>
        	 <!-- <div  id="msg" style="margin-left:140px;width:80%; font-size:12px"></div> -->
        	<!-- <span  id="msg" style="margin-left:200px;margin-top:150px;width:80%; font-size:12px"></span> -->
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

 