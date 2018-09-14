<%@page import="java.util.ResourceBundle"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="java.io.*, java.util.*" %>

<%
File file = new File(request.getRealPath("/") + File.separator + "WEB-INF" 
		+ File.separator + "spring" + File.separator + "appServlet","root-context.xml");

ResourceBundle bundle = ResourceBundle.getBundle("conf/db");
Enumeration em = bundle.getKeys();
%>
<html>
<head>
	<title>Admin - Properties</title>
</head>
<body>
	<h3>DB Properties</h3>
	<table border=1>
		<tr><th width=100>Key</th><th width=200>Value</th></tr>
	<% while (em.hasMoreElements()) { 
		String key = (String)em.nextElement();
		String value = (String)bundle.getString(key);
	%>
		<tr>
			<td><%=key %></td><td><%=value %></td>
		</tr>
	<% }  %>
	</table>
	<input type="button" value="EDIT">
</body>
</html>
