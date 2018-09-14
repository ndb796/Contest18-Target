<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.ResultCode" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<!-- <link href="css/admin.css" rel="stylesheet" type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/index.css" />
<link rel="stylesheet" href="css/jquery.mobile-1.4.2.min.css" />
<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}

.ui-content {
    padding: 0 !important;
}
 
.ui-listview {
    margin: 0 !important;
}
 
.example-wrapper, .example-wrapper div.iscroll-scroller {
    width: 100% !important;
}
</style>
<script type="text/javascript">
function doSubmit() {
	var frm = document.getElementById("f1");
	
	frm.submit();
}
</script>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery.mobile-1.4.2.min.js"></script>
</head>

<body>
<div style="position:absolute; top: 20px; left:20px; width:960px; height:600px; ">
<input type="button" value="Write" data-inline="true" onclick="doSubmit();"/>
<br/><br/>

<form action="boardAdd.html" id="f1" name="f1">
<input type="hidden" name="user_id" value="<c:out value='${user_id }'/>">
<input type="hidden" name="group_id" value="<c:out value='${group_id }'/>">
</form>

<div style="width:960px; height:400px; text-align:center; overflow-y:auto; border: 1px solid #c3c3c3;">	
<table style="width:100%; text-align:center; border: 0px;">
	<c:forEach var="entity" items="${data }">
	<tr>
		<td>
			<table style="width: 100%; text-align: left; board: 0px;">
				<tr>
					<th rowspan="2" style="width: 50px; text-align: left;"><b>No</b>: <c:out value='${entity.no }'/></th>
					<td style="text-align: left;"><c:out value='${entity.user_id }'/></td>
				</tr>
				<tr>
					<td style="text-align: left;"><fmt:formatDate value="${entity.upd_date }" pattern="MM/dd/yyyy HH:mm:ss"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">
						<c:out value='${entity.contents }'/>
						<p>
							<c:out value='${entity.emotionEntity.emotion_h }'/>,
							<c:out value='${entity.emotionEntity.emotion_f }'/>, 
							<c:out value='${entity.emotionEntity.emotion_k }'/>, 
							<c:out value='${entity.emotionEntity.emotion_n }'/>, 
							<c:out value='${entity.emotionEntity.emotion_s }'/>  
						</p>
					</td>
				</tr>
				<!-- comment part -->
				<c:if test="${fn:length(entity.commentList) != 0}">
				<tr>
					<td colspan="2">
						<table style="width: 90%; text-align: right; board: 0px;">
						<c:forEach var="comment" items="${entity.commentList }">
							<tr>
								<td style="text-align: left;">
									<b><c:out value='${comment.user_id }'/></b> <c:out value='${comment.comments }'/>
									<br/>
									<fmt:formatDate value="${comment.upd_date }" pattern="MM/dd/yyyy HH:mm:ss"/>
								</td>
							</tr>
						</c:forEach>
						</table>
					</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="2">
						<form action="commentAddProc.html" id="f2" name="f2" method="POST">
							<input type="hidden" name="user_id" value="<c:out value='${user_id }'/>">
							<input type="hidden" name="group_id" value="<c:out value='${group_id }'/>">
							<input type="hidden" name="board_id" value="<c:out value='${entity.board_id }'/>">
							Comments: 
							<textarea name="comments" style="width:510px; height:50px;"></textarea> 
							<input type="submit" value="Submit">
						</form>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</c:forEach>
</table>	
</div>
</div>
</body>
</html>
