<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>Schedule |My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/schedule.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

</head>
<body>
<%-- <jsp:include page="../../common/header.jsp" flush="false" />

<jsp:include page="../navigation.jsp" flush="false">
	<jsp:param value="3" name="selMenu"/>
</jsp:include> --%>

<div class="layout_fix"></div>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/my_schedule_on.png" alt="my_schedule" width="186" height="50" border="0"></li>
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
            <p class="month"><c:out value='${current_year}'/>년 <c:out value='${current_month + 1}'/>월 <c:out value=' ${current_day}'/>일</p>
            <p class="select_schedule"><a href="<%=request.getContextPath() %>/user/schedule/scheduleMonthly.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_month_off.png" alt="month" width="50" height="21" border="0"></a></p>
            <p class="select_schedule"><a href="<%=request.getContextPath() %>/user/schedule/scheduleWeekly.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_week_off.png" alt="week" width="50" height="21" border="0"></a></p>
            <p class="select_schedule"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/schedule/btn_day_on.png" alt="day" width="50" height="21" border="0"></p>
            </div>
            
            <div class="schedule_day">
			<p class="day"><c:out value='${current_month + 1}'/>월 <c:out value='${current_day}'/>일(<c:out value='${current_dayOfWeek}'/>)</p>
            	<table>
                	<tr>
                    	<th rowspan="2">00:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">01:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">02:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">03:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">04:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">05:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    	<tr>
                    	<th rowspan="2">06:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">07:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">08:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">09:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th class="event_date">10:00</th>
                        <td class="event">10:00～11:00<br> 학원</td>
                    </tr>
                    	<tr>
                    	<th rowspan="2">11:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">12:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">13:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">14:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">15:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    	<tr>
                    	<th rowspan="2">16:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">17:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th class="event_date">18:00</th>
                        <td class="event2">18:00～19:00<br> 학원</td>
                    </tr>
                    <tr>
                    	<th class="event_date">19:00</th>
                        <td class="event2">19:00～20:00<br>塾</td>
                    </tr>

                    <tr>
                    	<th rowspan="2">20:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">21:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">22:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">23:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
                    	<td class="center_border">-</td>
                    </tr>
                    <tr>
                    	<th rowspan="2">24:00</th>
                        <td>-</td>
                    </tr>
                    <tr>
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
