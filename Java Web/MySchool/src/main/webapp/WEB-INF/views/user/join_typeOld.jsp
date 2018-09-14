<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>로그인|My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/pc_login.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script>
$(function() {
	$( "#dialog-message" ).dialog({
		autoOpen: false,
	   	modal: true,
	    height: 480,
	    width: 660
	});
});

var joinType = "", memberType = "";

function doEmail() {
	joinType = 4;
	$( "#dialog-message" ).dialog( "open" );
}

function selectType(obj) {
	memberType = obj;
	$( "#dialog-message" ).dialog( "close" );
	var f1 = document.getElementById("frm");
	f1.joinType.value = joinType;
	f1.memberType.value = memberType;
	
	f1.submit();
}
</script>
</head>
<body>
<form action="<%=request.getContextPath() %>/user/emailForm.do" id="frm" name="frm">
	<input type="hidden" name="joinType" >
	<input type="hidden" name="memberType" >
</form>

	<div class="header_cover">
	<header class="header_menu">
	<div class="leftarea">
	<span class="btn_header01"><a href="<%=request.getContextPath() %>/index.do"><img src="<%=request.getContextPath() %>/images/common/logo.gif" alt="logo" width="256" height="41" border="0"></a></span>
	</div>

	</header>	
    
<!--header_menu--></div>
	
<section class="main_contents">
	
	<h1 class="register">신규등록</h1>
  		
	<p>My School을 이용하기 위해서는 회원등록이 필요합니다. 등록은 무료입니다.<br>
이메일로 등록ID을 이미 가지고 있는 분은 해당 이메일을 ID로 신규등록, 로그인에 이용할 수도 있습니다. 익숙한 ID을 이용하여 ID나 비밀번호 분실을 방지하고 간편하게 로그인 할 수 있습니다.</p>

	<p>아이디 외부 ID로 신규 등록하려면 아래에서 이용하고 싶은 연계 ID 서비스을 클릭하고 화면의 지시를 따라 등록을 진행합니다.</p>


	<div class="register_list">
	<p>
    <span class="icon"><a href=""><img src="<%=request.getContextPath() %>/images/common/pc_login/icon_twitter.png" alt="" width="155" height="155" border="0"></a></span>
    <span class="icon"><a href=""><img src="<%=request.getContextPath() %>/images/common/pc_login/icon_facebook.png" alt="" width="155" height="155" border="0"></a></span>
    <span class="icon"><a href=""><img src="<%=request.getContextPath() %>/images/common/pc_login/icon_google.png" alt="" width="155" height="155" border="0"></a></span>
    <span class="icon"><a href="javascript:doEmail();"><img src="<%=request.getContextPath() %>/images/common/pc_login/icon_mail.png" alt="" width="155" height="155" border="0"></a></span>
    </p>
	</div>

</section>

<div class="fix01"></div>

<div id="dialog-message" title="Select member type">
	<section class="select_area">
    	<p><span class="ui-helper-hidden-accessible"><input type="text"/></span></p>
		<h1><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/join/massage.png" alt="logo" width="568" height="128" border="0"></h1>
        <h2><a href="javascript:selectType(1);"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/join/student_parents.png" alt="logo" width="155" height="155" border="0"></a><span class="centerfix"></span>
        <a href="javascript:selectType(2);"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/join/school_agent.png" alt="logo" width="155" height="156" border="0"></a></h2>
	</section>
</div>

<footer>
	<jsp:include page="../common/footer.jsp" />
</footer>
</body>
</html>
