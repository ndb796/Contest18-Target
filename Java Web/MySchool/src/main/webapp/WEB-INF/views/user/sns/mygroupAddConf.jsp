<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="css/admin.css" rel="stylesheet" type="text/css">

<div style="position:absolute; top:50px; left:20px; width:960px; height:600px; ">
<input type="button" value="Next" style="width:70px; height: 25px; font-size: 12px;" onclick="doSubmit();"/>&nbsp;
<input type="button" value="Cancel" style="width:70px; height: 25px; font-size: 12px;" onclick="doMyGroupAdd();" />
<br/><br/>
<br/><br/>
<table  style="width:600px; border: 0px;">
	<tr>
		<td class="star_class" style="width:11px;"><font color='red'>*</font></td>
		<td class="input_td" style="width:118px;" nowrap>User ID</td>
		<td class="input_td" style="width:70px;">&nbsp;</td>
		<td class="input_td" style="width:510px; height: 50px; text-align: left;">
			<c:out value='${entity.user_id }'/>
		</td>
	</tr>
	<tr>
		<td class="star_class" style="width:11px;"><font color='red'>*</font></td>
		<td class="input_td" style="width:118px;" nowrap>Requested User ID</td>
		<td class="input_td" style="width:70px;">&nbsp;</td>
		<td class="input_td" style="width:510px; height: 50px; text-align: left;">
			<c:out value='${entity.myuser_id }'/>
		</td>
	</tr>
</table>
<form name="f1" id="f1" method="post">
	<input type="hidden" name="myuser_id" value="<c:out value='${entity.myuser_id}'/>" />
	<input type="hidden" name="user_id" value="<c:out value='${entity.user_id}'/>" />
</form>	
</div>

<script type="text/javascript">
function doSubmit() {
	var frm = document.getElementById("f1");
	
	frm.action = "mygroupAddProc.html";
	frm.submit();
}
function doMyGroupAdd() {
	var frm = document.getElementById("f1");
	
	frm.action = "mygroupAdd.html";
	frm.submit();
}
</script>