<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script>
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
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	   	   var frm = document.f1;
	   	   if (dialog_configm_status == "logout") {
	   			frm.action = "<%=request.getContextPath() %>/logoutProc.do";
	   	   } else {
		   	 	frm.action = "<%=request.getContextPath() %>/customer/myprofile/schChargerModForm.do";
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
        	<li><a href="javascript:selMenu(1)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/basic_info_off.png" alt="" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(11)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/detail_info_off.png" alt="" width="186" height="50" border="0"></a></li>
            <li><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/tantou_info_on.png" alt="" width="186" height="50" border="0"></li>
        </ul>
    
    </div>
	<div class="form_box">
		<c:if test="${branch_count == 0}">
		<div style="width: 850px; height: 30px; padding-top: 10px; text-align: center; color: #ff0000; font-weight: bold;">
	  		School 메뉴에서 학교정보를 등록해 주세요.
		</div>
		</c:if>
		
	<form:form name="f1" commandName="schoolChargerEntity" method="post">
		<input type="hidden" name="school_id" value="<c:out value='${schoolChargerEntity.school_id }'/>" />
		
		<table class="input_school_data">
			<tr>
		    	<th valign="top"><font color="red">*</font> 담당자 이름</th>
		    	<td>
		    		<c:out value='${schoolChargerEntity.chr_lname }'/> <c:out value='${schoolChargerEntity.chr_fname }'/>
	    		</td>
		    </tr>
		    
		    <tr>
		    	<th valign="top">담당부서</th>
		    	<td>
	    			<c:out value='${schoolChargerEntity.chr_dept }'/>
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top"><font color="red">*</font> 전화</th>
		    	<td>
		    		<c:out value='${schoolChargerEntity.chr_tel }'/>
		    	</td>
		    </tr>
		    
		     <tr>
		    	<th valign="top">FAX</th>
		    	<td>
		    		<c:out value='${schoolChargerEntity.chr_fax }'/>
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top">Facebook</th>
		    	<td>
		    		<c:out value='${schoolChargerEntity.chr_fb }'/>
		        </td>
	    	</tr>
		    <tr>
		    	<th valign="top">Twitter</th>
		    	<td>
		    		<c:out value='${schoolChargerEntity.chr_tw }'/>
		    	</td>
		    </tr>
	    </table>
	</form:form>
	
	<div style="width: 850px; height: 50px; padding-top: 10px; text-align: right; ">
  		<button id="modify" style="width:100px; height: 30px; font-size: 10px;">수정페이지로</button>
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
