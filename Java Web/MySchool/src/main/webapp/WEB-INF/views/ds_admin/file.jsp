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
	
function doView(school_id) {
	$.ajax({
    	url: "/ds_admin/file/" + school_id,
    	type: "POST",
	  	dataType: "jsonp",
	  	jsonp: "jsoncallback",
		data: {'dir_type': document.getElementById("dir_type").value},
	  	success: function( response ) {
	  		document.getElementById("school_id").value = response.school_id;
	  		document.getElementById("file_list").value = response.file_list;
	  		
	  		$('#dialog-view').modal({
	  			show: true
	  		});
	  	},
		error: function( jqXHR, textStatus, errorThrown ) {
			console.log('Status: ' + textStatus + "\nError: " + errorThrown);
		}
    });
}

</script>
</head>

<body>

<div id="wrapper">
    <!-- Navigation S -->
    <jsp:include page="./left.jsp" flush="false">
    	<jsp:param name="menu" value="file"/>
    </jsp:include>
    <!-- Navigation E -->

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">업로드 파일목록 관리</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
						업로드 파일 종류&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;
						<select id="dir_type">
							<option value="-">- 선택하세요 -</option>
							<option value="gallery" <c:if test="${dir_type == 'gallery' }">selected</c:if>>Gallery</option>
							<option value="logo" <c:if test="${dir_type == '/' }">selected</c:if>>Logo</option>
						</select>
                    </div>
                    
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-list">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>School ID</th>
                                        <th>School Name</th>
                                        <th>Last Updated date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="school" items="${school_list }" varStatus="status">
                                    <tr class="odd gradeX">
                                        <td class="center"><c:out value='${school.idx }'/></td>
                                        <td><a href="javascript:doView('<c:out value="${school.school_id}"/>')"><c:out value='${school.school_id }'/></a></td>
                                        <td class="center"><c:out value='${school.school_name }'/></td>
                                        <td class="center"><c:out value='${school.upd_date_str }'/></td>
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
        <h4 class="modal-title" id="myModalLabel">업로드 파일 상세정보</h4>
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
						<th class="">School ID</th>
						<td class="">
							<input type="text" id="school_id" readonly="readonly" class="text ui-widget-content ui-corner-all" style="width: 600px;"/>
						</td>						
					</tr>
					<tr style="height: 40px; ">
						<th class="">File List</th>
						<td class=""><textarea id="file_list" readonly="readonly" style="width: 600px; height: 200px;" class="text ui-widget-content ui-corner-all" ></textarea></td>
					</tr>
				</tbody>
			</table>
			<!-- //table Object1 -->
		</div>	
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
		</div>
    </div>
  </div>
</div>	

<div id="dialog-message" title="Blog modified">
</div>

</body>

</html>
