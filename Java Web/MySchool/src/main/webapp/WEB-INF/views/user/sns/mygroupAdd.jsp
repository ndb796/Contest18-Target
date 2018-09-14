<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<link href="css/admin.css" rel="stylesheet" type="text/css">

<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}
</style>

<div style="position:absolute; top:50px; left:20px; width:960px; height:600px; ">
<input type="button" value="MyGroup List" style="width:100px; height: 25px; font-size: 12px;" onclick="doMyGroupList();" />
<br/><br/>
<br/><br/>
User ID: <c:out value='${user_id }'/> [My Request List]
<form name="f1" id="f1" method="post"> 
<input type="hidden" name="cmd" value=""> 
<input type="hidden" name="user_id" value="<c:out value='${user_id }'/>">
<input type="hidden" name="myuser_id" value="">
<input type="hidden" name="group_id" value="">
<input type="hidden" name="idx" value="">
</form>
<table style="width:100%; border: 0px;">
	<tr>
		<th style="width: 20px;">No</th>
		<th style="width: 150px;">User Name</th>
		<th style="width: 200px;">Group</th>
		<th style="width: 90px;">Status</th>
		<th style="width: 90px;">&nbsp;</th>
	</tr>
	<c:forEach items="${user_list }" var="entity">
	<tr>
		<td><c:out value='${entity.no }'/></td>
		<td><c:out value='${entity.fname }'/> <c:out value='${entity.lname }'/> </td>
		<td style="text-align: center; height: 30px; text-align: left;">
			<c:if test="${user_id == entity.user_id && entity.grp_status == 1 }">
			<select id="group_id" name="group_id" style="width:100px; height:20px;">
			<c:forEach items="${group_list }" var="grp">
				<option value="<c:out value='${grp.group_id }'/>" <c:if test="${entity.group_id == grp.group_id}">selected</c:if>><c:out value='${grp.group_name }'/></option>
			</c:forEach>
			</select>
			&nbsp;
			<input type="button" value="Modify" style="width:70px; height: 25px; font-size: 12px;" onclick="doModify('<c:out value="${entity.idx}"/>');" />
			</c:if>
		</td>
		<td>
			<c:if test="${entity.grp_status == 0 }">Requested</c:if>
			<c:if test="${entity.grp_status == 1 }">Accepted</c:if>
			<c:if test="${entity.grp_status == 2 }">Rejected</c:if>
			<c:if test="${entity.grp_status == 8 }">My Group</c:if>
		</td>
		<td>
			<c:if test="${entity.grp_status == 9 }">
			<input type="button" value="Request" style="width:70px; height: 25px; font-size: 12px;" onclick="doRequest('<c:out value="${entity.myuser_id}"/>');" />
			</c:if>
			<c:if test="${entity.grp_status == 0 || entity.grp_status == 1 || entity.grp_status == 2}">
			<input type="button" value="Cancel" style="width:70px; height: 25px; font-size: 12px;" onclick="doCancel('<c:out value="${entity.myuser_id}"/>');" />
			</c:if>
		</td>
	</tr>
	</c:forEach>
</table>	
</div>

<script type="text/javascript">
function doRequest(myuser_id) {
	var frm = document.getElementById("f1");
	
	frm.action = "mygroupAddConf.html";
	frm.myuser_id.value = myuser_id;
	frm.submit();
}
function doCancel(myuser_id) {
	var frm = document.getElementById("f1");
	
	if (confirm("Do you want to cancel the request of the selected user?")) {
		frm.action = "mygroupRemoveProc.html";
		frm.myuser_id.value = myuser_id;
		frm.submit();
	}
}
function doModify(idx) {
	var frm = document.getElementById("f1");
	var group_id = document.getElementById("group_id");
	
	frm.action = "mygroupModifyProc.html";
	frm.idx.value = idx;
	frm.group_id.value = group_id.value;
	frm.cmd.value = "<%=Constants.MYSTUDENT_CMD_GROUP_MODIFY %>";
	frm.submit();
}
function doMyGroupList() {
	var frm = document.getElementById("f1");
	
	frm.action = "mygroupList.html";
	frm.submit();
}
</script>