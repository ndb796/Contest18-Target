<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="css/admin.css" rel="stylesheet" type="text/css">

<div style="position:absolute; top:50px; left:20px; width:960px; height:600px; ">
<input type="button" value="Next" style="width:70px; height: 25px; font-size: 12px;" onclick="doSubmit();"/>&nbsp;
<input type="button" value="Cancel" style="width:70px; height: 25px; font-size: 12px;" onclick="javascript:location.href='userList.html'"/>&nbsp;
<br/><br/>
<br/><br/>
<table  style="width:600px; border: 0px;">
	<tr>
		<td class="star_class" style="width:11px;"><font color='red'>*</font></td>
		<td class="input_td" style="width:118px;" nowrap>First Name</td>
		<td class="input_td" style="width:70px;">&nbsp;</td>
		<td class="input_td" style="width:510px; height: 50px; text-align: left;">
			<c:out value='${user_entity.fname }'/>
		</td>
	</tr>
	<tr>
		<td class="star_class" style="width:11px;"><font color='red'>*</font></td>
		<td class="input_td" style="width:118px;" nowrap>Last Name</td>
		<td class="input_td" style="width:70px;">&nbsp;</td>
		<td class="input_td" style="width:510px; height: 50px; text-align: left;">
			<c:out value='${user_entity.lname }'/>
		</td>
	</tr>
	<tr>
		<td class="star_class" style="width:11px;"><font color='red'>*</font></td>
		<td class="input_td" style="width:118px;" nowrap>Location</td>
		<td class="input_td" style="width:70px;">&nbsp;</td>
		<td class="input_td" style="width:510px; height: 50px; text-align: left;">
			<c:if test="${user_entity.location == 'kr'}" >KR</c:if> 
			<c:if test="${user_entity.location == 'jp'}" >JP</c:if> 
			<c:if test="${user_entity.location == 'en'}" >EN</c:if> 
		</td>
	</tr>
	<tr>
		<td class="star_class"><font color='red'>*</font></td>
		<td class="input_td" nowrap>User E-mail</td>
		<td class="input_td" style="width:50px;">&nbsp;</td>
		<td class="input_td" style="width:510px; height: 50px; text-align: left;">
			<c:out value='${user_entity.email }'/>
		</td>
	</tr>
	<tr>
		<td class="star_class"><font color='red'>*</font></td>
		<td class="input_td" nowrap>User Password</td>
		<td class="input_td" style="width:50px;">&nbsp;</td>
		<td class="input_td" style="width:510px; height: 50px; text-align: left;">
			<c:out value='${user_entity.pwd }'/>
		</td>
	</tr>
</table>
<form name="f1" id="f1" method="post">
	<input type="hidden" name="fname" value="<c:out value='${user_entity.fname}'/>" />
	<input type="hidden" name="lname" value="<c:out value='${user_entity.lname}'/>" />
	<input type="hidden" name="location" value="<c:out value='${user_entity.location}'/>" />
	<input type="hidden" name="email" value="<c:out value='${user_entity.email}'/>" />
	<input type="hidden" name="pwd" value="<c:out value='${user_entity.pwd}'/>" />
</form>	
</div>

<script type="text/javascript">
function doSubmit() {
	var frm = document.getElementById("f1");
	
	frm.action = "userAddProc.html";
	frm.submit();
}
</script>