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
<title>전체 블로그 | My School | 학생과 학교의 최고의 매칭 시스템</title>
<meta name="description" content="학생들에게 적합한 학교 정보 제공! 학교로부터 스카우트된다! 보이는 학생, 보이는 학교 정보! 학교는 학교 행사 참여율 응모 율 진학률 등이 사실적으로 관리, 확인할 수있다.">
<meta name="keywords" content="보이는 학생, 보이는 학교 데뷔 스토리 학교 매칭, student, school, 학교, 학교, 진로 상담, 학교 정보, 학생 정보, 진로, 진학, 대학 예비 학교, 유학, 오픈 캠퍼스, 이벤트, 입시 담당자 고민, 상담">
<link rel="stylesheet" href="css/style.css" >
<link rel="stylesheet" href="css/top.css" >
<link rel="stylesheet" href="css/easydropdown.css" />

<link rel="stylesheet" href="css/start/jquery-ui-1.10.4.custom.css" />
<!-- <link rel="stylesheet" href="css/cupertino/jquery-ui.min.css" /> -->
		
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="js/jquery.easydropdown.js"></script>

<style>
	body { font-size: 80%; background-color: #fff; position: relative;}
	/*label, input { display:block; }*/
	input.text { margin-bottom:12px; width:95%; padding: .4em; }
	fieldset { padding:0; border:0; margin-top:25px; }
	h1 { font-size: 1.2em; margin: .6em 0; }
	.ui-dialog .ui-state-error { padding: .3em; }
	.validateTips { border: 1px solid transparent; padding: 0.3em; }
	.likeBox {
		width: 960px;
		margin-bottom: 40px;
	}
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
		/* buttons: {
			로그인: function() {
				doLogin($("#user_email"), $("#user_pwd"));
			}
		}, */
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
	bValid = bValid && checkRegexp( objEmail, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "ex) test@test.com" );
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

$(function(){
	var h = $(window).height();
	// $(".searchForm").css("top", h - 161);
	if (h <= 800) {
		$(".viewer ul li, .headerwrap01").css("height", h);
	} else {
		$(".viewer ul li, .headerwrap01").css("height", '800px');
	}
});

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
	 	errorMsg += "<p><span class='ui-icon ui-icon-alert' style='text-align: left; float:left; margin:0 7px 20px 0;'></span>검색하려는 학교의 종류와 진학장소는 필수항목입니다.</p>";
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

function doBlogMove(idx) {
	location.href = "<%=request.getContextPath()%>/common/blog_new/blog.do?type=1#" + idx;
}
</script>
<script src="js/functions.js"></script>

<script src="http://connect.facebook.net/ko_KR/all.js "></script>
<script type="text/javascript">
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
	  
  function statusChangeCallback(response) {
    if (response.status === 'connected') {
      connected();
    } else if (response.status === 'not_authorized') {
      console.log("not_authorized");
    } else {
      console.log("statusChangeCallback else");
    }
  }

  var isCLickedLogin = false;
  function checkLoginState() {
	  console.log("test=" + isCLickedLogin);
    FB.getLoginStatus(function(response) {
      isCLickedLogin = true;
      //statusChangeCallback(response);
      if (response.status === 'connected') {
	      connected();
	    } else if (response.status === 'not_authorized') {
	      console.log("not_authorized");
	    } else {
	      console.log("checkLoginState else");
	      FB.login(function(response) {
	    	  if (response.status === 'connected') {
	    	      connected();
	    	  }
	      }, { scope:'user_website,email,offline_access,read_stream,publish_stream' });
	    }
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
      
      if (isCLickedLogin) {
      	f1.submit();
      }
    });
  }
</script>
</head>
<body>
<section class="main_slide">
	<div class="viewer">
		<ul>
			<li class="viewer_01">
			</li>
			<li class="viewer_02">
			</li>
			<li class="viewer_03">
			</li>
			<li class="viewer_04">
			</li>
		</ul>
	</div>
</section>

<div class="headerwrap01">
    <div class="header_navigation_wrap">
    	<div class="header_navigation">
    			<h1>
    				<a href="<%=request.getContextPath() %>/index.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/main_logo.png" alt="MY SCHOOL" width="310"></a></h1>
    		    <p class="select_language">
    		</p>
    		
    			<p class="loginbtn">
    				<a href="javascript:openLogin();">
    				<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/btn_login.png" alt="login" width="83" height="23"></a>
    			</p>
    			<p class="register_btn">
    				<a href="/user/joinType.do">
    				<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/btn_jp.png" alt="신규등록" width="83" height="23"></a>
    			</p>
    	 </div>
    </div>
    <div class="centerfix">
    <h2 class="slogan">자신에게 맞는 학교를 찾을 수 있다.<br><span class="subSlogan">당신과 학교의 교육 솔루션 플랫폼!</span></h2>
	<div class="schoolName_08">
		<span class="title">학교모집</span>
		<span class="text">교육방침과 교육환경을<br>
		소개하시겠습니까？<br>
		지금이라면 무료로 등록<br>
		할 수 있습니다.</span>
	</div>
	<div class="schoolName_10">
		<span class="text">보호자와 학생은<br>
		<span class="underline">무료</span>로 등록<br>
		할 수 있습니다.</span>
	</div>

    </div>
    <div class="searchForm">
    	<form action="<%=request.getContextPath() %>/common/search.do" id="frm" method="post" name="frm">
	        <select class="dropdown" id="srch_school" name="srch_school">
	        	<option value="" class="label">학교</option>
	            <option value="2">중학교</option>
	            <option value="3">고등학교</option>
	            <option value="4">대학교</option>
	            <option value="5">전문학교</option>
	        </select>
	        <select class="dropdown" id="srch_loc" name="srch_loc">
	        	<option value="" class="label">진학장소</option>
                <option value="13">경기도</option>
                <option value="14">강원도</option>
                <option value="11">경상남도</option>
                <option value="12">경상북도</option>
                <option value="19">전라남도</option>
                <option value="20">전라북도</option>
                <option value="15">충청남도</option>
                <option value="16">충청북도</option>
                <option value="17">제주도</option>
	            <%-- <% for (int i=0; i<Constants.JPN_LOCATION.length; i++) { %>
	            	 <option value="<%=Constants.JPN_LOCATION[i]%>"><%=Constants.JPN_LOCATION[i]%></option>
	            <% } %> --%>
	        </select>
	        <select class="dropdown" id="srch_founder" name="srch_founder">
	        	<option value="" class="label">학교 종류</option>
	            <option value="1">국/공립</option>
	            <option value="3">사립</option>
	        </select>
        	<p class="keyword">키워드<span class="carat"></span></p>
			<div class="checkWrap">
				<label><input type="checkbox" name="keywords" value="CK0000001">영어, ESS, 어학</label>
				<label><input type="checkbox" name="keywords" value="CK0000002">동아리활동</label>
				<label><input type="checkbox" name="keywords" value="CK0000003">해외진학과 연계</label>
				<label><input type="checkbox" name="keywords" value="CK0000004">집에서의 거리</label>
				<label><input type="checkbox" name="keywords" value="CK0000005">젊은 선생님이 많음</label>
				<label><input type="checkbox" name="keywords" value="CK0000006">교정이 넓음</label>
				<label><input type="checkbox" name="keywords" value="CK0000007">최고의 시설</label>
				<label><input type="checkbox" name="keywords" value="CK0000008">장학금제도</label>
				<label><input type="checkbox" name="keywords" value="CK0000009">수영장</label>
				<label><input type="checkbox" name="keywords" value="CK0000010">도서관</label>
				<label><input type="checkbox" name="keywords" value="CK0000011">OB/OG</label>
				<label><input type="checkbox" name="keywords" value="CK0000012">부모회</label>
				<label><input type="checkbox" name="keywords" value="CK0000013">강의식 수업</label>
				<label><input type="checkbox" name="keywords" value="CK0000014">교복</label>
				<label><input type="checkbox" name="keywords" value="CK0000015">스포츠</label>
				<label><input type="checkbox" name="keywords" value="CK0000016">음악</label>
				<label><input type="checkbox" name="keywords" value="CK0000017">미술</label>
				<label><input type="checkbox" name="keywords" value="CK0000018">과학</label>
				<label><input type="checkbox" name="keywords" value="CK0000019">IT</label>
				<label><input type="checkbox" name="keywords" value="CK0000020">예체능</label>
				<label><input type="checkbox" name="keywords" value="CK0000021">식당, 카페테리아</label>
				<label><input type="checkbox" name="keywords" value="CK0000022">해외연수</label>
				<label><input type="checkbox" name="keywords" value="CK0000023">자연환경</label>
				<label><input type="checkbox" name="keywords" value="CK0000024">학교상품</label>
				<label><input type="checkbox" name="keywords" value="CK0000025">표창제도</label>
			</div>
			<input type="hidden" name="srch_keywords" value="">
			<input id="submit_button" type="button" value="검색" onclick="doSearch()">
    	</form>
    </div>
</div>

<section class="main_contents">
<div class="pr_pickup">
	<div class="pr_inner">
		<h4>PICK UP SCHOOL</h4>
		<div class="pickup_wrap clearfix">
			<a href="<%=request.getContextPath() %>/pickupschool/toshimagaoka.jsp" class="pickup">
				<div class="pickup_img"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/img_pickup_01.jpg" width="449" height="350" alt=""></div>
				<div class="pickup_desc">
					<h5>한국여자재단<br>중학교, 고등학교</h5>
					<dl>
						<dt>한국여자재단에서는 창립 이래의 건학 정신을 계승해, 사람으로서의 올바른 길과 배려의 마음을 소중히하고, 노력을 쌓아 재능을 늘리는 교육을 목표로 왔습니다.</dt>
						<dd>한국여자재단에서는 학생들의 자주적인 학생회 활동이나 학교 행사가 한창입니다. 문화계, 체육계 따라 48 클럽이 옛날부터 모든 학생들이 참가하고 고3까지 활동을 계속하고 있습니다. 다양한 분야에서 학생 개개인이 가지고있는 재능을 발견하고 감성 풍부한 성장하고 있습니다.</dd>
					</dl>
					<p class="link_pickup">상세보기</p>
				</div>
			</a>
			<a href="<%=request.getContextPath() %>/pickupschool/seijo.jsp" class="pickup">
				<div class="pickup_img"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/img_pickup_02.jpg" width="449" height="350" alt=""></div>
				<div class="pickup_desc">
					<h5>서울중학교, 서울고등학</h5>
					<dl>
						<dt>창립이후부터 전해오는 <br>'사회에 유능한 인재를 육성한다'느 ㄴ정신</dt>
						<dd>학교는 널리 학문과 지식을 흡수하는 중요한 장소 임과 동시에 많은 교사 나 친구와의 관계 속에서 지식을 닦고 클럽 활동, 학생회 활동이나 학교 행사 등에 적극적으로 대처하고, 밝고 느긋 한 학교 생활을 통해 인간으로 성장을 도모하고, 매사에 실패를 두려워하지 않고 도전하고 향후 다양한 사회의 변화에 유연하게 대응할 수있는 정신을 기르는 장소로 생각하고 있습니다.</dd>
					</dl>
					<p class="link_pickup">상세보기</p>
				</div>
			</a>
		</div>
	</div>
</div>
<div id="guardianArea" class="pr_guardian">
	<div class="pr_inner">
		<p class="go_top"><a href="#top"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/pagetop.png" alt="PAGE TOP" width="82" height="12"></a></p>
		<h4>전체 블로그<span class="link_blog"><a href="<%=request.getContextPath() %>/common/blog_new/blog.do?type=1">전체 블로그 목록</a></span>
				   <span class="btn_myblog"><a href="<%=request.getContextPath() %>/common/blog_new/blog.do?type=1"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/btn_myblog.png" alt="블로그 작성"></a></span></h4>
		<div class="guardian_wrap">
			<c:forEach items="${blog_list }" var="blog" varStatus="status">
				<div class="guardian <c:out value='${blog.gender }'/>" onclick="doBlogMove(<c:out value='${blog.idx}'/>)">
					<h5 class="title"><span><c:out value='${blog.title }'/></span></h5>
					<p class="desc"><c:out value='${blog.contents }'/></p>
					<p class="name">보호자(자녀:<c:out value='${blog.current_grade }'/>)</p>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<div id="studentArea" class="pr_student">
	<div class="pr_inner">
		<p class="go_top"><a href="#top"><img src="images/locale/ja/user/pages/top/pagetop.png" alt="PAGE TOP" width="82" height="12"></a></p>
		<h4>My School를<span class="pr_student_cp">보호자, 학생</span>이 사용하면...</h4>
		<dl class="pr_student_01">
			<dt>학생과 학교<br>의 매칭!</dt>
			<dd>당신의 프로필을 보고, <br>학교로부터 스카우트가 올지도 <br>모릅니다.</dd>
		</dl>
		<dl class="pr_student_02">
			<dt>당신에 딱 맞는<br> 학교를 안내</dt>
			<dd>등록한 성적이나<br>부활동 정보, 취미, 진로 상담으로 부터<br> 10개의 학교를 추천합니다.</dd>
		</dl>
		<dl class="pr_student_03">
			<dt>스케줄로<br> 이벤트 관리</dt>
			<dd>자신의 스케줄로부터 <br>학교의 이벤트까지 <br> 모든 것을 관리할 수 있습니다.</dd>
		</dl>
		<dl class="pr_student_04">
			<dt>마음에 드는 학교의 <br>인기도를 알수 있습니다.</dt>
			<dd>마음에 드는 학교의 관심도, <br>지원율, 이벤트의 참가율을<br> 을 알수 있습니다.</dd>
		</dl>
		<dl class="pr_student_05">
			<dt>희망하는 학교와<br> 직접 연락을 취할 수 있습니다.</dt>
			<dd>메시지 기능을 사용하여, <br>직접, 희망학교의 선생님에게 <br>질문 할 수 있습니다.</dd>
		</dl>
		<dl class="pr_student_06">
			<dt>빠른 <br>학교 정보를 알려드립니다.</dt>
			<dd>당신이 관심가지고 있는 <br>학교의 정보를<br> 가장 빠르게 전달해 드립니다.</dd>
		</dl>
	</div>
</div>
<div id="schoolArea" class="pr_school">
	<div class="pr_inner">
		<p class="go_top"><a href="#top"><img src="images/locale/ja/user/pages/top/pagetop.png" alt="PAGE TOP" width="82" height="12"></a></p>
		<h4>My School을<span class="pr_school_cp">학교</span>가 사용하면…</h4>
		<dl class="pr_school_01">
			<dt>학교에 맞는 학생을 <br>찾을 수 있습니다.</dt>
			<dd>학생정보로 부터<br> 본학교와 맞는 학생을 <br> 찾는 것이 가능합니다.</dd>
		</dl>
		<dl class="pr_school_02">
			<dt>학교정보의 <br>등록, 관리</dt>
			<dd>고등학교, 대학교, 전문학교 등과 같은<br> 복수의 학교정보의 <br>등록도 가능합니다.</dd>
		</dl>
		<dl class="pr_school_03">
			<dt>학교의 이벤트<br> 스케줄을 관리</dt>
			<dd>오픈 캠퍼스나<br>학교설명회 등과 같은 이벤트<br> 스케줄을 관리</dd>
		</dl>
		<dl class="pr_school_04">
			<dt>학생의 활동상황을<br> 실시간으로 관리, 확인</dt>
			<dd>학생의 학교 이벤트 참가율, <br>응모율, 진학율과 같은 정보를 실시간<br>으로 관리, 확인할 수 있습니다.</dd>
		</dl>
		<dl class="pr_school_05">
			<dt>학생과<br> 직접 연락</dt>
			<dd>메시지 기능을 사용하여, <br>학교에 맞는 학생과 직접<br> 상담을 할 수 있습니다.</dd>
		</dl>
		<dl class="pr_school_06">
			<dt>가장 빨리, <br>학교정보를 알려 드립니다.</dt>
			<dd>학생에게 학교의 정보를 <br>가장 빨리 전해 드립니다.</dd>
		</dl>
		<dl class="pr_school_recruitment">
			<dt>[학교모집]</dt>
			<dd>교육방침과 교육환경을 소개하시지 않겠습니까? 지금이라면<span>무료</span>로 등록하실 수 있습니다!</dd>
		</dl>
	</div>
</div>
<div id="newsArea" class="pr_news">
	<div class="pr_inner">
		<p class="go_top"><a href="#top"><img src="images/locale/ja/user/pages/top/pagetop.png" alt="PAGE TOP" width="82" height="12"></a></p>
		<h4>NEWS</h4>
		<div class="news_wrap">
		
			<div class="news">
				<div class="news_body">
					<c:forEach items="${news_list }" var="news" varStatus="status">
						<dl>
							<dt>
								<c:if test="${news.is_new == 0 }">
									<span class="new_news">[NEW!]</span>
								</c:if>
								<c:out value='${news.upd_date_str }'/>
								<span class="category">
								<c:choose>
									<c:when test="${news.news_type == 1 }">
										소식
									</c:when>
									<c:when test="${news.news_type == 2 }">
										이벤트
									</c:when>
									<c:when test="${news.news_type == 3 }">
										뉴스소식
									</c:when>
									<c:when test="${news.news_type == 4 }">
										미디어
									</c:when>
									<c:otherwise>
										기타
									</c:otherwise>
								</c:choose>
								</span>
							</dt>
							<dd><c:out value='${news.news_contents }'/></dd>
						</dl>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
</section>

<div id="dialog-error" title="에러 메시지">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>에러 메시지</p>
</div>

<footer>
   	<jsp:include page="footerForIndex.jsp" />
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
	<p class="validateTips">이메일주소와 비밀번호를 입력해 주세요.</p>
	<form id="register" method="post">
	<fieldset>
		<label for="name">이메일주소</label>
		<input type="text" name="user_email" id="user_email" class="text ui-widget-content ui-corner-all" />
		<label for="password">비밀번호</label>
		<input type="password" name="user_pwd" id="user_pwd" value="" class="text ui-widget-content ui-corner-all" />
		<label for="facebook"></label>
	</fieldset>
	</form>
	<div style="text-align: center;">
		<!-- <fb:login-button scope="public_profile,email" onlogin="checkLoginState();" /> -->
		<!-- <a href="javascript:doLogin($('#user_email'), $('#user_pwd'))">로그인</a> -->
		<a href="javascript:doLogin($('#user_email'), $('#user_pwd'))"><img src="images/common/login_icon.png" /></a>
	</div>
	<div style="margin-top: 20px; text-align: center;">
		<table width="100%">
			<tr>
				<td>
					<!-- <a href="javascript:checkLoginState();">Facebook</a> -->
					<a href="javascript:checkLoginState();"><img src="images/common/fb_icon.png" /></a>
				</td>
				<!-- <td>Twitter</td>
				<td>Google</td> -->
			</tr>
			<tr>
				<td>
				    &nbsp;
				</td>
			</tr>
		</table>
	</div>
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
</body>
</html>
