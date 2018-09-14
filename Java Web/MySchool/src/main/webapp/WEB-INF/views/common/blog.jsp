<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.entity.base.UserEntity" %>
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
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/start/jquery-ui-1.10.4.custom.css" />
		
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easydropdown.js"></script>

<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/dataTables.bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/sb-admin-2.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/blog.css" />

<%-- <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script> --%>
<script src="<%=request.getContextPath() %>/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/sb-admin-2.js"></script>

<style>
	body { background-color: #fcfbd0; position: relative;}
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

<script src="<%=request.getContextPath() %>/js/functions.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var table = $('#dataTables-list').dataTable({
    	"lengthChange": false,
    	"searching": false,
    	"stateSave": true,
    	fnDrawCallback: function() {
    	    $("#selector thead").remove();
    	  },
    	"columnDefs": [
                {"targets": [ 0 ],
                 "searchable": false,
                 "sortable": false}
       ]
    });
});

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
	   	   frm.action = "<%=request.getContextPath() %>/logoutProc.do";
	   	   frm.submit();
	      },
	      "No": function() {
	        $( this ).dialog( "close" );
	      }
	    }
	  });
	
	$("#logout").button({
	    icons: {
	        primary: "ui-icon-unlocked"
	    }
	}).click(function() {
		//dialog_configm_status = "logout";
		$( "#dialog-logout" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>로그아웃 하시겠습니까?");
	    $( "#dialog-logout" ).dialog( "open" );
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
		var f1 = document.getElementById("register");
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

</script>
</head>
<body>

<div class="headerwrap01">
    <div class="header_navigation_wrap">
    	<div class="header_navigation">
    			<h1>
    				<a href="<%=request.getContextPath() %>/index.do">
    					<%-- <img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/pickup/main_logo_w.png" alt="My School" width="309" height="34"> --%>
    					<img src="<%=request.getContextPath() %>/images/common/logo.gif" alt="logo" width="256" height="41" border="0"></a>
    			</h1>
    	
    	
    		    <p class="select_language">
    	<!--	    
    	    <select name="" size="" class="dropdown">
    	    	<option value="1" class="label">COUNTRY/LANGUAGE</option>
    	        <option value="2">Japan(日本語)</option>
    	        <option value="3">America(English)</option>
    	        <option value="4">South Korea(韓국어)</option>
    	    </select>
    	-->
    		</p>
		<c:choose>
		<c:when test="${is_member == 'yes' }">
			<span style="float: right; margin-top: 25px;">
				<font color="white">안녕하세요, <%=((UserEntity)session.getAttribute("login_user")).getEmail() %> 님</font>
				<button id="logout" style="width:110px; height: 20px; font-size: 10px;">로그아웃</button>
			</span>
		</c:when>
		<c:otherwise>    		
   			<p class="loginbtn">
   				<a href="javascript:openLogin();">
   				<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/btn_login.png" alt="login" width="83" height="23"></a>
   			</p>
   			<p class="register_btn">
   				<a href="<%=request.getContextPath() %>/user/joinType.do">
   				<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/btn_jp.png" alt="신규등록" width="83" height="23"></a>
   			</p>
    	    <p class="fb">
    	    	<a href="https://www.facebook.com/pages/My School/611518178947356?ref=hl" target="_blank">
    		        	<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/pickup/icon_fb_w.png" alt="facebook" width="42" height="42" border="0"></a>
    	    </p>
   			<p class="tw">
   				<a href="https://twitter.com/My School?original_referer=http%3A%2F%2Fukaruresume.com%2F&profile_id=2267427624&tw_i=420011764361621504&tw_p=embeddedtimeline&tw_w=417488098335797248" target="_blank">
   				<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/pickup/icon_tw_w.png" alt="twitter" width="42" height="42" border="0"></a>
   	        </p>
   	    </c:otherwise>
		</c:choose>
    	 </div>
    </div>
</div>

<div class="contents centerfix">
	<div id="breadCrumb">
		<ul id="bread" class="clearfix">
			<c:if test="${is_member == 'yes' }">
			<li><!-- <a href="/">TOP</a> -->
				<span style="">
					<font color="white">
						<a href="<%=request.getContextPath()%>/common/blog/myblog.do">내 블로그</a>
					</font>
				</span><span>&gt;</span></li>
			<li>전체 블로그 목록</li>
			</c:if>
		</ul>
	</div>
	<h2 class="contents_title">전체 블로그</h2>
	<div class="contents_inner clearfix">
		<div class="contents_left">
			<div class="guardian_wrap">
				<table class="table table-nowrap" id="dataTables-list">
                    <thead>
                    	<tr>
                    		<th></th>
                    	</tr>
		            </thead>
                    <tbody>
                    	<c:forEach var="blog" items="${blog_list }">
                     	<tr style="border-width: 0px;">
	                     	<td style="border-width: 0px;">
	                        <div class="guardian <c:out value='${blog.gender }'/>">
								<h3 class="title"><c:out value='${blog.title }'/><span><c:out value='${blog.upd_date_str }'/></span></h3>
								<p class="desc"><c:out value='${blog.contents }'/></p>
								<p class="name">보호자 (자녀:<c:out value='${blog.current_grade }'/>)</p>
							</div>
							</td>
						</tr>
                        </c:forEach>
                    </tbody>
                </table>
			</div>
		</div>
		<c:choose>
		<c:when test="${is_member == 'yes' }">
		<div class="contents_right">
			<p class="btn_blog"><a href="<%=request.getContextPath()%>/common/blog/myblog_enter.do"><img src="<%=request.getContextPath()%>/images/locale/ja/user/pages/myblog/btn_write.png" width="250" height="130" alt="블로그 작성 블로그 작성하기 위해서는 등록(무료)이 필요합니다."></a></p>
		</div>
		</c:when>
		<c:otherwise>
		<div class="contents_right">
			<p class="btn_blog"><a href="#"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/blog/btn_write.png" width="250" height="130" alt="블로그 작성 블로그 작성하기 위해서는 등록(무료)이 필요합니다."></a></p>
			<p class="new_register"><a href="<%=request.getContextPath() %>/user/joinType.do">신규등록</a></p>
		</div>
		<div id="glayLayer"></div>
		<div id="overLayer">
			<p class="btn_close"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/blog/btn_close.png" width="29" height="29" alt=""></p>
			<p class="title">블로그 작성을 위해서는 회원등록이 필요합니다.。</p>
			<p class="desc">My School의 등록은 무료입니다.</p>
			<p class="new_register"><a href="<%=request.getContextPath() %>/user/joinType.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/blog/btn_new_register.png" width="258" height="57" alt="신규등록"></a></p>
			<p class="login">이미 등록된 분은 이쪽에서 <a href="javascript:openLogin();">로그인</a>해 주세요.</p>
		</div>
		</c:otherwise>
		</c:choose>
	</div>
</div>

<div id="dialog-error" title="에러 메시지"></div>
<div id="dialog-confirm" title="확인 메시지"></div>
<div id="dialog-logout" title="확인 메시지"></div>

<form name="logoutFrm" method="post">
</form>	

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

<footer>
	<div class="bottom_contents">
		<div class="bottomInner">
			<div class="navigation_area">
				<p>
				   <!--<a href="">プラン</a>&nbsp;&nbsp;l&nbsp;&nbsp;-->
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

</body>
</html>