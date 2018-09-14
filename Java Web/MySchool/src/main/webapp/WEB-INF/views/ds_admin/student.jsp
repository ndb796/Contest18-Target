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

var isBacUpdated = false;
var isCurUpdated = false;
var isSpcUpdated = false;
function doChangeField(menu) {
	if (menu == 'bac') {
		if (!isBacUpdated) {
			isBacUpdated = true;
		}
	} else if (menu == 'cur') {
		if (!isCurUpdated) {
			isCurUpdated = true;
		}
	} else if (menu == 'spc') {
		if (!isSpcUpdated) {
			isSpcUpdated = true;
		}
	}
}

function updateTips( t ) {
    tips.text( t ).addClass( "ui-state-highlight" );
    setTimeout(function() {
      tips.removeClass( "ui-state-highlight", 1500 );
    }, 500 );
}
	
function doView(student_id) {
	$.ajax({
    	url: "<%=request.getContextPath() %>/ds_admin/student/" + student_id + ".do",
    	type: "POST",
	  	dataType: "jsonp",
	  	jsonp: "jsoncallback",
		data: $("form#studentView").serialize(),
	  	success: function( response ) {
	  		/* basic info */
	  		console.log(response);
	  		//document.getElementById("idx").value = response.idx;
	  		document.getElementById("student_id").value = response.student_id;
	  		document.getElementById("bac_ins_date").value = response.bac_ins_date;
	  		document.getElementById("bac_upd_date").value = response.bac_upd_date;
	  		document.getElementById("fname").value = response.fname;
	  		document.getElementById("lname").value = response.lname;
	  		if (response.gender == 'M') {
	  			document.getElementById("gender_m").checked = true;
	  		} else {
	  			document.getElementById("gender_f").checked = true;
	  		}
	  		if (response.rcmmd_type == 1) {
	  			document.getElementById("rcmmd_type_1").checked = true;
	  		} else if (response.rcmmd_type == 2) {
	  			document.getElementById("rcmmd_type_2").checked = true;
	  		} else if (response.rcmmd_type == 3) {
	  			document.getElementById("rcmmd_type_3").checked = true;
	  		} else if (response.rcmmd_type == 4) {
	  			document.getElementById("rcmmd_type_4").checked = true;
	  		} else if (response.rcmmd_type == 5) {
	  			document.getElementById("rcmmd_type_5").checked = true;
	  		}
	  		document.getElementById("birth_y").value = response.birth_y;
	  		document.getElementById("birth_m").value = response.birth_m;
	  		document.getElementById("birth_d").value = response.birth_d;
	  		document.getElementById("zipcode").value = response.zipcode;
	  		document.getElementById("address1").value = response.address1;
	  		document.getElementById("address2").value = response.address2;
	  		document.getElementById("address3").value = response.address3;
	  		document.getElementById("address4").value = response.address4;
	  		
	  		/* current school info */
	  		if (response.is_curschool_entity == "yes") {
	  			document.getElementById("cur_ins_date").value = response.cur_ins_date;
		  		document.getElementById("cur_upd_date").value = response.cur_upd_date;
		  		if (response.cur_school_type1 == 1) {
		  			document.getElementById("cur_school_type1").checked = true;
		  		} else if (response.cur_school_type1 == 2) {
		  			document.getElementById("cur_school_type2").checked = true;
		  		} else if (response.cur_school_type1 == 3) {
		  			document.getElementById("cur_school_type3").checked = true;
		  		} else if (response.cur_school_type1 == 4) {
		  			document.getElementById("cur_school_type4").checked = true;
		  		} else if (response.cur_school_type1 == 5) {
		  			document.getElementById("cur_school_type5").checked = true;
		  		}
		  		document.getElementById("cur_school_name").value = response.cur_school_name;
		  		document.getElementById("cur_school_grade").value = response.cur_school_grade;
		  		document.getElementById("lang_score").value = response.lang_score;
		  		document.getElementById("eng_score").value = response.eng_score;
		  		document.getElementById("hist_score").value = response.hist_score;
		  		document.getElementById("civil_score").value = response.civil_score;
		  		document.getElementById("mat_score").value = response.mat_score;
		  		document.getElementById("sci_score").value = response.sci_score;
		  		document.getElementById("rank").value = response.rank;
	  		} else {
	  			document.getElementById("cur_ins_date").value = "";
		  		document.getElementById("cur_upd_date").value = "";
		  		document.getElementById("cur_school_type1").checked = false;
		  		document.getElementById("cur_school_type2").checked = false;
		  		document.getElementById("cur_school_type3").checked = false;
		  		document.getElementById("cur_school_type4").checked = false
		  		document.getElementById("cur_school_type5").checked = false;
		  		document.getElementById("cur_school_name").value = "";
		  		document.getElementById("cur_school_grade").value = "";
		  		document.getElementById("lang_score").value = "";
		  		document.getElementById("eng_score").value = "";
		  		document.getElementById("hist_score").value = "";
		  		document.getElementById("civil_score").value = "";
		  		document.getElementById("mat_score").value = "";
		  		document.getElementById("sci_score").value = "";
		  		document.getElementById("rank").value = "";
	  		}
	  		
	  		/* specific info */
	  		if (response.is_specific_entity == "yes") {
	  			document.getElementById("spc_ins_date").value = response.spc_ins_date;
		  		document.getElementById("spc_upd_date").value = response.spc_upd_date;
		  		document.getElementById("school1").value = response.school1;
		  		document.getElementById("school2").value = response.school2;
		  		document.getElementById("school3").value = response.school3;
		  		document.getElementById("location1").value = response.location1;
		  		document.getElementById("location2").value = response.location2;
		  		document.getElementById("location3").value = response.location3;
		  		document.getElementById("subject1").value = response.subject1;
		  		document.getElementById("subject2").value = response.subject2;
		  		document.getElementById("subject3").value = response.subject3;
		  		document.getElementById("job1").value = response.job1;
		  		document.getElementById("job2").value = response.job2;
		  		document.getElementById("job3").value = response.job3;
		  		document.getElementById("field1").value = response.field1;
		  		document.getElementById("field2").value = response.filed2;
		  		document.getElementById("field3").value = response.filed3;
		  		document.getElementById("school_ord").value = response.school_ord;
		  		document.getElementById("subject_ord").value = response.subject_ord
		  		document.getElementById("location_ord").value = response.location_ord;
		  		document.getElementById("job_ord").value = response.job_ord;
		  		document.getElementById("field_ord").value = response.field_ord;
	  		} else {
	  			document.getElementById("spc_ins_date").value = "";
		  		document.getElementById("spc_upd_date").value = "";
		  		document.getElementById("school1").value = "";
		  		document.getElementById("school2").value = "";
		  		document.getElementById("school3").value = "";
		  		document.getElementById("location1").value = "";
		  		document.getElementById("location2").value = "";
		  		document.getElementById("location3").value = "";
		  		document.getElementById("subject1").value = "";
		  		document.getElementById("subject2").value = "";
		  		document.getElementById("subject3").value = "";
		  		document.getElementById("job1").value = "";
		  		document.getElementById("job2").value = "";
		  		document.getElementById("job3").value = "";
		  		document.getElementById("field1").value = "";
		  		document.getElementById("field2").value = "";
		  		document.getElementById("field3").value = "";
		  		document.getElementById("school_ord").value = "";
		  		document.getElementById("subject_ord").value = "";
		  		document.getElementById("location_ord").value = "";
		  		document.getElementById("job_ord").value = "";
		  		document.getElementById("field_ord").value = "";
	  		}
	  		
	  		isBacUpdated = false;
	  		isCurUpdated = false;
	  		isSpcUpdated = false;
	  		
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
	var f1 = document.getElementById("studentView");
	f1.student_id.value = document.getElementById("student_id").value;
	f1.fname.value = document.getElementById("fname").value;
	f1.lname.value = document.getElementById("lname").value;
	if (document.getElementById("gender_m").checked) {
		f1.gender.value = "M";
	} else if (document.getElementById("gender_f").checked) {
		f1.gender.value = "F";
	}
	if (document.getElementById("rcmmd_type_1").checked) {
		f1.rcmmd_type.value = "1";
	} else if (document.getElementById("rcmmd_type_2").checked) {
		f1.rcmmd_type.value = "2";
	} else if (document.getElementById("rcmmd_type_3").checked) {
		f1.rcmmd_type.value = "3";
	} else if (document.getElementById("rcmmd_type_4").checked) {
		f1.rcmmd_type.value = "4";
	} else if (document.getElementById("rcmmd_type_5").checked) {
		f1.rcmmd_type.value = "5";
	}
	f1.birth_y.value = document.getElementById("birth_y").value;
	f1.birth_m.value = document.getElementById("birth_m").value;
	f1.birth_d.value = document.getElementById("birth_d").value;
	f1.zipcode.value = document.getElementById("zipcode").value;
	f1.address1.value = document.getElementById("address1").value;
	f1.address2.value = document.getElementById("address2").value;
	f1.address3.value = document.getElementById("address3").value;
	f1.address4.value = document.getElementById("address4").value;

	if (document.getElementById("cur_school_type1").checked) {
		f1.cur_school_type1.value = "1";
	} else if (document.getElementById("cur_school_type2").checked) {
		f1.cur_school_type1.value = "2";
	} else if (document.getElementById("cur_school_type3").checked) {
		f1.cur_school_type1.value = "3";
	} else if (document.getElementById("cur_school_type4").checked) {
		f1.cur_school_type1.value = "4";
	} else if (document.getElementById("cur_school_type5").checked) {
		f1.cur_school_type1.value = "5";
	}
	f1.cur_school_name.value = document.getElementById("cur_school_name").value;
	f1.cur_school_grade.value = document.getElementById("cur_school_grade").value;
	f1.lang_score.value = document.getElementById("lang_score").value;
	f1.eng_score.value = document.getElementById("eng_score").value;
	f1.hist_score.value = document.getElementById("hist_score").value;
	f1.civil_score.value = document.getElementById("civil_score").value;
	f1.mat_score.value = document.getElementById("mat_score").value;
	f1.sci_score.value = document.getElementById("sci_score").value;
	f1.rank.value = document.getElementById("rank").value;
	
	f1.school1.value = document.getElementById("school1").value;
	f1.school2.value = document.getElementById("school2").value;
	f1.school3.value = document.getElementById("school3").value;
	f1.school_ord.value = document.getElementById("school_ord").value;
	f1.location1.value = document.getElementById("location1").value;
	f1.location2.value = document.getElementById("location2").value;
	f1.location3.value = document.getElementById("location3").value;
	f1.location_ord.value = document.getElementById("location_ord").value;
	f1.subject1.value = document.getElementById("subject1").value;
	f1.subject2.value = document.getElementById("subject2").value;
	f1.subject3.value = document.getElementById("subject3").value;
	f1.subject_ord.value = document.getElementById("subject_ord").value;
	f1.job1.value = document.getElementById("job1").value;
	f1.job2.value = document.getElementById("job2").value;
	f1.job3.value = document.getElementById("job3").value;
	f1.job_ord.value = document.getElementById("job_ord").value;
	f1.field1.value = document.getElementById("field1").value;
	f1.field2.value = document.getElementById("field2").value;
	f1.field3.value = document.getElementById("field3").value;
	f1.field_ord.value = document.getElementById("field_ord").value;
	
	f1.isBacUpdated.value = isBacUpdated;
	f1.isCurUpdated.value = isCurUpdated;
	f1.isSpcUpdated.value = isSpcUpdated;
	
	f1.submit();
}

function doDelete() {
	var f1 = document.getElementById("studentView");
	f1.action ="<%=request.getContextPath() %>/ds_admin/student/studentDelProc.do";
	f1.submit();
}
</script>
</head>

<body>

<form action="<%=request.getContextPath() %>/ds_admin/student/studentModProc.do" name="studentView" id="studentView">
<input type="hidden" name="idx" id="idx" value="">
<input type="hidden" name="isBacUpdated" value="">
<input type="hidden" name="isCurUpdated" value="">
<input type="hidden" name="isSpcUpdated" value="">

<input type="hidden" name="student_id" value="">
<input type="hidden" name="fname" value="">
<input type="hidden" name="lname" value="">
<input type="hidden" name="gender" value="">
<input type="hidden" name="rcmmd_type" value="">
<input type="hidden" name="birth_y" value="">
<input type="hidden" name="birth_m" value="">
<input type="hidden" name="birth_d" value="">
<input type="hidden" name="zipcode" value="">
<input type="hidden" name="address1" value="">
<input type="hidden" name="address2" value="">
<input type="hidden" name="address3" value="">
<input type="hidden" name="address4" value="">

<input type="hidden" name="cur_school_type1" value="">
<input type="hidden" name="cur_school_name" value="">
<input type="hidden" name="cur_school_grade" value="">
<input type="hidden" name="lang_score" value="">
<input type="hidden" name="eng_score" value="">
<input type="hidden" name="hist_score" value="">
<input type="hidden" name="civil_score" value="">
<input type="hidden" name="mat_score" value="">
<input type="hidden" name="sci_score" value="">
<input type="hidden" name="rank" value="">

<input type="hidden" name="school1" value="">
<input type="hidden" name="school2" value="">
<input type="hidden" name="school3" value="">
<input type="hidden" name="school_ord" value="">
<input type="hidden" name="location1" value="">
<input type="hidden" name="location2" value="">
<input type="hidden" name="location3" value="">
<input type="hidden" name="location_ord" value="">
<input type="hidden" name="subject1" value="">
<input type="hidden" name="subject2" value="">
<input type="hidden" name="subject3" value="">
<input type="hidden" name="subject_ord" value="">
<input type="hidden" name="job1" value="">
<input type="hidden" name="job2" value="">
<input type="hidden" name="job3" value="">
<input type="hidden" name="job_ord" value="">
<input type="hidden" name="field1" value="">
<input type="hidden" name="field2" value="">
<input type="hidden" name="field3" value="">
<input type="hidden" name="field_ord" value="">
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
                <h1 class="page-header">학생회원관리</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
			학생목록
			        </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-list">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>ID</th>
                                        <th>이름</th>
                                        <th>학교</th>
                                        <th>특징</th>
                                        <th>추천</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="student" items="${student_list }" varStatus="status">
                                    <tr class="odd gradeX">
                                        <td class="center"><c:out value='${student.idx }'/></td>
                                        <td><a href="javascript:doView('<c:out value="${student.student_id}"/>')"><c:out value='${student.student_id }'/></a></td>
                                        <td class="center"><c:out value='${student.lname }'/> <c:out value='${student.fname }'/></td>
                                        <td class="center">
                                        	<c:choose>
                                         	<c:when test="${student.student_sub1_entity != null }">
                                         		등록완료
                                         	</c:when>
                                         	<c:otherwise>
                                         		-
                                         	</c:otherwise>
                                        	</c:choose>
                                        </td>
                                        <td class="center">
                                        	<c:choose>
                                         	<c:when test="${student.student_sub2_entity != null }">
                                         		등록완료
                                         	</c:when>
                                         	<c:otherwise>
                                         		-
                                         	</c:otherwise>
                                        	</c:choose>
                                        </td>
                                        <td class="center"><c:out value='${student.rcmmd_cnt }'/></td>
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
        <h4 class="modal-title" id="myModalLabel">학생 - 기본정보</h4>
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
						<th class="">학생ID<span class="require">*</span></th>
						<td class="" colspan="3">
							<input type="text" id="student_id" class="text ui-widget-content ui-corner-all" style="width: 600px;"/>
						</td>						
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">등록일 <span class="require">*</span></th>
						<td class="">
							<input type="text" id="bac_ins_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
						</td>
						<th class="" style="width: 150px;">최신수정일 </th>
						<td class="">
							<input type="text" id="bac_upd_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">이름<span class="require">*</span></th>
						<td class="" colspan="3">
							성 <input type="text" id="lname" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
							&nbsp;&nbsp;
							명<input type="text" id="fname" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
						</td>						
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">성별<span class="require">*</span></th>
						<td class="" colspan="3">
							<label class="checkbox-inline"><input type="radio" name="gender_mf" id="gender_m" value="M"> 남 </label>
							<label class="checkbox-inline"><input type="radio" name="gender_mf" id="gender_f" value="F"> 여</label>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">희망학교유형<span class="require">*</span></th>
						<td class="" colspan="3">
							<label class="checkbox-inline"><input type="radio" name="rcmmd_type_12345" id="rcmmd_type_1" value="1"> 초</label>
							<label class="checkbox-inline"><input type="radio" name="rcmmd_type_12345" id="rcmmd_type_2" value="2"> 중</label>
							<label class="checkbox-inline"><input type="radio" name="rcmmd_type_12345" id="rcmmd_type_3" value="3"> 고</label>
							<label class="checkbox-inline"><input type="radio" name="rcmmd_type_12345" id="rcmmd_type_4" value="4"> 대</label>
							<label class="checkbox-inline"><input type="radio" name="rcmmd_type_12345" id="rcmmd_type_5" value="5"> 기타</label>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">생년월일<span class="require">*</span></th>
						<td class="" colspan="3">
							<input type="text" id="birth_y" class="text ui-widget-content ui-corner-all" style="width: 70px;"/> 년
							&nbsp;&nbsp;
							<input type="text" id="birth_m" class="text ui-widget-content ui-corner-all" style="width: 70px;"/> 월
							&nbsp;&nbsp;
							<input type="text" id="birth_d" class="text ui-widget-content ui-corner-all" style="width: 70px;"/> 일
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">우편번호<span class="require">*</span></th>
						<td class="" colspan="3">
							<input type="text" id="zipcode" class="text ui-widget-content ui-corner-all" style="width: 100px;"/>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">주소<span class="require">*</span></th>
						<td class="" colspan="3">
							주소1 (도)<input type="text" id="address1" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
							&nbsp;&nbsp;
							주소2 (시)<input type="text" id="address2" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
							<br/>
							주고3 (동)<input type="text" id="address3" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
							&nbsp;&nbsp;
							주소4 (건물)<input type="text" id="address4" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //table Object1 -->
		</div>
	    <hr />
	    <h4 class="modal-title" style="margin-left: 16px;" id="myModalLabel">학생 - 학교정보</h4>
		<div class="modal-body">
			<table width="100%" cellspacing="0" border="0" class="tbl_type">
				<tbody>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">등록일 </th>
						<td class="">
							<input type="text" id="cur_ins_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
						</td>
						<th class="" style="width: 150px;">최근수정일 </th>
						<td class="">
							<input type="text" id="cur_upd_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">학교 종류</th>
						<td class="" colspan="3">
							<label class="checkbox-inline"><input type="radio" name="cur_school_type12345" id="cur_school_type1" value="1" onchange="doChangeField('cur')"> 초</label>
							<label class="checkbox-inline"><input type="radio" name="cur_school_type12345" id="cur_school_type2" value="2" onchange="doChangeField('cur')"> 중</label>
							<label class="checkbox-inline"><input type="radio" name="cur_school_type12345" id="cur_school_type3" value="3" onchange="doChangeField('cur')"> 고</label>
							<label class="checkbox-inline"><input type="radio" name="cur_school_type12345" id="cur_school_type4" value="4" onchange="doChangeField('cur')"> 대</label>
							<label class="checkbox-inline"><input type="radio" name="cur_school_type12345" id="cur_school_type5" value="5" onchange="doChangeField('cur')"> 기타</label>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">재학중인 학교</th>
						<td class="">
							<input type="text" id="cur_school_name" class="text ui-widget-content ui-corner-all" style="width: 300px;" onchange="doChangeField('cur')"/>
						</td>	
						<th class="">현재 학년</th>
						<td class="">
							<input type="text" id="cur_school_grade" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('cur')"/>
						</td>					
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">성적</th>
						<td class="" colspan="3">
							국어 <input type="text" id="lang_score" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('cur')"/>
							&nbsp;&nbsp;
							지리 <input type="text" id="hist_score" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('cur')"/>
							&nbsp;&nbsp;
							윤리   <input type="text" id="civil_score" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('cur')"/>
							<br/>
							수학 <input type="text" id="mat_score" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('cur')"/>
							&nbsp;&nbsp;
							과학 <input type="text" id="sci_score" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('cur')"/>
							&nbsp;&nbsp;
							영어    <input type="text" id="eng_score" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('cur')"/>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="" style="width: 150px;">순위<span class="require">*</span></th>
						<td class="" colspan="3">
							<input type="text" id="rank" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('cur')"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>	
		<hr />
	    <h4 class="modal-title" style="margin-left: 16px;" id="myModalLabel">학생 - 기타</h4>
		<div class="modal-body">
			<table width="100%" cellspacing="0" border="0" class="tbl_type">
				<tbody>
					<tr style="height: 40px; ">
						<th class="specific_th" style="width: 150px;">등록일 </th>
						<td class="">
							<input type="text" id="spc_ins_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
						</td>
						<th class="specific_th">최근수정일 </th>
						<td class="">
							<input type="text" id="spc_upd_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 200px;"/>
						</td>
					</tr>
					<tr style="height: 40px; ">
						<th class="specific_th" style="width: 150px;">특징</th>
						<td class="" colspan="3">
							<table cellspacing="0" border="0">
								<tr>
									<td>희망학교</td>
									<td><input type="text" id="school1" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="school2" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="school3" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td class="specific_td_grade">우선순위</td>
									<td><input type="text" id="school_ord" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
								</tr>
								<tr>
									<td>지역</td>
									<td><input type="text" id="location1" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="location2" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="location3" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td class="specific_td_grade">우선순위</td>
									<td><input type="text" id="location_ord" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
								</tr>
								<tr>
									<td>특기과목</td>
									<td><input type="text" id="subject1" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="subject2" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="subject3" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td class="specific_td_grade">우선순위</td>
									<td><input type="text" id="subject_ord" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
								</tr>
								<tr>
									<td>흥미과목</td>
									<td><input type="text" id="job1" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="job2" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="job3" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td class="specific_td_grade">우선순위</td>
									<td><input type="text" id="job_ord" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
								</tr>
								<tr>
									<td>전문분야</td>
									<td><input type="text" id="field1" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="field2" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td><input type="text" id="field3" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
									<td class="specific_td_grade">우선순위</td>
									<td><input type="text" id="field_ord" class="text ui-widget-content ui-corner-all" style="width: 100px;" onchange="doChangeField('spc')"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
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
