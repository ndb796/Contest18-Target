<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%
if(request.getAttribute("token_key") == null) {
	Token.set(request);
}

String resultCode = request.getParameter("resultCode");
String resultMsg = "";
if (resultCode != null) {
	resultMsg = ResultCode.GET_MESSAGE_MAP().get(resultCode);
}

String joinType = request.getParameter("joinType");
String memberType = request.getParameter("memberType");

if (joinType == null || joinType.isEmpty()) {
	joinType = "4";
	memberType = "1";
}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>로그인|My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/login_form.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script>
window.onload = function () {
	var resultMsg = "<%=resultMsg%>";
	if (resultMsg != null && resultMsg.length > 0) {
		$( "#dialog-error" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>" + resultMsg);
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
	       "Close": function() {
	         $( this ).dialog( "close" );
	       }
	    }
	});
});

function checkRegexp( o, regexp, n ) {
    if ( !( regexp.test( o.value ) ) ) {
      return false;
    } else {
      return true;
    }
}
  var subcnt = 0;
function doSubmit() {
	var email = document.getElementById("email");
	var pwd = document.getElementById("pwd");
	var repwd = document.getElementById("repwd");
	
	var errorMsg = ""; 
	if (email.value == "") {
		errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>이메일을 입력해 주세요.</p>";
	}
	
	if (pwd.value == "") {
		errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>비밀번호을 입력해 주세요.</p>";
	}
	
	if (pwd.value != repwd.value) {
		errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>비밀번호가 일치하지 않습니다.</p>";
	}
	
	if (!checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. test@test.com" )) {
		errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>이메일이 정확하지 않습니다.</p>";
	}

	if (!checkRegexp( pwd,  /^[A-Za-z+]{8,16}$/, "eg. PasswOrd" )) {
		errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>비밀번호는 8~16글자 내의 영대소문자만 가능합니다.</p>";
	}
	
	if (errorMsg != "") {
		$( "#dialog-error" ).html(errorMsg);
		$( "#dialog-error" ).dialog( "open" );
		return;
	} else {
		var f1 = document.getElementById("frm");
		f1.action = "<%=request.getContextPath() %>/user/addUser.do";			
		console.log("<%=request.getContextPath() %>/user/addUser.do" + subcnt++);
		f1.submit();
	}
}
</script>

</head>
<body>
<div id="dialog-error" title="Error dialog">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>에러 메시지</p>
</div>

<div class="header_cover">
<header class="header_menu">
	<div class="leftarea">
		<span class="btn_header01"><a href="<%=request.getContextPath() %>/index.do"><img src="<%=request.getContextPath() %>/images/common/logo.gif" alt="logo" width="256" height="41" border="0"></a></span>
	</div>
</header>	
</div>

<section class="main_contents">
	<div class="topic">
		<h1 class="mail_register">
        	<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/login_form/icon_mail.png" alt="" width="155" height="155" border="0">
        </h1>
    </div>
</section>

<section class="form_area">
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="joinType" value="<%=joinType %>" >
<input type="hidden" name="memberType" value="<%=memberType %>" >
<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
<table>
	<tr>
    	<td class="mail_form">이메일</td>
        <td class="item"><input type="text" id="email" name="email" placeholder=" 이메일" class="form_fix1"></td>
    </tr>
    <tr>
    	<td>비밀번호</td>
        <td class="item"><input type="password" id="pwd" name="pwd" placeholder=" 비밀번호" class="form_fix2">
         <span class="notice">영대소문자 8~16자 이내로 입력해 주세요.</span></td>
    </tr>
    <tr>
    	<td>비밀번호확인</td>
        <td class="item"><input type="password" id="repwd" name="repwd" placeholder=" 비밀번호" class="form_fix2">
         <span class="notice">확인을 위해 한번 더 입력해 주세요.</span></td>
    </tr>
    <tr>
    	<td colspan="2" style="text-align: left;">
    		<p class="">※<a href="<%=request.getContextPath() %>/rules.jsp" target="_blank">이용규약</a>、<a href="<%=request.getContextPath() %>/privacy_policy.jsp" target="_blank">개인정보취급에 대하여</a>을 확인하고 동인한 후에 버튼을 클릭해 주세요.</p>
    	</td>
    </tr>
</table>
</form>
<p class="register_btn"><a href="javascript:doSubmit();"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/login_form/btn_form.png" alt="" height="57" width="458" border="0"></a></p>
</section>
	
<footer>
	<jsp:include page="../common/footer.jsp">
		<jsp:param value="no" name="logoutPage"/>
	</jsp:include>
</footer>
</body>
</html>
