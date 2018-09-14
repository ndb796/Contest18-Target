<%@ page contentType="text/html;charset=utf-8" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<script>
function doMove(m) {
	var f1 =document.f1;
	f1.action = "<%=request.getContextPath() %>/ds_admin/" + m + ".do";
	f1.menu.value = m;
	
	f1.submit();
}
</script>
<form action="" name="f1" method="post">
	<input type="hidden" name="menu" value="">
</form>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
<jsp:include page="header.jsp"></jsp:include>
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse" style="magin-top: 50px;">
            <ul class="nav" id="side-menu">
            	<li>
                    <a <c:if test="${menu == 'user' }">class="active"</c:if> href="javascript:doMove('user')"><i class="fa fa-user fa-fw"></i> 사용자</a>
                </li>
                <li>
                    <a <c:if test="${menu == 'student' }">class="active"</c:if> href="javascript:doMove('student')"><i class="fa fa-group fa-fw"></i> 학생</a>
                </li>
                <li>
                    <a <c:if test="${menu == 'blog' }">class="active"</c:if> href="javascript:doMove('blog')"><i class="fa fa-comments fa-fw"></i> 블로그</a>
                </li>
                <li>
                    <a <c:if test="${menu == 'consult' }">class="active"</c:if> href="javascript:doMove('consult')"><i class="fa fa-send fa-fw"></i> 상담</a>
                </li>
                <li>
                    <a <c:if test="${menu == 'news' }">class="active"</c:if> href="javascript:doMove('news')"><i class="fa fa-newspaper-o fa-fw"></i> 뉴스/이벤트</a>
                </li>
                <li>
                    <a <c:if test="${menu == 'file' }">class="active"</c:if> href="javascript:doMove('file')"><i class="fa fa-files-o fa-fw"></i> 업로드 파일목록</a>
                </li>
            </ul>
        </div>
    </div>
</nav>