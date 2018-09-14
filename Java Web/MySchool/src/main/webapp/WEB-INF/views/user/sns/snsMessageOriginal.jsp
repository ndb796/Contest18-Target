<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>SNS Message | My School</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/sns_message2.css" >

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/smoothness/jquery-ui-1.10.4.custom.css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/image_ov.js"></script>

<!-- jqGrid -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/ui.jqgrid.css" type="text/css" />
<!-- The jqGrid language file code-->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/i18n/grid.locale-en.js" ></script>
<!-- The atual jqGrid code -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.jqGrid.js" ></script>

<script>
jQuery().ready(function (){
	jQuery("#rcmmd_list").jqGrid({
			/* data: mydata1,
			datatype: "local", */
			url:'<c:out value="{current_uri }"/><%=request.getContextPath() %>/user/sns/rcmmdList/jsonpTest.do',
			datatype: "jsonp",
			height: '400',
			width: '390',
			rowNum: 10,
			rowList: [10,20,30],
			pager: '#rcmmd_pager',
		   	colNames:['No', '학교ID', '학교명', '추천종류', '상담상태'],
		   	colModel:[
		   		{name:'idx', index:'idx', width:50, align:"center", sortable:true},		
		   		{name:'school_id', index:'school_id', hidden:true},
		   		{name:'school_name', index:'school_name', width:200, align:"left", sortable:true},
		   		{name:'rcmmd_type', index:'rcmmd_type', width:70, align:"center", sortable:true},
		   		{name:'const_status',index:'const_status', width:70, align:"center", sortable:true/* , searchoptions:{sopt:['eq','cn']} */}
		   	],
		   	viewrecords: true,
		   	sortname: 'no',
		   	sortorder: "desc",
		   	caption: "School List"
	});
});
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
});		
</script>
</head>
<body>

<jsp:include page="../../common/header.jsp" flush="false" />

<jsp:include page="../navigation.jsp" flush="false">
	<jsp:param value="5" name="selMenu"/>
</jsp:include>
    
<div class="layout_fix"></div>

<section class="form_area">
	<div class="tab_area">
    	 <ul>
        	<li><a href="javascript:selMenu(5)"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/news_feed_off.png" alt="news_feed" width="186" height="50" border="0"></a></li>
            <li><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_newsfeed/message_on.png" alt="message" width="186" height="50" border="0"></li>
        </ul>
    </div>
    
    <div style="width: 898px; margin: 0 auto; border:solid 1px #CCC; " >
       <div class="sns_rcmmd">
	    	<table id="rcmmd_list"></table>
			<div id="rcmmd_pager"></div>
	   </div>
	    
       <div class="sns_box">
	       <!--메시지1시작 -->	
	       <h1><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_message/person_view.png" alt="" width="40" height="40" border="0"></h1>
	       <h2>○○○○고</h2>
	       <div class="message01">
	         <p><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_message/comment_top01.png" alt="" width="815" height="29" border="0"></p>
	      	 <p class="comment_middle01"><span class="txt01">텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력<br>텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력
	        텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력</span></p>
	        
	       	 <p><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_message/comment_bottom01.png" alt="" width="815" height="12" border="0"></p>
	         <p class="post_date01">7/1 00:00</p>
	       </div>
	       <!--메시지1끝 -->
	       
	       <!--메시지2시작 -->	   
	       <div class="message02">
	         <p><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_message/comment_top02.png" alt="" width="813" height="28" border="0"></p>
	      	 <p class="comment_middle02"><span class="txt01">텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력
	      	 텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력</span></p>
	       
	       	 <p><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_message/comment_bottom02.png" alt="" width="813" height="11" border="0"></p>
	         <p class="post_date02">7/1 00:00</p>
	       </div>
	       <!--메시지2끝 -->   
	       
	       <!--메시지1시작 -->	
	       <h1><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_message/person_view.png" alt="" width="40" height="40" border="0"></h1>
	       <h2>○○○○고</h2>
	       
	       <div class="message01">
	         <p><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_message/comment_top01.png" alt="" width="815" height="29" border="0"></p>
	      	 <p class="comment_middle01"><span class="txt01">텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력텍스트입력</span></p>
	       	 <p><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/sns_message/comment_bottom01.png" alt="" width="815" height="12" border="0"></p>
	         <p class="post_date01">7/1 00:00</p>
	       </div>
	       <!--메시지1끝 -->   
		</div>
    </div>
	
</section>

<div class="layout_fix2"></div>

<div id="dialog-confirm" title="Confirm dialog"></div>
	
<footer>
	<jsp:include page="../../common/footer.jsp" />
</footer>
</body>
</html>
