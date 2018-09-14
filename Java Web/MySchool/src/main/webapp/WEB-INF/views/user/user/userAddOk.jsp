<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%
String resultCode = (String)request.getAttribute("resultCode");
String resultMsg = (String)request.getAttribute("resultMsg");

%>
<html>
<head>
<script type="text/javascript">
function onLoad() {
	alert("<%=resultMsg%>");
	location.href = "userList.html";
}
</script>
</head>
<body onload="onLoad()">

</body>
</html>