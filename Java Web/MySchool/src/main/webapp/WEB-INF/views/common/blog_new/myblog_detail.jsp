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
		modal: true,
		buttons: {
			Login: function() {
				doLogin($("#user_email"), $("#user_pwd"));
			}
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
	    	  var frm = document.f1;
	    	  frm.action = "<%=request.getContextPath()%>/common/blog/myblog_modify.do";
	    	  frm.submit();
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
			<p class="btn_blog"><a href="#"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/myblog/btn_write.png" width="250" height="130" alt="블로그 작성"></a></p>
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
			<p class="profile_edit"><a href="<%=request.getContextPath()%>/user/myprofile/stdBasicModForm.do?student_id=<c:out value='${login_user.user_id}'/>">프로필수정</a></p>
			<p class="blog_list"><a href="<%=request.getContextPath()%>/common/blog_new/blog.do?type=1">전체 블로그 목록</a></p>
		</div>
		<div class="contents_right blog_detail">
			<div class="blogTitleCell">
				<%-- <p class="backList"><a href="<%=request.getContextPath()%>/common/blog_new/myblog.do">내 블로그</a></p> --%>
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
							<dt>맞벌이 부부 경력을 활용<span><a href="#">수정</a></span></dt>
							<dd>2018.09.16</dd>
						</dl>
					</div>
					<p class="article">중, 고등학교는 우리에게 있어서 기초 만들기의시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할수 있는 여유로운 환경이 있는 학교를 찾았습니다. 설명회에서는 학교가 가장 소중히하는 교육을 잘 알 수 있었고, 최대한 참여해서 학교의 방침에 공감 수 있는지 여부을 확인했습니다.</p>
				</div>
			</div>
			<ul class="utilityLinks detail">
				<li><a href="<%=request.getContextPath()%>/common/blog_new/myblog_enter.do">블로그 작성</a></li>
				<li><a href="<%=request.getContextPath()%>/common/blog_new/myblog.do">내 블로그</a></li>
			</ul>
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