<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
if(request.getAttribute("token_key") == null) {
	Token.set(request);
}

String resultCode = (String)request.getAttribute("resultCode");
String resultMsg = "";
if (resultCode != null) {
	resultMsg = ResultCode.GET_MESSAGE_MAP().get(resultCode);
}
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">

<title>학교정보목록 | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common_design.css" >

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
window.onload = function () {
	var resultMsg = "<%=resultMsg%>";
	if (resultMsg != null && resultMsg.length > 0) {
		$( "#dialog-info" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>" + resultMsg);
		$( "#dialog-info" ).dialog( "open" );
	}
};

var dialog_configm_status = "";
var selected_ids = "";

$(document).ready(function() {
    var table = $('#dataTables-list').dataTable({
    	"lengthChange": false,
    	/* "searching": true,
    	"ordering": true, */
    	"stateSave": true,
    	"order": [1, "desc"],
    	"columnDefs": [
                       {"targets": [ 0 ],
                        "searchable": false,
                        "sortable": false}
            ]
    });
    
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
});
$(function() {
	  $("#add").button({
		    icons: {
		        primary: "ui-icon-plus"
		    }
	  }).click(function() {
		  var frm = document.branchFrm;
		  frm.action = "/customer/school/branchAddForm.do";
		  frm.submit();
	  });
	  
	  $("#modify").button({
		    icons: {
		        primary: "ui-icon-pencil"
		    }
	  }).click(function() {
		  doModify();
	  });
	  
	  $("#remove").button({
		    icons: {
		        primary: "ui-icon-minus"
		    }
	  }).click(function() {
			doRemove();
	  });
	  
	  $( "#dialog-info" ).dialog({
	  	 autoOpen: false,
	     resizable: false,
	     height: 200,
	     width: 350,
	     modal: true,
	     buttons: {
	       "닫기": function() {
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
	   	   if (dialog_configm_status == "logout") {
	   			var frm = document.f1;
	   			frm.action = "<%=request.getContextPath() %>/logoutProc.do";
	   			frm.submit();
	   	   } else {
	   		   if (selected_ids == "") {
	   			   console.log("selected ids is null");
	   			   return;
	   		   }
	   			var frm = document.branchFrm;
				frm.action = "/customer/school/branchRemove.do";
				frm.sb_id.value = selected_ids;
				frm.submit();
	   	   }
	      },
	      "아니오え": function() {
	        $( this ).dialog( "close" );
	      }
	    }
	  });
});
function doView(sb_id) {
	var frm = document.branchFrm;
	frm.action = "/customer/school/branchDetail.do";
	frm.sb_id.value = sb_id;
	frm.submit();
}
function doModify() {
	var sbIds = document.getElementsByName("chkId");
	
	var selIds = "";
	var selCount = 0;
	for (var i = 0; i < sbIds.length; i++) {
		if (sbIds[i].checked) {
			selIds = selIds + sbIds[i].value;
			
			if (i < (sbIds.length - 1)) {
				selIds = selIds + ",";
			}
			
			selCount++;
		}
	}
	
	if (selCount > 1 || selCount == 0) {
		 $( "#dialog-error" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>수정하기 위해서는 1개의 학교만 선택해 주세요.");
		 $( "#dialog-error" ).dialog( "open" );
		 return;
	} else {
		var frm = document.branchFrm;
		frm.action = "/customer/school/branchModifyForm.do";
		frm.sb_id.value = selIds;
		frm.submit();
	}
}
function doRemove() {
	var sbIds = document.getElementsByName("chkId");
	
	var selIds = "";
	var selCount = 0;
	for (var i = 0; i < sbIds.length; i++) {
		if (sbIds[i].checked) {
			selIds = selIds + sbIds[i].value;
			
			if (i < (sbIds.length - 1)) {
				selIds = selIds + ",";
			}
			
			selCount++;
		}
	}

	if (selCount == 0) {
		dialog_configm_status = "";
		selected_ids = "";
		$( "#dialog-error" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>삭제할 학교를 선택해 주세요.");
		$( "#dialog-error" ).dialog( "open" );
		return;
	} else {
		dialog_configm_status = "delete";
		console.log(selIds);
		selected_ids = selIds;
		$( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>선택한 학교를 삭제해도 좋겠습니까?");
		$( "#dialog-confirm" ).dialog( "open" );
	}
}
</script>
</head>
<body>

<jsp:include page="../../common/header.jsp" />

<jsp:include page="../navigation.jsp" >
	<jsp:param value="2" name="selMenu"/>
</jsp:include>

<div class="layout_fix"></div>

<form name="branchFrm" action="" method="post">
	<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
	<input type="hidden" name="school_id" value="<c:out value='${schoolEntity.school_id }'/>">
	<input type="hidden" name="sb_id" value="">
</form>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/school_list_on.png" alt="school_list" width="186" height="50" border="0"></li>
            <li><a href="javascript:selMenu(21)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/recomend_std_off.png" alt="추천 학생" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(22)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/bookmark_off.png" alt="즐겨찾기한 학생" width="186" height="50" border="0"></a></li> 
        </ul>
    </div>
	<div class="form_box">
    	<div id="div_list" style=" width:880px; margin: 0 auto; margin-top:20px; ">
    		<div style="width: 850px; height: 50px; padding-bottom: 10px; text-align: right; ">
	   			<button id="add" style="width:130px; height: 30px; font-size: 10px;">학교정보추가</button>
	   			<button id="modify" style="width:130px; height: 30px; font-size: 10px;">학교정보수정</button>
	   			<button id="remove" style="width:130px; height: 30px; font-size: 10px;">학교정보삭제</button>
   			</div>
   			
			<div class="row">
		        <div class="col-lg-12">
		            <div class="panel panel-default">
		                <!-- /.panel-heading -->
		                <div class="panel-body">
		                    <div class="table-responsive">
		                        <table class="table table-striped table-bordered table-hover" id="dataTables-list">
		                            <thead>
		                                <tr>
		                                	<th></th>
		                                	<th>No</th>
		                                	<th>학교명</th>
		                                	<th>종류</th>
		                                	<th>추천수</th>
		                                	<th>즐겨찾기수</th>
		                            </thead>
		                            <tbody>
		                            	<c:forEach var="branch" items="${branch_list }" varStatus="status">
			                            	<c:set var="rowClass" value="odd gradeX" />
			                            	<c:if test="${status.count % 2 == 0 }">
			                            		<c:set var="rowClass" value="even gradeX" />
			                            	</c:if>
			                                <tr class="<c:out value='${rowClass}'/>">
			                                    <td style="width: 25px; vertical-align: baseline; text-align: center;">
			                                    	<input type="checkbox" name="chkId" value="<c:out value='${branch.sb_id }'/>">
			                                    </td>
			                                    <td style="width: 50px; vertical-align: baseline;"><c:out value='${branch.no}'/></td>
			                                    <td style="width: 380px; ">
			                                    	<a href="javascript:doView('<c:out value="${branch.sb_id }"/>')"><c:out value='${branch.sb_name}'/></a>
			                                    </td>
			                                    <td style="width: 110px; text-align: center;">
			                                    	<c:choose>
			                                    		<c:when test="${branch.sb_type == 2}">중학교</c:when>
			                                    		<c:when test="${branch.sb_type == 3}">고등학교</c:when>
			                                    		<c:when test="${branch.sb_type == 4}">대학교</c:when>
			                                    		<c:when test="${branch.sb_type == 5}">전문학교</c:when>
			                                    		<c:otherwise>-</c:otherwise>
			                                    	</c:choose>
			                                    </td>
			                                    <td class="center" style="width: 120px; text-align: center;"><c:out value='${branch.rcmmd_cnt}'/></td>
			                                    <td class="center" style="width: 120px; text-align: center;"><c:out value='${branch.favr_cnt}'/></td>
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

<div id="dialog-info" title="정보 메시지"></div>
<div id="dialog-error" title="에러 메시지"></div>
<div id="dialog-confirm" title="확인 메시지"></div>
	
<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
