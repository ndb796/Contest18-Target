<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%@ page import="com.mycompany.myschool.web.util.Util" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
<title>학교등록 | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/school_customer.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<script type="text/javascript">
window.onload = function () {
	var resultMsg = "<%=resultMsg%>";
	if (resultMsg != null && resultMsg.length > 0) {
		$( "#dialog-info" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>" + resultMsg);
		$( "#dialog-info" ).dialog( "open" );
	}
};

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
	   	   	   <% if (request.getAttribute("mode").equals("view")) { %>
		   	 	frm.action = "<%=request.getContextPath() %>/customer/school/branchModifyProc.do";
		   	   <% } else { %>
		   			frm.action = "<%=request.getContextPath() %>/customer/school/branchAddProc.do";
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
});		

function doSave() {
	  var sb_name = document.getElementById("sb_name");
	  var sb_type = document.getElementById("sb_type");
	  var sb_zipcode = document.getElementById("sb_zipcode");
	  var sb_address1 = document.getElementById("sb_address1");
	  var errorMsg = "";
	  
	  if (sb_type.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>학교 종류를 선택해 주세요.</p>";
	  }
	  if (sb_name.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>학교명을 입력해 주세요.</p>";
	  }
	  if (sb_zipcode.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>우편번호를 입력해 주세요.</p>";
	  }
	  if (sb_address1.value == "") {
		  errorMsg += "<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>주소를 입력해 주세요.</p>";
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

var curs_ord = ${fn:length(branchCoursesList)};
function insertRow() {
  var oTbl = document.getElementById("addTable");
  var oRow = oTbl.insertRow();
  
  //삽입될 Form Tag
  curs_ord = curs_ord + 1;
  var frmTag = "<tr style='width: 830px; height: 30px;'>";
  frmTag += "<td style='width: 300px; vertical-align: top;'>";
  frmTag += "<input type='text' name='curs_title_" + curs_ord + "' style='width: 280px;' placeholder=' 학교/학과'>";
  frmTag += "</td>";
  frmTag += "<td style='width: 530px;'>";
  frmTag += "<textarea name='curs_contents_" + curs_ord + "' style='height:50px; width:510px;' placeholder=' 내용'></textarea>";
  frmTag += "</td>";
  frmTag += "</tr>";
  
  oRow.innerHTML = frmTag;
}

//Row 삭제 - 마지막 Row 삭제
function removeRow() {
	if ($('#addTable tr').length > 2) {
		var oTbl = document.getElementById("addTable");
  		oTbl.deleteRow($('#addTable tr').length - 1);
	}
}

function frmCheck() {
  var frm = document.form;
  
  for( var i = 0; i <= frm.elements.length - 1; i++ ) {
     if( frm.elements[i].name == "addText" ) {
         if( !frm.elements[i].value ){
             alert("텍스트박스에 값을 입력하세요!");
             frm.elements[i].focus();
			 return;
          }
      }
   }
 }

var xhr;
function createXhr(){
    if(window.ActiveXObject){   // IE 이전버전
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }else{
        xhr = new XMLHttpRequest();
    }
}
 
function zipcodecheck(){
    var zipcode = document.getElementById("sb_zipcode").value;
    var queryString = "zipcode="+zipcode;
    if(zipcode.length<6){
        document.getElementById("idcheckLayer").innerHTML = "<font color=red>7자리 이상 입력하세요.</font>";     
    }else{
        // 1. XMLHttpReqeust 객체 생성
        createXhr();
        // 2. 이벤트 핸들러 등록
        xhr.onreadystatechange = callback;  // callback 함수를 등록
        // 3. open()를 통해 요청관련 설정을 설정
        xhr.open("POST", "<%=request.getContextPath()%>/common/findByZipcode.do", true);
        // 4. Header에 contentType 지정 - post
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        // 5. send()를 통해 요청
        xhr.send(queryString);  // 요청 쿼리를 보내준다.
    }
}
function callback(){
    if(xhr.readyState==4){      // 응답을 다 받은 경우
        if(xhr.status == 200){  // 응답코드가 200인 경우 - 정상인 경우
            var resTxt = xhr.responseText;  // 서버가 보낸 응답 text
            var info = resTxt.split("|");
            
            if (info[0] == "ERRCODE") {
            	document.getElementById("sb_address1").value = info[1];
            } else {
            	document.getElementById("sb_address1").value = info[0];
            	document.getElementById("sb_address2").value = info[1];
            	document.getElementById("sb_address3").value = info[2];
            	document.getElementById("sb_address4").focus();
            }
             
        }else{
            alert("요청 처리가 정상적으로 되지 않았습니다.\n"+xhr.status);
        }
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

<section class="main_contents">
    <div class="breadpath"></div>
</section>

<div class="layout_fix"></div>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><a href="javascript:selMenu(2)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/school_list_off.png" alt="school_list" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(21)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/recommended_school_off.png" alt="recommended_school" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(22)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/favorites_school_off.png" alt="favorites_school" width="186" height="50" border="0"></a></li>
        </ul>
    </div>
    
	<div class="form_box">
		<form:form name="f1" commandName="branchEntity" method="post" enctype="multipart/form-data">
		<input type="hidden" name="mode" value="<c:out value='${mode }'/>">
		<form:hidden path="sb_id"/>
		<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
		<form:hidden path="school_id"/>
		
	    <div class="school_promotion">
	    	<span class="school_picture">
	    		<c:choose>
	    			 <c:when test="${mode == 'modify'}">
	    			 	<c:choose>
	    					<c:when test="${branchEntity.photo1_name != null and branchEntity.photo1_name != ''}">
	    					<img src="<%=request.getContextPath() %>/images/upload/school/<c:out value='${branchEntity.school_id }'/>/<c:out value='${branchEntity.photo1_name}'/>" alt="school_view1" width="249" height="190">
	    					</c:when>
	    					<c:otherwise>
	    						<img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/school_view1.png" alt="school_view1" width="249" height="190">
	    					</c:otherwise>
	    				</c:choose>
	    			</c:when>
	    			<c:otherwise>
	    				<img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/school_view1.png" alt="school_view1" width="249" height="190">
	    			</c:otherwise>
	    		</c:choose>
	    	</span>
	    	
	    	<table style="padding-left: 20px; width: 580px;">
		    	<tr style="width: 580px;">
		        	<td style="width: 180px;">학교의 메인 이미지</td>
		     		<td style="width: 400px;"><form:input path="photo1_file" type="file" size="200px"/></td>
		        </tr>
		        <tr style="width: 580px;">
		        	<td style="width: 180px;"><font color="red">*</font> 학교 종류</td>
		     		<td style="width: 400px;">
		     			<form:select path="sb_type">  
				        	<form:options items="${school_type_map }" />
		        		</form:select>
		     		</td>
		        </tr>
		        <tr style="width: 580px;">
		        	<td style="width: 180px;"><font color="red">*</font> 학교명</td>
		     		<td style="width: 400px;"><form:input path="sb_name" type="text" style="width: 380px;" placeholder=" 학교명" /></td>
		        </tr>
		        <tr style="width: 580px;">
		        	<td style="width: 180px;">캐치프레이즈</td>
		     		<td style="width: 400px;"><form:input path="sb_title1" type="text" style="width: 380px;" placeholder=" 캐치프레이즈" /></td>
		        </tr>
		        <tr style="width: 580px;">
		        	<td style="width: 180px; vertical-align: top;">요약설명</td>
		     		<td style="width: 400px;"><form:textarea path="sb_contents" style="height:50px; width:380px;" placeholder=" 요약설명" /></td>
		        </tr>
		    </table>
	    	
	    </div>
	    
	    <div class="layout_fix"></div>
	    
	    <h3>특징</h3>
	    <div class="school_guide">
	    	<span class="school_picture"><!-- <span class="subpic"> -->
	    		<c:choose>
	    			 <c:when test="${mode == 'modify'}">
	    			 	<c:choose>
	    					<c:when test="${branchEntity.photo1_name != null and branchEntity.photo1_name != ''}">
	    					<img src="<%=request.getContextPath() %>/images/upload/school/<c:out value='${branchEntity.school_id }'/>/<c:out value='${branchEntity.photo2_name}'/>" alt="school_view2" width="179" height="136">
	    					</c:when>
	    					<c:otherwise>
	    						<img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/school_view2.png" alt="school_view2" width="179" height="136">
	    					</c:otherwise>
	    				</c:choose>
	    			</c:when>
	    			<c:otherwise>
	    				<img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/school_view2.png" alt="school_view2" width="179" height="136">
	    			</c:otherwise>
	    		</c:choose>
	    	</span>
	    	<table style="padding-left: 20px; width: 650px;">
	    		<tr style="width: 650px;">
		        	<td style="width: 180px;">학교 서브 이미지</td>
		     		<td style="width: 470px;"><form:input path="photo2_file" type="file" size="270px"/></td>
		        </tr>
		    	<tr style="width: 650px;">
		        	<td style="width: 180px; vertical-align: top;">특징</td>
		     		<td style="width: 470px;"><form:textarea path="sb_features" style="height:50px; width:450px;" placeholder=" 특징" /></td>
		        </tr>
		        <tr style="width: 650px;">
		        	<td style="width: 180px; vertical-align: top;">학교방침</td>
		     		<td style="width: 470px;"><form:textarea path="sb_policy" style="height:50px; width:450px;" placeholder=" 학교방침" /></td>
		        </tr>
		    </table>		   
	    </div>
	    
	    <h3>학교/학과/과정</h3>
	    <div style="width: 830px; height: 30px; padding-bottom: 10px; text-align: right; ">
   			<a href="javascript:insertRow();">추가</a>
   			<a href="javascript:removeRow();">삭제</a>
  		</div>
	    <table id="addTable" style="width: 830px;">
	    	<tr style="width: 830px; height: 30px;">
	        	<th style="width: 300px;">학교/학과</th>
	     		<th style="width: 530px;">내용</th>
	        </tr>
	        <c:forEach var="course" items="${branchCoursesList }">
		    	<tr style="width: 830px; height: 30px;">
		        	<td style="width: 300px; vertical-align: top;"><input type="text" name="curs_title_${course.curs_ord }" style="width: 280px;" placeholder=" 학부/학과" value="<c:out value='${course.curs_title }'/>"></td>
		     		<td style="width: 530px;">
		     			<textarea name="curs_contents_${course.curs_ord }" style="height:50px; width:510px;" placeholder=" 내용"><c:out value='${course.curs_contents1 }'/></textarea>
		     		</td>
		        </tr>
		    </c:forEach>
	    </table>
	    
	    <h3>키워드</h3>
	    <div class="">
	        <table style="width: 800px;">
	        	<tr style="">
			        <c:set var="cnt" value="0" />
			        <c:forEach items="${code_mst }" var="code">
			        	<c:set var="cnt" value="${cnt + 1}" />
				        	<td style="border-bottom-style: none;"><input type="checkbox" name="keywords" value="<c:out value='${code.code }'/>" <c:if test="${! empty customer_key[code.code]}">checked</c:if>> <c:out value='${code.code_str }'/></td>
				        	<c:if test="${cnt % 5 == 0 }">
				        		</tr><tr>
				        	</c:if>
				    </c:forEach>
		    	</tr>
		    </table>	
	    </div>  
	    
	    <h3>상담문의/주소/연락처</h3>
	    <div class="school_info">
	    	<h4>상담문의</h4>
	    	<table style="width: 800px;">
		    	<tr style="width: 800px;">
		        	<td style="width: 150px; vertical-align: top;">담당부서</td>
		     		<td style="width: 650px;"><form:input path="sb_dept" type="text" style="width: 630px;" placeholder=" 담당부서" /></td>
		        </tr>
		        <tr style="width: 800px;">
		        	<td style="width: 150px; vertical-align: top;">TEL</td>
		     		<td style="width: 650px;"><form:input path="sb_tel" type="text" style="width: 200px;" placeholder=" TEL" /></td>
		        </tr>
		        <tr style="width: 800px;">
		        	<td style="width: 150px; vertical-align: top;">FAX</td>
		     		<td style="width: 650px;"><form:input path="sb_fax" type="text" style="width: 200px;" placeholder=" FAX" /></td>
		        </tr>
		    </table>	
	        <h4>주소</h4>
	        <table style="width: 800px;">
		    	<tr style="width: 800px;">
		        	<td style="width: 150px; vertical-align: top;"><font color="red">*</font> 우편번호</td>
		     		<td style="width: 650px;">
		     			<form:input path="sb_zipcode" type="text" style="width: 200px;" placeholder=" 우편번호" /> 
		     			<font color="grey"> (숫자만) </font>
		     			<!-- <a href="javascript:zipcodecheck()">검색</a> -->
		     		</td>
		        </tr>
		        <tr style="width: 800px;">
		        	<td style="width: 150px; vertical-align: top;"><font color="red">*</font> 주소1 (도)）</td>
		     		<td style="width: 650px;"><form:input path="sb_address1" type="text" style="width: 630px;" placeholder=" 도" /></td>
		        </tr>
		        <tr style="width: 800px;">
		        	<td style="width: 150px; vertical-align: top;">주소2 (시/군)</td>
		     		<td style="width: 650px;"><form:input path="sb_address2" type="text" style="width: 630px;" placeholder=" 시/군" /></td>
		        </tr>
		        <tr style="width: 800px;">
		        	<td style="width: 150px; vertical-align: top;">주소3 (동)</td>
		     		<td style="width: 650px;"><form:input path="sb_address3" type="text" style="width: 630px;" placeholder=" 동" /></td>
		        </tr>
		        <tr style="width: 800px;">
		        	<td style="width: 150px; vertical-align: top;">주소4 (건물)</td>
		     		<td style="width: 650px;"><form:input path="sb_address4" type="text" style="width: 630px;" placeholder=" 건물" /></td>
		        </tr>
		    </table>	
	        <h4>교통</h4>
	        <table style="width: 800px;">
		    	<tr style="width: 800px;">
		        	<td style="width: 800px;"><form:textarea path="sb_access" style="height:70px; width:780px;" placeholder=" 교통" /></td>
		        </tr>
		    </table>	
	    </div>  
	      
		<div class="layout_fix02"></div>  
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
