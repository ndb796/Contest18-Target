<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>My School</title>
<style type="text/css">
body, html {
	margin: 0; padding: 0; height: 100%; overflow: hidden;
}
#content {
	position: absolute; left: 0; right: 0; bottom: 0; top: 0;
}
</style>
</head>
<body>
<% 
String resultCode = request.getParameter("resultCode") != null && request.getParameter("resultCode").length() > 0 ? "?resultCode=" + request.getParameter("resultCode") : "";
response.sendRedirect(request.getContextPath() + "/index.do" + resultCode); 
%>
<%-- <div id="content">
<iframe src="<%=request.getContextPath() %>/index.do" width="100%" height="100%" frameborder="0" name="main_frame" id="main_frame"></iframe> 
</div> --%>
</body>
</html>