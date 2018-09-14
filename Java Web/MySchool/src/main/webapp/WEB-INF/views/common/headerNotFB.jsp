<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.mycompany.myschool.web.entity.base.UserEntity" %>

<div class="header_cover">
	<header class="header_menu">
		<div class="leftarea">
			<span class="btn_header01">
				<a href="<%=request.getContextPath() %>/index.do">
				<img src="<%=request.getContextPath() %>/images/common/logo.gif" alt="logo" width="256" height="41" border="0">
				</a>
			</span>
		</div>
		<div class="righttarea">
			<span style="float: right; margin-top: 25px;">
				<font color="white">안녕하세요, <%=((UserEntity)session.getAttribute("login_user")).getEmail() %> 님</font>
				<button id="logout" style="width:110px; height: 20px; font-size: 10px;">로그아웃</button>
			</span>
		</div>
	</header>	
</div>

