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
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myblog.css" />

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/start/jquery-ui-1.10.4.custom.css" />
<!-- <link rel="stylesheet" href="css/cupertino/jquery-ui.min.css" /> -->
		
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easydropdown.js"></script>

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

</script>
</head>
<body>
<div class="headerwrap01">
    <div class="centerfix">
    <div class="header_navigation">
		<h1>
			<a href="<%=request.getContextPath() %>/index.do"><img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/search/main_logo_w.png" alt="My School" width="256" height="28"></a>
		</h1>

		<p class="home_btn">
			<a href="#">
			<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/myblog/btn_home.png" alt="HOME" width="83" height="23"></a>
		</p>
		<p class="logout_btn">
			<img src="<%=request.getContextPath() %>/images/locale/ja/user/pages/myblog/btn_logout.png" alt="LOGOUT" width="83" height="23">
		</p>
     </div>
     
    </div>
</div>

<div class="contents centerfix">
	<div id="breadCrumb">
		<ul id="bread">
			<li><a href="/">TOP</a><span>&gt;</span></li>
			<li><a href="/">전체 블로그 목록</a><span>&gt;</span></li>
			<li>내 블로그</li>
		</ul>
	</div>
	<div class="contents_inner">
		<div class="contents_left">
			<p class="btn_blog"><a href="#"><img src="/images/locale/ja/user/pages/myblog/btn_write.png" width="250" height="130" alt="블로그 작성 블로그 작성하기 위해서는 등록(무료)이 필요합니다."></a></p>
			<div class="guardian_area">
				<p class="guardian_logo"><img src="images/locale/ja/user/pages/myblog/icon_guardian_02.gif" width="142" height="142" alt=""></p>
				<p class="guardian_name">◯○ ◯○◯<span>(학생: 중학교3년 여자)</span></p>
			</div>
			<p class="profile_edit"><a href="#">프로필 수정</a></p>
			<p class="blog_list"><a href="#">전체 블로그 목록</a></p>
		</div>
		<div class="contents_right">
			<div class="attention"><span>삭제할 블로그를 선택해 주세요.</span></div>
			<form action="" method="post">
				<div class="blogTitleCell">
					<h4>내 블로그</h4>
					<div class="pageNavigation">
						<ul>
							<li><a href="#">이전 표시</a></li>
							<li>1건～30건을 표시</li>
							<li><a href="#">다음 표시</a></li>
						</ul>
					</div>
				</div>
				<div class="myblog_wrap">
					<div class="listBlogBlock">
						<div class="listBlogTitle">
							<dl>
								<dt>
									<input name="blog_id" type="checkbox" value="">맞벌이 부부 경력을 활용
									<span><a href="#">수정</a></span>
								</dt>
								<dd>2018.09.16</dd>
							</dl>
						</div>
						<p class="article">중, 고등학교는 우리에게 있어서 기초 만들기의시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할수 있는 여유로운 환경이 있는 학교를 찾았습니다. 설명회에서는 학교가 가장 소중히하는 교육을 잘 알 수 있었고, 최대한 참여해서 학교의 방침에 공감 수 있는지 여부을 확인했습니다.</p>
					</div>
					<div class="listBlogBlock">
						<div class="listBlogTitle">
							<dl>
								<dt>
									<input name="blog_id" type="checkbox" value="">맞벌이 부부 경력을 활용
									<span><a href="#">수정</a></span>
								</dt>
								<dd>2018.09.16</dd>
							</dl>
						</div>
						<p class="article">중, 고등학교는 우리에게 있어서 기초 만들기의시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할수 있는 여유로운 환경이 있는 학교를 찾았습니다. 설명회에서는 학교가 가장 소중히하는 교육을 잘 알 수 있었고, 최대한 참여해서 학교의 방침에 공감 수 있는지 여부을 확인했습니다.</p>
					</div>
					<div class="listBlogBlock">
						<div class="listBlogTitle">
							<dl>
								<dt>
									<input name="blog_id" type="checkbox" value="">맞벌이 부부 경력을 활용
									<span><a href="#">수정</a></span>
								</dt>
								<dd>2018.09.16</dd>
							</dl>
						</div>
						<p class="article">중, 고등학교는 우리에게 있어서 기초 만들기의시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할수 있는 여유로운 환경이 있는 학교를 찾았습니다. 설명회에서는 학교가 가장 소중히하는 교육을 잘 알 수 있었고, 최대한 참여해서 학교의 방침에 공감 수 있는지 여부을 확인했습니다.</p>
					</div>
					<div class="listBlogBlock">
						<div class="listBlogTitle">
							<dl>
								<dt>
									<input name="blog_id" type="checkbox" value="">맞벌이 부부 경력을 활용
									<span><a href="#">수정</a></span>
								</dt>
								<dd>2018.09.16</dd>
							</dl>
						</div>
						<p class="article">중, 고등학교는 우리에게 있어서 기초 만들기의시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할수 있는 여유로운 환경이 있는 학교를 찾았습니다. 설명회에서는 학교가 가장 소중히하는 교육을 잘 알 수 있었고, 최대한 참여해서 학교의 방침에 공감 수 있는지 여부을 확인했습니다.</p>
					</div>
					<div class="listBlogBlock">
						<div class="listBlogTitle">
							<dl>
								<dt>
									<input name="blog_id" type="checkbox" value="">맞벌이 부부 경력을 활용
									<span><a href="#">수정</a></span>
								</dt>
								<dd>2018.09.16</dd>
							</dl>
						</div>
						<p class="article">중, 고등학교는 우리에게 있어서 기초 만들기의시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할수 있는 여유로운 환경이 있는 학교를 찾았습니다. 설명회에서는 학교가 가장 소중히하는 교육을 잘 알 수 있었고, 최대한 참여해서 학교의 방침에 공감 수 있는지 여부을 확인했습니다.</p>
					</div>
					<div class="listBlogBlock">
						<div class="listBlogTitle">
							<dl>
								<dt>
									<input name="blog_id" type="checkbox" value="">맞벌이 부부 경력을 활용
									<span><a href="#">수정</a></span>
								</dt>
								<dd>2018.09.16</dd>
							</dl>
						</div>
						<p class="article">중, 고등학교는 우리에게 있어서 기초 만들기의시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할수 있는 여유로운 환경이 있는 학교를 찾았습니다. 설명회에서는 학교가 가장 소중히하는 교육을 잘 알 수 있었고, 최대한 참여해서 학교의 방침에 공감 수 있는지 여부을 확인했습니다.</p>
					</div>
					<div class="listBlogBlock">
						<div class="listBlogTitle">
							<dl>
								<dt>
									<input name="blog_id" type="checkbox" value="">맞벌이 부부 경력을 활용
									<span><a href="#">수정</a></span>
								</dt>
								<dd>2018.09.16</dd>
							</dl>
						</div>
						<p class="article">중, 고등학교는 우리에게 있어서 기초 만들기의시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할수 있는 여유로운 환경이 있는 학교를 찾았습니다. 설명회에서는 학교가 가장 소중히하는 교육을 잘 알 수 있었고, 최대한 참여해서 학교의 방침에 공감 수 있는지 여부을 확인했습니다.</p>
					</div>
				</div>
				<div class="formButtons">
					<ul>
						<li><input type="submit" class="formBt01" value="체크한 블로그를 삭제"></li>
					</ul>
				</div>
			</form>
			<ul class="utilityLinks">
				<li><a href="#">블로그 작성く</a></li>
			</ul>
		</div>
	</div>
</div>
<footer>
	<div class="bottom_contents">
		<div class="bottomInner">
			<div class="navigation_area">
				<p>
				   <a href="http://www.myschool.com" target="_blank">회사개요</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="privacy_policy.jsp" target="_blank">개인정보취급에 대하여</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="rules.jsp" target="_blank">이용규약</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="mailto:info@My School.com?subject=상담문의">상담문의</a>&nbsp;&nbsp;l&nbsp;&nbsp;
				   <a href="press.jsp" target="_blank">기사</a>				   
				</p>
				<p class="copyright">Copyright &copy; 2014, Inc. All rights reserved.</p>
		       </div>
		    <div class="logo_area">
		    	<p><a href="http://www.myschool.com/" target="_blank"><img src="<%=request.getContextPath() %>/images/common/company_logo.png" alt="My School" width="212" height="70"></a></p>
		    </div>
		</div>
	</div>
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
	<p class="validateTips">이메일과 비밀번호를 입력해 주십시오.</p>
	<form id="loginFrm" method="post">
	<fieldset>
		<label for="name">이메일</label>
		<input type="text" name="user_email" id="user_email" class="text ui-widget-content ui-corner-all" />
		<label for="password">비밀번호</label>
		<input type="password" name="user_pwd" id="user_pwd" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
	</form>
</div>
</body>
</html>