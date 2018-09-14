<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

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
function doRequest() {
	var frm = document.getElementById("f1");
	
	frm.action = "mygroupAdd.html";
	frm.submit();
}
function doAccept(idx) {
	var frm = document.getElementById("f1");
	
	frm.action = "mygroupModifyProc.html";
	frm.idx.value = idx;
	frm.cmd.value = "<%=Constants.MYSTUDENT_CMD_STATUS_MODIFY %>";
	frm.grp_status.value = "<%=Constants.MYSTUDENT_GRP_STATUS_ACCEPT %>";
	frm.submit();
}
function doReject(idx) {
	var frm = document.getElementById("f1");
	
	frm.action = "mygroupModifyProc.html";
	frm.idx.value = idx;
	frm.cmd.value = "<%=Constants.MYSTUDENT_CMD_STATUS_MODIFY %>";
	frm.grp_status.value = "<%=Constants.MYSTUDENT_GRP_STATUS_REJECT %>";
	frm.submit();
}
</script>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery.mobile-1.4.2.min.js"></script>
</head>

<body>
<div style="position:absolute; top:20px; left:20px; width:960px; height:600px; ">
<input type="button" value="Request User ID"  data-inline="true" onclick="doRequest();" />&nbsp;
<br/><br/>
<br/><br/>
<form name="f1" id="f1" method="post">
<input type="hidden" name="user_id" value="<c:out value='${user_id }'/>">
<input type="hidden" name="cmd" value=""> 
<input type="hidden" name="grp_status" value="">
<input type="hidden" name="idx" value="">
</form>
User ID: <c:out value='${user_id }'/> [Requested List]
<table style="width:100%; border: 0px;">
	<tr>
		<th width='20px'>No</th>
		<th width='150px'>User Name</th>
		<th width='90px'>Status</th>
		<th width='150px'>&nbsp;</th>
	</tr>
	<c:forEach items="${myuser_list }" var="myuser">
	<tr>
		<td class="input_td">${myuser.no }</td>
		<td class="input_td" style="text-align: center; width:20px;"><c:out value='${myuser.fname }'/> <c:out value='${myuser.lname }'/></td>
		<td class="input_td" style="text-align: center; width:90px;">
			<c:if test="${myuser.grp_status == 0 }">Requested</c:if>
			<c:if test="${myuser.grp_status == 1 }">Accepted</c:if>
			<c:if test="${myuser.grp_status == 2 }">Rejected</c:if>
		</td>
		<td class="input_td" style="text-align: center; width: 150px;">
			<input type="button" value="Accept" style="width:70px; height: 25px; font-size: 12px;" onclick="doAccept('<c:out value="${myuser.idx}"/>');" />&nbsp;
			<input type="button" value="Reject" style="width:70px; height: 25px; font-size: 12px;" onclick="doReject('<c:out value="${myuser.idx}"/>');" />
		</td>
	</tr>
	</c:forEach>
</table>	
</div>
</body>
</html>