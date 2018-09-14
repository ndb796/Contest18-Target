<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>로그인|My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/pc_login.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" >

<style>
#share *{
   filter:alpha(opacity=0.1); 
   opacity:0.01;/*Hide Facebook button*/
}

#share{
  display:inline-block;

  /*The width and height of your image. Not bigger than the Like button!*/
  width: 10px;
  height: 10px;

  /*Your image*/
  background:url(<%=request.getContextPath() %>/images/common/pc_login/icon_facebook.png);
}
</style>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script>
//This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
  console.log('statusChangeCallback');
  console.log(response);
  // The response object is returned with a status field that lets the
  // app know the current login status of the person.
  // Full docs on the response object can be found in the documentation
  // for FB.getLoginStatus().
  if (response.status === 'connected') {
    // Logged into your app and Facebook.
    connected();
  } else if (response.status === 'not_authorized') {
    // The person is logged into Facebook, but not your app.
    // document.getElementById('status').innerHTML = 'Please log into this app.';
  } else {
    // The person is not logged into Facebook, so we're not sure if
    // they are logged into this app or not.
    // document.getElementById('status').innerHTML = 'Please log into Facebook.';
  }
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });
}

window.fbAsyncInit = function() {
FB.init({
  appId      : '1492172677662181',
  cookie     : true,  // enable cookies to allow the server to access 
                      // the session
  xfbml      : true,  // parse social plugins on this page
  version    : 'v2.0' // use version 2.0
});

// Now that we've initialized the JavaScript SDK, we call 
// FB.getLoginStatus().  This function gets the state of the
// person visiting this page and can return one of three states to
// the callback you provide.  They can be:
//
// 1. Logged into your app ('connected')
// 2. Logged into Facebook, but not your app ('not_authorized')
// 3. Not logged into Facebook and can't tell if they are logged into
//    your app or not.
//
// These three cases are handled in the callback function.

FB.getLoginStatus(function(response) {
  statusChangeCallback(response);
});

};

// Load the SDK asynchronously
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function connected() {
  console.log('Welcome!  Fetching your information.... ');
  FB.api('/me', function(user) {
    console.log('Successful login for: ' + user.name + "," + user.token);
    var f1 = document.getElementById('fb_frm');
    f1.userid.value = user.id; // http://graph.facebook.com/' + user.id + '/picture
    f1.email.value = user.email;
    f1.fname.value = user.first_name;
    f1.lname.value = user.last_name;
    f1.gender.value = user.gender;
    f1.locale.value = user.locale;
    
    console.log(user.id);
    console.log(user.email);
    console.log(user.first_name);
    console.log(user.last_name);
    console.log(user.gender);
    console.log(user.locale);
    console.log(user.image);
    //f1.action = "/Openapi/login/2.html";
    //f1.submit();
  });
}

$(function() {
	$( "#dialog-message" ).dialog({
		autoOpen: false,
	   	modal: true,
	    height: 480,
	    width: 660
	});
});

var joinType = "", memberType = "";

function doFB() {
	checkLoginState();
}

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

<form id="fb_frm" name="fb_frm" method="POST">
	<input type="hidden" name="userid" value="" />
	<input type="hidden" name="email" value="" />
	<input type="hidden" name="fname" value="" />
	<input type="hidden" name="lname" value="" />
	<input type="hidden" name="gender" value="" />
	<input type="hidden" name="locale" value="" />
</form>

<div id="dialog-message" title="회원종류을 선택해 주세요">
	<section class="select_area">
    	<p><span class="ui-helper-hidden-accessible"><input type="text"/></span></p>
		<h1><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/join/massage.png" alt="logo" width="568" height="128" border="0"></h1>
        <h2><a href="javascript:selectType(1);"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/join/student_parents.png" alt="logo" width="155" height="155" border="0"></a><span class="centerfix"></span>
        <a href="javascript:selectType(2);"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/join/school_agent.png" alt="logo" width="155" height="156" border="0"></a></h2>
	</section>
</div>

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


	<div class="register_list" style="vertical-align: top;">
	<p style="vertical-align: top;">
    <%-- <span class="icon"><a href=""><img src="<%=request.getContextPath() %>/images/common/pc_login/icon_twitter.png" alt="" width="155" height="155" border="0"></a></span>
    <span class="icon"><a href="javascript:doFB();"><img src="<%=request.getContextPath() %>/images/common/pc_login/icon_facebook.png" alt="" width="155" height="155" border="0"></a></span>
    <span class="icon"><a href=""><img src="<%=request.getContextPath() %>/images/common/pc_login/icon_google.png" alt="" width="155" height="155" border="0"></a></span> --%>
    <span class="icon">
    	<a href="javascript:doEmail();"><img src="<%=request.getContextPath() %>/images/common/pc_login/icon_mail.png" alt="" width="155" height="155" border="0"></a>
    </span>
    
    </p>
	</div>
</section>

<div class="fix01"></div>
	
<footer>
	<jsp:include page="../common/footer.jsp">
		<jsp:param value="no" name="logoutPage"/>
	</jsp:include>	
</footer>
</body>
</html>
