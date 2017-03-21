$(document).ready(function() {
	getMoreHot();
	getMoreNewst();
	operation();
});

function getMoreHot(){
	$("#moreHot>span").click(
		function(){
			$(this).css("display","none");
			$("#mosthot").children("section").css("display", "block");
		}
	);
}

function getMoreNewst(){
	$("#moreNewst>span").click(
		function(){
			// 前端需要告诉后台当前页码,页面长
			var zongshu = $(this).attr("messagepage.zongshu");
			var yema=$(this).attr("messagepage.yema");
			var yechang=$(this).attr("messagepage.yechang");
			if((zongshu-yema*yechang)>0 ){
				$.ajax({
					url: 'enter/homePage_getMoreMess.action?messagePage.zongshu='+zongshu+"&messagePage.yema="+yema+"&messagePage.yechang="+yechang,     //要访问的service路径
					type: "get",        //访问实现的方式:get 或 post
					async:true,      //是否异步
					cache:false,      //是否缓存
					success:function(result){    //请求成功后执行
						for (var i=0;i<result['messList'].length;i++){
							var template=$("#template").html();
							if(result['messList'][i]['anonymity']==0){
								template=template.replace("TARGETUID",'userPage_getTargetHome.action?targetUid='+result['messList'][i]['uid']);
								template=template.replace("HEADIMGURL",result['messList'][i]['headimgurl']);
							}else{
								template=template.replace('href="TARGETUID"','');// 移除href属性
								template=template.replace("HEADIMGURL","../img/ni_img.png");
							}
							if(result['messList'][i]['mtype']=='complain'){
								template=template.replace("PRAYIMG","complainimg");
							}else if(result['messList'][i]['mtype']=='pray'){
								template=template.replace("PRAYIMG","prayimg");
							}else{
								template=template.replace("PRAYIMG","sayhiimg");
							}
							template=template.replace("FROM",result['messList'][i]['fromname']);
							template=template.replace("CONTENT",result['messList'][i]['content']);
							template=template.replace("DESTINATION",result['messList'][i]['toyou']);
							template=template.replace("ZAN",result['messList'][i]['zantimes']);
							template=template.replace("SIMPLETIME",result['messList'][i]['simpleTime']);
							template=template.replace("MID",result['messList'][i]['mid']);
							if(result['messList'][i]['sstate']==0){
								template=template.replace("HEART","../img/heart_white.png");	
							}else{
								template=template.replace("HEART","../img/heart_red.png");	
							}
							
							$("#weekNew").append(template);
						}
						if(result['havemore']>0){
							$("#moreNewst>span").attr("messagepage.zongshu",result['zongshu']);
							$("#moreNewst>span").attr("messagepage.yema",result['yema']);
							$("#moreNewst>span").attr("messagepage.yechang",result['yechang']);
						}else{
							$("#moreNewst").css("display","none");
							$("#theend").css("display","block");
						}
					}
				});
				
			}else{
				$("#moreNewst").css("display","none");
				$("#theend").css("display","block");
			}
			 
		}
	);
}

function operation(){
	var state=false;
	$("#btn>img").click(
		function(){
			if(!state){
				state=true;
				$("#btn").addClass("rotate-show");
				$("#mask").css("display","block");
				
				$("#mask").addClass("fade-show");
				$("#topray>a>span").addClass("span-anima");
				$("#shake>a>span").addClass("span-anima2");
				$("#topray>a>img").addClass("icon-anima");
				$("#shake>a>img").addClass("icon-anima");
			}else{
				state=false;
				$("#btn").removeClass("rotate-show");
				$("#mask").removeClass("fade-show");
				$("#topray>a>span").removeClass("span-anima");
				$("#shake>a>span").removeClass("span-anima2");
				$("#topray>a>img").removeClass("icon-anima");
				$("#shake>a>img").removeClass("icon-anima");
			}
			
		}
	);
	
	$("#mask").click(
		function(){
			state=false;
			$("#btn").removeClass("rotate-show");
			$("#mask").removeClass("fade-show");
			$("#topray>a>span").removeClass("span-anima");
			$("#shake>a>span").removeClass("span-anima2");
			$("#topray>a>img").removeClass("icon-anima");
			$("#shake>a>img").removeClass("icon-anima");
			
			$("#mask").css("display","none");
		}
	);
}

