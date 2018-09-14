<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.entity.base.UserEntity" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
UserEntity loginUserEntity = (UserEntity)session.getAttribute("login_user");
%>
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

<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easydropdown.js"></script>

<link href="<%=request.getContextPath() %>/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
	    width: 400,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	    	  $( this ).dialog( "close" );
	    	  doSubmit();
	      },
	      "No": function() {
	          $( this ).dialog( "close" );
	      }
	    }
	  });
	
	$( "#dialog-logout" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	       //jQuery.sFacebook.logout();
	   	   var frm = document.logoutFrm;
	   	   frm.action = "<%=request.getContextPath()%>/logoutProc.do";
	   	   frm.submit();
	      },
	      "No": function() {
	        $( this ).dialog( "close" );
	      }
	    }
	  });
});

function doLogout() {
	$( "#dialog-logout" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>로그아웃하시겠습니까?");
	$( "#dialog-logout" ).dialog( "open" );
}

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

function displayLeng( sz, id ) {
    var form = document.forms[0];
    var obj  = document.getElementById( id );

    if (form[id].value.length > sz) {
        if (event.keyCode != '8') { //백스페이스는 지우기작업시 바이트 체크하지 않기 위해서
        	$( "#dialog-error" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>" + sz + "까지 입력해 주세요.");
        	$( "#dialog-error" ).dialog( "open" );
        }
        form[id].value = form[id].value.substring(0, sz);
    }

    document.getElementById(id+'_bytes').innerHTML = "(" + form[id].value.length + "/100문자)";
}

function doConfirm() {
	var title = document.getElementById("title");
	var contents = document.getElementById("contents");

	var errorMsg = "";
	  
	if (title.value == "") {
		errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>제목을 입력해 주세요.</p>";
	}
	if (contents.value == "") {
		errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>내용을 입력해 주세요.</p>";
	}
	  
	if (errorMsg != "") {
		  $( "#dialog-error" ).html(errorMsg);
		  $( "#dialog-error" ).dialog( "open" );
	} else {
		/*   $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>이 정보로 등록 하시겠습니까?");
		  $( "#dialog-confirm" ).dialog( "open" ); */
		doSubmit();
	}
}

function doSubmit() {
	var frm = document.getElementById("f1");
	
	frm.action = "<%=request.getContextPath()%>/common/blog_new/myblog_confirm.do";
	frm.submit();
}
</script>
</head>
<body>

<div class="headerwrap01">
    <div class="centerfix">
    <div class="header_navigation">
		<h1>
			<%     String moveUrl = request.getContextPath();
				   if (loginUserEntity != null) { 
				   		if (loginUserEntity.getMem_type() == Constants.USER_MEM_STUDENT) { 
							moveUrl += "/user/myprofile/stdBasicView.do?student_id=" + loginUserEntity.getUser_id();
						} else { 
							moveUrl += "/customer/myprofile/schBasicView.do?school_id=" + loginUserEntity.getUser_id();
				 		}
				   } else { 
					   moveUrl += "/index.do";
				   } %>
			<a href="<%=moveUrl%>">
			<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/search/main_logo_w.png" alt="My School" width="256" height="28"></a></h1>

	    <!-- <p class="select_language">
        <select name="" size="" class="dropdown">
        	<option value="1" class="label">COUNTRY/LANGUAGE</option>
            <option value="2">Japan(日本語)</option>
            <option value="3">America(English)</option>
            <option value="4">South Korea(韓국어)</option>
        </select>
    	</p> -->
	
		<p class="home_btn">
			<a href="<%=request.getContextPath()%>/user/myprofile/stdBasicModForm.do?student_id=<c:out value='${login_user.user_id}'/>">
			<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/myblog/btn_home.png" alt="HOME" width="83" height="23"></a>
		</p>
		<p class="logout_btn">
			<a href="javascript:doLogout()">
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
			<div class="guardian_area">
				<p class="guardian_logo">
					<c:choose>
						<c:when test="${student_entity.gender == 'M'}">
							<img src="<%=request.getContextPath()%>/images/locale/ja/user/pages/myblog/icon_guardian_01.gif" width="142" height="142" alt="">
						</c:when>
						<c:when test="${student_entity.gender == 'F'}">
							<img src="<%=request.getContextPath()%>/images/locale/ja/user/pages/myblog/icon_guardian_02.gif" width="142" height="142" alt="">
						</c:when>
						<c:otherwise>
							<img src="<%=request.getContextPath()%>/images/locale/ja/user/pages/myblog/icon_guardian_03.gif" width="142" height="142" alt="">
						</c:otherwise>
					</c:choose>
				</p>
				<p class="guardian_name"><c:out value='${student_entity.lname }'/> <c:out value='${student_entity.fname }'/><span>(<c:out value='${student_entity.current_grade }'/>)</span></p>
			</div>
			<p class="profile_edit"><a href="<%=request.getContextPath()%>/user/myprofile/stdBasicModForm.do?student_id=${login_user.user_id}">프로필수정</a></p>
			<p class="blog_list"><a href="<%=request.getContextPath()%>/common/blog_new/myblog.do">내 블로그</a></p>
			<p class="blog_list"><a href="<%=request.getContextPath()%>/common/blog_new/blog.do?type=1">전체 블로그 목록</a></p>
		</div>
		<div class="contents_right blog_enter">
			<form action="" id="f1" method="post">
				<input type="hidden" name="mode" value="${mode }">
				<input type="hidden" name="idx" value="${blog_entity.idx }">
				<div class="blogTitleCell">
					<h4>블로그 작성<span>(<span class="notes">*</span> 항목은 필수입니다)</span></h4>
				</div>
				<div class="myblog_wrap">
					<div class="BlogEnterBlock">
						<dl class="BlogEnterTitle">
							<dt>제목<span class="notes">＊</span> <span id='title_bytes' style="width: 80px;">(${title_len }/50문자)</span></dt>
							<dd style="height: 57px;"><input type="text" name="title" id="title" value="${title }" onkeyup='displayLeng(50,"title");'></dd>
						</dl>
						<dl class="BlogEnterDesc">
							<dt>본문<span class="notes">＊</span><span id='contents_bytes' style="width: 80px;">(${contents_len }/100문자)</span></dt>
							<dd style="height: 281px;"><textarea cols="30" name="contents" id="contents" onkeyup='displayLeng(100,"contents");' >${contents }</textarea></dd>
						</dl>
					</div>
					<p class="notice"><a href="<%=request.getContextPath() %>/rules.jsp" target="_blank">이용규약</a> 및 <a href="<%=request.getContextPath() %>/privacy_policy.jsp" target="_blank">개인정보취급에 대하여</a>을 확인, 동의 한 후에 작성해 주세요.</p>
					<div class="formButtons">
						<ul>
							<li><input type="button" class="formBt01" value="입력내용을 확인" onclick="doConfirm()"></li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div id="dialog-error" title="에러 메시지"></div>
<div id="dialog-confirm" title="확인 메시지"></div>
<div id="dialog-logout" title="확인 메시지"></div>

<form name="logoutFrm" method="post">
</form>

<jsp:include page="../footerForBlog.jsp" />

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-52579319-1', 'auto');
  ga('send', 'pageview');
</script>

</body>
</html>