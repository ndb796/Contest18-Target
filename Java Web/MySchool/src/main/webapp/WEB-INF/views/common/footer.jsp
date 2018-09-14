<%@ page contentType="text/html;charset=utf-8" %>
<%
String logoutPage = request.getParameter("logoutPage");
%>
<div class="bottom_contents">
	<div class="navigation_area">
		<p>
		      회사개요&nbsp;&nbsp;l&nbsp;&nbsp;
		   <%-- <a href="<%=request.getContextPath() %>/privacy_policy.jsp" target="_blank">개인정보보호</a>&nbsp;&nbsp;l&nbsp;&nbsp;
		   <a href="<%=request.getContextPath() %>/rules.jsp" target="_blank">이용약관</a>&nbsp;&nbsp;l&nbsp;&nbsp;
		   <a href="mailto:info@test.com?subject=[상담요청]">상담요청</a> --%>
		   개인정보보호&nbsp;&nbsp;l&nbsp;&nbsp;
		   이용약관&nbsp;&nbsp;l&nbsp;&nbsp;
		   상담요청
		</p>
		<p class="copyright">Copyright &copy; 2014 My School, Inc. All rights reserved.</p>
       </div>
</div>

<%
if (logoutPage == null || !logoutPage.equals("no")) {
%>
<div id="dialog-logout" title="확인 메시지">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span></p>
</div>
<%
}
%>