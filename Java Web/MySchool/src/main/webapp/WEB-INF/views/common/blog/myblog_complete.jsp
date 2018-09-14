<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>전체 블로그 | My School | 학생과 학교의 최고의 매칭 시스템</title>
<meta name="description" content="학생들에게 적합한 학교 정보 제공! 학교로부터 스카우트된다! 보이는 학생, 보이는 학교 정보! 학교는 학교 행사 참여율 응모 율 진학률 등이 사실적으로 관리, 확인할 수있다.">
<meta name="keywords" content="보이는 학생, 보이는 학교 데뷔 스토리 학교 매칭, student, school, 학교, 학교, 진로 상담, 학교 정보, 학생 정보, 진로, 진학, 대학 예비 학교, 유학, 오픈 캠퍼스, 이벤트, 입시 담당자 고민, 상담">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myblog.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
		
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easydropdown.js"></script>

<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/dataTables.bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/sb-admin-2.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<style>
	body { background-color: #fff; position: relative;}
</style>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<%-- <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script> --%>
<script src="<%=request.getContextPath() %>/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/sb-admin-2.js"></script>

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
	
	$( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 350,
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

function doModify() {
	var msg = "수정";
	$( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>" + msg + "페이지로 이동할까요?");
	$( "#dialog-confirm" ).dialog( "open" );
}

</script>
</head>
<body>

<jsp:include page="../header.jsp" flush="false">
	<jsp:param name="blog" value="yes"/>
</jsp:include>

<form action="" method="POST" name="f1">
<input type="hidden" name="mode" value="modify"> 
<input type="hidden" name="idx" value="<c:out value='${idx }'/>"> 
</form>

<div class="contents centerfix">
	<div id="breadCrumb">
		<ul id="bread">
			<li>Blog</li>
			<li>|</li>
			<li><a href="<%=request.getContextPath()%>/user/myprofile/stdBasicView.do?student_id=<c:out value='${login_user.user_id}'/>">My School</a></li>
		</ul>
	</div>
	<div class="contents_inner">
		<div class="contents_left">
			<div class="guardian_area">
				<p class="guardian_logo"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/myblog/icon_guardian_02.gif" width="142" height="142" alt=""></p>
				<p class="guardian_name"><c:out value='${student_entity.lname }'/> <c:out value='${student_entity.fname }'/><span>(<c:out value='${student_entity.current_grade }'/>)</span></p>
			</div>
			<p class="profile_edit"><a href="#">프로필 편집</a></p>
			<p class="blog_list"><a href="<%=request.getContextPath()%>/common/blog/myblog.do">내 블로그</a></p>
			<p class="blog_list"><a href="<%=request.getContextPath()%>/common/blog.do">보호자 소리</a></p>
		</div>
		<div class="contents_right blog_complete">
			<div class="blogTitleCell">
				<h4>블로그 작성</h4>
			</div>
			<div class="myblog_wrap">
				<div class="listBlogBlock">
					<div class="listBlogTitle">
						<dl>
							<dt>작성이 완료 되었습니다. 반영되기까지는 시간이 소요됩니다.</dt>
						</dl>
					</div>
					<ul class="utilityLinks detail">
						<li><a href="javascript:doModify()">작성한 블로그로</a></li>
						<li><a href="<%=request.getContextPath()%>/common/blog/myblog.do">내 블로그</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="dialog-error" title="에러 메시지"></div>
<div id="dialog-confirm" title="확인 메시지"></div>
<div id="dialog-logout" title="확인 메시지">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span></p>
</div>

<footer>
	<div class="bottom_contents">
		<div class="bottomInner">
			<div class="navigation_area">
				<p>
				   회사개요&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="privacy_policy.jsp" target="_blank">개인보호방침에 대해서</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="rules.jsp" target="_blank">이용약관</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="mailto:info@My School.com?subject=상담문의">상담문의</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="press.jsp" target="_blank">기사</a>				   
				</p>
				<p class="copyright">Copyright &copy; 2014, Inc. All rights reserved.</p>
		       </div>
		    <div class="logo_area">
		    	<p><img src="<%=request.getContextPath() %>/images/common/company_logo.png" alt="" width="212" height="70"></p>
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

</body>
</html>