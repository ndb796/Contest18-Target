<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="css/admin.css" rel="stylesheet" type="text/css">

<div style="position:absolute; top:50px; left:20px; width:960px; height:600px; ">
<input type="button" value="Next" style="width:70px; height: 25px; font-size: 12px;" onclick="doSubmit();"/>&nbsp;
<input type="button" value="Cancel" style="width:70px; height: 25px; font-size: 12px;" onclick="javascript:location.href='groupList.html?user_id=<c:out value="${user_id }"/>&group_id=<c:out value="${group_id }"/>'"/>&nbsp;
<br/><br/>
<br/><br/>
<table  style="width:600px; border: 0px;">
	<tr>
		<td class="star_class" style="width:11px;"><font color='red'>*</font></td>
		<td class="input_td" style="width:118px;" nowrap>Board Type</td>
		<td class="input_td" style="width:70px;">&nbsp;</td>
		<td class="input_td" style="width:510px; height: 50px; text-align: left;">
			<c:if test="${board_entity.board_type == 0 }">Public</c:if>
			<c:if test="${board_entity.board_type == 1 }">Group</c:if>
			<c:if test="${board_entity.board_type == 2 }">Privacy</c:if>
		</td>
	</tr>
	<tr>
		<td class="star_class" style="width:11px;"><font color='red'>*</font></td>
		<td class="input_td" style="width:118px;" nowrap>Contents</td>
		<td class="input_td" style="width:70px;">&nbsp;</td>
		<td class="input_td" style="width:510px; height: 50px; text-align: left;">
			<textarea name="contents" id="contents" style="width: 510px; height: 100px;" readonly="readonly"><c:out value='${board_entity.contents}'/></textarea>
		</td>
	</tr>
</table>
<form name="f1" id="f1" method="post">
	<input type="hidden" name="user_id" value="<c:out value='${board_entity.user_id}'/>" />
	<input type="hidden" name="group_id" value="<c:out value='${board_entity.group_id}'/>" />
	<input type="hidden" name="board_type" value="<c:out value='${board_entity.board_type}'/>" />
	<input type="hidden" name="contents" value="<c:out value='${board_entity.contents}'/>" />
</form>	
</div>

<script type="text/javascript">
function doSubmit() {
	var frm = document.getElementById("f1");
	
	frm.action = "boardAddProc.html";
	frm.submit();
}
</script>