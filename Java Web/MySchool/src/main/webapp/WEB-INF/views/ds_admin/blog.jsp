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
	
function doView(idx) {
	$.ajax({
    	url: "/ds_admin/blog/" + idx,
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
	  		
	  		$("#addBtn").hide();
	  		$("#updBtn").show();
	  		$("#delBtn").show();
	  		
	  		$('#dialog-view').modal({
	  			show: true
	  		});
	  	},
		error: function( jqXHR, textStatus, errorThrown ) {
			console.log('Status: ' + textStatus + "\nError: " + errorThrown);
		}
    });
}

function doReg() {
	$("#addBtn").show();
	$("#updBtn").hide();
	$("#delBtn").hide();
		
	document.getElementById("idx").value ="";
	document.getElementById("title").value = "";
	document.getElementById("upd_date").value = new Date();
	document.getElementById("name").value = "administrator";
	document.getElementById("contents").value = "";
	document.getElementById("show_flg_1").checked = true;
	document.getElementById("show_flg_0").checked = false;
		
	$('#dialog-view').modal({
		show: true
	});
}

function doAdd() {
	var f1 = document.getElementById("blogView");
	f1.title.value = document.getElementById("title").value;
	
	if (document.getElementById("show_flg_0").checked) {
		f1.show_flg.value = "0";
	} else {
		f1.show_flg.value = "1";
	}
	f1.contents.value = document.getElementById("contents").value;
	
	f1.action = "<%=request.getContextPath() %>/ds_admin/blog/blogAddProc.do";
	f1.submit();
}

function doModify() {
	var f1 = document.getElementById("blogView");
	f1.title.value = document.getElementById("title").value;
	if (document.getElementById("show_flg_0").checked) {
		f1.show_flg.value = "0";
	} else {
		f1.show_flg.value = "1";
	}
	f1.contents.value = document.getElementById("contents").value;
	
	f1.submit();
}

function doDelete() {
	var f1 = document.getElementById("blogView");
	f1.idx.value = document.getElementById("idx").value;
	f1.action ="<%=request.getContextPath() %>/ds_admin/blog/blogDelProc.do";
	f1.submit();
}
</script>
</head>

<body>

<form action="<%=request.getContextPath() %>/ds_admin/blog/blogModProc.do" name="blogView" id="blogView">
<input type="hidden" name="idx" id="idx" value="">
<input type="hidden" name="student_id" id="student_id" value="">
<input type="hidden" name="title" value="">
<input type="hidden" name="show_flg" value="">
<input type="hidden" name="contents" value="">
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
                <h1 class="page-header">블로그 관리</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
            	<div style="height: 50px;">
            		<a class="btn btn btn-success btn-sm" href="javascript:doReg();">등록</a>
            	</div>
                <div class="panel panel-default">
                    <div class="panel-heading">
						전체 블로그 목록
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-list">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Title</th>
                                        <th>Writer</th>
                                        <th>Register date</th>
                                        <th>View</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="blog" items="${blog_list }" varStatus="status">
                                    <tr class="odd gradeX">
                                        <td class="center"><c:out value='${blog.no }'/></td>
                                        <td><a href="javascript:doView(<c:out value='${blog.idx}'/>)"><c:out value='${blog.title }'/></a></td>
                                        <td class="center"><c:out value='${blog.name }'/></td>
                                        <td class="center"><c:out value='${blog.upd_date_str }'/></td>
                                        <td class="center">
                                        	<c:choose>
                                         	<c:when test="${blog.show_flg == 0 }">
                                         		표시 안함
                                         	</c:when>
                                         	<c:otherwise>
                                         		표시
                                         	</c:otherwise>
                                        	</c:choose>
                                        </td>
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
        <h4 class="modal-title" id="myModalLabel">블로그 상세정보</h4>
      </div>	
		<div class="modal-body">
			<p><span class="require">*</span> 항목은 필수입니다.</p>
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
					<tr style="height: 40px; ">
						<th class="">등록일 <span class="require">*</span></th>
						<td class="">
							<input type="text" id="upd_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 600px;"/>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="">작성일<span class="require">*</span></th>
						<td class=""><input type="text" id="name" readonly="readonly" class="text ui-widget-content ui-corner-all" style="width: 600px;"/></td>						
					</tr>
					<tr style="height: 40px; ">
						<th class="">표시여부 <span class="require">*</span></th>
						<td class="">
						<label class="checkbox-inline"><input type="radio" name="show_flg_10" id="show_flg_1" value="1"> 표시</label>
						<label class="checkbox-inline"><input type="radio" name="show_flg_10" id="show_flg_0" value="0"> 표시안함</label>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="">내용</th>
						<td class=""><textarea id="contents" style="width: 600px; height: 200px;" class="text ui-widget-content ui-corner-all" ></textarea></td>
					</tr>
				</tbody>
			</table>
			<!-- //table Object1 -->
		</div>	
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" id="addBtn" data-dismiss="modal" onclick="doAdd()">등록</button>
			<button type="button" class="btn btn-primary" id="updBtn" data-dismiss="modal" onclick="doModify()">변경</button>
			<button type="button" class="btn btn-outline btn-primary" id="delBtn" data-dismiss="modal" onclick="doDelete()">삭제</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
		</div>
    </div>
  </div>
</div>	

<div id="dialog-message" title="Blog modified">
</div>

</body>

</html>
