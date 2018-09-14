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
<!-- <link rel="stylesheet" href="css/cupertino/jquery-ui.min.css" /> -->
		
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
window.onload = function () {
	var resultMsg = "<c:out value='${resultMsg}' />'";
	if (resultMsg != null && resultMsg == "S10003") {
		$( "#delete_err_msg" ).html("<span>선택한 항목을 선택합니다.</span>");
		document.getElementById("delete_err_msg").style.visibility = "visible";
		setTimeout("doDisplayErrMsg(false)", 3000);
	}
};

$(function() {
	$( "#dialog-error" ).dialog({
		autoOpen: false,
		resizable: false,
		height: 350,
		width: 400,
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
			Login: function() {
				doLogin($("#user_email"), $("#user_pwd"));
			}
		}, */
		close: function() {
			document.getElementById("user_email").value = "";
			document.getElementById("user_pwd").value = "";
		}
	});
	
	var dialog_configm_status = "";
	  $( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 400,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	   	   var frm = document.f1;
	   	   if (dialog_configm_status == "logout") {
	   		frm.action = "<%=request.getContextPath() %>/logoutProc.do";
	   	   } else {
 	   	   	frm.action = "<%=request.getContextPath() %>/user/myprofile/stdBasicModForm.do";
	   	   }
	   	   
	   	   frm.submit();
	      },
	      "No": function() {
	        $( this ).dialog( "close" );
	      }
	    }
	  });
	  
    $("#modify").button({
		    icons: {
		        primary: "ui-icon-pencil"
		    }
	  }).click(function() {
		  dialog_configm_status = "modify";
		  $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>수정페이지로 이동하시겠습니까?");
		  $( "#dialog-confirm" ).dialog( "open" );
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
	$( "#dialog-logout" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>로그아웃 하시겠습니까?");
	$( "#dialog-logout" ).dialog( "open" );
}

function doDelete() {
	var frm = document.f1;
	frm.action = "<%=request.getContextPath()%>/common/blog_new/myblog_delete.do";
	var arrIdxs = document.getElementsByName("blog_idx");
	var idxs = "";
	for (var i=0; i<arrIdxs.length; i++) {
		if (arrIdxs[i].checked) {
			idxs += arrIdxs[i].value + ",";
		}
	}
	
	if (idxs.length > 0) {
		frm.idx.value = idxs;
		frm.submit();
	} else {
		//alert("Please, select a item to delete.");
		window.scrollTo(0, 0);
		document.getElementById("delete_err_msg").style.visibility = "visible";
		setTimeout("doDisplayErrMsg(false)", 3000);
	}
}

function doDisplayErrMsg(flg) {
	if (flg) {
		document.getElementById("delete_err_msg").style.visibility = "visible";
	} else {
		document.getElementById("delete_err_msg").style.visibility = "hidden";
	}
}

function doModify(idx) {
	var frm = document.f1;
	frm.action = "<%=request.getContextPath()%>/common/blog_new/myblog_modify.do";
	frm.idx.value = idx;
	
	frm.submit();
}
</script>
</head>
<body>

<form action="" method="POST" name="f1">
<input type="hidden" name="cmd" value="">
<input type="hidden" name="idx" value="">
</form>

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
		<p style="vertical-align:middle;padding-top: 25px;">
			<font color="white">안녕하세요, <%=((UserEntity)session.getAttribute("login_user")).getEmail() %> 님</font>&nbsp;&nbsp;&nbsp;
		</p>
		<p class="home_btn" style="vertical-align:middle;">
			<a href="<%=request.getContextPath()%>/myprofile.do">
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
			<li>TOP<span>&gt;</span></li>
			<li>전체 블로그 목록<span>&gt;</span></li>
			<li>내 블로그</li>
		</ul>
	</div>
	<div class="contents_inner">
		<div class="contents_left">
			<p class="btn_blog"><a href="<%=request.getContextPath()%>/common/blog_new/myblog_enter.do"><img src="<%=request.getContextPath()%>/images/locale/ja/user/pages/myblog/btn_write.png" width="250" height="130" alt="블로그 작성 블로그 작성하기 위해서는 등록(무료)이 필요합니다."></a></p>
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
				<p class="guardian_name"><c:out value="${student_entity.lname }"/> <c:out value="${student_entity.fname }"/><span>（<c:out value="${student_entity.current_grade }"/>）</span></p>
			</div>
			<p class="profile_edit"><a href="<%=request.getContextPath()%>/user/myprofile/stdBasicModForm.do?student_id=<c:out value='${login_user.user_id}'/>">프로필수정</a></p>
			<p class="blog_list"><a href="<%=request.getContextPath()%>/common/blog_new/blog.do?type=1">전체 블로그 목록</a></p>
		</div>
		<div class="contents_right">
			<div id="delete_err_msg" class="attention" style="visibility: hidden;"><span>삭제할 블로그를 선택해 주세요.</span></div>
			<div class="blogTitleCell">
				<h4>내 블로그</h4>
				<div class="pageNavigation">
					<ul>
						<li><c:out value='${disp_page }'/></li>
					</ul>
				</div>
			</div>
			<div class="myblog_wrap">
				<div class="listBlogBlock">
					<c:forEach var="blog" items="${blog_list }">
					<div class="listBlogTitle">
						<dl>
							<dt>
								<input name="blog_idx" id="blog_idx" type="checkbox" value="<c:out value='${blog.idx }'/>">&nbsp;<c:out value='${blog.title }'/>
								<span><a href="javascript:doModify(<c:out value='${blog.idx }'/>)">수정</a></span>
							</dt>
							<dd><c:out value='${blog.upd_date_str }'/></dd>
						</dl>
					</div>
					<p class="article"><c:out value='${blog.contents }'/></p>
					</c:forEach>
				</div>
			</div>
			<div class="formButtons">
				<ul>
					<li><input type="button" class="formBt01" value="체크한 블로그를 삭제" onclick="doDelete()"></li>
				</ul>
			</div>
			<ul class="utilityLinks">
				<li><a href="<%=request.getContextPath()%>/common/blog_new/myblog_enter.do">블로그 작성</a></li>
			</ul>
		</div>
	</div>
</div>

<jsp:include page="../footerForBlog.jsp" />

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-52579319-1', 'auto');
  ga('send', 'pageview');
</script>

<div id="dialog-error" title="에러 메시지"></div>
<div id="dialog-confirm" title="확인 메시지"></div>
<div id="dialog-logout" title="확인 메시지"></div>

<form name="logoutFrm" method="post">
</form>	

<div id="dialog-form" title="로그인">
	<p class="validateTips">이메일과 비밀번호를 입력해 주십시오.</p>
	<form id="loginFrm" method="post">
	<fieldset>
		<label for="name">이메일</label>
		<input type="text" name="user_email" id="user_email" class="text ui-widget-content ui-corner-all" />
		<label for="password">비밀번호</label>
		<input type="password" name="user_pwd" id="user_pwd" value="" class="text ui-widget-content ui-corner-all" />
		<label for="facebook"></label>
	</fieldset>
	</form>
	<div style="text-align: right;">
		<!-- <fb:login-button scope="public_profile,email" onlogin="checkLoginState();" /> -->
		<a href="javascript:doLogin($('#user_email'), $('#user_pwd'))">로그인</a>
	</div>
	<div style="margin-top: 20px; text-align: center;">
		<table width="100%">
			<tr>
				<td><a href="javascript:checkLoginState();">Facebook</a></td>
				<!-- <td>Twitter</td>
				<td>Google</td> -->
			</tr>
		</table>
	</div>
</div>

</body>
</html>