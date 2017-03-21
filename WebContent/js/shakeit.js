$(document).ready(function() {
	var state=false;
	
	var SHAKE_THRESHOLD = 800;
	var last_update = 0;
	var x = y = z = last_x = last_y = last_z = 0;

	if(window.DeviceMotionEvent) {
		window.addEventListener('devicemotion', deviceMotionHandler, false);
	} else {
		alert('本设备不支持devicemotion事件');
	}
	
	deviceMotionHandler(eventData);
	
});

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
		var status = document.getElementById("status");

		if(speed > SHAKE_THRESHOLD) {
			doResult();
		}
		last_x = x;
		last_y = y;
		last_z = z;
	}
}

function doResult() {
	if(state){
		document.getElementById("mess").className="mess mess-hid";
		document.getElementById("xiaoguo").className = "xiaoguo-show";
		document.getElementById("loading").className = "loading loading-show";
		
		state=false;
		
	}else{
		document.getElementById("loading").className = "loading loading-show";
		setTimeout(function() {
			document.getElementById("loading").className = "loading";
			document.getElementById("xiaoguo").className = "xiaoguo-hid";

			// 请求数据
			getRandomMess();
			document.getElementById("mess").className="mess mess-show";
			state=true;
		}, 1000);
	}
	
}


function getRandomMess(){
	$.ajax({
		url: 'enter/randomMess.action',     //要访问的service路径
		type: "post",        //访问实现的方式:get 或 post
		async:true,      //是否异步
		cache:false,      //是否缓存
		success:function(result){    //请求成功后执行
			console.log(result)
			updateMess(result);
		}
	});
}

function updateMess(result){
	$("#header>img").attr('src','#');
	$(".messBlock").addClass("prayimg pointimg");
	$(".from").text("From:???");
	$(".message").text("message...");
	$(".to").text("TO:???");
	$(".dtt").text("time..");
	$(".zan>span").text("800赞");
	$(".zan>img").attr('src','');
	$(".zan>img").attr('mid',3);
	
}