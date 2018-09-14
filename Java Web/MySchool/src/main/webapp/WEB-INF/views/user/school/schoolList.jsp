<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Expires" content="-1">

<title>학교정보 목록 | My School</title>
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
var sb_type = 0;

$(document).ready(function() {
	var oTable = $('#dataTables-list').dataTable({
		"lengthChange": false,
		"processing": true,
        "serverSide": true,
        "stateSave": true,
        "ajax" : {
        	"url": "/user/school/schoolList/schoolListJson.do",
        	"data": function ( d ) {
        		if ($('#school_type').val() != '') {
                	d.school_type = $('#school_type').val();
        		}
            }
        },
        "columns": [
                   /*  { "data": "idx" }, */
                    { "data": "name" },
                    { "data": "sb_type" },
                    { "data": "rcmmd_cnt" },
                    { "data": "favr_cnt" }
         ],
         "columnDefs": [
                    {"targets": [ 1 ],
                     "searchable": false,
                     "sortable": false},
                    {"targets": [ 2 ],
                     "searchable": false,
                     "sortable": false},
                    {"targets": [ 3 ],
                     "searchable": false,
                     "sortable": false}
         ]
	});
	
	$('select#school_type').change( function() { 
		oTable.fnFilter( $(this).val() );
	});
});

function doSelect(obj) {
	$('input[type=search]').val('');
	//console.log($('input[type=search]').val());
}
/* 
$('input[type=search]').bind('click', function(e) {
	  var $earch = $(this);
	  var offset = $earch.offset();

	  if (e.pageX > offset.left + $earch.width() - 16) { // X button 16px wide?
	  }
	}); */
</script>
</head>
<body>

<jsp:include page="../../common/header.jsp" />

<jsp:include page="../navigation.jsp" >
	<jsp:param value="2" name="selMenu"/>
</jsp:include>

<div class="layout_fix"></div>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/school/school_list_on.png" alt="school_list" width="186" height="50" border="0"></li>
            <li><a href="javascript:selMenu(21)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/school/recommended_school_off.png" alt="recommended_school" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(22)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/school/favorites_school_off.png" alt="favorites_school" width="186" height="50" border="0"></a></li>
        </ul>
    </div>
	<div class="form_box">
    	<div id="div_list" style=" width:880px; margin: 0 auto; margin-top:20px; ">
			<div class="row">
		        <div class="col-lg-12">
		            <div class="panel panel-default">
		                <!-- /.panel-heading -->
		                <div class="panel-body">
		                    <div class="table-responsive">
		                    	<table border="0" cellspacing="5" cellpadding="5" >
							        <tbody>
							        <tr>
							            <td>
							            	<select id="school_type" onchange="doSelect(this)">
											    <option value="" class="label">학교종류</option>
									            <option value="2">중학교</option>
									            <option value="3">고등학교</option>
									            <option value="4">대학교</option>
									            <option value="5">전문학교</option>
											</select>
							            </td>
							        </tr>
							    	</tbody>
							    </table>
		                        <table class="table table-striped table-bordered table-hover" id="dataTables-list">
		                            <thead>
		                                <tr>
		                                	<!-- <th>No</th> -->
		                                	<th>학교명</th>
		                                	<th>종류</th>
		                                	<th>즐겨찾기</th>
		                                	<th>추천</th>
		                            </thead>
		                            <tbody>
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

<div id="dialog-confirm" title="Confirm dialog"></div>

<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
