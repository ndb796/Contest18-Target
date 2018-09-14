<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">

<title>My Profile|My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" >

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>


<script src="<%=request.getContextPath() %>/js/jcarousellite_1.0.1.js"></script>

<script>
$(function() {
	  $( "#dialog-error" ).dialog({
		autoOpen: false,
		resizable: false,
		height: 350,
		width: 350,
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
		   		frm.action = "<%=request.getContextPath() %>/customer/myprofile/schBasicModForm.do";
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
		  $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>수정 페이지로 이동하시겠습니까?");
		  $( "#dialog-confirm" ).dialog( "open" );
	  });
	  
	  $(".anyClass").jCarouselLite({
	        btnNext: ".next",
	        btnPrev: ".prev"
	    });
});

</script>
</head>
<body>

<jsp:include page="../../common/header.jsp" flush="false" />

<jsp:include page="../navigation.jsp" flush="false">
	<jsp:param value="1" name="selMenu"/>
</jsp:include>

<div class="layout_fix"></div>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/basic_info_on.png" alt="" width="186" height="50" border="0"></li>
            <li><a href="javascript:selMenu(11)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/detail_info_off.png" alt="" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(12)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/tantou_info_off.png" alt="" width="186" height="50" border="0"></a></li>
        </ul>
    
    </div>
	
	<div class="form_box">
		<c:if test="${branch_count == 0}">
		<div style="width: 850px; height: 30px; padding-top: 10px; text-align: center; color: #ff0000; font-weight: bold;">
	  		School 메뉴에서 학교정보를 등록해 주세요.
		</div>
		</c:if>
		
		<form:form name="f1" commandName="schoolEntity" method="post">
			<input type="hidden" name="email" value="<c:out value='${userEntity.email }'/>" />
			<input type="hidden" name="mem_type" value="<c:out value='${userEntity.mem_type }'/>" />
			<input type="hidden" name="school_id" value="<c:out value='${schoolEntity.school_id }'/>" />
			
			<table style="height: 20px;">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
		
			<table class="input_data">
				<tr>
			    	<th valign="top"><font color="red">*</font> E-mail</th>
			    	<td><i><c:out value='${userEntity.email }'/></i></td>
			    </tr>
			    
			    <tr>
			    	<th valign="top"><font color="red">*</font> 학교명</th>
			    	<td><c:out value='${schoolEntity.school_name }'/></td>
			    </tr>
			    <tr>
			    	<th valign="top">슬로건</th>
			    	<td>
			    		<c:out value='${schoolEntity.title1 }'/>
			        </td>
		    	</tr>
			    <tr>
			    	<th valign="top">개요</th>
			    	<td>
			    		<c:out value='${schoolEntity.contents }'/>
			    	</td>
			    </tr>
			    <tr>
			    	<th valign="top">설립년도</th>
			    	<td>
			    		<c:out value='${schoolEntity.built_year }'/> 년
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top">홈페이지</th>
			    	<td>
			    		<c:out value='${schoolEntity.homepage }'/>
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top"><font color="red">*</font> 우편번호</th>
			    	<td>
			    		<c:out value='${schoolEntity.zipcode }'/>
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top"><font color="red">*</font> 주소</th>
			    	<td>
			    		<c:out value='${schoolEntity.address1 }'/> <c:out value='${schoolEntity.address2 }'/> <br/>
			    		<c:out value='${schoolEntity.address3 }'/> <c:out value='${schoolEntity.address4 }'/>
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top">학생정보</th>
			    	<td>
			    		<c:out value='${schoolEntity.std_info }'/>
			    	</td>
			    </tr>
			    <%-- <tr>
			    	<th valign="top">프로필 사진</th>
			    	<td>
			    		<c:choose>
	   						<c:when test="${schoolEntity.logoname != null and schoolEntity.logoname != ''}">
		   						<img src="<%=request.getContextPath() %>/images/upload/school/<c:out value='${schoolEntity.school_id }'/>/<c:out value='${schoolEntity.logoname }'/>" alt="photo" width="94" height="93" style="border: 1px;" border="0">
		   					</c:when>
	   						<c:otherwise>
		   						<img src="<%=request.getContextPath() %>/images/common/dammy.png" alt="" width="94" height="93">
		   					</c:otherwise>
	   					</c:choose>
			    	</td>
			    </tr> --%>
		    </table>
		</form:form>
	
		<div style="width: 850px; height: 50px; padding-top: 10px; text-align: right; ">
	  		<button id="modify" style="width:100px; height: 30px; font-size: 10px;">수정페이지로</button>
		</div>
	
		<div class="section_line"></div>
		<div class="slide_area">
			<h2>Images</h2>
			<p class="pr_view">
				<c:if test="${studentEntity.filename == '' }">
  					<img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/school_img.png" alt="" width="140" height="100">
  				</c:if>
  				<c:if test="${studentEntity.filename != '' }">
					<img src="<%=request.getContextPath() %>/images/upload/school/<c:out value='${userEntity.user_id}'/>/<c:out value='${schoolEntity.logoname }'/>" alt="" width="120" height="100">
				</c:if>
			</p>
	      	<p><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/school_img.png" alt="" width="2" height="125"></p>
			
	  	</div>
	</div>
	<div class="layout_fix"></div>
</section>
	
<div id="dialog-error" title="에러 메시지">
</div>

<div id="dialog-confirm" title="확인 메시지">
</div>

<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
