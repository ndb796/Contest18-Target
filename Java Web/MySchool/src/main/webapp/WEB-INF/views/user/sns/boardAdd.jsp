<%@ page contentType="text/html;charset=utf-8" %>
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
<input type="button" value="Next" style="width:70px; height: 25px; font-size: 12px;" onclick="doSubmit();" />&nbsp;
<input type="button" value="Cancel" style="width:70px; height: 25px; font-size: 12px;" onclick="javascript:location.href='boardList.html?user_id=<c:out value="${user_id }"/>&group_id=<c:out value="${group_id }"/>'" />&nbsp;
<br/><br/>
<br/><br/>
<form:form commandName="boardEntity" autocomplete="off" name="f1" id="f1" method="post"> 
<input type="hidden" name="user_id" value="<c:out value='${user_id }'/>">
<input type="hidden" name="group_id" value="<c:out value='${group_id }'/>">
<table style="width:100%; border: 0px;">
	<tr>
		<td class="star_class"><font color='red'>*</font></td>
		<td class="input_td" nowrap>Board Type</td>
		<td class="input_td" style="width:50px;">&nbsp;</td>
		<td class="input_td" style="height: 50px; text-align: left;">
			<form:radiobutton path="board_type" cssStyle="width:30px;" value="0"/>Public
			<form:radiobutton path="board_type" cssStyle="width:30px;" value="1"/>Group
			<form:radiobutton path="board_type" cssStyle="width:30px;" value="2"/>Privacy
		</td>
	</tr>
	<tr>
		<td class="star_class"><font color='red'>*</font></td>
		<td class="input_td" nowrap>Contents</td>
		<td class="input_td" style="width:50px;">&nbsp;</td>
		<td class="input_td" style="height: 50px; text-align: left;">
			<form:textarea path="contents" cssStyle="width:510px; height:100px;" maxlength="100" value="<c:out value='${boardEntity.contents }'/>" /><br/>
			<form:errors path="contents" cssClass="error" />
		</td>
	</tr>
</table>	
</form:form>
</div>

<script type="text/javascript">
function doSubmit() {
	var frm = document.getElementById("f1");
	
	frm.action = "boardAddConf.html";
	frm.submit();
}
</script>