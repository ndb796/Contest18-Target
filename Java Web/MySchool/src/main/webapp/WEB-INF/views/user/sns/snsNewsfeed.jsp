<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>newsfeed | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common_design.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<script>
$(function() {
      $( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	    	   var frm = document.f1;
	   	       frm.action = "<%=request.getContextPath() %>/logoutProc.do";
		   	   frm.submit();
	      },
	      "No": function() {
	        $( this ).dialog( "close" );
	      }
	    }
	  });
});		
</script>
</head>
<body>

<jsp:include page="../../common/header.jsp" flush="false" />

<jsp:include page="../navigation.jsp" flush="false">
	<jsp:param value="5" name="selMenu"/>
</jsp:include>

<div class="layout_fix"></div>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/news_feed_on.png" alt="news_feed" width="186" height="50" border="0"></li>
            <li><a href="javascript:selMenu(51)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/message_off.png" alt="message" width="186" height="50" border="0"></a></li>
        </ul>
    </div>
	<div class="sns_box">
    	<div class="leftarea2">
			<div class="user_settings">
        	<span class="select_group"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/select_group.jpg" alt="select_group" width="254" height="34" border="0"></a></span>
            
            <div class="user_panel">
        		<h1><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/profile_view.gif" alt="profile_view" width="99" height="99" border="0"></h1>
            	<span class="user_name">테스트1</span>
                <form>
                	<textarea name="" rows="10" placeholder="今なにしてる？" class="user_comment"></textarea>
                </form>
      			<p class="btn_carmera"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_carmera.gif" alt="btn_carmera" width="23" height="20" border="0"></a></p>
                <p class="btn_post"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_post.gif" alt="btn_carmera" width="80" height="25" border="0"></a></p>
            	<div class="layout_fix2"></div>
            </div>
			</div>
        </div>
        <div class="rightarea2">   
            <div class="comment_area">
        		<h2><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/user01.gif" alt="user01" width="47" height="47" border="0"></h2>
           		<div class="post_data">
				<span class="user_name">TEST A</span>
				<span class="date">4.10 20:25</span><br>
                <span class="comment_contents">커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 </span>
				</div>
            
            	<div class="layout_fix2"></div>
            
           		<div class="condition">
	            	<p class="open_btn"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_open.jpg" alt="open" width="27" height="15" border="0">
	                </p>
	                <p class="heart"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/icon_heart.gif" alt="heart" width="17" height="25" border="0"></p>
	                <p class="count">＋1</p>
	                <p><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/icon_message.gif" alt="message" width="18" height="22" border="0"></p>
	                <p class="count">＋2</p>
	            	<p class="btn_emotion"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_emotion.gif" alt="btn_emotion" width="76" height="25" border="0"></a></p>
	            	<p><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_comment.gif" alt="btn_comment" width="76" height="25" border="0">
	                </a></p>	
	            	<div class="layout_fix3"></div>
	           	 </div>
             
             	<div class="add_comment"></div>
	            	<h2><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/user03.gif" alt="user01" width="47" height="47" border="0"></h2>
	           		<div class="post_data">
						<span class="user_name">A</span>
						<span class="date">4.10 20:25</span><br>
	                	<span class="comment_contents">커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 커멘트 </span>
					</div>
	            	<div class="layout_fix2"></div>
	                <div class="condition">
	            	<p class="close_btn"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_close.jpg" alt="close" width="39" height="13" border="0">
	                </p>
	                <p class="heart2"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/icon_heart.gif" alt="heart" width="17" height="25" border="0"></p>
	                <p class="count">＋1</p>
	                <p><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/icon_message.gif" alt="message" width="18" height="22" border="0"></p>
	                <p class="count">＋2</p>
	                
	            	<p class="btn_emotion"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_emotion.gif" alt="btn_emotion" width="76" height="25" border="0"></a></p>
	            	<p><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_comment.gif" alt="btn_comment" width="76" height="25" border="0">
	                </a></p>
	            		
	            	<div class="layout_fix3"></div>
	            </div>
            
            	<div class="response"></div>
                <div class="condition">
                	<div class="res_data">
                		<p class="heart3">
                        <a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/icon_h.gif" alt="heart" width="18" height="18" border="0"></a>
                        </p>
                		<p class="count">＋1</p>
               			<p><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/icon_star.gif" alt="icon_star" width="17" height="18" border="0"></p>
               			<p class="count">＋2</p>
                    </div>
                    <div class="user_caption">
					<p class="btn_emotion"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/other_user01.gif" alt="other_user" width="37" height="37" border="0"></a></p>
            		<p><a href="">
                    <img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/other_user02.gif" alt="other_user" width="40" height="37" border="0"></a></p>
            		</div>
            		<div class="layout_fix4"></div>
            	</div>
                <!--커멘트 송신 폼 -->
				<div class="condition">
                	<div class="answer">
                    <img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/icon_upper.jpg" alt="upper" border="0">
                	<input type="text" id="inputName" placeholder=" A님에게 송신" class="form_fix4">
                    	
                        <span class="emotion_area"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_like.gif" alt="btn_like" width="85" height="25" border="0"></a>
                    	<a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_fight.gif" alt="btn_fight" width="85" height="25" border="0"></a>
                    	<a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_understand.gif" alt="btn_understand" width="85" height="25" border="0"></a>
                   		<a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_good.gif" alt="btn_good" width="85" height="25" border="0"></a>
                        
                        <span class="post"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_post.gif" alt="btn_carmera" width="80" height="25" border="0"></a></span>
                        </span> 
                    </div>      
				</div>
              
                <!-- 유저로부터 커멘트 1 개시-->
                <div class="comment_top"></div>
                <div class="res">
                	<h2><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/user01.gif" alt="user01" width="47" height="47"></h2>
           			<div class="post_data2">
						<span class="user_name">테스트1</span>
						<span class="date">4.10 20:25</span><br>
                		<span class="comment_contents">A 님<br>커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 커멘트1 </span>
					</div>
                </div>
           
           		<div class="layout_fix2"></div>
                <!-- 유저로부터 커멘트 1 종료-->
                <!-- 유저로부터 커멘트 2 개시-->
               	<div class="comment_top"></div>
               	<div class="res">
               		<h2><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/user01.gif" alt="user01" width="47" height="47" border="0"></h2>	
          				<div class="post_data2">
						<span class="user_name">테스트 A</span>
						<span class="date">4.10 20:25</span><br>
		                <span class="comment_contents">A 님<br>Comment Comment Comment Comment Comment Comment Comment Comment Comment Comment Comment Comment Comment Comment Comment Comment Comment Comment </span>
					</div>
               	</div>
           		<div class="layout_fix2"></div>
             	<!-- 유저로부터 커멘트 2 종료-->
            </div><!-- comment_area end-->
            
            <div class="comment_area2">
        		<h2><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/user01.gif" alt="user01" width="47" height="47" border="0"></h2>
           		<div class="post_data">
					<span class="user_name">TEST K</span>
					<span class="date">4.10 20:25</span><br>
	                <span class="comment_contents">COMENT COMENT COMENT COMENT COMENT COMENT COMENT COMENT COMENT COMENT COMENT COMENT COMENT </span>
				</div>
            
            	<div class="layout_fix2"></div>
            
	            <div class="condition2">
	            	<p class="open_btn">
	                <a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_open.jpg" alt="open" width="27" height="15" border="0"></a>
	                </p>
	
	                
	            	<p class="btn_emotion"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_emotion.gif" alt="btn_emotion" width="76" height="25" border="0"></a></p>
	            	<p><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_comment.gif" alt="btn_comment" width="76" height="25" border="0">
	                </a></p>
	            		
	            	<div class="layout_fix3"></div>
	            </div>
            
            	<div class="comment_top"></div>
            	<h2><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/user01.gif" alt="user01" width="47" height="47" border="0"></h2>
           		<div class="post_data">
					<span class="user_name">이도원</span>
					<span class="date">4.10 20:25</span><br>
	                <span class="comment_contents">뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 뭐라고 </span>
				</div>
            
            	<div class="layout_fix2"></div>
            
            	<div class="condition2">
	            	<p class="open_btn">
	                <a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_open.jpg" alt="open" width="27" height="15" border="0"></a>
	                </p>
                
	            	<p class="btn_emotion"><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_emotion.gif" alt="btn_emotion" width="76" height="25" border="0"></a></p>
	            	<p><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/btn_comment.gif" alt="btn_comment" width="76" height="25" border="0">
	                </a></p>
            		
            		<div class="layout_fix3"></div>
           	 	</div>
        	</div><!-- comment_area end-->
    	</div>
    	<div class="layout_fix3"></div>
	</div>
</section>

<div class="layout_fix2"></div>

<div id="dialog-confirm" title="Confirm dialog"></div>
	
<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
