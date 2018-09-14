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

<title>NEWS | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/news.css" >

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
var dialog_configm_status = "";
var selected_ids = "";

$(document).ready(function() {
    $('#dataTables-list').dataTable({
    	"lengthChange": false,
    	"searching": false,
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
	  var frm = document.newsFrm;
	  frm.action = "/customer/news/eventAddForm.do";
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
			   			var frm = document.newsFrm;
						frm.action = "/customer/news/newsRemove.do";
						frm.news_id.value = selected_ids;
						frm.submit();
			   	   }
		      },
		      "No": function() {
		        $( this ).dialog( "close" );
		      }
	    }
	  });
	
	$( "#dialog-view" ).dialog({
        autoOpen: false,
        show: {
            effect: "blind",
            duration: 300
          },
        height: 550,
        width: 400,
        modal: true,
        buttons: {
        	닫기: function() {
              $( this ).dialog( "close" );
              document.getElementById("news_title").value = "";
  	  		  document.getElementById("upd_date").value = "";
  	  		  document.getElementById("news_contents").value = "";
            }
          },
    	close: function() {
    		document.getElementById("news_title").value = "";
	  		document.getElementById("upd_date").value = "";
	  		document.getElementById("news_contents").value = "";
        }
  });
});		

function doView(idx) {
	$.ajax({
    	url: "/customer/news/newsView/" + idx + ".do",
    	type: "POST",
	  	dataType: "jsonp",
	  	jsonp: "jsoncallback",
		data: $("form#evtView").serialize(),
	  	success: function( response ) {
	  		$( "#dialog-view" ).dialog( "open" );
	  		document.getElementById("news_title").value = response.news_title;
	  		document.getElementById("upd_date").value = response.upd_date;
	  		console.log(response.upd_date);
	  		document.getElementById("news_contents").value = response.news_contents;
	  	},
		error: function( jqXHR, textStatus, errorThrown ) {
			console.log('Status: ' + textStatus + "\nError: " + errorThrown);
		}
    });
}

function doModify() {
	var newsIds = document.getElementsByName("chkId");
	
	var selIds = "";
	var selCount = 0;
	for (var i = 0; i < newsIds.length; i++) {
		if (newsIds[i].checked) {
			selIds = selIds + newsIds[i].value;
			
			if (i < (newsIds.length - 1)) {
				selIds = selIds + ",";
			}
			
			selCount++;
		}
	}
	
	if (selCount > 1 || selCount == 0) {
		 $( "#dialog-error" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>수정하기 위해서는 하나의 이벤트만 선택해 주세요.");
		 $( "#dialog-error" ).dialog( "open" );
		 return;
	} else {
		var frm = document.newsFrm;
		frm.action = "/customer/news/eventModForm.do";
		frm.news_id.value = selIds;
		frm.submit();
	}
}

function doRemove() {
	var newsIds = document.getElementsByName("chkId");
	
	var selIds = "";
	var selCount = 0;
	for (var i = 0; i < newsIds.length; i++) {
		if (newsIds[i].checked) {
			selIds = selIds + newsIds[i].value;
			
			if (i < (newsIds.length - 1)) {
				selIds = selIds + ",";
			}
			
			selCount++;
		}
	}

	if (selCount == 0) {
		dialog_configm_status = "";
		selected_ids = "";
		$( "#dialog-error" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>삭제하려는 이벤트를 선택해 주세요.");
		$( "#dialog-error" ).dialog( "open" );
		return;
	} else {
		dialog_configm_status = "delete";
		console.log(selIds);
		selected_ids = selIds;
		$( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>선택한 이벤트를 삭제해도 되겠습니까?");
		$( "#dialog-confirm" ).dialog( "open" );
	}
}
</script>

</head>
<body>

<jsp:include page="../../common/header.jsp" flush="false" />

<jsp:include page="../navigation.jsp" flush="false">
	<jsp:param value="6" name="selMenu"/>
</jsp:include>

<div class="layout_fix"></div>

<form name="newsFrm" action="" method="post">
	<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
	<input type="hidden" name="school_id" value="<c:out value='${schoolEntity.school_id }'/>">
	<input type="hidden" name="news_id" value="">
	<input type="hidden" name="news_type" value="<%=Constants.NEWS_TYPE_EVNTS %>" />
</form>

<section class="form_area">
	<form name="f1" method="post">
			<input type="hidden" name="email" value="<c:out value='${userEntity.email }'/>" />
			<input type="hidden" name="mem_type" value="<c:out value='${userEntity.mem_type }'/>" />
			<input type="hidden" name="student_id" value="<c:out value='${studentEntity.student_id }'/>" />
			<input type="hidden" name="school_id" value="<c:out value='${schoolEntity.school_id }'/>" />
	</form>
	<div class="tab_area">
    	 <ul>
        	<li><a href="<%=request.getContextPath() %>/customer/news/newsList.do"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/news/news_off.png" alt="news" width="186" height="50" border="0"></a></li>
            <li><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/news/event_on.png" alt="event" width="186" height="50" border="0"></li>
        </ul>
    </div>
	<div class="form_box">
     	<div id="div_list" style=" width:880px; margin: 0 auto; margin-top:20px; ">
     		<div style="width: 850px; height: 50px; padding-bottom: 10px; text-align: right; ">
	   			<button id="add" style="width:130px; height: 30px; font-size: 10px;">이벤트 추가</button>
	   			<button id="modify" style="width:130px; height: 30px; font-size: 10px;">이벤트 수정</button>
	   			<button id="remove" style="width:130px; height: 30px; font-size: 10px;">이벤트 삭제</button>
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
		                                	<th>제목</th>
		                                	<th>종류</th>
		                                	<th>등록일</th>
		                            </thead>
		                            <tbody>
		                            	<c:forEach var="event" items="${news_list }" varStatus="status">
			                            	<c:set var="rowClass" value="odd gradeX" />
			                            	<c:if test="${status.count % 2 == 0 }">
			                            		<c:set var="rowClass" value="even gradeX" />
			                            	</c:if>
			                                <tr class="<c:out value='${rowClass}'/>">
			                                	<td style="width: 25px; vertical-align: baseline; text-align: center;">
			                                	<c:choose>
				                                	<c:when test="${event.school_id == 'ADMIN' }">
				                                    	<input type="checkbox" name="chkId" value="<c:out value='${event.idx }'/>" disabled="disabled">
				                                    </c:when>
				                                    <c:otherwise>
				                                    	<input type="checkbox" name="chkId" value="<c:out value='${event.idx }'/>">
				                                    </c:otherwise>
			                                    </c:choose>
			                                    </td>
			                                    <td style="width: 50px; vertical-align: baseline;"><c:out value='${event.no}'/></td>
			                                    <td style="width: 480px; ">
			                                    	<a href="javascript:doView(<c:out value='${event.idx}'/>)"><c:out value='${event.news_title}'/></a>
			                                    </td>
			                                    <td style="width: 120px;">
			                                    <c:choose>
				                                	<c:when test="${event.news_type == 1 }">
				                                		<c:choose>
				                                		<c:when test="${event.school_id == 'ADMIN' }">
				                                    		My School로 부터
				                                    	</c:when>
				                                    	<c:otherwise>
						                                	News
						                                </c:otherwise>
					                                    </c:choose>
				                                    </c:when>
				                                    <c:otherwise>
				                                    	<c:choose>
				                                		<c:when test="${event.school_id == 'ADMIN' }">
				                                    		My School로 부터
				                                    	</c:when>
				                                    	<c:otherwise>
						                                	Event
						                                </c:otherwise>
					                                    </c:choose>
				                                    </c:otherwise>
			                                    </c:choose>
			                                    </td>
			                                    <td class="center" style="width: 100px;"><c:out value='${event.upd_date}'/></td>
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

<div id="dialog-view" title="이벤트 상세표시">
  <form id="evtView" method="post">
  <fieldset>
  	<label for="title">제목</label><br/>
    <input type="text" name="news_title" id="news_title" readonly="readonly" class="text ui-widget-content ui-corner-all" style="width: 100%;"/><br/><br/>
    <label for="sdate">등록일</label><br/>
    <input type="text" name="upd_date" id="upd_date" readonly="readonly" value="" class="text ui-widget-content ui-corner-all" style="width: 100%;"/><br/><br/>
    <label for="location">내용</label><br/>
    <textarea name="news_contents" id="news_contents" style="width: 360px; height: 200px;" class="text ui-widget-content ui-corner-all" readonly="readonly"></textarea>
 	
  </fieldset>
  </form>
</div>

<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
