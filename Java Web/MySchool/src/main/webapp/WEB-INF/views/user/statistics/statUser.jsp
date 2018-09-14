<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>statistics |My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common_design.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<script src="<%=request.getContextPath() %>/js/highcharts/highcharts.js"></script>
<script src="<%=request.getContextPath() %>/js/highcharts/modules/exporting.js"></script>

<script>
$(function() {
	$( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	    	  var frm = document.f1;
		   	  frm.action = "<%=request.getContextPath() %>/logoutProc.do";
		   	  frm.submit();
	      },
	      "No": function() {
	        $( this ).dialog( "close" );
	      }
	    }
	  });
	  
	$('#container').highcharts({
        chart: { type: 'column' },
        title: { text: '회원 등록수' },
        /* subtitle: { text: 'Test sub' }, */
        xAxis: {
	        	type: 'category',
	            labels: {
	                rotation: -45,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
            },
        yAxis: {
            min: 0,
            title: {
                text: '등록수'
            }
        },
        tooltip: {
        	pointFormat: '등록수: <b>{point.y:.0f} 명</b>'
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [${series }]
    });
});		
</script>
</head>
<body>

<jsp:include page="../../common/header.jsp" flush="false" />

<jsp:include page="../navigation.jsp" flush="false">
	<jsp:param value="4" name="selMenu"/>
</jsp:include>

<div class="layout_fix"></div>

<section class="form_area">

	<div class="tab_area">
    	 <ul>
        	<li><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/statistics/member_on.png" alt="회원 등록수" width="186" height="50" border="0"></li>
            <li><a href="<%=request.getContextPath() %>/user/statistics/statConsult.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/statistics/question_off.png" alt="진로상담수" width="186" height="50" border="0"></a></li>
        </ul>
    
    </div>
	<div class="form_box">
       <p>
       	<%-- <img src="<%=request.getContextPath() %>/images/pages/statistics/graph.png" alt="graph" width="860" height="402"> --%>
       	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
       </p>
	</div>
</section>

<div id="dialog-confirm" title="Confirm dialog"></div>

<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>

