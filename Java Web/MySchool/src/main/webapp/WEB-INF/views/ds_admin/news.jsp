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
    	url: "/ds_admin/news/" + idx + ".do",
    	type: "POST",
	  	dataType: "jsonp",
	  	jsonp: "jsoncallback",
		data: $("form#newsView").serialize(),
	  	success: function( response ) {
	  		document.getElementById("idx").value = response.idx;
	  		document.getElementById("news_title").value = response.news_title;
	  		document.getElementById("upd_date").value = response.upd_date;
	  		document.getElementById("writer").value = response.writer;
	  		document.getElementById("news_contents").value = response.news_contents;
	  		if (response.news_type == 1) {
	  			document.getElementById("news_type_1").checked = true;
	  		} else {
	  			document.getElementById("news_type_2").checked = true;
	  		}
	  		if (response.del_flg == 0) {
	  			document.getElementById("del_flg_0").checked = true;
	  		} else {
	  			document.getElementById("del_flg_1").checked = true;
	  		}
	  		
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
	document.getElementById("news_title").value = "";
	document.getElementById("upd_date").value = new Date();
	document.getElementById("writer").value = "";
	document.getElementById("news_contents").value = "";
	document.getElementById("news_type_1").checked = true;
	document.getElementById("news_type_2").checked = false;
	document.getElementById("del_flg_0").checked = true;
	document.getElementById("del_flg_1").checked = false;
		
	$('#dialog-view').modal({
		show: true
	});
}

function doAdd() {
	var f1 = document.getElementById("newsView");
	f1.news_title.value = document.getElementById("news_title").value;
	if (document.getElementById("news_type_1").checked) {
		f1.news_type.value = "1";
	} else {
		f1.news_type.value = "2";
	}
	if (document.getElementById("del_flg_0").checked) {
		f1.del_flg.value = "0";
	} else {
		f1.del_flg.value = "1";
	}
	//console.log(document.getElementById("news_type_1").checked + "/" + document.getElementById("news_type_2").checked);
	//console.log(document.getElementById("del_flg_0").checked + "/" + document.getElementById("del_flg_1").checked);
	f1.news_contents.value = document.getElementById("news_contents").value;
	
	f1.action = "<%=request.getContextPath() %>/ds_admin/news/newsAddProc.do";
	f1.submit();
}

function doModify() {
	var f1 = document.getElementById("newsView");
	f1.news_title.value = document.getElementById("news_title").value;
	if (document.getElementById("news_type_1").checked) {
		f1.news_type.value = "1";
	} else {
		f1.news_type.value = "2";
	}
	if (document.getElementById("del_flg_0").checked) {
		f1.del_flg.value = "0";
	} else {
		f1.del_flg.value = "1";
	}
	f1.news_contents.value = document.getElementById("news_contents").value;
	
	f1.submit();
}

function doDelete() {
	var f1 = document.getElementById("newsView");
	f1.idx.value = document.getElementById("idx").value;
	f1.action ="<%=request.getContextPath() %>/ds_admin/news/newsDelProc.do";
	f1.submit();
}
</script>
</head>

<body>

<form action="<%=request.getContextPath() %>/ds_admin/news/newsModProc.do" name="newsView" id="newsView">
<input type="hidden" name="idx" id="idx" value="">
<input type="hidden" name="news_type" value="">
<input type="hidden" name="news_title" value="">
<input type="hidden" name="del_flg" value="">
<input type="hidden" name="news_contents" value="">
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
                <h1 class="page-header">뉴스/이벤트 관리</h1>
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
						뉴스/이벤트 목록
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
                                        <th>Type</th>
                                        <th>Update date</th>
                                        <th>View</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="news" items="${news_list }" varStatus="status">
                                    <tr class="odd gradeX">
                                        <td class="center"><c:out value='${news.idx }'/></td>
                                        <td><a href="javascript:doView(<c:out value='${news.idx}'/>)"><c:out value='${news.news_title }'/></a></td>
                                        <td class="center"><c:out value='${news.school_id }'/></td>
                                        <td class="center">
                                        	<c:choose>
                                         	<c:when test="${news.news_type == 1 }">
                                         		뉴스
                                         	</c:when>
                                         	<c:otherwise>
                                         		이벤트
                                         	</c:otherwise>
                                        	</c:choose>
                                        </td>
                                        <td class="center"><c:out value='${news.upd_date_str }'/></td>
                                        <td class="center">
                                        	<c:choose>
                                         	<c:when test="${news.del_flg == 0 }">
                                         		표시
                                         	</c:when>
                                         	<c:otherwise>
                                         		표시안함
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
        <h4 class="modal-title" id="myModalLabel">뉴스/이벤트 상세정보</h4>
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
						<th class="">종류<span class="require">*</span></th>
						<td class="">
						<label class="checkbox-inline"><input type="radio" name="news_type_12" id="news_type_1" value="8"> 뉴스</label>
						<label class="checkbox-inline"><input type="radio" name="news_type_12" id="news_type_2" value="9"> 이벤트</label>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="">제목<span class="require">*</span></th>
						<td class="">
							<input type="text" id="news_title" class="text ui-widget-content ui-corner-all" style="width: 600px;"/>
						</td>						
					</tr>
					<tr style="height: 40px; ">
						<th class="">등록일 <span class="require">*</span></th>
						<td class="">
							<input type="text" id="upd_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 600px;"/>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="">작성자<span class="require">*</span></th>
						<td class=""><input type="text" id="writer" readonly="readonly" class="text ui-widget-content ui-corner-all" style="width: 600px;"/></td>						
					</tr>
					<tr style="height: 40px; ">
						<th class="">표시유무 <span class="require">*</span></th>
						<td class="">
						<label class="checkbox-inline"><input type="radio" name="del_flg_10" id="del_flg_0" value="0"> 표시</label>
						<label class="checkbox-inline"><input type="radio" name="del_flg_10" id="del_flg_1" value="1"> 표시안함</label>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="">내용</th>
						<td class=""><textarea id="news_contents" style="width: 600px; height: 200px;" class="text ui-widget-content ui-corner-all" ></textarea></td>
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
