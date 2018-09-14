<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>전체 블로그 | My School | 학생과 학교의 최고의 매칭 시스템</title>
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

<%-- <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script> --%>
<script src="<%=request.getContextPath() %>/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/sb-admin-2.js"></script>

<script>
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
      
      $("#link").button({
		    icons: {
		        primary: "ui-icon-link"
		    }
	  }).click(function() {
		  $("#div_mypath").css("display", "block");
		  $("#div_list").css("display", "none");
	  });
      
      $("#list").button({
		    icons: {
		        primary: "ui-icon-document"
		    }
	  }).click(function() {
		  $("#div_list").css("display", "block");
		  $("#div_mypath").css("display", "none");
	  });
});		

function doDelete() {
	var frm = document.f1;
	frm.action = "<%=request.getContextPath()%>/common/blog/myblog_delete.do";
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
		alert("Please, select a item to delete.")
	}
	
}

function doModify(idx) {
	var frm = document.f1;
	frm.action = "<%=request.getContextPath()%>/common/blog/myblog_modify.do";
	frm.idx.value = idx;
	
	frm.submit();
}
</script>
</head>
<body>

<div class="headerwrap01">
    <div class="centerfix">
    <div class="header_navigation">
		<h1>
			<a href="/index.do"><img src="<%=request.getContextPath()%>/images/locale/ja/user/pages/search/main_logo_w.png" alt="My School" width="256" height="28"></a></h1>
	
		<p class="home_btn">
			<a href="#">
			<img src="<%=request.getContextPath()%>/images/locale/ja/user/pages/myblog/btn_home.png" alt="HOME" width="83" height="23"></a>
		</p>
		<p class="logout_btn">
			<a href="#">
			<img src="<%=request.getContextPath()%>/images/locale/ja/user/pages/myblog/btn_logout.png" alt="LOGOUT" width="83" height="23"></a>
		</p>
     </div>
     
    </div>
</div>

<form action="" method="POST" name="f1">
<input type="hidden" name="cmd" value="">
<input type="hidden" name="idx" value="">
</form>

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
			<p class="btn_blog"><a href="<%=request.getContextPath()%>/common/blog/myblog_enter.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/myblog/btn_write.png" width="250" height="130" alt="블로그 작성 블로그 작성하기 위해서는 등록(무료)이 필요합니다."></a></p>
			<div class="guardian_area">
				<p class="guardian_logo"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/myblog/icon_guardian_02.gif" width="142" height="142" alt=""></p>
				<p class="guardian_name"><c:out value='${student_entity.lname }'/> <c:out value='${student_entity.fname }'/><span>(<c:out value='${student_entity.current_grade }'/>)</span></p>
			</div>
			<p class="profile_edit"><a href="<%=request.getContextPath()%>/user/myprofile/stdBasicModForm.do?student_id=<c:out value='${login_user.user_id}'/>">프로필수정</a></p>
			<p class="blog_list"><a href="<%=request.getContextPath()%>/common/blog.do?type=1">전체 블로그 목록</a></p>
		</div>
		<div class="contents_right">
			<div class="myblog_wrap">
			<!-- <div class="attention"><span>삭제할 블로그를 선택해 주세요.</span></div> -->
			<table class="table table-nowrap" id="dataTables-list">
                 <thead>
                 	<tr>
                 		<th>내 블로그</th>
                 	</tr>
         		 </thead>
                 <tbody>
                 	<c:forEach var="blog" items="${blog_list }">
                  	<tr style="border-width: 0px;">
	                   	<td style="border-width: 0px;">
							<div class="listBlogBlock">
								<div class="listBlogTitle">
									<input name="blog_idx" id="blog_idx" type="checkbox" value="<c:out value='${blog.idx }'/>">&nbsp;<c:out value='${blog.title }'/>
									<span><a href="javascript:doModify(<c:out value='${blog.idx }'/>)">수정</a></span>
									<br/>
									<div style="float: right;"><c:out value='${blog.upd_date_str }'/></div>
								</div>
								<p class="article"><c:out value='${blog.contents }'/></p>
							</div>
						</td>
					</tr>
                    </c:forEach>
                 </tbody>
             </table>
             </div>
			 <div class="formButtons">
				<ul>
					<li><input type="button" class="formBt01" value="체크한 블로그를 삭제" onclick="doDelete()"></li>
				</ul>
			 </div>
			 <ul class="utilityLinks">
				<li><a href="<%=request.getContextPath()%>/common/blog/myblog_enter.do">블로그 작성</a></li>
			 </ul>
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

</body>
</html>
