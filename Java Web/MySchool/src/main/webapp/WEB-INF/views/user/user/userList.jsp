<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<!-- <link href="css/admin.css" rel="stylesheet" type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/index.css" />
<link rel="stylesheet" href="css/jquery.mobile-1.4.2.min.css" />
<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}

.ui-content {
    padding: 0 !important;
}
 
.ui-listview {
    margin: 0 !important;
}
 
.example-wrapper, .example-wrapper div.iscroll-scroller {
    width: 100% !important;
}
</style>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery.mobile-1.4.2.min.js"></script>
</head>

<body>
<div style="position:absolute; top: 20px; left:20px; width:960px; height:600px; ">
<input type="button" value="Add" data-inline="true" onclick="javascript:location.href='userAdd.html"/>
<br/><br/>

<div style="width:960px; height:400px; text-align:center; overflow-y:auto; border: 1px solid #c3c3c3;">	
<table style="width:100%; text-align:center; border: 0px;">
	<tr>
		<th width='20px'>No</th>
		<th width='130px'>First Name</th>
		<th width='130px'>Last Name</th>
		<th width='50px'>Location</th>
		<th width='*'>E-mail</th>
		<th width='90px'>Register Date</th>
	</tr>
	<c:forEach var="entity" items="${data }">
	<tr>
		<td><c:out value='${entity.no }'/></td>
		<td><c:out value='${entity.fname }'/></td>
		<td><c:out value='${entity.lname }'/></td>
		<td>
			<c:if test="${entity.location == 'kr'}" >KR</c:if> 
			<c:if test="${entity.location == 'jp'}" >JP</c:if>
			<c:if test="${entity.location == 'en'}" >EN</c:if>
		</td>
		<td><a href="userInfo.html?email=<c:out value='${entity.email }'/>"><c:out value='${entity.email }'/></a></td>
		<td><c:out value='${entity.ins_date }'/></td>
	</tr>
	</c:forEach>
</table>	
</div>
</div>

</body>
</html>

