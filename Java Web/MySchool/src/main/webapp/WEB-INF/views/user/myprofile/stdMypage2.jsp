<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
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
		width: 350,
		modal: true,
		buttons: {
		  "Close": function() {
		  	$( this ).dialog( "close" );
		  }
		}
	  });
	 
	  $( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	   	   var frm = document.f1;
	   	   	   <% if (request.getAttribute("mode").equals("view")) { %>
		   	 	frm.action = "<%=request.getContextPath() %>/user/myprofile/stdEducationModForm.do";
		   	   <% } else { %>
		   		frm.action = "<%=request.getContextPath() %>/user/myprofile/stdEducationProc.do";
		   	   <% } %>
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
	$( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>Do you want to modify?");
	$( "#dialog-confirm" ).dialog( "open" );
}

function doSave() {
	var cur_school_type1 = document.f1.cur_school_type1;
	var cur_school_name = document.getElementById("cur_school_name");
	  
	var errorMsg = "";
	 
	if (!cur_school_type1[0].checked && !cur_school_type1[1].checked && !cur_school_type1[2].checked
			&& !cur_school_type1[3].checked && !cur_school_type1[4].checked) {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>재학중인 학교의 종류를 선택해 주세요.</p>";
	}
	
	if (cur_school_name.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>재학중인 학교명을 입력해 주세요.</p>";
	}
	
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
        	<li><a href="javascript:selMenu(1)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/mypage/basic_info_off.png" alt="" width="186" height="50" border="0"></a></li>
            <li><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/mypage/school_info_on.png" alt="" width="186" height="50" border="0"></li>
            <li><a href="javascript:selMenu(12)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/mypage/tokutyou_off.png" alt="" width="186" height="50" border="0"></a></li>
        </ul>
    </div>
	
	<div class="form_box">
		<div style="width: 850px; height: 50px; padding-top: 5px; padding-bottom: 5px; text-align: left; color: #ffa052; font-weight: bold;">
	  		학교정보 메뉴에서는 현재 학생의 재학중인 정보를 등록합니다. <br/>
	  		등급정보에 대해서는 암호호 처리되어 저장됩니다.
		</div>
		<form:form name="f1" commandName="curSchoolEntity" method="post">
		<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
		<input type="hidden" name="student_id" value="<c:out value='${studentEntity.student_id }'/>" />
		<input type="hidden" name="mode" value="<c:out value='${mode }'/>" />
		<!-- <input type="hidden" name="join_type" value=""/>
		<input type="hidden" name="mem_type" value=""/> -->
		
		<table class="input_school_data" style="border-collapse: separate; border-spacing: 2px;">
			<tr>
		    	<th valign="top" width="160"><font color="red">*</font> 학교종류</th>
		    	<td>
		    		<form:radiobutton path="cur_school_type1" cssStyle="width:30px;" value="1"/>초
		    		<form:radiobutton path="cur_school_type1" cssStyle="width:30px;" value="2"/>중
		    		<form:radiobutton path="cur_school_type1" cssStyle="width:30px;" value="3"/>고
		    		<form:radiobutton path="cur_school_type1" cssStyle="width:30px;" value="4"/>대
		    		<form:radiobutton path="cur_school_type1" cssStyle="width:30px;" value="5"/>기타
		    	</td>
		    </tr>
		    
		    <tr>
		    	<th valign="top" width="160"><font color="red">*</font> 재학중인 학교명</th>
		    	<td>
		    		<form:input path="cur_school_name" type="text" class="form_fix3" placeholder=" 학교명" />
		    	</td>
		    </tr>
		    
		     <tr>
		    	<th valign="top">현재학교</th>
		    	<td>
		    		<%-- <form:input path="cur_school_grade" type="text" class="form_fix3" placeholder=" 学년" /> --%>
		    		<form:select path="cur_school_grade">  
			        	<form:options items="${cur_school_grade_map}" />
	        		</form:select>
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top">성적</th>
		    	<td>
		        	국어 <form:input path="lang_score" type="text" class="form_fix1" style="width: 70px; text-align: right;"/>
				            역사 <form:input path="hist_score" type="text" class="form_fix1" style="width: 70px; text-align: right;"/>
				            윤리 <form:input path="civil_score" type="text" class="form_fix1" style="width: 70px; text-align: right;"/>
				            <br /><br />
				            수학 <form:input path="mat_score" type="text" class="form_fix1" style="width: 70px; text-align: right;"/>
				            과학 <form:input path="sci_score" type="text" class="form_fix1" style="width: 70px; text-align: right;"/>
				            영어 <form:input path="eng_score" type="text" class="form_fix1" style="width: 70px; text-align: right;"/>
		        </td>
		    </tr>
		    <tr>
		    	<th valign="top">등급</th>
		    	<td>
		    		<form:input path="rank" type="text" class="form_fix1" placeholder=" 등급" />
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
		
		<div style="width: 850px; height: 30px; padding-top: 10px; margin-left: 50px; text-align: left; ">
			<button id="link" style="width:120px; height: 30px; font-size: 10px;">추천정보</button>&nbsp;&nbsp;
			<button id="list" style="width:220px; height: 30px; font-size: 10px;">추천학교목록</button>
	    </div>
	    
	    <div id="div_mypath" style=" width:830px; background:#FFF; margin: 0 auto; height:400px; margin-bottom:40px; margin-top: 20px;">
  			<iframe src="<%=request.getContextPath() %>/user/myprofile/myPath.do?context_name=<%=request.getContextPath() %>&student_id=<c:out value='${userEntity.user_id}'/>" width="830" height="400" frameborder="0"></iframe>
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
