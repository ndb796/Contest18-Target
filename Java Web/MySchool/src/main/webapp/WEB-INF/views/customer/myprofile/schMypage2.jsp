<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%@ page import="com.mycompany.myschool.web.util.Util" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
if(request.getAttribute("token_key") == null) {
	Token.set(request);
}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">

<title>My Profile|My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false" ></script>
<script src="<%=request.getContextPath() %>/js/googlemap.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var geocoder = new google.maps.Geocoder();
	var address = '<c:out value="${schoolEntity.address }"/>"';
	var lat = 0.0;
	var lng = 0.0;
	geocoder.geocode( { 'address': address}, function(results, status) {
	      if (status == google.maps.GeocoderStatus.OK) {
	    	  lat = results[0].geometry.location.lat();
	    	  lng = results[0].geometry.location.lng();
	      } else {
	    	  address = '서울특별시 중구 명동 세종대로 110';
	    	  lat = '37.5662952';
	    	  lng = '126.97794509999994';
	      }
	      
	      initialize(lat, lng, address);
	});
});
</script>

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
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	   	   var frm = document.f1;
	   	   if (dialog_configm_status == "logout") {
	   			frm.action = "<%=request.getContextPath() %>/logoutProc.do";
	   	   } else {
	   	   	   <% if (request.getAttribute("mode").equals("view")) { %>
		   	 	frm.action = "<%=request.getContextPath() %>/customer/myprofile/schDetailModForm.do";
		   	   <% } else { %>
		   		frm.action = "<%=request.getContextPath() %>/customer/myprofile/schDetailProc.do";
		   	   <% } %>
	   	   }
	   	   frm.submit();
	      },
	      "No": function() {
	        $( this ).dialog( "close" );
	      }
	    }
	  });
	  
	  $("#save").button({
		    icons: {
		        primary: "ui-icon-disk"
		    }
	  }).click(function() {
		  doSave();
	  });
	  
	  $("#modify").button({
		    icons: {
		        primary: "ui-icon-pencil"
		    }
	  }).click(function() {
		  doModify();
	  });
});

function doModify() {
	$( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>이 정보로 수정 하시겠습니까?");
	$( "#dialog-confirm" ).dialog( "open" );
}

function doSave() {
	  /* var school_name = document.getElementById("school_name");
	  var zipcode = document.getElementById("zipcode");
	  var address1 = document.getElementById("address1"); */
	  var errorMsg = "";
	  
	 /*  if (school_name.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>Please, enter a shcool name.</p>";
	  }
	  if (zipcode.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>Please, enter a zipcode.</p>";
	  }
	  if (address1.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>Please, enter an address.</p>";
	  } */
	  
	 if (errorMsg != "") {
		  $( "#dialog-error" ).html(errorMsg);
		  $( "#dialog-error" ).dialog( "open" );
	  } else {
		  $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>이 정보로 등록 하시겠습니까?");
		  $( "#dialog-confirm" ).dialog( "open" );
	  }
}
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
            <li><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/detail_info_on.png" alt="" width="186" height="50" border="0"></li>
            <li><a href="javascript:selMenu(12)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/mypage/tantou_info_off.png" alt="" width="186" height="50" border="0"></a></li>
        </ul>
    
    </div>
	<div class="form_box">
		<c:if test="${branch_count == 0}">
		<div style="width: 850px; height: 30px; padding-top: 10px; text-align: center; color: #ff0000; font-weight: bold;">
	  		School 메뉴에서 학교정보를 등록해 주세요.
		</div>
		</c:if>
		
	<form:form name="f1" commandName="schoolDetailEntity" method="post">
		<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
		<input type="hidden" name="school_id" value="<c:out value='${schoolEntity.school_id }'/>" />
		<input type="hidden" name="sch_idx" value="<c:out value='${schoolEntity.idx }'/>" />
		<input type="hidden" name="mode" value="<c:out value='${mode }'/>" />
		
		<table class="input_school_data">
			<tr>
		    	<th valign="top">학교소개</th>
		    	<td>
		    		<form:textarea path="features" style="height:50px; width:550px;" placeholder=" 학교소개" />
		    	</td>
		    </tr>
		    
		    <tr>
		    	<th valign="top">학과소개</th>
		    	<td>
	    			<form:textarea path="courses" style="height:50px; width:550px;" placeholder=" 학과소개" />
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top">학비</th>
		    	<td>
		    		<form:textarea path="fee" style="height:50px; width:550px;" placeholder=" 학비" />
		    	</td>
		    </tr>
		    
		     <tr>
		    	<th valign="top">장학제도</th>
		    	<td>
		    		<form:textarea path="financial" style="height:50px; width:550px;" placeholder=" 장학제도" />
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top">선발기준</th>
		    	<td>
		    		<form:input path="selection" type="text" class="form_fix2" placeholder=" 선발기준" />
		        </td>
	    	</tr>
		    <tr>
		    	<th valign="top">교통</th>
		    	<td>
		    		<form:textarea path="access" style="height:50px; width:550px;" placeholder=" 교통" />
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top">지도</th>
		    	<td>
		    		<!-- <div id="map_canvas" style="width: 550px; height: 300px; background-color: #cccccc; border: #cccccc 1px solid;"></div>
		    		<input type="hidden" id="map_info" name="map" value=""/> -->
		    		<div id="map_basic" style="width: 550px; height: 328px; background-color: #cccccc; border: #cccccc 1px solid; float:left; display:block;"></div>
		   			<span id="lat"></span>
		   			<span id="lng"></span>
		   			<span id="address"></span>
		        </td>
	    	</tr>
	    </table>
	</form:form>

	<div style="width: 850px; height: 50px; padding-top: 10px; text-align: right; ">
		<c:choose >
  				<c:when test="${mode == 'add'}">
  					<button id="save" style="width:100px; height: 30px; font-size: 10px;">등록</button>
  				</c:when>
  				<c:when test="${mode == 'modify'}">
  					<button id="save" style="width:100px; height: 30px; font-size: 10px;">수정</button>
  				</c:when>
  			</c:choose>
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
