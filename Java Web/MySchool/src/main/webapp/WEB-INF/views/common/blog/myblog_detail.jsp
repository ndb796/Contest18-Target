<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>전체 블로그 | My School | 학생과 학교의 최고의 매칭 시스템</title>
<meta name="description" content="학생들에게 적합한 학교 정보 제공! 학교로부터 스카우트된다! 보이는 학생, 보이는 학교 정보! 학교는 학교 행사 참여율 응모 율 진학률 등이 사실적으로 관리, 확인할 수있다.">
<meta name="keywords" content="보이는 학생, 보이는 학교 데뷔 스토리 학교 매칭, student, school, 학교, 학교, 진로 상담, 학교 정보, 학생 정보, 진로, 진학, 대학 예비 학교, 유학, 오픈 캠퍼스, 이벤트, 입시 담당자 고민, 상담">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/easydropdown.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myblog.css" />

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/start/jquery-ui-1.10.4.custom.css" />
<!-- <link rel="stylesheet" href="css/cupertino/jquery-ui.min.css" /> -->
		
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easydropdown.js"></script>
<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
<script src="<%=request.getContextPath() %>/js/googlemap.js"></script>

<style>
	body { font-size: 80%; background-color: #fff; position: relative;}
	/*label, input { display:block; }*/
	input.text { margin-bottom:12px; width:95%; padding: .4em; }
	fieldset { padding:0; border:0; margin-top:25px; }
	h1 { font-size: 1.2em; margin: .6em 0; }
	.ui-dialog .ui-state-error { padding: .3em; }
	.validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>

<script src="<%=request.getContextPath() %>/js/functions.js"></script>
<script type="text/javascript">
window.onload = function () {
	var resultMsg = "";
	var resultCode = "null";
	if (resultMsg != null && resultMsg.length > 0) {
		$( "#dialog-error" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>" + resultMsg);
		if (resultCode == "S10006" ) {
			$( "#dialog-error" ).dialog( "option", "title", "Information" );
		} 
		
		$( "#dialog-error" ).dialog( "open" );
	}
};

$(function() {
	$("#form .dropdown, .keyword").click(function(){
		if ($("#form .dropdown, .keyword").hasClass("open")) {
			$(".searchForm").addClass("open");
		} else {
			$(".searchForm").removeClass("open");			
		}
	});
	
	$( "#dialog-error" ).dialog({
		autoOpen: false,
	    resizable: false,
	    height: 250,
	    width: 350,
	    modal: true,
	    buttons: {
	       "Close": function() {
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
			Login: function() {
				doLogin($("#user_email"), $("#user_pwd"));
			}/* ,
			Join: function() {
				document.getElementById("user_email").value = "";
				document.getElementById("user_pwd").value = "";
				$(this).dialog("close");
				document.location.href = "join_type.jsp";
			} */
		},
		close: function() {
			document.getElementById("user_email").value = "";
			document.getElementById("user_pwd").value = "";
		}
	});
	
	$( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	    	  $( this ).dialog( "close" );
	    	  openLogin();
	      },
	      "No": function() {
	          $( this ).dialog( "close" );
	      }
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
	bValid = bValid && checkRegexp( objEmail, /^((([a-z]|\d|[!#$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "ex) test@My School.com" );
	bValid = bValid && checkNull( objPwd, "password" );
	
	if (bValid) {
		var f1 = document.getElementById("loginFrm");
		f1.action = "<%=request.getContextPath()%>/loginProc.do";
		f1.submit();
	}
}

function openLogin() {
	$("#dialog-form").dialog("open");
}

function doDetail(sb_id) {
	var f1 = document.getElementById("loginFrm");
	f1.sb_id.value = sb_id;
	
	$( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 30px 0;'></span>상세정보을 見るためには로그인が必要です。로그인しますか?<br>등록がまだの方は신규등록을 お願い致します。");
	$( "#dialog-confirm" ).dialog( "open" );
}

function doSearch() {
	var f1 = document.frm;
	
	var srch_school = f1.srch_school;
	var srch_loc = f1.srch_loc;
	var srch_cate = f1.srch_cate;
	var keywords = f1.keywords;
	
	var selKeywords = "";
	for (var i=0; i<keywords.length; i++) {
		if (keywords[i].checked) {
			selKeywords += keywords[i].value + ",";
		}
	}
	console.log(srch_loc);
	var errorMsg = "";
	
	if (srch_school.value == "" && srch_loc.value == "" && srch_cate.value == "" && selKeywords == "") {
	 errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>검색 조건을 하나이상 선택해 주세요.</p>";
	}
	
	if (errorMsg != "") {
	 	$( "#dialog-error" ).html(errorMsg);
	 	$( "#dialog-error" ).dialog( "open" );
	}  else {
		console.log(srch_school.value + "/" + srch_loc.value + "/" + srch_cate.value + "/" + keywords.length);
		f1.srch_keywords.value = selKeywords;
		f1.submit();
	}
}

function showMap(address) {
	var geocoder = new google.maps.Geocoder();
	var lat = 0.0;
	var lng = 0.0;
	geocoder.geocode( { 'address': address}, function(results, status) {
		
	      if (status == google.maps.GeocoderStatus.OK) {
	    	  lat = results[0].geometry.location.lat();
	    	  lng = results[0].geometry.location.lng();
	    	  
	    	  initialize(lat, lng, address);
	      } else {
	        alert("Geocode was not successful for the following reason: " + status);
	      }
	});
}
</script>
</head>
<body>

<div class="headerwrap01">
    <div class="centerfix">
    <div class="header_navigation">
		<h1>
			<a href="<%=request.getContextPath() %>/index.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/search/main_logo_w.png" alt="My School" width="256" height="28"></a></h1>


	    <!-- <p class="select_language">
        <select name="" size="" class="dropdown">
        	<option value="1" class="label">COUNTRY/LANGUAGE</option>
            <option value="2">Japan(日本語)</option>
            <option value="3">America(English)</option>
            <option value="4">South Korea(韓국어)</option>
        </select>
    	</p> -->
	
		<p class="home_btn">
			<a href="#">
			<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/myblog/btn_home.png" alt="HOME" width="83" height="23"></a>
		</p>
		<p class="logout_btn">
			<a href="#">
			<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/myblog/btn_logout.png" alt="LOGOUT" width="83" height="23"></a>
		</p>
        <!-- <p class="fb">
        	<a href="https://www.facebook.com/pages/My School/611518178947356?ref=hl" target="_blank">
	        	<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/icon_fb.png" alt="facebook" width="42" height="42" border="0"></a>
        </p>
		<p class="tw">
			<a href="https://twitter.com/My School?original_referer=http%3A%2F%2Fukaruresume.com%2F&profile_id=2267427624&tw_i=420011764361621504&tw_p=embeddedtimeline&tw_w=417488098335797248" target="_blank">
			<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/icon_tw.png" alt="twitter" width="42" height="42" border="0"></a>
          </p> -->
     </div>
     
    </div>
</div>

<div class="contents centerfix">
	<div id="breadCrumb">
		<ul id="bread">
			<li><a href="/">TOP</a><span>&gt;</span></li>
			<li><a href="/">전체 블로그 목록</a><span>&gt;</span></li>
			<li>내 블로그</li>
		</ul>
	</div>
	<div class="contents_inner">
		<div class="contents_left">
			<p class="btn_blog"><a href="#"><img src="/images/locale/ja/user/pages/myblog/btn_write.png" width="250" height="130" alt="블로그 작성"></a></p>
			<div class="guardian_area">
				<p class="guardian_logo"><img src="images/locale/ja/user/pages/myblog/icon_guardian_02.gif" width="142" height="142" alt=""></p>
				<p class="guardian_name">◯○ ◯○◯<span>(학생: 중학교3년 여자)</span></p>
			</div>
			<p class="profile_edit"><a href="#">프로필 수정</a></p>
			<p class="blog_list"><a href="#">전체 블로그 목록</a></p>
		</div>
		<div class="contents_right blog_detail">
			<div class="blogTitleCell">
				<p class="backList"><a href="#">＜내 블로그</a></p>
				<div class="pageNavigation">
					<ul>
						<li><a href="#">이전 표시</a></li>
						<li><a href="#">다음 표시</a></li>
					</ul>
				</div>
			</div>
			<div class="myblog_wrap">
				<div class="listBlogBlock">
					<div class="listBlogTitle">
						<dl>
							<dt>맞벌이 부부의 경력 활용<span><a href="#">수정</a></span></dt>
							<dd>2018.09.16</dd>
						</dl>
					</div>
					<p class="article">중학교/고등학교는 인생의 기초를 만들기의시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할수 있는 여유로운 환경이 있으면 좋겠다고 생각하고 학교을 찾았습니다. 설명회에서 학교가 가장 소중히하는 교육을 잘 알 수 있었고, 가능한 한 참여하여 학교의 방침에 공감 수 있는지 여부도 확인했습니다.</p>
				</div>
			</div>
			<ul class="utilityLinks detail">
				<li><a href="#">블로그 작성く</a></li>
				<li><a href="#">내 블로그</a></li>
			</ul>
		</div>
	</div>
</div>
<footer>
	<div class="bottom_contents">
		<div class="bottomInner">
			<div class="navigation_area">
				<p>
				   <a href="http://www.myschool.com" target="_blank">회사개요</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="privacy_policy.jsp" target="_blank">개인정보취급에 대하여</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="rules.jsp" target="_blank">이용규약</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="mailto:info@My School.com?subject=상담문의">상담문의</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="press.jsp" target="_blank">기사</a>				   
				</p>
				<p class="copyright">Copyright &copy; 2014, Inc. All rights reserved.</p>
		       </div>
		    <div class="logo_area">
		    	<p><a href="http://www.myschool.com/" target="_blank"><img src="<%=request.getContextPath() %>/images/common/company_logo.png" alt="My School" width="212" height="70"></a></p>
		    </div>
		</div>
	</div>
</footer>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-52579319-1', 'auto');
  ga('send', 'pageview');
</script>

<div id="dialog-confirm" title="Confirm dialog">
</div>

<div id="dialog-error" title="Error dialog">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>에러 메시지</p>
</div>
<div id="dialog-form" title="로그인">
	<p class="validateTips">이메일과 비밀번호를 입력해 주십시오.</p>
	<form id="loginFrm" method="post">
	<fieldset>
		<label for="name">이메일</label>
		<input type="text" name="user_email" id="user_email" class="text ui-widget-content ui-corner-all" />
		<label for="password">비밀번호</label>
		<input type="password" name="user_pwd" id="user_pwd" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
	</form>
</div>
</body>
</html>