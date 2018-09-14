<%
String agt = request.getHeader("user-agent").toLowerCase();
boolean isNotIE = false;
if (agt.indexOf("chrome") != -1 || agt.indexOf("safari") != -1 || agt.indexOf("firefox") != -1) {
	isNotIE = true;
} 
if (agt.indexOf("trident") != -1) {
	isNotIE = false;
}
isNotIE = true;
%>

<% if (isNotIE) { %>
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/atlas/style/atlas.css">
<script src="<%=request.getContextPath() %>/js/arbor.js"></script>
<script src="<%=request.getContextPath() %>/atlas/atlasForMain.js"></script>
<% } %>

<div id="maps"><ul></ul></div>
<canvas id="viewport" style="width:100%; height:100%;"></canvas>