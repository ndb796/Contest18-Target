<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/plugins/timeline.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/ds_admin/css/sb-admin-2.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="<%=request.getContextPath() %>/ds_admin/js/sb-admin-2.js"></script>

<script>
$(function() {
	var container = document.getElementById('sns_messageIn');
	container.scrollTop = container.scrollHeight;	
});

</script>
<body style="background: #d9edf8;">
<div id="sns_messageOut" style="margin-left: 7px; width: 872px; height: 402px; overflow-y: hidden; background: #d9edf8;" >
	<div id="sns_messageIn" class="sns_box" style="margin-left: 10px; width: 850px; border: 1px #ccc solid;">
	 <!-- Page Content -->
        <div class="panel panel-default" style="height: 420px; overflow-y: auto; overflow-x: hidden; ">
            <div class="panel-heading">
                <i class="fa fa-clock-o fa-fw"></i> <c:out value='${aite }'/>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <ul class="timeline">
                	<c:set var="preStatus" value="" />
                	<c:forEach var="con" items="${constList }" varStatus="status">
                		<c:set var="timelineStatus" value="" />
                		<c:choose>
	                		<c:when test="${con.const_status ==  2}">
	                			<li>
		                			<c:if test="${preStatus == '' or preStatus !=  '2'}">
			                			<div class="timeline-badge success"><i class="fa fa-user"></i></div>
			                		</c:if>
			                        
			                        <div class="timeline-panel">
			                            <div class="timeline-heading">
			                                <h4 class="timeline-title">나</h4>
			                                <p><small class="text-muted"><i class="fa fa-clock-o"></i> <c:out value='${con.ins_date_str}'/></small>
			                                </p>
			                            </div>
			                            <div class="timeline-body">
			                                <p><c:out value='${con.contents }'/></p>
			                            </div>
			                        </div>
			                    </li>
	                		</c:when>
	                		<c:otherwise>
	                			<li class="timeline-inverted">
		                			<c:if test="${preStatus == '' or preStatus !=  '3'}">
			                			<div class="timeline-badge info"><i class="fa fa-university"></i></div>
			                		</c:if>
			                        
			                        <div class="timeline-panel">
			                            <div class="timeline-heading">
			                                <h4 class="timeline-title">나</h4>
			                                <p><small class="text-muted"><i class="fa fa-clock-o"></i> <c:out value='${con.ins_date_str}'/></small>
			                                </p>
			                            </div>
			                            <div class="timeline-body">
			                                <p><c:out value='${con.contents }'/></p>
			                            </div>
			                        </div>
			                    </li>
	                		</c:otherwise>
	                	</c:choose>
	                    <c:set var="preStatus" value="<c:out value='${con.const_status}'/>" />
                	</c:forEach>
                </ul>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /#page-wrapper -->
	</div>
</div>
</body>