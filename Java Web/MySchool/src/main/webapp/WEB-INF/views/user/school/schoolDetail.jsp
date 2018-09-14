<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mycompany.myschool.web.util.Token" %>
<%
if(request.getAttribute("token_key") == null) {
	Token.set(request);
}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title><c:out value='${schoolEntity.name }'/> | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/school.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false" ></script>
<script src="<%=request.getContextPath() %>/js/googlemap.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var geocoder = new google.maps.Geocoder();
	var address = "서울특별시 중구 명동 세종대로 110";
	var lat = 0.0;
	var lng = 0.0;
	geocoder.geocode( { 'address': address}, function(results, status) {
	      if (status == google.maps.GeocoderStatus.OK) {
	    	  lat = results[0].geometry.location.lat();
	    	  lng = results[0].geometry.location.lng();
	      } else {
	    	  address = '경기도 남양주시 다산순환로 333';
	    	  lat = '37.5662952';
	    	  lng = '126.97794509999994';
	      }
	      
	      initialize(lat, lng, address);
	});
});
$(function() {
  $("#add").button({
	    icons: {
	        primary: "ui-icon-plus"
	    }
  }).click(function() {
	  var frm = document.fabrFrm;
	  frm.action = "<%=request.getContextPath() %>/user/favorite/favrAdd.do";
	  frm.submit();
  });
  
  $("#remove").button({
	    icons: {
	        primary: "ui-icon-minus"
	    }
}).click(function() {
	  var frm = document.fabrFrm;
	  frm.action = "<%=request.getContextPath() %>/user/favorite/favrRemove.do";
	  frm.submit();
});
});
</script>

</head>
<body>

<jsp:include page="../../common/header.jsp" />

<jsp:include page="../navigation.jsp" >
	<jsp:param value="2" name="selMenu"/>
</jsp:include>

<div class="layout_fix"></div>

<form name="fabrFrm" action="" method="post">
	<input type="hidden" name="token_key" value="<%=request.getAttribute("token_key") %>" />
	<input type="hidden" name="student_id" value="<c:out value='${userEntity.user_id }'/>">
	<input type="hidden" name="sch_id" value="<c:out value='${schoolEntity.sch_id }'/>">
	<input type="hidden" name="sb_type" value="<c:out value='${schoolEntity.sb_type }'/>">
</form>

<div class="layout_fix"></div>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><a href="javascript:selMenu(2)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/school/school_list_off.png" alt="school_list" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(21)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/school/recommended_school_off.png" alt="recommended_school" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(22)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/school/favorites_school_off.png" alt="favorites_school" width="186" height="50" border="0"></a></li>
        </ul>
    </div>
    
	<div class="form_box">
		<div style="width: 850px; height: 50px; padding-top: 10px; text-align: right; ">
			<c:if test="${is_favorite != 'yes' }">
   				<button id="add" style="width:130px; height: 30px; font-size: 10px;">즐겨찾기에 추가</button>
   			</c:if>
   			<c:if test="${is_favorite == 'yes' }">
   				<button id="remove" style="width:130px; height: 30px; font-size: 10px;">즐겨찾기에서 삭제</button>
   			</c:if>
		</div>
		
		<div class="layout_fix"></div>
	    <div class="school_promotion">
	    	<span class="school_picture"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/school/school_view1.png" alt="school_view1" width="249" height="190"></span>
			<h1><c:out value='${schoolEntity.name }'/></h1>
	        <h2><c:if test="${schoolEntity.school_copy != null }"><c:out value='${schoolEntity.school_copy}'/></c:if></h2>
	        <p></p>
	    </div>
	    
	    <div class="layout_fix"></div>
	    
	    <h3>특징</h3>
	    <div class="school_guide">
	    	<p></p>
	    	<span class="subpic"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/school/school_view2.png" alt="school_view2" width="179" height="136"></span>
	    	<p><c:if test="${schoolEntity.history != null }"><c:out value='${schoolEntity.history}'/></c:if></p>
	    </div>
	    
	    <h3>학부/학과/코스</h3>
	    
	    <table cellspacing="0" cellpadding="0">
	    <c:forEach var="course" items="${branchCoursesList }">
	    	<tr>
	        	<th><span class="subject_list">●</span><c:out value='${course.curs_title }'/></th>
	     		<td><span class="subject01"><c:out value='${course.curs_contents1 }'/></span>
	                <span class="course"><c:out value='${course.curs_contents2 }'/></span></td>
	        </tr>
	    </c:forEach>
	    </table>
	    
	    <h3>상담문의/주소/교통</h3>
	    <div class="school_info">
	    	<h4>상담문의</h4>
	        <p><c:out value='${schoolEntity.chr_dept }'/> TEL:<c:out value='${schoolEntity.chr_tel }'/></p>
	        <h4>주소</h4>
	        <p><c:out value='${schoolEntity.zipcode }'/> <c:out value='${schoolEntity.address }'/></p>
	        <h4>찾아오는길</h4>
	        <ul>
	        	<li><c:if test="${schoolEntity.station != null }"><c:out value='${schoolEntity.station}'/></c:if></li>
	        </ul>
	    </div>  
	   		<div id="map_basic" style="width: 405px; height: 328px; background-color: #cccccc; border: #cccccc 1px solid; float:left; display:block;"></div>
   			<span id="lat"></span>
   			<span id="lng"></span>
   			<span id="address"></span>
	      
		<div class="layout_fix02"></div>  
	</div>
</section>

<div id="dialog-error" title="에러 메시지">
</div>

<div id="dialog-confirm" title="확인 메시지">
</div>

<footer>
    <jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
