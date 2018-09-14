<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>Schedule |My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/schedule.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

</head>
<body>
<jsp:include page="../../common/header.jsp" flush="false" />

<jsp:include page="../navigation.jsp" flush="false">
	<jsp:param value="3" name="selMenu"/>
</jsp:include>

<div class="layout_fix"></div>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/my_schedule_on.png" alt="my_schedule" width="186" height="50"></li>
            <li><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/recommended_off.png" alt="recommended_school"
            width="186" height="50" border="0"></a></li>
            <li><a href=""><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/consult_off.png" alt="consult" width="186" height="50" border="0"></a></li>
        </ul>
    
    </div>
	<div class="form_box">
    	<div class="schedule_cover">
        <section class="calendar">
            <div class="schedule_btn">
            <p><a href="<%=request.getContextPath() %>/user/schedule/scheduleDaily.do?curYear=<c:out value='${current_year }'/>&curMonth=<c:out value='${current_month}'/>&curDay=<c:out value='${current_day - 1}'/>"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_back.png" alt="back" width="22" height="25" border="0"></a></p>
            <p><a href="<%=request.getContextPath() %>/user/schedule/scheduleDaily.do?curYear=<c:out value='${current_year }'/>&curMonth=<c:out value='${current_month}'/>&curDay=<c:out value='${current_day + 1}'/>"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_next.png" alt="next" width="22" height="25" border="0"></a></p>
            <p class="month"><c:out value='${current_year}'/>년 <c:out value='${current_month + 1}'/>월 </p>
            <p class="select_schedule"><a href="<%=request.getContextPath() %>/user/schedule/scheduleMonthly.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_month_off.png" alt="month" width="50" height="21" border="0"></a></p>
            <p class="select_schedule"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_week_on.png" alt="week" width="50" height="21"></p>
            <p class="select_schedule"><a href="<%=request.getContextPath() %>/user/schedule/scheduleDaily.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_day_off.png" alt="day" width="50" height="21" border="0"></a></p>
            </div>
            
            <div class="schedule_day">
        	<table>
        		<tr>
                	<th class="week"></th>
                	<c:forEach var="day" items="${weekly_days }" varStatus="status">
                		<c:if test="${status.count % 7 == 1}">
                		<th class="weekend"><c:out value='${current_month + 1}'/>/<c:out value='${day }'/>(<c:out value='${weekdays[status.index] }'/>)</th>
                		</c:if>
                		<c:if test="${status.count % 7 != 1}">
                		<th class="week"><c:out value='${current_month + 1}'/>/<c:out value='${day }'/>(<c:out value='${weekdays[status.index] }'/>)</th>
                		</c:if>
                	</c:forEach>
                </tr>
        
            	<tr>
                	<th rowspan="2">00:00</th>
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td rowspan="2" class="event">10:00～11:00</td>
                    <td>e</td>
                    <td>f</td>
                    <td>g</td>
                </tr>
                <tr>
                	<td class="center_border">h</td>
                    <td class="center_border">i</td>
                    <td class="center_border">j</td>
                    <td class="center_border">k</td>
                    <td class="center_border">l</td>
                    <td class="center_border">n</td>
                </tr>
                <tr>
                	<th rowspan="2">01:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                <tr>
                	<th rowspan="2">02:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                <tr>
                	<th rowspan="2">03:00</th>
                    <td>a</td>
                    <td>b</td>
                    <td>c</td>
                    <td rowspan="4" class="event2">3:00～5:00</td>
                    <td>e</td>
                    <td>f</td>
                    <td>g</td>
                </tr>
                <tr>
                	<td class="center_border">h</td>
                    <td class="center_border">i</td>
                    <td class="center_border">j</td>
                    <td class="center_border">k</td>
                    <td class="center_border">l</td>
                    <td class="center_border">n</td>
                    
                </tr>
                <tr>
                	<th rowspan="2">04:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                  
                </tr>
                <tr>
                	<th rowspan="2">05:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                <tr>
                	<th rowspan="2">06:00</th>
                    <td>-</td>
                    <td rowspan="6" class="event6">6:00～9:00<br> 학원</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    
                </tr>
                <tr>
                	<th rowspan="2">07:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                <tr>
                	<th rowspan="2">08:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td> 
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>

                </tr>
                <tr>
                	<th rowspan="2">09:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                <tr>
                	<th rowspan="2">10:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                <tr>
                	<th rowspan="2">11:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">12:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">13:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">14:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">15:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">16:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">17:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">18:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">19:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">20:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">21:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">22:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
                                    <tr>
                	<th rowspan="2">23:00</th>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr>
                	<td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                    <td class="center_border">-</td>
                </tr>
            </table>    
            </div>
		</section>
		</div>

    	<div class="layout_fix02"></div>  
	</div>
</section>

<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
