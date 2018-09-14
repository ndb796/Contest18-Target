<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%@ page import="com.mycompany.myschool.web.util.Util" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/dataTables.bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/sb-admin-2.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<%-- <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script> --%>
<script src="<%=request.getContextPath() %>/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/sb-admin-2.js"></script>

<script>
$(document).ready(function() {
    $('#dataTables-list').dataTable({
    	"lengthChange": false,
    	"searching": false,
    	"stateSave": true
    });
});

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
    width: 400,
    modal: true,
    buttons: {
      "Yes": function() {
   	   var frm = document.f1;
   	   if (dialog_configm_status == "logout") {
   			frm.action = "<%=request.getContextPath() %>/logoutProc.do";
   	   } else {
   	   	   <% if (request.getAttribute("mode").equals("view")) { %>
	   	 	frm.action = "<%=request.getContextPath() %>/user/myprofile/stdBasicModForm.do";
	   	   <% } else { %>
	   		frm.action = "<%=request.getContextPath() %>/user/myprofile/stdBasicProc.do";
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
  
  	$("#link").button({
	    icons: {
	        primary: "ui-icon-link"
	    }
	}).click(function() {
		  $("#div_mypath").css("display", "block");
		  $("#div_list").css("display", "none");
	});
	
	$("#list").button({
		    icons: {
		        primary: "ui-icon-document"
		    }
	}).click(function() {
		  $("#div_list").css("display", "block");
		  $("#div_mypath").css("display", "none");
	});
});		

function doModify() {
	$( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>이 정보로 수정 하시겠습니까?");
	$( "#dialog-confirm" ).dialog( "open" );
}

function callError() {
	 $( "#dialog-error" ).html("<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>You must register basic information before moving other information.</p>");
	 $( "#dialog-error" ).dialog( "open" );
}

function doSave() {
	  var lname = document.getElementById("lname");
	  var fname = document.getElementById("fname");
	  var gender = document.f1.gender;
	  var rcmmd_type = document.f1.rcmmd_type;
	  var zipcode = document.getElementById("zipcode");
	  var address1 = document.getElementById("address1");
	  var errorMsg = "";
	  
	  if (lname.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; '></span>이름 (성)을 입력해 주세요.</p>";
	  }
	  if (fname.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; '></span>이름 (이름)을 입력해 주세요.</p>";
	  }
	  if (!gender[0].checked && !gender[1].checked) {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; '></span>성별을 선택해 주세요。</p>";
	  }
	  if (!rcmmd_type[0].checked && !rcmmd_type[1].checked && !rcmmd_type[2].checked
				&& !rcmmd_type[3].checked && !rcmmd_type[4].checked) {
			  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; '></span>희망하는 학교종류을 선택해 주세요。</p>";
		}
	  if (zipcode.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; '></span>우편번호을 입력해 주세요.</p>";
	  }
	  if (address1.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; '></span>주소를  입력해 주세요.</p>";
	  }
	  
	 if (errorMsg != "") {
		  $( "#dialog-error" ).html(errorMsg);
		  $( "#dialog-error" ).dialog( "open" );
	  } else {
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
        document.getElementById("idcheckLayer").innerHTML = "<font color=red>5자리 이상 입력하세요.</font>";     
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
            	document.getElementById("address2").value = "";
            	document.getElementById("address3").value = "";
            	document.getElementById("address4").value = "";
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
        	<li><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/mypage/basic_info_on.png" alt="" width="186" height="50" border="0"></li>
            <li><a href="javascript:selMenu(11)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/mypage/school_info_off.png" alt="" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(12)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/mypage/tokutyou_off.png" alt="" width="186" height="50" border="0"></a></li>
         </ul>
    </div>
	
	<div class="form_box">
		<div style="width: 850px; height: 50px; padding-top: 5px; padding-bottom: 5px; text-align: left; color: #ffa052; font-weight: bold;">
	  		기본정보 메뉴에서는 학생의 기본 인적정보를 등록합니다. <br/>
		</div>
		<form:form name="f1" commandName="studentEntity" method="post" enctype="multipart/form-data">
		<input type="hidden" name="mode" value="<c:out value='${mode }'/>">
		<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
		<form:hidden path="student_id"/>
		
        <table class="profile_image" style="padding-top: 40px; padding-left: 40px; margin-bottom: 25px; border-collapse: separate; border-spacing: 2px;">
			<tr>
		    	<th valign="top">프로필이미지</th>
		   	<c:choose >
		 	<c:when test="${mode == 'add'}">
		 		<td valign="top"><div style="text-align: left; color: #888888; font-weight: bold;">
	  		초기 기본정보를 등록한 다음 이미지를 등록할 수 있습니다.<br/>
		</div></td>
			    <td valign="top" class="select_btn">
			    	<%-- <form:input path="photo_file" type="file" size="230px"/> --%>
			    </td>
		 	</c:when>
		 	<c:when test="${mode == 'modify'}">
				<td valign="top">
					<c:if test="${studentEntity.filename == '' or fn:length(studentEntity.filename) == 0}">
						<img src="/images/common/dammy.png" alt="" width="94" height="93" border="0">
					</c:if>
					<c:if test="${studentEntity.filename != '' and fn:length(studentEntity.filename) > 0}">
						<img src="/images/upload/student/<c:out value='${studentEntity.filename }'/>" alt="photo" width="94" height="93" style="border: 1px;" border="0">
					</c:if>
					<form:input path="photo_file" type="file" size="230px"/>
				</td>
		        <td valign="top" class="select_btn">
		        	<form:input path="photo_file" type="file" size="230px"/>
		        </td>
		 	</c:when>
		 	</c:choose>
		    </tr>
		</table>
		
		<table class="input_data" style="padding-left:105px; margin-bottom:20px; border-collapse: separate; border-spacing: 2px;">
			<tr>
		    	<th valign="top"><font color="red">*</font> E-mail</th>
		    	<td style="padding-bottom:20px;"><i><c:out value='${userEntity.email }'/></i><input type="hidden" name="email" value="<c:out value='${userEntity.email }'/>"/></td>
		    </tr>
		    
		   <%--  <tr>
		    	<th valign="top"><font color="red">*</font> Password</th>
		    	<td>
	    			<input type="password" name="pwd" value="<c:out value='${userEntity.pwd }'/>" class="form_fix1"/>
		    	</td>
		    </tr> --%>
		    
		    <tr>
		    	<th valign="top"><font color="red">*</font> 이름</th>
		    	<td>
		    		<form:input path="lname" type="text" class="form_fix1" placeholder=" 성" />
		    		<form:input path="fname" type="text" class="form_fix1" placeholder=" 이름" />
	    		</td>
		    </tr>
		    
		    <tr>
		    	<th valign="top"><font color="red">*</font> 성별</th>
		    	<td>
	    			<form:radiobutton path="gender" cssStyle="width:30px;" value="M"/>남
		    		<form:radiobutton path="gender" cssStyle="width:30px;" value="F"/>여
		    	</td>
		    </tr>
		    
		    <tr>
		    	<th valign="top"><font color="red">*</font> 희망학교종류</th>
		    	<td>
		    		<form:radiobutton path="rcmmd_type" cssStyle="width:30px;" value="1"/>초
		    		<form:radiobutton path="rcmmd_type" cssStyle="width:30px;" value="2"/>중
		    		<form:radiobutton path="rcmmd_type" cssStyle="width:30px;" value="3"/>고
		    		<form:radiobutton path="rcmmd_type" cssStyle="width:30px;" value="4"/>대
		    		<form:radiobutton path="rcmmd_type" cssStyle="width:30px;" value="5"/>기타
		    	</td>
		    </tr>
		    
		    <tr>
		    	<th valign="top">생년월일</th>
		    	<td>
   					<form:select path="birth_y">  
			        	<form:options items="${birth_y_map}" />
	        		</form:select>년
	        		<form:select path="birth_m">  
			        	<form:options items="${birth_m_map}" />
	        		</form:select>월
	        		<form:select path="birth_d">  
			        	<form:options items="${birth_d_map}" />
	        		</form:select>일
		        </td>
		    </tr>
		    <tr>
		    	<th valign="top"><font color="red">*</font> 우편번호</th>
		    	<td>
		    		<form:input path="zipcode" type="text" class="form_fix1" onkeydown="onlyNum();" maxlength="7" placeholder=" 우편번호" /> 
		    		<font color="grey"> (숫자만) </font>
		    		<!-- <a href="javascript:zipcodecheck()">검색</a> -->
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top"><font color="red">*</font> 주소1 (도)</th>
		    	<td>
		    		<form:input path="address1" type="text" class="form_fix2" placeholder=" 도" />
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top">주소2 (시/군)</th>
		    	<td>
		    		<form:input path="address2" type="text" class="form_fix2" placeholder=" 시/군" />
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top">주소3 (동)</th>
		    	<td>
		    		<form:input path="address3" type="text" class="form_fix2" placeholder=" 동" />
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top">주소4 (건물)</th>
		    	<td>
		    		<form:input path="address4" type="text" class="form_fix2" placeholder=" 건물" />
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
		
		<div class="section_line"></div>
		
		<c:choose >
   			<c:when test="${mode == 'modify'}">
		    <div style="width: 850px; height: 30px; padding-top: 10px; margin-left: 50px; text-align: left; ">
				<button id="link" style="width:120px; height: 30px; font-size: 10px;">추천정보</button>&nbsp;&nbsp;
				<button id="list" style="width:220px; height: 30px; font-size: 10px;">추천학교목록</button>
		    </div>
		    
		    <div id="div_mypath" style=" width:830px; background:#FFF; margin: 0 auto; height:400px; margin-bottom:40px; margin-top: 20px;">
	  			<iframe src="/user/myprofile/myPath.do?student_id=${userEntity.user_id}" width="830" height="400" frameborder="0"></iframe>
		    </div>
		    <div id="div_list" style=" width:830px; margin: 0 auto; margin-top:20px; display: none;">
				<div class="row">
			        <div class="col-lg-12">
			            <div class="panel panel-default">
			                <!-- /.panel-heading -->
			                <div class="panel-body">
			                    <div class="table-responsive">
			                        <table class="table table-striped table-bordered table-hover" id="dataTables-list">
			                            <thead>
			                                <tr>
			                                	<!-- <th>No</th> -->
			                                	<th>학교명</th>
			                                	<th>추천종류</th>
			                                	<th>추천건수</th>
			                                	<th>추천일시</th>
			                            </thead>
			                            <tbody>
			                            	<c:forEach var="school" items="${school_list }" varStatus="status">
				                            	<c:set var="rowClass" value="odd gradeX" />
				                            	<c:if test="${status.count % 2 == 0 }">
				                            		<c:set var="rowClass" value="even gradeX" />
				                            	</c:if>
				                                <tr class="<c:out value='${rowClass}'/>">
				                                    <td style="width: auto; ">
				                                    	<c:out value='${school.school_name}'/>
				                                    </td>
				                                    <td style="width: 120px;">
				                                    	<c:if test="${school.rcmmd_type == 'LOC'}">희망지역</c:if>
				                                    	<c:if test="${school.rcmmd_type == 'SCH'}">희망학교</c:if>
				                                    	<c:if test="${school.rcmmd_type == 'BAC'}">기본정보</c:if>
				                                    </td>
				                                    <td class="center" style="width: 100px;"><c:out value='${school.rcmmd_cnt}'/></td>
				                                    <td class="center" style="width: 100px;"><c:out value='${school.ins_date}'/></td>
				                                </tr>
			                                </c:forEach>
			                            </tbody>
			                        </table>
			                    </div>
			                </div>
			                <!-- /.panel-body -->
			            </div>
			            <!-- /.panel -->
			        </div>
			        <!-- /.col-lg-12 -->
			    </div>
		    </div>
			</c:when>
   		</c:choose>
	</div>
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
