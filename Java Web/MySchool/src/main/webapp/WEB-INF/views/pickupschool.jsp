<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.entity.base.UserEntity" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
if(request.getAttribute("token_key") == null) {
	Token.set(request);
}

String resultCode = request.getParameter("resultCode");
String resultMsg = "";
if (resultCode != null) {
	resultMsg = ResultCode.GET_MESSAGE_MAP().get(resultCode);
}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="description" content="학생들에게 적합한 학교 정보 제공! 학교로부터 스카우트된다! 보이는 학생, 보이는 학교 정보! 학교는 학교 행사 참여율 응모 율 진학률 등이 사실적으로 관리, 확인할 수있다.">
<meta name="keywords" content="보이는 학생, 보이는 학교 데뷔 스토리 학교 매칭, student, school, 학교, 학교, 진로 상담, 학교 정보, 학생 정보, 진로, 진학, 대학 예비 학교, 유학, 오픈 캠퍼스, 이벤트, 입시 담당자 고민, 상담">
<title>My School | 학생과 학교의 최고의 매칭 시스템</title>
<link rel="stylesheet" href="css/style.css" >
<!-- <link rel="stylesheet" href="css/top.css" > -->
<link rel="stylesheet" href="css/pickup.css" >
<link rel="stylesheet" href="css/easydropdown.css" />

<link rel="stylesheet" href="css/start/jquery-ui-1.10.4.custom.css" />
<!-- <link rel="stylesheet" href="css/cupertino/jquery-ui.min.css" /> -->
<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="js/jquery.easydropdown.js"></script>

<style>
	body { font-size: 80%; background-color: #fff;}
	/*label, input { display:block; }*/
	input.text { margin-bottom:12px; width:95%; padding: .4em; }
	fieldset { padding:0; border:0; margin-top:25px; }
	h1 { font-size: 1.2em; margin: .6em 0; }
	.ui-dialog .ui-state-error { padding: .3em; }
	.validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>

<script type="text/javascript">
window.onload = function () {
	var resultMsg = "<%=resultMsg%>";
	var resultCode = "<%=resultCode%>";
	if (resultMsg != null && resultMsg.length > 0) {
		$( "#dialog-error" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>" + resultMsg);
		if (resultCode == "S10006" ) {
			$( "#dialog-error" ).dialog( "option", "title", "확인 메시지" );
		} 
		
		$( "#dialog-error" ).dialog( "open" );
	}
};

$(function() {
	$( "#dialog-error" ).dialog({
		autoOpen: false,
	    resizable: false,
	    height: 250,
	    width: 350,
	    modal: true,
	    buttons: {
	       "닫기": function() {
	         $( this ).dialog( "close" );
	       }
	    }
	});
	
	$("#dialog-form").dialog({
		autoOpen: false,
		show:	{effect: "blind", duration: 500},
		height: 320,
		width: 400,
//		position: [800, 20],
		modal: true,
		buttons: {
			로그인: function() {
				doLogin($("#user_email"), $("#user_pwd"));
			}
		},
		close: function() {
			document.getElementById("user_email").value = "";
			document.getElementById("user_pwd").value = "";
		}
	});
});

function updateTips( t ) {
	$(".validateTips").text( t ).addClass( "ui-state-highlight" );
	setTimeout(function() {
		$(".validateTips").removeClass( "ui-state-highlight", 1500 );
	}, 500 );
}

function checkNull( o, n ) {
	if ( o.val().length == 0) {
		o.addClass( "ui-state-error" );
		updateTips( "Please, enter your " + n + "." );
		return false;
	} else {
		return true;
	}
}

function checkRegexp( o, regexp, n ) {
	if ( !( regexp.test( o.val() ) ) ) {
		o.addClass( "ui-state-error" );
		updateTips( n );
		return false;
	} else {
		return true;
	}
}

function doLogin(objEmail, objPwd) {
	var bValid = true;
	objEmail.removeClass( "ui-state-error" );
	objPwd.removeClass( "ui-state-error" );
	
	bValid = bValid && checkNull( objEmail, "e-mail" );
	bValid = bValid && checkRegexp( objEmail, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "ex) test@My School.com" );
	bValid = bValid && checkNull( objPwd, "password" );
	
	if (bValid) {
		var f1 = document.getElementById("register");
		f1.action = "loginProc.do";
		f1.submit();
	}
}

function openLogin() {
	$("#dialog-form").dialog("open");
}

function doSearch() {
	var f1 = document.frm;
	
	var srch_school = f1.srch_school;
	var srch_loc = f1.srch_loc;
	var srch_founder = f1.srch_founder;
	var keywords = f1.keywords;
	
	var selKeywords = "";
	for (var i=0; i<keywords.length; i++) {
		if (keywords[i].checked) {
			selKeywords += keywords[i].value + ",";
		}
	}
	
	var errorMsg = "";
	
	if (srch_school.value == "" || srch_loc.value == "") {
	 	errorMsg += "<p><span class='ui-icon ui-icon-alert' style='text-align: left; float:left; margin:0 7px 20px 0;'></span>검색하는 학교종류와 진학장소는 검색 필수항목입니다.</p>";
	}
	
	if (errorMsg != "") {
	 	$( "#dialog-error" ).html(errorMsg);
	 	$( "#dialog-error" ).dialog( "open" );
	}  else {
		//console.log(srch_school.value + "/" + srch_loc.value + "/" + srch_founder.value + "/" + keywords.length);
		f1.srch_keywords.value = selKeywords;
		f1.submit();
	}
}
</script>
<script src="js/functions.js"></script>

<script src="http://connect.facebook.net/ko_KR/all.js "></script>
<script type="text/javascript">
function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
      connected();
    } else if (response.status === 'not_authorized') {
      // document.getElementById('status').innerHTML = 'Please log into this app.';
      console.log("not_authorized");
    } else {
      // document.getElementById('status').innerHTML = 'Please log into Facebook.';
    	console.log("else");
    }
  }

  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
  FB.init({
    appId      : '',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.0' // use version 2.0
  });
  
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });

  };

  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  function connected() {
    FB.api('/me', function(user) {
      var f1 = document.getElementById('fb_frm');
      f1.fbUserId.value = user.id; // http://graph.facebook.com/' + user.id + '/picture
      f1.email.value = user.email;
      f1.fname.value = user.first_name;
      f1.lname.value = user.last_name;
      f1.gender.value = user.gender;
      
      console.log(user.id);
      console.log(user.email);
      console.log(user.first_name);
      console.log(user.last_name);
      console.log(user.gender);
      
      f1.action = "<%=request.getContextPath()%>/fbLoginProc.do";
      //f1.submit();
    });
  }
</script>
</head>
<body onload="initialize();">

<div class="headerwrap01">
    <div class="header_navigation_wrap">
    	<div class="header_navigation">
    			<h1>
    				<a href="<%=request.getContextPath() %>/index.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/pickup/main_logo_w.png" alt="My School" width="248" height="28"></a></h1>
    	
    	
    		    <p class="select_language">
    	    </select>
    		</p>
    		
    			<p class="loginbtn">
    				<a href="javascript:openLogin();">
    				<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/btn_login.png" alt="login" width="83" height="23"></a>
    			</p>
    			<p class="register_btn">
    				<a href="/user/joinType.do">
    				<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/btn_jp.png" alt="신규등록" width="83" height="23"></a>
    			</p>
    	    <!-- <p class="fb">
    	    	<a href="https://www.facebook.com/pages/My School/611518178947356?ref=hl" target="_blank">
    		        	<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/pickup/icon_fb_w.png" alt="facebook" width="42" height="42" border="0"></a>
    	    </p>
    			<p class="tw">
    				<a href="https://twitter.com/My School?original_referer=http%3A%2F%2Fukaruresume.com%2F&profile_id=2267427624&tw_i=420011764361621504&tw_p=embeddedtimeline&tw_w=417488098335797248" target="_blank">
    				<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/pickup/icon_tw_w.png" alt="twitter" width="42" height="42" border="0"></a>
    	      </p> -->
    	 </div>
    </div>
</div>
<section class="main_contents">
<div class="breadcrumbs">
<a href="/">TOP</a>
&nbsp;<span>＞</span>&nbsp;
한국 여자 중/고등학교
</div>
<div class="form_box">
		
	    <div class="school_promotion">
	    	<div class="school_Movie">
	    		<div><img src="/images/locale/ja/user/pages/pickup/movie.jpg" width="301" height="203" alt=""></div>
	    	</div>
			<h1>한국 여자 중학교, 고등학교</h1>
	        <h2>한국 여자 중학교에서는 창립이래 건학 정신을 계승해, 사람으로서의 올바른 길과 배려의 마음을 소중히하고, 노력을 쌓아 재능을 늘리는 교육을 목표로 왔습니다.</h2>
	        <p>한국 여자 중학교에서는 학생들의 자주적인 학생회 활동이나 학교 행사가 한창입니다. 문화계, 체육계 따라 48 클럽이 옛날부터 모든 학생들이 참가 고 3까지 활동을 계속하고 있습니다. 다양한 장면에서 학생 개개인이 가지고있는 재능을 발견하고 감성 풍부한 성장하고 있습니다.</p>
	    </div>
	    	    
	    <h3>한국 여자 중학교, 고등학교의 시설</h3>
	    <div class="school_facility">
	    	<div class="mainPicArea">
	    		<div id="full">
					<img src="/images/locale/ja/user/pages/pickup/full_facility_01.jpg">
				</div>
	    	</div>
	    	<div class="thumbArea">
	    		<ul id="thumb">
					<li><img src="/images/locale/ja/user/pages/pickup/thumb_facility_01.jpg"></li>
					<li><img src="/images/locale/ja/user/pages/pickup/thumb_facility_02.jpg"></li>
					<li><img src="/images/locale/ja/user/pages/pickup/thumb_facility_03.jpg"></li>
					<li><img src="/images/locale/ja/user/pages/pickup/thumb_facility_04.jpg"></li>
					<li><img src="/images/locale/ja/user/pages/pickup/thumb_facility_05.jpg"></li>
					<li><img src="/images/locale/ja/user/pages/pickup/thumb_facility_06.jpg"></li>
				</ul>
	    	</div>
	    </div>
	    
	    <h3>한국 여자 중학교의 교육방침</h3>
		<div class="school_policy_wrap">
		    <div class="school_policy">
				<dl>
					<dt>【도덕적 실천】</dt>
					<dd>어느 시대, 어느 국민도 모두 도의적 바로 인간이어야한다 것은 물론이고 있습니다. 학생들에게 도덕을 수행하는 것이 중요하다는 소중함을 훈계 더욱이를 실행하는 습관과 기쁨을 체득시키고 싶습니다. </dd>
					<dt> [근면 노력] </dt>
					<dd> 사물을 이루는있어서 열심히 노력하는 것보다 더 귀한 것은 없습니다. 이것이 사건의 성패의 열쇠라고합시다. 일상의 학습과 클럽 활동을 통해 성실하게 노력을 쌓아가는 것을 목표로하고 있습니다. </dd>
					<dt> [一能 집중] </dt>
					<dd> 인간에게는 반드시 그 특유의 재능이있는 것으로 믿고 있습니다. 지금을 "一能"라고하자. 아직 명확하게 나타나 있지 않을지도 모른다 학생들의 뛰어난 재능을 발견하고 육성하며, 광택시키는 것이야말로 사람을 살리는 까닭이라고 생각하고 있습니다. </dd>
					</dl>
					<h4> 특색있는 교육으로 매일 아침 바느질 </h4>
					<dl>
					<dt> [무심하게된다] </dt>
					<dd> 일을 이루는 데 일사불란하게 이에 대처하는 것은 매우 중요한 일입니다. 다른 잡념을 깨끗이 버리고 무심가 무아의 경지에 들어갑니다. </dd>
					<dt> [기초의 중요성을 알] </dt>
					<dd> 사물에 모든 기초가 중요합니다. 학문도 연구도 스포츠도, 직장도 모두 기초적인 것이 중요합니다. 헝겊 조각 한 바늘을 통해 "기초의 중요성 '을 배웁니다. </dd>
					<dt> 【노력의 축적이 중요합니다] </dt>
					<dd> 상황이 냉정한 노력을 쌓아 이야말로 타의 추종을 허락하지 않는 것에 크게 성장해가는 것입니다. 매일 아침 5 분의 바느질, 3 년 또는 6 년 동안 입학 당초의 자신과 지금의 자신을 비교하고 그것이 중요한 것인지을 체득합니다. </dd>
					<dt> [특기을 가진] </dt>
					<dd> 개인 모두 각각 특기을 가지고있을 것입니다. 이것은을 가져야하지 않으며 기지개해야합니다. 본교 자신도 다른 학교에없는 특기을 가져야한다고 생각하고 있습니다. </dd>
					</dl>
					<h4> 특색있는 교육으로의 예법 · 예절 교실 </h4>
					<p> 매너는 타인에 대한 배려의 마음이 형태가 나타난 것입니다. 모양을 배우고 그 안쪽에있는 마음을 체득합니다. 사회에 나와 필수가되는 장소에 어울리는 매너을 익힌 품위있는여 성 성장했으면 좋겠다고 바라고 있습니다. 예법 부의지도도하고있다 오가사와라 류 예법 선생님이 예법 매너에 관한 강의와 일본식과 서양식의 서 행동의지도을 실시하고 있습니다. </p>
		    </div>  
			<!-- <span class="map"> -->
	   		
	   		<div class="policy_thumb">
	   			<ul class="policy_thumb_list">
	   				<li><img src="/images/locale/ja/user/pages/pickup/policy_01.jpg" alt=""></li>
	   				<li><img src="/images/locale/ja/user/pages/pickup/policy_02.jpg" alt=""></li>
	   				<li><img src="/images/locale/ja/user/pages/pickup/policy_03.jpg" alt=""></li>
	   			</ul>
	   		</div>
		</div>	      
		<p class="go_top" style="display: block; opacity: 1;"><a href="#top"><img src="/images/locale/ja/user/pages/top/pagetop.png" alt="PAGE TOP" width="82" height="12"></a></p>
	    
	    
	    <h3>상담문의/주소/교통</h3>
		<div class="school_info_wrap">
		    <div class="school_info">
		    	<h4>상담문의</h4>
		        <p>한국 중학교, 고등학교 TEL：02-3983-8261</p>
		        <h4>주소</h4>
		        <p>우 12345 서울시 강동구 1-25-222</p>
		        <h4>교통</h4>
		        <ul>
		        	<li>지하철 5호선 강동역 1번출구 걸어서 3분</li>
		        </ul>
		    </div>  
			<div id="map_canvas" style="width:484px;height:328px;"></div>

		</div>	      
		<p class="go_top" style="display: block; opacity: 1;"><a href="#top"><img src="/images/locale/ja/user/pages/top/pagetop.png" alt="PAGE TOP" width="82" height="12"></a></p>
	</div>
</section>

<div id="dialog-error" title="エーら메시지">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>에러 메시지</p>
</div>

<footer>
   	<jsp:include page="footer.jsp" />
</footer>
<meta name="copyright" content="Copyright (C) 2014-My School" />
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-52579319-1', 'auto');
  ga('send', 'pageview');

</script>
<!--
<div id="dialog-error" title="Error dialog">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>error message</p>
</div>
-->
<div id="dialog-form" title="로그인">
	<p class="validateTips">이메일과 비밀번호를 입력해 주십시오.</p>
	<form id="register" method="post">
	<fieldset>
		<label for="name">이메일</label>
		<input type="text" name="user_email" id="user_email" class="text ui-widget-content ui-corner-all" />
		<label for="password">비밀번호</label>
		<input type="password" name="user_pwd" id="user_pwd" value="" class="text ui-widget-content ui-corner-all" />
		<label for="facebook"></label>
	</fieldset>
	</form>
		<div style="text-align: right;"><fb:login-button scope="public_profile,email" onlogin="checkLoginState();" /></div>
</div>

<form id="fb_frm" name="fb_frm" method="POST">
	<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
	<input type="hidden" name="fbUserId" value="" />
	<input type="hidden" name="email" value="" />
	<input type="hidden" name="fname" value="" />
	<input type="hidden" name="lname" value="" />
	<input type="hidden" name="gender" value="" />
	<input type="hidden" name="joinType" value="1" />
	<input type="hidden" name="memberType" value="1" />
</form>

<script>
$(function () {
  for(var i = 1; i <= 5; i++) {
    $('<img>').attr('src', '/images/locale/ja/user/pages/pickup/full_facility_0' + i + '.jpg');
  }

  $('#thumb img').click(function() {
    var img_full = $(this).attr('src').replace('thumb', 'full');

    $('#full img').attr('src', img_full);
  });
});
</script>
<script>
function initialize() {
	var latlng = new google.maps.LatLng(35.728552,139.716457);
	var myOptions = {
	    zoom: 17, 
    	center: latlng, 
    	mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map(document.getElementById('map_canvas'), myOptions);
	
	var markerOptions = {
    	position: latlng,
    	map: map,
    	title: ''
	};
	
	var marker = new google.maps.Marker(markerOptions);
}
</script>
</body>
</html>
