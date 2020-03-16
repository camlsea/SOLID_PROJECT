//윈도우 사이즈(디버깅용)
function docSizeCheck(){
	var docSize=$(window).width();
	var docHeight=$(window).height();
	$('.web-size').remove();
	$('body').append('<div class="web-size">'+docSize +' * ' + docHeight+'</div>');
}
$(function(){docSizeCheck();});
$(window).resize(function(){docSizeCheck();});

//바디에 브라우저별 클래스 삽입(디버깅용)
$(function(){
	var agent=navigator.userAgent;
	var passAgentList=['iPhone','iPad','Android','MSIE 10','MSIE 9','MSIE 8','MSIE 7','MSIE 6','Firefox','OPR','Chrome','Safari','rv:']
	//alert(agent)
	for(i=0;i<passAgentList.length;i++){
		if(agent.indexOf(passAgentList[i])>-1){
			var browserName=passAgentList[i].replace(/\s/g,"");
			browserName=(browserName=='OPR')?'Opera':browserName;
			browserName=(browserName=='rv:')?'msie-edge':browserName;
			$('body').addClass(browserName.toLowerCase());
			$('body').append('<div class="web-browser">'+browserName.toLowerCase()+'</div>');
			break;
		}
	}
});

/* 달력 UI datepicker*/
function fnDatePicker(selecter){
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNamesShort: ['1월','2월','3월','4월','5월','6월',
		'7월','8월','9월','10월','11월','12월'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		weekHeader: 'Wk',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true
		//,yearSuffix: '년'
	};
	
	var tday = new Date();
	var tyear = tday.getFullYear();
	$(selecter).datepicker({
		changeMonth: true, //월 셀렉트
		changeYear: true,  //년 셀렉트
		yearRange: '1930:'+(tyear+10), //년도 범위(현재년도+10년)
		//showButtonPanel: true, 
		dateFormat:"yy-mm-dd",  //년도타입
		showOn: 'button',  
		buttonImage: '../images/bbs/ui-datepicker-cal.gif', //달력이미지
		buttonText:'달력보기',
		buttonImageOnly: false,
		onClose: function( selectedDate ) {
			var naming=$(this).attr('period');
			if($(this).hasClass('from')){
				$( "input[period="+naming+"].to" ).datepicker( "option", "minDate", selectedDate );
			}else if($(this).hasClass('to')){
				$( "input[period="+naming+"].from" ).datepicker( "option", "maxDate", selectedDate );
			}
      }

	});
	$.datepicker.setDefaults($.datepicker.regional['ko']);
}
//달력실행
 $(function(){
	if($('input.datepicker').length>0){
		fnDatePicker('.datepicker'); 
	}
});	

//faq
function faqView(elm){
	if(!$(elm).hasClass('on')){
		$(elm).addClass('on').parent().parent().find('.answer').show();
		$(elm).parent().parent().siblings().find('.on').removeClass('on').parent().parent().find('.answer').hide();
	}
}

//패밀리사이트
$(function(){
	$('.family-site .default').bind('mouseover focus',function(){
		$($(this).attr('href')).slideDown(300);
	});
	$('.family-site').mouseleave(function(){
		$('.site-list').slideUp(300);
	});
	$('.site-list a').eq(-1).bind('blur',function(){
		$('.site-list').slideUp(300);
	});
});

//GNB
$(function(){
	$('#nav a').each(function(){
		$(this).bind('mouseenter focus',function(){
			var menuIdx=$(this).parent().index()+1;
			$('.sub-menu'+menuIdx).stop().slideDown(300).siblings('.sub-menu').stop().slideUp(300);
		});
	}).bind('mouseleave',function(){
		$('.sub-menu').stop().slideUp(300);
	});
	$('.sub-menu').mouseenter(function(){
		$(this).stop().slideDown(300);
	}).mouseleave(function(){
		$(this).stop().slideUp(300);
	});
});

//pageTop
$(function(){
	$('#pageTop').click(function(){
		var wPos=$($(this).attr('href')).offset().top;
		$('html, body').animate({'scrollTop':wPos},500);
		return false;
	});
});

//location
$(function(){
	$('.location-path .select').click(function(){
		if(!$(this).hasClass('on')){
			$(this).addClass('on').next().fadeIn(300);
		}else{
			$(this).removeClass('on').next().fadeOut(300);
		}
		return false;
	});
});


//메인 브랜드
function mainBrand(elm){
		$(elm).addClass('on');
		$(elm).parent().siblings().find('a').removeClass('on');
		$('#brandInfo').find('img').attr('src',function(){
			return this.src.replace($('#brandInfo').find('img').attr('src'),'images/main/main-'+$(elm).attr('rel')+'.jpg');
		});
		$('#brandInfo').find('img').attr('alt',$(elm).text()).parent('a').attr('href',$(elm).attr('href'));
}

/* 슬라이드 배너 */
function slideBanner(sliderWrap,playStyle,playSpeed,slidespeed,viewitem,resize){//감싸는 DIV, 자동실행(autoPlay),자동실행시간
	var slideSpeed=parseInt(slidespeed)
	var sliderWrap=$(sliderWrap)
	var slider=$(sliderWrap).find('.item-wrap');
	var nextBtn=$(sliderWrap).find('.next');
	var prevBtn=$(sliderWrap).find('.prev');
	var shortCut=$(sliderWrap).find('.item-short-cut');
	var autoPlay=$(sliderWrap).find('.auto-play');
	var pausePlay=$(sliderWrap).find('.pause-play');
	var viewItem=(viewitem)?viewitem:1;
	if(viewItem<slider.find('li').length){
		if($(sliderWrap).find('.item-wrap').hasClass('on')){
			var itemWidth=slider.find('li').outerWidth();
			var itemlen=slider.find('li').length;
			var itemWrap=slider.find('ul').clone();
			slider.append(itemWrap);
			var sliderWidth=itemWidth*itemlen;
			slider.width(sliderWidth*2);
			slider.css('left',xPos);
		}else{
		var xPos=0;
		if(resize=='resize'){
				slider.find('li').width(sliderWrap.width());
		}
		var itemWidth=slider.find('li').outerWidth();
		var itemlen=slider.find('li').length;
		var sliderWidth=itemWidth*itemlen;
		slider.find('ul').width(sliderWidth);
		var itemWrap=slider.find('ul').clone();		
		slider.append(itemWrap);
		slider.width(sliderWidth*2);
		$(window).resize(function(){
			if(resize=='resize'){
				slider.find('li').width(sliderWrap.width());
				itemWidth=slider.find('li').outerWidth();
				sliderWidth=itemWidth*itemlen;
				slider.find('ul').width(sliderWidth);
				slider.width(sliderWidth*2);
				shortCut.find('.on').trigger('click');
			}
		});
		slider.addClass('on')
		//다음으로 이동
		function nextMove(){
			xPos-=itemWidth;
			if(xPos>=(sliderWidth)*-1){
				slider.stop().animate({left:xPos},slideSpeed);
			}else{
				slider.stop().animate({left:0},0);
				xPos=itemWidth*-1;
				slider.stop().animate({left:xPos},slideSpeed);
			}
			scActive();
		};
		//이전으로이동
		function prevMove(){
			xPos+=itemWidth;
			if(xPos<=0){
				slider.stop().animate({left:xPos},slideSpeed);
			}else{
				xPos=(sliderWidth)*-1
				slider.stop().animate({left:xPos},0);
				xPos+=itemWidth;
				slider.stop().animate({left:xPos},slideSpeed);
			}
			scActive();
		};

		//숏컷생성
		var sNum=0;
		for (i=1;i<=itemlen ;i++ ){
			if(i<=itemlen){
				sNum=i;
				var sc='<button></button>'
				shortCut.append(sc);
			}
		}
		//숏컷이동
		shortCut.children().each(function(){
			$(this).click(function(){
				xPos=$(this).index()*itemWidth*-1;
				slider.stop().animate({left:xPos},slideSpeed);
				scActive();
			})
		})
		//숏컷 활성화
		function scActive(){
			//if(!$('.xPos').is(':visible')){$('body').append('xPos <span class="xPos"></span> | slideWidth <span class="sliderWidth"></span> |xPos/itemWidth<span class="sum"></span>')};
			var scNum=(xPos*-1)/itemWidth;
			scNum=((xPos*-1)/itemWidth==itemlen)?0:scNum;
			//$('.xPos').html(xPos*-1);
			//$('.sliderWidth').html(sliderWidth);
			//$('.sum').html(scNum);
			shortCut.children().removeClass('on').eq(scNum).addClass('on');
		}
		//자동 이동 관련
		var autoSlide;
		//자동실행 클릭
		autoPlay.click(function(){
			if(!$(this).hasClass('on')){
				pausePlay.removeClass('on');
				$(this).addClass('on');
				autoSlide=setInterval(function(){
						nextMove()
					},playSpeed);
			}
			return false;
		});
		//일시정지 클릭
		pausePlay.click(function(){
			autoPlay.removeClass('on');
			$(this).addClass('on');
			clearInterval(autoSlide);
		})

		//슬라이드공간에 마우스 들어올시 자동실행 멈춤
		$(sliderWrap).find('a').mouseenter(function(){
			if(autoPlay.hasClass('on')){
				clearInterval(autoSlide);
			}
		});
		$(sliderWrap).find('button').mouseenter(function(){
			if(autoPlay.hasClass('on')){
				clearInterval(autoSlide);
			}
		});
		//슬라이드 공간에서 마우스 나갈시 자동실행 실행
		$(sliderWrap).find('a').bind('mouseleave mouseup',function(){
			if(autoPlay.hasClass('on')){
				clearInterval(autoSlide);
				autoSlide=setInterval(function(){
						nextMove();
					},playSpeed);
			}
		});
		$(sliderWrap).find('button').bind('mouseleave mouseup',function(){
			if(autoPlay.hasClass('on')){
				clearInterval(autoSlide);
				autoSlide=setInterval(function(){
						nextMove();
					},playSpeed);
			}
		});

		//이전버튼 클릭이벤트
		nextBtn.click(function(){
			nextMove();
			return false;
		});
		//이전버튼 클릭이벤트
		prevBtn.click(function(){
			prevMove();
			return false;
		});
		
		//숏컷실행
		scActive();

		//function에 autoPlay 인자입력시 자동흐름 시작
		if(playStyle=='autoPlay'){
			autoPlay.trigger('click');
		}
		}
	}else{
		nextBtn.css('opacity','0.2')
		prevBtn.css('opacity','0.2')
		nextBtn.click(function(){
			return false;
		});
		prevBtn.click(function(){
			return false;
		})
	}
}

function campaignBigImg(elm){
	$(elm).addClass('on');
	$(elm).parent().siblings().find('a').removeClass('on');
	$('#campaignInfo').find('img').attr('src',function(){
		return this.src.replace($('#campaignInfo').find('img').attr('src'),$(elm).find('img').attr('src'));
	});
	$('#campaignInfo').find('img').attr('alt',$(elm).find('img').attr('alt'));
}	


function number_filter(str_value){
	return str_value.replace(/[^0-9]/gi, ""); 
}


var secureUrlList = [  "/login.do"
                       , "/a1_cust_group"
                       , "/customerVoice2.do"];

var isSecureTargetUrl = false;
 for( var i = 0;  i < secureUrlList.length;  i++ ) {
   	if( location.href.toLowerCase().indexOf(secureUrlList[i]) > -1 ) {
   		isSecureTargetUrl = true;
   		break;
   	}
 }
 var newlocation = "";
 /* https설정 로컬에서는 주석처리 / SSL설정
 if( location.protocol === "https:" && !isSecureTargetUrl ) {
   	newlocation = location.href.replace("https:", "http:");
 }
 else if(location.protocol === "http:" && isSecureTargetUrl ) {
  	newlocation = location.href.replace("http:", "https:");
 }
 */
 
 if( newlocation != "" ) {
 	location.replace(newlocation);
 }
