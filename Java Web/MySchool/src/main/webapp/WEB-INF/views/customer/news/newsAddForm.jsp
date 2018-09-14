<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<title>뉴스등록 | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/css/news.css" > --%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<script>
$(document).ready(function() {
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
	
	var dialog_configm_status = "";
	$( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	   	   var frm = document.f1;
	   	   if (dialog_configm_status == "logout") {
	   			frm.action = "<%=request.getContextPath() %>/logoutProc.do";
	   	   } else {
	   	   	   <% if (request.getAttribute("mode").equals("modify")) { %>
		   	 		frm.action = "<%=request.getContextPath() %>/customer/news/newsAddProc.do";
		   	   <% } else { %>
		   			frm.action = "<%=request.getContextPath() %>/customer/news/newsAddProc.do";
		   	   <% } %>
	   	   }
	   	   frm.submit();
	      },
	      "No": function() {
	        $( this ).dialog( "close" );
	      }
	    }
	});
	  
	$("#save").button({
		    icons: {
		        primary: "ui-icon-disk"
		    }
	}).click(function() {
		  doSave();
	});
	  
	$("#modify").button({
		    icons: {
		        primary: "ui-icon-pencil"
		    }
	}).click(function() {
		doSave();
	});
	
	$("#cancel").button({
	    icons: {
	        primary: "ui-icon-arrowreturnthick-1-w"
	    }
}).click(function() {
	doSave();
});
});

function doSave() {
	  var news_title = document.getElementById("news_title");
	  var news_type = document.getElementById("news_type");
	  var news_contents = document.getElementById("news_contents");
	  var errorMsg = "";
	  
	  if (news_title.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>제목을 입력해 주세요.</p>";
	  }
	  if (news_type.value == "" || news_type.value == "0") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>종류를 선택해 주세요.</p>";
	  }
	  if (news_contents.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>내용을 입력해 주세요</p>";
	  }
	  
	 if (errorMsg != "") {
		  $( "#dialog-error" ).html(errorMsg);
		  $( "#dialog-error" ).dialog( "open" );
	  } else {
		  
		  if ('<c:out value="${mode}"/>' == 'modify') {
			  $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>이 정보로 수정 하시겠습니까?");
		  } else {
			  $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>이 정보로 등록 하시겠습니까?");
		  }
		 
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

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><a href="<%=request.getContextPath() %>/customer/news/newsList.do"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/news/news_off.png" alt="news" width="186" height="50" border="0"></a></li>
            <li><a href="<%=request.getContextPath() %>/customer/news/eventList.do"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/news/event_off.png" alt="event" width="186" height="50" border="0"></a></li>
        </ul>
    </div>
    
	<div class="form_box">
		<form:form name="f1" commandName="newsEntity" method="post">
			<input type="hidden" name="mode" value="<c:out value='${mode }'/>">
			<form:hidden path="idx"/>
			<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
			<form:hidden path="school_id"/>
			
			<table class="input_school_data">
	    		<tr>
		        	<th valign="top"><font color="red">*</font> 제목</th>
		     		<td><form:input path="news_title" type="text" style="width: 380px;" placeholder=" 제목" /></td>
		        </tr>
		        <tr>
		        	<th valign="top"><font color="red">*</font> 종류</th>
		     		<td>
		        		<input type="hidden" id="news_type" name="news_type" value="1">
		        		뉴스
		     		</td>
		        </tr>  
		        <tr>
			    	<th valign="top"><font color="red">*</font> 내용</th>
			    	<td>
			    		<form:textarea path="news_contents" style="height:200px; width:550px;" placeholder=" 내용" />
			    	</td>
			    </tr>
		    </table>
		</form:form>
		
		<div style="width: 850px; height: 50px; padding-top: 10px; text-align: right; ">
			<c:choose >
  				<c:when test="${mode == 'modify'}">
  					<button id="modify" style="width:100px; height: 30px; font-size: 10px;">수정</button>
  				</c:when>
  				<c:otherwise>
  					<button id="save" style="width:100px; height: 30px; font-size: 10px;">등록</button>
  				</c:otherwise>
  			</c:choose>
  			<button id="cancel" style="width:100px; height: 30px; font-size: 10px;">취소</button>
		</div>
	</div>
	
	<div class="layout_fix"></div>
</section>

<div id="dialog-info" title="정보 메시지"></div>
<div id="dialog-error" title="에러 메시지"></div>
<div id="dialog-confirm" title="확인 메시지"></div>

<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
