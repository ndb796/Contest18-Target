<%@ page info="Hello SpringDM on Web" %>
<%@ page import="java.util.*"%>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String resultCode = request.getParameter("resultCode");
String resultMsg = "";
if (resultCode != null) {
	resultMsg = ResultCode.GET_MESSAGE_MAP().get(resultCode);
}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<link rel="stylesheet" href="css/black-tie/jquery-ui-1.10.4.custom.css" />
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/jquery-ui-1.10.4.custom.js"></script>
	
	<style>
	 body { font-size: 62.5%; }
	 label, input { display:block; }
	 input.text { margin-bottom:12px; width:95%; padding: .4em; }
	 fieldset { padding:0; border:0; margin-top:25px; }
	 h1 { font-size: 1.2em; margin: .6em 0; }
	 div#users-contain { width: 350px; margin: 20px 0; }
	 div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
	 div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
	 .ui-dialog .ui-state-error { padding: .3em; }
	 .validateTips { border: 1px solid transparent; padding: 0.3em; }
	</style>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
	
	<!-- <style type="text/css">
	body .style a{
		color:gray;
		font-family:sans-serif;
		font-size:13px;
		text-decoration:none;
	}
    .menu{
      display:none;
    }
	</style> -->
	
	<script>
	  $(function() {
	    var user_name = $( "#user_name" ),
	        email = $( "#email" ),
	        pwd = $( "#pwd" ),
	        user_id = $( "#user_id" ),
	        allFields = $( [] ).add( user_name ).add( email ).add( pwd ).add( user_id ),
	        tips = $( ".validateTips" );
	 
	    function updateTips( t ) {
	      tips.text( t ).addClass( "ui-state-highlight" );
	      setTimeout(function() {
	        tips.removeClass( "ui-state-highlight", 1500 );
	      }, 500 );
	    }
	 
	    function checkLength( o, n, min, max ) {
	      if ( o.val().length > max || o.val().length < min ) {
	        o.addClass( "ui-state-error" );
	        updateTips( "Length of " + n + " must be between " +
	          min + " and " + max + "." );
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
	        return true;c
	      }
	    }
	 	
	    $( "#dialog-message" ).dialog({
	    	autoOpen: false,
	        modal: true,
	        buttons: {
	          Ok: function() {
	        	$( "#dialog-form" ).dialog( "close" );
	            $( this ).dialog( "close" );
	          }
	        }
	      });
	    
	    $( "#dialog-form" ).dialog({
	      autoOpen: false,
	      show: {
	          effect: "blind",
	          duration: 1000
	        },
	      height: 400,
	      width: 400,
	      modal: true,
	      buttons: {
	        "Create an account": function() {
	          var bValid = true;
	          allFields.removeClass( "ui-state-error" );
	 
	          bValid = bValid && checkLength( user_id, "User ID", 3, 20 );
	          bValid = bValid && checkLength( pwd, "Password", 5, 20 );
	          bValid = bValid && checkLength( user_name, "User Name", 3, 50 );
	          bValid = bValid && checkLength( email, "E-mail", 6, 100 );
	 
	          bValid = bValid && checkRegexp( user_id, /^[a-z]([0-9a-z_])+$/i, "User ID may consist of a-z, 0-9, underscores, begin with a letter." );
	          bValid = bValid && checkRegexp( pwd, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
	          bValid = bValid && checkRegexp( user_name, /^[a-z]([0-9a-z_ ])+$/i, "User Name may consist of a-z, 0-9, underscores, space, begin with a letter." );
	          // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
	          bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. test@test.com" );
	 
	          if ( bValid ) {
	            /* $( "#users tbody" ).append( "<tr>" +
	              "<td>" + name.val() + "</td>" +
	              "<td>" + email.val() + "</td>" +
	              "<td>" + password.val() + "</td>" +
	            "</tr>" ); */
	            $.ajax({
	    			url: "http://127.0.0.1:8080/sehub.admin/userJoinProc.html",
	    		  	type: "POST",
	    		  	dataType: "jsonp",
	    		  	jsonp: "jsoncallback",
	    			data: $("form#register").serialize(),
	    		  	success: function( response ) {
	    		  		if (response.result_code == "E40007") {
	    		  			user_id.addClass( "ui-state-error" );
	    			        updateTips( response.result_msg );
	    		  		} else {
	    		  			$( "#dialog-message" ).append("<p>" + 
		    		  						"<span class='ui-icon ui-icon-circle-check' style='float:left; margin:0 7px 50px 0;''></span>" +
		    		  						response.result_msg + 
		    		    		  		  	"</p>");
	    		  			$( "#dialog-message" ).dialog( "open" );
	    		  			$( "#errorDiv" ).append("");
	    		  		}
	    				//$.mobile.changePage( "register-thanks.html", { data: {"email": response.email}} );
	    		  	},
	    			error: function( jqXHR, textStatus, errorThrown ) {
	    				$.mobile.hidePageLoadingMsg();
	    				console.log('Status: ' + textStatus + "\nError: " + errorThrown);
	    			}
	    		});
	          }
	        },
	        Cancel: function() {
	          $( this ).dialog( "close" );
	        }
	      },
	      close: function() {
	        allFields.val( "" ).removeClass( "ui-state-error" );
	      }
	    });
	 
	    $( "#join" ).button().click(function() {
	    	$( "#errorDiv" ).append("");
	        $( "#dialog-form" ).dialog( "open" );
	    });
	    
	    $( "#login" ).button().click(function() {
	        doLogin();
	    });
	  });
  </script>
	
	<script language="javascript" type="text/javascript">
	function doStart() {
		if ("<%=request.getParameter("target")%>" == "_top") {
			parent.window.location.href="login.jsp"; 	
		} else {
			document.getElementById('email').focus();
		}
	}
	
	function doLogin() {
		var f1 = document.loginForm;
		var email = document.getElementById("email");
		var pwd = document.getElementById("pwd");
		
		if (email.value == "") {
			//alert("Plaese, enter your ID.");
			document.getElementById("errorDiv").color = "#ff0000"
			document.getElementById("errorDiv").innerHTML = "<li>Plaese, enter your Email."
			id.focus();
			return;
		}
		
		if (pwd.value == "") {
			//alert("Plaese, enter your Password.");
			document.getElementById("errorDiv").color = "#ff0000"
			document.getElementById("errorDiv").innerHTML = "<li>Plaese, enter your Password."
			pwd.focus();
			return;
		}
		
		f1.action = "loginProc.do";
		f1.cmd.value = "1";
		f1.user_email.value = email.value;
		f1.user_pwd.value = pwd.value;
		
		f1.submit();
	}
	</script>
</head>

<body style="vertical-align: middle;" bgcolor="#ffffff" style="overflow:auto; " onload="doStart();">
<!-- Load facebook sdk -->

<div id="dialog-form" title="Create new user">
  <p class="validateTips">All form fields are required.</p>
 
  <form id="register" method="post">
  <input type="hidden" name="targetPage" value="login.jsp" />
  <fieldset>
  	<label for="name">User ID</label>
    <input type="text" name="user_id" id="user_id" class="text ui-widget-content ui-corner-all" />
    <label for="password">Password</label>
    <input type="password" name="pwd" id="pwd" value="" class="text ui-widget-content ui-corner-all" />
    <label for="name">User Name</label>
    <input type="text" name="user_name" id="user_name" class="text ui-widget-content ui-corner-all" />
    <label for="email">Email</label>
    <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
  </fieldset>
  </form>
</div>
 
<div id="dialog-message" title="User join completed">
</div>
 
<form name="loginForm" method="post" onsubmit="return false;">
	<input type="hidden" name="cmd"/>
	<input type="hidden" name="user_email" value=""/>
	<input type="hidden" name="user_pwd" value=""/>
</form>

<div id="container" style="position: absolute; top:50%; left:50%; overflow:hidden; width: 400px; height: 150px; margin-top:-75px; margin-left:-200px; background-color: #CECECE;">
	<table style="width: 400px; height: 150px; vertical-align: middle;" border="0" cellpadding="0" cellspacing="0" bgcolor="#CECECE" >
		<tr>
			<td style="width: 100px;">
				<table border="0" cellpadding="0" cellspacing="0" bgcolor="#CECECE">
					<tr style="height: 50px; vertical-align: middle;">
						<td style="font-size: 10pt; text-align: right; vertical-align: middle;">
							<b>E-mail</b>
						</td>
					</tr>
					<tr style="height: 50px; vertical-align: middle;">
						<td style="font-size: 10pt; text-align: right; vertical-align: middle;">
							<b>Password</b>
						</td>
					</tr>
					<tr style="height: 50px; vertical-align: middle;">
						<td style="font-size: 10pt; text-align: left; vertical-align: middle;">
							&nbsp;
						</td>
					</tr>
				</table>
			</td>
			<td style="width: 230px; vertical-align: middle;">
				<table border="0" cellpadding="0" cellspacing="0" bgcolor="#CECECE">
					<tr style="height: 50px; vertical-align: middle;">
						<td style="font-size: 8pt; text-align: right;">
							<input type="text" style="width: 200px; height: 15px;" id="email" value="student2@test.com"/>
						</td>
					</tr>
					<tr style="height: 50px; vertical-align: middle;">
						<td style="font-size: 8pt; text-align: right;">
							<input type="password" style="width: 200px; height: 15px;" id="pwd" value="111" onkeypress="if(event.keyCode==13){doLogin();event.keyCode=0;}"/>
						</td>
					</tr>
					<tr style="height: 50px; vertical-align: middle;">
						<td style="font-size: 10pt; text-align: left; vertical-align: middle; color: #ff0000;">
							<div id="errorDiv">
								<% if (resultCode != null && resultCode.length() != 0) { 
										if (resultCode.startsWith("S")) {
											out.println("<font color='#0000ff'>");
										} else {
											out.println("<font color='#ff0000'>");
										}
								%>
										<li><%=resultMsg %>
								<% } %>
							</div>
						</td>
					</tr>
				</table>
			</td>
			<td style="width: 70px; vertical-align: middle;">
				<table  border="0" cellpadding="0" cellspacing="0" bgcolor="#CECECE">
					<tr style="height: 50px; vertical-align: middle;">
						<td style="font-size: 8pt; text-align: right;">
							<!-- <input type="button" style="width: 70px; height: 30px;" value="Login" onclick="doLogin();"/> -->
							<button id="login" style="width: 70px; height: 30px;">Login</button>
						</td>
					</tr>
					<tr style="height: 50px; vertical-align: middle;">
						<td style="font-size: 8pt; text-align: right;">
							<!-- <input type="button" style="width: 70px; height: 30px;" value="Join" onclick="doJoin();" /> -->
							<button id="join" style="width: 70px; height: 30px;">Join</button>
						</td>
					</tr>
					<tr style="height: 50px; vertical-align: middle;">
						<td style="font-size: 10pt; text-align: left; vertical-align: middle;">
							&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
</body>
</html>