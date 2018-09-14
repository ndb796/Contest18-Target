<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.entity.base.UserEntity" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
if(request.getAttribute("token_key") == null) {
	Token.set(request);
}

UserEntity userEntity = (UserEntity)session.getAttribute("loginOK");

if (userEntity != null) {
	System.out.println("You are already logined.");
	response.sendRedirect("index.jsp");
} else {
%>
<!DOCTYPE html>
<html>
<head>
<title>Facebook Login JavaScript Example</title>
<meta charset="UTF-8">
<style>

</style>
</head>
<body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js" charset="utf-8"></script>  
<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      connected();
    } else if (response.status === 'not_authorized') {
      // The person is logged into Facebook, but not your app.
      // document.getElementById('status').innerHTML = 'Please log into this app.';
    } else {
      // The person is not logged into Facebook, so we're not sure if
      // they are logged into this app or not.
      // document.getElementById('status').innerHTML = 'Please log into Facebook.';
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
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

  // Now that we've initialized the JavaScript SDK, we call 
  // FB.getLoginStatus().  This function gets the state of the
  // person visiting this page and can return one of three states to
  // the callback you provide.  They can be:
  //
  // 1. Logged into your app ('connected')
  // 2. Logged into Facebook, but not your app ('not_authorized')
  // 3. Not logged into Facebook and can't tell if they are logged into
  //    your app or not.
  //
  // These three cases are handled in the callback function.

  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
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
      f1.submit();
    });
  }
  
  function doLogin() {
	  var f1 = document.getElementById("web_frm");
	  if (f1.email.value == "") {
		  alert("Please, enter your e-mail address.");
		  f1.email.focus();
		  return;
	  }
	  if (f1.pwd.value == "") {
		  alert("Please, enter your password.");
		  f1.pwd.focus();
		  return;
	  }
	  
	  f1.action = "/Openapi/login/1.html";
	  f1.submit();
  }
</script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->
<form id="web_frm" name="web_frm" method="post">
<table style="">
	<tr>
		<td style="text-align: right;">E-mail : </td>
		<td><input type="text" id="email" name="email" style="width: 200px; height: 30px; font-size: 14px;" /></td>
	</tr>
	<tr style="height: 30px;">
		<td style="text-align: right;">Password : </td>
		<td><input type="password" id="pwd" name="pwd" style="width: 200px; height: 30px; font-size: 14px;" /></td>
	</tr>
	<tr style="height: 30px;">
		<td colspan="2" style="text-align: center;">
			<input type="button" value="Login" style="width:100px;height:100px; font-size: 50px;" onclick="doLogin()" />&nbsp;
			<input type="button" value="Join" style="width:100px;height:100px; font-size: 50px;" />
		</td>
	</tr>	
	<tr style="height: 30px;">
		<td colspan="2" style="text-align: center;">
			<fb:login-button scope="public_profile,email" onlogin="checkLoginState();" />
		</td>
	</tr>
</table>
</form>

<br/>
<br/>


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
<% } %>