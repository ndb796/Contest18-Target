<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Administration Page</title>

<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/dataTables.bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/sb-admin-2.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />

<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/sb-admin-2.js"></script>

<script>
$(document).ready(function() {
    $('#dataTables-list').dataTable();
});

$(function() {
});		

function updateTips( t ) {
    tips.text( t ).addClass( "ui-state-highlight" );
    setTimeout(function() {
      tips.removeClass( "ui-state-highlight", 1500 );
    }, 500 );
}
	
function doView(student_id, sb_id) {
	$.ajax({
    	url: "/ds_admin/consult/detail.do?student_id=" + studnet_id + "&sb_id=" + sb_id ,
    	type: "POST",
	  	dataType: "jsonp",
	  	jsonp: "jsoncallback",
		data: $("form#blogView").serialize(),
	  	success: function( response ) {
	  		document.getElementById("idx").value = response.idx;
	  		document.getElementById("student_id").value = response.student_id;
	  		document.getElementById("title").value = response.title;
	  		document.getElementById("upd_date").value = response.upd_date;
	  		document.getElementById("name").value = response.name;
	  		if (response.show_flg == 0) {
	  			document.getElementById("show_flg_0").checked = true;
	  		} else {
	  			document.getElementById("show_flg_1").checked = true;
	  		}
	  		document.getElementById("contents").value = response.contents;
	  		
	  		$('#dialog-view').modal({
	  			show: true
	  		});
	  	},
		error: function( jqXHR, textStatus, errorThrown ) {
			console.log('Status: ' + textStatus + "\nError: " + errorThrown);
		}
    });
}

function doDelete() {
	var f1 = document.getElementById("consultView");
	//f1.action ="<%=request.getContextPath() %>/ds_admin/student/studentDelProc.do";
	f1.submit();
}

</script>
</head>

<body>

<form action="<%=request.getContextPath() %>/ds_admin/consult/consultDelProc.do" name="consultView" id="consultView">
<input type="hidden" name="idx" id="idx" value="">
</form>

<div id="wrapper">
    <!-- Navigation S -->
    <jsp:include page="./left.jsp" flush="false">
    	<jsp:param name="menu" value="blog"/>
    </jsp:include>
    <!-- Navigation E -->

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">학생과 학교의 상담</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
			상담목록
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-list">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>학생</th>
                                        <th>학교</th>
                                        <th>상담상태</th>
                                        <th>최신상담일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="consult" items="${consult_list }" varStatus="status">
                                    <tr class="odd gradeX">
                                        <td class="center"><c:out value='${consult.idx }'/></td>
                                        <td><a href="javascript:doView('${consult.student_id}', '${consult.sb_id}')"><c:out value='${consult.student_id }'/></a></td>
                                        <td class="center"><c:out value='${consult.sb_id}'/></td>
                                        <td class="center">
                                        	<c:choose>
                                        		<c:when test="${consult.const_status == 2}">
                                        			학생으로부터
                                        		</c:when>
                                        		<c:when test="${consult.const_status == 3}">
                                        			학교로부터
                                        		</c:when>
                                        		<c:otherwise>
                                        			학교로부터
                                        		</c:otherwise>
                                        	</c:choose>
                                        </td>
                                        <td class="center"><c:out value='${consult.ins_date_str}'/></td>
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
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Large Modal -->
<div id="dialog-view" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">상담내용</h4>
      </div>	
		<div class="modal-body">
			<!-- table Object1 -->
			<table width="100%" cellspacing="0" border="0" class="tbl_type">
				<colgroup>
					<col style="width:160px">
					<col>
				</colgroup>
				<tbody>
					<tr style="height: 40px; ">
						<th class="">제목 <span class="require">*</span></th>
						<td class="">
							<input type="text" id="title" class="text ui-widget-content ui-corner-all" style="width: 600px;"/>
						</td>						
					</tr>
				</tbody>
			</table>
			<!-- //table Object1 -->
		</div>	
		<div class="modal-footer">
			<button type="button" class="btn btn-outline btn-primary" data-dismiss="modal" onclick="doDelete()">삭제</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		</div>
    </div>
  </div>
</div>	

</body>

</html>
