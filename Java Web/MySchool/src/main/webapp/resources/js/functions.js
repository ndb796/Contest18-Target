$(function(){
	var setElm = $('.go_top, .go_top_end'),
	delayHeight = 100;

	setElm.css({display:'block',opacity:'0'});
	$('html,body').animate({scrollTop:0},1);

	$(window).on('load scroll resize',function(){
		setElm.each(function(){
			var setThis = $(this),
			elmTop = setThis.offset().top,
			elmHeight = setThis.height(),
			scrTop = $(window).scrollTop(),
			winHeight = $(window).height();
			if (scrTop > elmTop - winHeight + delayHeight && scrTop < elmTop + elmHeight){
				setThis.stop().animate({opacity:'1'},100); // 【上】からスクロールしてきた時の이벤트
			} else if (scrTop < elmTop - winHeight + delayHeight && scrTop < elmTop + delayHeight){
				setThis.stop().animate({opacity:'0'},100); // 【下】からスクロールしてきた時の이벤트
			}
		});
	});

	var $setView = $('.viewer'),
	fadeSpeed = 900,
	switchDelay = 5000;

	$setView.each(function(){
		var targetObj = $(this);
		var findUl = targetObj.find('ul');
		var findLi = targetObj.find('li');
		var findLiFirst = targetObj.find('li:first');

		findLi.css({display:'block',opacity:'0',zIndex:'99'});
		findLiFirst.css({zIndex:'100'}).stop().animate({opacity:'1'},fadeSpeed);

		setInterval(function(){
			findUl.find('li:first-child').animate({opacity:'0'},fadeSpeed).next('li').css({zIndex:'100'}).animate({opacity:'1'},fadeSpeed).end().appendTo(findUl).css({zIndex:'99'});
		},switchDelay);
	});
});

$(function(){
	$(".searchForm form .keyword").click(function(){
		$(".searchForm form .keyword, .searchForm form .checkWrap").toggleClass("open");
	});
	$(".searchForm form .dropdown").click(function(){
		$(".searchForm form .keyword, .searchForm form .checkWrap").removeClass("open");
	});
	$(".keyword02").click(function(){
		$(".keyword02, .checkWrap02").toggleClass("open");
	});
	$(".searchForm02 form .dropdown").click(function(){
		$(".keyword02, .checkWrap02").removeClass("open");
	});
	$(".searchForm form .dropdown, .searchForm form .keyword, body").click(function(){
		if($(".searchForm form .dropdown, .searchForm form .keyword").hasClass('open')){
			$(".searchForm").addClass("open").removeAttr('style');
		} else {
			$(".searchForm").removeClass("open");
		}
	});
});

$(function() {
	$('#glayLayer').hide();
	$('#overLayer').hide();

	$('#glayLayer').click(function() {
		$(this).fadeOut('fast');
		$('#overLayer').fadeOut('fast');
	});

	$('.btn_close').click(function() {
		$('#glayLayer').fadeOut('fast');
		$('#overLayer').fadeOut('fast');
	});

	$('.btn_blog').click(function() {
		$('#glayLayer').fadeIn('fast');
		$('#overLayer').fadeIn('fast').css({
			marginTop: '-' + $('#overLayer').height() / 2 + 'px',
			marginLeft: '-' + $('#overLayer').width() / 2 + 'px'
		});
	});
});