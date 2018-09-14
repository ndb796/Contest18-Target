<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String resultCode = (String)request.getAttribute("resultCode");
String resultMsg = (String)request.getAttribute("resultMsg");

%>
<html>
<head>
<script type="text/javascript">
function onLoad() {
	alert("<%=resultMsg%>");
	location.href = "groupList.html?user_id=<c:out value='${user_id }'/>";
}
</script>
</head>
<body onload="onLoad()">

</body>
</html>