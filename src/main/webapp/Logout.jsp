<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
					<c:choose>
					<c:when test="${empty userId}">
					<li><a href="#login" class='inline' title="Login"
							id="loginPop">Login</a></li>
						
 
					</c:when>
					<c:otherwise>
						<li><a href="/SapeStore/logout">logout</a></li>
					</c:otherwise>
					</c:choose>