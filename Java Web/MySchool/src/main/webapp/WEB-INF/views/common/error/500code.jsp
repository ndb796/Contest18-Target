<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%
String resultMsg = "<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>" + "申し訳ございません。もう少し後で利用してください。<br/><br/>";
resultMsg += "<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>同じ状態が続けると管理者までご連絡お願いいたします。(mailto://info@My School.com)";
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

<link rel="stylesheet" href="css/start/jquery-ui-1.10.4.custom.css" />
<!-- <link rel="stylesheet" href="css/cupertino/jquery-ui.min.css" /> -->
		
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>

<style>
	body { font-size: 80%; background-color: #fff;}
	label, input { display:block; }
	input.text { margin-bottom:12px; width:95%; padding: .4em; }
	fieldset { padding:0; border:0; margin-top:25px; }
	h1 { font-size: 1.2em; margin: .6em 0; }
	.ui-dialog .ui-state-error { padding: .3em; }
	.validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>

<script type="text/javascript">
window.onload = function () {
	var resultMsg = "<%=resultMsg%>";
	if (resultMsg != null && resultMsg.length > 0) {
		$( "#dialog-error" ).html(resultMsg);
		$( "#dialog-error" ).dialog( "open" );
	}
};

$(function() {
	$( "#dialog-error" ).dialog({
		autoOpen: false,
	    resizable: false,
	    height: 250,
	    width: 420,
	    modal: true,
	    buttons: {
	       "Close": function() {
	         $( this ).dialog( "close" );
	         location.href = "http://www.My School.com";
	       }
	    }
	});
});
</script>
</head>
<body>
<div id="dialog-error" title="Error dialog">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>error message</p>
</div>
</body>
</html>
