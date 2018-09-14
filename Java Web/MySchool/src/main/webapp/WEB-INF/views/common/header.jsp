<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.mycompany.myschool.web.entity.base.UserEntity" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
UserEntity loginUserEntity = (UserEntity)session.getAttribute("login_user");
%>
<script src="http://connect.facebook.net/ko_KR/all.js "></script>
<script type="text/javascript">
(function($) {
    $.sFacebook = {
      image_url : null,
      photo_id : null,
      me_uid : null,
      me_name : null,

      initialize : function(settings) {
        settings = jQuery.extend(true , {
          appId : '',
          cookie:true, 
          status:true,
          xfbml:true,
          image_url : 'http://graph.facebook.com/{image}/picture',
          me_photo_target : '#facebook-me-photo',
          me_name_target : '#facebook-me-name',
          facebook_close_target : '#facebook-close'

        }, settings);

        this.image_url = settings.image_url;
        this.me_photo_target = settings.me_photo_target;
        this.me_name_target = settings.me_name_target;
        this.facebook_close_target = settings.facebook_close_target;

        var obj = this;
        jQuery('body').append("<div id='fb-root'></div>");

        FB.init({ 
          appId : settings.appId,
          cookie : settings.cookie, 
          status : settings.status,
          xfbml : settings.xfbml
        });

        FB.Event.subscribe('auth.login', function (response) {
          // do something with response
          obj.reload();
        });

        FB.Event.subscribe('auth.logout', function (response) {
          // do something with response
          obj.reload();
        });

        obj.reload();
      },

      reload : function() {
        var obj = this;

        this.me_profile();
        FB.getLoginStatus(function (response) {
           
          if (response.session) {
            // logged in and connected user, someone you know
            jQuery(obj.facebook_close_target).show();
          } else {
            jQuery(obj.facebook_close_target).hide();
          }

        });
      },

      me_profile : function() {
        var obj = this;

        FB.getLoginStatus(function (response) {
          if (response.session) {
            FB.api('/me', function(user) {
              if(user != null) {
                var image = obj.image_url.replace('{image}',user.id);
                 
                obj.me_uid = user.id;
                obj.me_name = user.name;

                jQuery(obj.me_photo_target).attr("src",image);
                jQuery(obj.me_name_target).text(obj.me_name);
                 
              }

            });

          } else {
             
            obj.me_uid = null;
            obj.me_name = null;
            jQuery(obj.me_photo_target).attr("src",'');
            jQuery(obj.me_name_target).empty();

          }

        });

      },

      login : function() {
        FB.getLoginStatus(function (response) {
           
          if (response.session) {
            obj.me_profile();
          } else {
            FB.login(function(response) { }, { perms:'user_website,email,offline_access,read_stream,publish_stream' });
          }

        });

      },

      logout : function() {
       FB.logout(function(response) { });
      },

    };

  })(jQuery);
  
$(function() {
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

</script>

<script type="text/javascript">
        jQuery(function() {
           
          jQuery.sFacebook.initialize({
            appId : '' // 페이스북 App Id 입력.
          });
 
        });
</script>
      
<form name="logoutFrm" method="post">
</form>		
		
<div class="header_cover">
	<header class="header_menu">
		<div class="leftarea">
			<span class="btn_header01">
				<%  String moveUrl = request.getContextPath();
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
					<img src="<%=request.getContextPath() %>/images/common/logo.gif" alt="logo" width="256" height="41" border="0">
				</a>
			</span>
		</div>
		<div class="righttarea">
			<span style="float: right; margin-top: 25px;">
				<%if (loginUserEntity != null && (loginUserEntity.getMem_type() == Constants.USER_MEM_STUDENT)) { %>
				<span style="">
					<font color="white"><a href="<%=request.getContextPath()%>/common/blog_new/myblog.do">내 블로그</a></font>
				</span>
				<%} %>
				<font color="white">안녕하세요, <%=((UserEntity)session.getAttribute("login_user")).getEmail() %> 님</font>
				<button id="logout" style="width:110px; height: 20px; font-size: 10px;">로그아웃</button>
			</span>
		</div>
	</header>	
</div>
