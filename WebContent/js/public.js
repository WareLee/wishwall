$(document).ready(function() {
	clickHeart();
});
function clickHeart(){
	$(".zan>img").live("click",function (){
		var imgsrc=$(this).attr('src');
		var mid=$(this).attr('mid');
		var zanNum=parseInt($(this).siblings().eq(0).text());
		if(imgsrc=='../img/heart_red.png'){
			$(this).attr('src','../img/heart_white.png');
			if(zanNum>0){
				$(this).siblings().eq(0).text((zanNum-1)+'赞');
			}
		}else{
			$(this).attr('src','../img/heart_red.png');
			$(this).siblings().eq(0).text((zanNum+1)+'赞');
		}
		
		 $.ajax({
				url: 'enter/homePage_doZan.action?targetMid='+mid,     //要访问的service路径
				type: "get",        //访问实现的方式:get 或 post
				async:true,      //是否异步
				cache:false,      //是否缓存
				success:function(result){    //请求成功后执行
				}
		});
	});
}

