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
<script type="text/javascript">
function doSubmit() {
	var frm = document.getElementById("f1");
	
	frm.submit();
}
</script>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery.mobile-1.4.2.min.js"></script>
</head>

<body>
<div style="position:absolute; top:20px; left:20px; width:960px; height:600px; ">
<input type="button" value="Group Add" data-inline="true" onclick="javascript:location.href='groupAdd.html?user_id=<c:out value="${user_id }"/>'"/>&nbsp;
<br/><br/>

<div style="width:960px; height:400px; text-align:center; overflow-y:auto; border: 1px solid #c3c3c3;">	
<table style="width:100%; text-align:center; border: 0px;">
	<tr>
		<th width='20px'>No</th>
		<th width='130px'>Group ID</th>
		<th width='130px'>Group Name</th>
		<th width='50px'>User ID</th>
		<th width='90px'>Register Date</th>
	</tr>
	<c:forEach var="entity" items="${data }">
	<tr>
		<td><c:out value='${entity.no }'/></td>
		<td><a href="groupInfo.html?group_id=<c:out value='${entity.group_id }'/>"><c:out value='${entity.group_id }'/></a></td>
		<td><c:out value='${entity.group_name }'/></td>
		<td><c:out value='${entity.user_id }'/></td>
		<td><c:out value='${entity.upd_date }'/></td>
	</tr>
	</c:forEach>
</table>	
</div>
</div>
</body>
</html>