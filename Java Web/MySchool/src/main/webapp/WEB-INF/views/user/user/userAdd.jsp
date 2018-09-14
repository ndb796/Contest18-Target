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
<input type="button" value="Cancel" style="width:70px; height: 25px; font-size: 12px;" onclick="javascript:location.href='userList.html'" />&nbsp;
<br/><br/>
<br/><br/>
<form:form commandName="userEntity" autocomplete="off" name="f1" id="f1" method="post"> 
<table style="width:100%; border: 0px;">
	<tr>
		<td class="star_class"><font color='red'>*</font></td>
		<td class="input_td" nowrap>First Name</td>
		<td class="input_td" style="width:50px;">&nbsp;</td>
		<td class="input_td" style="height: 50px; text-align: left;">
			<form:input path="fname" cssStyle="width:510px; height:20px;" maxlength="100" /><br/>
			<form:errors path="fname" cssClass="error" />
		</td>
	</tr>
	<tr>
		<td class="star_class"><font color='red'>*</font></td>
		<td class="input_td" nowrap>Last Name</td>
		<td class="input_td" style="width:50px;">&nbsp;</td>
		<td class="input_td" style="height: 50px; text-align: left;">
			<form:input path="lname" cssStyle="width:510px; height:20px;" maxlength="100" /><br/>
			<form:errors path="lname" cssClass="error" />
		</td>
	</tr>
	<tr>
		<td class="star_class"><font color='red'>*</font></td>
		<td class="input_td" nowrap>Location</td>
		<td class="input_td" style="width:50px;">&nbsp;</td>
		<td class="input_td" style="height: 50px; text-align: left;">
		   	<form:select path="location" cssStyle="width:70px; height:20px;">
		   		<form:option value="kr">KR</form:option>
		   		<form:option value="jp">JP</form:option>
		   		<form:option value="en">EN</form:option>
		   	</form:select>
		</td>
	</tr>
	<tr>
		<td class="star_class"><font color='red'>*</font></td>
		<td class="input_td" nowrap>E-mail</td>
		<td class="input_td" style="width:50px;">&nbsp;</td>
		<td class="input_td" style="height: 50px; text-align: left;">
			<form:input path="email" cssStyle="width:510px; height:20px;" maxlength="100" /><br/>
			<form:errors path="email" cssClass="error" />
		</td>
	</tr>
	<tr>
		<td class="star_class"><font color='red'>*</font></td>
		<td class="input_td" nowrap>User Password</td>
		<td class="input_td" style="width:50px;">&nbsp;</td>
		<td class="input_td" style="height: 50px; text-align: left;">
			<form:password path="pwd" cssStyle="width:510px; height:20px;" maxlength="100" /><br/>
			<form:errors path="pwd" cssClass="error" />
		</td>
	</tr>
</table>	
</form:form>
</div>

<script type="text/javascript">
function doSubmit() {
	var frm = document.getElementById("f1");
	
	frm.action = "userAddConf.html";
	frm.submit();
}
</script>