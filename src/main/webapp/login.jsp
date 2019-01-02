
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style type="text/css">
table.yui { 

border-collapse:collapse; 

border-spacing: 0; 
}
.wwFormTable
{
margin-left:-6px;
}
table.wwFormTable tr{
margin: 0 0 10px 0;
}
/* #login .lightButton { background:#21addd; color:#fff; font-size:18px; font-family:Georgia, "Times New Roman", Times, serif; border:solid 1px #1d97c1; padding:5px 10px;}
#login {height:220px; width:500px;} */
</style>



<body>

 <div style="height: 320px">

<table class="wwFormTable">


 <form:form id="searchForm" name="myform" action="/SapeStore/login"  commandName="user">


   
     
	 <tr>
    	<td class="tdLabel"></td>
    	<td><div style="margin-left: 10px;"><label id="login_" style="font-size: 16px;font-weight: bold">User Name<span class="required">*</span></label></div></td>
	  </tr>
     <tr>
   		 <td class="tdLabel"></td>
    	<td><div style="margin-left: 10px;"><input type="text" name="userId" value="" id="userId" placeholder="User Name" required/></div></td>
	 </tr>
     <tr>
    	 <td class="tdLabel"></td>
    	 <td><div style="margin-left: 13px;"><label id="login_" style="font-size: 16px;font-weight: bold;float: left;">Password<span class="required">*</span></label></div>
      <a class="forgotPassword inline" title="Forgot Password" style="float: right;" href="#forgotPassword">Forgot password?</a>
      </td>
	</tr>
    <tr>
  		<td class="tdLabel" style="margin-left: 13px;"></td>
    	<td><div style="margin-left: 13px;"><input type="password" name="password" id="password" placeholder="Password" required/>
    	</div></td>
	</tr>
	  </form:form>
	  
	  
	
			   
    <tr>
    	<td colspan="2"><div align="center" style="margin-left: 13px;"><input type="button" id="button"  value="Log In" class="lightButton" />
		</div>
		</td>
	</tr>
	
	<tr>
	<td class="tdLabel"></td>
	<td><div id="errormessage" style="color: #FF0000; font-size: 13px; margin-left: 13px;" > </div></td>
	</tr>
	
    <tr>
    	<td class="tdLabel"></td>
    	<td><div align="center"><label id="login_">Don't have an account yet?</label></div></td>
	</tr>
   
 
  </table>

   
    
  <form:form id="searchForm1" name="myform1"  action="/SapeStore/beforeRegister"  >
      <table>
      <tr>
   		 <td colspan="2"><div align="center" style="margin-left: 8px;">
   		 <input type="text" name="stateId" id="stateId" value="111" hidden>
   		 <input type="submit" id="login_2" value="Register" class="darkButton"/>
   		
		</div>
		</td>
	</tr>
	</table>
 </form:form>
 </div>
</body>
</html>