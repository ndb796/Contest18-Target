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
		   		frm.action = "<%=request.getContextPath() %>/user/myprofile/stdEducationModForm.do";
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
		  $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>수정페이지로 이동하시겠습니까?");
		  $( "#dialog-confirm" ).dialog( "open" );
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
		<form:form name="f1" commandName="curSchoolEntity" method="post">
		<input type="hidden" name="student_id" value="<c:out value='${studentEntity.student_id }'/>" />
		
		<table class="input_school_data" style="border-collapse: separate; border-spacing: 2px;">
			<tr>
		    	<th valign="top" width="130">학교종류</th>
		    	<td>
		    		<c:if test="${curSchoolEntity.cur_school_type1 == 1 }">초</c:if>
		    		<c:if test="${curSchoolEntity.cur_school_type1 == 2 }">중</c:if>
		    		<c:if test="${curSchoolEntity.cur_school_type1 == 3 }">고</c:if>
		    		<c:if test="${curSchoolEntity.cur_school_type1 == 4 }">대</c:if>
		    		<c:if test="${curSchoolEntity.cur_school_type1 == 5 }">기타</c:if>
		    	</td>
		    </tr>
		    
		    <tr>
		    	<th valign="top" width="130">재학중인 학교명</th>
		    	<td>
		    		<c:out value='${curSchoolEntity.cur_school_name }'/>
		    	</td>
		    </tr>
		    
		     <tr>
		    	<th valign="top">현재학교</th>
		    	<td>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '1' }">초1년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '2' }">초2년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '3' }">초3년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '4' }">초4년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '5' }">초5년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '6' }">초6년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '7' }">중1년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '8' }">중2년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '9' }">중3년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '10' }">고1년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '11' }">고2년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '12' }">고3년</c:if>
	        		<c:if test="${curSchoolEntity.cur_school_grade == '12' }">기타</c:if>
		    	</td>
		    </tr>
		    <tr>
		    	<th valign="top">성적</th>
		    	<td>
		        	국어 : <c:out value='${curSchoolEntity.lang_score }'/> 
				         역사 : <c:out value='${curSchoolEntity.hist_score }'/> 
				         윤리 : <c:out value='${curSchoolEntity.civil_score }'/> 
				            <br /><br />
				         수학 : <c:out value='${curSchoolEntity.mat_score }'/>
				         과학 : <c:out value='${curSchoolEntity.sci_score }'/>
				         영어 : <c:out value='${curSchoolEntity.eng_score }'/>
		        </td>
		    </tr>
		    <tr>
		    	<th valign="top">등급</th>
		    	<td>
		    		<c:out value='${curSchoolEntity.rank }'/>
		    	</td>
		    </tr>
		</table>
		</form:form>
		
		<div style="width: 850px; height: 50px; padding-top: 10px; text-align: right; ">
			<button id="modify" style="width:100px; height: 30px; font-size: 10px;">수정페이지로</button>
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
	</div>
</section>

<div id="dialog-error" title="에러 메시지">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>error message</p>
</div>

<div id="dialog-confirm" title="확인 메시지">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>confirm message</p>
</div>
	
<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
