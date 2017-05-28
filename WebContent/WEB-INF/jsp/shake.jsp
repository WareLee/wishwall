<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>摇一摇</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	
		<link rel="stylesheet" href="../css/public.css" />
		<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="../js/public.js"></script>
	
		<style type="text/css">
			* {
					margin: 0;
					padding: 0;
				}
			
			body {
				/*background: #292D2E;*/
				background-color: gray;
			}
			
			.bar{
				position: absolute;
				width: 100%;
				height: 35px;
				top: 20px;
				background-color: rgba(91,91,91,0.7);
			}
			.shake{
				width: 100%;
				height: 100%;
				margin-top: 40%;
				text-align: center;	
			}
			.shake img{
				width: 200px;
				height: auto;
				-webkit-transform: rotate(-7deg);
				-moz-transform: rotate(-7deg);
				-ms-transform: rotate(-7deg);
				-o-transform: rotate(-7deg);
				transform: rotate(-7deg);
			}
			
			.shake-animate {
				-webkit-animation: hand_move infinite 2s;
			}
			@-webkit-keyframes hand_move {
					0% {
						-webkit-transform: rotate(0);
						-moz-transform: rotate(0);
						-ms-transform: rotate(0);
						-o-transform: rotate(0);
						transform: rotate(0);
					}
					50% {
						-webkit-transform: rotate(15deg);
						-moz-transform: rotate(15deg);
						-ms-transform: rotate(15deg);
						-o-transform: rotate(15deg);
						transform: rotate(15deg);
					}
					100% {
						-webkit-transform: rotate(0);
						-moz-transform: rotate(0);
						-ms-transform: rotate(0);
						-o-transform: rotate(0);
						transform: rotate(0);
					}
				}
			
			.loading {
					position: absolute;
					top: 70%;
					left: 50%;
					margin-left: -50px;
					width: 100px;
					height: 100px;
					background: url(../img/spinner.png) no-repeat;
					background-size: 100px 100px;
					opacity: 0;
					-webkit-animation: loading infinite linear .5s;
					-moz-animation: loading infinite linear .5s;
					-ms-animation: loading infinite linear .5s;
					-o-animation: loading infinite linear .5s;
					animation: loading infinite linear .5s;
					-webkit-transition: all .5s;
					-moz-transition: all .5s;
					-ms-transition: all .5s;
					-o-transition: all .5s;
					transition: all .5s;
				}
				
			.loading-show {
				opacity: 1;
			}
			
			@-webkit-keyframes loading {
					0% {
						-webkit-transform: rotate(0);
						-moz-transform: rotate(0);
						-ms-transform: rotate(0);
						-o-transform: rotate(0);
						transform: rotate(0);
					}
					100% {
						-webkit-transform: rotate(360deg);
						-moz-transform: rotate(360deg);
						-ms-transform: rotate(360deg);
						-o-transform: rotate(360deg);
						transform: rotate(360deg);
					}
				}	
				
				
			.mess{
				display: none;
			}
			.mess-hid{
				display: none;
			}
			.mess-show{
				display: block;
			}
			.xiaoguo-hid{
				display: none;
			}
			.xiaoguo-show{
				display: block;
			}
			
				
			#mess{
				width:100%;
			}
			#header{
				text-align: center;
				width: 100%;
				height: auto;
				margin-top: 60px;
				margin-bottom: 40px;
			}
			
			#header>a>img{
				width: 65px;
				height: 65px;
				/*display: inline-block;*/
				border-radius: 5px;
			}
			.messBlock{
				/*margin-left: 50px;*/
				margin-left: 55px;
				margin-right: 40px;
			}
			
			#shake{
				width: 100%;
				height: 30px;
				position: fixed;
				bottom: 0;
				line-height: 30px;
				text-align: center;
				color: white;
				background-color: rgba(255,185,62,0.7);
			}
			
		</style>
	</head>
	<link rel="icon" href="../img/aa.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="../img/aa.ico">
	<link rel="Bookmark" href="../img/aa.ico">
			
	<body>

		<section id="xiaoguo">
			<div class="bar"></div>
			<div class="shake shake-animate"><img src="../img/onecopy.png" alt="shake it"></div>
			<div id="loading" class="loading"></div>
		</section>
		
		<section class="mess" id="mess">
			<div id="header">
				<a href="#"><img src=""></a>
			</div>
			<section>
				<section class="messSection">
						<blockquote class="messBlock prayimg pointimg">
							<b class="to"></b>
							<p class="message"></p>
							<b class="from">From:Bitwork</b>
							<time datetime="" class="dtt"></time>
						</blockquote>
						<div class="zan">
							<span></span>
							<button style="border:none;background:none;">
								<img src="" mid="0">
							</button>
						</div>
				</section>
			</section>
			<div id="shake">继续摇一摇即可查看更多愿望哦</div>
		</section>
		
		<script>
			var state=false;
			
			// 关于摇动事件频繁被触发的简易解决方式,设置一个定时器
			setInterval(function(){
				if(!state){
					state= !state;
				}
			},2000);
			
		
			var SHAKE_THRESHOLD = 2000;
			var last_update = 0;
			var x = y = z = last_x = last_y = last_z = 0;

			if(window.DeviceMotionEvent) {
				window.addEventListener('devicemotion', deviceMotionHandler, false);
			} else {
				alert('本设备不支持devicemotion事件');
			}

			function deviceMotionHandler(eventData) {
				var acceleration = eventData.accelerationIncludingGravity;
				var curTime = new Date().getTime();

				if((curTime - last_update) > 100) {
					var diffTime = curTime - last_update;
					last_update = curTime;
					x = acceleration.x;
					y = acceleration.y;
					z = acceleration.z;
					var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
					
					if(speed > SHAKE_THRESHOLD && state) {
						state= false;
						doResult();
					}
					last_x = x;
					last_y = y;
					last_z = z;
				}
			}

			function doResult() {
				// 请求数据
				getRandomMess();
				document.getElementById("mess").className="mess mess-hid";
				document.getElementById("xiaoguo").className = "xiaoguo-show";
				document.getElementById("loading").className = "loading loading-show";
				
				setTimeout(function() {
						document.getElementById("loading").className = "loading";
						document.getElementById("xiaoguo").className = "xiaoguo-hid";

						 
						document.getElementById("mess").className="mess mess-show";
						state=true;
					}, 1000);
				
			}
			function getRandomMess(){
				$.ajax({
					url: 'enter/messPage_getRandomMess.action',     //要访问的service路径
					type: "post",        //访问实现的方式:get 或 post
					async:true,      //是否异步
					cache:false,      //是否缓存
					success:function(result){    //请求成功后执行
						updateMess(result);
					}
				});
			}

			function updateMess(result){
				
				if(result['anonymity']==1){
					$("#header>a>img").attr('src','../img/ni_img.png');
					$(".from").text('From:'+result['fromname']);
					$("#header>a").removeAttr('href');
				}else{
					$("#header>a>img").attr('src',result['headimgurl']);
					$("#header>a").attr('href','userPage_getTargetHome.action?targetUid='+result['uid']);
					$(".from").text('From:'+result['nickname']);
				}
				if(result['mtype']=='pray'){
					$(".messBlock").addClass("prayimg pointimg");
				}else if(result['mtype']=='sayhi'){
					$(".messBlock").addClass("sayhiimg pointimg");
				}else{
					$(".messBlock").addClass("complainimg pointimg");
				}
				
				$(".message").text(result['content']);
				$(".to").text('TO:'+result['toyou']);
				$(".dtt").text(result['simpleTime']);
				$(".zan>span").text(result['zantimes']+'赞');
				
				if(result['sstate']>0){
					$(".zan>button>img").attr('src','../img/heart_red.png');
				}else{
					$(".zan>button>img").attr('src','../img/heart_white.png');
				}
				$(".zan>button>img").attr('mid',result['mid']);
				
			}
			
		</script>
		
	</body>
 
</html>

