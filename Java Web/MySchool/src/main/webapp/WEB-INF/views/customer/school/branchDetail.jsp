<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title><c:out value='${branchEntity.sb_name }'/> | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/school_customer.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false" ></script>
<script src="<%=request.getContextPath() %>/js/googlemap.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var geocoder = new google.maps.Geocoder();
	var address = "<c:out value='${branchEntity.sb_address1 }'/><c:out value='${branchEntity.sb_address2 }'/><c:out value='${branchEntity.sb_address3 }'/>";
	var lat = 0.0;
	var lng = 0.0;
	geocoder.geocode( { 'address': address}, function(results, status) {
	      if (status == google.maps.GeocoderStatus.OK) {
	    	  lat = results[0].geometry.location.lat();
	    	  lng = results[0].geometry.location.lng();
	      } else {
	    	  address = '서울시 종로구';
	    	  lat = '35.6203974';
	    	  lng = '139.745004';
	      }
	      
	      initialize(lat, lng, address);
	});
  
	$( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 400,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	    	  var frm = document.branchFrm;
	    	  frm.action = "/customer/school/branchModifyForm.do";
	    	  frm.submit();
	      },
	      "No": function() {
	          $( this ).dialog( "close" );
	      }
	    }
	  });
	
  $("#modify").button({
	    icons: {
	        primary: "ui-icon-pencil"
	    }
  }).click(function() {
	  $( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>수정페이지로 이동하시겠습니까?");
	  $( "#dialog-confirm" ).dialog( "open" );
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

<section class="main_contents">
    <div class="breadpath"></div>
</section>

<div class="layout_fix"></div>

<form name="branchFrm" action="" method="post">
	<input type="hidden" name="school_id" value="<c:out value='${branchEntity.school_id }'/>">
	<input type="hidden" name="sb_id" value="<c:out value='${branchEntity.sb_id }'/>">
</form>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><a href="javascript:selMenu(2)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/school_list_off.png" alt="school_list" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(21)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/recommended_school_off.png" alt="recommended_school" width="186" height="50" border="0"></a></li>
            <li><a href="javascript:selMenu(22)"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/favorites_school_off.png" alt="favorites_school" width="186" height="50" border="0"></a></li>
        </ul>
    </div>
    
	<div class="form_box">
	    <div class="school_promotion">
	    	<div style="width: 850px; height: 50px; padding-bottom: 10px; text-align: right; ">
	   			<button id="modify" style="width:130px; height: 30px; font-size: 10px;">학교정보수정</button>
	   			<!-- <button id="list" style="width:130px; height: 30px; font-size: 10px;">학교リスト</button> -->
   			</div>
   			
	    	<c:choose>
	    		<c:when test="${branchEntity.photo1_name != null and branchEntity.photo1_name != ''}">
	    			<c:set var="photo1" value="/images/upload/school/${branchEntity.school_id }/${branchEntity.photo1_name }" />
	    		</c:when>
	    		<c:otherwise>
	    			<c:set var="photo1" value="/images/locale/ja/customer/pages/school/school_view1.png" />
	    		</c:otherwise>
	    	</c:choose>
	    	
	    	<span class="school_picture">
	    		<img src="<c:out value='${photo1 }'/>" alt="school_view1" width="249" height="190">
	    	</span>
			<h1><c:out value='${branchEntity.sb_name }'/></h1>
	        <h2><c:out value='${branchEntity.sb_title1 }'/></h2>
	        <p><c:out value='${branchEntity.sb_contents }'/></p>
	    </div>
	    
	    <div class="layout_fix"></div>
	    
	    <h3>특징</h3>
	    <div class="school_guide">
	    	<p><c:out value='${branchEntity.sb_features }'/></p>
	    	<c:choose>
	    		<c:when test="${branchEntity.photo2_name != null and branchEntity.photo2_name != ''}">
	    			<c:set var="photo2" value="/images/upload/school/${branchEntity.school_id }/${branchEntity.photo2_name }" />
	    		</c:when>
	    		<c:otherwise>
	    			<c:set var="photo2" value="/images/locale/ja/customer/pages/school/school_view2.png" />
	    		</c:otherwise>
	    	</c:choose>
	    	<span class="subpic">
	    		<img src="<c:out value='${photo2 }'/>" alt="school_view2" width="179" height="136">
	    	</span>
	    </div>
	    
	    <h3>학교방침</h3>
	    <div class="">
	    	<table style="width: 800px;">
	        	<tr style="">
	        		<td style="border-bottom-style: none;"><p><c:out value='${branchEntity.sb_policy }'/></p></td>
	        	</tr>
	        </table>
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
	    
	    <h3>키워드</h3>
	    <div class="">
	        <table style="width: 800px;">
	        	<tr style="">
	        		<td style="border-bottom-style: none;">
	        			<c:forEach items="${customer_key }" var="keyword">
	        				<li><c:out value='${keyword.keyword }'/>
					    </c:forEach>
	        		</td>
		    	</tr>
		    </table>	
	    </div>  
	    
	    <h3>상담문의/주소/교통</h3>
	    <div class="school_info">
	    	<h4>상담문의</h4>
	        <p><c:out value='${branchEntity.sb_dept }'/> TEL:<c:out value='${branchEntity.sb_tel }'/></p>
	        <h4>우편번호</h4>
	        <p><c:out value='${branchEntity.sb_zipcode }'/></p>
	        <h4>주소</h4>
	        <p><c:out value='${branchEntity.sb_address1 }'/> <c:out value='${branchEntity.sb_address2 }'/><br/>
	        	<c:out value='${branchEntity.sb_address3 }'/> <c:out value='${branchEntity.sb_address4 }'/></p>
	        
	        <h4>교통</h4>
	        <ul>
	        	<li><c:out value='${branchEntity.sb_access }'/></li>
	        </ul>
	    </div>  
		<!-- <span class="map"> -->
	   		<%-- <img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/map.png" alt="map" width="405" height="328"> --%>
	   		<div id="map_basic" style="width: 405px; height: 328px; background-color: #cccccc; border: #cccccc 1px solid; float:left; display:block;"></div>
   			<span id="lat"></span>
   			<span id="lng"></span>
   			<span id="address"></span>
		<!-- </span> -->
	      
		<div class="layout_fix02"></div>  
	</div>
</section>

<div id="dialog-confirm" title="확인 메시지"></div>
	
<footer>
    <jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
