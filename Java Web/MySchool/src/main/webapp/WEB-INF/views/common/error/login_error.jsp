<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%
String errMsg = (String)request.getAttribute("loginNG");
response.sendRedirect("/Openapi/login.jsp?errMsg=" + errMsg);
%>