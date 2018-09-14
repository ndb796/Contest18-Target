<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.mycompany.myschool.web.util.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>전체 블로그 | My School | 학생과 학교의 최고의 매칭 시스템</title>
<meta name="description" content="학생들에게 적합한 학교 정보 제공! 학교로부터 스카우트된다! 보이는 학생, 보이는 학교 정보! 학교는 학교 행사 참여율 응모 율 진학률 등이 사실적으로 관리, 확인할 수있다.">
<meta name="keywords" content="보이는 학생, 보이는 학교 데뷔 스토리 학교 매칭, student, school, 학교, 학교, 진로 상담, 학교 정보, 학생 정보, 진로, 진학, 대학 예비 학교, 유학, 오픈 캠퍼스, 이벤트, 입시 담당자 고민, 상담">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/easydropdown.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/search.css" />

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/start/jquery-ui-1.10.4.custom.css" />
<!-- <link rel="stylesheet" href="css/cupertino/jquery-ui.min.css" /> -->
		
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easydropdown.js"></script>
<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
<script src="<%=request.getContextPath() %>/js/googlemap.js"></script>

<style>
	body { font-size: 80%; background-color: #fff; position: relative;}
	/*label, input { display:block; }*/
	input.text { margin-bottom:12px; width:95%; padding: .4em; }
	fieldset { padding:0; border:0; margin-top:25px; }
	h1 { font-size: 1.2em; margin: .6em 0; }
	.ui-dialog .ui-state-error { padding: .3em; }
	.validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>

<script src="<%=request.getContextPath() %>/js/functions.js"></script>
<script type="text/javascript">
window.onload = function () {
	var resultMsg = "";
	var resultCode = "null";
	if (resultMsg != null && resultMsg.length > 0) {
		$( "#dialog-error" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 20px 0;'></span>" + resultMsg);
		if (resultCode == "S10006" ) {
			$( "#dialog-error" ).dialog( "option", "title", "Information" );
		} 
		
		$( "#dialog-error" ).dialog( "open" );
	}
};

$(function() {
	$("#form .dropdown, .keyword").click(function(){
		if ($("#form .dropdown, .keyword").hasClass("open")) {
			$(".searchForm").addClass("open");
		} else {
			$(".searchForm").removeClass("open");			
		}
	});
	
	$( "#dialog-error" ).dialog({
		autoOpen: false,
	    resizable: false,
	    height: 250,
	    width: 350,
	    modal: true,
	    buttons: {
	       "Close": function() {
	         $( this ).dialog( "close" );
	       }
	    }
	});
	
	$("#dialog-form").dialog({
		autoOpen: false,
		show:	{effect: "blind", duration: 500},
		height: 320,
		width: 400,
//		position: [800, 20],
		modal: true,
		buttons: {
			Login: function() {
				doLogin($("#user_email"), $("#user_pwd"));
			}/* ,
			Join: function() {
				document.getElementById("user_email").value = "";
				document.getElementById("user_pwd").value = "";
				$(this).dialog("close");
				document.location.href = "join_type.jsp";
			} */
		},
		close: function() {
			document.getElementById("user_email").value = "";
			document.getElementById("user_pwd").value = "";
		}
	});
	
	$( "#dialog-confirm" ).dialog({
		autoOpen: false,
		resizable: false,
	    height: 350,
	    width: 350,
	    modal: true,
	    buttons: {
	      "Yes": function() {
	    	  $( this ).dialog( "close" );
	    	  openLogin();
	      },
	      "No": function() {
	          $( this ).dialog( "close" );
	      }
	    }
	  });
});

function updateTips( t ) {
	$(".validateTips").text( t ).addClass( "ui-state-highlight" );
	setTimeout(function() {
		$(".validateTips").removeClass( "ui-state-highlight", 1500 );
	}, 500 );
}

function checkNull( o, n ) {
	if ( o.val().length == 0) {
		o.addClass( "ui-state-error" );
		updateTips( "Please, enter your " + n + "." );
		return false;
	} else {
		return true;
	}
}

function checkRegexp( o, regexp, n ) {
	if ( !( regexp.test( o.val() ) ) ) {
		o.addClass( "ui-state-error" );
		updateTips( n );
		return false;
	} else {
		return true;
	}
}

function doLogin(objEmail, objPwd) {
	var bValid = true;
	objEmail.removeClass( "ui-state-error" );
	objPwd.removeClass( "ui-state-error" );
	
	bValid = bValid && checkNull( objEmail, "e-mail" );
	bValid = bValid && checkRegexp( objEmail, /^((([a-z]|\d|[!#$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "ex) test@My School.com" );
	bValid = bValid && checkNull( objPwd, "password" );
	
	if (bValid) {
		var f1 = document.getElementById("loginFrm");
		f1.action = "<%=request.getContextPath()%>/loginProc.do";
		f1.submit();
	}
}

function openLogin() {
	$("#dialog-form").dialog("open");
}

function doDetail(sb_id) {
	$( "#dialog-confirm" ).html("<span class='ui-icon ui-icon-check' style='float:left; margin:0 7px 30px 0;'></span>상세정보를 보기위해서는 로그인을 해 주세요. 로그인을 하시겠습니까? <br>등록을 아직 하지 않으신 분은 신규등록을 해 주세요.");
	$( "#dialog-confirm" ).dialog( "open" );
}

function doSearch() {
	var f1 = document.frm;
	
	var srch_school = f1.srch_school;
	var srch_loc = f1.srch_loc;
	var srch_founder = f1.srch_founder;
	var keywords = f1.keywords;
	
	var selKeywords = "";
	for (var i=0; i<keywords.length; i++) {
		if (keywords[i].checked) {
			selKeywords += keywords[i].value + ",";
		}
	}
	var errorMsg = "";
	
	/* if (srch_school.value == "" || srch_loc.value == "") { */
	if (srch_school.value == "") {
	 	errorMsg += "<p><span class='ui-icon ui-icon-alert' style='text-align: left; float:left; margin:0 7px 20px 0;'></span>검핵하려는 학교의 종류와 진학장소는 필수 항목입니다.</p>";
	}
	
	if (errorMsg != "") {
	 	$( "#dialog-error" ).html(errorMsg);
	 	$( "#dialog-error" ).dialog( "open" );
	}  else {
		console.log(srch_school.value + "/" + srch_loc.value + "/" + srch_founder.value + "/" + keywords.length);
		f1.srch_keywords.value = selKeywords;
		f1.submit();
	}
}

function showMap(address) {
	var geocoder = new google.maps.Geocoder();
	var lat = 0.0;
	var lng = 0.0;
	geocoder.geocode( { 'address': address}, function(results, status) {
		
	      if (status == google.maps.GeocoderStatus.OK) {
	    	  lat = results[0].geometry.location.lat();
	    	  lng = results[0].geometry.location.lng();
	    	  
	    	  initialize(lat, lng, address);
	      } else {
	        alert("To use the Google Ma on your web site, you have to enter a correct API_KEY : " + status);
	      }
	});
}
</script>
</head>
<body onload="initialize(37.5662952, 126.97794509999994, '대한민국 서울특별시 중구 명동 세종대로 110')">

<div class="headerwrap01">
    <div class="centerfix">
    <div class="header_navigation">
		<h1>
			<a href="<%=request.getContextPath() %>/index.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/search/main_logo_w.png" alt="MY SCHOOL" width="256" height="28"></a></h1>

	    <p class="select_language">
    	</p>
	
		<p class="loginbtn">
			<a href="javascript:openLogin();">
			<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/btn_login.png" alt="login" width="83" height="23"></a>
		</p>
		<p class="register_btn">
			<a href="/user/joinType.do">
			<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/top/btn_jp.png" alt="신규등록" width="83" height="23"></a>
		</p>
     </div>
     
    </div>
</div>

<div class="contents">
	<div class="contents_inner">
		<div class="searchForm02">
			<form action="<%=request.getContextPath() %>/common/search.do" id="frm" method="post" name="frm">
		        <select class="dropdown" id="srch_school" name="srch_school">
		        	<option value="" class="label">학교</option>
		            <option value="2" <c:if test="${srch_school == 2 }">selected</c:if>>중학교</option>
		            <option value="3" <c:if test="${srch_school == 3 }">selected</c:if>>고등학교</option>
		            <option value="4" <c:if test="${srch_school == 4 }">selected</c:if>>대학교</option>
		            <option value="5" <c:if test="${srch_school == 5 }">selected</c:if>>전문학교</option>
		        </select>
		        <select class="dropdown" id="srch_loc" name="srch_loc">
		        	<option value="" class="label">진학장소</option>
		        	<option value="10" <c:if test="${srch_loc == '10' }">selected</c:if>>서울</option>
		            <option value="13" <c:if test="${srch_loc == '13' }">selected</c:if>>경기도</option>
		            <option value="14" <c:if test="${srch_loc == '14' }">selected</c:if>>강원도</option>
		            <option value="11" <c:if test="${srch_loc == '11' }">selected</c:if>>경상남도</option>
		            <option value="12" <c:if test="${srch_loc == '12' }">selected</c:if>>경상북도</option>
		            <option value="19" <c:if test="${srch_loc == '19' }">selected</c:if>>전라남도</option>
		            <option value="20" <c:if test="${srch_loc == '20' }">selected</c:if>>전라북도</option>
		            <option value="15" <c:if test="${srch_loc == '15' }">selected</c:if>>충청남도</option>
		            <option value="16" <c:if test="${srch_loc == '16' }">selected</c:if>>충청북도</option>
		            <option value="17" <c:if test="${srch_loc == '17' }">selected</c:if>>제주도</option>
		        </select>
		        <select class="dropdown" id="srch_founder" name="srch_founder">
		        	<option value="" class="label">학교종류</option>
		            <option value="1" <c:if test="${srch_founder == 1 }">selected</c:if>>국/공립</option>
		            <option value="3" <c:if test="${srch_founder == 3 }">selected</c:if>>사립</option>
		        </select>
		    	<p class="keyword02">키워드<span class="carat"></span></p>
				<div class="checkWrap02">
					<label><input type="checkbox" name="keywords" value="CK0000001" <c:if test="${ ! empty keywords['CK0000001'] }">checked</c:if>>영어, ESS, 어학</label>
					<label><input type="checkbox" name="keywords" value="CK0000002" <c:if test="${ ! empty keywords['CK0000002'] }">checked</c:if>>동아리활동</label>
					<label><input type="checkbox" name="keywords" value="CK0000003" <c:if test="${ ! empty keywords['CK0000003'] }">checked</c:if>>해외진학과 연계</label>
					<label><input type="checkbox" name="keywords" value="CK0000004" <c:if test="${ ! empty keywords['CK0000004'] }">checked</c:if>>집에서의 거리</label>
					<label><input type="checkbox" name="keywords" value="CK0000005" <c:if test="${ ! empty keywords['CK0000005'] }">checked</c:if>>젊은 선생님이 많음</label>
					<label><input type="checkbox" name="keywords" value="CK0000006" <c:if test="${ ! empty keywords['CK0000006'] }">checked</c:if>>교정이 넓음</label>
					<label><input type="checkbox" name="keywords" value="CK0000007" <c:if test="${ ! empty keywords['CK0000007'] }">checked</c:if>>최고의 시설</label>
					<label><input type="checkbox" name="keywords" value="CK0000008" <c:if test="${ ! empty keywords['CK0000008'] }">checked</c:if>>장학금제도</label>
					<label><input type="checkbox" name="keywords" value="CK0000009" <c:if test="${ ! empty keywords['CK0000009'] }">checked</c:if>>수영장</label>
					<label><input type="checkbox" name="keywords" value="CK0000010" <c:if test="${ ! empty keywords['CK0000010'] }">checked</c:if>>도서관</label>
					<label><input type="checkbox" name="keywords" value="CK0000011" <c:if test="${ ! empty keywords['CK0000011'] }">checked</c:if>>OB/OG</label>
					<label><input type="checkbox" name="keywords" value="CK0000012" <c:if test="${ ! empty keywords['CK0000012'] }">checked</c:if>>맞벌이부부가 많음</label>
					<label><input type="checkbox" name="keywords" value="CK0000013" <c:if test="${ ! empty keywords['CK0000013'] }">checked</c:if>>강의식 수업</label>
					<label><input type="checkbox" name="keywords" value="CK0000014" <c:if test="${ ! empty keywords['CK0000014'] }">checked</c:if>>교복</label>
					<label><input type="checkbox" name="keywords" value="CK0000015" <c:if test="${ ! empty keywords['CK0000015'] }">checked</c:if>>스포츠</label>
					<label><input type="checkbox" name="keywords" value="CK0000016" <c:if test="${ ! empty keywords['CK0000016'] }">checked</c:if>>음악</label>
					<label><input type="checkbox" name="keywords" value="CK0000017" <c:if test="${ ! empty keywords['CK0000017'] }">checked</c:if>>미술</label>
					<label><input type="checkbox" name="keywords" value="CK0000018" <c:if test="${ ! empty keywords['CK0000018'] }">checked</c:if>>과학</label>
					<label><input type="checkbox" name="keywords" value="CK0000019" <c:if test="${ ! empty keywords['CK0000019'] }">checked</c:if>>IT</label>
					<label><input type="checkbox" name="keywords" value="CK0000020" <c:if test="${ ! empty keywords['CK0000020'] }">checked</c:if>>예체능</label>
					<label><input type="checkbox" name="keywords" value="CK0000021" <c:if test="${ ! empty keywords['CK0000021'] }">checked</c:if>>식당, 카페테리아</label>
					<label><input type="checkbox" name="keywords" value="CK0000022" <c:if test="${ ! empty keywords['CK0000022'] }">checked</c:if>>해외연수</label>
					<label><input type="checkbox" name="keywords" value="CK0000023" <c:if test="${ ! empty keywords['CK0000023'] }">checked</c:if>>자연환경</label>
					<label><input type="checkbox" name="keywords" value="CK0000024" <c:if test="${ ! empty keywords['CK0000024'] }">checked</c:if>>학교상품</label>
					<label><input type="checkbox" name="keywords" value="CK0000025" <c:if test="${ ! empty keywords['CK0000025'] }">checked</c:if>>표창제도</label>
				</div>
				<input type="hidden" name="srch_keywords" value="">
				<input id="submit_button02" type="button" value="검색" onclick="doSearch()">
			</form>
		</div>
		<div id="map_basic" ></div>
		<div class="contents_left">
			<!-- data exists -->
			<c:choose>
				<c:when test="${fn:length(resultList) == 0}">
				<div style="text-align: center;">
					<font color="#ff0000">검색 결과가 없습니다.</font>
				</div>
				</c:when>
				<c:otherwise>
				<div class="indicate">
					<c:forEach var="school" items="${resultList }">
					<dl>
						<dt><a href="javascript:showMap('${school.address }')"><c:out value='${school.name }'/></a></dt>
						<dd>［주소］<c:out value='${school.address }'/></dd>
						<dd>［연락처］<c:out value='${school.chr_tel }'/></dd>
						<dd class="detail_btn"><a href="javascript:doDetail('${school.sch_id }')">상세보기</a></dd>
					</dl>
					</c:forEach>
				</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<footer>
   	<jsp:include page="footerForIndex.jsp" />
</footer>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-52579319-1', 'auto');
  ga('send', 'pageview');
</script>

<div id="dialog-confirm" title="Confirm dialog">
</div>

<div id="dialog-error" title="Error dialog">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>에러 메시지</p>
</div>
<div id="dialog-form" title="로그인">
	<p class="validateTips">이메일주소와 비밀번호을 입력해 주세요.</p>
	<form id="loginFrm" method="post">
	<fieldset>
		<label for="name">이메일주소</label>
		<input type="text" name="user_email" id="user_email" class="text ui-widget-content ui-corner-all" />
		<label for="password">비밀번호</label>
		<input type="password" name="user_pwd" id="user_pwd" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
	</form>
</div>
</body>
</html>