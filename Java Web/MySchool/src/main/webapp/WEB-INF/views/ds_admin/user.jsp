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

function updateTips( t ) {
    tips.text( t ).addClass( "ui-state-highlight" );
    setTimeout(function() {
      tips.removeClass( "ui-state-highlight", 1500 );
    }, 500 );
}
	
function doView(user_id) {
	$.ajax({
    	url: "<%=request.getContextPath() %>/ds_admin/user/" + user_id + ".do",
    	type: "POST",
	  	dataType: "jsonp",
	  	jsonp: "jsoncallback",
		data: $("form#userView").serialize(),
	  	success: function( response ) {
	  		//$( "#dialog-view" ).dialog( "open" );
	  		document.getElementById("idx").value = response.idx;
	  		document.getElementById("user_id").value = response.user_id;
	  		document.getElementById("pwd").value = response.pwd;
	  		document.getElementById("email").value = response.email;
	  		document.getElementById("upd_date").value = response.upd_date;
	  		
	  		if (response.join_type == 1) {
	  			document.getElementById("join_type_1").checked = true;
	  		} else if (response.join_type == 2) {
	  			document.getElementById("join_type_2").checked = true;
	  		} else if (response.join_type == 3) {
	  			document.getElementById("join_type_3").checked = true;
	  		} else if (response.join_type == 4) {
	  			document.getElementById("join_type_4").checked = true;
	  		}
	  		
	  		if (response.mem_type == 1) {
	  			document.getElementById("mem_type_1").checked = true;
	  		} else {
	  			document.getElementById("mem_type_2").checked = true;
	  		}
	  		
	  		$('#dialog-view').modal({
	  			show: true
	  		});
	  	},
		error: function( jqXHR, textStatus, errorThrown ) {
			console.log('Status: ' + textStatus + "\nError: " + errorThrown);
		}
    });
}

function doModify() {
	var f1 = document.getElementById("userView");
	f1.user_id.value = document.getElementById("user_id").value;
	f1.email.value = document.getElementById("email").value;
	
	if (document.getElementById("join_type_1").checked) {
		f1.join_type.value = "1";
	} else if (document.getElementById("join_type_2").checked) {
		f1.join_type.value = "2";
	} else if (document.getElementById("join_type_3").checked) {
		f1.join_type.value = "3";
	} else if (document.getElementById("join_type_4").checked) {
		f1.join_type.value = "4";
	}
	
	if (document.getElementById("mem_type_1").checked) {
		f1.mem_type.value = "1";
	} else if (document.getElementById("mem_type_2").checked) {
		f1.mem_type.value = "2";
	} 
	
	f1.submit();
}

function doDelete() {
	var f1 = document.getElementById("userView");
	f1.user_id.value = document.getElementById("user_id").value;
	f1.action ="<%=request.getContextPath() %>/ds_admin/user/userDelProc.do";
	f1.submit();
}
</script>
</head>

<body>

<form action="<%=request.getContextPath() %>/ds_admin/user/userModProc.do" name="userView" id="userView">
<input type="hidden" name="idx" id="idx" value="">
<input type="hidden" name="user_id" value="">
<input type="hidden" name="email" value="">
<input type="hidden" name="join_type" value="">
<input type="hidden" name="mem_type" value="">
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
                <h1 class="page-header">사용자 관리</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
			사용자 목록
			        </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-list">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>ID</th>
                                        <th>이메일</th>
                                        <th>등록일</th>
                                        <th>가입경로</th>
                                        <th>회원종류</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="user" items="${user_list }" varStatus="status">
                                    <tr class="odd gradeX">
                                        <td class="center"><c:out value='${user.idx }'/></td>
                                        <td><a href="javascript:doView('<c:out value="${user.user_id}"/>')"><c:out value='${user.user_id }'/></a></td>
                                        <td class="center"><c:out value='${user.email }'/></td>
                                        <td class="center"><c:out value='${user.upd_date_str }'/></td>
                                        <td class="center">
                                        	<c:choose>
                                         	<c:when test="${user.join_type == 1 }">
                                         		Google+
                                         	</c:when>
                                         	<c:when test="${user.join_type == 2 }">
                                         		Facebook
                                         	</c:when>
                                         	<c:when test="${user.join_type == 3 }">
                                         		Twitter
                                         	</c:when>
                                         	<c:otherwise>
                                         		Web
                                         	</c:otherwise>
                                        	</c:choose>
                                        </td>
                                        <td class="center">
                                        	<c:choose>
                                         	<c:when test="${user.mem_type == 1 }">
                                         		학생
                                         	</c:when>
                                         	<c:when test="${user.mem_type == 2 }">
                                         		학교
                                         	</c:when>
                                         	<c:otherwise>
                                         		관리자
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
        <h4 class="modal-title" id="myModalLabel">사용자 상세정보</h4>
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
						<th class="">사용자 ID <span class="require">*</span></th>
						<td class="">
							<input type="text" id="user_id" readonly="readonly" class="text ui-widget-content ui-corner-all" style="width: 600px;"/>
						</td>						
					</tr>
					<tr style="height: 40px; ">
						<th class="">비밀번호<span class="require">*</span></th>
						<td class="">
							<input type="text" id="pwd" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 600px;"/>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="">이메일<span class="require">*</span></th>
						<td class=""><input type="text" id="email" class="text ui-widget-content ui-corner-all" style="width: 600px;"/></td>						
					</tr>
					<tr style="height: 40px; ">
						<th class="">등록일 <span class="require">*</span></th>
						<td class="">
							<input type="text" id="upd_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 600px;"/>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="">가입경로<span class="require">*</span></th>
						<td class="">
							<label class="checkbox-inline"><input type="radio" name="join_type_1234" id="join_type_1" value="1"> Google+</label>
							<label class="checkbox-inline"><input type="radio" name="join_type_1234" id="join_type_2" value="2"> Facebook</label>
							<label class="checkbox-inline"><input type="radio" name="join_type_1234" id="join_type_3" value="3"> Twitter</label>
							<label class="checkbox-inline"><input type="radio" name="join_type_1234" id="join_type_4" value="4"> Web</label>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="">회원종류<span class="require">*</span></th>
						<td class="">
							<label class="checkbox-inline"><input type="radio" name="mem_type_12" id="mem_type_1" value="1"> 학생</label>
							<label class="checkbox-inline"><input type="radio" name="mem_type_12" id="mem_type_2" value="0"> 학교</label>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //table Object1 -->
		</div>	
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="doModify()">변경</button>
			<button type="button" class="btn btn-outline btn-primary" data-dismiss="modal" onclick="doDelete()">삭제</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
		</div>
    </div>
  </div>
</div>	

<div id="dialog-message" title="Blog modified">
</div>

</body>

</html>
