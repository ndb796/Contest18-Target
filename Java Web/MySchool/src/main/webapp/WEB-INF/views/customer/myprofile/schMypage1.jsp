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

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/blueimp-gallery.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/fileupload2/css/jquery.fileupload.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/fileupload2/css/jquery.fileupload-ui.css">

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

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
	   	   	   <% if (request.getAttribute("mode").equals("view")) { %>
		   	 	frm.action = "<%=request.getContextPath() %>/customer/myprofile/schBasicModForm.do";
		   	   <% } else { %>
		   		frm.action = "<%=request.getContextPath() %>/customer/myprofile/schBasicProc.do";
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
	var realNames = document.getElementsByName("real_name");
	var originNames = document.getElementsByName("origin_name");
	var filesizes = document.getElementsByName("filesize");
	
	var galleryNames = "";
	var galleryOriginNames = "";
	var galleryFilesizes = "";
	for (var i=0; i<realNames.length; i++) {
		galleryNames = galleryNames + realNames[i].value + ",";
		galleryOriginNames = galleryOriginNames + originNames[i].value + ",";
		galleryFilesizes = galleryFilesizes + filesizes[i].value + ",";
	}
	document.f1.gallery_files.value = galleryNames;
	document.f1.gallery_origin_files.value = galleryOriginNames;
	document.f1.gallery_filesizes.value = galleryFilesizes;
	$( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>이 정보로 수정 하시겠습니까?");
	$( "#dialog-confirm" ).dialog( "open" );
}

function doSave() {
	  var school_name = document.getElementById("school_name");
	  /* var school_type = document.getElementById("school_type"); */
	  var zipcode = document.getElementById("zipcode");
	  var address1 = document.getElementById("address1");
	  var errorMsg = "";
	  
	  if (school_name.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>학교명을 입력해 주세요.</p>";
	  }
	  if (zipcode.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>우편번호를 입력해 주세요.</p>";
	  }
	  if (address1.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>주소를 입력해 주세요.</p>";
	  }
	  
	 if (errorMsg != "") {
		  	$( "#dialog-error" ).html(errorMsg);
		  	$( "#dialog-error" ).dialog( "open" );
	  } else {
		  	var realNames = document.getElementsByName("real_name");
			var originNames = document.getElementsByName("origin_name");
			var filesizes = document.getElementsByName("filesize");
			
			var galleryNames = "";
			var galleryOriginNames = "";
			var galleryFilesizes = "";
			for (var i=0; i<realNames.length; i++) {
				galleryNames = galleryNames + realNames[i].value + ",";
				galleryOriginNames = galleryOriginNames + originNames[i].value + ",";
				galleryFilesizes = galleryFilesizes + filesizes[i].value + ",";
			}
			document.f1.gallery_files.value = galleryNames;
			document.f1.gallery_origin_files.value = galleryOriginNames;
			document.f1.gallery_filesizes.value = galleryFilesizes;
			
		  $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>이 정보로 등록 하시겠습니까?");
		  $( "#dialog-confirm" ).dialog( "open" );
	  }
}
var xhr;
function createXhr(){
    if(window.ActiveXObject){   // IE 이전버전
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }else{
        xhr = new XMLHttpRequest();
    }
}
 
function zipcodecheck(){
    var zipcode = document.getElementById("zipcode").value;
    var queryString = "zipcode="+zipcode;
    if(zipcode.length<6){
        document.getElementById("idcheckLayer").innerHTML = "<font color=red>7자리 이상 입력하세요.</font>";     
    }else{
        // 1. XMLHttpReqeust 객체 생성
        createXhr();
        // 2. 이벤트 핸들러 등록
        xhr.onreadystatechange = callback;  // callback 함수를 등록
        // 3. open()를 통해 요청관련 설정을 설정
        xhr.open("POST", "<%=request.getContextPath()%>/common/findByZipcode.do", true);
        // 4. Header에 contentType 지정 - post
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        // 5. send()를 통해 요청
        xhr.send(queryString);  // 요청 쿼리를 보내준다.
    }
}
function callback(){
    if(xhr.readyState==4){      // 응답을 다 받은 경우
        if(xhr.status == 200){  // 응답코드가 200인 경우 - 정상인 경우
            var resTxt = xhr.responseText;  // 서버가 보낸 응답 text
            var info = resTxt.split("|");
            
            if (info[0] == "ERRCODE") {
            	document.getElementById("address1").value = info[1];
            } else {
            	document.getElementById("address1").value = info[0];
            	document.getElementById("address2").value = info[1];
            	document.getElementById("address3").value = info[2];
            	document.getElementById("address4").focus();
            }
             
        }else{
            alert("요청 처리가 정상적으로 되지 않았습니다.\n"+xhr.status);
        }
    }
}

function onlyNum() { 
  var keycode = window.event.keyCode;
 
  if(keycode == 8 || (keycode >= 35 && keycode <= 40) || (keycode >= 46 && keycode <= 57) || (keycode >= 96 && keycode <= 105) || keycode == 110 || keycode == 190) {
    window.event.returnValue = true;
    return;
  } else {
    window.event.returnValue = false;
    return;
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
		
		<form:form name="f1" commandName="schoolEntity" method="post" enctype="multipart/form-data">
			<input type="hidden" name="mode" value="<c:out value='${mode }'/>">
			<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
			<input type="hidden" name="gallery_files" value="" />
			<input type="hidden" name="gallery_origin_files" value="" />
			<input type="hidden" name="gallery_filesizes" value="" />
			<form:hidden path="school_id"/>
			
			<table style="height: 20px;">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
		
			<table class="input_data">
				<tr>
			    	<th valign="top"><font color="red">*</font> E-mail</th>
			    	<td><i><c:out value='${userEntity.email }'/></i><input hidden="hidden" name="email" value="<c:out value='${userEntity.email }'/>"/></td>
			    </tr>
			    
			    <!-- <tr>
			    	<th valign="top"><font color="red">*</font> Password</th>
			    	<td>
		    			<input type="password" name="pwd" value="" class="form_fix1"/>
			    	</td>
			    </tr> -->
			    <tr>
			    	<th valign="top"><font color="red">*</font> 학교명</th>
			    	<td><form:input path="school_name" type="text" class="form_fix2" placeholder="학교명" /></td>
			    </tr>
			    <tr>
			    	<th valign="top">슬로건</th>
			    	<td>
			    		<form:input path="title1" type="text" class="form_fix2" placeholder="슬로건" />
			        </td>
		    	</tr>
			    <tr>
			    	<th valign="top">개요</th>
			    	<td>
			    		<form:textarea path="contents" style="height:50px; width:550px;" placeholder="개요" />
			    	</td>
			    </tr>
			    <tr>
			    	<th valign="top">설립년도</th>
			    	<td>
			    		<form:input path="built_year" type="text" class="form_fix1" onkeydown="onlyNum();" maxlength="4" placeholder="설립년도" /> 년
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top">홈페이지</th>
			    	<td>
			    		<form:input path="homepage" type="text" class="form_fix2" placeholder="홈페이지" />
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top"><font color="red">*</font> 우편번호</th>
			    	<td>
			    		<form:input path="zipcode" type="text" class="form_fix1" onkeydown="onlyNum();" maxlength="7" placeholder="우편번호" />
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top"><font color="red">*</font> 주소1 (도)</th>
			    	<td>
			    		<form:input path="address1" type="text" class="form_fix2" placeholder="도" />
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top"><font color="red">*</font> 주소2 (시)</th>
			    	<td>
			    		<form:input path="address2" type="text" class="form_fix2" placeholder="시" />
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top"><font color="red">*</font> 주소3 (구/군/동)</th>
			    	<td>
			    		<form:input path="address3" type="text" class="form_fix2" placeholder="구/군/동" />
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top"> 주소4 (건물명)</th>
			    	<td>
			    		<form:input path="address4" type="text" class="form_fix2" placeholder="건물명" />
			        </td>
		    	</tr>
		    	<tr>
			    	<th valign="top">학생정보</th>
			    	<td>
			    		<form:textarea path="std_info" style="height:50px; width:550px;" placeholder="학생정보 "/>
			    	</td>
			    </tr>
			    <!-- logo image -->
			    <tr>
			    	<th valign="top">프로필 사진</th>
			    	<td>
			    		<c:out value='${schoolEntity.origin_name }'/>&nbsp;&nbsp;<form:input path="photo_file" type="file" size="230px"/>
			    	</td>
			    </tr>
		    </table>
		</form:form>
		
		<table class="input_data" style="width: 813px; height: 46px; ">
		    <tr>
		    	<th style="width: 148px; vertical-align: top;">갤러리 사진</th>
		    	<td style="width: 675px;">
		    		<form:form id="fileupload" action="/fileController/upload.do" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
		    		<%-- <form id="fileupload" action="<%=request.getContextPath() %>/fileController/upload.do" method="POST" enctype="multipart/form-data" --%>
		    			<input type="hidden" name="school_id" value="<c:out value='${userEntity.user_id }'/>">
					    <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
					    <div class="fileupload-buttonbar">
					        <div class="fileupload-buttons">
					            <!-- The fileinput-button span is used to style the file input field as button -->
					            <span class="fileinput-button" style="width:150px; height: 28px; font-size: 10px; ">
					                <span>Add files...</span>
					                <input type="file" name="files[]" multiple>
					            </span>
					            <button type="submit" class="start" style="width:150px; height: 30px; font-size: 10px;">Start upload</button>
					            <button type="button" class="delete" style="width:100px; height: 30px; font-size: 10px;">Delete</button>
					            <input type="checkbox" class="toggle">
					        </div>
					        <!-- The global file processing state -->
					        <br/>
					        <div style="border: 1px #cccccc solid ; overflow-y: scroll; height: 200px;">
					        	<span class="fileupload-process"></span>
						    	<!-- The global progress state -->
						        <div class="fileupload-progress fade" style="display:none; ">
						            <!-- The global progress bar -->
						            <div class="progress" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
						            <!-- The extended global progress state -->
						            <div class="progress-extended">&nbsp;</div>
						        </div>
						        
						    	<table role="presentation"><tbody class="files"></tbody></table>
						    </div>
						    
					    </div>
					</form:form>
		    	</td>
		    </tr>
		</table>
		
		<div style="width: 850px; height: 50px; padding-top: 10px; text-align: right; ">
			<c:choose >
  				<c:when test="${mode == 'add'}">
  					<button id="save" style="width:100px; height: 30px; font-size: 10px;">등록</button>
  				</c:when>
  				<c:when test="${mode == 'modify'}">
  					<button id="modify" style="width:100px; height: 30px; font-size: 10px;">수정</button>
  				</c:when>
  			</c:choose>
		</div>
	
		<!-- <div class="section_line"></div> -->
   		<%-- <jsp:include page="gallery.jsp"></jsp:include> --%>
	</div>
	
	<div class="layout_fix"></div>
</section>

<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade"  style='width: 600px;'>
        <td style='width: 80px;'>
            <span class="preview"></span>
        </td>
        <td style='width: 80px;'>
            <p class="name">{%=file.name%}</p>
            <strong class="error"></strong>
        </td>
        <td style='width: 150px;'>
            <div class="progress" style="padding-left: 20px; width: 120px; height: 30px; text-align:center;"></div>
        </td>
        <td style='width: 230px;'>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="start" disabled style='width:100px; height: 30px; font-size: 10px;'>Start</button>
            {% } %}
            {% if (!i) { %}
                <button class="cancel" style='width:100px; height: 30px; font-size: 10px;'>Cancel</button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
				<input type="hidden" id="real_name" name="real_name" value="{%=file.real_name%}">
				<input type="hidden" id="origin_name" name="origin_name" value="{%=file.name%}">
				<input type="hidden" id="filesize" name="filesize" value="{%=file.size%}">
            </p>
            {% if (file.error) { %}
                <div><span class="error">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=file.size%}</span>
        </td>
        <td>
            <button class="delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %} style='width:100px; height: 30px; font-size: 10px;'>Delete</button>
            <input type="checkbox" name="delete" value="1" class="toggle">
        </td>
    </tr>
{% } %}
</script>
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script> -->
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="<%=request.getContextPath() %>/js/blueimp/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="<%=request.getContextPath() %>/js/blueimp/load-image.all.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="<%=request.getContextPath() %>/js/blueimp/canvas-to-blob.min.js"></script>
<!-- blueimp Gallery script -->
<script src="<%=request.getContextPath() %>/js/blueimp/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-ui.js"></script>
<!-- The File Upload jQuery UI plugin -->
<script src="<%=request.getContextPath() %>/fileupload2/js/jquery.fileupload-jquery-ui.js"></script>
<!-- The main application script -->
<script src="<%=request.getContextPath() %>/fileupload2/js/main.js"></script>

<div id="dialog-error" title="에러 메시지">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span></p>
</div>

<div id="dialog-confirm" title="확인 메시지">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span></p>
</div>

<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
