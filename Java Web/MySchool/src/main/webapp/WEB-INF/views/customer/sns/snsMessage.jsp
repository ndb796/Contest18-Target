<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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

<title>SNS Message | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sns_message.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/css/ui.jqgrid.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/i18n/grid.locale-en.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.jqGrid.js" ></script> --%>

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
    
    document.getElementById("ifmSnsMsgDigl").src ="/customer/sns/snsMessageDialog.do?student_id=<c:out value='${rcmmd_list[0].student_id}'/>";
	document.getElementById("student_id").value = "<c:out value='${rcmmd_list[0].student_id}'/>";
	
	$('#dataTables-list tbody').on( 'click', 'tr', function (e) {
        console.log(e);
    } );
});

$(function() {
	var dialog_configm_status = "";
	$( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	    	  if (dialog_configm_status == "logout") {
		    		var frm = document.f1;
		    	    frm.action = "<%=request.getContextPath() %>/logoutProc.do";
		    	    frm.submit();
		    	} else {
		    		var contents = document.getElementById("const_contents");
		    		if (contents.value != "") {
		    			var frm = document.getElementById("constFrm");
		    			frm.target = "ifmSnsMsgDigl";
		    			frm.action = "<%=request.getContextPath() %>/customer/sns/snsConsultProc.do";
		    			frm.contents.value = contents.value;
			    		frm.submit();
		    		} 
					
		    		contents.value = "";
		    		$( this ).dialog( "close" );
		    	}
	      },
	      "No": function() {
	        $( this ).dialog( "close" );
	      }
	    }
	  });
	
	$("#submit").button({
	    icons: {
	        primary: "ui-icon-disk"
	    }
  }).click(function() {
	  $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>상담내용을 등록하시겠습니까?");
	  $( "#dialog-confirm" ).dialog( "open" );
  });
});		

function doSelect(student_id, student_name) {
	document.getElementById("ifmSnsMsgDigl").src ="/customer/sns/snsMessageDialog.do?school_id=<c:out value='${userEntity.user_id }'/>&student_id=" + student_id + "&student_name=" + student_name;
	document.getElementById("student_id").value = student_id;
	//$("#student_name").html(student_name + "と相談내용");
}
</script>
</head>
<body>

<jsp:include page="../../common/header.jsp" flush="false" />

<jsp:include page="../navigation.jsp" flush="false">
	<jsp:param value="5" name="selMenu"/>
</jsp:include>
    
<div class="layout_fix"></div>

<form id="constFrm" name="constFrm" method="post">
	<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
	<input type="hidden" id="sch_idx" name="sch_idx" value="<c:out value='${schoolEntity.idx }'/>" />
    <input type="hidden" id="student_id" name="student_id" value="" />
	<input type="hidden" id="school_id" name="school_id" value="<c:out value='${userEntity.user_id }'/>" />
	<input type="hidden" id="sb_id" name="sb_id" value="<c:out value='${userEntity.user_id }'/>" />
	<input type="hidden" id="contents" name="contents" value="" />
</form>  

<section class="form_area">
	<div class="tab_area">
    	 <ul>
            <li><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/sns_newsfeed/message_on.png" alt="message" width="186" height="50" border="0"></li>
        </ul>
    </div>
    
    <div class="form_box">
    	<div id="div_list" style="width:880px; margin: 0 auto; margin-top:20px; ">
			<div class="row">
		        <div class="col-lg-12">
		            <div class="panel panel-default">
		                <!-- /.panel-heading -->
		                <div class="panel-body">
		                    <div class="table-responsive">
		                        <table class="table table-striped table-bordered table-hover" id="dataTables-list">
		                            <thead>
		                                <tr>
		                                	<th>No</th>
		                                	<th>학생명</th>
		                                	<th>추천유형</th>
		                                	<th>상담상태</th>
		                            </thead>
		                            <tbody>
		                            	<c:forEach var="student" items="${rcmmd_list }" varStatus="status">
			                            	<c:set var="rowClass" value="odd gradeX" />
			                            	<c:if test="${status.count % 2 == 0 }">
			                            		<c:set var="rowClass" value="even gradeX" />
			                            	</c:if>
			                                <tr class="<c:out value='${rowClass}'/>">
			                                    <td style="width: 50px; vertical-align: baseline;"><c:out value='${student.no}'/></td>
			                                    <td style="width: 480px; ">
			                                    	<a href="javascript:doSelect('<c:out value="${student.student_id}"/>', '<c:out value="${student.student_name}"/>')"><c:out value='${student.student_name}'/></a>
			                                    </td>
			                                    <td style="width: 120px;"><c:out value='${student.rcmmd_type}'/></td>
			                                    <td class="center" style="width: 100px;"><c:out value='${student.const_status_msg}'/></td>
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
	   
	   <div id="student_name" style="margin-left: 20px; margin-top: 30px;">
	   		학생과 상담내용
	   </div>
	   
	   <iframe id="ifmSnsMsgDigl" name="ifmSnsMsgDigl" src="" width="880" height="410" frameborder="0" style="margin-left: 5px;" style="overflow-y: scroll;">
	   </iframe>
	   
	   <div class="answer" style="clear:both; background:#d9edf8 !important; height:150px; text-align: center; padding-top: 30px;">
	   		<c:if test="${fn:length(rcmmd_list) == 0}">
       			<textarea id="const_contents" name="const_contents" style="height:70px; width:850px;" readonly="readonly" placeholder=" 추천 된 학생이 아직 없기 때문에 메시지를 보낼 수 없습니다."></textarea>
       		</c:if>
		    <c:if test="${fn:length(rcmmd_list) > 0}">
       			<textarea id="const_contents" name="const_contents" style="height:70px; width:850px;" placeholder=" 상담 내용을 입력하십시오."></textarea>
       			<button id="submit" style="width:100px; height: 30px; font-size: 10px; float: right; margin-top: 5px; margin-right: 50px; 'padding-right: 10px;">Submit</button>
       		</c:if>
       </div>  
    </div>
</section>

<div class="layout_fix2"></div>

<div id="dialog-confirm" title="확인 메시지"></div>
	
<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
