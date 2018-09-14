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
	$(".keyword").click(function(){
		$(".keyword, .checkWrap").toggleClass("open");
	});
	$("#form .dropdown").click(function(){
		$(".keyword, .checkWrap").removeClass("open");
	});
	$(".keyword02").click(function(){
		$(".keyword02, .checkWrap02, #submit_button02").toggleClass("open");
	});
	$("#form_small .dropdown").click(function(){
		$(".keyword02, .checkWrap02, #submit_button02").removeClass("open");
	});
});