<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>학교정보  | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/school_customer.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false" ></script>
<script type="text/javascript">
$(document).ready(function() {
	var latlng = new google.maps.LatLng(${lat}, ${lng});
	var myOptions = {
			zoom: 12,
			center: latlng,
			mapTypeId: google.maps.MapTypeId.ROADMAP
	}
	var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	var marker = new google.maps.Marker({
		position: latlng,
		map: map
	});
	
	var geocoder = new google.maps.Geocoder();
	
	google.maps.event.addListener(map, "click", function(event) {
		var location = event.latLng;
		geocoder.geocode({
			'latLng': location
		},
		function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				$("#address").html(results[0].formatted_address);
				$("#lat").html(results[0].geometry.location.lat());
				$("#lng").html(results[0].geometry.location.lng());
			} else {
				alert("Geocoder failed due to: " + status);
			}
		});
		if (!marker) {
			marker = new google.maps.Marker({
				position: location,
				map: map
			});
		} else {
			marker.setMap(null);
			marker = new google.maps.Marker({
				position: location,
				map: map
			});
		}
		map.setCenter(location);
	});
	
	$("#address").focusout(function() {
		var address = $(this).val();
		if (address != "") {
			geocoder.geocode({
				'address' : address
			}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					$("#lat").html(results[0].geometry.location.lat());
					$("#lng").html(results[0].geometry.location.lng());
					map.setCenter(results[0].geometry.location);
					if (!marker) {
						marker = new google.maps.Market({
							position: results[0].getmetry.location,
							map: map
						});
					} else {
						marker.setMap(null);
						marker = new google.maps.Market({
							position: results[0].getmetry.location,
							map: map
						});
					}
				} else {
					alert("Geocoder failed to due to: " + status);
				}
			});
		}
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
	    	<span class="school_picture"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/school_view1.png" alt="school_view1" width="249" height="190"></span>
			<h1><c:out value='${schoolEntity.school_name }'/></h1>
	        <h2><c:out value='${schoolEntity.title1 }'/></h2>
	        <p><c:out value='${schoolEntity.contents }'/></p>
	    </div>
	    
	    <div class="layout_fix"></div>
	    
	    <h3><c:out value='${schoolEntity.school_name }'/>의 특징</h3>
	    <div class="school_guide">
	    	<p><c:out value='${schoolEntity.features }'/></p>
	    	<span class="subpic"><img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/school_view2.png" alt="school_view2" width="179" height="136"></span>
	    </div>
	    
	    <h3><c:out value='${schoolEntity.school_name }'/>의 학부/학과/과정</h3>
	    
	    <table cellspacing="0" cellpadding="0">
	    	<tr>
	        	<th><span class="subject_list">●</span>외국어학부</th>
	     		<td><span class="subject01">【영어어학부】</span>
	            	【아시아언어학부】<br>
	                <span class="course">중국어전공｜한국어전공｜인도네시아어전공｜베트남어전공｜태국어전공</span></td>
	        </tr>
	        <tr>
	        	<th><span class="subject_list">●</span>외국어학부</th>
	     		<td>【아시아언어학부】<br>
	                <span class="course">중국어전공｜한국어전공｜인도네시아어전공｜베트남어전공｜태국어전공</span></td>
	        </tr>
	        <tr>
	        	<th class="subject_end"><span class="subject_list">●</span>외국어학부</th>
	     		<td class="subject_end">【아시아언어학부】<br>
	                <span class="course">중국어전공｜한국어전공｜인도네시아어전공｜베트남어전공｜태국어전공</span></td>
	        </tr>
	    </table>
	    
	    <h3>상담문의/주소/교통</h3>
	    <div class="school_info">
	    	<h4>상담문의</h4>
	        <p><c:out value='${schoolEntity.chr_dept }'/> TEL:<c:out value='${schoolEntity.chr_tel }'/></p>
	        <h4>주소</h4>
	        <p><c:out value='${schoolEntity.zipcode }'/> <c:out value='${schoolEntity.address1 }'/> <c:out value='${schoolEntity.address2 }'/></p>
	        <h4>교통</h4>
	        <ul>
	        	<li><c:out value='${schoolEntity.access }'/></li>
	        </ul>
	    </div>  
		<!-- <span class="map"> -->
	   		<%-- <img src="<%=request.getContextPath() %>/images/locale/ja/customer/pages/school/map.png" alt="map" width="405" height="328"> --%>
	   		<div id="map_canvas" style="width: 405px; height: 328px; background-color: #cccccc; border: #cccccc 1px solid; float:left; display:block;"></div>
   			<span id="lat"></span>
   			<span id="lng"></span>
   			<span id="address"></span>
		<!-- </span> -->
	      
		<div class="layout_fix02"></div>  
	</div>
</section>

<div id="dialog-confirm" title="Confirm dialog"></div>
	
<footer>
    <jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
