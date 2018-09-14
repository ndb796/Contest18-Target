<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/highcharts/highcharts.js"></script>
<script src="<%=request.getContextPath() %>/js/highcharts/modules/exporting.js"></script>

<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: { type: 'column' },
            title: { text: 'Test' },
            subtitle: { text: 'Test sub' },
            xAxis: { categories: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'] },
            yAxis: { min: 0,
                	 title: {
                     text: '사용자'
                   }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr>' + 
                				'<td style="color:{series.color};padding:0">{series.name}: </td>' +
                    		    '<td style="padding:0"><b>{point.y:.1f} mm</b></td>' + 
                    		 '</tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [<c:out value='${series }'/>]
        });
    });
</script>
</head>
<body>

	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</body>
</html>
