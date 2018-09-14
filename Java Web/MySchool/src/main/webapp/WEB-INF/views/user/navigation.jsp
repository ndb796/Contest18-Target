<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(function() {
	var selectedMenu = <%=request.getParameter("selMenu")%>;
	switch(selectedMenu){
		case 1:
		case 11:
		case 12:
			document.getElementById("selImg1").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/my_profile_on.png";
			document.getElementById("selImg2").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/school_off.png";
			document.getElementById("selImg3").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/schedule_off.png";
			document.getElementById("selImg4").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/statistics_off.png";
			document.getElementById("selImg5").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/sns_off.png";
			document.getElementById("selImg6").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/news_event_off.png";
				
			break;
		case 2:
		case 21:
		case 22:
			document.getElementById("selImg1").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/my_profile_off.png";
			document.getElementById("selImg2").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/school_on.png";
			document.getElementById("selImg3").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/schedule_off.png";
			document.getElementById("selImg4").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/statistics_off.png";
			document.getElementById("selImg5").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/sns_off.png";
			document.getElementById("selImg6").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/news_event_off.png";
			
			break;
		case 3:
		case 31:
		case 32:
			document.getElementById("selImg1").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/my_profile_off.png";
			document.getElementById("selImg2").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/school_off.png";
			document.getElementById("selImg3").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/schedule_on.png";
			document.getElementById("selImg4").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/statistics_off.png";
			document.getElementById("selImg5").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/sns_off.png";
			document.getElementById("selImg6").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/news_event_off.png";
			
			break;
		case 4:
			document.getElementById("selImg1").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/my_profile_off.png";
			document.getElementById("selImg2").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/school_off.png";
			document.getElementById("selImg3").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/schedule_off.png";
			document.getElementById("selImg4").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/statistics_on.png";
			document.getElementById("selImg5").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/sns_off.png";
			document.getElementById("selImg6").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/news_event_off.png";
			
			break;
		case 5:
		case 51:
			document.getElementById("selImg1").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/my_profile_off.png";
			document.getElementById("selImg2").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/school_off.png";
			document.getElementById("selImg3").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/schedule_off.png";
			document.getElementById("selImg4").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/statistics_off.png";
			document.getElementById("selImg5").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/sns_on.png";
			document.getElementById("selImg6").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/news_event_off.png";
			break;
		case 6:
			document.getElementById("selImg1").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/my_profile_off.png";
			document.getElementById("selImg2").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/school_off.png";
			document.getElementById("selImg3").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/schedule_off.png";
			document.getElementById("selImg4").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/statistics_off.png";
			document.getElementById("selImg5").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/sns_off.png";
			document.getElementById("selImg6").src = "<%=request.getContextPath() %>/images/locale/ja/user/pages/common/news_event_on.png";
			
			break;
	}
});

function selMenu(num){
	var frm = document.navFrm;
	
	switch(num){
		case 1:
			frm.action = "<%=request.getContextPath() %>/user/myprofile/stdBasicView.do";
			frm.submit();
				
			break;
		case 11:
			frm.action = "<%=request.getContextPath() %>/user/myprofile/stdEducationView.do";
			frm.submit();
				
			break;
		case 12:
			frm.action = "<%=request.getContextPath() %>/user/myprofile/stdSpecificView.do";
			frm.submit();
				
			break;
		case 2:
			frm.action = "<%=request.getContextPath() %>/user/school/schoolList.do";
			frm.submit();
			
			break;
		case 21:
			frm.action = "<%=request.getContextPath() %>/user/school/schoolRcmmdList.do";
			frm.submit();
				
			break;
		case 22:
			frm.action = "<%=request.getContextPath() %>/user/school/schoolFavorList.do";
			frm.submit();
				
			break;
		case 3:
			frm.action = "<%=request.getContextPath() %>/user/schedule/schedule.do";
			frm.submit();
			
			break;
		case 31:
			frm.action = "<%=request.getContextPath() %>/user/schedule/scheduleRcmmd.do";
			frm.submit();
			
			break;
		case 32:
			frm.action = "<%=request.getContextPath() %>/user/schedule/scheduleAll.do";
			frm.submit();
			
			break;
		case 4:
			frm.action = "<%=request.getContextPath() %>/user/statistics/statUser.do";
			frm.submit();
			
			break;
		case 5:
			frm.action = "<%=request.getContextPath() %>/user/sns/snsMessage.do";
			frm.submit();
			
			break;
		case 51:
			frm.action = "<%=request.getContextPath() %>/user/sns/snsMessage.do";
			frm.submit();
				
			break;
		case 6:
			frm.action = "<%=request.getContextPath() %>/user/news/newsList.do";
			frm.submit();
			
			break;
	}
}
</script>
<%-- <div class="header_cover">
	<header class="header_menu">
		<div class="leftarea">
			<span class="btn_header01">
				<a href="javascript:doMove('home')">
				<img src="<%=request.getContextPath() %>/images/common/logo.gif" alt="logo" width="256" height="41" border="0">
				</a>
			</span>
		</div>
		<div class="righttarea">
			<span style="float: right; margin-top: 25px;"><button id="logout" style="width:70px; height: 20px; font-size: 10px;">Logout</button></span>
		</div>
	</header>	
</div> --%>

<section class="main_contents">
	<div class="topic">
		<div class="navigation_btn">
        	<ul>
        		<c:choose >
   				<c:when test="${mode == 'add'}">
   					<li><img id="selImg1" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/my_profile_off.png" alt="my_profile" width="101" height="101" border="0"></li>
	           	 	<li><img id="selImg2" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/school_off.png" alt="school" width="100" height="101" border="0"></li>
	            	<li><img id="selImg3" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/schedule_off.png" alt="schedule" width="100" height="101" border="0"></li>
					<li><img id="selImg4" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/statistics_off.png" alt="statistics" width="100" height="101" border="0"></li>
	            	<li><img id="selImg5" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/sns_off.png" alt="sns" width="101" height="101" border="0"></li>
	            	<li><img id="selImg6" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/news_event_off.png" alt="news_event" width="100" height="101" border="0"></li>
   				</c:when>
   				<c:otherwise>
   					<li><a href="javascript:selMenu(1);"><img id="selImg1" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/my_profile_off.png" alt="my_profile" width="101" height="101" border="0"></a></li>
	           	 	<li><a href="javascript:selMenu(2);"><img id="selImg2" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/school_off.png" alt="school" width="100" height="101" border="0"></a></li>
	            	<li><a href="javascript:selMenu(3);"><img id="selImg3" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/schedule_off.png" alt="schedule" width="100" height="101" border="0"></a></li>
					<li><a href="javascript:selMenu(4);"><img id="selImg4" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/statistics_off.png" alt="statistics" width="100" height="101" border="0"></a></li>
	            	<li><a href="javascript:selMenu(5);"><img id="selImg5" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/sns_off.png" alt="sns" width="101" height="101" border="0"></a></li>
	            	<li><a href="javascript:selMenu(6);"><img id="selImg6" src="<%=request.getContextPath() %>/images/locale/ja/user/pages/common/news_event_off.png" alt="news_event" width="100" height="101" border="0"></a></li>
   				</c:otherwise>
   				</c:choose>
        		
        	</ul>
        </div>
    </div>
</section>

<form name="navFrm" method="post">
	<input type="hidden" name="email" value="<c:out value='${userEntity.email }'/>" />
	<input type="hidden" name="mem_type" value="<c:out value='${userEntity.mem_type }'/>" />
	<input type="hidden" name="student_id" value="<c:out value='${studentEntity.student_id }'/>" />
</form>